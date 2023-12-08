<template>
  <v-menu
    v-model="menu"
    :offset-y="true"
    z-index="100"
    nudge-bottom="25px"
    tile
    max-height="500px"
    :close-on-content-click="false"
  >
    <template #activator="{ on, attrs }">
      <v-btn color="primary" dark class="me-4" icon v-bind="attrs" v-on="on">
        <v-badge
          :value="unreadCount"
          color="red darken-1"
          offset-x="6px"
          offset-y="6px"
          :content="unreadCount"
          left
        >
          <v-icon v-text="'mdi-bell'" />
        </v-badge>
      </v-btn>
    </template>

    <bp-ui-notifications-list @notification-click="menu = false" />

    <div
      v-if="ormModel('notifications').getState('pagination').totalPages > 1"
      class="py-0 background pos-sticky white w-100 d-flex justify-center"
      style="bottom: 0"
    >
      <bp-model-pagination
        hide-per-page
        :model="ormModel('notifications')"
        hide-total-text
      />
    </div>
  </v-menu>
</template>

<script>
export default {
  data() {
    return {
      menu: false,
      notificationsInterval: null,
    }
  },
  fetch() {
    this.$auth.loggedIn && this.ormModel('notifications').bpApi().fetchUnread()
  },
  computed: {
    unreadCount() {
      return this.ormModel('notifications').getState('unreadCount')
    },
  },
  watch: {
    unreadCount(count, old) {
      count && count !== old && this.fetchNotifications()
    },
  },
  mounted() {
    this.startInterval()
  },
  beforeDestroy() {
    clearInterval(this.notificationsInterval)
  },
  methods: {
    fetchNotifications() {
      this.$auth.loggedIn && this.ormModel('notifications').fetch()
    },
    startInterval() {
      this.notificationsInterval = setInterval(() => {
        this.$fetch()
      }, 15000)
    },
  },
}
</script>

<style></style>
