import Model from '@bp-models/BaseModel'
import traits, { addTrait } from '@bp-models/traits'

class Auth extends Model {
  static entity = 'auth'
  static traits = [traits.bpApi]

  static apiConfig = {
    actions: {
      login: {
        bpConfig: {
          request(data, config) {
            const { $auth } = useNuxtApp()
            return $auth
              .login(data, { ...(config || {}), fetchUser: false })
              .then((response) => {
                if (response?.data?.data?.tfa) {
                  Auth.setState('tfa', response.data.data.tfa)
                  return useRouter().push({
                    name: 'auth-tfa-index',
                    state: config.data,
                  })
                }
                return $auth.fetchUser().then((response) => ({ response }))
              })
          },
          loading: 'loading.login',
          error: { alertResponse: 'message' },
        },
      },
      logout: {
        bpConfig: {
          request: (...args) => useNuxtApp().$auth.logout(...args),
          loading: 'loading.logout',
          error: { alertResponse: 'message' },
        },
      },
      forgotPassword: {
        url: '/auth/password/forgot',
        method: 'post',
        bpConfig: {
          loading: 'loading.forgotPassword',
          success: {
            redirect: '/auth/login',
            persistAlerts: true,
            alertResponse: 'message',
          },
          error: {
            validate: true,
            alertKey: 'forgetPasswordPage',
            alertResponse: 'message',
          },
        },
      },
      resetPassword: {
        url: '/auth/password/reset',
        method: 'post',
        bpConfig: {
          loading: 'loading.resetPassword',
          success: {
            redirect: '/auth/login',
            persistAlerts: true,
            alertResponse: 'message',
          },
          error: {
            validate: true,
            alertKey: 'resetPasswordPage',
            alertResponse: 'message',
          },
        },
      },
    },
  }
}

addTrait(Auth, ...Auth.traits)

export default Auth
