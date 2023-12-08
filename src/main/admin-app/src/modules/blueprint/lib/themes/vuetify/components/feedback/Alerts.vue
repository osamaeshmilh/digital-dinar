<script>
export default {
  inheritAttrs: false,
  props: {
    alertKey: { type: [Array, String], default: 'global' },
    alertAttrs: { type: Object, default: () => ({}) },
    multiple: Boolean,
    closable: Boolean,
    deleteSingle: { type: Boolean, default: true },
  },
  computed: {
    stateAlerts() {
      return this.$store.state.alerts.alerts
    },
    alerts() {
      const alerts = this.stateAlerts.filter(this.filterAlerts)
      return this.multiple
        ? alerts
        : alerts.length
        ? [alerts[alerts.length - 1]]
        : []
    },
  },
  methods: {
    filterAlerts(alert) {
      return Array.isArray(this.alertKey)
        ? this.alertKey.includes(alert.key)
        : this.alertKey === alert.key
    },
    remove(index) {
      if (this.deleteSingle) {
        return this.$alerts.remove(index)
      }
      this.$alerts.clear()
    },
  },
}
</script>
<!-- eslint-disable vue/no-multiple-template-root -->
<template>
  <v-slide-y-transition
    v-if="Array.isArray(alerts) && alerts.length"
    :group="multiple && alerts.length > 1"
    tag="div"
    class="bp-alerts"
  >
    <BpBaseAlert
      v-for="(alert, i) in alerts"
      :key="`alert-${i}`"
      v-bind="{
        type: alert.type,
        text: alert.text,
        closable,
        ...(alert.attrs || {}),
        ...alertAttrs,
      }"
      @update:model-value="remove(alert.id)"
    >
      <slot v-bind="{ alert, alerts }" />
    </BpBaseAlert>
  </v-slide-y-transition>
</template>
