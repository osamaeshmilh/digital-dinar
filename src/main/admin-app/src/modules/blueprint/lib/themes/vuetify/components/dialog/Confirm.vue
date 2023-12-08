<script>
export default {
  props: {
    buttonColor: { type: String, default: 'info darken-4' },
    alertIconColor: { type: String, default: 'info darken-2' },
    alertIcon: { type: String, default: 'mdi-information' },
    subtitleClass: { type: String, default: '' },
    titleClass: { type: String, default: 'text-black' },
    subtitle: { type: String, default: '' },
    title: { type: String, default: '' },
    modelValue: Boolean,
  },
  computed: {
    model: {
      get() {
        return this.modelValue
      },
      set(currentValue) {
        return this.$emit('update:modelValue', currentValue)
      },
    },
  },
}
</script>

<template>
  <BpBaseDialog
    v-model="model"
    class="bp-dialog-confirm"
    v-bind="{
      showFooter: true,
      title: $t('dialogs.confirm.title'),
      subtitle: $t('dialogs.confirm.subtitle'),
      submitBtnText: $t('buttons.confirm'),
      onSubmit: $emit('confirm'),
      ...$attrs,
    }"
  >
  </BpBaseDialog>
</template>
