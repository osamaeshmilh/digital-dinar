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
  <v-banner class="bp-base-banner" v-bind="{ color, ...attrs, ...$attrs }">
    <BpBaseButton
      v-if="$attrs.onClose"
      v-bind="{
        class: 'pos-absolute right-10 top-10',
        variant: 'text',
        color: 'grey',
        density: 'comfortable',
        iconSize: '20px',
        icon: true,
        iconClass: 'mdi-close',
        onClick: () => $emit('close'),
        ...closeBtnAttrs,
      }"
    />
    <div
      v-if="title"
      :class="titleClass"
      class="bp-base-banner--title"
      v-text="title"
    />
    <slot>{{ text }}</slot>
    <template #prepend>
      <v-icon
        class="bp-base-banner--prepend-icon"
        v-bind="{
          color,
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
    </template>
  </v-banner>
</template>

<script>
export default { inheritAttrs: false }
</script>
