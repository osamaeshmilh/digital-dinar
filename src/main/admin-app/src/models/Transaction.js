import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class Transaction extends Model {
  static entity = 'transactions'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.notes', value: 'data.paymentType' },
    { text: 'attributes.total', value: 'data.total' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(Transaction),
    },
  }

  static filterConfig = {
    filters: [
      { name: 'id', type: 'text', rewriteParam: 'id.equals' },
      { name: 'amount', type: 'text', rewriteParam: 'amount.equals' },
      {
        name: 'status',
        rewriteParam: 'transactionStatus.equals',
        type: 'select',
        options: [
          { rawText: 'CANCELLED', value: 'CANCELLED' },
          { rawText: 'ERROR', value: 'ERROR' },
          { rawText: 'PAYED', value: 'PAYED' },
          { rawText: 'PENDING_PAYMENT', value: 'PENDING_PAYMENT' },
        ],
      },
    ],
  }
}

addTrait(Transaction, ...Transaction.traits)

export default Transaction
