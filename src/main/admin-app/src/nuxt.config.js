// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  blueprint: {
    plugins: { dayjs: true },
    // theme: { routes: false },
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
  // nitro: {
  //   output: {
  //     publicDir: '',
  //   },
  // },
  modules: ['./modules/blueprint/module.js'],
  ssr: false,
  sourcemap: {server: false},
  devtools: { enabled: true }
})
