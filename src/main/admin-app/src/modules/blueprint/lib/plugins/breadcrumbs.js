export default defineNuxtPlugin((app) => {
  const { breadcrumbs: breadcrumbsOptions } = app.$bp.options
  const { meta } = useRoute()

  const breadcrumbs = useState('blueprint-breadcrumbs', () =>
    reactive({
      route: meta?.breadcrumb || [],
      page: [],
      prefix: breadcrumbsOptions.prefix,
    })
  ).value

  app.provide('breadcrumbs', breadcrumbs)

  app.hook('page:finish', () => {
    const { meta } = useRoute()
    breadcrumbs.route = meta?.breadcrumb || []
  })

  watch(
    () => app.$router.currentRoute.value,
    (route) => {
      if (!route.meta?.persist) {
        breadcrumbs.page = []
      }
    }
  )
})
