import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class Country extends Model {
  static entity = 'countries'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.nameAr', value: 'data.nameAr' },
    { text: 'attributes.nameEn', value: 'data.nameEn' },
    { text: 'attributes.isoNo', value: 'data.isoNo' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(Country),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(Country, ...Country.traits)

export default Country
