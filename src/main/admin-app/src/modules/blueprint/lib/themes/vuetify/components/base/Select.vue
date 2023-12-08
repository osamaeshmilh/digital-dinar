<template>
  <v-select
    class="bp-base-select"
    v-bind="{
      name,
      label: label ? label : name ? $t(`attributes.${name}`) : '',
      errorMessages:
        name && typeof errors === 'object'
          ? getError(name, errors)
          : Array.isArray(errors)
          ? errors
          : [],
      hideDetails: 'auto',
      menuProps: { 'offset-y': true },
      ...$attrs,
    }"
    @update:model-value="clearError"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="slotProps"
    >
      <slot v-if="slotProps" :name="slot" v-bind="slotProps"></slot>
      <slot v-else :name="slot"></slot>
    </template>
    <slot />
  </v-select>
</template>

<script>
export default {
  inheritAttrs: false,
  props: {
    name: { type: String, default: '' },
    label: { type: String, default: '' },
    errors: { type: Object, default: () => ({}) },
  },
  setup() {
    const { getError } = useValidation()
    return { getError }
  },
  methods: {
    clearError() {
      this.name in this.errors && delete this.errors[this.name]
    },
  },
}
</script>
