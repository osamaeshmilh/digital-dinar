<script>
export default {
  props: {
    tooltipColor: { type: String, default: 'grey' },
    tooltipTransition: { type: String, default: 'fade-transition' },
    btnColor: { type: String, default: 'grey lighten-5' },
    icon: { type: String, default: 'mdi-help' },
    btnAttrs: { type: Object, default: () => ({}) },
    iconAttrs: { type: Object, default: () => ({}) },
  },
}
</script>

<template>
  <v-tooltip
    class="bp-ui-tooltip-help"
    v-bind="{ color: tooltipColor, transition: tooltipTransition, ...$attrs }"
  >
    <template #activator="{ props }">
      <v-btn
        v-bind="{
          class: `${btnColor} me-2`,
          icon: true,
          ripple: false,
          variant: 'plain',
          ...props,
          ...btnAttrs,
        }"
      >
        <v-icon v-bind="iconAttrs" :icon="icon" />
      </v-btn>
    </template>
    <slot />
  </v-tooltip>
</template>

<style></style>
