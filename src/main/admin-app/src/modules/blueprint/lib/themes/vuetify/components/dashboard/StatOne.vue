<script setup>
defineProps({
  icon: { type: String, default: '' },
  iconColor: { type: String, default: '' },
  iconAttrs: {
    type: Object,
    default: () => ({ variant: 'tonal', size: 'large' }),
  },
  titleClass: { type: String, default: '' },
  title: { type: String, default: '' },
  valueClass: { type: String, default: '' },
  value: { type: String, default: '' },
})
</script>

<template>
  <v-card class="bp-dashboard-stat-one elevation-default py-4 px-2">
    <template #prepend>
      <v-avatar
        v-if="icon"
        :icon="icon"
        :color="iconColor"
        v-bind="iconAttrs"
      />
    </template>
    <template #title>
      <v-card-subtitle
        v-if="title"
        class="pb-0"
        :class="titleClass"
        v-text="title"
      />
      <v-card-title v-if="value" :class="valueClass" v-text="value" />
    </template>

    <template v-for="(_, name) in $slots" #[name]="slotProps">
      <slot v-if="slotProps" :name="name" v-bind="slotProps" />
      <slot v-else :name="name" />
    </template>
  </v-card>
</template>
