/* eslint-disable camelcase */
export default {
  traitApiParams(model) {
    const router = useRouter()
    return {
      page: router.currentRoute.value.query.page || 0,
    }
  },
  traitState() {
    return {
      pagination: {
        from: 0,
        to: 0,
        total: 0,
        totalPages: 1,
        currentPage: 0,
        perPage: 10,
      },
    }
  },

  updatePageQuery(newPage) {
    const router = useRouter()
    const { page, ...rest } = router.currentRoute.value.query
    // don't need to push route if:
    // - page parameter doesn't exist AND newPage is undefined or 0
    if (!page && page !== 0 && (!newPage || +newPage === 0)) {
      return
    }
    router.push({
      query: {
        ...rest,
        ...(+newPage === 0 ? {} : { page: newPage }),
      },
    })
  },

  handlePaginationResponse({
    current_page,
    total,
    to,
    from,
    last_page,
    per_page,
  }) {
    this.setState('pagination', {
      from,
      to,
      currentPage: current_page,
      total,
      totalPages: last_page,
      perPage: per_page,
    })
  },

  setPage(page) {
    this.updatePageQuery(page)
  },

  setPerPage(perPage) {
    this.setState('pagination.perPage', perPage)
    this.setPage(0)
  },
}
