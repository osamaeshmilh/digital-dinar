import Model from '@bp-models/BaseModel'
import traits, { addTrait } from '@bp-models/traits'

class Tfa extends Model {
  static entity = 'tfa'

  static traits = [traits.bpApi]

  static state() {
    return {
      ...Tfa.getTraitState(),
      qrCode: '',
    }
  }

  static apiConfig = {
    actions: {
      register: {
        method: 'POST',
        url: '/auth/tfa/register',
        save: false,
        bpConfig: {
          loading: 'loading.register',
          successHandler(model, response) {
            model.setState('qrCode', response.data.svg)
          },
          success: { alertKey: 'tfaCard', alert: 'notify.tfaEnabled' },
          error: {
            validate: true,
            alertResponse: 'message',
            alertKey: 'tfaRegisterDialog',
          },
        },
      },
      enable: {
        method: 'POST',
        url: '/auth/tfa/enable',
        save: false,
        bpConfig: {
          loading: 'loading.enable',
          errorHandler(model, error) {
            if (error.response.status === 423) {
              throw error
            } else if (error.response.data.message) {
              useNuxtApp().$alerts.error(error.response.data.message, 'tfaCard')
            } else {
              useNuxtApp().$alerts.error(
                useNuxtApp().$t('errors.other'),
                'tfaCard'
              )
            }
          },
        },
      },
      disable: {
        method: 'POST',
        url: '/auth/tfa/disable',
        save: false,
        bpConfig: {
          loading: 'loading.disable',
          error: {
            alertKey: 'tfaDisableDialog',
            alertResponse: 'message',
          },
          success: {
            alertKey: 'tfaCard',
            alert: 'notify.tfaDisabled',
          },
        },
      },
      fetchRecovery: {
        method: 'POST',
        url: '/auth/tfa/recovery-codes',
        save: false,
        bpConfig: {
          loading: 'loading.fetchRecovery',
          successHandler(model, response) {
            return response.data.data.codes
          },
          error: {
            alertKey: 'tfaCard',
            alertResponse: 'message',
          },
        },
      },
    },
  }
}
addTrait(Tfa, ...Tfa.traits)

export default Tfa
