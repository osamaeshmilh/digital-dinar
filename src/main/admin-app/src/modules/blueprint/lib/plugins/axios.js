import axios from 'axios'
import { stringify } from 'qs'
import _set from 'lodash/set'
import _get from 'lodash/get'
import _merge from 'lodash/merge'
import { isRef, isReactive } from 'vue'

export default defineNuxtPlugin(async (app) => {
  const bpOptions = app.$bp.options
  let controller = new AbortController()

  const instance = axios.create({
    signal: controller.signal,
    ...configDefaults,
    ...bpOptions.axios.args,
  })

  app.provide('axios', instance)

  const axiosOptions = bpOptions.axios
  /*
   * Axios cancel requests on route change
   */
  axiosOptions.cancelAfterEach &&
    app.$router.afterEach((to, from) => {
      if (to.name !== from.name) {
        controller.abort()
        controller = new AbortController()
        instance.defaults.signal = controller.signal
      }
    })

  app.$axios.interceptors.request.use((config, ...args) => {
    if (axiosOptions.onRequest) {
      return axiosOptions.onRequest(config, ...args)
    } else {
      if (
        (['put', 'patch', 'post', 'delete'].includes(config.method) &&
          config.deleteAlerts !== false) ||
        config.deleteAlerts === true
      ) {
        useNuxtApp().$alerts.clear()
      }
      config.errorBag && isRef(config.errorBag) && (config.errorBag.value = {})
      return config
    }
  })

  app.$axios.interceptors.response.use(
    (response) => response,
    (error) => {
      // Get the error handler for this request.
      const mergedErrorHandler = _merge(
        {},
        axiosOptions,
        error.config?.bpConfig?.error
      )

      // If there is an error handler, call it.
      if (mergedErrorHandler.onError) {
        return mergedErrorHandler.onError(error)
      }

      // Get the HTTP status code from the error.
      const code = parseInt(error.response && error.response.status)

      // If there is an error handler for this status code, call it.
      if (
        mergedErrorHandler?.onStatusError[code] &&
        typeof mergedErrorHandler.onStatusError[code] === 'function'
      ) {
        return mergedErrorHandler.onStatusError[code](error)
      }

      // Get the error message from the response.
      const errorMessage = _get(error.response, axiosOptions.props.errorMessage)
      // Get the error alert key from the request config.
      const errorAlertKey = error.config?.bpConfig?.error?.alertKey || 'global'

      // If there is an error message, display it to the user.
      if (errorMessage) {
        app.$alerts.error(errorMessage, errorAlertKey)
      }

      // Handle specific errors.
      switch (code) {
        case 419:
          // Request the CSRF endpoint and try again once.
          if (
            bpOptions.auth.endpoints.csrf &&
            app.$auth.loggedIn &&
            !error.config._noRetry
          ) {
            error.config._noRetry = true
            return app.$axios
              .request(bpOptions.auth.endpoints.csrf)
              .then(() => app.$axios.request(error.config))
          } else {
            if (!errorMessage) {
              app.$alerts.error(app.$t('errors.other'), errorAlertKey)
            }
          }
          break
        case 401:
          // Reset the authentication state.
          app.$auth.reset()
          break
        case 403:
          // If there is a onUnauthorized callback for this error, call it.
          if (app.$guard?.onUnauthorized) {
            app.$guard.onUnauthorized(error)
          }
          break
        case 422:
          // Set the error bag on the error object.
          error.errorBag = handleValidation(
            error.response,
            error.config.errorBag || {}
          )
          break
        case 404:
          // If this is a GET request and the request config has a "notFound" property,
          // throw an error with the specified status code and message.
          if (
            error.config.method === 'get' &&
            error.config?.bpConfig?.error?.notFound
          ) {
            throw createError({ fatal: true, statusCode: 404 })
          }
          break
        default:
          // If there is no error message, display a generic error message to the user.
          if (!errorMessage) {
            app.$alerts.error(app.$t('errors.other'), errorAlertKey)
          }
      }

      // Return a rejected promise with the error object.
      return Promise.reject(error)
    }
  )
})

const configDefaults = {
  transformRequest: (data) => {
    if (!(data instanceof FormData) && typeof data === 'object') {
      const needsRewrite = Object.keys(data).some((key) => key.includes('.'))
      if (needsRewrite) {
        data = Object.entries(data).reduce(
          (pv, [k, v]) => _merge(pv, _set({}, k, v)),
          {}
        )
      }
      return JSON.stringify(data)
    }

    return data
  },
  headers: {
    'Content-Type': 'application/json',
  },
  paramsSerializer: {
    serialize: (params) => stringify(params, { arrayFormat: 'indices' }),
  },
}
