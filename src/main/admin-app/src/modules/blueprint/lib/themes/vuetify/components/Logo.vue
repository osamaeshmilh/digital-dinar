<script setup>
const props = defineProps({
  black: { type: Boolean, default: false },
  white: { type: Boolean, default: false },
  mini: Boolean,
})
const { $bp } = useNuxtApp()

const normalSrc = computed(() => {
  return typeof $bp.$theme.logo === 'string'
    ? $bp.$theme.logo
    : props.black
    ? $bp.$theme.logo.src
    : $bp.$theme.logo.darkSrc || $bp.$theme.logo.src
})
const miniSrc = computed(() => {
  return typeof $bp.$theme.miniLogo === 'string'
    ? $bp.$theme.miniLogo
    : props.black
    ? $bp.$theme.miniLogo?.src
    : $bp.$theme.miniLogo?.darkSrc || $bp.$theme.miniLogo?.src
})
</script>

<template>
  <v-img
    v-if="mini && miniSrc"
    class="bp-logo"
    v-bind="{
      src: miniSrc,
      contain: true,
      eager: true,
      alt: 'logo',
      maxWidth: $attrs['max-width'] || '100px',
      ...$attrs,
    }"
  />
  <v-img
    v-else
    class="bp-logo"
    v-bind="{
      src: normalSrc,
      alt: 'logo',
      maxWidth: $attrs['max-width'] || '200px',
      ...$attrs,
    }"
  />
</template>
