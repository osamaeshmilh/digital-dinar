<template>
  <BpModelIndexPage entity="consumers" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.consumers' }],
})
</script>

<style></style>
