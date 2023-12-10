<template>
  <BpModelIndexPage entity="banks" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.banks' }, { title: 'labels.all' }],
})
</script>

<style></style>
