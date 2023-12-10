<template>
  <BpModelIndexPage entity="beneficiaries" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.beneficiaries' }],
})
</script>

<style></style>
