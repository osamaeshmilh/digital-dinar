export default {
  /// Alert
  removeAlert(...args) {
    useNuxtApp().$alerts.remove(...args)
  },
  deleteAlerts() {
    useNuxtApp().$alerts.clear()
  },
  alertSuccess(...args) {
    useNuxtApp().$alerts.success(...args)
  },
  alertInfo(...args) {
    useNuxtApp().$alerts.info(...args)
  },
  alertError(...args) {
    useNuxtApp().$alerts.error(...args)
  },
}
