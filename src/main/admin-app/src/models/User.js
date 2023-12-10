import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class User extends Model {
  static entity = 'users'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.username', value: 'data.login' },
    { text: 'attributes.firstName', value: 'data.firstName' },
    { text: 'attributes.lastName', value: 'data.lastName' },
    { text: 'attributes.email', value: 'data.email' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(User),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(User, ...User.traits)

export default User
