<script setup>
definePageMeta({ middleware: 'guest', layout: 'auth-one' })
useHead({ title: $t('pages.tfa.title') })

const { rules, validate, errors } = useValidation()
const otp = ref('')
const loading = computed(
  () => hasOrmModel('auth') && ormModel('auth').loading('login')
)

const { email, password } = window.history.state
if (!email || !password) {
  useRouter().push('/auth/login')
}

const login = (e) => {
  validate(e).then(
    (valid) =>
      valid &&
      ormApi('auth').login({
        data: { email, password, otp: otp.value },
        errorBag: errors,
      })
  )
}
</script>

<template>
  <v-container>
    <BpBaseCard
      title-class="text-center text-black fs-1_6 font-weight-bold pb-1 mb-6"
      class="px-4 py-8 mx-auto elevation-default"
      max-width="600px"
      :title="$t('pages.tfa.title')"
    >
      <BpAlerts closable />
      <v-form @submit.prevent="login">
        <BpBaseInput
          v-model="otp"
          name="otp"
          hide-details="auto"
          class="required-star mb-6"
          :errors="errors"
          :rules="[rules.required, rules.min(otp, 6)]"
        />
        <BpBaseButton
          :title="$t('buttons.login')"
          block
          type="submit"
          color="primary"
          :loading="loading"
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
