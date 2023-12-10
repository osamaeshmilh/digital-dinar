import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class Category extends Model {
  static entity = 'categories'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.nameAr', value: 'data.nameAr' },
    { text: 'attributes.nameEn', value: 'data.nameEn' },
    { text: 'attributes.code', value: 'data.code' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(Category),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(Category, ...Category.traits)

export default Category
