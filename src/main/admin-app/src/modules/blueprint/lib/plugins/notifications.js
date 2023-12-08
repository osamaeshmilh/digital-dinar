export default defineNuxtPlugin((app) => {
  app.$store.registerModule('notifications', notificationsModule)

  app.provide('notifications', {
    _uniqueId: (prefix) =>
      `${prefix}_` + Math.random().toString(36).substr(2, 9),
    success(text = '', attrs = {}) {
      text &&
        app.$store.dispatch('notifications/add', {
          id: this._uniqueId('notification_'),
          color: 'success',
          text,
          attrs,
        })
    },
    info(text = '', attrs = {}) {
      text &&
        app.$store.dispatch('notifications/add', {
          id: this._uniqueId('notification_'),
          color: 'info',
          text,
          attrs,
        })
    },
    error(text = '', attrs = {}) {
      text &&
        app.$store.dispatch('notifications/add', {
          id: this._uniqueId('notification_'),
          color: 'error',
          text,
          attrs,
        })
    },
    warning(text = '', attrs = {}) {
      text &&
        app.$store.dispatch('notifications/add', {
          id: this._uniqueId('notification_'),
          color: 'warning',
          text,
          attrs,
        })
    },
    remove: (id) => app.$store.dispatch('notifications/remove', id),
    clear: () => app.$store.dispatch('notifications/clear'),
    all: () => app.$store.getters['notifications/all'],
  })
})

const notificationsModule = {
  namespaced: true,
  state: () => ({
    notifications: [],
  }),
  getters: {
    all: (state) => state.notifications,
  },
  mutations: {
    ADD_NOTIFICATION: (state, data) => state.notifications.push(data),
    REMOVE_NOTIFICATION: (state, id) => {
      const index = state.notifications.findIndex((item) => item.id === id)
      if (index !== -1) {
        state.notifications.splice(index, 1)
      }
    },
    DELETE_NOTIFICATIONS: (state) => (state.notifications = []),
  },

  actions: {
    add: ({ commit }, notification) => commit('ADD_NOTIFICATION', notification),
    remove: ({ commit }, id) => commit('REMOVE_NOTIFICATION', id),
    clear: ({ commit }) => commit('DELETE_NOTIFICATIONS'),
  },
}

// export default defineNuxtPlugin((app) => {
//   app.$store.registerModule('notifications', notificationModule)

//   app.provide('notifications', {
//     success: (message = '', attrs = {}) =>
//       message &&
//       app.$store.dispatch('notifications/addNotification', {
//         message,
//         attrs,
//         type: 'success',
//       }),
//     info: (message = '', attrs = {}) =>
//       message &&
//       app.$store.dispatch('notifications/addNotification', {
//         message,
//         attrs,
//         type: 'info',
//       }),
//     warning: (message = '', attrs = {}) =>
//       message &&
//       app.$store.dispatch('notifications/addNotification', {
//         message,
//         attrs,
//         type: 'warning',
//       }),
//     error: (message = '', attrs = {}) =>
//       message &&
//       app.$store.dispatch('notifications/addNotification', {
//         message,
//         attrs,
//         type: 'error',
//       }),
//   })
// })

// const notificationModule = {
//   namespaced: true,
//   state: () => ({
//     notificationQueue: [],
//     notification: false,
//     notificationText: '',
//   }),
//   mutations: {
//     ADD_NOTIFICATION: (state, data) => state.notificationQueue.push(data),
//     SET_NOTIFICATION: (state, data) => (state.notification = data),
//     SET_NOTIFICATION_TEXT: (state, data) => (state.notificationText = data),
//     SHIFT_NOTIFICATION_QUEUE: (state) => {
//       const remove = state.notificationQueue.shift()
//       return remove
//     },
//   },
//   getters: {
//     hasNotificationsPending: (state) => state.notificationQueue.length > 0,
//   },
//   actions: {
//     addNotification: ({ commit, state }, text) => {
//       commit('ADD_NOTIFICATION', text)
//       if (!state.notification) {
//         commit('SET_NOTIFICATION_TEXT', [...state.notificationQueue].shift())
//         commit('SHIFT_NOTIFICATION_QUEUE')
//         commit('SET_NOTIFICATION', true)
//       }
//     },
//     setNotification: ({ commit }, data) => commit('SET_NOTIFICATION', data),
//     setNotificationText: ({ commit }, data) =>
//       commit('SET_NOTIFICATION_TEXT', data),
//     shiftNotificationQueue: ({ commit }) => commit('SHIFT_NOTIFICATION_QUEUE'),
//   },
// }
