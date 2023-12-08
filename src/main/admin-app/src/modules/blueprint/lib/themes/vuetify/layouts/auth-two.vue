<template>
  <v-app>
    <v-container class="pa-0" fluid fill-height>
      <v-row style="height: 100%; min-height: 600px">
        <v-col
          cols="12"
          lg="7"
          class="justify-center primary d-none d-md-flex align-center"
        >
          <v-container>
            <div class="pa-10">
              <v-row justify="center">
                <v-col xl="7" cols="8">
                  <div>
                    <h1 class="white--text font-weight-bold">
                      {{ $t('identity.title') }}
                    </h1>
                    <h6 class="mt-4 subtitle-1 white--text font-weight-regular">
                      {{ $t('identity.subtitle') }}
                    </h6>
                    <bp-base-button
                      outlined
                      x-large
                      tile
                      class="mt-8 text-capitalize white--text"
                      :title="$t('identity.dashboard')"
                      nuxt
                      to="/"
                    />
                  </div>
                </v-col>
              </v-row>
            </div>
          </v-container>
        </v-col>
        <v-col lg="5" cols="12" class="d-flex align-center">
          <v-container class="pa-7">
            <div class="mb-15">
              <bp-logo black max-width="300px" />
            </div>
            <slot />
            <div
              v-if="$bp.options.locales.multiLang"
              class="justify-center mt-5 d-flex align-center"
            >
              <v-divider />
              <bp-language-switcher class="mx-4" />
              <v-divider />
            </div>
          </v-container>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>
