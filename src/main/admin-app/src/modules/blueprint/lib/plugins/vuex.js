import { createStore } from 'vuex'

export default defineNuxtPlugin((app) => {
  const bpOptions = app.$bp.options
  const store = createStore({
    ...(bpOptions.vuex.args || {}),
  })
  app.vueApp.use(store)
  app.provide('store', store)
})
