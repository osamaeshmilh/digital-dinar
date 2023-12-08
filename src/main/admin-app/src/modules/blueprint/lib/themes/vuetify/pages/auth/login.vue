<script setup>
definePageMeta({ middleware: 'guest', layout: 'auth-one' })
useHead({ title: $t('pages.login.title') })

const { rules, validate, errors } = useValidation()
const loading = computed(
  () => hasOrmModel('auth') && ormModel('auth').loading('login')
)
const model = ref({
  email: '',
  password: '',
  remember: false,
})
const login = (e) => {
  validate(e).then(
    (valid) =>
      valid && ormApi('auth').login({ data: model.value, errorBag: errors })
  )
}
</script>

<template>
  <v-container>
    <BpBaseCard
      title-class="text-center text-black fs-1_6 font-weight-bold pb-1 mb-3"
      subtitle-class="text-center  fs-0_9 mb-4"
      class="px-4 py-8 mx-auto elevation-default"
      max-width="600px"
      :title="$t('pages.login.title')"
      :subtitle="$t('pages.login.subtitle')"
    >
      <BpAlerts closable />
      <v-form validate-on="submit" class="my-6" @submit.prevent="login">
        <BpBaseInput
          v-model="model.email"
          :rules="[rules.required, rules.email]"
          class="required-star mb-6"
          :errors="errors"
          name="email"
        />
        <BpBaseInput
          v-model="model.password"
          :rules="[rules.required]"
          class="required-star mb-6"
          :errors="errors"
          name="password"
          password
        />
        <v-checkbox-btn
          v-model="model.remember"
          :label="$t('labels.rememberMe')"
          class="mb-6 mt-0 pt-0"
        />
        <BpBaseButton
          block
          color="primary"
          :title="$t('buttons.login')"
          :loading="loading"
          type="submit"
        />
      </v-form>
      <v-divider class="mt-12 mb-6" />
      <nuxt-link
        class="fs-0_9 text-decoration-none"
        to="/auth/forget-password"
        v-text="$t('pages.forgetPassword.title')"
      />
    </BpBaseCard>
  </v-container>
</template>
