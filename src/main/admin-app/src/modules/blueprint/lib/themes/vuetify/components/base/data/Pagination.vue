<template>
  <v-row
    class="bp-base-data-pagination"
    v-bind="{
      class: 'py-2',
      align: 'center',
      justify: 'space-between',
      noGutters: true,
      ...rowAttrs,
    }"
  >
    <slot name="total-items" v-bind="{ pagination, totalItemsString }">
      <div
        v-if="!hideTotalText && pagination.total"
        v-bind="{ class: ' mb-2 mb-md-0', ...totalItemsAttrs }"
        v-text="
          $t(totalItemsString, {
            total: pagination.total,
          })
        "
      />
    </slot>

    <slot name="pagination" v-bind="{ pagination }">
      <v-pagination
        v-show="pagination.totalPages > 1"
        v-bind="{
          activeColor: 'primary',
          variant: 'text',
          rounded: 'circle',
          border: 0,
          density: 'compact',
          modelValue: pagination.currentPage,
          length: pagination.totalPages,
          totalVisible: 5,
          disabled,
          ...$attrs,
        }"
        @update:model-value="setPage"
      />
    </slot>
  </v-row>
</template>

<script>
export default {
  props: {
    pagination: { type: Object, default: () => ({}) },
    totalItemsString: { type: String, default: 'pagination.total_items' },
    totalItemsAttrs: { type: Object, default: () => ({}) },
    rowAttrs: { type: Object, default: () => ({}) },
    hidePerPage: Boolean,
    hideTotalText: Boolean,
    disabled: Boolean,
  },
  data: () => ({ perPageOptions: [10, 15, 25, 50, 100] }),
  computed: {
    pageModel: {
      get() {
        return this.pagination.currentPage
      },
      set(value) {
        this.setPage(value)
      },
    },
  },
  methods: {
    setPage(page) {
      this.$emit('updatePage', page)
    },
    setPerPage(perPage) {
      this.$emit('updatePerPage', perPage)
    },
    nextPage() {
      this.setPage(this.pagination.currentPage + 1)
    },
    previousPage() {
      this.setPage(this.pagination.currentPage - 1)
    },
    firstPage() {
      this.setPage(1)
    },
    lastPage() {
      this.setPage(this.pagination.totalPages)
    },
  },
}
</script>

<style></style>
