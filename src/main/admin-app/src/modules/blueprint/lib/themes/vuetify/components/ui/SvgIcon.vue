<template>
  <component
    :is="props.tag"
    ref="el"
    class="d-block"
    :style="{ width: props.size, height: props.size }"
    v-html="response"
  ></component>
</template>

<script setup>
const el = ref(null)

const props = defineProps({
  tag: { type: String, default: 'span' },
  iconName: { type: String, required: true },
  iconDirective: { type: String, default: 'assets/icons/' },
  baseURL: { type: String, default: '' },
  size: { type: String, default: '32px' },
})

const response = await fetch(
  (props.baseURL || useRuntimeConfig().app.baseURL) +
    props.iconDirective +
    (props.iconName + '.svg')
).then((r) => r.text())
</script>

<style></style>
