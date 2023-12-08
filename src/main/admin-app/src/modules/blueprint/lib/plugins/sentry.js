import * as Sentry from '@sentry/vue'

export default defineNuxtPlugin((nuxtApp) => {
  const { sentry: sentryOptions } = nuxtApp.$bp.options

  Sentry.init({ app: nuxtApp.vueApp, ...(sentryOptions.args || {}) })

  if (sentryOptions.setUser) {
    nuxtApp.$store.watch(
      (state) => state.auth.user,
      (user) => Sentry.setUser(user),
      { immediate: true }
    )
  }
})
