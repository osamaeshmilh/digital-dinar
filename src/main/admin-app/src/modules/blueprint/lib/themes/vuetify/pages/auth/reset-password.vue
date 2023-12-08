<script setup>
const { $router } = useNuxtApp()
definePageMeta({ middleware: 'guest', layout: 'auth-one' })
useHead({ title: $t('pages.resetPassword.title') })

let model = {}
onBeforeMount(() => {
  const query = readonly(useRoute().query)
  if (!query.token || !query.email) {
    $router.replace('/auth/login')
    return
  }
  model = reactive({
    ...{ token: query.token, email: query.email },
    password: '',
    password_confirmation: '',
  })
  $router.replace({ query: {} })
})

const loading = computed(
  () => hasOrmModel('auth') && ormModel('auth').loading('resetPassword')
)

const { rules, validate, errors } = useValidation()
const resetPassword = (e) => {
  validate(e).then(
    (valid) =>
      valid && ormApi('auth').resetPassword({ errorBag: errors, data: model })
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
      :title="$t('pages.resetPassword.title')"
      :subtitle="$t('pages.resetPassword.subtitle')"
    >
      <BpAlerts alert-key="resetPasswordPage" />
      <v-form class="my-6" validate-on="submit" @submit.prevent="resetPassword">
        <BpBaseInput
          v-model="model.email"
          :errors="errors"
          :rules="[rules.required]"
          class="required-star mb-6"
          name="email"
          disabled
        />
        <BpBaseInput
          v-model="model.password"
          :errors="errors"
          :rules="[rules.required]"
          class="required-star mb-6"
          name="password"
          password
        />
        <BpBaseInput
          v-model="model.password_confirmation"
          :errors="errors"
          :rules="[rules.required]"
          class="required-star mb-6"
          name="password_confirmation"
          password
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
