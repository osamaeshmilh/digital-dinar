export default defineNuxtPlugin(async (nuxtApp) => {
  const { auth: authOptions } = nuxtApp.$bp.options
  const token = useCookie('app-token').value
  if (token) {
    nuxtApp.$axios.defaults.headers.common = {
      Authorization: `Bearer ${token}`,
    }
    await nuxtApp.$auth.fetchUser()
  }

  nuxtApp.$store.watch(
    (state) => state.auth.user,
    (user) => {
      const loggedIn = !!user
      const currentRoute = nuxtApp.$router.currentRoute.value
      const currentRouteMiddlware = currentRoute?.meta?.middleware

      if (!currentRouteMiddlware) return

      const isGuestPage = Array.isArray(currentRouteMiddlware)
        ? currentRouteMiddlware.includes('guest')
        : currentRouteMiddlware === 'guest'

      const isAuthPage = Array.isArray(currentRouteMiddlware)
        ? currentRouteMiddlware.includes('auth')
        : currentRouteMiddlware === 'auth'
      if (loggedIn && isGuestPage) {
        if (
          currentRoute.path === authOptions.redirect.login &&
          currentRoute.query?.redirect
        ) {
          return nuxtApp.$router.push(currentRoute.query.redirect)
        }
        authOptions.redirect.home &&
          nuxtApp.$router.push(authOptions.redirect.home)
      } else if (!loggedIn && isAuthPage) {
        authOptions.redirect.login &&
          nuxtApp.$router.push(authOptions.redirect.login)
      }
    },
    { immediate: true },
  )

  if (process.server) {
    nuxtApp.payload.vuex = nuxtApp.$store.state
  } else if (nuxtApp.payload && nuxtApp.payload.vuex) {
    nuxtApp.$store.replaceState(nuxtApp.payload.vuex)
  }

  // ormApi('etravel').fetchSettingsByKey({
  //   id: 'flights_available_to_non_registered',
  // })
})
