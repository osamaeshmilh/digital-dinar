import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class Voucher extends Model {
  static entity = 'vouchers'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.code', value: 'data.code' },
    { text: 'attributes.amount', value: 'data.amount' },
    { text: 'attributes.serialNumber', value: 'data.serialNumber' },
    { text: 'attributes.isSold', value: 'data.isSold' },
    { text: 'attributes.voucherType', value: 'data.voucherType' },
    { text: 'attributes.voucherCompany', value: 'data.voucherCompany' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(Voucher),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(Voucher, ...Voucher.traits)

export default Voucher
