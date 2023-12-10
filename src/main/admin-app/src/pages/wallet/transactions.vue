<template>
  <BpModelIndexPage entity="wallet-transactions" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [
    { title: 'navigation.wallet' },
    { title: 'navigation.transactions' },
  ],
})
</script>

<style></style>
