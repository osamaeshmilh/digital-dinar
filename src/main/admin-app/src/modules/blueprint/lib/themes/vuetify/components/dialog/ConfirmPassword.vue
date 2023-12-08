<script>
export default {
  props: {
    modelValue: Boolean,
    callback: { type: Function, default: () => {} },
    closeBtnAttrs: { type: Object, default: () => ({}) },
    submitBtnAttrs: { type: Object, default: () => ({}) },
    inputAttrs: { type: Object, default: () => ({}) },
  },
  setup() {
    const validation = useValidation()
    return validation
  },
  data: () => ({
    password: '',
  }),
  computed: {
    loading() {
      return ormModel('auth').getState('loading.confirmPassword')
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
    async confirmPassword(e) {
      if (await !this.validate(e)) {
        return
      }

      ormModel('auth')
        .bpApi()
        .confirmPassword({
          errorBag: this.errors,
          data: { password: this.password },
        })
        .then(() => {
          this.callback()
          this.onCancel()
        })
    },
    onCancel() {
      this.$alerts.clear()
      this.errors = {}
      this.password = ''
      this.show = false
    },
  },
}
</script>

<template>
  <BpBaseDialog
    v-if="show"
    v-model="show"
    class="bp-dialog-confirm-password"
    v-bind="{
      title: $t('dialogs.confirmPassword.title'),
      subtitle: $t('dialogs.confirmPassword.subtitle'),
      loading,
      cardAttrs: { bodyClass: 'pa-5' },
      ...$attrs,
    }"
  >
    <BpAlerts alert-key="confirmPasswordDialog" />
    <v-form validate-on="submit" @submit.prevent="confirmPassword">
      <BpBaseInput
        v-model="password"
        v-bind="{
          name: 'password',
          class: 'mb-6',
          password: true,
          errors,
          rules: [rules.required],
          ...inputAttrs,
        }"
      />

      <div class="d-flex align-center">
        <BpBaseButton
          v-bind="{
            title: $t('buttons.cancel'),
            variant: 'text',
            ...closeBtnAttrs,
          }"
          @click="onCancel"
        />
        <v-spacer />
        <BpBaseButton
          v-bind="{
            title: $t('buttons.continue'),
            color: 'primary',
            type: 'submit',
            ...submitBtnAttrs,
          }"
        />
      </div>
    </v-form>
  </BpBaseDialog>
</template>
