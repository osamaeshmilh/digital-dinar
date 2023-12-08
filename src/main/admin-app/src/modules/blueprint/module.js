import { defineNuxtModule } from '@nuxt/kit'
import _mergeWith from 'lodash/mergeWith'
import * as setup from './setup'
import DEFAULTS from './defaults'

export default defineNuxtModule({
  meta: { name: 'blueprint', configKey: 'blueprint' },
  defaults: DEFAULTS,
  async setup(_, nuxt) {
    // manually merge option defaults to make arrays overridable instead of concatenated
    const options = mergeWithArrayOverride(
      DEFAULTS,
      nuxt.options.blueprint || {}
    )

    options.theme = mergeWithArrayOverride(
      options.theme.name &&
        Object.keys(options.themes).includes(options.theme.name)
        ? options.themes[options.theme.name]
        : {},
      options.theme || {}
    )

    // setup blueprint core
    !!process.env.BP_PROXY_BASE_URL && setup.setupProxy(options, nuxt)
    setup.setAliases(options, nuxt)
    setup.loadAutoImports(options, nuxt)
    options.singleEntryBuild && setup.singleEntryBuild(options, nuxt)
    options.plugins && setup.loadPlugins(options, nuxt)

    // setup blueprint theme
    if (options.theme) {
      options.theme.plugins && setup.loadThemePlugins(options, nuxt)
      options.theme.css && setup.loadThemeCss(options, nuxt)
      options.theme.components && setup.loadThemeComponents(options, nuxt)
      options.theme.composables &&
        (await setup.loadThemeComposables(options, nuxt))
      options.theme.routes && setup.loadThemeRoutes(options, nuxt)
      options.theme.layouts && setup.loadThemeLayouts(options, nuxt)
    }
  },
})

function mergeWithArrayOverride(object, sources) {
  return _mergeWith(object, sources, (objValue, srcValue) => {
    if (Array.isArray(objValue)) return srcValue
  })
}
