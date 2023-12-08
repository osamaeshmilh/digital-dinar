export default defineNuxtPlugin(async (app) => {
  const items = await fetchRuntimeOptions('nav', [])
  if (Array.isArray(items) && items.length) {
    app.provide('navItems', items)
  }
})
