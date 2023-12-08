export default {
  notifySuccess(...args) {
    useNuxtApp().$notifications.success(...args)
  },
  notifyInfo(...args) {
    useNuxtApp().$notifications.info(...args)
  },
  notifyError(...args) {
    useNuxtApp().$notifications.error(...args)
  },
}
