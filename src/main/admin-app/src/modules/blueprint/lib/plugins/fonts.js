export default defineNuxtPlugin(async (app) => {
  const bpOptions = app.$bp.options
  const { fonts: bpFontsOptions } = bpOptions

  let fontLinks = []
  let fontCss = []

  const gFont = (font) =>
    `https://fonts.googleapis.com/css2?family=${font}:wght@300;400;500;700;900&display=swap`

  const fontOption = bpFontsOptions.localeFonts
  if (fontOption && typeof fontOption === 'string') {
    fontLinks = bpFontsOptions.loadGoogleFont
      ? [{ rel: 'stylesheet', href: gFont(fontOption) }]
      : []
    fontCss = [{ children: `html, body { font-family: '${fontOption}' }` }]
  } else if (fontOption && typeof fontOption === 'object') {
    fontLinks = bpFontsOptions.loadGoogleFont
      ? Object.entries(fontOption).map(([_, font]) => ({
          rel: 'stylesheet',
          href: gFont(font),
        }))
      : []

    fontCss = Object.entries(fontOption).map(
      ([locale, font], _, fontsArray) => {
        const fallback = fontsArray
          .filter(([_locale]) => locale !== _locale)
          .map(([_, _font]) => _font)
        const fallbackString = fallback.join(',')
          ? ', ' + fallback.join(',')
          : ''
        return {
          children: `html, body.bp-locale-${locale} { font-family: ${font} ${fallbackString} }`,
        }
      }
    )
  }

  // load more fonts
  const moreFonts =
    bpFontsOptions.load.map((font) => ({
      rel: 'stylesheet',
      href: gFont(font),
    })) || []

  fontLinks = fontLinks.concat(moreFonts)

  fontLinks = (fontLinks || []).length
    ? fontLinks.concat([
        { rel: 'preconnect', href: `https://fonts.googleapis.com` },
        { rel: 'preconnect', href: `https://fonts.gstatic.com` },
      ])
    : []

  useHead({
    link: fontLinks,
    style: fontCss,
  })
})
