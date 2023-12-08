<script setup>
const props = defineProps({
  avatarAttrs: { type: Object, default: () => ({}) },
  btnAttrs: { type: Object, default: () => ({}) },
  iconAttrs: { type: Object, default: () => ({}) },
  cardAttrs: { type: Object, default: () => ({}) },
  listAttrs: { type: Object, default: () => ({}) },
  listItemAttrs: { type: Object, default: () => ({}) },
  listItems: { type: Array, default: null },
})
defineEmits(['logout'])

const { $auth } = useNuxtApp()
const theListItems = computed(
  () =>
    props.listItems || [
      {
        icon: 'mdi-account',
        to: '/account',
        text: $t('pages.profile.account'),
      },
    ]
)
const user = computed(() => $auth?.user)
</script>

<template>
  <div
    v-if="user.value"
    id="appbar-profile"
    class="bp-layout-profile-dropdown d-flex align-center cursor-pointer"
    style="gap: 10px"
  >
    <v-icon size="small" color="grey" icon="mdi-chevron-down" />
    <div class="d-none d-md-flex align-end flex-column">
      <span
        class="d-block font-weight-bold text-capitalize"
        v-text="user.value ? user.value.name : ''"
      />
      <span
        class="font-weight-light fs-0_8"
        v-text="user.value ? user.value.email : ''"
      />
    </div>
    <v-avatar
      v-bind="{
        color: 'primary',
        class: 'cursor-pointer',
        size: 'large',
        ...avatarAttrs,
      }"
    >
      <v-icon v-bind="iconAttrs" :icon="iconAttrs.icon || 'mdi-account'" />
    </v-avatar>
  </div>
  <v-menu v-if="user.value" activator="#appbar-profile" :open-delay="0">
    <v-card v-bind="cardAttrs">
      <v-list v-bind="{ density: 'compact', ...listAttrs }">
        <v-list-item
          v-for="item in theListItems"
          :key="item.to"
          :title="item.text"
          v-bind="{ to: item.to, ...listItemAttrs }"
        >
          <template #prepend>
            <v-icon size="small" :icon="item.icon" />
          </template>
        </v-list-item>
        <v-list-item @click="$emit('logout')">
          <template #prepend>
            <v-icon size="small" icon="mdi-logout" />
          </template>
          <v-list-item-title v-locale-text="'buttons.logout'" />
        </v-list-item>
      </v-list>
    </v-card>
  </v-menu>
</template>
