<script>
export default {
  props: {
    modelValue: Boolean,
    entity: { type: String, default: '' },
    alertKey: { type: String, default: 'global' },
    action: {
      type: String,
      default: 'delete',
      validator: (value) => ['delete', 'clear'].includes(value),
    },
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
  methods: {
    cancel() {
      this.model = false
      this.$emit('cancel')
    },
  },
}
</script>

<template>
  <BpBaseDialog
    v-model="model"
    class="bp-dialog-delete"
    v-bind="{
      showFooter: true,
      title: $t('dialogs.' + action + '.title', { entity }),
      subtitle: $t('dialogs.' + action + '.subtitle', { entity }),
      deleteDialog: true,
      ...$attrs,
    }"
    @submit="$emit('confirm')"
  >
    <BpAlerts :alert-key="alertKey" />
  </BpBaseDialog>
</template>
