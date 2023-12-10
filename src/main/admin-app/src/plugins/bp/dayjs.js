import 'dayjs/locale/ar-dz'
import dayjs from 'dayjs'
import customParseFormat from 'dayjs/plugin/customParseFormat'
import duration from 'dayjs/plugin/duration'
import relativeTime from 'dayjs/plugin/relativeTime'
import utc from 'dayjs/plugin/utc'
import timezone from 'dayjs/plugin/timezone'
import isBetween from 'dayjs/plugin/isBetween'
import isSameOrBefore from 'dayjs/plugin/isSameOrBefore'
import isSameOrAfter from 'dayjs/plugin/isSameOrAfter'

dayjs.extend(customParseFormat)
dayjs.extend(duration)
dayjs.extend(relativeTime)
dayjs.extend(utc)
dayjs.extend(timezone)
dayjs.extend(isBetween)
dayjs.extend(isSameOrBefore)
dayjs.extend(isSameOrAfter)

export default defineNuxtPlugin((nuxtApp) => {
  watch(
    () => nuxtApp.$bp.options.locales.current,
    (locale) => dayjs.locale(locale === 'ar' ? 'ar-dz' : locale),
    { immediate: true }
  )
  nuxtApp.provide('dayjs', dayjs)
})
