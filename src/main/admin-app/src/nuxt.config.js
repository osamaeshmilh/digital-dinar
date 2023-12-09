// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  app: {
    head: {
      title: 'Digital Dinar',
    },
  },
  css: ['@/assets/app.scss'],
  blueprint: {
    singleEntryBuild: true,
    plugins: { dayjs: true },
    axios: {
      props: { errorMessage: ['data.title', 'data.message'] },
    },
    theme: {
      options: {
        logo: '/logo.svg',
        miniLogo: '/logo.svg',
        appbar: {
          color: 'white',
          attrs: { class: 'elevation-default' },
          props: {},
        },
        drawer: {
          color: 'primary',
          dark: true,
          attrs: {},
          props: { logoAttrs: { height: '80px' } },
        },
      },
    },
    vuetify: {
      args: {
        defaults: {
          global: { ripple: false },
          VCheckboxBtn: { color: 'primary' },
          VCheckbox: { color: 'primary' },
          VTextField: { density: 'comfortable', variant: 'outlined' },
          VSelect: { density: 'comfortable', variant: 'outlined' },
          VAutocomplete: { density: 'comfortable', variant: 'outlined' },
          VTextarea: { density: 'comfortable', variant: 'outlined' },
          VFileInput: { density: 'comfortable', variant: 'outlined' },
          VCombobox: { density: 'comfortable', variant: 'outlined' },
          VProgressLinear: { color: 'primary' },
          VBadge: { color: 'primary' },
          VMenu: { VList: { density: 'compact' } },
          VTooltip: { contentClass: 'font-weight-bold' },
        },
        theme: {
          defaultTheme: 'default',
          themes: {
            default: {
              colors: {
                primary: '#12264E',
                secondary: '#F89900',
                authBackground: '#12264E',
                background: '#E8EAED',
              },
            },
          },
        },
      },
    },
    alerts: { removeTimeout: 8000 },
    fonts: { localeFonts: { ar: 'Almarai', en: 'Roboto' } },
    locales: { default: 'ar', multiLang: true, localeApiHeader: 'lang' },
    auth: {
      redirect: { logout: '/' },
      endpoints: {
        login: { url: '/authenticate', method: 'post' },
        user: { url: '/account', method: 'get' },
        csrf: false,
        logout: false,
      },
    },
  },
  nitro: {
    output: {
      publicDir: '../dist',
    },
  },
  modules: ['./modules/blueprint/module.js'],
  ssr: false,
  sourcemap: { server: false },
  devtools: { enabled: true },
})
