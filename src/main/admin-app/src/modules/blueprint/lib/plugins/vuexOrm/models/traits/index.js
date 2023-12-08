import notification from './notifications'
import pagination from './pagination'
import filter from './filter'
import alerts from './alerts'
import bpApi from './bpApi'
import backendFilters from './backendFilters'

export function addTrait(target, ...sources) {
  sources.forEach((source) => {
    const descriptors = Object.keys(source).reduce((descriptors, key) => {
      descriptors[key] = Object.getOwnPropertyDescriptor(source, key)
      return descriptors
    }, {})

    Object.getOwnPropertySymbols(source).forEach((sym) => {
      const descriptor = Object.getOwnPropertyDescriptor(source, sym)
      if (descriptor.enumerable) {
        descriptors[sym] = descriptor
      }
    })
    Object.defineProperties(target, descriptors)
  })
  return target
}

export default {
  pagination,
  notification,
  alerts,
  filter,
  bpApi,
  backendFilters,
}
