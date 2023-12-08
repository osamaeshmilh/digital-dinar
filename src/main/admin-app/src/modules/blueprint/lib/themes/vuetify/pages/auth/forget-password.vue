<script setup>
definePageMeta({ middleware: 'guest', layout: 'auth-one' })
useHead({ title: $t('pages.forgetPassword.title') })

const { rules, validate } = useValidation()
const errorBag = reactive({})
const model = reactive({ email: '' })
const loading = computed(
  () =>
    hasOrmModel('auth') &&
    (ormModel('auth').loading('initForgotPassword') ||
      ormModel('auth').loading('forgotPassword'))
)

const forgetPassword = async (e) => {
  validate(e).then(
    (valid) => valid && ormApi('auth').forgotPassword({ errorBag, data: model })
  )
}
</script>

<template>
  <v-container>
    <BpBaseCard
      title-class="text-center text-black fs-1_6 font-weight-bold pb-1 mb-3"
      subtitle-class="text-center fs-0_9 mb-4"
      class="px-4 py-8 mx-auto elevation-default"
      max-width="600px"
      :title="$t('pages.forgetPassword.title')"
      :subtitle="$t('pages.forgetPassword.subtitle')"
    >
      <BpAlerts alert-key="forgetPasswordPage" />
      <v-form class="my-6" @submit.prevent="forgetPassword">
        <BpBaseInput
          v-model="model.email"
          class="required-star mb-6"
          :rules="[rules.required]"
          :errors="errorBag"
          name="email"
        />
        <BpBaseButton
          block
          color="primary"
          :title="$t('buttons.reset')"
          :loading="loading"
          type="submit"
        />
      </v-form>
      <v-divider class="mt-12 mb-6" />
      <nuxt-link
        class="fs-0_9 text-decoration-none"
        to="/auth/login"
        v-text="$t('pages.login.title')"
      />
    </BpBaseCard>
  </v-container>
</template>
