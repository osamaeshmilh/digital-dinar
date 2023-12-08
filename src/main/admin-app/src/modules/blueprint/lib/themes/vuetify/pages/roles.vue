<script setup>
definePageMeta({
  middleware: 'auth',
  // permissions: ['roles-view'],
  breadcrumb: [{ text: 'navigation.roles' }],
})
const form = computed(() => ({
  fields: [
    {
      name: 'name',
      type: 'text',
      rules: 'required',
      attrs: { class: 'required-star' },
    },
    // {
    //   name: 'ar.label',
    //   getter: 'translations.ar.label',
    //   type: 'text',
    //   rules: 'required',
    //   attrs: {
    //     label: $t('attributes.label') + ' (العربية)',
    //     class: 'required-star',
    //   },
    // },
    // {
    //   name: 'en.label',
    //   getter: 'translations.en.label',
    //   type: 'text',
    //   rules: 'required',
    //   attrs: {
    //     label: $t('attributes.label') + ' (English)',
    //     class: 'required-star',
    //   },
    // },
    {
      name: 'permissions',
      type: 'select',
      options: ormModel('permissions').list(),
      attrs: {
        label: $t('labels.permissions'),
        itemTitle: 'label',
        itemValue: 'permission',
        loading: false,
        multiple: true,
        class: 'required-star',
      },
    },
  ],
}))

onMounted(() => ormApi('permissions').fetch())

const somePermissions = (crudDialog) => {
  return crudDialog?.selected?.value?.permissions?.length > 0
}
const allPermissions = (crudDialog) => {
  return (
    (ormModel('permissions').list() || []).length ===
    crudDialog?.selected?.value?.permissions?.length
  )
}
const toggle = (crudDialog) => {
  if (allPermissions(crudDialog)) {
    crudDialog.selected.value.permissions = []
  } else {
    crudDialog.selected.value.permissions = ormModel('permissions').list()
  }
}
</script>

<template>
  <BpModelIndexPage
    entity="roles"
    :form="form"
    :create="true || $guard.hasPermission(['roles-update'])"
    :update="true || $guard.hasPermission(['roles-update'])"
    :delete="true || $guard.hasPermission(['roles-delete'])"
  >
    <template #[`item.permissions`]="{ item }">
      <BpBaseChip>
        {{ item.permissions.length }}
        <v-menu
          activator="parent"
          content-class="bg-grey pa-2"
          open-on-hover
          :open-delay="0"
        >
          <ul style="max-height: 400px; overflow-y: scroll">
            <li
              v-for="permission in item.permissions"
              :key="item.id + '' + permission"
            >
              {{ permission }}
            </li>
          </ul>
        </v-menu>
      </BpBaseChip>
    </template>

    <template #[`field.permissions.slots.prepend-item`]="{ crudDialog }">
      <v-list-item title="Select All" @click="toggle(crudDialog)">
        <template #prepend>
          <v-checkbox-btn
            :indeterminate="
              somePermissions(crudDialog) && !allPermissions(crudDialog)
            "
            :model-value="somePermissions(crudDialog)"
          />
        </template>
      </v-list-item>
      <v-divider />
    </template>
    <template
      #[`field.permissions.slots.selection`]="{ index, item, ...slotProps }"
    >
      <v-chip v-if="index < 3" size="small">
        {{ item.title }}
      </v-chip>
      <span v-if="index === 3" size="small" class="text-grey fs-0_8 ms-1">
        (+{{
          slotProps?.crudDialog?.selected?.value?.permissions?.length - 3 || 0
        }}
        {{ $t('labels.otherPermissions') }})
      </span>
    </template>
    <template #[`item.actions`]="{ item, crudDialog }">
      <BpBaseButton
        v-if="item.editable && (true || $guard.hasPermission(['roles-update']))"
        table-action
        icon-class="mdi-pencil"
        @click="crudDialog.openEditor(item)"
      />
      <BpBaseButton
        v-if="
          item.deletable && (true || $guard.hasPermission(['roles-delete']))
        "
        table-action
        icon-class="mdi-delete"
        @click="crudDialog.openDelete(item)"
      />
    </template>
  </BpModelIndexPage>
</template>

<style></style>
