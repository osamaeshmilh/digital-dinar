<template>
  <BpModelIndexPage entity="bank-branches" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.banks' }, { title: 'navigation.branches' }],
})
</script>

<style></style>
