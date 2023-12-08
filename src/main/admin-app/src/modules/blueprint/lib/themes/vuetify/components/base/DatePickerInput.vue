<template>
  <v-overlay
    ref="menu"
    v-model="menuToggle"
    v-bind="{
      eager: true,
      openOnFocus: false,
      openOnClick: true,
      closeOnContentClick: false,
      closeDelay: 0,
      openDelay: 0,
      locationStrategy: 'connected',
      scrim: false,
      scrollStrategy: 'reposition',
      maxWidth: '280px',
      contentProps: { style: 'min-width: unset !important;' },
      ...menuAttrs,
    }"
  >
    <template #activator="{ props }">
      <BpBaseInput
        ref="textField"
        v-model="model"
        autocomplete="off"
        v-bind="{ ...$attrs, ...props }"
        @update:focused="focusHandler"
      />
    </template>

    <v-card ref="card" class="v-theme--default" max-width="280px">
      <v-date-picker
        ref="datepicker"
        v-model.string="model"
        class="w-100"
        color="primary"
        disable-page-swipe
        :locale="$vuetify.locale.current"
        borderless
        :masks="{ modelValue: 'YYYY-MM-DD' }"
        v-bind="{
          ...(minToday ? { minDate: new Date() } : {}),
          ...(maxToday ? { maxDate: new Date() } : {}),
          ...datePickerAttrs,
        }"
        @update:model-value="menuToggle = false"
      />
    </v-card>
  </v-overlay>
</template>

<script>
export default {
  inheritAttrs: false,
  props: {
    modelValue: { type: String, default: '' },
    datePickerAttrs: { type: Object, default: () => ({}) },
    menuAttrs: { type: Object, default: () => ({}) },
    minToday: Boolean,
    maxToday: Boolean,
  },
  data: () => ({ menuToggle: false }),
  computed: {
    model: {
      get() {
        return this.modelValue
      },
      set(value) {
        this.$emit('update:modelValue', value)
      },
    },
  },
  methods: {
    focusHandler(e) {
      setTimeout(() => {
        if (!this.menuToggle && !!e) {
          this.menuToggle = true
        }
      }, 100)
    },
  },
}
</script>

<style></style>
