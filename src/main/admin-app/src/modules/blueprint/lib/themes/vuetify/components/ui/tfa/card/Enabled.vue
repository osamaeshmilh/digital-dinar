<template>
  <div class="bp-ui-tfa-card bp-ui-tfa-card-enabled">
    <BpBaseButton
      v-bind="{
        iconClass: 'mdi-close-circle',
        title: $t('buttons.disable'),
        color: 'error',
        class: smAndDown ? 'w-100 mb-2' : 'me-2',
      }"
      @click="dialogs.disable = true"
    />
    <bp-base-button
      v-bind="{
        title: $t('buttons.generateRecoveryCodes'),
        class: smAndDown ? 'w-100 mb-2' : '',
      }"
      @click="dialogs.recovery = true"
    />
    <bp-ui-tfa-dialog-recovery-codes v-model="dialogs.recovery" />
    <bp-ui-tfa-dialog-disable v-model="dialogs.disable" />
  </div>
</template>

<script>
import { useDisplay } from 'vuetify'
/* eslint-disable camelcase */
export default {
  setup() {
    const { smAndDown } = useDisplay()
    return { smAndDown }
  },
  data() {
    return {
      dialogs: {
        enable: false,
        disable: false,
        recovery: false,
      },
      recoveryCodes: [],
    }
  },
  head() {
    return {
      title: this.$t('pages.tfa.title'),
    }
  },
  methods: {
    fetchRecoveryCodes() {
      return ormModel('tfa')
        .bpApi()
        .fetchRecovery()
        .then(({ response }) => (this.recoveryCodes = response.data.data))
    },
  },
}
</script>
