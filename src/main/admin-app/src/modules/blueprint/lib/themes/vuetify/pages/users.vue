<script setup>
definePageMeta({
  middleware: 'auth',
  // permissions: ['users-view'],
  breadcrumb: [{ text: 'navigation.users' }],
})
const crudPage = ref(null)
const form = computed(() => ({
  fields: [
    {
      name: 'name',
      type: 'text',
      rules: 'required',
      attrs: { class: 'required-star' },
    },
    {
      name: 'email',
      type: 'text',
      rules: 'required|email',
      attrs: { class: 'required-star' },
    },
    {
      name: 'password',
      type: 'password',
      rules: crudPage?.value?.crudDialog?.selected?.value
        ? crudPage?.value?.crudDialog?.selected?.value.password &&
          crudPage?.value?.crudDialog?.selected?.value.password.length > 0
          ? 'minLength:8'
          : ''
        : 'required|minLength:8',
      attrs: {
        reverseDirection: true,
        class: crudPage?.value?.crudDialog?.selected.value
          ? ''
          : 'required-star',
      },
    },
    {
      name: 'role_id',
      getter: 'role.id',
      type: 'select',
      rules: 'required',
      attrs: {
        label: $t('attributes.role'),
        class: 'required-star',
        itemTitle: 'label',
        itemValue: 'id',
      },
      options: ormModel('roles').all(),
    },
  ],
}))

onMounted(() => {
  if (true || useNuxtApp().$guard.hasPermission('roles-view'))
    ormApi('roles').fetch()
})
const onDisableTfa = () =>
  ormApi('users')
    .resetTfa({ id: crudPage?.value?.crudDialog?.selected?.value?.id })
    .then(() => {
      crudPage.value.crudDialog.dialogs.disableTfa = false
      ormApi('users').fetch()
    })
</script>

<template>
  <BpModelIndexPage
    ref="crudPage"
    entity="users"
    :form="form"
    filter
    :create="true || $guard.hasPermission(['users-update'])"
    :update="true || $guard.hasPermission(['users-update'])"
    :delete="true || $guard.hasPermission(['users-delete'])"
  >
    <template #[`item.role.name`]="{ item }">
      <v-chip
        v-if="item.role.id"
        variant="outlined"
        size="small"
        class="text-uppercase"
        :text="item.role.name"
      />
    </template>
    <template #[`item.tfa_enabled`]="{ item }">
      <v-chip
        :color="item.has_tfa_enabled ? 'success' : ''"
        :prepend-icon="item.has_tfa_enabled ? 'mdi-check' : 'mdi-close'"
        :text="$t('labels.' + (item.has_tfa_enabled ? 'enabled' : 'disabled'))"
      />
    </template>
    <template #table-actions-prepend="{ item, crudDialog }">
      <BpBaseButton
        v-if="item.has_tfa_enabled"
        size="small"
        variant="text"
        border
        flat
        color="error"
        class="me-2"
        :title="$t('buttons.disable') + ' ' + $t('attributes.tfa')"
        @click="crudDialog.openEditor(item, 'disableTfa')"
      />
    </template>
    <template #append="{ crudDialog }">
      <BpBaseDialog
        v-model="crudDialog.dialogs.disableTfa"
        :title="$t('dialogs.tfa.userDisable.title')"
        :subtitle="$t('dialogs.tfa.userDisable.subtitle')"
        show-footer
        :submit-btn-text="$t('buttons.confirm')"
        :loading="ormModel('users').loading('resetTfa')"
        @submit="onDisableTfa"
      >
        <BpAlerts alert-key="userResetTfaDialog" />
      </BpBaseDialog>
    </template>
  </BpModelIndexPage>
</template>

<style></style>
