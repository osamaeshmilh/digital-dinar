import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class VoucherType extends Model {
  static entity = 'voucher-types'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.nameAr', value: 'data.nameAr' },
    { text: 'attributes.nameEn', value: 'data.nameEn' },
    { text: 'attributes.slug', value: 'data.slug' },
    { text: 'attributes.amount', value: 'data.amount' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(VoucherType),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(VoucherType, ...VoucherType.traits)

export default VoucherType
