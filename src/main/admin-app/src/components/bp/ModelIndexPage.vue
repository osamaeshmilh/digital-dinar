<script setup>
const props = defineProps({
  entity: { type: String, required: true },
  crudDialogAttrs: { type: Object, default: () => ({}) },
  alertAttrs: { type: Object, default: () => ({}) },
  headerAttrs: { type: Object, default: () => ({}) },
  tableAttrs: { type: Object, default: () => ({}) },
  deleteDialogAttrs: { type: Object, default: () => ({}) },
  editorDialogAttrs: { type: Object, default: () => ({}) },
  previewDialogAttrs: { type: Object, default: () => ({}) },
  filterBtnAttrs: { type: Object, default: () => ({}) },
  form: { type: [Object, undefined], default: undefined },
  create: Boolean,
  update: Boolean,
  delete: Boolean,
  filter: Boolean,
  clear: Boolean,
  preview: { type: Boolean, default: true },
  export: Boolean,
  table: { type: Boolean, default: true },
  header: { type: Boolean, default: true },
})

const emit = defineEmits(['on-export'])

const table = ref(null)
const crudDialog = useCrud({
  ...(props.form ? { form: props.form } : {}),
  ...props.crudDialogAttrs,
})

const onCreate = (data, form) =>
  ormApi(props.entity)
    .create({ data, errorBag: crudDialog.errors })
    .then(() => {
      crudDialog.editor.value = false
      ormApi(props.entity).fetch()
      form?.reset?.()
    })
const onUpdate = (data) =>
  ormApi(props.entity)
    .update({
      id: crudDialog.selected.value.id,
      data: { ...data, id: crudDialog.selected.value.id },
      errorBag: crudDialog.errors,
    })
    .then(() => {
      crudDialog.editor.value = false
      ormApi(props.entity).fetch()
    })
const onDelete = () =>
  ormApi(props.entity)
    .delete({ id: crudDialog.selected.value.id })
    .then(() => {
      crudDialog.delete.value = false
      ormApi(props.entity).fetch()
    })

const onExport = () =>
  ormApi(props.entity)
    .export()
    .then(({ response }) => emit('on-export', response.data))

const loadItems = ({ page, itemsPerPage, sortBy }) => {
  ormModel(props.entity)?.setPage?.(page - 1)
}

defineExpose({
  crudDialog,
  onDelete,
  onUpdate,
  onCreate,
})
</script>

