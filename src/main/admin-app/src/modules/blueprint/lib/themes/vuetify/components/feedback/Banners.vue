<script setup>
const props = defineProps({
  timeout: { type: Number, default: 4000 },
  multiple: { type: Boolean, default: false },
})
const { $store, $banners } = useNuxtApp()

const shownItems = computed(() =>
  props.multiple
    ? $banners.all()
    : $banners.all().length
    ? [$banners.all().slice(-1)[0]]
    : []
)

const unsubscribe = $store.subscribe((mutation) => {
  if (mutation.type === 'banners/ADD_BANNER') {
    setTimeout(() => $banners.remove(mutation.payload.id), props.timeout)

    if (!props.multiple)
      $banners
        .all()
        .forEach(
          (banner) =>
            banner.id !== mutation.payload.id && $banners.remove(banner.id)
        )
  }
})
onUnmounted(() => unsubscribe())
</script>

<template>
  <v-slide-y-transition
    :group="multiple && shownItems.length > 1"
    leave-absolute
    tag="div"
    class="bp-banners"
  >
    <BpBaseBanner
      v-for="item in shownItems"
      :key="item.id"
      v-bind="item"
      @close="$banners.remove(item.id)"
    />
  </v-slide-y-transition>
</template>

<script>
export default { inheritAttrs: false }
</script>
