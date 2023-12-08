// To use this trait, set filterConfig in your model
/*
{
  rewriteParam: '',
  type: 'backend' || 'static',
  sorting: true,
  sortByOptions: [
    { text: 'registration_number', value: 'registration_number' },
  ],
  filters: [
    {
      name: 'sort_by',
      rewriteParam: 'sorting[by]',
    },
    {
      name: 'sort_dir',
      rewriteParam: 'sorting[dir]',
    },
    {
      name: 'id',
      label: '',
      rewriteParam: '{name}' || 'String' (optional),
      requestOptions: {
        url: '',
        resultKey: '',
        textKey: '',
        valueKey: '',
      },
      options: [
        { text: '', value: '' },
        { text: '', value: '' },
      ],
      // called by model.getState()
      // { [value]: [label] }
      stateOptions: '',
      // input attrs
      attrs: {},
      grid: { cols: 12, md: 4 }
    },
  ]
}
 */
export default {
  traitState(model) {
    return {
      ...(model.filterConfig.type === 'backend'
        ? { filtersResponse: null }
        : {}),
      filters: {},
    }
  },
  // Call this function to get the filter api params
  traitApiParams(model) {
    model.setState('filters', {})
    const urlParams = useRoute().query
    const filterConfig = model.filterConfig
    const modelFilters = filterConfig.filters
    const apiParams = {}
    const stateFilters = {}

    for (const key of Object.keys(urlParams)) {
      // if filter are set, get the filter object from model
      const filter =
        Array.isArray(modelFilters) &&
        modelFilters.find(({ name }) => !!name && name === key)
      if (
        key === 'page' ||
        (!filter && filterConfig.type !== 'backend') ||
        (filterConfig.excludeParams || []).includes(key)
      ) {
        continue
      }

      // if type is backend, filter might not be declared statically
      const filterName = filterConfig.type === 'backend' ? key : filter.name

      // set api param string and then check if rewriting is needed
      let paramString = filterName
      if (filter && filter.rewriteParam) {
        paramString = filter.rewriteParam.replace('{name}', filterName)
      } else if (filterConfig.rewriteParam) {
        paramString = filterConfig.rewriteParam.replace('{name}', filterName)
      }

      if (filter && !filter.hide) {
        stateFilters[filter.name] = urlParams[key]
      }

      // set state object
      stateFilters[filterName] = urlParams[key]
      // set api params object
      apiParams[paramString] = urlParams[key]
    }

    // Write filters to state
    model.setState('filters', stateFilters)

    return {
      ...(!model.getState('filtersResponse')
        ? { [filterConfig?.includeParam || 'include_filters']: true }
        : {}),
      ...apiParams,
      ...(model.filterConfig.appendParams
        ? model.filterConfig.appendParams
        : {}),
    }
  },
  // check if the filter state have value
  filtersHaveValue() {
    // eslint-disable-next-line camelcase
    const { page, per_page, sort_by, sort_dir, ...query } = useRoute().query
    return Object.keys(query).length > 0
  },
  filtersResponseFetched() {
    return this.getState('filtersResponse')
  },
  filtersCount() {
    const { page, per_page, sort_by, sort_dir, ...query } = useRoute().query
    return Object.keys(query).length
  },
}
