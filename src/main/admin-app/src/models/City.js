import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class City extends Model {
  static entity = 'cities'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.city.nameAr', value: 'data.nameAr' },
    { text: 'attributes.city.nameEn', value: 'data.nameEn' },
    { text: 'attributes.country', value: 'data.country.nameAr' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(City),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(City, ...City.traits)

export default City
