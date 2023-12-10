import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class VoucherCompany extends Model {
  static entity = 'voucher-companies'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.nameAr', value: 'data.nameAr' },
    { text: 'attributes.nameEn', value: 'data.nameEn' },
    { text: 'attributes.mobileNo', value: 'data.mobileNo' },
    { text: 'attributes.email', value: 'data.email' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(VoucherCompany),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(VoucherCompany, ...VoucherCompany.traits)

export default VoucherCompany
