import _merge from 'lodash/merge'

export function etApi(model, overrides) {
  const entity = model.apiEntity || model.entity
  const camelizedEntity = camelize(model.apiAlertEntity || entity)

  return _merge(
    {
      fetch: {
        persistBy: 'create',
        url: `/${entity}?size=15&sort=id,desc`,
        dataTransformer: ({ data, headers }) => {
          data.meta?.filters &&
            model.setState('filtersResponse', data.meta.filters)
          headers &&
            model.handlePaginationResponse?.({
              total: +headers['x-total-count'],
            })
          return model.transformData(data)
        },
        bpConfig: {
          loading: 'loading.fetch',
          apiParams: true,
        },
      },
      export: {
        url: `/${entity}?size=100000000&sort=id,desc`,
        save: false,
        bpConfig: {
          loading: 'loading.export',
          apiParams: true,
          successHandler(model, response) {
            if ((response.data || []).length > 0) {
              const fileName =
                entity + '_' + useNuxtApp().$dayjs().format('YYYYMMDDHHmmss')
              if (model.exportDataFormat) {
                const data = model.exportDataFormat(response.data)
                exportCSV(data, fileName)
              } else {
                exportCSV(response.data, fileName)
              }
            } else {
              useNuxtApp().$alerts.warning(
                useNuxtApp().$t('notify.nothingToExport'),
              )
            }
          },
        },
      },
      fetchList: {
        save: false,
        url: `/${entity}?per_page=all`,
        bpConfig: {
          loading: 'loading.fetchList',
        },
      },
      fetchOne: {
        persistBy: 'insertOrUpdate',
        url: `/${entity}/{id}`,
        dataTransformer: (response) => {
          return { id: response.data.id, data: response.data }
        },
        bpConfig: {
          loading: 'loading.fetchOne',
          error: { notFound: true },
        },
      },
      create: {
        method: 'POST',
        dataKey: 'data',
        url: `/${entity}`,
        bpConfig: {
          loading: 'loading.create',
          success: {
            alertKey: `${camelizedEntity}Index`,
            alert: [
              'notify.added',
              { entity: 'entities.singular.' + camelizedEntity },
            ],
          },
        },
      },
      update: {
        method: 'PATCH',
        dataKey: 'data',
        url: `/${entity}/{id}`,
        bpConfig: {
          loading: 'loading.update',
          success: {
            alertKey: `${camelizedEntity}Index`,
            alert: [
              'notify.updated',
              { entity: 'entities.singular.' + camelizedEntity },
            ],
          },
        },
      },
      delete: {
        save: false,
        method: 'DELETE',
        url: `/${entity}/{id}`,
        bpConfig: {
          loading: 'loading.delete',
          success: {
            alertKey: `${camelizedEntity}Index`,
            alert: [
              'notify.deleted',
              { entity: 'entities.singular.' + camelizedEntity },
            ],
          },
        },
      },
      clear: {
        save: false,
        method: 'DELETE',
        url: `/${entity}`,
        bpConfig: {
          loading: 'loading.clear',
          success: {
            alertKey: `${camelizedEntity}Index`,
            alert: [
              'notify.deleted',
              { entity: 'entities.' + camelizedEntity },
            ],
          },
        },
      },
    },
    model.apiOverrides || {},
    overrides,
  )
}
