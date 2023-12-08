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
          disabled: !props.model.getState('filtersResponse'),
          onClick: () => !props.menu && (toggle = !toggle),
          ...props.btnAttrs,
        }"
      >
        <template #prepend>
          <v-icon :icon="toggle ? 'mdi-filter-off' : 'mdi-filter'" />
        </template>
        {{ $t('buttons.filter') }}
        <v-menu
          v-if="props.menu"
          v-model="toggle"
          activator="parent"
          v-bind="{
            closeOnContentClick: false,
            ...menuAttrs,
          }"
        >
          <BpBaseCard
            class="bp-model-filter-button--card"
            v-bind="{
              overlayLoading:
                !props.model.getState('filtersResponse') &&
                props.model.loading('fetch'),
              class: 'elevation-default mb-5',
              minHeight: '130px',
              bodyClass: 'pa-2',
              ...props.cardAttrs,
            }"
          >
            <BpModelFilters
              v-bind="{
                model: props.model,
                rowAttrs: { class: 'ma-0' },
                footerClass: 'd-flex',
                submitBtnAttrs: { width: 140 },
                inputAttrs: { errors: props.errors },
                ...modelFiltersAttrs,
              }"
            />
          </BpBaseCard>
        </v-menu>
      </BpBaseButton>
    </v-badge>
    <Teleport v-if="isMounted && !props.menu" :to="filtersContainer">
      <v-slide-y-transition>
        <BpBaseCard
          v-if="toggle"
          class="bp-model-filter-button--card"
          v-bind="{
            overlayLoading:
              !props.model.getState('filtersResponse') &&
              props.model.loading('fetch'),
            class: 'elevation-default mb-5',
            minHeight: '130px',
            bodyClass: 'pa-2',
            ...props.cardAttrs,
          }"
        >
          <BpModelFilters
            v-bind="{
              model: props.model,
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
  model: { type: Function, required: true },
  filtersContainer: { type: String, default: '#filters-container' },
  errors: { type: Object, default: () => ({}) },
  btnAttrs: { type: Object, default: () => ({}) },
  cardAttrs: { type: Object, default: () => ({}) },
  menuAttrs: { type: Object, default: () => ({}) },
  modelFiltersAttrs: { type: Object, default: () => ({}) },
  menu: Boolean,
})

const toggle = ref(false)
const isMounted = ref(false)
onMounted(() => {
  setTimeout(() => {
    isMounted.value = true
  }, 500)
})
const filterCount = computed(() => props.model.filtersCount?.() || 0)
</script>
