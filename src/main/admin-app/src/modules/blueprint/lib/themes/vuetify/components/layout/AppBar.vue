<script setup>
import { useDisplay } from 'vuetify'
const rawProps = defineProps({
  containerAttrs: { type: Object, default: () => ({}) },
  profileDropdownAttrs: { type: Object, default: () => ({}) },
  drawerIconAttrs: { type: Object, default: () => ({ color: 'grey' }) },
  multiLangSwitcherAttrs: { type: Object, default: () => ({}) },
  logoAttrs: { type: Object, default: () => ({}) },
  notificationsDropdownAttrs: { type: Object, default: () => ({}) },
  hideDrawerIcon: Boolean,
  hideLogo: Boolean,
})
const { mdAndDown } = useDisplay()
const { $bp, $auth, $store } = useNuxtApp()
const loggedIn = computed(() => $auth?.loggedIn)
const showNotifications = $store?.hasModule('entities/notifications')

const options = $bp.$theme.appbar
const props = { ...rawProps, ...(options.props || {}) }
</script>

<template>
  <v-app-bar
    class="bp-layout-app-bar"
    v-bind="{
      flat: true,
      color: options.color,
      absolute: true,
      order: mdAndDown ? 0 : '1',
      ...$attrs,
      ...options.attrs,
    }"
  >
    <v-container
      v-bind="{
        fluid: $bp.$theme.navLayout === 'drawer',
        ...props.containerAttrs,
      }"
    >
      <div class="d-flex align-center" style="gap: 10px">
        <v-app-bar-nav-icon
          v-if="
            !props.hideDrawerIcon &&
            ($bp.$theme.navLayout === 'drawer' || mdAndDown)
          "
          v-bind="props.drawerIconAttrs"
          @click="$theme.miniDrawer = !$theme.miniDrawer"
        />

        <slot name="logo">
          <nuxt-link
            v-if="!props.hideLogo && $bp.$theme.navLayout === 'navbar'"
            to="/"
          >
            <BpLogo
              v-bind="{
                width: '140px',
                black: !options.dark,
                ...props.logoAttrs,
              }"
            />
          </nuxt-link>
        </slot>
        <v-spacer />
        <BpLanguageSwitcher
          v-if="$bp.options.locales?.multiLang"
          v-bind="props.multiLangSwitcherAttrs"
        />
        <BpUiNotificationsDropdown
          v-if="showNotifications"
          v-bind="props.notificationsDropdownAttrs"
        />
        <BpLayoutProfileDropdown
          v-if="loggedIn"
          v-bind="props.profileDropdownAttrs"
          @logout="ormApi('auth').logout()"
        />
      </div>
    </v-container>
  </v-app-bar>
</template>
