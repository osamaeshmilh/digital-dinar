<script setup>
const props = defineProps({
  titleClass: { type: String, default: '' },
  subtitleClass: { type: String, default: '' },
  title: { type: String, default: '' },
  subtitle: { type: String, default: '' },
  submitBtnText: { type: String, default: '' },
  footerClass: { type: String, default: 'pa-5' },
  submitBtnColor: { type: String, default: 'primary' },
  closeBtnAttrs: { type: Object, default: () => ({}) },
  submitBtnAttrs: { type: Object, default: () => ({}) },
  cardAttrs: { type: Object, default: () => ({}) },
  noSubmit: Boolean,
  cardLoading: Boolean,
  modelValue: Boolean,
  deleteDialog: Boolean,
  loading: Boolean,
  showDivider: Boolean,
  showFooter: Boolean,
})
const emit = defineEmits(['closed', 'update:modelValue'])
const dialog = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value),
})
const getTitle = computed(() => {
  if (props.submitBtnText) {
    return props.submitBtnText
  } else if (props.deleteDialog) {
    return $t('buttons.delete')
  } else {
    return $t('buttons.save')
  }
})

const closeDialog = () => {
  dialog.value = false
  emit('closed', true)
}
</script>

<template>
  <v-dialog
    v-model="dialog"
    class="bp-base-dialog"
    v-bind="{
      persistent: true,
      maxWidth: '600px',
      overlayOpacity: '0.2',
      ...$attrs,
    }"
  >
    <slot name="card">
      <BpBaseCard
        v-bind="{
          title,
          titleClass,
          subtitle,
          subtitleClass,
          loading: cardLoading,
          footerClass,
          showDivider,
          closeBtn: true,
          onClose: closeDialog,
          ...cardAttrs,
        }"
      >
        <slot />
        <template v-if="showFooter || deleteDialog || $slots.footer" #footer>
          <slot name="footer">
            <BpBaseButton
              v-bind="{
                variant: 'text',
                title: $t('buttons.close'),
                ...closeBtnAttrs,
              }"
              @click="closeDialog"
            />
            <v-spacer />
            <BpBaseButton
              v-if="!noSubmit"
              v-bind="{
                width: 120,
                color: deleteDialog ? 'error' : submitBtnColor,
                title: getTitle,
                loading,
                ...submitBtnAttrs,
              }"
              @click="$emit('submit')"
            />
          </slot>
        </template>
        <template
          v-for="(slot, index) of Object.keys($slots)"
          :key="index"
          #[slot]
        >
          <slot :name="slot"></slot>
        </template>
      </BpBaseCard>
    </slot>
  </v-dialog>
</template>
