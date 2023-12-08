<script setup>
defineProps({
  modelValue: { type: [Boolean, Number, String], default: false },
  trueValue: { type: [Boolean, Number, String], default: true },
  falseValue: { type: [Boolean, Number, String], default: false },
  trueIcon: { type: [String, Boolean], default: 'mdi-check-circle' },
  falseIcon: { type: [String, Boolean], default: 'mdi-close' },
  trueText: { type: [String], default: '' },
  falseText: { type: [String], default: '' },
  trueTooltip: { type: [String], default: '' },
  falseTooltip: { type: [String], default: '' },
  trueValue: { type: [String, Number, Boolean], default: true },
  falseValue: { type: [String, Number, Boolean], default: false },
  chipAttrs: { type: Object, default: () => ({ variant: 'text' }) },
  iconAttrs: { type: Object, default: () => ({}) },
  tooltipAttrs: { type: Object, default: () => ({}) },
  trueChipClass: { type: String, default: '' },
  trueIconColor: { type: String, default: 'success' },
  falseChipClass: { type: String, default: '' },
  falseIconColor: { type: String, default: 'grey lighten-1' },
  noIcons: Boolean,
})
</script>
<template>
  <v-tooltip
    v-if="modelValue == trueValue"
    :disabled="!trueTooltip"
    v-bind="{ top: true, ...tooltipAttrs }"
  >
    <template #activator="{ props }">
      <slot name="true" v-bind="{ props }">
        <v-icon
          v-if="trueIcon && !trueText"
          class="bp-boolean-chip bp-boolean-chip-true bp-boolean-chip--icon"
          v-bind="{ color: trueIconColor, ...iconAttrs, ...props }"
          :icon="trueIcon"
        />
        <v-chip
          v-else-if="trueText"
          class="bp-boolean-chip bp-boolean-chip-true bp-boolean-chip--chip no-opacity px-0"
          v-bind="{ label: true, class: trueChipClass, ...chipAttrs, ...props }"
        >
          <v-icon
            v-if="trueIcon && !noIcons"
            v-bind="{
              color: trueIconColor,
              size: 18,
              class: 'me-2',
              ...iconAttrs,
            }"
            :icon="trueIcon"
          />
          {{ trueText }}
        </v-chip>
      </slot>
    </template>
    {{ trueTooltip }}
  </v-tooltip>

  <v-tooltip
    v-else-if="modelValue == falseValue"
    :disabled="!falseTooltip"
    v-bind="{ top: true, ...tooltipAttrs }"
  >
    <template #activator="{ props }">
      <slot name="false" v-bind="{ props }">
        <v-icon
          v-if="falseIcon && !falseText"
          class="bp-boolean-chip bp-boolean-chip-false bp-boolean-chip--icon"
          v-bind="{ color: falseIconColor, ...iconAttrs, ...props }"
          :icon="falseIcon"
        />
        <v-chip
          v-else-if="falseText"
          class="bp-boolean-chip bp-boolean-chip-false bp-boolean-chip--chip no-opacity px-0"
          v-bind="{
            label: true,
            class: falseChipClass,
            ...chipAttrs,
            ...props,
          }"
        >
          <v-icon
            v-if="falseIcon && !noIcons"
            v-bind="{
              color: falseIconColor,
              size: 18,
              class: 'me-2',
              ...iconAttrs,
            }"
            :icon="falseIcon"
          />
          {{ falseText }}
        </v-chip>
      </slot>
    </template>
    {{ falseTooltip }}
  </v-tooltip>
</template>

<script>
export default { inheritAttrs: false }
</script>
