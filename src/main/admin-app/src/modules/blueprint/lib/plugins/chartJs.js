import {
  Chart as ChartJS,
  BarController,
  LineController,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  Title,
  Tooltip,
  Legend,
  Filler,
} from 'chart.js'

ChartJS.register(
  BarController,
  LineController,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  Title,
  Tooltip,
  Legend,
  Filler
)

export default defineNuxtPlugin((nuxtApp) => {
  const { fonts } = nuxtApp.$bp.options
  if (typeof fonts.localeFonts === 'object') {
    watch(
      () => nuxtApp.$vuetify.locale.current.value,
      (locale) => (ChartJS.defaults.font.family = fonts.localeFonts[locale]),
      { immediate: true }
    )
  } else {
    ChartJS.defaults.font.family = fonts.localeFonts
  }
})
