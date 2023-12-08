<script setup>
import { useDisplay } from 'vuetify'
const { lgAndUp, mdAndDown } = useDisplay()
</script>

<template>
  <v-app>
    <BpLayoutNavDrawer v-if="$bp.$theme.navLayout === 'drawer' || mdAndDown" />
    <BpLayoutAppBar />
    <BpNotifications v-if="$bp.options.plugins.notifications" />
    <v-main>
      <BpLayoutNavBar v-if="$bp.$theme.navLayout === 'navbar' && lgAndUp" />
      <BpBanners v-if="$bp.options.plugins.banners" />
      <NuxtLoadingIndicator
        v-bind="{
          color: $vuetify.theme.current.colors.primary,
          throttle: 0,
          height: 4,
        }"
      />
      <slot />
    </v-main>
    <BpLayoutFooter />
  </v-app>
</template>
