import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class Beneficiary extends Model {
  static entity = 'beneficiaries'

  static tableHeaders = [
    { text: '#', value: 'id' },
    {
      text: 'attributes.beneficiary.arabicFirstName',
      value: 'data.arabicFirstName',
    },
    {
      text: 'attributes.beneficiary.arabicLastName',
      value: 'data.arabicLastName',
    },
    {
      text: 'attributes.beneficiary.englishFirstName',
      value: 'data.englishFirstName',
    },
    {
      text: 'attributes.beneficiary.englishLastName',
      value: 'data.englishLastName',
    },
    {
      text: 'attributes.beneficiary.bankAccountName',
      value: 'data.bankAccountName',
    },
    {
      text: 'attributes.beneficiary.bankAccountNumber',
      value: 'data.bankAccountNumber',
    },
    { text: 'attributes.beneficiary.idType', value: 'data.idType' },
    { text: 'attributes.beneficiary.idNo', value: 'data.idNo' },
    { text: 'attributes.beneficiary.mobileNo', value: 'data.mobileNo' },
    { text: 'attributes.beneficiary.email', value: 'data.email' },
    { text: 'attributes.beneficiary.notes', value: 'data.notes' },
    { text: 'attributes.beneficiary.isVerified', value: 'data.isVerified' },
    {
      text: 'attributes.beneficiary.hasTransferred',
      value: 'data.hasTransferred',
    },
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
