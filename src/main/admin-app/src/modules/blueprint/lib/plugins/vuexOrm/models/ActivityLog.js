import Model from '@bp-models/BaseModel'
import traits, { addTrait } from '@bp-models/traits'

class ActivityLog extends Model {
  static entity = 'activity-logs'
  static traits = [traits.bpApi, traits.pagination, traits.backendFilters]

  static filterConfig = {
    rewriteParam: `filters[{name}]`,
    type: 'backend',
    filters: [
      { name: 'created_at[from]', rewriteParam: 'filters[created_at][from]' },
      { name: 'created_at[to]', rewriteParam: 'filters[created_at][to]' },
    ],
  }

  static tableHeaders = [
    { text: 'attributes.description', value: 'description' },
    { text: 'attributes.log_name', value: 'log_name' },
    { text: 'attributes.subject', value: 'subject' },
    { text: 'attributes.created_at', value: 'createdAt', width: '180px' },
  ]

  static fields() {
    return {
      id: this.attr(),
      log_name: this.attr(''),
      description: this.attr(''),
      user: this.attr({}),
      properties: this.attr([]),
      subject: this.attr({}),
      created_at: this.attr(''),
      order: this.attr(''),
    }
  }

  static apiConfig = {
    actions: {
      fetch: traits.bpApi.crudApi(ActivityLog).fetch,
    },
  }
}

addTrait(ActivityLog, ...ActivityLog.traits)

export default ActivityLog
