import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class WalletUser extends Model {
  static entity = 'wallet-users'

  static tableHeaders = [
    { text: '#', value: 'id' },
    {
      text: 'attributes.walletUser.arabicFirstName',
      value: 'data.arabicFirstName',
    },
    {
      text: 'attributes.walletUser.arabicLastName',
      value: 'data.arabicLastName',
    },
    {
      text: 'attributes.walletUser.englishFirstName',
      value: 'data.englishFirstName',
    },
    {
      text: 'attributes.walletUser.englishLastName',
      value: 'data.englishLastName',
    },
    {
      text: 'attributes.walletUser.commercialRegistryNo',
      value: 'data.commercialRegistryNo',
    },
    {
      text: 'attributes.walletUser.commercialLicenceNo',
      value: 'data.commercialLicenceNo',
    },
    {
      text: 'attributes.walletUser.commercialName',
      value: 'data.commercialName',
    },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(WalletUser),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(WalletUser, ...WalletUser.traits)

export default WalletUser
