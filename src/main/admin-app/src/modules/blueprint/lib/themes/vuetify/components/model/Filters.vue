<template>
  <BpBaseDataFilters
    v-if="type !== 'backend' || (filtersResponse && !filtersLoading)"
    class="bp-model-filters"
    v-bind="{
      filters,
      prePopulateFields,
      clearButton: filtersHaveValue,
      loading: computedLoading,
      excludeParams: computedExcludeParams,
      ...$attrs,
    }"
    @submit="fetchData"
    @reset="fetchData"
  >
    <template v-for="(slot, index) of Object.keys($slots)" :key="index" #[slot]>
      <slot :name="slot"></slot>
    </template>
    <slot />
  </BpBaseDataFilters>
</template>

<script>
/* eslint-disable camelcase */
import _get from 'lodash/get'
import _set from 'lodash/set'
export default {
  props: {
    model: { type: Function, required: true },
    fetchFunction: { type: [String, Function], default: () => 'fetch' },
    loading: { type: [String, Boolean], default: () => 'fetching' },
    fetchQuery: { type: Object, default: () => ({}) },
    excludeParams: { type: Array, default: () => [] },
  },
  data: () => ({
    prePopulateFields: {},
  }),
  computed: {
    computedExcludeParams() {
      return this.excludeParams.concat(
        this.model.filterConfig?.excludeParams || []
      )
    },
    filtersHaveValue() {
      const exclude = ['page', 'per_page', 'sort_by', 'sort_dir'].concat(
        this.computedExcludeParams
      )
      const filters = Object.keys(this.$route.query).reduce(
        (obj, item) =>
          exclude.includes(item)
            ? obj
            : { ...obj, [item]: this.$route.query[item] },
        {}
      )
      return Object.keys(filters).length > 0
      // const { page, per_page, sort_by, sort_dir, ...filters } =
      //   this.$route.query
      // return Object.keys(filters).length > 0
    },
    computedLoading() {
      return typeof this.loading === 'string'
        ? this.model.getState(this.loading)
        : typeof this.loading === 'boolean'
        ? this.loading
        : false
    },
    filtersLoading() {
      return this.model.getState('loading.filters')
    },
    filtersResponse() {
      return this.model.getState('filtersResponse')
    },
    staticFilters() {
      return this.model.filterConfig?.filters?.map((filter) => ({
        ...filter,
        loading: false,
        options: this.getFilterOptions(filter),
        grid: filter.grid || this.model.filterConfig?.grid || {},
      }))
    },
    backendFilters() {
      return Object.entries(this.filtersResponse)
        .map(this.convertResponseToFilters)
        .flat()
    },
    filters() {
      return this.type === 'backend' ? this.backendFilters : this.staticFilters
    },
    type() {
      return this.model.filterConfig?.type
    },
  },
  watch: {
    filtersHaveValue: {
      immediate: true,
      handler(value) {
        value && this.$emit('update:show-filters', value)
      },
    },
  },
  mounted() {
    this.populateFields()
  },
  methods: {
    populateFields() {
      if (!this.model.filterConfig?.prePopulate) {
        return
      }

      const requests = this.model.filterConfig.prePopulate

      Object.keys(requests).forEach((key) => {
        const options = requests[key]
        this.$axios.get(options.url, { ...options }).then(({ data }) => {
          const results = options.resultKey
            ? _get(data, options.resultKey)
            : data
          _set(this.prePopulateFields, key, results)
        })
      })
    },
    fetchData() {
      if (typeof this.fetchFunction === 'string') {
        if (this.model.bpApi) {
          return this.model.bpApi()[this.fetchFunction](this.fetchQuery)
        }
        return this.model[this.fetchFunction](this.fetchQuery)
      } else if (typeof this.fetchFunction === 'function') {
        return this.fetchFunction(this.fetchQuery)
      }
    },
    getFilterOptions(filter) {
      // state options: static
      if (filter.options) {
        return filter.options.map(({ value, text, rawText }) => ({
          value,
          title: rawText || this.$t(`filters.${filter.name}.${text}`),
        }))
      }
      // state options: Object or Array of objects
      if (filter.stateOptions) {
        return this.getFilterStateOptions(filter)
      }
      // state options: Request
      if (filter.requestOptions) {
        return this.getFilterRequestOptions(filter)
      }
      // state options: prePopulateOptions
      if (filter.prePopulateOptions) {
        return []
      }
      return []
    },
    getFilterStateOptions(filter) {
      const stateOptions = this.model.getState(filter.stateOptions, {})
      return Array.isArray(stateOptions)
        ? stateOptions.map((option) => ({
            ...option,
            ...(filter?.stateItemValue
              ? { value: '' + _get(option, filter.stateItemValue) }
              : {}),
            ...(filter?.stateItemText
              ? { title: '' + _get(option, filter.stateItemText) }
              : {}),
            ...(filter?.attrs?.itemValue
              ? {
                  [filter.attrs.itemValue]:
                    '' + _get(option, filter.attrs.itemValue),
                }
              : {}),
          }))
        : Object.keys(stateOptions).map((option) => ({
            title: stateOptions[option],
            value: '' + option,
          }))
    },
    getFilterRequestOptions(filter) {
      return this.$axios
        .get(filter.requestOptions.url, { ...filter.requestOptions })
        .then(({ data }) => {
          const results = filter.requestOptions.resultKey
            ? _get(data, filter.requestOptions.resultKey)
            : data

          return results.map((item) => {
            return {
              title: _get(item, filter.requestOptions.textKey),
              value: '' + _get(item, filter.requestOptions.valueKey),
            }
          })
        })
    },
    // Backend filter handler
    convertResponseToFilters([name, filter]) {
      const modelField =
        this.model.filterConfig?.filters?.find(
          (modelField) => modelField.name === name
        ) || {}
      return ['date-range', 'date_range'].includes(filter.type)
        ? [
            {
              ...modelField,
              name: name + '[from]',
              label: this.$t(`attributes.${name}_from`),
              type: 'date',
            },
            {
              ...modelField,
              name: name + '[to]',
              label: this.$t(`attributes.${name}_to`),
              type: 'date',
            },
          ]
        : {
            ...modelField,
            name,
            type:
              Array.isArray(filter) ||
              filter.type === 'like' ||
              filter.type === 'translation-like'
                ? 'text'
                : filter.type === 'dropdown'
                ? 'select'
                : filter.type,
            ...(filter.type === 'dropdown'
              ? {
                  options: Object.entries(filter.values).map(
                    ([value, text]) => ({ title: text, value: '' + value })
                  ),
                }
              : {}),
          }
    },
  },
}
</script>

<style></style>
