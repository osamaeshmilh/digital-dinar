<template>
  <v-app>
    <v-container fluid fill-height>
      <v-row justify="center" class="mb-15">
        <v-col md="4" sm="12">
          <div class="d-flex justify-center mb-10">
            <bp-logo black max-width="150px" />
          </div>
          <slot />
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>
