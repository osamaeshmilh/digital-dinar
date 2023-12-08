import { defineAsyncComponent } from 'vue'
import { ReCaptcha } from './instance'
export default defineNuxtPlugin((nuxtApp) => {
  const { recaptcha } = nuxtApp.$bp.options

  nuxtApp.vueApp.component(
    'RecaptchaV2',
    defineAsyncComponent(() => import('./RecaptchaV2.vue'))
  )

  nuxtApp.provide('recaptcha', new ReCaptcha(recaptcha))
})
