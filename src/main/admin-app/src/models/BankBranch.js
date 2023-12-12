import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class BankBranch extends Model {
  static entity = 'bank-branches'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.bankBranch.nameAr', value: 'data.nameAr' },
    { text: 'attributes.bankBranch.nameEn', value: 'data.nameEn' },
    { text: 'attributes.bankBranch.address', value: 'data.address' },
    { text: 'attributes.bankBranch.bank', value: 'data.bank.nameAr' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(BankBranch),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(BankBranch, ...BankBranch.traits)

export default BankBranch
