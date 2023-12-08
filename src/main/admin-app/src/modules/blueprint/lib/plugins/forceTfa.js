export default defineNuxtPlugin((app) => {
  const { forceTfa: forceTfaOptions } = app.$bp.options

  addRouteMiddleware('force-tfa', () => {
    const { $auth } = useNuxtApp()
    const user = $auth.user.value

    if (
      $auth.loggedIn.value &&
      user.tfa_forced &&
      !user.tfa_enabled &&
      route.path !== forceTfaOptions.redirect
    ) {
      if (forceTfaOptions.handler) {
        forceTfaOptions.handler(forceTfaOptions)
      } else {
        if (forceTfaOptions.alertKey) {
          app.$alerts.error(
            $bp.$t('errors.forceEnableTfa'),
            forceTfaOptions.alertKey
          )
          $bp.persistAlerts = true
        }

        if (forceTfaOptions.redirect) {
          app.$router.replace(forceTfaOptions.redirect)
        }
      }
    }
  })
})
