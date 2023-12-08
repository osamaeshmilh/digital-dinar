import { Database, Model, Container, use } from '@vuex-orm/core'

import VuexORMAxios from '@vuex-orm/plugin-axios'
use(VuexORMAxios)

let database = new Database()
const models = import.meta.glob('@/models/*.js', { eager: true })

Object.keys(models).forEach((name) => {
  if (name === './BaseModel.js') return

  const classOnModel = models[name].default
  if (classOnModel.prototype instanceof Model) {
    const vuexModule = classOnModel[classOnModel.name + 'Vuex']
    if (vuexModule !== undefined) database.register(classOnModel, vuexModule)
    else database.register(classOnModel)
  }
})

export default defineNuxtPlugin((app) => {
  const { vuexOrm } = app.$bp.options

  if (vuexOrm.autoImport) {
    database.start(app.$store, 'entities')
    Container.register(app.$store)
  }

  if (app.$auth && vuexOrm.clearOnLogout) {
    watch(
      () => app.$store.state.auth.user,
      (value) =>
        !value &&
        setTimeout(() => app.$resetOrmState(), vuexOrm.clearOnLogoutTimeout)
    )
  }

  app.$bp?.options?.plugins?.axios && app.$axios && Model.setAxios(app.$axios)

  app.provide('resetOrmState', () => {
    app.$store.dispatch('entities/deleteAll')
    app.$store
      .$db()
      .entities.forEach((entity) =>
        entity.model.commit((state) =>
          Object.assign(state, entity.model.state())
        )
      )
  })
  app.provide('ormModel', (model) => app.$store.$db().model(model))
})
