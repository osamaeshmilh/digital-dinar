<script setup>
const props = defineProps({
  string: { type: [String, Array], required: true },
  color: { type: String, default: 'primary' },
  timeoutDuration: { type: [String, Number], default: 2000 },
})

const data = reactive({
  copied: false,
  timeout: '',
})

const copy = () => {
  clearTimeout(data.timeout)
  data.copied = true
  data.timeout = setTimeout(() => (data.copied = false), props.timeoutDuration)
  return navigator.clipboard
    ? navigator.clipboard.writeText(props.string)
    : fallbackCopyText()
}

const fallbackCopyText = () => {
  const textArea = document.createElement('textarea')
  textArea.value = props.string
  textArea.style.top = '0'
  textArea.style.left = '0'
  textArea.style.position = 'fixed'
  document.body.appendChild(textArea)
  textArea.focus()
  textArea.select()
  document.execCommand('copy')
  document.body.removeChild(textArea)
}
</script>

<template>
  <BpBaseButton
    class="bp-ui-copy-button"
    v-bind="{
      color,
      variant: data.copied ? 'outlined' : 'flat',
      border: data.copied,
      title: data.copied ? $t('buttons.copied') : $t('buttons.copy'),
      iconClass: data.copied
        ? 'mdi-clipboard-check-outline'
        : 'mdi-content-copy',
      ...$attrs,
    }"
    @click="copy()"
  />
</template>
