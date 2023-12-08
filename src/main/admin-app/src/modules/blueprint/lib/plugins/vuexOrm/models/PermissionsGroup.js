import Model from '@bp-models/BaseModel'

class PermissionsGroup extends Model {
  static entity = 'permissions-groups'

  static traits = []

  static fields() {
    return {
      id: this.attr(),
      name: this.attr(),
      permissions: this.attr(),
      display_name: this.attr(),
    }
  }

  static state() {
    return {
      ...this.getTraitState(),
      fetching: false,
    }
  }

  static tableHeaders = [
    { text: 'attributes.name', value: 'name' },
    { text: 'attributes.display_name', value: 'display_name' },
    {
      text: '',
      value: 'actions',
      align: 'right',
      sortable: false,
    },
  ]

  static fetch() {
    this.setState('fetching', true)
    return this.api()
      .fetch(this.apiParams())
      .finally(() => this.setState('fetching', false))
  }

  static apiConfig = {
    actions: {
      fetch: {
        persistBy: 'create',
        method: 'get',
        url: '/api/permissions/groups',
        dataTransformer: ({ data }) => data.data,
      },
    },
  }
}

export default PermissionsGroup
