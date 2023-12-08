<template>
  <bp-base-input
    ref="date-input"
    v-model="model"
    class="bp-base-date-range-picker-input"
    :name="name"
    :errors="errors"
    :rules="rules"
    :class="inputClass"
    readonly
    v-bind="$attrs"
  />
  <v-menu
    v-model="menuToggle"
    class="bp-base-date-range-picker-input--menu"
    :activator="$refs['date-input']"
    v-bind="{
      offsetY: true,
      bottom: true,
      minWidth: 'auto',
      openOnFocus: true,
      openOnClick: false,
      closeOnContentClick: false,
      eager: true,
      ...menuAttrs,
    }"
  >
    // todo
    <!-- <v-date-picker
      v-model="model"
      v-bind="{
        locale: $vuetify.locale,
        name,
        noTitle: true,
        outlined: true,
        dense: true,
        firstDayOfWeek: '6',
        scrollable: true,
        errorMessages: getError(name),
        error: hasBackendError(name),
        reactive: true,
        ...datePickerAttrs,
      }"
      @change="menuToggle = false"
      @input="$emit('input', $event)"
      v-on="datePickerOn"
    /> -->
  </v-menu>
</template>

<script>
export default {
  props: {
    name: { type: String, required: true },
    rules: { type: Array, default: () => [] },
    errors: { type: Object, default: () => ({}) },
    datePickerAttrs: { type: Object, default: () => ({}) },
    datePickerOn: { type: Object, default: () => ({}) },
    inputClass: { type: String, default: '' },
    modelValue: { type: String, default: '' },
    menuAttrs: { type: Object, default: () => ({}) },
    menuOn: { type: Object, default: () => ({}) },
  },
  data: () => ({ menuToggle: false }),
  computed: {
    model: {
      get() {
        return this.modelValue
      },
      set(value) {
        this.$emit('update:modelValue', value)
      },
    },
  },
  mounted() {},
  methods: {},
}
</script>

<style></style>
