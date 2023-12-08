<template>
  <bp-base-dialog
    v-model="show"
    class="bp-ui-tfa-dialog bp-ui-tfa-dialog-register"
    :title="$t('dialogs.tfa.enable.title')"
    :subtitle="$t('dialogs.tfa.enable.subtitle')"
    :card-loading="loading"
  >
    <div class="d-flex justify-center align-center">
      <v-img
        v-if="qrCode"
        :src="`data:image/jpeg;base64,${qrCode}`"
        alt="QR Code"
        width="240px"
        height="240px"
        contain
      />
    </div>
    <bp-base-form
      ref="register-tfa-form"
      alert-key="tfaRegisterDialog"
      :schema="form"
      :error-bag="errors"
      :form-attrs="{ class: 'pt-4', readOnly: loading }"
      @submit="register"
    >
      <template #form-actions>
        <v-col cols="12" class="d-flex justify-end align-center py-5">
          <bp-base-button
            icon-class="mdi-close"
            variant="text"
            :title="$t('buttons.cancel')"
            @click="cancel"
          />
          <v-spacer />
          <bp-base-button
            type="submit"
            :loading="loading"
            size="large"
            icon-class="mdi-shield-check"
            :title="$t('buttons.enable')"
            color="success"
          />
        </v-col>
      </template>
    </bp-base-form>
  </bp-base-dialog>
</template>

<script>
/* eslint-disable vue/no-v-html */
export default {
  props: {
    modelValue: {
      type: Boolean,
    },
    qrCode: {
      type: String,
      required: true,
    },
  },
  setup() {
    const validation = useValidation()
    return { ...validation, errors: ref({}) }
  },
  data() {
    return {
      form: {
        fields: [
          {
            name: 'otp',
            type: 'text',
            rules: 'required|number',
            attrs: { class: 'required-star' },
          },
        ],
      },
    }
  },
  computed: {
    loading() {
      return ormModel('tfa').getState('loading.register')
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
    register(model) {
      ormModel('tfa')
        .bpApi()
        .register({ errorBag: this.errors, data: model })
        .then(() => {
          this.show = false
          this.$emit('success')
        })
    },
    cancel() {
      this.$alerts.clear()
      this.$refs['register-tfa-form'].$refs.form.reset()
      this.show = false
    },
  },
}
</script>

<style></style>
