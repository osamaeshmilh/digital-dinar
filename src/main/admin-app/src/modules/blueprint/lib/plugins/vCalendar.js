import 'v-calendar/dist/style.css'
import VCalendar from 'v-calendar'

export default defineNuxtPlugin((app) => {
  app.vueApp.use(VCalendar)
})
