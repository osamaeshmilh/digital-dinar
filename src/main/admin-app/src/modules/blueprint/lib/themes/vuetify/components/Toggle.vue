<template>
  <component :is="transition" v-intersect="onIntersectHandle" v-bind="$attrs">
    <div v-show="show" class="bp-toggle">
      <slot />
    </div>
  </component>
</template>

<script>
import {
  VFadeTransition,
  VScaleTransition,
  VScrollXTransition,
  VScrollXReverseTransition,
  VScrollYTransition,
  VScrollYReverseTransition,
  VSlideXTransition,
  VSlideXReverseTransition,
  VSlideYTransition,
  VSlideYReverseTransition,
  VExpandTransition,
  VExpandXTransition,
} from 'vuetify/components'

export default {
  components: {
    VFadeTransition,
    VScaleTransition,
    VScrollXTransition,
    VScrollXReverseTransition,
    VScrollYTransition,
    VScrollYReverseTransition,
    VSlideXTransition,
    VSlideXReverseTransition,
    VSlideYTransition,
    VSlideYReverseTransition,
    VExpandTransition,
    VExpandXTransition,
  },
  props: {
    transition: { type: String, default: 'v-scroll-y-transition' },
    toggle: { type: Boolean, default: true },
    onIntersect: Boolean,
  },
  data() {
    return {
      show: false,
    }
  },
  watch: {
    toggle(value) {
      this.show = value
    },
  },
  mounted() {
    this.show = this.toggle
  },
  methods: {
    onIntersectHandle() {
      // todo
    },
  },
}
</script>

<style></style>
