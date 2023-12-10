<template>
  <BpModelIndexPage entity="countries" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.countries' }],
})
</script>

<style></style>
