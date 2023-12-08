export default [
  {
    name: 'account',
    path: '/account',
    file: '@bp-theme/pages/account.vue',
    meta: {
      breadcrumb: [{ text: 'pages.profile.account', disabled: true }],
    },
  },
  {
    name: 'auth-forget-password',
    path: '/auth/forget-password',
    file: '@bp-theme/pages/auth/forget-password.vue',
  },
  {
    name: 'auth-login',
    path: '/auth/login',
    file: '@bp-theme/pages/auth/login.vue',
  },
  {
    name: 'auth-reset-password',
    path: '/auth/reset-password',
    file: '@bp-theme/pages/auth/reset-password.vue',
  },
  {
    name: 'auth-tfa-index',
    path: '/auth/tfa',
    file: '@bp-theme/pages/auth/tfa.vue',
  },
]
