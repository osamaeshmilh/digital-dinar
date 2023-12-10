<template>
  <BpModelIndexPage entity="users" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.users' }],
})
</script>

<style></style>
