export default {
  runtimeConfig: process.env.BP_RUNTIME_CONFIG == 'true',
  singleEntryBuild: process.env.BP_SINGLE_ENTRY_BUILD == 'true',
  themes: {
    vuetify: {
      layouts: true, // boolean || string (path)
      routes: true, // boolean || string (path)
      composables: true,
      components: [
        '@bp/themes/vuetify/components/feedback',
        '@bp/themes/vuetify/components',
      ],
      plugins: ['@bp-theme/plugin.js'],
      css: ['@bp-theme/assets/common.scss', '@bp/assets/utilities.scss'],
      options: {
        authLayout: 'auth-one',
        navLayout: 'drawer',
        appbar: { color: 'white', attrs: {}, props: {} },
        navbar: { color: 'grey-lighten-4', attrs: {}, props: {} },
        drawer: { color: 'white', attrs: {}, props: {} },
        logo: { src: 'logo.png', darkSrc: 'logo.png' },
        miniLogo: { src: '', darkSrc: '' },
      },
    },
  },
  theme: {
    name: 'vuetify',
  },
  plugins: {
    blueprint: true,
    nav: true,
    loading: true,
    icons: true,
    axios: true,
    locales: true,
    dayjs: false,
    fonts: true,
    vuetify: true,
    vuex: true,
    auth: true,
    vuexOrm: true,
    guard: true,
    alerts: true,
    banners: true,
    notifications: true,
    breadcrumbs: true,
    forceTfa: false,
    recaptcha: !!process.env.RECAPTCHA_SITE_KEY,
    sentry: process.env.BP_RUNTIME_CONFIG == 'true' || !!process.env.SENTRY_DSN,
    chartJs: false,
    vCalendar: false,
  },
  alerts: {
    removeTimeout: false, // auto remove alert
  },
  sentry: {
    setUser: true,
    args: {
      dsn: process.env.SENTRY_DSN,
      environment: process.env.SENTRY_ENVIRONMENT,
    },
  },
  recaptcha: {
    hideBadge: false, // Hide badge element (v3 & v2 via size=invisible)
    language: 'auto', // Recaptcha language (v2)
    mode: 'base', // Mode: 'base', 'enterprise'
    siteKey: process.env.RECAPTCHA_SITE_KEY, // Site key for requests
    version: 3, // Version
    size: 'invisible', // Size: 'compact', 'normal', 'invisible' (v2)
  },
  icons: {
    active: 'mdi',
    cdn: {
      mdi: 'https://cdn.jsdelivr.net/npm/@mdi/font@6.x/css/materialdesignicons.min.css',
      md: 'https://fonts.googleapis.com/css?family=Material+Icons',
      fa: 'https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@latest/css/all.min.css',
      fa4: 'https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css',
    },
  },
  vuex: {
    args: {},
  },
  loading: {
    timeout: 100,
  },
  vuetify: {
    args: {
      theme: {
        defaultTheme: 'default',
        themes: {
          default: {
            variables: {
              'medium-emphasis-opacity': 0.87,
              'high-emphasis-opacity': 1,
              'disabled-opacity': 0.55,
              'idle-opacity': 0.02,
              'hover-opacity': 0.02,
              'border-opacity': 0.08,
              'focus-opacity': 0.06,
              'selected-opacity': 0.06,
              'activated-opacity': 0.06,
              'pressed-opacity': 0.06,
              'dragged-opacity': 0.06,
            },
            colors: {
              primary: '#2467C0',
            },
          },
        },
      },
    },
    variables: '@bp-theme/assets/variables.scss',
    labs: false,
  },
  axios: {
    args: {
      withCredentials: true,
      timeout: 20000,
      baseURL: process.env.BP_PROXY_BASE_URL
        ? '/backend'
        : process.env.API_BASE_URL,
    },
    props: {
      errorMessage: 'data.message',
    },
    cancelAfterEach: false,
    onStatusError: {
      // 404: function
    },
    onError: false,
    onRequest: false,
  },
  vuexOrm: {
    clearOnLogout: true,
    clearOnLogoutTimeout: 500,
    autoImport: true,
  },
  auth: {
    autoFetchUser: true,
    watchUser: true,
    onLogin: false,
    onLogout: false,
    redirect: {
      home: '/',
      login: '/auth/login',
    },
    props: {
      user: '',
    },
    endpoints: {
      csrf: { url: '/auth/csrf-cookie', method: 'GET' },
      user: { url: '/auth/user', method: 'GET' },
      login: { url: '/auth/login', method: 'POST' },
      logout: { url: '/auth/logout', method: 'POST' },
    },
  },
  locales: {
    default: 'ar',
    multiLang: true,
    localeApiHeader: 'lang',
  },
  fonts: {
    localeFonts: {
      ar: 'Almarai',
      en: 'Roboto',
    },
    loadGoogleFont: true,
    load: [],
  },
  breadcrumbs: {
    prefix: {
      icon: 'mdi-home',
      to: '/',
    },
  },
  guard: {
    superAdminRole: 'super',
    // can be a handler function || object
    onUnauthorized: {
      throwError: false,
      redirect: '/',
      alertKey: 'index-page',
    },
    props: {
      role: 'role',
      permissions: 'permissions',
    },
  },
  forceTfa: {
    // callable (options) => {}
    handler: false,
    redirect: '/account/tfa',
    props: {
      forced: 'tfa_forced',
      enabled: 'tfa_enabled',
    },
  },
  dayjs: {},
}
