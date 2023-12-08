<template>
  <BpBaseDialog
    v-model="model"
    class="bp-dialog-editor"
    v-bind="{
      title: entityLabel,
      noFooter: true,
      showDivider: false,
      ...$attrs,
    }"
    @closed="$emit('closed')"
  >
    <BpBaseForm
      ref="form"
      v-bind="{
        modelValue: selected ? selected : formData,
        schema: form,
        rowAttrs: { class: 'ma-0' },
        alertKey,
        errorBag: errors,
        onSubmit: (data, form) =>
          selected?.id ? updateItem(data, form) : createItem(data, form),
        ...formAttrs,
      }"
    >
      <template
        v-for="(slot, index) of Object.keys($slots)"
        :key="index"
        #[slot]="data"
      >
        <slot :name="slot" v-bind="data"></slot>
      </template>

      <template #form-actions>
        <v-col
          v-bind="{
            class:
              'd-flex justify-space-between align-center w-100 py-5 flex-wrap',
            ...footerAttrs,
          }"
        >
          <BpBaseButton
            v-bind="{
              variant: 'text',
              title: $t('buttons.cancel'),
              onClick: () => (model = false),
            }"
          />
          <BpBaseButton
            v-bind="{
              type: 'submit',
              width: '120px',
              color: 'primary',
              title: selected?.id ? $t('buttons.save') : $t('buttons.add'),
              loading: selected?.id ? updateLoading : createLoading,
            }"
          />
        </v-col>
      </template>
    </BpBaseForm>
  </BpBaseDialog>
</template>

<script>
export default {
  props: {
    modelValue: Boolean,
    entity: { type: String, required: true },
    errors: { type: Object, default: () => ({}) },
    form: {
      required: true,
      type: Object,
      default: () => ({}),
    },
    formAttrs: { type: Object, default: () => ({}) },
    footerAttrs: { type: Object, default: () => ({}) },
    updateLoading: Boolean,
    createLoading: Boolean,
    selected: {},
  },
  data: () => ({
    formData: {},
  }),
  computed: {
    entityLabel() {
      return this.$t(
        'dialogs.' + (this.selected?.id ? 'update' : 'add') + '.title',
        { entity: this.$t('entities.singular.' + this.entity) }
      )
    },
    alertKey() {
      return this.entity + (this.selected?.id ? 'UpdateDialog' : 'CreateDialog')
    },
    model: {
      get() {
        return this.modelValue
      },
      set(value) {
        this.$emit('update:modelValue', value)
      },
    },
  },
  watch: {
    model() {
      this.formData = {}
      this.$alerts?.clearByKey?.(this.alertKey)
    },
  },
  methods: {
    updateItem(data, form) {
      this.$emit('update', data, form)
    },
    createItem(data, form) {
      this.$emit('create', data, form)
    },
  },
}
</script>

<style></style>
