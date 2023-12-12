<template>
  <BpModelIndexPage entity="voucher-types" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [
    { title: 'navigation.vouchers' },
    { title: 'navigation.voucherTypes' },
  ],
})
</script>

<style></style>
