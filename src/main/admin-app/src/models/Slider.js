import Model from './src/BaseModel'
import traits, { addTrait } from '@bp-models/traits'
import pagination from './src/pagination'

class Slider extends Model {
  static entity = 'sliders'

  static tableHeaders = [
    { text: 'attributes.id', value: 'data.id' },
    { text: 'attributes.details', value: 'data.details' },
    { text: 'attributes.created_at', value: 'createdAt' },
  ]

  static traits = [pagination, traits.bpApi, traits.filter]

  static apiConfig = {
    actions: {
      ...etApi(Slider),
    },
  }

  static filterConfig = {
    filters: [{ name: 'id', type: 'text', rewriteParam: 'id.equals' }],
  }
}

addTrait(Slider, ...Slider.traits)

export default Slider
