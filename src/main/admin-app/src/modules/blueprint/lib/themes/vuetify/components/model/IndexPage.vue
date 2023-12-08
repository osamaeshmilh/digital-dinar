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
  preview: Boolean,
  table: { type: Boolean, default: true },
  header: { type: Boolean, default: true },
})

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
      data,
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
      <BpLayoutPageHeader v-bind="props.headerAttrs">
        <template #second-column>
          <slot
            name="header-second-column-prepend"
            v-bind="{ props, crudDialog }"
          ></slot>
          <slot name="header-second-column" v-bind="{ props, crudDialog }">
            <BpModelFilterButton
              v-if="props.filter"
              :model="ormModel(props.entity)"
              v-bind="filterBtnAttrs"
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
      <BpAlerts
        v-bind="{
          alertKey: camelize(props.entity) + 'Index',
          ...props.alertAttrs,
        }"
      />
    </slot>
    <slot name="prepend" v-bind="{ crudDialog, props }" />
    <slot>
      <slot v-if="props.table" name="table" v-bind="{ props, crudDialog }">
        <BpModelDataTable
          v-bind="{ model: ormModel(props.entity), ...props.tableAttrs }"
        >
          <template
            v-for="name in Object.keys($slots).filter((name) =>
              name.includes('item.')
            )"
            #[name]="slotProps"
          >
            <slot
              v-if="slotProps"
              :name="name"
              v-bind="{ ...slotProps, crudDialog }"
            />
          </template>
          <template #[`item.actions`]="{ item }">
            <slot
              name="table-actions-prepend"
              v-bind="{ item, props, crudDialog }"
            ></slot>
            <slot name="table-actions" v-bind="{ item, props, crudDialog }">
              <BpBaseButton
                v-if="props.preview"
                table-action
                icon-class="mdi-eye"
                :tooltip-text="$t('buttons.view')"
                @click="crudDialog.openEditor(item, 'preview')"
              />
              <BpBaseButton
                v-if="props.update"
                table-action
                icon-class="mdi-pencil"
                :tooltip-text="$t('buttons.edit')"
                @click="crudDialog.openEditor(item)"
              />
              <BpBaseButton
                v-if="props.delete"
                table-action
                icon-class="mdi-delete"
                :tooltip-text="$t('buttons.delete')"
                @click="crudDialog.openDelete(item)"
              />
            </slot>
            <slot
              name="table-actions-append"
              v-bind="{ item, props, crudDialog }"
            ></slot>
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
            (name) => name.includes('item.') && !$slots[`${name}.value`]
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
            (name) => name.includes('item.') && name.includes('.value')
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
          errors: crudDialog.errors,
          updateLoading: ormModel(props.entity).loading('update'),
          createLoading: ormModel(props.entity).loading('create'),
          onUpdate,
          onCreate,
          ...props.editorDialogAttrs,
        }"
      >
        <template
          v-for="name in Object.keys($slots).filter((name) =>
            name.includes('field.')
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
