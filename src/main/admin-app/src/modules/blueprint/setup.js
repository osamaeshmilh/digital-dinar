import {
  addPlugin,
  addServerPlugin,
  addComponentsDir,
  addTemplate,
  extendPages,
  resolvePath,
  addLayout,
  extendViteConfig,
} from '@nuxt/kit'
import { resolve } from 'path'
import { existsSync, copyFileSync, readdirSync } from 'fs'
import _set from 'lodash/set'
import _merge from 'lodash/merge'

export const setAliases = (options, nuxt) => {
  const base = resolve(__dirname, `./lib`)
  nuxt.options.alias['@blueprint'] = base
  nuxt.options.alias['@bp'] = base
  nuxt.options.alias['@bp-models'] = `${base}/plugins/vuexOrm/models`
  nuxt.options.alias['@bp-theme'] = `${base}/themes/${options.theme.name}`

  // Inject options via virtual template
  const virtualOptions = [
    `export const options = ${JSON.stringify(options, (_, value) =>
      typeof value === 'function' ? 'FUNCTION=>' + value.toString() : value
    )}`,
    `export const srcDir = "${nuxt.options.srcDir}"`,
  ].join('\n')
  nuxt.options.alias['#bp-options'] = addTemplate({
    filename: 'bp-options.mjs',
    getContents: () => virtualOptions,
  }).dst

  nuxt.hook('nitro:config', async (config) => {
    config.virtual = config.virtual || {}
    config.virtual['#bp-options'] = virtualOptions
  })
}

export const setupProxy = (options, nuxt) => {
  extendViteConfig((config) => {
    config.server.proxy = {
      '/backend': {
        target: process.env.BP_PROXY_BASE_URL,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/backend/, '/'),
      },
    }
  })
}

export const singleEntryBuild = (_, nuxt) => {
  nuxt.hooks.hook('nitro:init', (nitro) => {
    nitro.hooks.hook('prerender:routes', (routes) => {
      routes.clear()
      routes.add('/')
    })
  })
}

export const loadAutoImports = async (_options, nuxt) => {
  // use nuxt hook to prepend dirs instead of using addImportsDir
  // to allow autoimport overriding overriding
  // addImportsDir(resolve(__dirname, 'lib/composables'))
  const dirs = resolve(__dirname, 'lib/composables')
  nuxt.hooks.hook('imports:dirs', (_dirs) => {
    _dirs.unshift(dirs)
  })
}

export const loadPlugins = (options, nuxt) => {
  Object.entries(options.plugins || {}).forEach(([key, activate]) => {
    if (activate) {
      const pluginAppPath = resolve(nuxt.options.srcDir, `plugins/bp/${key}`)
      const pluginAppExists =
        existsSync(pluginAppPath + '.js') ||
        existsSync(pluginAppPath + '/index.js')
      if (pluginAppExists) {
        addPlugin(pluginAppPath, { append: true })
      } else {
        const pluginPath = resolve(__dirname, `lib/plugins/${key}`)
        const pluginExists =
          existsSync(pluginPath + '.js') || existsSync(pluginPath + '/index.js')
        pluginExists && addPlugin(pluginPath, { append: true })
      }

      key in _pluginSetup && _pluginSetup[key](options, nuxt)
    }
  })
}

const _pluginSetup = {
  loading: async (_, nuxt) => {
    const loadingPath = await resolvePath('~/loading.html')
    const loadingPathExists = existsSync(loadingPath)

    if (!loadingPathExists)
      copyFileSync(
        await resolvePath('@bp/plugins/loading/loading.html'),
        loadingPath
      )

    if (existsSync(loadingPath))
      addServerPlugin(
        await resolvePath('@bp/plugins/loading/loading.server.js')
      )
  },
  nav: (_, nuxt) => {
    const navPath = resolve(nuxt.options.srcDir, './nav.js')
    const navFileDoesntExists = !existsSync(navPath)
    navFileDoesntExists &&
      copyFileSync(resolve(__dirname, 'lib/plugins/nav/nav.js'), navPath)
  },
  vuetify: (options, nuxt) => {
    const vuetify = require('vite-plugin-vuetify')
    nuxt.hooks.hook('vite:extendConfig', async (config) => {
      config.plugins.push(
        vuetify({
          styles: { configFile: await resolvePath(options.vuetify.variables) },
        })
      )
    })
  },
  icons: (options, nuxt) => {
    let activeIcons = options.icons.active || []
    if (typeof activeIcons === 'string') {
      activeIcons = [
        {
          rel: 'stylesheet',
          type: 'text/css',
          href: options.icons.cdn[activeIcons],
        },
      ]
    } else if (Array.isArray(options.icons.active)) {
      activeIcons = options.icons.active.map((icon) => ({
        rel: 'stylesheet',
        type: 'text/css',
        href: options.icons.cdn[icon],
      }))
    }

    _set(
      nuxt.options,
      'app.head.link',
      (nuxt.options?.app?.head?.link || []).concat(activeIcons)
    )
  },
}

