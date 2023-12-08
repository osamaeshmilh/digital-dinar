<script setup>
const props = defineProps({
  tabAttrs: { type: Object, default: () => ({}) },
  iconAttrs: { type: Object, default: () => ({}) },
  navItems: { type: [Boolean, Array], default: false },
})

const theNavItems = props.navItems || [
  {
    to: '/account/profile',
    icon: 'mdi-account',
    title: $t('pages.profile.title'),
  },
  {
    to: '/account/change-password',
    icon: 'mdi-lock-reset',
    title: $t('pages.change-password.title'),
  },
  {
    to: '/account/tfa',
    icon: 'mdi-two-factor-authentication',
    title: $t('pages.tfa.title'),
  },
]
</script>

<template>
  <v-tabs class="bp-layout-account-nav" v-bind="{ height: '60px', ...$attrs }">
    <v-tab
      v-for="item in theNavItems"
      :key="item.to + '-item'"
      v-bind="{ to: item.to, ...tabAttrs }"
    >
      <v-icon
        v-bind="{ start: true, size: 'small', ...iconAttrs }"
        :icon="item.icon"
      />
      {{ item.title }}
    </v-tab>
  </v-tabs>
</template>
