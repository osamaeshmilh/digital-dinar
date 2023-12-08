import { isRef } from 'vue'
import _merge from 'lodash/merge'
import _set from 'lodash/set'
import _get from 'lodash/get'
import _pick from 'lodash/pick'
import { options as bpOptions } from '#bp-options'

export function kebabize(str) {
  return str.replace(
    /[A-Z]+(?![a-z])|[A-Z]/g,
    ($, ofs) => (ofs ? '-' : '') + $.toLowerCase()
  )
}

export function camelize(s) {
  return s.replace(/-./g, (x) => x[1].toUpperCase())
}

export function $t(...args) {
  return useNuxtApp().$t?.(...args)
}

export function hasOrmModel(model) {
  const { $store } = useNuxtApp()
  return $store.hasModule(['entities', model])
}

export function ormModel(model) {
  const { $ormModel } = useNuxtApp()
  return $ormModel(model)
}

export function ormApi(model) {
  const { $ormModel } = useNuxtApp()
  return $ormModel(model).bpApi()
}

export function ormState(model, state) {
  const { $ormModel } = useNuxtApp()
  return $ormModel(model).getState(state)
}

export function $populate(object, key, value) {
  !key ? _merge(object, value) : _set(object, key, value)
}

export function handleValidation(response, errorBag) {
  if (response?.data) {
    const errors =
      response.data?.errors || response.data?.error?.fields || response.data
    if (errorBag.value || isRef(errorBag)) {
      _set(errorBag, 'value', errors)
    } else {
      for (const error in errors) {
        _set(errorBag, error, errors[error])
      }
    }
  }
  return errorBag
}

export function formatMoney(amount = 0, { currency = false } = {}) {
  const { $t } = useNuxtApp()
  const formattedAmount = amount.toLocaleString(undefined, {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  })
  return (
    formattedAmount +
    (currency ? ' ' + (currency === true ? $t('lyd') : $t(currency)) : '')
  )
}

export function formatFileSize(bytes, decimalPoint = 2) {
  if (bytes == 0) return '0 Bytes'
  const k = 1000,
    dm = decimalPoint,
    sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
    i = Math.floor(Math.log(bytes) / Math.log(k))
  return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i]
}

export function exportCSV(csvData, csvFileName = 'export') {
  const BOM = '\uFEFF'
  let csvContent = 'data:text/csv;charset=utf-8,' + BOM
  csvContent += [
    Object.keys(csvData[0]).join(','),
    ...csvData.map((item) => Object.values(item).join(',')),
  ]
    .join('\n')
    .replace(/(^\[)|(\]$)/gm, '')
  const data = encodeURI(csvContent)
  const link = document.createElement('a')
  link.setAttribute('href', data)
  link.setAttribute('download', `${csvFileName}.csv`)
  link.click()
}

export function downloadURI(uri, name) {
  const link = document.createElement('a')
  link.setAttribute('href', uri)
  link.setAttribute('download', name)
  link.click()
  document.body.removeChild(link)
}

export function fetchRuntimeOptions(path, options) {
  return bpOptions?.runtimeConfig
    ? fetch(`/_runtime/${path}.json`)
        .then((response) => response.json())
        .then((runtime) => _merge(options, runtime))
        .catch(() => options || {})
    : options || {}
}

export function pickNested(object, fields) {
  const shallowFields = fields.filter(
    (field) => typeof field === 'string' && !field.includes('.')
  )
  const deepFields = fields.filter(
    (field) => typeof field === 'string' && field.includes('.')
  )
  const initialValue = _pick(object, shallowFields)
  return deepFields.reduce((output, field) => {
    output[field] = _get(object, field)
    return output
  }, initialValue)
}

export function formatXml(xml, tab) {
  // tab = optional indent value, default is tab (\t)
  var formatted = '',
    indent = ''
  tab = tab || '\t'
  xml.split(/>\s*</).forEach(function (node) {
    if (node.match(/^\/\w/)) indent = indent.substring(tab.length) // decrease indent by one 'tab'
    formatted += indent + '<' + node + '>\r\n'
    if (node.match(/^<?\w[^>]*[^\/]$/)) indent += tab // increase indent
  })
  return formatted.substring(1, formatted.length - 3)
}

export function hexToRgb(hex) {
  const result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex)
  const rgb = result
    ? {
        r: parseInt(result[1], 16),
        g: parseInt(result[2], 16),
        b: parseInt(result[3], 16),
      }
    : null
  return rgb ? `${rgb.r},${rgb.g},${rgb.b}` : ''
}
