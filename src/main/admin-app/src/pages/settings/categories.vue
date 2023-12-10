<template>
  <BpModelIndexPage entity="categories" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.categories' }],
})
</script>

<style></style>
