import Model from '@bp-models/BaseModel'
import traits, { addTrait } from '@bp-models/traits'

class Account extends Model {
  static entity = 'account'

  static traits = [traits.bpApi]

  static apiConfig = {
    actions: {
      updateProfile: {
        method: 'PATCH',
        url: '/user/profile',
        save: false,
        bpConfig: {
          loading: 'loading.updateProfile',
          success: {
            alertKey: 'profileForm',
            alert: ['notify.updated', { entity: 'entities.account' }],
          },
          error: {
            alertKey: 'profileForm',
            alertResponse: 'message',
            validate: true,
          },
        },
      },
      updatePassword: {
        method: 'PATCH',
        url: '/user/profile',
        save: false,
        bpConfig: {
          loading: 'loading.updatePassword',
          success: {
            alertKey: 'passwordForm',
            alert: ['notify.updated', { entity: 'entities.password' }],
          },
          error: {
            alertKey: 'passwordForm',
            alertResponse: 'message',
            validate: true,
          },
        },
      },
    },
  }
}
addTrait(Account, ...Account.traits)

export default Account
