<script setup>
import { useDisplay } from 'vuetify'
defineProps({
  tooltipAttrs: { type: Object, default: () => ({}) },
  iconAttrs: { type: Object, default: () => ({}) },
  titleAttrs: { type: Object, default: () => ({}) },
  item: { type: Object, required: true },
  // color: { type: String, default: '' },
  child: Boolean,
})

const { mobile } = useDisplay()
const { $theme } = useNuxtApp()
const { getItemChildren, routeMatchesItem } = useNav()
const miniDrawer = computed(() => (mobile.value ? false : $theme.miniDrawer))
</script>

<template>
  <v-list-subheader
    v-if="!item.to && !item.children && !miniDrawer"
    class="bp-layout-nav-drawer-item"
    v-bind="{
      class:
        'text-uppercase ' + !!item.titleColor ? `text-${item.titleColor}` : '',
      ...item.attrs,
    }"
    v-text="$t(item.title)"
  />
  <v-list-item
    v-else-if="item.to"
    class="bp-layout-nav-drawer-item"
    v-bind="{ to: item.to, ...item.attrs, ...$attrs }"
  >
    <v-tooltip
      v-bind="{ activator: 'parent', disabled: !miniDrawer, ...tooltipAttrs }"
    >
      {{ $t(item.title) }}
    </v-tooltip>
    <template v-if="item.icon || miniDrawer" #prepend>
      <v-icon
        v-bind="{
          color: item.iconColor,
          icon: item.icon || 'mdi-minus',
          ...item.iconAttrs,
          ...iconAttrs,
        }"
      />
    </template>
    <v-list-item-title
      :class="{ 'd-none': miniDrawer }"
      v-bind="{
        class: !!item.titleColor ? `text-${item.titleColor}` : '',
        ...item.titleAttrs,
        ...titleAttrs,
      }"
      v-text="$t(item.title)"
    />
  </v-list-item>
  <v-list-group
    v-else-if="item.children && getItemChildren(item).length > 0"
    :model-value="routeMatchesItem(item)"
    :sub-group="child"
    class="bp-layout-nav-drawer-item bp-layout-nav-drawer-item--parent"
    v-bind="item.attrs"
  >
    <template #activator="{ props }">
      <v-list-item
        v-bind="{
          class: 'px-0',
          to: item.to,
          ...props,
          ...item.attrs,
          ...$attrs,
        }"
      >
        <v-tooltip
          v-bind="{
            activator: 'parent',
            disabled: !miniDrawer,
            ...tooltipAttrs,
          }"
        >
          {{ $t(item.title) }}
        </v-tooltip>
        <template v-if="item.icon" #prepend>
          <v-icon
            v-bind="{
              color: item.iconColor,
              icon: item.icon,
              ...item.iconAttrs,
              ...iconAttrs,
            }"
          />
        </template>
        <v-list-item-title
          :class="{ 'd-none': miniDrawer }"
          v-bind="{
            class: !!item.titleColor ? `text-${item.titleColor}` : '',
            ...item.titleAttrs,
            ...titleAttrs,
          }"
          v-text="$t(item.title)"
        />
      </v-list-item>
    </template>
    <BpLayoutNavDrawerItem
      v-for="citem in getItemChildren(item)"
      :key="`navItem-${citem.title}`"
      class="bp-layout-nav-drawer-item--child"
      v-bind="{
        item: citem,
        subGroup: true,
        child: true,
        ...$attrs,
      }"
    />
  </v-list-group>
</template>
