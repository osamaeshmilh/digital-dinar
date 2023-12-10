export default defineNuxtPlugin((app) => {
  app.$store.registerModule('alerts', {
    state: () => ({
      alerts: [],
      persistAlerts: false,
    }),
  })
  app.provide('alerts', {
    remove: (id) => {},
    clear: () => {},
    clearByKey: (key) => {},
    all: () => [],
    setPersistAlerts(value) {},
    _uniqueId: (prefix = 'banner_') =>
      `${prefix}_` + Math.random().toString(36).substr(2, 9),
    success(text = '', key, attrs = {}) {
      text &&
        app.$store.dispatch('banners/add', {
          id: this._uniqueId(),
          color: 'success',
          text,
          attrs,
        })
    },
    info(text = '', key, attrs = {}) {
      text &&
        app.$store.dispatch('banners/add', {
          id: this._uniqueId(),
          color: 'info',
          text,
          attrs,
        })
    },
    error(text = '', key, attrs = {}) {
      text &&
        app.$store.dispatch('banners/add', {
          id: this._uniqueId(),
          color: 'error',
          text,
          attrs,
        })
    },
    warning(text = '', key, attrs = {}) {
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
