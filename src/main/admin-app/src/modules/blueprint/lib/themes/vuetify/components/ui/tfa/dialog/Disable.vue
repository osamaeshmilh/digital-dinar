<template>
  <bp-base-dialog
    v-if="show"
    v-model="show"
    class="bp-ui-tfa-dialog bp-ui-tfa-dialog-disable"
    v-bind="{
      title: $t('dialogs.tfa.disable.title'),
      subtitle: $t('dialogs.tfa.disable.subtitle'),
      loading,
      deleteDialog: true,
      submitBtnText: $t('buttons.disable'),
      cardAttrs: { bodyClass: 'pb-0 pt-4' },
      ...$attrs,
    }"
    @closed="onClose"
    @submit="disableTfa"
  >
    <BpAlerts alert-key="tfaDisableDialog" />
  </bp-base-dialog>
</template>

<script>
export default {
  props: {
    modelValue: {
      type: Boolean,
    },
    disableBtnAttrs: {
      type: Object,
      default: () => ({}),
    },
  },
  computed: {
    loading() {
      return ormModel('tfa').getState('loading.disable')
    },
    show: {
      get() {
        return this.modelValue
      },
      set(value) {
        this.$emit('update:modelValue', value)
      },
    },
  },
  methods: {
    disableTfa() {
      ormModel('tfa')
        .bpApi()
        .disable()
        .then(() => this.$auth.fetchUser())
        .then(() => (this.show = false))
    },
    onClose() {
      this.$alerts.clear()
    },
  },
}
</script>

<style></style>
