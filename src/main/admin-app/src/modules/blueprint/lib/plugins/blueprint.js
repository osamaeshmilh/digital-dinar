import { reactive } from 'vue'
import _merge from 'lodash/merge'
import * as config from '#bp-options'

export default defineNuxtPlugin(async (app) => {
  let options = await fetchRuntimeOptions(
    `blueprint`,
    _convertFunctions(config.options)
  )
  const bpOptions = useState('blueprint-options', () => reactive(options)).value

  app.vueApp.mixin({
    mounted() {
      this.$el.__vueComponent = this
    },
  })

  app.provide('bp', {
    options: bpOptions,
    $theme: bpOptions.theme.options,
  })

  if (options.head && typeof options.head === 'object') {
    useHead(options.head)
  }
})

/**
 * Recursively converts function strings in an object to actual functions.
 * @param {Object} obj - The JavaScript object to convert.
 * @returns {Object} - The converted JavaScript object with actual functions.
 */
function _convertFunctions(obj) {
  for (var key in obj) {
    if (typeof obj[key] === 'object') {
      _convertFunctions(obj[key])
    } else if (
      typeof obj[key] === 'string' &&
      obj[key].startsWith('FUNCTION=>')
    ) {
      const func = obj[key].replace('FUNCTION=>', '')
      obj[key] = eval('(' + func + ')') // new Function(func)
    }
  }
  return obj
}
