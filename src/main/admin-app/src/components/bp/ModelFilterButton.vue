<template>
  <div class="bp-model-filter-button">
    <v-badge
      class="bp-model-filter-button--badge"
      :model-value="!!filterCount"
      :content="filterCount"
      color="black"
    >
      <BpBaseButton
        class="bp-model-filter-button--button"
        v-bind="{
          color: 'secondary',
          title: $t('buttons.filter'),
          menuAttrs: { closeOnContentClick: false },
          iconClass: toggle ? 'mdi-filter-off' : 'mdi-filter',
          disabled: !ormModel(props.entity).getState('filtersResponse'),
          onClick: () => (toggle = !toggle),
          ...props.btnAttrs,
        }"
      />
    </v-badge>
    <Teleport v-if="isMounted" :to="filtersContainer">
      <v-slide-y-transition hide-on-leave>
        <BpBaseCard
          v-show="toggle"
          class="bp-model-filter-button--card"
          v-bind="{
            color: 'white',
            overlayLoading:
              !ormModel(props.entity).getState('filtersResponse') &&
              ormModel(props.entity).loading('fetch'),
            class: 'elevation-default mb-5',
            minHeight: '130px',
            bodyClass: 'pa-2',
            ...props.cardAttrs,
          }"
        >
          <BpModelFilters
            v-bind="{
              model: ormModel(props.entity),
              rowAttrs: { class: 'ma-0' },
              footerClass: 'd-flex',
              submitBtnAttrs: { width: 140 },
              inputAttrs: { errors: props.errors },
              ...modelFiltersAttrs,
            }"
          />
        </BpBaseCard>
      </v-slide-y-transition>
    </Teleport>
  </div>
</template>

<script setup>
const props = defineProps({
  entity: { type: String, required: true },
  filtersContainer: { type: String, default: '#filters-container' },
  errors: { type: Object, default: () => ({}) },
  btnAttrs: { type: Object, default: () => ({}) },
  cardAttrs: { type: Object, default: () => ({}) },
  modelFiltersAttrs: { type: Object, default: () => ({}) },
})

const toggle = ref(false)
const isMounted = ref(false)
onMounted(() => {
  setTimeout(() => {
    isMounted.value = true
  }, 500)
})
const filterCount = computed(() => ormModel(props.entity).filtersCount?.() || 0)
</script>
