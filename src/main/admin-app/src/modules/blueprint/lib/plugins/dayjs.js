import dayjs from 'dayjs'
import customParseFormat from 'dayjs/plugin/customParseFormat'
import duration from 'dayjs/plugin/duration'
import relativeTime from 'dayjs/plugin/relativeTime'
import 'dayjs/locale/ar-dz'

dayjs.extend(customParseFormat)
dayjs.extend(duration)
dayjs.extend(relativeTime)

export default defineNuxtPlugin((nuxtApp) => {
  watch(
    () => nuxtApp.$bp.options.locales.current,
    (locale) => dayjs.locale(locale === 'ar' ? 'ar-dz' : locale),
    { immediate: true }
  )
  nuxtApp.provide('dayjs', dayjs)
})
