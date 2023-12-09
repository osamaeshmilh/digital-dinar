import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'

class Auth extends Model {
  static entity = 'auth'
  static traits = [traits.bpApi]

  static apiConfig = {
    actions: {
      login: {
        bpConfig: {
          request(data, config) {
            const { $auth, $axios } = useNuxtApp()
            return $auth
              .login(data, { ...(config || {}), fetchUser: false })
              .then((response) => {
                const token = response.data.id_token
                useCookie('app-token', { maxAge: 3600 }).value =
                  response.data.id_token
                $axios.defaults.headers.common = {
                  Authorization: `Bearer ${token}`,
                }
                return $auth.fetchUser()
              })
          },
          loading: 'loading.login',
        },
      },
      logout: {
        bpConfig: {
          request: (...args) => useNuxtApp().$auth.logout(...args),
          loading: 'loading.logout',
        },
      },
      forgotPassword: {
        url: '/account/reset-password/init',
        method: 'post',
        bpConfig: {
          loading: 'loading.forgotPassword',
          success: {},
          error: {
            validate: true,
            alertKey: 'forgetPasswordPage',
          },
        },
      },
      resetPassword: {
        url: '/account/reset-password/finish',
        method: 'post',
        bpConfig: {
          loading: 'loading.resetPassword',
          success: {
            redirect: '/auth/login',
            persistAlerts: true,
            alert: 'notify.resetPassword',
          },
          error: {
            validate: true,
            alertKey: 'resetPasswordPage',
          },
        },
      },
    },
  }
}

addTrait(Auth, ...Auth.traits)

export default Auth
