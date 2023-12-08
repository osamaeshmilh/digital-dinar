import _isEqual from 'lodash/isEqual'

export default function ({ checkLogin = false } = {}) {
  const populatedModel = ref('')
  const currentModel = ref('')
  const confirmLeaveDialog = ref(false)
  const confirmCheck = ref(true)
  const confirmLeaveActions = ref({})

  const watchChanges = (model) => {
    currentModel.value = model.value
    populatedModel.value = model.value
    watch(
      () => model.value,
      (value) => (currentModel.value = value),
      { deep: true }
    )
  }

  const confirmLeavePromise = () => {
    return new Promise((resolve, reject) => {
      confirmLeaveActions.value = reactive({ resolve, reject })
      confirmLeaveDialog.value = true
    })
  }
  onBeforeRouteLeave(() => {
    const { $auth } = useNuxtApp()

    if ((checkLogin && !$auth.loggedIn.value) || !confirmCheck.value) {
      return
    }

    if (!_isEqual(currentModel.value, populatedModel.value)) {
      return confirmLeavePromise().then((shouldLeave) => shouldLeave)
    }
  })

  return {
    watchChanges,
    populatedModel,
    currentModel,
    confirmLeaveDialog,
    confirmCheck,
    confirmLeaveActions,
  }
}
