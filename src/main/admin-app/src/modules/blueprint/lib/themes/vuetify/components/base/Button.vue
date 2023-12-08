<script>
export default {
  props: {
    title: { type: [String, Number], default: '' },
    tooltipText: { type: String, default: '' },
    iconClass: { type: String, default: '' },
    iconColor: { type: String, default: '' },
    iconSize: { type: [String, Number], default: '' },
    iconAttrs: { type: Object, default: () => ({}) },
    iconPosition: { type: String, default: 'left' },
    tooltipAttrs: { type: Object, default: () => ({}) },
    menuAttrs: { type: Object, default: () => ({}) },
    menuItems: { type: [Boolean, Array], default: false },
    tableAction: Boolean,
  },
}
</script>

<template>
  <v-btn
    class="bp-base-button"
    v-bind="{
      ...(iconClass !== '' && iconPosition === 'center'
        ? {
            icon: iconClass,
          }
        : {}),
      ...(tableAction
        ? {
            minWidth: '34px',
            height: '34px',
            color: 'secondary',
            class: 'px-0',
            variant: 'text',
          }
        : {}),
      ...$attrs,
    }"
  >
    <slot>{{ title }}</slot>
    <v-tooltip
      v-if="$slots.tooltip || tooltipText"
      class="bp-base-button--tooltip"
      v-bind="{ activator: 'parent', ...tooltipAttrs }"
    >
      <slot name="tooltip">{{ tooltipText }}</slot>
    </v-tooltip>
    <v-menu
      v-if="$slots.menu || menuItems"
      class="bp-base-button--list-menu"
      v-bind="{ activator: 'parent', ...menuAttrs }"
    >
      <slot name="menu">
        <v-list class="bp-base-button--list">
          <v-list-item
            v-for="(item, index) in menuItems"
            :key="index"
            class="bp-base-button--list-item"
            v-bind="item.attrs"
          >
            <template v-if="item.icon" #prepend>
              <v-icon
                class="bp-base-button--list-item-icon"
                v-bind="item.iconAttrs"
                >{{ item.icon }}</v-icon
              >
            </template>
            <v-list-item-subtitle
              v-if="item.subtitle"
              v-bind="item.subtitleAttrs"
              class="bp-base-button--item-subtitle"
              v-text="item.subtitle"
            />
            <v-list-item-title
              v-if="item.title"
              v-bind="item.titleAttrs"
              class="bp-base-button--item-title"
              v-text="item.title"
            />
          </v-list-item>
        </v-list>
      </slot>
    </v-menu>

    <v-icon
      v-if="!title && iconClass"
      v-bind="{
        start: !('icon' in $attrs || 'fab' in $attrs),
        color: iconColor,
        ...(iconSize ? { size: iconSize } : {}),
        ...(tableAction ? { end: false, start: false, size: 22 } : {}),
        ...iconAttrs,
      }"
      :icon="iconClass"
    />
    <template
      v-if="title && iconClass !== '' && iconPosition === 'left'"
      #prepend
    >
      <v-icon
        v-bind="{
          color: iconColor,
          size: iconSize,
          ...(tableAction ? { end: false, start: false, size: 22 } : {}),
          ...iconAttrs,
        }"
        :icon="iconClass"
      />
    </template>
    <template
      v-if="title && iconClass !== '' && iconPosition === 'right'"
      #append
    >
      <v-icon
        v-bind="{
          color: iconColor,
          size: iconSize,
          ...(tableAction ? { end: false, start: false, size: 22 } : {}),
          ...iconAttrs,
        }"
        :icon="iconClass"
      />
    </template>

    <template v-for="(slot, index) of Object.keys($slots)" :key="index" #[slot]>
      <slot :name="slot"></slot>
    </template>
  </v-btn>
</template>
