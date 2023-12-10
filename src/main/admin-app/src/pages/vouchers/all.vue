<template>
  <BpModelIndexPage entity="vouchers" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.vouchers' }, { title: 'labels.all' }],
})
</script>

<style></style>