<template>
  <v-container :class="`bp-model-index-page bp-page--${props.entity}`">
    <slot v-if="props.header" name="header" v-bind="{ props, crudDialog }">
      <BpLayoutPageHeader
        v-bind="{
          class: 'bg-transparent text-black mb-3',
          ...props.headerAttrs,
        }"
      >
        <template #second-column>
          <slot
            name="header-second-column-prepend"
            v-bind="{ props, crudDialog }"
          ></slot>
          <slot name="header-second-column" v-bind="{ props, crudDialog }">
            <BpModelFilterButton
              v-if="props.filter"
              v-bind="{
                entity: props.entity,
                btnAttrs: { border: true, disabled: false },
                ...filterBtnAttrs,
              }"
            />
            <BpBaseButton
              v-if="props.create"
              class="ms-2"
              color="primary"
              icon-class="mdi-plus"
              :title="$t('buttons.add')"
              @click="crudDialog.openEditor()"
            />
            <BpBaseButton
              v-if="props.clear"
              :disabled="
                ormModel(props.entity).getState('pagination.total') === 0
              "
              class="ms-2"
              color="error"
              icon-class="mdi-delete"
              :title="$t('buttons.delete')"
              @click="crudDialog.openEditor({}, 'clear')"
            />
            <BpBaseButton
              v-if="props.export"
              icon-class="mdi-export"
              class="ms-2"
              rounded
              :title="$t('buttons.export')"
              :loading="ormModel(props.entity).loading('export')"
              @click="onExport"
            />
          </slot>
          <slot
            name="header-second-column-append"
            v-bind="{ props, crudDialog }"
          ></slot>
        </template>
      </BpLayoutPageHeader>
    </slot>
    <div v-if="props.filter" id="filters-container"></div>
    <slot name="alert" v-bind="{ props }">
      <div class="bg-white rounded-xl"></div>
    </slot>
    <slot name="prepend" v-bind="{ crudDialog, props }" />
    <slot>
      <slot v-if="props.table" name="table" v-bind="{ props, crudDialog }">
        <BpModelDataTable
          ref="table"
          :items-per-page="10"
          :items-length="ormState(props.entity, 'pagination.total')"
          class="elevation-1"
          item-value="name"
          :show-actions="props.preview || props.update || props.delete"
          :items-per-page-options="[{ value: 10, title: '10' }]"
          v-bind="{ model: ormModel(props.entity), ...props.tableAttrs }"
          @update:options="loadItems"
        >
          <template
            v-for="name in Object.keys($slots).filter((name) =>
              name.includes('item.'),
            )"
            #[name]="slotProps"
          >
            <slot
              v-if="slotProps"
              :name="name"
              v-bind="{ ...slotProps, crudDialog }"
            />
          </template>
          <template #[`item.data.amount`]="{ item }">
            {{ formatMoney(item.data.amount, { currency: true }) }}
          </template>
          <template #[`item.data.totalPrice`]="{ item }">
            {{ formatMoney(item.data.totalPrice, { currency: true }) }}
          </template>
          <template #[`item.actions`]="{ item }">
            <div class="d-flex justify-end">
              <slot
                name="table-actions-prepend"
                v-bind="{ item, props, crudDialog }"
              ></slot>
              <slot name="table-actions" v-bind="{ item, props, crudDialog }">
                <BpBaseButton
                  v-if="props.preview"
                  table-action
                  color="grey"
                  icon-class="mdi-eye"
                  :tooltip-text="$t('buttons.view')"
                  @click="crudDialog.openEditor(item.data, 'preview')"
                />
                <BpBaseButton
                  v-if="props.update"
                  table-action
                  color="grey"
                  icon-class="mdi-pencil"
                  :tooltip-text="$t('buttons.edit')"
                  @click="crudDialog.openEditor(item.data)"
                />
                <BpBaseButton
                  v-if="props.delete"
                  table-action
                  color="grey"
                  icon-class="mdi-delete"
                  :tooltip-text="$t('buttons.delete')"
                  @click="crudDialog.openDelete(item.data)"
                />
              </slot>
              <slot
                name="table-actions-append"
                v-bind="{ item, props, crudDialog }"
              ></slot>
            </div>
          </template>
        </BpModelDataTable>
      </slot>
    </slot>
    <slot name="append" v-bind="{ crudDialog, props }" />
    <slot
      v-if="props.delete"
      name="delete-dialog"
      v-bind="{ crudDialog, props }"
    >
      <BpDialogDelete
        v-model="crudDialog.delete.value"
        v-bind="{
          entity: $t('entities.singular.' + camelize(props.entity)),
          loading: ormModel(props.entity).loading('delete'),
          alertKey: props.entity + 'DeleteDialog',
          onConfirm: onDelete,
          ...props.deleteDialogAttrs,
        }"
      />
    </slot>
    <slot v-if="props.clear" name="clear-dialog" v-bind="{ crudDialog, props }">
      <BpDialogDelete
        v-model="crudDialog.dialogs.clear"
        v-bind="{
          entity: $t('entities.' + camelize(props.entity)),
          loading: ormModel(props.entity).loading('clear'),
          alertKey: props.entity + 'ClearDialog',
          action: 'clear',
          onConfirm: onDelete,
          ...props.clearDialogAttrs,
        }"
      />
    </slot>
    <slot
      v-if="props.preview"
      name="preview-dialog"
      v-bind="{ crudDialog, props }"
    >
      <BpDialogPreview
        v-model="crudDialog.dialogs.preview"
        v-bind="{ data: crudDialog.selected.value, ...previewDialogAttrs }"
      >
        <template
          v-for="name in Object.keys($slots || {}).filter(
            (name) => name.includes('item.') && !$slots[`${name}.value`],
          )"
          #[`${name}.value`]="slotProps"
        >
          <v-list-item-title v-if="slotProps" :key="`preview-${name}`">
            <slot
              :name="name"
              v-bind="{
                ...slotProps,
                crudDialog,
                item: crudDialog.selected.value,
              }"
            />
          </v-list-item-title>
        </template>

        <template
          v-for="name in Object.keys($slots || {}).filter(
            (name) => name.includes('item.') && name.includes('.value'),
          )"
          #[name]="slotProps"
        >
          <slot
            v-if="slotProps"
            :name="name"
            v-bind="{ ...slotProps, crudDialog }"
          />
        </template>
      </BpDialogPreview>
    </slot>

    <slot
      v-if="props.update || props.create"
      name="editor-dialog"
      v-bind="{ crudDialog, props }"
    >
      <BpDialogEditor
        v-model="crudDialog.editor.value"
        v-model:selected="crudDialog.selected.value"
        v-bind="{
          ...(props.form ? { form: props.form } : {}),
          entity: camelize(props.entity),
          cardAttrs: { color: 'secondary' },
          errors: crudDialog.errors,
          updateLoading: ormModel(props.entity).loading('update'),
          createLoading: ormModel(props.entity).loading('create'),
          onUpdate,
          onCreate,
          ...props.editorDialogAttrs,
        }"
      >
        <template v-if="$slots['prepend-fields']" #prepend-fields>
          <slot name="prepend-fields" v-bind="{ ...slotProps, crudDialog }" />
        </template>
        <template
          v-for="name in Object.keys($slots).filter((name) =>
            name.includes('field.'),
          )"
          #[name]="slotProps"
        >
          <slot
            v-if="slotProps"
            :name="name"
            v-bind="{ ...slotProps, crudDialog }"
          />
        </template>
      </BpDialogEditor>
    </slot>
  </v-container>
</template>
