<template>
  <NuxtLayout :name="$auth.loggedIn.value ? 'default' : 'auth-one'">
    <div>
      <v-container>
        <v-card
          class="d-flex flex-column justify-center align-center pa-10 mx-auto elevation-default"
          style="margin-top: 40px"
          flat
          max-width="600px"
        >
          <template v-if="[404, 503, 403].includes(error.statusCode)">
            <h1
              v-text="
                $t(
                  `errors.${error.statusCode}${
                    404 === error.statusCode ? '' : '.title'
                  }`
                )
              "
            />
            <p
              v-if="error.statusCode !== 404"
              class="text-center text-grey-darken-1"
              v-text="$t('errors.' + error.statusCode + '.description')"
            />
          </template>
          <h2 v-else class="text-center mt-4" v-text="$t('errors.other')" />
          <v-btn
            class="mt-4"
            color="primary"
            prepend-icon="mdi-home"
            rounded
            flat
            border
            href="/"
            :text="$t('navigation.home')"
          />
        </v-card>
      </v-container>
    </div>
  </NuxtLayout>
</template>

<script>
export default {
  props: {
    error: { type: Object },
  },
}
</script>
