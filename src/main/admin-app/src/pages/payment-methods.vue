<template>
  <BpModelIndexPage entity="payment-methods" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.paymentMethods' }],
})
</script>

<style></style>
