<template>
  <BpBaseDialog
    class="bp-dialog-preview"
    v-bind="{ cardAttrs: { noPadding: true }, ...$attrs }"
  >
    <slot v-bind="{ data, details }">
      <BpBaseDataList
        v-bind="{
          data: props.data,
          details: props.details,
          ...props.listAttrs,
        }"
      >
        <template
          v-for="(slot, index) of Object.keys($slots)"
          :key="index"
          #[slot]="data"
        >
          <slot :name="slot" v-bind="data"></slot>
        </template>
      </BpBaseDataList>
    </slot>
  </BpBaseDialog>
</template>

<script setup>
const props = defineProps({
  data: { type: [Object, Array], default: () => ({}) },
  details: { type: [Object, Array], default: () => ({}) },
  listAttrs: { type: Object, default: () => ({}) },
})
</script>
