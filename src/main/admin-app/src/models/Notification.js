import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class Notification extends Model {
  static entity = 'notifications'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.title', value: 'data.title' },
    { text: 'attributes.description', value: 'data.description' },
    { text: 'attributes.userId', value: 'data.country.userId' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(Notification),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(Notification, ...Notification.traits)

export default Notification
