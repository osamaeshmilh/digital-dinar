import Model from '@bp-models/BaseModel'
import traits, { addTrait } from '@bp-models/traits'

class User extends Model {
  static entity = 'users'
  static traits = [traits.bpApi, traits.pagination, traits.backendFilters]

  static filterConfig = {
    rewriteParam: `filters[{name}]`,
    type: 'backend',
  }

  static tableHeaders = [
    { text: 'attributes.name', value: 'name' },
    { text: 'attributes.email', value: 'email' },
    { text: 'attributes.role', value: 'role.name' },
    { text: 'attributes.tfa_enabled', value: 'tfa_enabled' },
    { text: 'attributes.created_at', value: 'createdAt', width: '180px' },
    { align: 'end', value: 'actions' },
  ]

  static fields() {
    return {
      id: this.attr(),
      name: this.string(),
      email: this.string(),
      role: this.attr({}),
      has_tfa_enabled: this.boolean(),
      created_at: this.attr(''),
      updated_at: this.attr(''),
      order: this.attr(),
    }
  }

  static apiConfig = {
    actions: {
      ...traits.bpApi.crudApi(User),
    },
  }
}

addTrait(User, ...User.traits)

export default User
