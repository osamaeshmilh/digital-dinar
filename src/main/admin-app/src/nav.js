export default [
  {
    title: 'navigation.general',
  },
  {
    icon: 'mdi-home',
    title: 'navigation.home',
    to: '/',
    // iconColor: 'primary',
    // titleColor: 'primary',
    // attrs: {},
    // iconAttrs: {},
    // titleAttrs: {},
    // childAttrs: {}
    // childTitleAttrs: {},
    // childIconAttrs: {},
  },
  {
    icon: 'mdi-file-arrow-left-right',
    title: 'navigation.transactions',
    to: '/transactions',
  },
  {
    icon: 'mdi-chart-bar',
    title: 'navigation.reports',
    to: '/reports',
  },
  {
    title: 'navigation.management',
    attrs: { class: 'mt-4' },
  },
  {
    icon: 'mdi-store',
    title: 'navigation.merchants',
    to: '/merchants',
  },
  {
    icon: 'mdi-account-multiple',
    title: 'navigation.beneficiaries',
    to: '/beneficiaries',
  },
  {
    icon: 'mdi-credit-card',
    title: 'navigation.paymentMethods',
    to: '/payment-methods',
  },
  {
    icon: 'mdi-wallet',
    title: 'navigation.wallet',
    children: [
      {
        title: 'navigation.transactions',
        to: '/wallet/transactions',
      },
      {
        title: 'navigation.users',
        to: '/wallet/users',
      },
      {
        title: 'navigation.profiles',
        to: '/wallet/profiles',
      },
    ],
  },
  {
    icon: 'mdi-bank',
    title: 'navigation.banks',
    children: [
      {
        title: 'labels.all',
        to: '/banks/all',
      },
      {
        title: 'navigation.branches',
        to: '/banks/branches',
      },
    ],
  },
  {
    icon: 'mdi-ticket-percent',
    title: 'navigation.vouchers',
    children: [
      {
        title: 'labels.all',
        to: '/vouchers/all',
      },
      {
        title: 'navigation.companies',
        to: '/vouchers/companies',
      },
      {
        title: 'navigation.types',
        to: '/vouchers/types',
      },
    ],
  },
  {
    icon: 'mdi-bell',
    title: 'navigation.notifications',
    to: '/notifications',
  },
  {
    icon: 'mdi-play-box',
    title: 'navigation.sliders',
    to: '/sliders',
  },
  {
    icon: 'mdi-cog',
    title: 'navigation.settings',
    children: [
      {
        title: 'navigation.cities',
        to: '/settings/cities',
      },
      {
        title: 'navigation.countries',
        to: '/settings/countries',
      },
      {
        title: 'navigation.categories',
        to: '/settings/categories',
      },
      {
        title: 'navigation.users',
        to: '/settings/users',
      },
    ],
  },
]