export const loadThemePlugins = (options, _) => {
  options.theme.plugins.forEach((path) => addPlugin(path, { append: true }))
}

export const loadThemeCss = (options, nuxt) => {
  nuxt.options.css
    ? (nuxt.options.css = nuxt.options.css.concat(options.theme.css))
    : (nuxt.options.css = [...options.theme.css])
}

export const loadThemeComponents = async (options, _) => {
  if (Array.isArray(options.theme.components)) {
    options.theme.components.forEach(async (path) => {
      if (typeof path === 'string') {
        addComponentsDir({ prefix: 'bp', path: path })
      } else if (typeof path === 'object') {
        addComponentsDir({ prefix: 'bp', pathPrefix: false, ...path })
      }
    })
  } else if (options.theme.components === true) {
    addComponentsDir({
      path: '@bp-theme/components',
      prefix: 'bp',
    })
  }
}

export const loadThemeRoutes = async (options, _) => {
  let blueprintRoutes
  if (options.theme.routes === true) {
    blueprintRoutes = require(await resolvePath('@bp-theme/routes.js'))
  } else if (typeof options.theme.routes === 'string') {
    blueprintRoutes = require(await resolvePath(options.theme.routes))
  } else {
    return
  }

  await new Promise((resolve) => {
    blueprintRoutes.forEach(async (bpRoute, i) => {
      bpRoute.file = await resolvePath(bpRoute.file)
      if (i === blueprintRoutes.length - 1) resolve()
    })
  })

  await new Promise((resolve) => {
    const blueprintChildrenRoutes = blueprintRoutes.filter(
      (route) => !!route.children
    )
    if (!blueprintChildrenRoutes.length) resolve()
    blueprintChildrenRoutes.forEach((parentRoute, parentIndex, parentArr) => {
      parentRoute.children.forEach(async (route, childIndex, children) => {
        route.file = await resolvePath(route.file)
        if (
          parentIndex === parentArr.length - 1 &&
          childIndex === children.length - 1
        ) {
          resolve()
        }
      })
    })
  })

  extendPages((routes) => {
    const appRoutePaths = routes.map(({ path }) => path)
    routes = blueprintRoutes
      .filter((route) => !appRoutePaths.includes(route.path))
      .forEach((route) => routes.push(route))
  })
}

export const loadThemeLayouts = async (options, nuxt) => {
  let folderPath
  if (options.theme.layouts === true) {
    folderPath = await resolvePath('@bp-theme/layouts')
  } else if (typeof options.theme.layouts === 'string') {
    folderPath = await resolvePath(options.theme.layouts)
  } else {
    return
  }

  const errorComponentPath = resolve(nuxt.options.srcDir, './error.vue')
  if (!existsSync(errorComponentPath)) {
    nuxt.hook('app:templates', async (app) => {
      if (options.theme.layouts === true) {
        app.errorComponent = await resolvePath('@bp-theme/layouts/error.vue')
      } else if (typeof options.theme.layouts === 'string') {
        app.errorComponent = await resolvePath(
          options.theme.layouts + '/error.vue'
        )
      }
    })
  }

  readdirSync(folderPath).forEach(
    async (file) =>
      file !== 'error.vue' &&
      addLayout(
        { name: file.replace('.vue', ''), src: resolve(folderPath, file) },
        file.replace('.vue', '')
      )
  )
}

export const loadThemeComposables = async (options, nuxt) => {
  let themeComposablesPath
  if (options.theme.composables === true) {
    themeComposablesPath = await resolvePath('@bp-theme/composables')
  } else if (typeof options.theme.composables === 'string') {
    themeComposablesPath = await resolvePath(options.theme.composables)
  } else {
    return
  }

  nuxt.hooks.hook('imports:dirs', (_dirs) => {
    _dirs.unshift(themeComposablesPath)
  })
}
