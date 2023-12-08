<template>
  <v-app class="bg-authBackground">
    <v-main style="padding: 120px 0px">
      <BpLogo width="180px" class="mx-auto mb-4" />
      <slot />
      <div class="text-center">
        <BpLanguageSwitcher
          v-if="$bp.options.locales.multiLang"
          class="mx-4"
          variant="flat"
        />
        <BpLayoutFooter class="py-md-0" color="transparent" hide-logo />
      </div>
    </v-main>
  </v-app>
</template>
