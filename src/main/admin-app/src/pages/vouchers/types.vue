<template>
  <BpModelIndexPage entity="voucher-types" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.vouchers' }, { title: 'navigation.types' }],
})
</script>

<style></style>
