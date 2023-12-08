<template>
  <v-list
    class="pb-0"
    color="white"
    :width="hasNotifications ? '500px' : '400px'"
  >
    <div v-if="!hasNotifications" class="d-flex flex-column align-center py-5">
      <v-icon class="mb-1" right color="primary" v-text="'mdi-information'" />
      <h3 v-text="$t('labels.noNotifications')" />
    </div>
    <v-list-item v-if="hasNotifications" color="primary">
      <v-list-item-content>
        <v-list-item-title class="font-weight-bold">
          <span v-text="$t('navigation.notifications')" />
        </v-list-item-title>
      </v-list-item-content>
      <v-list-item-action-text>
        <a
          class="text-decoration-underline fs-0_8"
          @click="markAsRead"
          v-text="$t('labels.markAsRead')"
        />
      </v-list-item-action-text>
    </v-list-item>
    <v-list-item
      v-for="notification in notifications"
      :key="notification.id"
      color="primary"
      :ripple="false"
      :class="notification.is_read ? 'grey--text background' : 'black--text '"
      @click="onClick(notification)"
    >
      <v-list-item-avatar class="px-0 mx-0">
        <v-icon v-if="!notification.is_read" class="mb-5" size="50" color="red">
          mdi-circle-small
        </v-icon>
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-title
          class="two-line-text"
          v-text="notification.subject"
        />
        <v-list-item-subtitle
          v-text="$dayjs(notification.created_at).format('YYYY/M/D')"
        />
      </v-list-item-content>
      <v-list-item-action>
        <v-icon color="primary ">mdi-chevron-left</v-icon>
      </v-list-item-action>
    </v-list-item>
  </v-list>
</template>

<script>
export default {
  computed: {
    notifications() {
      return this.ormModel('notifications').all()
    },
    hasNotifications() {
      return this.notifications.length > 0
    },
    page() {
      return this.ormModel('notifications').getState('pagination.currentPage')
    },
  },
  watch: {
    page: 'fetchNotifications',
  },
  methods: {
    fetchNotifications() {
      this.ormModel('notifications').fetch()
    },
    fetchUnread() {
      this.ormModel('notifications').bpApi().fetchUnread()
    },
    onClick(notification) {
      this.ormModel('notifications')
        .bpApi()
        .markAsRead({
          data: { ids: [notification.id] },
        })
        .finally(() => {
          this.fetchUnread()
          this.fetchNotifications()
        })
      this.$emit('notification-click')
      this.$router.push(notification.link)
    },
    markAsRead() {
      this.ormModel('notifications')
        .bpApi()
        .markAsRead({
          data: { ids: this.notifications.map(({ id }) => id) },
        })
        .finally(() => {
          this.fetchUnread()
          this.fetchNotifications()
        })
    },
  },
}
</script>

<style></style>
