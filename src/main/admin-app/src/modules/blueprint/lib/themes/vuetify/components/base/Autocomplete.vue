<template>
  <v-autocomplete
    class="bp-base-autocomplete"
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
    <template v-for="(_, name) in $slots" #[name]="slotProps">
      <slot v-if="slotProps" :name="name" v-bind="slotProps" />
      <slot v-else :name="name" />
    </template>
    <slot />
  </v-autocomplete>
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
