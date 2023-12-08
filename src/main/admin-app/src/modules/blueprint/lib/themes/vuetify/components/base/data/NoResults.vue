<script>
export default {
  props: {
    iconColor: { type: String, default: 'grey lighten-3' },
    icon: { type: String, default: 'mdi-alert' },
    iconAttrs: { type: Object, default: () => ({}) },
    title: { type: String, default: '' },
    titleClass: { type: String, default: 'text-medium-emphasis d-block' },
    subtitle: { type: String, default: '' },
    subtitleClass: { type: String, default: 'text-medium-emphasis' },
    tag: { type: String, default: 'span' },
  },
}
</script>

<template>
  <v-sheet
    class="bp-base-data-no-results"
    v-bind="{ class: 'pa-4 text-center', color: 'bg-transparent', ...$attrs }"
  >
    <slot name="icon">
      <v-icon
        v-bind="{
          class: 'mb-2',
          icon,
          color: iconColor,
          size: 'x-large',
          ...iconAttrs,
        }"
      />
    </slot>
    <component
      :is="tag"
      :class="titleClass"
      v-text="title || $t('labels.noResults')"
    />
    <p v-show="!!subtitle" :class="subtitleClass" v-text="subtitle" />
    <slot name="footer" />
  </v-sheet>
</template>
