<script setup>
import { useDisplay } from 'vuetify'

const rawProps = defineProps({
  listAttrs: { type: Object, default: () => ({}) },
  itemAttrs: { type: Object, default: () => ({}) },
  logoAttrs: { type: Object, default: () => ({}) },
  miniLogoAttrs: { type: Object, default: () => ({}) },
})
const { mobile } = useDisplay()
const { $theme, $bp } = useNuxtApp()
const { items: navItems } = useNav()
const { drawer } = $bp.$theme
const props = { ...rawProps, ...(drawer.props || {}) }
const onDrawerInput = (e) => ($theme.miniDrawer = e)
</script>

<template>
  <v-navigation-drawer
    :class="{
      'bp-layout-nav-drawer': true,
      'bp-layout-nav-drawer--dark': drawer.dark,
    }"
    :model-value="mobile ? $theme.miniDrawer : true"
    v-bind="{
      rail: $theme.miniDrawer && !mobile,
      color: drawer.color,
      dark: drawer.dark,
      width: 260,
      railWidth: 70,
      ...$attrs,
      ...drawer.attrs,
    }"
    @update:model-value="onDrawerInput"
  >
    <slot name="logo">
      <nuxt-link to="/">
        <div class="px-2 py-4">
          <BpLogo
            v-if="!$theme.miniDrawer || mobile"
            v-bind="{
              class: 'me-auto',
              height: '40px',
              black: !drawer.dark,
              ...props.logoAttrs,
            }"
          />
          <BpLogo
            v-else
            v-bind="{
              class: 'me-auto',
              height: '40px',
              mini: true,
              black: !drawer.dark,
              ...props.miniLogoAttrs,
            }"
          />
        </div>
      </nuxt-link>
    </slot>

    <v-list
      class="bp-layout-nav-drawer--list py-0"
      v-bind="{
        nav: true,
        density: 'comfortable',
        color: 'secondary',
        ...props.listAttrs,
      }"
    >
      <template v-for="(item, i) in navItems">
        <slot name="nav-item" v-bind="{ item }">
          <slot :name="`${item.title}-item`" v-bind="{ item }">
            <BpLayoutNavDrawerItem
              :key="`navItem-${i}`"
              class="bp-nav-drawer--item text-white"
              v-bind="{ item, ...props.itemAttrs }"
            />
          </slot>
        </slot>
      </template>
    </v-list>
  </v-navigation-drawer>
</template>
