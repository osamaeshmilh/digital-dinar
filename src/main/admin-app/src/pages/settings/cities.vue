<template>
  <BpModelIndexPage entity="cities" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.cities' }],
})
</script>

<style></style>
