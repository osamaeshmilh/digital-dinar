<template>
  <BpModelIndexPage entity="wallet-users" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.wallet' }, { title: 'navigation.users' }],
})
</script>

<style></style>
