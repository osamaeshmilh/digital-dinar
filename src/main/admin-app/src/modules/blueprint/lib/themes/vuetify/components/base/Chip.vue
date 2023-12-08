<script setup>
defineProps({
  title: { type: [String, Number], default: '' },
  tooltipText: { type: String, default: '' },
  iconClass: { type: String, default: '' },
  iconSize: { type: String, default: '' },
  iconPosition: { type: String, default: 'left' },
  iconColor: { type: String, default: '' },
  avatar: { type: String, default: '' },
  avatarAttrs: { type: Object, default: () => ({}) },
  avatarImgAttrs: { type: Object, default: () => ({}) },
  iconAttrs: { type: Object, default: () => ({}) },
  tooltipAttrs: { type: Object, default: () => ({}) },
})
</script>

<template>
  <v-chip class="bp-base-chip">
    <v-tooltip
      v-if="$slots.tooltip || tooltipText"
      v-bind="{ activator: 'parent', ...tooltipAttrs }"
    >
      <slot name="tooltip">{{ tooltipText }}</slot>
    </v-tooltip>
    <v-avatar v-if="avatar" v-bind="{ start: true, tile: true, avatarAttrs }">
      <v-img
        :src="avatar"
        :alt="title"
        v-bind="{ eager: true, contain: true, ...avatarImgAttrs }"
      />
    </v-avatar>
    <v-icon
      v-if="iconClass !== '' && iconPosition === 'left'"
      v-bind="{
        start: 'icon' in $attrs || 'fab' in $attrs ? false : true,
        size: iconSize,
        color: iconColor,
        ...iconAttrs,
      }"
      :icon="iconClass"
    />
    <slot>{{ title }}</slot>
    <v-icon
      v-if="iconClass !== '' && iconPosition === 'right'"
      v-bind="{
        end: 'icon' in $attrs || 'fab' in $attrs ? false : true,
        size: iconSize,
        color: iconColor,
        ...iconAttrs,
      }"
      :icon="iconClass"
    />
  </v-chip>
</template>

<style></style>
