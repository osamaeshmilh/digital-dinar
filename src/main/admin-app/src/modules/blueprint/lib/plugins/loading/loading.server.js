import { resolve } from 'path'
import { readFileSync } from 'fs'
import { srcDir } from '#bp-options'

export default defineNitroPlugin((nitroApp) => {
  nitroApp.hooks.hook('render:html', (html) => {
    const loadingHtml = readFileSync(resolve(srcDir, 'loading.html')).toString()
    html.bodyAppend.push(loadingHtml)
  })
})
