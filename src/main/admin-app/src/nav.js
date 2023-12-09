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
    icon: 'mdi-chart-bar',
    title: 'navigation.merchants',
    to: '/merchants',
  },
  {
    icon: 'mdi-chart-bar',
    title: 'navigation.paymentMethods',
    to: '/payment-methods',
  },
  {
    icon: 'mdi-chart-bar',
    title: 'navigation.banners',
    to: '/banners',
  },
  {
    icon: 'mdi-ticket-percent',
    title: 'navigation.vouchers',
    children: [
      {
        title: 'labels.all',
        to: '/voucher-management/vouchers',
      },
      {
        title: 'navigation.companies',
        to: '/voucher-management/companies',
      },
      {
        title: 'navigation.types',
        to: '/voucher-management/types',
      },
    ],
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
