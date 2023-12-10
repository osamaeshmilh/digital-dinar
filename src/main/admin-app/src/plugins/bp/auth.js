import _get from 'lodash/get'

export default defineNuxtPlugin(async (app) => {
  const { auth: authOptions } = app.$bp.options

  app.$store.registerModule('auth', authModule)

  app.provide('auth', {
    async login(data = {}, config = { fetchUser: true }) {
      if (authOptions.endpoints.csrf) {
        await app.$axios.request(authOptions.endpoints.csrf)
      }
      return app.$axios
        .request({ ...authOptions.endpoints.login, ...data, ...config })
        .then(async (data) => {
          config.fetchUser && (await this.fetchUser())
          authOptions.onLogin && authOptions.onLogin()
          return data
        })
    },
    fetchUser(data = {}) {
      return app.$axios
        .request({ ...authOptions.endpoints.user, ...data })
        .then(({ data }) => {
          this.setUser(_get(data, authOptions.props.user || '', data))
        })
        .catch(() => {})
    },
    logout(data = {}) {
      this.reset()
      authOptions.onLogout && authOptions.onLogout()
    },
    reset() {
      this.setUser(null)
      useCookie('app-token').value = null
      delete app.$axios.defaults.headers.common['Authorization']
    },
    setUser: (data) => app.$store.dispatch('auth/setUser', data),
    loggedIn: computed(() => app.$store.getters['auth/loggedIn']),
    user: computed(() => app.$store.getters['auth/user']),
    options: authOptions,
  })

  addRouteMiddleware('guest', (to) => {
    if (app.$store.getters['auth/loggedIn']) {
      if (to.path !== authOptions.redirect.home) {
        return app.$router.push(authOptions.redirect.home)
      }
    }
  })
  addRouteMiddleware('auth', (to) => {
    if (!app.$store.getters['auth/loggedIn']) {
      if (to.path !== authOptions.redirect.login) {
        return app.$router.push({
          path: authOptions.redirect.login,
          ...(authOptions.redirect.home !== to.path
            ? { query: { redirect: to.path } }
            : {}),
        })
      }
    }
  })
})

const authModule = {
  namespaced: true,
  state: () => ({
    user: null,
  }),
  mutations: {
    SET_USER: (state, data) => (state.user = data),
  },
  getters: {
    loggedIn: (state) => !!state.user,
    user: (state) => state.user,
  },
  actions: {
    setUser: ({ commit }, data) => commit('SET_USER', data),
  },
}
