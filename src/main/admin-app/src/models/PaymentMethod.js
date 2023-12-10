import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class PaymentMethod extends Model {
  static entity = 'payment-methods'

  static tableHeaders = [
    { text: '#', value: 'id' },
    { text: 'attributes.name', value: 'data.name' },
    { text: 'attributes.details', value: 'data.details' },
    { text: 'attributes.feePercentage', value: 'data.feePercentage' },
    { text: 'attributes.paymentType', value: 'data.paymentType' },
    { text: 'attributes.notes', value: 'data.notes' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(PaymentMethod),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(PaymentMethod, ...PaymentMethod.traits)

export default PaymentMethod
