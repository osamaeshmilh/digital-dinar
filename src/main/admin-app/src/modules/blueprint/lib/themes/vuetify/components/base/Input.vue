<script>
export default {
  inheritAttrs: false,
  props: {
    name: { type: String, default: '' },
    label: { type: [String, Boolean], default: '' },
    errors: { type: [Object, Array, String], default: () => [] },
    validationProps: { type: [Object, Array, String], default: () => ({}) },
    password: { type: Boolean, default: false },
  },
  setup(props) {
    const { getError } = useValidation(props.validationProps)
    return { getError }
  },
  data: () => ({ showPassword: false }),
  computed: {
    inputType() {
      return this.password
        ? this.showPassword
          ? 'text'
          : 'password'
        : this.$attrs.type
    },
    passwordIcon() {
      return this.showPassword ? 'mdi-eye-off' : 'mdi-eye'
    },
    appendInnerIcon() {
      return this.password
        ? this.passwordIcon
        : this.$attrs['append-inner-icon']
    },
  },
  methods: {
    clearError() {
      this.name in this.errors && delete this.errors[this.name]
    },
  },
}
</script>

<template>
  <v-text-field
    ref="textField"
    class="bp-base-input"
    :class="{ 'bp-reverse-direction': password }"
    v-bind="{
      name,
      label: label
        ? label
        : name && label !== false
        ? $t(`attributes.${name}`)
        : '',
      errorMessages:
        name && typeof errors === 'object'
          ? getError(name, errors)
          : Array.isArray(errors)
          ? errors
          : [],
      hideDetails: 'auto',
      type: inputType,
      appendInnerIcon,
      ...$attrs,
    }"
    @update:model-value="clearError"
    @click:append-inner="showPassword = !showPassword"
  >
    <template v-for="(slot, index) of Object.keys($slots)" :key="index" #[slot]>
      <slot :name="slot"></slot>
    </template>
    <slot />
  </v-text-field>
</template>

<style>
.v-locale--is-rtl .bp-reverse-direction input {
  direction: ltr !important;
  text-align: right !important;
}
</style>
