import { Model } from '@vuex-orm/core'
import _get from 'lodash/get'
import _set from 'lodash/set'

export default class BaseModel extends Model {
  // call this function in your api requests to inject params from traits
  static apiParams(params = {}) {
    return {
      params: {
        ...this.traits
          .filter((trait) => !!trait.traitApiParams)
          .map((trait) => trait.traitApiParams(this))
          .reduce((previous, v) => ({ ...previous, ...v }), {}),
        ...params,
      },
    }
  }

  // call this function in model state() to inject state from traits
  static getTraitState() {
    return this.traits
      .filter((trait) => !!trait.traitState)
      .map((trait) => trait.traitState(this))
      .reduce((previous, v) => ({ ...previous, ...v }), {})
  }

  // call this function in model apiConfig.actinos to inject api methods from traits
  static getTraitApiConfig(...args) {
    return this.traits
      .filter((trait) => !!trait.traitApiConfig)
      .map((trait) => trait.traitApiConfig(this, ...args))
      .reduce((previous, v) => ({ ...previous, ...v }), {})
  }

  static $bp() {
    return this.store().$bp
  }

  static getState(key = false, defaultValue = false) {
    return key
      ? computed(() =>
          _get(this.store().state.entities[this.entity], key, defaultValue),
        ).value
      : computed(() => this.store().state.entities[this.entity]).value
  }

  static loading(key) {
    return this.getState('loading.' + key)
  }

  static setState(key, value) {
    this.commit((state) => _set(state, key, value))
  }

  static transformData(data = []) {
    return data
      .map((d, order) => ({
        ...d,
        order,
      }))
      .map(({ id, order, ...data }) => ({
        id,
        order,
        data: { id, ...data },
      }))
  }

  get createdAt() {
    return (
      useNuxtApp()
        .$dayjs?.(this.data.createdDate)
        .utcOffset(0)
        .format('YYYY-MM-DD HH:mmA') ?? this.created_at
    )
  }
  get updatedAt() {
    return (
      useNuxtApp()
        .$dayjs?.(this.data.updatedDate)
        .utcOffset(0)
        .format('YYYY-MM-DD HH:mmA') ?? this.updated_at
    )
  }

  // default attributes
  static state() {
    return {
      ...this.getTraitState(),
    }
  }

  static fields() {
    return {
      id: this.attr(),
      data: this.attr({}),
      order: this.attr(''),
    }
  }

  static tableHeaders = [
    { text: 'attributes.id', value: 'id' },
    { text: 'attributes.created_at', value: 'createdAt', width: '180px' },
  ]
}
