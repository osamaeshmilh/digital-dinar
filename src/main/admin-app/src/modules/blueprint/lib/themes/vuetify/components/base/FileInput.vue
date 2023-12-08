<template>
  <v-file-input
    class="bp-base-file-input"
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
      ...$attrs,
    }"
    @update:model-value="clearError"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="data"
    >
      <slot :name="slot" v-bind="data"></slot>
    </template>
    <slot />
  </v-file-input>
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
