// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  blueprint: {
    singleEntryBuild: true,
    plugins: { dayjs: true },
    axios: {
      props: { errorMessage: ['data.title', 'data.message'] },
    },
    alerts: { removeTimeout: 8000 },
    fonts: { localeFonts: 'Tajawal' },
    locales: { default: 'ar', multiLang: false, localeApiHeader: 'lang' },
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
  sourcemap: {server: false},
  devtools: { enabled: true }
})
