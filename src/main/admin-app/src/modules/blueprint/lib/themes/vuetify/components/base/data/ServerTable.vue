<template>
  <v-data-table-server
    v-model="selected"
    class="bp-base-data-server-table"
    v-bind="{ hideDefaultFooter, headers, items, ...$attrs }"
  >
    <template v-for="(slot, index) of Object.keys($slots)" :key="index" #[slot]>
      <slot :name="slot"></slot>
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

<style></style>
