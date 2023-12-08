<template>
  <v-alert
    class="bp-base-alert"
    :model-value="true"
    v-bind="{ class: 'mb-3', variant: 'tonal', ...$attrs }"
  >
    <slot>{{ text }}</slot>
  </v-alert>
</template>

<script>
export default {
  props: {
    text: { type: String, required: true },
  },
}
</script>
