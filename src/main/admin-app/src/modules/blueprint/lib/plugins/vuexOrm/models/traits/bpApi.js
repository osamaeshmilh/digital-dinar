/*
 bpConfig: {
  request: (inlineParams, config) => {}
   loading: '', // loading state path
   apiParams: true||false, // calls model.apiParams()
   success: {
     persistAlert: true,
     redirect: '/route/{id}',
     notifyResponse: '', // path to response message
     notify: 'translated.message' || []
     alertResponse: '', // path to response message
     alert: 'translated.message' || [],
     alertKey: '',
     populateKey: ''
   },
   successHandler: function(model, response, success, config) {}
   error: {
     persistAlert: true,
     redirect: '/route/{id}',
     notifyResponse: '', // path to response message
     notify: 'translated.message' || []
     alertResponse: '', // path to response message
     alert: 'translated.message' || [],
     alertKey: '',
   },
   errorBag: {} // should be passed from component
   errorHandler: function(model, response, success, config) {}
 }
 */
import _get from 'lodash/get'
import _set from 'lodash/set'
import _merge from 'lodash/merge'

export default {
  crudApi(model, overrides) {
    const entity = model.apiEntity || model.entity
    const camelizedEntity = camelize(model.apiAlertEntity || entity)

    return _merge(
      {
        fetch: {
          persistBy: 'create',
          url: `/${entity}`,
          dataTransformer: ({ data }) => {
            data.meta?.filters &&
              model.setState('filtersResponse', data.meta.filters)
            data.meta && model.handlePaginationResponse?.(data.meta)
            return model.transformData(data.data)
          },
          bpConfig: {
            loading: 'loading.fetch',
            apiParams: true,
            error: { alertKey: `${camelizedEntity}Index` },
          },
        },
        fetchList: {
          save: false,
          url: `/${entity}?per_page=all`,
          bpConfig: {
            loading: 'loading.fetchList',
          },
        },
        fetchOne: {
          persistBy: 'insertOrUpdate',
          url: `/${entity}/{id}`,
          dataKey: 'data',
          bpConfig: {
            loading: 'loading.fetchOne',
            error: { notFound: true },
          },
        },
        create: {
          method: 'POST',
          dataKey: 'data',
          url: `/${entity}`,
          bpConfig: {
            loading: 'loading.create',
            success: {
              alertKey: `${camelizedEntity}Index`,
              alert: [
                'notify.added',
                { entity: 'entities.singular.' + camelizedEntity },
              ],
            },
            error: { alertKey: `${camelizedEntity}CreateDialog` },
          },
        },
        update: {
          method: 'PATCH',
          dataKey: 'data',
          url: `/${entity}/{id}`,
          bpConfig: {
            loading: 'loading.update',
            success: {
              alertKey: `${camelizedEntity}Index`,
              alert: [
                'notify.updated',
                { entity: 'entities.singular.' + camelizedEntity },
              ],
            },
            error: { alertKey: `${camelizedEntity}UpdateDialog` },
          },
        },
        delete: {
          save: false,
          method: 'DELETE',
          url: `/${entity}/{id}`,
          bpConfig: {
            loading: 'loading.delete',
            success: {
              alertKey: `${camelizedEntity}Index`,
              alert: [
                'notify.deleted',
                { entity: 'entities.singular.' + camelizedEntity },
              ],
            },
            error: { alertKey: `${camelizedEntity}DeleteDialog` },
          },
        },
        clear: {
          save: false,
          method: 'DELETE',
          url: `/${entity}`,
          bpConfig: {
            loading: 'loading.clear',
            success: {
              alertKey: `${camelizedEntity}Index`,
              alert: [
                'notify.deleted',
                { entity: 'entities.' + camelizedEntity },
              ],
            },
            error: { alertKey: `${camelizedEntity}ClearDialog` },
          },
        },
      },
      model.apiOverrides || {},
      overrides
    )
  },
  bpApi() {
    // default api request class
    const _request = this.api()
    // api actions defined in "static apiConfig"
    const modelActions = this.apiConfig.actions
    // loop through request keys in model apiConfig actions
    Object.keys(_request).forEach((name) => {
      if (['model', 'config'].includes(name)) {
        return
      }

      // api action defined in "apiConfig.actions" eg: fetch
      const actionConfig = modelActions[name]
      // override request if it's and object and bpConfig exists
      const shouldOverride =
        typeof actionConfig === 'object' &&
        (!!actionConfig || !!this.apiConfig || !!this.globalApiConfig)

      // createBpRequest receieves defined in "static apiConfig""
      _request[name] = shouldOverride
        ? (inlineConfig) =>
            this.createBpRequest(inlineConfig, {
              ...this.globalApiConfig,
              ...this.apiConfig,
              ...actionConfig,
            })
        : _request[name]
    })

    return _request
  },
  createBpRequest(inlineConfig = {}, actionConfig = {}) {
    const bpConfig = _merge(
      actionConfig.bpConfig || {},
      inlineConfig.bpConfig || {}
    )
    const requestConfig = this._bpRequestConfig({
      ...actionConfig,
      ...inlineConfig,
    })

    !!bpConfig.loading && this.setState(bpConfig.loading, true)

    Object.keys(inlineConfig.errorBag || {}).forEach((key) =>
      _set(inlineConfig.errorBag, key, [])
    )

    return (
      bpConfig.request
        ? bpConfig.request(inlineConfig, requestConfig)
        : this.api().request(requestConfig)
    )
      .then((response) => {
        // execute bp success handler
        bpConfig.success !== undefined &&
          this.bpApiSuccessHandler(
            response.response,
            bpConfig.success,
            inlineConfig
          )

        // execute success handler defined in bpConfig
        return bpConfig.successHandler
          ? bpConfig.successHandler(
              this,
              response.response,
              bpConfig.success,
              inlineConfig
            )
          : response
      })
      .catch((error) => {
        // execute bp error handler
        bpConfig.error !== undefined &&
          this.bpApiErrorHandler(error, bpConfig.error, inlineConfig)

        // execute error handler defined in bpConfig
        if (bpConfig.errorHandler) {
          return bpConfig.errorHandler(
            this,
            error,
            bpConfig.error,
            inlineConfig
          )
        }

        if (!bpConfig.error?.catch) {
          throw error
        }
      })
      .finally(
        () => !!bpConfig.loading && this.setState(bpConfig.loading, false)
      )
  },
  bpApiSuccessHandler(response, config = {}, inlineConfig) {
    const { $alerts } = useNuxtApp()
    if ('persistAlerts' in config) {
      $alerts.setPersistAlerts(config.persistAlerts)
    }

    if (inlineConfig.populate && config.populateKey) {
      $populate(
        inlineConfig.populate,
        false,
        _get(response.data, config.populateKey)
      )
    }

    this._bpApiNotifyHandler(response, config)
    this._bpApiAlertHandler(response, config)
    const router = useRouter()
    config.redirect &&
      router.push(config.redirect.replace('{id}', inlineConfig.id))
  },
  bpApiErrorHandler(error, config = {}, inlineConfig) {
    const { $alerts, $bp } = useNuxtApp()
    if ('persistAlerts' in config) {
      $alerts.setPersistAlerts(config.persistAlerts)
    }
    this._bpApiNotifyHandler(error.response, config, 'error')
    this._bpApiAlertHandler(error.response, config, 'error')
    const router = useRouter()
    config.redirect &&
      router.push(config.redirect.replace('{id}', inlineConfig.id))

    if (
      error.response &&
      error.response.status === 422 &&
      inlineConfig.errorBag
    ) {
      handleValidation(error.response, inlineConfig.errorBag)
    }
  },
  _bpApiNotifyHandler(response = {}, config, type = 'success') {
    const notify = config.notifyResponse
      ? _get(response.data, config.notifyResponse, '')
      : Array.isArray(config.notify)
      ? $t.call(this, ...config.notify)
      : typeof config.notify === 'string'
      ? $t(config.notify)
      : false

    const { $notifications } = useNuxtApp()
    notify && type === 'error' && $notifications.error(notify)
    notify && type === 'success' && $notifications.success(notify)
  },
  _bpApiAlertHandler(response = {}, config, type = 'success') {
    const alert = this._bpGetAlert(config, response)
    const { $alerts } = useNuxtApp()
    alert && type === 'error' && $alerts.error(alert, config.alertKey)
    alert && type === 'success' && $alerts.success(alert, config.alertKey)
  },
  _bpRequestConfig(config) {
    if (config.url?.includes('{')) {
      Object.keys(config).forEach((param) => {
        config.url = config.url.replace(`{${param}}`, config[param])
      })
    }

    if (config.bpConfig?.apiParams) {
      config.params = { ...this.apiParams().params, ...config.params }
    }

    return config
  },
  _bpGetAlert(config, response) {
    const alertResponse = config.alertResponse
    let alertMessage = false

    if (alertResponse) {
      alertMessage = _get(response.data, alertResponse, '')
    } else if (Array.isArray(config.alert)) {
      let [alertKey, alertOptions] = config.alert

      if (alertOptions) {
        if (alertOptions.entity) {
          // improve?: translate all object values
          alertOptions = { entity: $t(alertOptions.entity) }
        }
      } else {
        alertOptions = {}
      }

      alertMessage = $t.call(this, alertKey, alertOptions)
    } else if (typeof config.alert === 'string') {
      alertMessage = $t(config.alert)
    }

    return alertMessage
  },
}
