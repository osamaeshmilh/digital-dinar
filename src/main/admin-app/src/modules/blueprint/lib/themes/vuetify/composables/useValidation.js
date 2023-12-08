import _get from 'lodash/get'

export default function (overrideRules = {}) {
  const form = ref(null)
  const errors = reactive({})
  const trans = (string, ...args) => $t('validations.' + string, ...args)

  const rules = {
    required: (value) => {
      if (
        value === null ||
        value === undefined ||
        (Array.isArray(value) && value.length === 0) ||
        value === false ||
        !String(value).trim().length
      ) {
        return trans('required')
      }

      return true
    },
    requiredIf(value, otherValue, condition) {
      return otherValue === condition ? this.required(value) : false
    },
    email: (v) =>
      /^([a-zA-Z0-9\.\+\]+)@([a-zA-Z0-9\.\+\\-]+)(\.\w{2,5})+$/.test(v) ||
      trans('email'),
    number: (v) => /^\d+$/.test(v) || trans('number'),
    decimal: (v) => /^\d*\.?\d*$/.test(v) || trans('number'),
    nullable: (value, ruleIfNotNull) =>
      value !== '' ? this[ruleIfNotNull](value) : true,
    alpha: (v) =>
      /^[a-zA-Z\u0600-\u06FF\u0750-\u077F ]+$/.test(v) || trans('alpha'),
    minLength: (value, threshold) =>
      value.length >= threshold || trans('minLength', { threshold }),
    min: (value, threshold) =>
      value >= threshold || trans('min', { threshold }),
    maxLength: (value, threshold) =>
      value.length <= threshold || trans('maxLength', { threshold }),
    max: (value, threshold) =>
      value <= threshold || trans('max', { threshold }),
    maxSize: (files, threshold) =>
      !files ||
      !files.some((file) => file.size > threshold * 1024 * 1024) ||
      trans('maxSize', { threshold }),
    letters(v) {
      return /^[a-zA-Z]+$/.test(v) || trans('letters')
    },
    noSpace(v) {
      return /^\S*$/.test(v) || trans('noSpace')
    },
    ...overrideRules,
  }

  // only works with vuetify
  const validate = async (e) => {
    const { valid } = await e.target.__vueComponent.validate()
    return valid
  }

  const getError = (field, errorBag, { translate } = { translate: true }) => {
    const error = _get(errorBag, field, [])
    return !translate
      ? error
      : Array.isArray(error)
      ? error.map(
          (msg) => msg.replace?.(field, $t(`attributes.${field}`)) ?? msg
        )
      : typeof error === 'string'
      ? error.replace(field, $t(`attributes.${field}`))
      : error
  }

  return {
    rules,
    form,
    errors,
    validate,
    getError,
  }
}
