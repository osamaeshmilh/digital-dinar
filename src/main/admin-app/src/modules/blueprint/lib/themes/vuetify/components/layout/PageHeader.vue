<script setup>
defineProps({
  titleClass: { type: String, default: '' },
  title: { type: String, default: '' },
  subtitle: { type: String, default: '' },
  subtitleClass: { type: String, default: '' },
  breadcrumb: { type: Boolean, default: true },
  breadcrumbAttrs: { type: Object, default: () => ({}) },
  hideTitle: Boolean,
})

const { $breadcrumbs, $t } = useNuxtApp()
const routeTitle = computed(() => {
  if ($breadcrumbs?.route && Array.isArray($breadcrumbs.route)) {
    const breadcrumb = [...$breadcrumbs.route, ...$breadcrumbs.page]
    if (!breadcrumb.length) return ''
    const { title, rawTitle, rawText, text } = breadcrumb[breadcrumb.length - 1]
    return (
      rawTitle || rawText || (text ? $t(text) : '') || (title ? $t(title) : '')
    )
  }
  return ''
})
</script>

<template>
  <div class="bp-layout-page-header" v-bind="{ class: 'mb-4', ...$attrs }">
    <v-row no-gutters>
      <v-col cols="12" :sm="'second-column' in $slots ? 6 : 12">
        <slot name="first-column">
          <v-card-title
            v-if="!hideTitle && (title || routeTitle)"
            :class="['pa-0', titleClass]"
            v-text="title || routeTitle"
          />
          <v-card-subtitle
            v-if="subtitle"
            :class="['pa-0', subtitleClass]"
            v-text="subtitle"
          />
          <BpBreadcrumb
            v-if="breadcrumb && $breadcrumbs"
            v-bind="{
              class: 'pa-0 ma-0 mt-1',
              ...breadcrumbAttrs,
            }"
          />
        </slot>
      </v-col>
      <v-col v-if="'second-column' in $slots" cols="12" sm="6">
        <div class="d-flex justify-sm-end align-items-center">
          <slot name="second-column" />
        </div>
      </v-col>
    </v-row>
    <slot name="append" />
  </div>
</template>
