<template>
  <BpModelIndexPage entity="notifications" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.notifications' }],
})
</script>

<style></style>
