import Model from '@bp-models/BaseModel'
import traits, { addTrait } from '@bp-models/traits'

// override to use state pagination
// todo: refactor pagination trait to handle this
const pagination = Object.assign({}, traits.pagination)
pagination.setPage = function (page) {
  this.setState('pagination.currentPage', page)
}
pagination.traitApiParams = function (model) {
  return {
    page: model.getState('pagination.currentPage') || 1,
  }
}

class Notification extends Model {
  static entity = 'notifications'

  static traits = [pagination, traits.bpApi]

  static fields() {
    return {
      id: this.attr(),
      link: this.attr(''),
      subject: this.attr(''),
      is_read: this.attr(false),
      created_at: this.attr(''),
      order: this.attr(''),
    }
  }

  static tableHeaders = [
    {
      text: 'attributes.url',
      value: 'link',
    },
    {
      text: 'attributes.description',
      value: 'subject',
    },
    { text: 'attributes.created_at', value: 'createdAt', width: '180px' },
  ]

  static fetch() {
    return this.bpApi().fetch(this.apiParams())
  }

  static state() {
    return {
      ...this.getTraitState(),
      fetching: false,
      loading: {
        fetchUnread: false,
        markAsRead: false,
      },
      unreadCount: 0,
    }
  }

  static apiConfig = {
    actions: {
      fetch: {
        persistBy: 'create',
        method: 'get',
        url: '/api/notifications',
        dataTransformer: ({ data }) => {
          this.handlePaginationResponse(data.meta)
          return data.data.map((notification, order) => ({
            ...notification,
            order,
          }))
        },
        bpConfig: {
          loading: 'fetching',
        },
      },
      fetchUnread: {
        save: false,
        method: 'get',
        url: '/api/notifications/unread-count',
        bpConfig: {
          loading: 'loading.fetchUnread',
          successHandler(model, response) {
            model.setState('unreadCount', response.data.data.count)
          },
        },
      },
      markAsRead: {
        save: false,
        method: 'post',
        url: '/api/notifications/mark-read',
        bpConfig: {
          loading: 'loading.markAsRead',
        },
      },
    },
  }
}

addTrait(Notification, ...Notification.traits)

export default Notification
