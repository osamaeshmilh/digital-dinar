export default defineNuxtPlugin((app) => {
  const { loading: loadingOptions } = app.$bp.options
  if (process.client) {
    app.hook('app:created', () => {
      setTimeout(() => {
        const elements = window.document.getElementsByClassName('pre-load')
        while (elements.length > 0) {
          elements[0].parentNode.removeChild(elements[0])
        }
      }, loadingOptions?.timeout || 100)
    })
  }
})
