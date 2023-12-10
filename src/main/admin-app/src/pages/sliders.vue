<template>
  <BpModelIndexPage entity="sliders" filter></BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.sliders' }],
})
</script>

<style></style>
