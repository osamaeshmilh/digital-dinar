export default defineNuxtPlugin((app) => {
  app.$store.registerModule('banners', bannersModule)

  app.provide('banners', {
    _uniqueId: (prefix = 'banner_') =>
      `${prefix}_` + Math.random().toString(36).substr(2, 9),
    success(text = '', attrs = {}) {
      text &&
        app.$store.dispatch('banners/add', {
          id: this._uniqueId(),
          color: 'success',
          text,
          attrs,
        })
    },
    info(text = '', attrs = {}) {
      text &&
        app.$store.dispatch('banners/add', {
          id: this._uniqueId(),
          color: 'info',
          text,
          attrs,
        })
    },
    error(text = '', attrs = {}) {
      text &&
        app.$store.dispatch('banners/add', {
          id: this._uniqueId(),
          color: 'error',
          text,
          attrs,
        })
    },
    warning(text = '', attrs = {}) {
      text &&
        app.$store.dispatch('banners/add', {
          id: this._uniqueId(),
          color: 'warning',
          text,
          attrs,
        })
    },
    remove: (id) => app.$store.dispatch('banners/remove', id),
    clear: () => app.$store.dispatch('banners/clear'),
    all: () => app.$store.getters['banners/all'],
  })
})

const bannersModule = {
  namespaced: true,
  state: () => ({
    banners: [],
  }),
  getters: {
    all: (state) => state.banners,
  },
  mutations: {
    ADD_BANNER: (state, data) => state.banners.push(data),
    REMOVE_BANNER: (state, id) => {
      const index = state.banners.findIndex((item) => item.id === id)
      if (index !== -1) {
        state.banners.splice(index, 1)
      }
    },
    DELETE_BANNERS: (state) => (state.banners = []),
  },

  actions: {
    add: ({ commit }, banner) => commit('ADD_BANNER', banner),
    remove: ({ commit }, id) => commit('REMOVE_BANNER', id),
    clear: ({ commit }) => commit('DELETE_BANNERS'),
  },
}
