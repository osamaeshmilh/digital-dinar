<template>
  <Line
    :options="chartOptions"
    :data="
      props.chartData || {
        labels: ['January', 'February', 'March'],
        datasets: [
          { label: 'Data One', data: [40, 20, 12], type: 'bar' },
          { label: 'Data Two', data: [80, 10, 42], type: 'line' },
        ],
      }
    "
  />
</template>

<script setup>
import { Line } from 'vue-chartjs'
const props = defineProps({
  /*
   * {
   *   labels: ['January', 'February', 'March'],
   *   datasets: [
   *     { label: 'Data One', data: [40, 20, 12], type: 'bar' },
   *     { label: 'Data Two', data: [80, 10, 42], type: 'line' },
   *   ],
   * }
   */
  chartData: { type: [Object, undefined], default: undefined },
})
const { $vuetify } = useNuxtApp()
const chartOptions = computed(() => ({
  responsive: true,
  plugins: {
    tooltip: { rtl: $vuetify.locale.isRtl.value },
    legend: { rtl: $vuetify.locale.isRtl.value },
  },
  scales: {
    x: {
      grid: { display: false },
      ticks: { padding: 20 },
      position: $vuetify.locale.isRtl.value ? 'left' : 'right',
    },
    y: {
      suggestedMin: 0,
      suggestedMax: 10,
    },
  },
}))
</script>
