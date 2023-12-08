export default defineNuxtPlugin((app) => {
  const { alerts: alertsOptions } = app.$bp.options
  app.$store.registerModule('alerts', alertModule)

  watch(
    () => app.$router.currentRoute.value,
    () => {
      const persistAlerts = app.$store.state.alerts.persistAlerts
      !persistAlerts && app.$store.dispatch('alerts/clear')
      app.$store.dispatch('alerts/setPersistAlerts', false)
    }
  )

  app.provide('alerts', {
    _uniqueId: (prefix = 'alert_') =>
      `${prefix}_` + Math.random().toString(36).substr(2, 9),
    success(text = '', key = 'global', attrs = {}) {
      text &&
        app.$store.dispatch('alerts/add', {
          text,
          key,
          type: 'success',
          id: this._uniqueId(),
          attrs,
        })
    },
    warning(text = '', key = 'global', attrs = {}) {
      text &&
        app.$store.dispatch('alerts/add', {
          text,
          key,
          type: 'warning',
          id: this._uniqueId(),
          attrs,
        })
    },
    info(text = '', key = 'global', attrs = {}) {
      text &&
        app.$store.dispatch('alerts/add', {
          text,
          key,
          type: 'info',
          id: this._uniqueId(),
          attrs,
        })
    },
    error(text = '', key = 'global', attrs = {}) {
      text &&
        app.$store.dispatch('alerts/add', {
          text,
          key,
          type: 'error',
          id: this._uniqueId(),
          attrs,
        })
    },
    remove: (id) => app.$store.dispatch('alerts/remove', id),
    clear: () => app.$store.dispatch('alerts/clear'),
    clearByKey: (key) => app.$store.dispatch('alerts/clearByKey', key),
    all: () => app.$store.getters['alerts/all'],
    setPersistAlerts(value) {
      app.$store.dispatch('alerts/setPersistAlerts', value)
    },
  })

  if (alertsOptions.removeTimeout) {
    app.$store.subscribe((mutation) => {
      if (
        mutation.type === 'alerts/ADD_ALERT' &&
        mutation.payload?.attrs?.persist !== true
      ) {
        setTimeout(
          () => app.$alerts.remove(mutation.payload.id),
          alertsOptions.removeTimeout
        )
      }
    })
  }
})

const alertModule = {
  namespaced: true,
  state: () => ({
    alerts: [],
    persistAlerts: false,
  }),
  getters: {
    all: (state) => state.alerts,
  },
  mutations: {
    SET_PERSIST_ALERTS: (state, data) => (state.persistAlerts = data),
    ADD_ALERT: (state, data) => state.alerts.push(data),
    REMOVE_ALERT: (state, id) => {
      state.alerts = state.alerts.filter((alert) => alert.id !== id)
    },
    CLEAR_ALERTS: (state) => (state.alerts = []),
    CLEAR_KEY_ALERTS: (state, key) =>
      (state.alerts = state.alerts.filter((alert) => key !== alert.key)),
  },
  actions: {
    add: ({ commit }, alert) => commit('ADD_ALERT', alert),
    remove: ({ commit }, id) => commit('REMOVE_ALERT', id),
    clear: ({ commit }) => commit('CLEAR_ALERTS'),
    clearByKey: ({ commit }, key = '') => commit('CLEAR_KEY_ALERTS', key),
    setPersistAlerts: ({ commit }, persist) =>
      commit('SET_PERSIST_ALERTS', persist),
  },
}
