<template>
  <bp-base-dialog
    v-model="show"
    class="bp-ui-tfa-dialog bp-ui-tfa-dialog-recovery-codes"
    :title="$t('dialogs.tfa.recoveryCodes.title')"
    :subtitle="$t('dialogs.tfa.recoveryCodes.subtitle')"
    :card-attrs="{ overlayLoading: loading }"
    @closed="$alerts.clear()"
  >
    <bp-alerts alert-key="recoveryCodesDialog" />
    <BpBaseCard
      flat
      class="text-left"
      color="grey-lighten-3"
      min-height="230px"
    >
      <span
        v-for="code in recoveryCodes ?? []"
        :key="code"
        class="d-block"
        v-text="code"
      />
    </BpBaseCard>
    <template #footer>
      <bp-base-button
        variant="text"
        :title="$t('buttons.close')"
        @click="show = false"
      />
      <v-spacer />
      <bp-ui-copy-button :string="recoveryCodes" />
    </template>
  </bp-base-dialog>
</template>

<script>
export default {
  props: {
    modelValue: {
      type: Boolean,
    },
  },
  data() {
    return {
      recoveryCodes: [],
    }
  },
  computed: {
    loading() {
      return ormModel('tfa').getState('loading.fetchRecovery')
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
  watch: {
    show(value) {
      value && !this.recoveryCodes.length && this.fetchRecoveryCodes()
    },
  },
  methods: {
    fetchRecoveryCodes() {
      ormApi('tfa')
        .fetchRecovery()
        .then((codes) => (this.recoveryCodes = codes))
    },
  },
}
</script>

<style></style>
