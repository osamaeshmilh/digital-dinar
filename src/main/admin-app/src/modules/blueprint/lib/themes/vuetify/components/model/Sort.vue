<template>
  <div class="bp-model-sort" v-bind="{ class: 'd-flex', ...$attrs }">
    <v-autocomplete
      v-model="selected.sort_by"
      v-bind="{
        density: 'compact',
        items: sortingOptions.sort_by,
        class: 'me-4',
        menuProps: { offsetY: true },
        placeholder: $t('attributes.sort_by'),
        persistentPlaceholder: true,
        hideDetails: true,
        ...sortByAttrs,
      }"
      @update:model-value="modelFetch"
    >
      <template #prepend-inner>
        <v-icon
          v-bind="{ color: 'grey lighten-2', ...sortByIconAttrs }"
          :icon="sortByIcon"
        />
      </template>
    </v-autocomplete>
    <v-select
      v-model="selected.sort_dir"
      v-bind="{
        density: 'compact',
        items: sortingOptions.sort_dir,
        menuProps: { offsetY: true },
        placeholder: $t('attributes.sort_dir'),
        persistentPlaceholder: true,
        hideDetails: true,
        ...sortDirAttrs,
      }"
      @update:model-value="modelFetch"
    >
      <template #prepend-inner>
        <v-icon
          v-bind="{ color: 'grey lighten-2', ...sortDirIconAttrs }"
          :icon="sortDirIcon"
        />
      </template>
    </v-select>
  </div>
</template>

<script>
export default {
  props: {
    model: { type: Function, required: true },
    fetchQuery: { type: Object, default: () => ({}) },
    defautSortBy: { type: String, default: 'created_at' },
    defautSortDir: { type: String, default: 'desc' },
    sortByIcon: { type: String, default: 'mdi-sort-alphabetical-variant' },
    sortDirIcon: { type: String, default: 'mdi-sort' },
    sortByAttrs: { type: Object, default: () => ({}) },
    sortDirAttrs: { type: Object, default: () => ({}) },
    sortByIconAttrs: { type: Object, default: () => ({}) },
    sortDirIconAttrs: { type: Object, default: () => ({}) },
    fetchFunction: { type: [Function, String], default: 'fetch' },
  },
  data() {
    return {
      selected: {
        sort_by: this.$route.query.sort_by || this.defautSortBy,
        sort_dir: this.$route.query.sort_dir || this.defautSortDir,
      },
    }
  },
  computed: {
    sortingOptions() {
      return {
        sort_by: this.model.filterConfig.sorting
          ? this.getSortOptions(this.model.filterConfig.sortByOptions)
          : [],
        sort_dir: this.getSortOptions([
          { text: 'desc', value: 'desc' },
          { text: 'asc', value: 'asc' },
        ]),
      }
    },
  },
  watch: {
    '$route.query.sort_by'(value) {
      this.selected.sort_by = value || this.defautSortBy
      setTimeout(() => this.fetchData())
    },
    '$route.query.sort_dir'(value) {
      this.selected.sort_dir = value || this.defautSortDir
      setTimeout(() => this.fetchData())
    },
  },
  methods: {
    modelFetch() {
      Object.keys(this.selected).forEach(
        (key) => !this.selected[key] && delete this.selected[key]
      )
      // eslint-disable-next-line camelcase
      const { sort_by, sort_dir, ...query } = this.$route.query
      this.$router.push({
        query: { ...query, ...this.selected },
      })
      // setTimeout(() => this.fetchData())
    },
    getSortOptions(options) {
      return options.map(({ value, text }) => ({
        value,
        title: this.$t(`labels.${text}`),
      }))
    },
    fetchData() {
      if (typeof this.fetchFunction === 'string') {
        if (this.model.bpApi) {
          return this.model.bpApi()[this.fetchFunction](this.fetchQuery)
        }
        return this.model[this.fetchFunction](this.fetchQuery)
      } else if (typeof this.fetchFunction === 'function') {
        return this.fetchFunction(this.fetchQuery)
      }
    },
  },
}
</script>

<style></style>
