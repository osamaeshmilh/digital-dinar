import _merge from 'lodash/merge'
import _get from 'lodash/get'

export default defineNuxtPlugin(async (app) => {
  const bpOptions = app.$bp.options
  const { locales: bpLocaleSettings } = bpOptions

  let runtimeLocales = await fetchRuntimeOptions('locales')

  let activeLocale = bpLocaleSettings.multiLang
    ? useCookie('app-locale').value || bpLocaleSettings.default
    : bpLocaleSettings.default

  const invalidActiveLocale =
    !Object.keys(appLocales).includes(activeLocale) &&
    bpLocaleSettings.default !== activeLocale

  invalidActiveLocale && (activeLocale = bpLocaleSettings.default)

  const allLocaleMessages = _merge(blueprintLocales, appLocales, runtimeLocales)
  bpOptions.locales.current = activeLocale
  bpOptions.locales.all = Object.keys(appLocales)
  bpOptions.locales.messages = allLocaleMessages

  app.provide('t', (string = '', terms = {}, config = {}) => {
    if (!string) return ''
    let translation = _get(
      allLocaleMessages[bpOptions.locales.current],
      string,
      config.default ?? string
    )
    Object.keys(terms).forEach(
      (term) =>
        (translation = translation.replace('{' + term + '}', terms[term]))
    )
    return (terms.prefix || '') + translation + (terms.suffix || '')
  })

  const localeHead = useHead({
    bodyAttrs: { class: 'bp-locale-' + bpOptions.locales.current },
  })
  watch(
    () => bpOptions.locales.current,
    (locale) => {
      localeHead?.patch?.({ bodyAttrs: { class: 'bp-locale-' + locale } })
      useCookie('app-locale', { maxAge: 1735707600 }).value = locale
      refreshNuxtData()
      app.$axios &&
        (app.$axios.defaults.headers.common[bpLocaleSettings.localeApiHeader] =
          locale)
    },
    {
      immediate: true,
    }
  )

  app.vueApp.directive('locale-text', (el, binding) => {
    el.innerHTML = Array.isArray(binding.value)
      ? $t(...binding.value)
      : $t(binding.value)
  })
})

const _appLocaleFiles = import.meta.glob(
  [
    '@/locales/*.js',
    '@/locales/*.json',
    '@/locales/*/*.js',
    '@/locales/*/*.json',
  ],
  { eager: true }
)
const appLocales = Object.keys(_appLocaleFiles).reduce((pv, locale) => {
  const localeName = locale.replace(/^.*[\\\/]/, '').split('.')[0]
  return {
    ...pv,
    [localeName]: _appLocaleFiles[locale].default,
  }
}, {})

const _blueprintLocalesFiles = import.meta.glob(
  [
    './translations/*.js',
    './translations/*.json',
    './translations/*/*.js',
    './translations/*/*.json',
  ],
  { eager: true }
)

const blueprintLocales = Object.keys(_blueprintLocalesFiles).reduce(
  (pv, locale) => {
    const localeName = locale.replace(/^.*[\\\/]/, '').split('.')[0]
    return {
      ...pv,
      [localeName]: _blueprintLocalesFiles[locale].default,
    }
  },
  {}
)
