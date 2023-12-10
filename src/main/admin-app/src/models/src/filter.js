export default {
  traitState() {
    return {
      filters: {},
    }
  },
  traitApiParams(model) {
    const router = useRouter()
    const urlParams = router.currentRoute.value.query
    const filterConfig = model.filterConfig
    const modelFilters = filterConfig.filters
    const apiParams = {}
    const stateFilters = {}

    for (const key of Object.keys(urlParams)) {
      const filter = modelFilters.find(({ name }) => name === key)
      if (!filter) {
        continue
      }

      let paramString = filter.name

      if (filter.rewriteParam) {
        paramString = filter.rewriteParam.replace('{name}', filter.name)
      } else if (filterConfig.rewriteParam) {
        paramString = filterConfig.rewriteParam.replace('{name}', filter.name)
      } else {
        paramString =
          filter.name + (filter.type === 'text' ? '.contains' : '.equals')
      }

      if (!filter.hide) {
        stateFilters[filter.name] = urlParams[key]
      }

      if (filter.type === 'date') {
        apiParams[paramString] = useNuxtApp()
          .$dayjs(urlParams[key], 'YYYY-MM-DD')
          .toISOString()
      } else {
        apiParams[paramString] = urlParams[key]
      }
    }

    // Write filters to state
    model.setState('filters', stateFilters)

    return {
      ...apiParams,
      ...(model.filterConfig.appendParams
        ? model.filterConfig.appendParams
        : {}),
    }
  },

  filtersHaveValue() {
    return Object.values(this.getState('filters')).some((value) => !!value)
  },
}
