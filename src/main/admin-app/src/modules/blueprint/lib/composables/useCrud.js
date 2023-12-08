export default ({ mutator, form } = { form: { fields: [] } }) => {
  let selected = ref(null)
  let editor = ref(false)
  let deletor = ref(false)
  let errors = reactive({})
  let dialogs = reactive({})

  if (!mutator && (form?.fields || []).length) {
    mutator = (item) => {
      const formFields = form.fields.filter((f) => f.getter || f.name)
      const getterName = (id) =>
        formFields.find((f) => f.getter === id || f.name === id).name
      const valueGetters = formFields.map((f) => f.getter || f.name)
      const values = Object.entries(pickNested(item, valueGetters)).reduce(
        (pv, [key, value]) => ({
          ...pv,
          [getterName(key)]: value,
        }),
        {}
      )

      return {
        ...(item.id ? { id: item.id } : {}),
        ...values,
      }
    }
  }

  watch(
    () => editor.value,
    (value) => {
      if (value) return
      setTimeout(() => {
        selected.value = null
        Object.keys(errors).forEach((key) => (errors[key] = ''))
      }, 300)
    }
  )

  return {
    editor,
    delete: deletor,
    selected,
    errors,
    dialogs,
    openEditor: (item = undefined, options = {}) => {
      options = typeof options === 'string' ? { target: options } : {}
      selected.value =
        mutator && item && options?.mutator !== false
          ? mutator(item)
          : item || {}
      if (!options.target) {
        editor.value = true
      } else {
        dialogs[options.target] = true
      }
    },
    openDelete: (item = undefined) => {
      selected.value = item
      deletor.value = true
    },
  }
}
