import Model from '@bp-models/BaseModel'
import traits, { addTrait } from '@bp-models/traits'

class Role extends Model {
  static entity = 'roles'
  static traits = [traits.bpApi, traits.pagination]

  static fields() {
    return {
      id: this.attr(),
      name: this.attr(''),
      label: this.attr(''),
      created_at: this.attr(''),
      updated_at: this.attr(''),
      permissions: this.attr([]),
      translations: this.attr({}),
      editable: this.boolean(),
      deletable: this.boolean(),
      order: this.attr(''),
    }
  }

  static tableHeaders = [
    { text: 'attributes.name', value: 'name' },
    { text: 'attributes.label', value: 'label' },
    { text: 'labels.permissions', value: 'permissions' },
    { text: 'attributes.created_at', value: 'createdAt', width: '180px' },
    { value: 'actions', align: 'end' },
  ]

  static apiConfig = {
    actions: {
      ...traits.bpApi.crudApi(Role, {
        fetch: { url: '/roles?embed=permissions,translations' },
      }),
    },
  }
}

addTrait(Role, ...Role.traits)

export default Role
