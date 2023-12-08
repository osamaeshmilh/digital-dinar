<template>
  <v-footer class="bp-layout-footer" v-bind="$attrs">
    <slot v-bind="{ year }">
      <p :class="textClass" v-text="$t('footer.copyrights') + ' © ' + year" />
    </slot>
  </v-footer>
</template>

<script>
export default {
  props: {
    textClass: { type: String, default: 'text-center w-100 mb-0 py-2 fs-0_9' },
  },
  data: () => ({ year: new Date().getFullYear() }),
}
</script>

<style></style>
