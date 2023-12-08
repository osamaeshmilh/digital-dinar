<template>
  <bp-base-input
    v-if="filter.type === 'text'"
    :class="`bp-base-data-filter bp-base-data-filter-${filter.type}`"
    v-bind="{
      name: filter.name,
      density: 'compact',
      label: filter.label,
      ...$attrs,
    }"
  />
  <bp-base-select
    v-else-if="filter.type === 'select'"
    :class="`bp-base-data-filter bp-base-data-filter-${filter.type}`"
    v-bind="{
      name: filter.name,
      density: 'compact',
      label: filter.label,
      loading: filter.loading,
      disabled: filter.loading,
      items: getFilterOptions(filter),
      clearable: true,
      ...$attrs,
    }"
  />
  <bp-base-autocomplete
    v-else-if="filter.type === 'autocomplete'"
    :class="`bp-base-data-filter bp-base-data-filter-${filter.type}`"
    v-bind="{
      name: filter.name,
      density: 'compact',
      label: filter.label,
      loading: filter.loading,
      disabled: filter.loading,
      items: getFilterOptions(filter),
      clearable: true,
      ...$attrs,
    }"
  />
  <bp-base-date-picker-input
    v-else-if="filter.type === 'date'"
    :class="`bp-base-data-filter bp-base-data-filter-${filter.type}`"
    v-bind="{
      name: filter.name,
      density: 'compact',
      label: filter.label,
      clearable: true,
      ...$attrs,
    }"
  />
  <bp-base-input
    v-else-if="filter.type === 'amount'"
    v-model="amountValue"
    :class="`bp-base-data-filter bp-base-data-filter-${filter.type}`"
    v-bind="{
      name: filter.name,
      density: 'compact',
      label: filter.label,
      ...amountAttrs,
    }"
    @update:model-value="
      $emit('update:model-value', `${amountOperation}${$event}`)
    "
  >
    <template #prepend>
      <BpBaseSelect
        v-model="amountOperation"
        class="bp-base-data-filter-amount-prepend"
        v-bind="{
          width: '100px',
          density: 'compact',
          hideDetails: true,
          items: amountItems,
        }"
        @update:model-value="
          $emit('update:model-value', `${$event}${amountValue}`)
        "
      />
    </template>
  </bp-base-input>
</template>

<script>
export default {
  inheritAttrs: false,
  props: {
    filter: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      loading: false,
      options: [],
      amountAttrs: {},
      amountValue: '',
      amountOperation: '=',
      amountItems: [
        { title: '=', value: '=' },
        { title: '<', value: '<' },
        { title: '<=', value: '<=' },
        { title: '>', value: '>' },
        { title: '>=', value: '>=' },
      ],
    }
  },
  mounted() {
    if (this.filter.type === 'amount') {
      this.amountAttrs = Object.keys(this.$attrs || {})
        .filter((key) => !['modelValue', 'onUpdate:modelValue'].includes(key))
        .reduce((pv, cv) => ({ ...pv, [cv]: this.$attrs[cv] }), {})
      if (this.$route.query[this.filter.name]) {
        this.setupAmountField()
      }
    }
  },
  methods: {
    setupAmountField() {
      const inputString = this.$route.query[this.filter.name]
      if (!inputString) {
        return
      }

      // Define the operators and their lengths
      const operators = this.amountItems.map(({ value }) => value)
      const operatorLengths = this.amountItems.map(({ value }) => value.length)

      let operator = ''
      let amount = ''

      // Iterate through the operators and find the longest match
      for (let i = 0; i < operators.length; i++) {
        const currentOperator = operators[i]
        const currentOperatorLength = operatorLengths[i]

        if (inputString.startsWith(currentOperator)) {
          operator = currentOperator
          amount = inputString.substring(currentOperatorLength).trim()
          break
        }
      }

      this.amountOperation = operator || '='
      this.amountValue = amount || ''
    },
    getFilterOptions() {
      Promise.resolve(this.filter.options).then((data) => (this.options = data))
      return this.options
    },
  },
}
</script>

<style></style>
