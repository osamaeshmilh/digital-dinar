<template>
  <div class="bp-ui-tfa-card bp-ui-tfa-card-disabled">
    <BpBaseButton
      v-bind="{
        title: $t('buttons.enable'),
        iconClass: 'mdi-shield-check',
        color: 'success',
        size: 'large',
        loading: loading,
        ...$attrs,
      }"
      @click.prevent="enableQrCode"
    />

    <bp-ui-tfa-dialog-register
      v-model="dialogs.qrCode"
      :qr-code="qrCode"
      @success="onSuccessRegister()"
    />
  </div>
</template>

<script>
/* eslint-disable camelcase */
export default {
  inheritAttrs: false,
  props: {
    footerClass: { type: String, default: 'mt-4' },
  },
  data() {
    return {
      qrCode: '',
      dialogs: {
        qrCode: false,
        enable: false,
      },
    }
  },
  computed: {
    loading() {
      return ormModel('tfa').getState('loading.enable')
    },
  },

  methods: {
    showQrTfa() {
      return ormApi('tfa').showQr()
    },
    onSuccessRegister() {
      this.$emit('tfa-enabled')
      this.$auth.fetchUser()
    },
    enableQrCode() {
      return ormApi('tfa')
        .enable()
        .then(({ response }) => (this.qrCode = response.data.data.qr_code))
        .then(() => (this.dialogs.qrCode = true))
    },
  },
}
</script>
