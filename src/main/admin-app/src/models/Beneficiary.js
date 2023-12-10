import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class Beneficiary extends Model {
  static entity = 'beneficiaries'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.bankAccountName', value: 'data.bankAccountName' },
    { text: 'attributes.bankAccountNumber', value: 'data.bankAccountNumber' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(Beneficiary),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(Beneficiary, ...Beneficiary.traits)

export default Beneficiary
