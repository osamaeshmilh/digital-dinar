<script setup>
defineProps({
  iconClass: { type: String, default: 'mdi-earth' },
  iconAttrs: { type: Object, default: () => ({}) },
})
const { $bp, $vuetify } = useNuxtApp()
const availableLanguages = computed(() =>
  $bp.options.locales.all.filter(
    (locale) => locale !== $vuetify.locale.current.value
  )
)
const switchLanguage = (locale) => ($bp.options.locales.current = locale)
</script>

<template>
  <v-btn
    v-if="Array.isArray(availableLanguages) && availableLanguages.length === 1"
    class="bp-language-switcher"
    v-bind="{ size: 'small', border: true, ...$attrs }"
    @click.stop="switchLanguage(availableLanguages[0])"
  >
    {{ $t('locales.' + availableLanguages[0]) }}
    <v-icon v-bind="{ end: true, ...iconAttrs }" :icon="iconClass" />
  </v-btn>
  <!-- TODO: Show list when multiple -->
</template>
