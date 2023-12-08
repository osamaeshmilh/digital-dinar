<template>
  <BpBaseDataPagination
    class="bp-model-pagination"
    v-bind="{
      pagination: model.getState('pagination'),
      hidePerPage,
      disabled: computedLoading,
      ...$attrs,
    }"
    @update-page="setPage"
    @update-per-page="setPerPage"
  />
</template>

<script>
export default {
  props: {
    model: { type: Function, required: true },
    loading: { type: [String, Boolean], default: 'loading.fetch' },
    hidePerPage: Boolean,
  },
  computed: {
    computedLoading() {
      return typeof this.loading === 'string'
        ? this.model.getState(this.loading)
        : typeof this.loading === 'boolean'
        ? this.loading
        : false
    },
    totalPagesIsOne() {
      return this.model.getState('pagination.totalPages') === 1
    },
  },
  methods: {
    setPage(page) {
      this.model.setPage(page)
    },
    setPerPage(perPage) {
      this.model.setPerPage(perPage)
    },
  },
}
</script>

<style></style>
