<script>
export default {
  props: {
    modelValue: Boolean,
    title: { type: String, default: '' },
    subtitle: { type: String, default: '' },
    color: { type: String, default: 'primary' },
    cardAttrs: { type: Object, default: () => ({}) },
    alertAttrs: { type: Object, default: () => ({}) },
    cancelBtnAttrs: { type: Object, default: () => ({}) },
    confirmBtnAttrs: { type: Object, default: () => ({}) },
  },
  computed: {
    model: {
      get() {
        return this.modelValue
      },
      set(model) {
        if (!model) {
          this.$emit('cancel')
        }
        return this.$emit('update:modelValue', model)
      },
    },
  },
}
</script>

<template>
  <BpBaseDialog
    v-model="model"
    class="bp-dialog-confirm-leave"
    v-bind="{
      showFooter: true,
      title: $t('dialogs.leaveForm.title'),
      subtitle: $t('dialogs.leaveForm.subtitle'),
      submitBtnText: $t('buttons.exit'),
      onSubmit: $emit('confirm'),
      cardAttrs: {
        // headerIcon: 'mdi-alert',
        headerIconAttrs: { color: 'warning' },
      },
      submitBtnAttrs: {
        color: 'warning',
      },
      ...$attrs,
    }"
  >
  </BpBaseDialog>
</template>
