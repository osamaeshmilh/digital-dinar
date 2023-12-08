<template>
  <bp-base-input
    v-if="field.type === 'text'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{
      name: field.name,
      hideDetails: 'auto',
      errors,
      ...field.attrs,
      ...$attrs,
    }"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="data"
    >
      <slot :name="slot" v-bind="data" />
    </template>
  </bp-base-input>
  <bp-base-input
    v-else-if="field.type === 'password'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{
      name: field.name,
      password: true,
      hideDetails: 'auto',
      errors,
      ...field.attrs,
      ...$attrs,
    }"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="data"
    >
      <slot :name="slot" v-bind="data" />
    </template>
  </bp-base-input>
  <bp-base-textarea
    v-else-if="field.type === 'textarea'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{
      name: field.name,
      hideDetails: 'auto',
      errors,
      ...field.attrs,
      ...$attrs,
    }"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="data"
    >
      <slot :name="slot" v-bind="data" />
    </template>
  </bp-base-textarea>
  <bp-base-select
    v-else-if="field.type === 'select'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{
      name: field.name,
      items: getFieldOptions(field),
      hideDetails: 'auto',
      errors,
      ...field.attrs,
      ...$attrs,
    }"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="data"
    >
      <slot :name="slot" v-bind="data" />
    </template>
  </bp-base-select>
  <bp-base-autocomplete
    v-else-if="field.type === 'autocomplete'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{
      name: field.name,
      items: getFieldOptions(field),
      hideDetails: 'auto',
      errors,
      ...field.attrs,
      ...$attrs,
    }"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="data"
    >
      <slot :name="slot" v-bind="data" />
    </template>
  </bp-base-autocomplete>
  <bp-base-date-picker-input
    v-else-if="field.type === 'date'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{
      name: field.name,
      hideDetails: 'auto',
      errors,
      ...field.attrs,
      ...$attrs,
    }"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="data"
    >
      <slot :name="slot" v-bind="data" />
    </template>
  </bp-base-date-picker-input>
  <bp-base-time-picker-input
    v-else-if="field.type === 'time'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{
      name: field.name,
      hideDetails: 'auto',
      errors,
      ...field.attrs,
      ...$attrs,
    }"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="data"
    >
      <slot :name="slot" v-bind="data" />
    </template>
  </bp-base-time-picker-input>
  <v-radio-group
    v-else-if="field.type === 'radio'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{
      name: field.name,
      label: $t(`attributes.${field.name}`),
      hideDetails: 'auto',
      errorMessages: getError(field.name),
      ...field.attrs,
      ...$attrs,
    }"
  >
    <v-radio
      v-for="option in field.options"
      :key="JSON.stringify(option)"
      :label="option.title || option.text || option"
      :value="option.value"
    />
  </v-radio-group>
  <v-checkbox
    v-else-if="field.type === 'checkbox'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{
      name: field.name,
      label: $t(`attributes.${field.name}`),
      hideDetails: 'auto',
      error: (getError(field.name, errors) || []).length > 0,
      errorMessages: getError(field.name, errors),
      ...field.attrs,
      ...$attrs,
    }"
  >
    <template
      v-for="(slot, index) of Object.keys($slots)"
      :key="index"
      #[slot]="data"
    >
      <slot :name="slot" v-bind="data" />
    </template>
  </v-checkbox>
  <div
    v-else-if="field.type === 'header'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{ ...field.attrs, ...$attrs }"
  >
    <h2 v-if="field.title" v-bind="field.titleAttrs" v-text="field.title"></h2>
    <span
      v-if="field.subtitle"
      v-bind="field.subtitleAttrs"
      v-text="field.subtitle"
    ></span>
  </div>
  <v-divider
    v-else-if="field.type === 'divider'"
    :class="`bp-base-form-field bp-base-form-field-${field.type}`"
    v-bind="{ ...field.attrs, ...$attrs }"
  />
</template>

<script>
export default {
  props: {
    field: { type: Object, required: true },
    errors: { type: [Array, Object], required: true },
  },
  setup() {
    const { getError } = useValidation()
    return { getError }
  },
  data() {
    return { loading: false, options: [] }
  },
  methods: {
    getFieldOptions() {
      Promise.resolve(this.field.options).then((data) => (this.options = data))
      return this.options
    },
  },
}
</script>

<style></style>
