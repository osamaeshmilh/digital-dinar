<script setup>
const props = defineProps({
  timeout: { type: Number, default: 4000 },
  multiple: { type: Boolean, default: true },
  bannerAttrs: { type: Object, default: () => ({}) },
})
const { $store, $notifications } = useNuxtApp()

const shownItems = computed(() =>
  props.multiple
    ? $notifications.all()
    : $notifications.all().length
    ? [$notifications.all().slice(-1)[0]]
    : []
)

const unsubscribe = $store.subscribe((mutation) => {
  if (mutation.type === 'notifications/ADD_NOTIFICATION') {
    setTimeout(() => $notifications.remove(mutation.payload.id), props.timeout)

    if (!props.multiple)
      $notifications
        .all()
        .forEach(
          (banner) =>
            banner.id !== mutation.payload.id &&
            $notifications.remove(banner.id)
        )
  }
})
onUnmounted(() => unsubscribe())
</script>

<template>
  <v-overlay
    class="bp-notifications"
    v-bind="{
      persistent: true,
      noClickAnimation: true,
      scrollStrategy: 'none',
      scrim: false,
      contentClass: 'right-10 top-10',
      location: 'bottom',
      locationStrategy: 'connected',
      modelValue: !!shownItems.length,
      ...$attrs,
    }"
  >
    <v-slide-y-transition
      :group="multiple && shownItems.length > 1"
      leave-absolute
      tag="div"
    >
      <BpBaseBanner
        v-for="item in shownItems"
        :key="item.id"
        v-bind="{
          ...item,
          minWidth: '400px',
          maxWidth: '400px',
          border: '0',
          class: 'elevation-2 mb-1',
          onClose: () => $notifications.remove(item.id),
          ...bannerAttrs,
        }"
      />
    </v-slide-y-transition>
  </v-overlay>
</template>

<!-- TODO -->
<!-- <template>
  <v-snackbar
    v-model="notification"
    v-bind="{
      timeout: notification.timeout || timeout,
      color: notificationText.type,
      ...$attrs,
    }"
  >
    <slot v-bind="{ notification: notificationText }">
      <v-alert
        v-bind="{
          text: notificationText.message,
          type: notificationText.type,
          class: 'pa-0',
          ...notificationText.attrs,
        }"
      />
    </slot>
    <template #actions>
      <slot name="close-icon" v-bind="{ notification: notificationText }">
        <v-icon
          :icon="closeIconClass"
          v-bind="closeIconAttrs"
          @click="notification = false"
        />
      </slot>
    </template>
  </v-snackbar>
</template>

<script>
import { mapGetters, mapState, mapActions } from 'vuex'
export default {
  props: {
    alertAttrs: {
      type: Object,
      default: () => ({}),
    },
    timeout: {
      type: Number,
      default: 3000,
    },
    closeIconClass: {
      type: String,
      default: 'mdi-close',
    },
    closeIconAttrs: {
      type: Object,
      default: () => ({}),
    },
  },
  computed: {
    notification: {
      set(value) {
        this.setNotification(value)
      },
      get() {
        return this.$store.state.notifications.notification
      },
    },
    ...mapState('notifications', ['notificationText', 'notificationQueue']),
    ...mapGetters('notifications', ['hasNotificationsPending']),
  },
  watch: {
    notification() {
      if (!this.notification && this.hasNotificationsPending) {
        this.setNotificationText(this.notificationQueue[0])
        this.shiftNotificationQueue()
        this.$nextTick(() => this.setNotification(true))
      }
    },
  },
  methods: {
    ...mapActions('notifications', [
      'shiftNotificationQueue',
      'setNotification',
      'setNotificationText',
    ]),
  },
}
</script>

<style></style> -->
