import nav from '@/nav'

export default function () {
  const { $guard, $navItems } = useNuxtApp()
  const route = useRoute()

  const items = computed(() => ($navItems || nav).filter(itemGuardCheck))
  const getItemChildren = (item) => {
    const filteredChildren = item.children?.filter(itemGuardCheck) || []
    // if the only child route is also the parent route then hide the submenu
    if (filteredChildren.length === 1 && filteredChildren[0].to === item.to) {
      return []
    }
    return filteredChildren
  }
  const itemGuardCheck = (item) => {
    if (!$guard) return true

    if (item.to) {
      const canEnter = $guard.canEnterRoute({ path: item.to })
      if (canEnter) {
        return true
      } else {
        if (item.children && item.children.some(itemGuardCheck)) {
          delete item.to
          return true
        } else {
          return false
        }
      }
    } else {
      if (item.children) {
        return item.children.some(itemGuardCheck)
      } else {
        return true
      }
    }
  }
  // check if one of nav item's child items is active
  const routeMatchesItem = (item) => {
    return route.matched.some(({ path }) => {
      if (item.match) {
        if (typeof item.match === 'string') {
          return path.includes(item.match)
        } else if (Array.isArray(item.match)) {
          return item.match.some((match) => path.includes(match))
        }
      }

      if (item.to && !item.children) {
        return path === item.to
      }

      const childActive =
        (item.children || [])
          .map((child) => child.to)
          .some((match) => path.includes(match)) || false

      const nestedChildActive = (item.children || [])
        .map((child) => (child.children || []).map((nested) => nested.to))
        .flat()
        .some((match) => path.includes(match))

      return childActive || nestedChildActive
    })
  }

  return {
    routeMatchesItem,
    getItemChildren,
    itemGuardCheck,
    items,
  }
}
