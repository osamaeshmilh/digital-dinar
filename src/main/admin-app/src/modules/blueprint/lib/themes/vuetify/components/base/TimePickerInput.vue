<template>
  <v-menu
    v-model="menu"
    class="bp-base-time-picker-input--menu"
    v-bind="{
      minWidth: '1px',
      maxWidth: '210px',
      openOnFocus: true,
      openOnClick: false,
      openDelay: 0,
      closeOnContentClick: false,
      eager: true,
    }"
    @mousedown.prevent
  >
    <template #activator="{ props }">
      <BpBaseInput
        ref="textField"
        class="bp-base-time-picker-input"
        :model-value="modelValue"
        autocomplete="off"
        readonly
        :class="inputClass"
        v-bind="{ ...$attrs, ...props }"
      />
    </template>

    <v-card dir="ltr" class="d-flex bp-base-time-picker-input--card">
      <div>
        <v-list-subheader
          class="pa-0 justify-center bp-base-time-picker-input--label"
          density="compact"
          v-text="$t('labels.hour')"
        />
        <v-list
          ref="hoursContainer"
          v-model="hours"
          color="primary"
          :mandatory="!!modelValue"
          class="bp-base-time-picker-input--list"
          max-height="140px"
          width="100px"
          density="compact"
          :selected="[hours]"
          @update:selected="hours = $event[0]"
        >
          <v-list-item
            v-for="n in 24"
            :key="'hour-' + n"
            class="text-center bp-base-time-picker-input--list-item"
            density="compact"
            :value="zeroPad(n - 1, 2)"
          >
            <v-list-item-title
              class="text-black fs-1"
              v-text="zeroPad(n - 1, 2)"
            />
          </v-list-item>
        </v-list>
      </div>
      <div>
        <v-list-subheader
          class="pa-0 justify-center bp-base-time-picker-input--label"
          v-text="$t('labels.minute')"
        />
        <v-list
          ref="minutesContainer"
          color="primary"
          :mandatory="!!modelValue"
          max-height="140px"
          width="100px"
          class="bp-base-time-picker-input--list"
          density="compact"
          :selected="[minutes]"
          @update:selected="minutes = $event[0]"
        >
          <v-list-item
            v-for="n in 60"
            :key="'hour-' + n"
            class="text-center bp-base-time-picker-input--list-item"
            density="compact"
            :value="zeroPad(n - 1, 2)"
          >
            <v-list-item-title
              class="text-black fs-1 bp-base-time-picker-input--list-item-title"
              v-text="zeroPad(n - 1, 2)"
            />
          </v-list-item>
        </v-list>
      </div>
    </v-card>
  </v-menu>
</template>

<script>
export default {
  inheritAttrs: false,
  props: {
    modelValue: { type: String, default: '' },
    inputClass: { type: String, default: '' },
  },
  data() {
    return {
      menu: false,
    }
  },
  computed: {
    hours: {
      get() {
        return this.modelValue ? this.modelValue.slice(0, 2) || '00' : ''
      },
      set(hours) {
        const minutes = this.modelValue ? this.modelValue.slice(-2) || '00' : ''
        if (hours) {
          if (minutes) {
            this.$emit('update:modelValue', `${hours}:${minutes}`)
          } else {
            this.$emit('update:modelValue', `${hours}:00`)
          }
        } else {
          const minutes = this.modelValue
            ? this.modelValue.slice(-2) || '00'
            : ''
          if (minutes) {
            this.$emit('update:modelValue', `00:${minutes}`)
          }
        }
      },
    },
    minutes: {
      get() {
        return this.modelValue ? this.modelValue.slice(-2) || '00' : ''
      },
      set(minutes) {
        const hours = this.modelValue ? this.modelValue.slice(0, 2) : '00'
        if (minutes) {
          if (hours) {
            this.$emit('update:modelValue', `${hours}:${minutes}`)
          } else {
            this.$emit('update:modelValue', `00:${minutes}`)
          }
        } else if (hours) {
          this.$emit('update:modelValue', `${hours}:00`)
        }
      },
    },
  },
  watch: {
    menu: {
      handler(value) {
        if (this.$refs.textField?.$children?.length) {
          !value && this.$refs.textField.$children[0].blur()
        }
        setTimeout(
          () => {
            this.scrollToSelected(this.$refs.minutesContainer.$el)
          },
          value ? 50 : 300
        )
        setTimeout(
          () => {
            this.scrollToSelected(this.$refs.hoursContainer.$el)
          },
          value ? 50 : 300
        )
      },
      immediate: true,
    },
  },
  methods: {
    zeroPad: (num, places) => String(num).padStart(places, '0'),
    scrollToSelected(el) {
      if (!el) {
        return
      }
      const childElement = el.getElementsByClassName('v-list-item--active')

      if (childElement[0]) {
        childElement[0].scrollIntoView()
      }
    },
  },
}
</script>
