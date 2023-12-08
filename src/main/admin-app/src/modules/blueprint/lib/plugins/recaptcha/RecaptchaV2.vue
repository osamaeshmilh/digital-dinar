<!-- reference: https://github.com/nuxt-community/recaptcha-module -->
<template>
  <div
    :data-sitekey="siteKey || $recaptcha.siteKey"
    :data-size="computedDataSize"
    :data-theme="dataTheme"
    :data-badge="dataBadge"
    :data-tabindex="dataTabindex"
    data-callback="recaptchaSuccessCallback"
    data-expired-callback="recaptchaExpiredCallback"
    data-error-callback="recaptchaErrorCallback"
    class="g-recaptcha"
  />
</template>

<script>
export default {
  props: {
    siteKey: {
      type: String,
      default: '',
    },

    dataTheme: {
      default: 'light',
      type: String,

      validator: (value) => {
        return ['dark', 'light'].includes(value)
      },
    },

    dataSize: {
      default: null,
      type: String,

      validator: (value) => {
        return ['compact', 'normal', 'invisible'].includes(value)
      },
    },

    dataBadge: {
      default: 'bottomright',
      type: String,

      validator: (value) => {
        return ['bottomright', 'bottomleft', 'inline'].includes(value)
      },
    },

    dataTabindex: {
      default: 0,
      type: Number,
    },
  },

  emits: { expired: null, success: null, error: null },

  computed: {
    computedDataSize() {
      return this.dataSize || this.$recaptcha.size || 'normal'
    },
  },
  beforeUnmount() {
    this.$recaptcha.destroy()
  },

  mounted() {
    this.$recaptcha.version = 2
    this.$recaptcha.init()

    this.$recaptcha.on('recaptcha-error', this.onError)
    this.$recaptcha.on('recaptcha-success', this.onSuccess)
    this.$recaptcha.on('recaptcha-expired', this.onExpired)
  },

  methods: {
    onError(message) {
      return this.$emit('error', message)
    },

    onSuccess(token) {
      return this.$emit('success', token)
    },

    onExpired() {
      return this.$emit('expired')
    },
  },
}
</script>
