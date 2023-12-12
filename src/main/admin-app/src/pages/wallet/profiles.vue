<template>
  <BpModelIndexPage entity="wallet-profiles" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [
    { title: 'navigation.wallet' },
    { title: 'navigation.walletProfiles' },
  ],
})
</script>

<style></style>
