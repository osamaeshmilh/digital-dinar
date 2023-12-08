<script setup>
defineProps({
  icon: { type: String, default: '' },
  iconColor: { type: String, default: '' },
  iconAttrs: { type: Object, default: () => ({}) },
  titleClass: { type: String, default: 'text-grey-darken-2 fs-1' },
  title: { type: String, default: '' },
  valueClass: { type: String, default: 'fs-1_6' },
  value: { type: String, default: '' },
  to: { type: [String, Object], default: '' },
  loading: Boolean,
})
</script>

<template>
  <v-card
    density="compact"
    class="elevation-default pa-2"
    :loading="loading && value !== null"
    v-bind="{ ...(to ? { to } : {}) }"
  >
    <template v-if="icon" #append>
      <v-icon
        :icon="icon"
        v-bind="{ color: iconColor, size: 40, ...iconAttrs }"
      />
    </template>

    <template v-if="title" #title>
      <v-card-subtitle class="d-flex align-center" :title-class="titleClass">
        {{ title }}
        <v-icon
          v-if="to"
          color="grey"
          size="small"
          :icon="$t('icons.chevronEnd')"
        />
      </v-card-subtitle>
      <template v-if="loading && value === null">
        <v-skeleton-loader
          class="bg-transparent"
          width="80"
          height="24"
          type="text"
        />
      </template>
      <template v-else-if="value !== null">
        <v-card-title :class="valueClass">
          {{ value }}
        </v-card-title>
      </template>
    </template>
  </v-card>
</template>
