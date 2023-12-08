<script>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [
    {
      title: 'pages.profile.account',
      to: '/account',
    },
  ],
})
export default {
  data() {
    return {
      dialogs: {
        recoveryCodes: false,
      },
      change: false,
      user: Object.assign({}, this.$auth.user.value),
      profileFormErrors: {},
      profileForm: {
        fields: [
          {
            name: 'name',
            type: 'text',
            rules: 'required',
            attrs: {
              class: 'required-star',
            },
          },
          {
            name: 'email',
            type: 'text',
            rules: 'required|email',
            attrs: {
              class: 'required-star',
            },
          },
        ],
      },
      passwordFormErrors: {},
      passwordForm: {
        fields: [
          {
            name: 'current_password',
            type: 'text',
            rules: 'required',
            attrs: {
              reverseDirection: true,
              password: true,
              class: 'required-star',
            },
          },
          {
            name: 'password',
            type: 'text',
            rules: 'required|minLength:8',
            attrs: {
              reverseDirection: true,
              password: true,
              class: 'required-star',
            },
          },
          {
            name: 'password_confirmation',
            type: 'text',
            rules: 'required|minLength:8',
            attrs: {
              reverseDirection: true,
              password: true,
              class: 'required-star',
            },
          },
        ],
      },
    }
  },
  computed: {
    tfaEnabled() {
      return this.$auth.user?.value?.tfa || false
    },
  },
  methods: {
    updateProfile(model) {
      ormApi('account').updateProfile({
        errorBag: this.profileFormErrors,
        data: model,
      })
    },
    updatePassword(model, form) {
      model.email = this.$auth.user.value.email
      model.name = this.$auth.user.value.name
      ormApi('account')
        .updatePassword({ errorBag: this.passwordFormErrors, data: model })
        .then(() => form.reset())
    },
  },
}
</script>

<template>
  <v-container class="bp-page--acount">
    <BpLayoutPageHeader />
    <BpBaseCard
      :title="$t('pages.profile.title')"
      :subtitle="$t('pages.profile.subtitle')"
      class="mb-6 elevation-default pa-5"
      header-row
      :header-row-attrs="{ align: 'start' }"
      :header-col-attrs="{ cols: 12, lg: 6 }"
    >
      <template #header-second-column>
        <BpBaseForm
          v-model="user"
          :loading="
            hasOrmModel('account') &&
            ormModel('account').loading('updateProfile')
          "
          alert-key="profileForm"
          :error-bag="profileFormErrors"
          :schema="profileForm"
          @submit="updateProfile"
        />
      </template>
    </BpBaseCard>
    <BpBaseCard
      v-if="hasOrmModel('tfa')"
      :title="$t('pages.tfa.title')"
      :subtitle="$t('pages.tfa.subtitle')"
      class="mb-6 elevation-default pa-5"
      header-row
      :header-col-attrs="{ cols: 12, lg: 6 }"
    >
      <template #header-second-column>
        <BpAlerts alert-key="tfaCard" />
        <div class="d-flex justify-end">
          <BpUiTfaCardDisabled
            v-if="!tfaEnabled"
            rounded
            width="140"
            @tfa-enabled="dialogs.recoveryCodes = true"
          />
          <BpUiTfaCardEnabled v-else />
          <BpUiTfaDialogRecoveryCodes v-model="dialogs.recoveryCodes" />
        </div>
      </template>
    </BpBaseCard>
    <BpBaseCard
      :title="$t('pages.changePassword.title')"
      class="mb-6 elevation-default pa-5"
      header-row
      :header-row-attrs="{ align: 'start' }"
      :header-col-attrs="{ cols: 12, lg: 6 }"
    >
      <template #header-second-column>
        <BpBaseForm
          :schema="passwordForm"
          :loading="
            hasOrmModel('account') &&
            ormModel('account').loading('updatePassword')
          "
          alert-key="passwordForm"
          :error-bag="passwordFormErrors"
          @submit="updatePassword"
        />
      </template>
    </BpBaseCard>
  </v-container>
</template>

<style>
.bp-page--acount .v-card-item__content {
  overflow: visible;
}
</style>
