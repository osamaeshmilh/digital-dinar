import _get from 'lodash/get'

export default defineNuxtPlugin((app) => {
  const { guard: guardOptions } = app.$bp.options

  addRouteMiddleware(
    'guard',
    (to) => {
      const { $guard, $auth } = useNuxtApp()
      if ($auth.user.value && (to.meta.permissions || to.meta.roles)) {
        if (!$guard.canEnterRoute(to)) {
          return $guard.onUnauthorized()
        }
      }
    },
    {
      global: true,
    }
  )

  app.vueApp.directive('can', (el, binding, vnode) => {
    const { $guard } = useNuxtApp()
    const can =
      $guard.hasRole(binding.value) || $guard.hasPermission(binding.value)
    return !can ? el.remove() : ''
  })
  app.vueApp.directive('cannot', (el, binding, vnode) => {
    const { $guard } = useNuxtApp()
    const can =
      $guard.hasRole(binding.value) || $guard.hasPermission(binding.value)
    return can ? el.remove() : ''
  })

  app.provide('guard', {
    userRole: computed(() =>
      _get(app.$store.getters['auth/user'], guardOptions.props.role, '')
    ),
    userPermissions: computed(() =>
      _get(app.$store.getters['auth/user'], guardOptions.props.permissions, [])
    ),
    isSuperAdmin() {
      return Array.isArray(guardOptions.superAdminRole)
        ? guardOptions.superAdminRole.includes(this.userRole.value)
        : guardOptions.superAdminRole === this.userRole.value
    },
    getRouteRoles(route) {
      const currentRoute = app.$router.currentRoute.value
      return (
        (route
          ? app.$router.resolve(route).meta?.roles || undefined
          : currentRoute.meta?.roles) || undefined
      )
    },
    getRoutePermissions(route) {
      const currentRoute = app.$router.currentRoute.value
      return (
        (route
          ? app.$router.resolve(route).meta?.permissions || undefined
          : currentRoute.meta?.permissions) || undefined
      )
    },
    canEnterRoute(route) {
      const routePermissions = this.getRoutePermissions(route)
      const routeRoles = this.getRouteRoles(route)
      const bothUndefined =
        routePermissions === undefined && routeRoles === undefined
      return (
        bothUndefined ||
        (routePermissions === undefined
          ? false
          : this.hasPermission(routePermissions)) ||
        (routeRoles === undefined ? false : this.hasRole(routeRoles))
      )
    },
    hasRole(role) {
      if (Array.isArray(role)) {
        if (role.length === 0) {
          return true
        }
        return (
          this.hasRole(guardOptions.superAdminRole) ||
          role.some(
            (r) =>
              r === '*' ||
              (Array.isArray(this.userRole)
                ? this.userRole.value.includes(r)
                : this.userRole.value === r)
          )
        )
      }
      return (
        this.userRole.value === guardOptions.superAdminRole ||
        role === '*' ||
        (Array.isArray(this.userRole.value)
          ? this.userRole.value.includes(role)
          : this.userRole.value === role)
      )
    },
    hasPermission(permission) {
      if (Array.isArray(permission)) {
        if (permission.length === 0) {
          return true
        }
        return (
          this.hasRole(guardOptions.superAdminRole) ||
          permission.some(
            (p) => p === '*' || this.userPermissions.value.includes(p)
          )
        )
      }
      return (
        this.hasRole(guardOptions.superAdminRole) ||
        permission === '*' ||
        this.userPermissions.value.includes(permission)
      )
    },
    ...{
      onUnauthorized:
        guardOptions.onUnauthorized &&
        typeof guardOptions.onUnauthorized === 'function'
          ? guardOptions.onUnauthorized
          : () => {
              if (guardOptions?.onUnauthorized?.throwError) {
                throw createError({
                  fatal: true,
                  statusCode: 403,
                })
              } else {
                if (guardOptions?.onUnauthorized?.alertKey) {
                  app.$alerts.error(
                    app.$t('errors.unauthorizedRoute'),
                    guardOptions.onUnauthorized.alertKey || 'index-page'
                  )
                  app.$alerts.setPersistAlerts(true)
                }
                if (guardOptions?.onUnauthorized?.redirect) {
                  app.$router.replace(guardOptions.onUnauthorized.redirect)
                }
              }
            },
    },
  })
})

/*
* Appends route permissions to route meta
* use it in nuxt.config.js inside router : { extendRoutes } function or use @nuxtjs/router-extra
eg: routesPermissionMap
{
  users-id: 'list users',
  users: ['create users', 'update users', 'list users', 'delete users'],
  index: '*',
}
*/
export const routePermissionSerielizer = (routes, routesPermissionMap) => {
  return routes.map((route) => {
    return {
      ...route,
      meta: {
        ...route.meta,
        ...(route.name in routesPermissionMap
          ? { permissions: routesPermissionMap[route.name] }
          : {}),
      },
    }
  })
}
