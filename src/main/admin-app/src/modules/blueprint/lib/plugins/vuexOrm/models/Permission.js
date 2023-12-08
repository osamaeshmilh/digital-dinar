import Model from '@bp-models/BaseModel'
import traits, { addTrait } from '@bp-models/traits'

class Permission extends Model {
  static entity = 'permissions'
  static traits = [traits.bpApi]

  static fields() {
    return {
      section: this.string(''),
      label: this.string(''),
      permissions: this.attr([]),
      order: this.attr(''),
    }
  }

  static list() {
    return Permission.all()
      .map((item) => [...item.permissions])
      .flat()
  }

  static apiConfig = {
    actions: {
      fetch: traits.bpApi.crudApi(Permission, {
        fetch: { url: '/permissions?embed=permissions,translations' },
      }).fetch,
    },
  }
}

addTrait(Permission, ...Permission.traits)

export default Permission
