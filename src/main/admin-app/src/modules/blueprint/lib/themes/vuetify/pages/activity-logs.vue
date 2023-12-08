<script setup>
definePageMeta({
  middleware: 'auth',
  // permissions: ['activity_logs-view'],
  breadcrumb: [{ text: 'navigation.activityLogs' }],
})
</script>

<template>
  <BpModelIndexPage entity="activity-logs" filter>
    <template #[`item.log_name`]="{ item }">
      <v-chip class="text-uppercase" size="small" variant="outlined">{{
        item.log_name
      }}</v-chip>
    </template>
    <template #[`item.subject`]="{ item }">
      <v-chip v-if="item.subject.type">
        {{ item.subject.type + ' #' + item.subject.id }}</v-chip
      >
      <v-chip v-if="item.user?.id" class="ms-2"
        >{{ item.user.name }} ({{ item.user.type }})</v-chip
      >
    </template>
  </BpModelIndexPage>
</template>

<style></style>
