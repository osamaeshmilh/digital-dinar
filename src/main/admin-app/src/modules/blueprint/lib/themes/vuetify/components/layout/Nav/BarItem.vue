<script setup>
defineProps({
  item: { type: Object, required: true },
  iconAttrs: { type: Object, default: () => ({}) },
  titleAttrs: { type: Object, default: () => ({}) },
  listAttrs: { type: Object, default: () => ({}) },
  menuAttrs: { type: Object, default: () => ({}) },
})
const { getItemChildren, routeMatchesItem } = useNav()
</script>

<template>
  <v-btn
    v-if="item.to || item.children"
    class="bp-layout-nav-bar-item"
    v-bind="{
      active: routeMatchesItem(item),
      rounded: 'md',
      height: '40px',
      class: 'align-self-center',
      to: item.to,
      exact: true,
      ...item.attrs,
      ...$attrs,
    }"
  >
    <v-icon
      v-if="item.icon"
      v-bind="{
        class: 'me-3',
        color: item.iconColor,
        ...item.iconAttrs,
        ...iconAttrs,
      }"
      :icon="item.icon"
    />
    <span
      v-bind="{ ...item.titleAttrs, ...titleAttrs }"
      v-text="$t(item.title)"
    />
    <template v-if="item.children && getItemChildren(item).length > 0">
      <v-icon class="ms-2" small icon="mdi-chevron-down" />
      <v-menu
        activator="parent"
        v-bind="{
          openDelay: 0,
          openOnClick: true,
          ...item.menuAttrs,
          ...menuAttrs,
        }"
      >
        <v-list
          v-bind="{ density: 'comfortable', ...item.listAttrs, ...listAttrs }"
        >
          <v-list-item
            v-for="(citem, i) in getItemChildren(item)"
            :key="`${item.title}-pages-${i}`"
            v-bind="{
              to: citem.to,
              exactPath: true,
              ...item.childAttrs,
              ...citem.attrs,
            }"
          >
            <template v-if="citem.icon" #prepend>
              <v-icon
                v-bind="{
                  color: citem.iconColor,
                  ...item.childIconAttrs,
                  ...citem.iconAttrs,
                }"
                :icon="citem.icon"
              />
            </template>

            <v-list-item-title
              v-bind="{ ...item.childTitleAttrs, ...citem.titleAttrs }"
              v-text="$t(citem.title)"
            />
          </v-list-item>
        </v-list>
      </v-menu>
    </template>
  </v-btn>
</template>
