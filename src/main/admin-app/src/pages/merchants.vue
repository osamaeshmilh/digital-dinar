<template>
  <BpModelIndexPage entity="merchants" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.merchants' }],
})
</script>

<style></style>
