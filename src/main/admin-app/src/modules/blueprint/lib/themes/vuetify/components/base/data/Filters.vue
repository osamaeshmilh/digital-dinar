<template>
  <div class="bp-base-data-filters">
    <v-form @submit.prevent="submitFilters">
      <v-row v-bind="rowAttrs">
        <template v-for="filter in computedFilters" :key="filter.name">
          <v-col
            v-if="!filter.hide"
            v-bind="{ ...getColsAttrs(filter), ...colAttrs }"
          >
            <slot :name="filter.name" v-bind="{ filter, inputAttrs, model }">
              <bp-base-data-filter
                v-model="model[filter.name]"
                :filter="filter"
                v-bind="{ ...filter.attrs, ...inputAttrs }"
              />
            </slot>
          </v-col>
        </template>
        <v-col cols="12" :class="footerClass">
          <BpBaseButton
            v-show="hasAnyValue"
            v-bind="{
              class: 'me-2',
              variant: 'text',
              iconClass: 'mdi-restart',
              title: $t('buttons.cancel'),
              ...resetBtnAttrs,
            }"
            @click="resetFilters"
          />
          <BpBaseButton
            :disabled="loading"
            v-bind="{
              class: 'flex-grow-1 flex-md-grow-0',
              iconClass: 'mdi-magnify',
              title: $t('buttons.search'),
              color: 'primary',
              type: 'submit',
              ...submitBtnAttrs,
            }"
          />
        </v-col>
      </v-row>
    </v-form>
  </div>
</template>

<script>
/* eslint-disable camelcase */
import _get from 'lodash/get'
import _set from 'lodash/set'
export default {
  inheritAttrs: false,
  props: {
    // [{ name, value, type, options?}]
    filters: { type: Array, required: true },
    loading: Boolean,
    clearButton: Boolean,
    rowAttrs: { type: Object, default: () => ({}) },
    colAttrs: { type: Object, default: () => ({}) },
    inputAttrs: { type: Object, default: () => ({}) },
    resetBtnAttrs: { type: Object, default: () => ({}) },
    submitBtnAttrs: { type: Object, default: () => ({}) },
    prePopulateFields: { type: Object, default: () => ({}) },
    excludeParams: { type: Array, default: () => [] },
    footerClass: { type: String, default: 'd-flex justify-end' },
  },
  data() {
    return {
      model: {},
    }
  },
  computed: {
    computedFilters() {
      return (
        this.filters?.map((filter) => {
          if (filter.prePopulateOptions) {
            filter.options = (
              this.prePopulateFields[filter.prePopulateOptions.key] || []
            ).map((item) => ({
              value: '' + _get(item, filter.prePopulateOptions.valueKey),
              title: _get(item, filter.prePopulateOptions.textKey),
            }))
          }
          return filter
        }) || []
      )
    },
    hasAnyValue() {
      return 'clearButton' in this.$props
        ? this.clearButton
        : Object.values(this.model).some((value) => !!value)
    },
    filtersQuery() {
      const exclude = ['page', 'per_page', 'sort_by', 'sort_dir'].concat(
        this.excludeParams
      )
      return Object.keys(this.$route.query).reduce(
        (obj, item) =>
          exclude.includes(item)
            ? obj
            : {
                ...obj,
                [item]: this.$route.query[item],
              },
        {}
      )
      // const { page, per_page, sort_by, sort_dir, ...filters } =
      //   this.$route.query
    },
    restQuery() {
      const exclude = ['per_page', 'sort_by', 'sort_dir'].concat(
        this.excludeParams
      )
      return Object.keys(this.$route.query).reduce(
        (obj, item) =>
          !exclude.includes(item)
            ? obj
            : {
                ...obj,
                [item]: this.$route.query[item],
              },
        {}
      )
      // const { per_page, sort_by, sort_dir } = this.$route.query
      // return { per_page, sort_by, sort_dir }
    },
  },
  watch: {
    filtersQuery: {
      deep: true,
      handler(query, old) {
        if (JSON.stringify(query) === JSON.stringify(old)) {
          return
        }
        this.populateQueryParamsToModel()
        this.$emit('submit', this.model)
      },
    },
  },
  created() {
    this.$nuxt.hook('filters', ({ key, value }) => {
      _set(this.model, key, value)
      this.submitFilters()
    })
    this.populateQueryParamsToModel()
  },
  methods: {
    getColsAttrs(filter) {
      return {
        cols: '12',
        md: '4',
        ...filter.grid,
      }
    },
    resetFilters() {
      this.model = {}
      this.submitFilters()
    },
    submitFilters() {
      this.removeEmptyFilters()
      this.$router.push({
        query: {
          ...this.restQuery,
          ...this.model,
        },
      })
    },
    removeEmptyFilters() {
      Object.keys(this.model).forEach(
        (key) => !this.model[key] && delete this.model[key]
      )
    },
    populateQueryParamsToModel() {
      this.filters?.forEach(
        (filter) => (this.model[filter.name] = this.$route.query[filter.name])
        // _set(this.model, filter.name, this.$route.query[filter.name])
      )
    },
  },
}
</script>

<style></style>
