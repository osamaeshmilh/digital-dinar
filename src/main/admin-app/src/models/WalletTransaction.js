import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class WalletTransaction extends Model {
  static entity = 'wallet-transactions'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.amount', value: 'data.amount' },
    {
      text: 'attributes.walletAction',
      value: 'data.walletAction',
      align: 'center',
    },
    { text: 'attributes.notes', value: 'data.notes' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(WalletTransaction),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(WalletTransaction, ...WalletTransaction.traits)

export default WalletTransaction
