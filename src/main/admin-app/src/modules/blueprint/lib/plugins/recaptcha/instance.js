// reference: https://github.com/nuxt-community/recaptcha-module

const API_URL = 'https://www.recaptcha.net/recaptcha/api.js'

export class ReCaptcha {
  constructor({ hideBadge, language, mode, siteKey, version, size }) {
    this._elements = {}
    this._grecaptcha = null
    this._eventBus = null
    this._ready = false

    this.hideBadge = hideBadge
    this.language = language
    this.siteKey = siteKey
    this.version = version
    this.size = size
    this.mode = mode
  }

  destroy() {
    if (!this.siteKey) return

    if (this._ready) {
      this._ready = false

      const { head } = document
      const { style } = this._elements

      const scripts = [...document.head.querySelectorAll('script')].filter(
        (script) => script.src.includes('recaptcha')
      )

      if (scripts.length) {
        scripts.forEach((script) => head.removeChild(script))
      }

      if (head.contains(style)) {
        head.removeChild(style)
      }

      const badge = document.querySelector('.grecaptcha-badge')
      if (badge) {
        badge.remove()
      }
    }
  }

  async execute(action) {
    if (!this.siteKey) return

    try {
      await this.init()

      if ('grecaptcha' in window) {
        return this._grecaptcha.execute(this.siteKey, { action })
      }
    } catch (error) {
      throw new Error(`ReCaptcha error: Failed to execute ${error}`)
    }
  }

  getResponse(widgetId) {
    if (!this.siteKey) return

    return new Promise((resolve, reject) => {
      if ('grecaptcha' in window) {
        if (this.size == 'invisible') {
          this._grecaptcha.execute(widgetId)

          window.recaptchaSuccessCallback = (token) => {
            this._eventBus.callHook('recaptcha-success', token)
            resolve(token)
          }

          window.recaptchaErrorCallback = (error) => {
            this._eventBus.callHook('recaptcha-error', error)
            reject(error)
          }
        } else {
          const response = this._grecaptcha.getResponse(widgetId)

          if (response) {
            this._eventBus.callHook('recaptcha-success', response)
            resolve(response)
          } else {
            const errorMessage = 'Failed to execute'
            this._eventBus.callHook('recaptcha-error', errorMessage)
            reject(errorMessage)
          }
        }
      }
    })
  }

  init() {
    if (!this.siteKey) return

    if (this._ready) {
      // make sure caller waits until recaptcha get ready
      return this._ready
    }

    this._eventBus = useNuxtApp().hooks
    this._elements = {
      script: document.createElement('script'),
      style: document.createElement('style'),
    }

    const { script, style } = this._elements

    script.setAttribute('async', '')
    script.setAttribute('defer', '')

    const params = []
    if (this.version === 3) {
      params.push('render=' + this.siteKey)
    }
    if (this.language) {
      if (this.language === 'auto') {
        params.push('hl=' + useNuxtApp().$bp.options.locales.current)
      } else {
        params.push('hl=' + this.language)
      }
    }

    let scriptUrl = API_URL

    if (this.mode === 'enterprise') {
      scriptUrl = scriptUrl.replace('api.js', 'enterprise.js')
      params.push('render=' + this.siteKey)
    }

    script.setAttribute('src', scriptUrl + '?' + params.join('&'))

    window.recaptchaSuccessCallback = (token) => {
      this._eventBus.callHook('recaptcha-success', token)
    }
    window.recaptchaExpiredCallback = () => {
      this._eventBus.callHook('recaptcha-expired')
    }
    window.recaptchaErrorCallback = () => {
      this._eventBus.callHook('recaptcha-error', 'Failed to execute')
    }

    this._ready = new Promise((resolve, reject) => {
      script.addEventListener('load', () => {
        if (this.version === 3 && this.hideBadge) {
          style.innerHTML = '.grecaptcha-badge { display: none }'
          document.head.appendChild(style)
        } else if (this.version === 2 && this.hideBadge) {
          // display: none DISABLES the spam checking!
          // ref: https://stackoverflow.com/questions/44543157/how-to-hide-the-google-invisible-recaptcha-badge
          style.innerHTML = '.grecaptcha-badge { visibility: hidden; }'
          document.head.appendChild(style)
        }

        this._grecaptcha = window.grecaptcha.enterprise || window.grecaptcha
        this._grecaptcha.ready(resolve)
      })

      script.addEventListener('error', () => {
        document.head.removeChild(script)
        reject('ReCaptcha error: Failed to load script')
        this._ready = null
      })

      document.head.appendChild(script)
    })

    return this._ready
  }

  on(event, callback) {
    return this._eventBus.hook(event, callback)
  }

  reset(widgetId) {
    if (!this.siteKey) return

    if (this.version === 2 || typeof widgetId !== 'undefined') {
      this._grecaptcha.reset(widgetId)
    }
  }

  render(reference, { sitekey, theme }) {
    if (!this.siteKey) return

    return this._grecaptcha.render(reference.$el || reference, {
      sitekey,
      theme,
    })
  }
}
