<script setup>
defineProps({
  headerAttrs: { type: Object, default: () => ({}) },
  title: { type: String, default: '' },
  titleClass: { type: String, default: '' },
  subtitle: { type: String, default: '' },
  subtitleClass: { type: String, default: '' },
  headerIcon: { type: String, default: '' },
  headerIconPosition: { type: String, default: 'left' },
  headerIconAttrs: { type: Object, default: () => ({}) },
  headerRow: Boolean,
  headerRowAttrs: { type: Object, default: () => ({}) },
  headerColAttrs: { type: Object, default: () => ({}) },
  showDivider: Boolean,
  dividerAttrs: { type: Object, default: () => ({}) },
  bodyClass: { type: String, default: '' },
  noPadding: Boolean,
  bodyAttrs: { type: Object, default: () => ({}) },
  footerClass: { type: String, default: '' },
  overlayLoading: Boolean,
  overlayAttrs: { type: Object, default: () => ({ color: 'white' }) },
  overlayProgressAttrs: { type: Object, default: () => ({ color: 'primary' }) },
  closeBtn: Boolean,
  closeBtnAttrs: { type: Object, default: () => ({}) },
})
defineEmits(['close'])
</script>

<template>
  <v-card class="bp-base-card" v-bind="$attrs">
    <BpBaseButton
      v-if="closeBtn"
      v-bind="{
        class: 'pos-absolute right-5 top-5 bp-base-card--close-btn',
        variant: 'text',
        color: 'grey',
        density: 'comfortable',
        iconSize: '24px',
        icon: true,
        iconClass: 'mdi-close',
        ...closeBtnAttrs,
      }"
      @click="$emit('close')"
    />
    <slot name="card-header">
      <v-card-item
        v-if="!headerRow && (title || subtitle || headerIcon)"
        class="bp-base-card-header"
        v-bind="headerAttrs"
      >
        <template
          v-if="
            headerIconPosition === 'left' &&
            ($slots['header-icon'] || headerIcon)
          "
          #prepend
        >
          <slot name="header-icon">
            <v-icon
              v-if="headerIcon"
              class="bp-base-card--header bp-base-card--header-left"
              v-bind="headerIconAttrs"
              :icon="headerIcon"
            />
          </slot>
        </template>
        <slot name="title" v-bind="{ title, titleClass }">
          <v-card-title
            v-if="title"
            class="bp-base-card--title"
            :class="titleClass"
            v-text="title"
          />
        </slot>
        <slot name="subtitle" v-bind="{ subtitle, subtitleClass }">
          <v-card-subtitle
            v-if="subtitle"
            class="bp-base-card--subtitle"
            :class="subtitleClass"
            v-text="subtitle"
          />
        </slot>
        <template
          v-if="
            headerIconPosition === 'right' &&
            ($slots['header-icon'] || headerIcon)
          "
          #append
        >
          <slot name="header-icon">
            <v-icon
              v-if="headerIcon"
              class="bp-base-card--header bp-base-card--header-right"
              v-bind="headerIconAttrs"
              :icon="headerIcon"
            />
          </slot>
        </template>
      </v-card-item>
      <v-card-item
        v-if="headerRow"
        class="bp-base-card-header bp-base-card-header--has-row"
        v-bind="headerAttrs"
      >
        <v-row
          class="bp-base-card--header-row"
          v-bind="{ align: 'center', noGutters: true, ...headerRowAttrs }"
        >
          <v-col class="bp-base-card--header-col" v-bind="headerColAttrs">
            <slot name="header-first-column">
              <v-card-title v-if="title" :class="titleClass" v-text="title" />
              <v-card-subtitle
                v-if="subtitle"
                :class="subtitleClass"
                v-text="subtitle"
              />
              <slot name="header-first-column-append" />
            </slot>
          </v-col>
          <v-col
            v-if="'header-second-column' in $slots"
            class="bp-base-card--header-col"
            v-bind="headerColAttrs"
          >
            <slot name="header-second-column" />
          </v-col>
        </v-row>
      </v-card-item>
    </slot>

    <v-divider
      v-if="showDivider"
      class="bp-base-card--divider"
      v-bind="dividerAttrs"
    />

    <v-card-text
      v-show="$slots.default"
      class="bp-base-card--text"
      v-bind="{
        class: { [bodyClass]: true, 'pa-0': noPadding },
        ...bodyAttrs,
      }"
    >
      <slot />
    </v-card-text>

    <slot name="overlay">
      <v-overlay
        v-if="overlayLoading"
        class="bp-base-card--loading-overlay bp-loading-overlay"
        :model-value="true"
        v-bind="{
          contentClass: 'h-100 w-100 justify-center align-center d-flex',
          contained: true,
          ...overlayAttrs,
        }"
      >
        <slot name="overlay-progress">
          <v-progress-circular
            v-bind="{ indeterminate: true, ...overlayProgressAttrs }"
          />
        </slot>
      </v-overlay>
    </slot>

    <div
      v-if="$slots.footer"
      class="bp-base-card--footer d-flex align-center"
      :class="footerClass"
    >
      <slot name="footer"></slot>
    </div>
  </v-card>
</template>
