export default defineNuxtPlugin((app) => {
  app.$store.registerModule('theme', themeModule)

  app.provide(
    'theme',
    reactive({
      miniDrawer: computed({
        get: () => app.$store.state.theme.miniDrawer,
        set: (value) => app.$store.commit('theme/SET_MINI_DRAWER', value),
      }),
    })
  )
})

const themeModule = {
  namespaced: true,
  state: () => ({
    miniDrawer: localStorage.getItem('MINI_DRAWER') === 'true',
  }),
  mutations: {
    SET_MINI_DRAWER(state, value) {
      localStorage.setItem('MINI_DRAWER', value)
      state.miniDrawer = value
    },
  },
  actions: {
    setMiniDrawer({ commit }, value) {
      commit('SET_MINI_DRAWER', value)
    },
  },
}
