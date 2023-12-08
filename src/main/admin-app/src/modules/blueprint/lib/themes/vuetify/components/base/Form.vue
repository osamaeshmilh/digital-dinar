<template>
  <div class="bp-base-form">
    <v-form ref="form" v-bind="formAttrs" @submit.prevent="onSubmit">
      <slot name="tabs" v-bind="{ tabs: schema.tabs }">
        <v-tabs v-if="schema.tabs" v-model="currentTab" v-bind="schema.tabs" />
      </slot>
      <slot v-if="!hideFormAlerts" name="form-alerts" v-bind="{ alertKey }">
        <BpAlerts
          v-bind="{ alertKey, alertAttrs: { class: 'mb-6' }, ...alertsAttrs }"
        />
      </slot>
      <v-row v-bind="rowAttrs">
        <slot name="prepend-fields" v-bind="{ model, loading }"> </slot>
        <slot
          v-for="field in schema.fields"
          :name="`field.${field.name}`"
          v-bind="{
            model,
            loading,
            field,
            rules: serelizeRules(field),
            show: !schema.tabs || currentTab === field.tab,
            errors: errorBag,
          }"
        >
          <slot
            :name="`field.${field.type}`"
            v-bind="{
              model,
              loading,
              field,
              rules: serelizeRules(field),
              show: !schema.tabs || currentTab === field.tab,
              errors: errorBag,
            }"
          >
            <v-col
              v-show="!schema.tabs || currentTab === field.tab"
              :key="`${field.name}-col`"
              v-bind="{ ...getColsAttrs(field), ...colAttrs }"
            >
              <bp-base-form-field
                v-model="model[field.name]"
                v-bind="{
                  field,
                  rules: serelizeRules(field),
                  errors: errorBag,
                  ...fieldAttrs,
                }"
                @input="$emit('changed')"
              >
                <template
                  v-for="name in Object.keys($slots)
                    .filter((name) =>
                      name.includes(`field.${field.name}.slots.`)
                    )
                    .map((name) =>
                      name.replace(`field.${field.name}.slots.`, '')
                    )"
                  #[`${name}`]="slotProps"
                >
                  <slot
                    v-if="slotProps"
                    :name="`field.${field.name}.slots.${name}`"
                    v-bind="{
                      ...slotProps,
                      bpForm: {
                        field,
                        rules: serelizeRules(field),
                        errors: errorBag,
                        ...fieldAttrs,
                      },
                    }"
                  />
                </template>
              </bp-base-form-field>
            </v-col>
          </slot>
        </slot>

        <v-col v-if="$slots['append-fields']">
          <slot name="append-fields" v-bind="{ model, loading }" />
        </v-col>

        <slot name="form-actions">
          <v-col cols="12" :class="formActionsClass">
            <slot name="prepend-form-actions" v-bind="{ model, loading }" />
            <bp-base-button
              v-if="resetBtnAttrs"
              v-bind="computedResetBtnAttrs"
              @click="onReset"
            />
            <BpBaseButton
              :loading="loading || computedSubmitBtnAttrs.disabled"
              type="submit"
              v-bind="computedSubmitBtnAttrs"
            />
            <slot name="append-form-actions" v-bind="{ model, loading }" />
          </v-col>
        </slot>
      </v-row>
    </v-form>
  </div>
</template>

<script>
import _get from 'lodash/get'
export default {
  inheritAttrs: false,
  props: {
    modelValue: { type: [Object, undefined], default: undefined },
    schema: { type: Object, required: true },
    alertKey: { type: String, default: 'global' },
    errorBag: { type: Object, default: () => ({}) },
    fieldAttrs: { type: Object, default: () => ({}) },
    loading: Boolean,
    // The following two props control sent form data when values are empty and undefined
    // When default value is undefined data is removed request.
    submitEmptyValue: { default: undefined },
    submitUndefinedValue: { default: undefined },
    hideResetButton: { type: Boolean, default: true },
    autoValidate: { type: Boolean, default: true },
    hideFormAlerts: { type: Boolean },
    formAttrs: { type: Object, default: () => ({}) },
    rowAttrs: { type: Object, default: () => ({}) },
    colAttrs: { type: Object, default: () => ({}) },
    defaultCols: { type: Object, default: () => ({ cols: 12 }) },
    alertsAttrs: { type: Object, default: () => ({}) },
    resetBtnAttrs: { type: [Object, undefined], default: undefined },
    formActionsClass: { type: String, default: 'd-flex justify-end' },
    submitBtnAttrs: {
      type: Object,
      default: () => ({
        class: 'flex-grow-1 flex-md-grow-0',
        title: 'buttons.save',
        width: '140px',
        color: 'primary',
      }),
    },
  },
  emits: ['submit', 'reset', 'changed'],
  setup() {
    const validation = useValidation()
    return validation
  },
  data() {
    return {
      currentTab: 0,
      localModel: {},
    }
  },
  computed: {
    model: {
      get() {
        return this.modelValue || this.localModel
      },
      set(value) {
        this.modelValue
          ? this.$emit('update:modelValue', value)
          : (this.localModel = value)
      },
    },
    computedSubmitBtnAttrs() {
      return {
        ...this.submitBtnAttrs,
        title: this.$t(this.submitBtnAttrs.title),
      }
    },
    computedResetBtnAttrs() {
      return {
        ...this.resetBtnAttrs,
        title: this.$t(this.resetBtnAttrs.title),
      }
    },
  },
  methods: {
    getColsAttrs(field) {
      return {
        ...this.defaultCols,
        ...field.grid,
      }
    },
    async onSubmit(e) {
      if (this.autoValidate && !(await this.validate(e))) {
        return
      }
      // field.submitUndefinedValue and field.submitEmptyValue control sent data when field value are empty and undefined
      const field = (id) =>
        this.schema.fields.find(({ name }) => name === id) || {}
      const model = e.target.__vueComponent.items
        .map((item) => ({
          ...item,
          value:
            _get(
              this.model,
              item.id,
              field(item.id).hasOwnProperty('submitUndefinedValue')
                ? field(item.id).submitUndefinedValue
                : this.schema.submitUndefinedValue || this.submitUndefinedValue
            ) ||
            (field(item.id).hasOwnProperty('submitEmptyValue')
              ? field(item.id).submitEmptyValue
              : this.schema.submitEmptyValue || this.submitEmptyValue),
        }))
        .reduce((p, item) => ({ ...p, [item.id]: item.value }), {})
      this.$emit('submit', model, e.target.__vueComponent, this.model)
    },
    onReset() {
      this.$emit('reset')
    },
    resetValidation() {
      this.$refs.form.resetValidation()
    },
    // Helpers to proccess rules in string form. eg: required|min:5
    serelizeRules(field) {
      if (!field.rules) {
        return []
      }

      const rules = Array.isArray(field.rules)
        ? field.rules
        : field.rules.split('|')

      return rules
        .map((rule) => this.mapRuleToArguments(rule, field))
        .map((rule) => {
          return typeof rule.ruleName === 'function'
            ? rule.ruleName
            : this.rules[rule.ruleName].call(this, ...rule.args)
        })
    },
    mapRuleToArguments(rule, field) {
      const value = _get(this.model, field.name, '')
      return {
        ruleName:
          typeof rule === 'string' && rule.includes(':')
            ? rule.slice(0, rule.indexOf(':'))
            : rule,
        args: [
          value,
          ...(typeof rule === 'string' && rule.includes(':')
            ? rule.slice(rule.indexOf(':') + 1, rule.length).split(',')
            : []),
        ],
      }
    },
  },
}
</script>

<style></style>
