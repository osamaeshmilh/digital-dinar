<template>
  <v-breadcrumbs
    class="bp-breadcrumbs"
    v-bind="{ items: breadcrumbItems, ...$attrs }"
  >
    <template #divider>
      <v-icon
        v-bind="{
          icon: $t('icons.chevronEnd'),
          size: 'small',
          ...dividerIconAttrs,
        }"
      />
    </template>
    <template #title="{ item }">
      <slot v-bind="{ breadcrumbItems, item }">
        <div v-bind="{ class: 'd-flex align-center fs-0_8', ...itemAttrs }">
          <v-icon
            v-if="item.icon"
            :icon="item.icon"
            v-bind="{ color: 'primary', ...iconAttrs }"
          />
          {{
            item.rawText ||
            item.rawTitle ||
            (item.text ? $t(item.text) : '') ||
            (item.title ? $t(item.title) : '')
          }}
        </div>
      </slot>
    </template>
  </v-breadcrumbs>
</template>

<script setup>
defineProps({
  itemAttrs: { type: Object, default: () => ({}) },
  iconAttrs: { type: Object, default: () => ({}) },
  dividerIconAttrs: { type: Object, default: () => ({}) },
})

const { $breadcrumbs, $t } = useNuxtApp()
const breadcrumbItems = computed(() => {
  return [
    ...($breadcrumbs?.prefix ? [$breadcrumbs?.prefix] : []),
    ...($breadcrumbs?.route || []),
    ...($breadcrumbs?.page || []),
  ]
})
</script>

<style></style>
