<template>
  <BpModelIndexPage entity="voucher-companies" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [
    { title: 'navigation.vouchers' },
    { title: 'navigation.voucherCompanies' },
  ],
})
</script>

<style></style>
