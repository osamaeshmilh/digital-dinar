<script setup>
defineProps({
  color: { type: String, default: '' },
  icon: { type: String, default: '' },
  iconAttrs: { type: Object, default: () => ({}) },
  text: { type: String, default: '' },
  title: { type: String, default: '' },
  titleClass: { type: String, default: '' },
  attrs: { type: Object, default: () => ({}) },
  closeBtnAttrs: { type: Object, default: () => ({}) },
})
</script>

<template>
  <v-banner
    :class="`bp-base-banner bg-${color} text-white pos-fixed top-0 font-weight-bold mb-0 d-flex align-center`"
    style="top: 0 !important; min-height: 88px; z-index: 100000 !important"
    height="88px"
    v-bind="{ variant: 'flat', color, ...attrs, ...$attrs }"
  >
    <BpBaseButton
      v-if="$attrs.onClose"
      v-bind="{
        class: 'pos-absolute right-10 top-10',
        variant: 'tonal',
        color: 'white',
        density: 'compact',
        iconSize: '20px',
        icon: true,
        iconClass: 'mdi-close',
        onClick: () => $emit('close'),
        ...closeBtnAttrs,
      }"
    />
    <div
      v-if="attrs.title"
      :class="titleClass"
      class="bp-base-banner--title"
      v-text="attrs.title"
    />
    <slot
      ><p class="fs-1_3">{{ text }}</p></slot
    >
    <template #prepend>
      <v-avatar variant="tonal" class="me-2">
        <v-icon
          class="bp-base-banner--prepend-icon fs-1_2"
          v-bind="{
            color: 'white',
            icon:
              icon ||
              (color === 'success'
                ? 'mdi-check-circle'
                : color === 'error'
                ? 'mdi-alert'
                : ['info', 'warning'].includes(color)
                ? 'mdi-information'
                : ''),
            ...iconAttrs,
          }"
        />
      </v-avatar>
    </template>
  </v-banner>
</template>

<script>
export default { inheritAttrs: false }
</script>
