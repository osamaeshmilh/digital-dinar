import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import ar from 'vuetify/lib/locale/ar'
import en from 'vuetify/lib/locale/en'
import _merge from 'lodash/merge'

export default defineNuxtPlugin(async (app) => {
  const bpOptions = app.$bp.options
  const vuetify = createVuetify({
    ...(bpOptions.locales
      ? {
          locale: {
            locale: computed(() => bpOptions.locales.current),
            messages: { ar, en },
          },
        }
      : {}),
    ...bpOptions.vuetify.args,
  })

  app.vueApp.use(vuetify)
  app.provide('vuetify', vuetify)
})
