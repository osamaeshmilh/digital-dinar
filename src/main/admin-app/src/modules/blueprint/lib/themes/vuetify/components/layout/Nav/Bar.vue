<script setup>
const rawProps = defineProps({
  containerAttrs: { type: Object, default: () => ({}) },
  itemAttrs: { type: Object, default: () => ({}) },
})
const { $bp } = useNuxtApp()
const props = { ...rawProps, ...($bp.$theme.navbar.props || {}) }
const { items: navItems } = useNav()
</script>

<template>
  <v-toolbar
    class="bp-layout-nav-bar"
    v-bind="{
      color: $bp.$theme.navbar.color,
      height: '60',
      ...$attrs,
      ...($bp.$theme?.navbar.attrs || {}),
    }"
  >
    <div
      v-bind="{
        class: 'v-container d-flex align-center flex-wrap',
        ...props.containerAttrs,
      }"
    >
      <template v-for="(item, i) in navItems">
        <slot name="nav-item" v-bind="{ item }">
          <slot :name="`${item.title}-item`" v-bind="{ item }">
            <BpLayoutNavBarItem
              :key="`navItem-${i}`"
              v-bind="{ item, ...(props.itemAttrs || {}) }"
            />
          </slot>
        </slot>
      </template>
    </div>
  </v-toolbar>
</template>
