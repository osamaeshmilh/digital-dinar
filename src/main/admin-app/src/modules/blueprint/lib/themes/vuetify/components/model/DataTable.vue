<template>
  <div class="bp-model-data-table">
    <div
      v-if="
        !hideSkeletonLoader &&
        computedLoading &&
        ($attrs.items ? !$attrs.items.length : !model.all().length)
      "
      class="bp-model-data-table--loading"
    >
      <slot name="loading">
        <v-skeleton-loader
          v-bind="{
            type: 'table-thead, table-tbody, table-tfoot',
            ...skeletonAttrs,
          }"
        />
      </slot>
    </div>
    <div
      v-else-if="
        !hideNoResults &&
        !computedLoading &&
        ($attrs.items ? !$attrs.items.length : !model.all().length)
      "
      class="bp-model-data-table--no-results"
    >
      <slot name="no-results">
        <bp-base-data-no-results v-bind="noResultsAttrs" />
      </slot>
    </div>
    <v-card v-else rounded class="bp-model-data-table--card elevation-default">
      <bp-base-data-table
        v-model="selected"
        class="bp-model-data-table--table"
        v-bind="{
          itemsPerPage: model.getState('pagination.itemsPerPage', 30),
          loading: computedLoading,
          headers: tableHeaders,
          items: !!modelSortBy
            ? model.query().orderBy(modelSortBy).get()
            : model.all().reverse(),
          ...$attrs,
        }"
      >
        <template
          v-for="field in booleanFields"
          :key="field + '-chip-field'"
          #[`item.${field}`]="slotData"
        >
          <BpBooleanChip
            :model-value="slotData.item[field]"
            v-bind="booleanFieldAttrs"
          />
        </template>
        <template
          v-for="field in chipFields"
          :key="field + '-chip-field'"
          #[`item.${field}`]="slotData"
        >
          <BpBaseChip :title="slotData.item[field]" v-bind="chipFieldAttrs" />
        </template>
        <template
          v-for="(slot, index) of Object.keys($slots)"
          :key="index"
          #[slot]="data"
        >
          <slot :name="slot" v-bind="data"></slot>
        </template>
      </bp-base-data-table>
    </v-card>
    <div v-if="!noPagination">
      <bp-model-pagination
        class="bp-model-data-table--pagination"
        v-bind="{
          class: 'mt-2',
          disabled: computedLoading,
          model,
          hidePerPage,
          ...modelPaginationAttrs,
        }"
      />
    </div>
  </div>
</template>

<script>
import _get from 'lodash/get'
export default {
  inheritAttrs: false,
  props: {
    model: { type: Function, required: true },
    fetchFunction: { type: [String, Function], default: 'fetch' },
    loading: { type: [String, Boolean], default: 'loading.fetch' },
    selectedItems: { type: Array, default: () => [] },
    hidePerPage: Boolean,
    noFetch: Boolean,
    noResultImage: { type: String, default: '' },
    // Force go to first page inspite of previous state
    firstPage: { type: Boolean, default: true },
    fetchQuery: { type: Object, default: () => ({}) },
    skeletonAttrs: { type: Object, default: () => ({}) },
    noResultsAttrs: { type: Object, default: () => ({}) },
    noResultsIcon: { type: String, default: 'mdi-alert' },
    modelSortBy: { type: [String, Boolean], default: 'order' },
    modelPaginationAttrs: { type: Object, default: () => ({}) },
    booleanFieldAttrs: { type: Object, default: () => ({}) },
    chipFieldAttrs: { type: Object, default: () => ({}) },
    modelValue: { type: Array, default: () => [] },
    booleanFields: { type: Array, default: () => [] },
    chipFields: { type: Array, default: () => [] },
    hideSkeletonLoader: Boolean,
    hideNoResults: { type: Boolean, default: true },
  },
  setup(props) {
    const fetchData = () => {
      if (typeof props.fetchFunction === 'string') {
        if (props.model.bpApi) {
          return props.model.bpApi()[props.fetchFunction](props.fetchQuery)
        }
        return props.model[props.fetchFunction](props.fetchQuery)
      } else if (typeof props.fetchFunction === 'function') {
        return props.fetchFunction(props.fetchQuery)
      }
    }

    useAsyncData('', () => {
      !props.noPagination &&
        !props.noFetch &&
        props.firstPage &&
        props.model.setPage(useRoute().query.page || 1)
      !props.noFetch && fetchData()
    })

    return {
      fetchData,
      routeName: useRoute().name,
    }
  },
  computed: {
    computedLoading() {
      return typeof this.loading === 'string'
        ? this.model.getState(this.loading)
        : typeof this.loading === 'boolean'
        ? this.loading
        : false
    },
    tableHeaders() {
      return (this.model.tableHeaders || []).map((header) => ({
        ...header,
        key: header.value,
        title: header.text ? this.$t(header.text) : header.rawText ?? '',
      }))
    },
    noPagination() {
      return !('pagination' in this.modelState)
    },
    modelState() {
      return this.model.getState()
    },
    selected: {
      get() {
        return this.modelValue
      },
      set(val) {
        this.$emit('update:modelValue', val)
      },
    },
  },
  watch: {
    '$route.query.page'(page, old) {
      if (+page === +old || (!page && this.routeName !== this.$route.name)) {
        return
      }
      !this.noFetch && this.fetchData()
    },
  },
  methods: {
    _get,
  },
}
</script>
