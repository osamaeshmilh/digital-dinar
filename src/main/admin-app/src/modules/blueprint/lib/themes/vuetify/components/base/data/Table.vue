<template>
  <v-data-table-server
    v-model="selected"
    class="bp-base-data-table"
    v-bind="{
      hideDefaultFooter,
      loading,
      headers,
      items,
      itemsLength: Array.isArray(items) ? items.length : 0,
      ...$attrs,
    }"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="data"
    >
      <slot :name="slot" v-bind="{ data, item: data.item }"></slot>
    </template>
  </v-data-table-server>
</template>

<script>
export default {
  inheritAttrs: false,
  props: {
    selectedItems: {
      type: Array,
      default: () => [],
    },
    loading: Boolean,
    hideDefaultFooter: {
      type: Boolean,
      default: true,
    },
    items: {
      type: Array,
      default: () => [],
    },
    headers: {
      type: Array,
      default: () => [],
    },
    modelValue: {
      type: Array,
      default: () => [],
    },
  },
  emits: ['update:modelValue'],
  computed: {
    selected: {
      get() {
        return this.modelValue
      },
      set(val) {
        this.$emit('update:modelValue', val)
      },
    },
  },
}
</script>

<style>
.bp-base-data-table .v-data-table-footer {
  display: none;
}
</style>
