import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class Bank extends Model {
  static entity = 'banks'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.bank.nameAr', value: 'data.nameAr' },
    { text: 'attributes.bank.nameEn', value: 'data.nameEn' },
    { text: 'attributes.bank.swiftCode', value: 'data.swiftCode' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(Bank),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(Bank, ...Bank.traits)

export default Bank
