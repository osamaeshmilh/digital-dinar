<template>
  <v-list class="bp-base-data-list" v-bind="$attrs">
    <slot name="prepend" />
    <template v-if="Array.isArray(data)">
      <v-list-item
        v-for="(item, i) in data"
        v-show="hideEmpty ? arrayItemValue(item) : true"
        :key="`item-${arrayItemTitle(item)}-${i}`"
        v-bind="{ border: 'b', ...itemAttrs }"
      >
        <template #prepend>
          <slot
            :name="`item.${arrayItemKey(item)}.prepend`"
            v-bind="arrayItemSlotData(item)"
          />
        </template>
        <slot
          :name="`item.${arrayItemKey(item)}`"
          v-bind="arrayItemSlotData(item)"
        >
          <slot
            :name="`item.${arrayItemKey(item)}.title`"
            v-bind="arrayItemSlotData(item)"
          >
            <v-list-item-subtitle
              :style="'flex-basis:' + titleWidth + ' !important'"
              :class="titleClass"
              v-text="arrayItemTitle(item)"
            />
          </slot>
          <slot
            :name="`item.${arrayItemKey(item)}.value`"
            v-bind="arrayItemSlotData(item)"
          >
            <slot name="item-value" v-bind="arrayItemSlotData(item)">
              <v-list-item-title
                :style="
                  valueWidth ? 'flex-basis:' + valueWidth + ' !important' : ''
                "
                :class="valueClass"
                v-text="arrayItemValue(item)"
              />
            </slot>
          </slot>
        </slot>
        <template #append>
          <slot
            :name="`item.${arrayItemKey(item)}.append`"
            v-bind="arrayItemSlotData(item)"
          />
        </template>
      </v-list-item>
    </template>
    <template
      v-else-if="data && typeof data === 'object' && Array.isArray(details)"
    >
      <v-list-item
        v-for="(key, i) in details"
        v-show="hideEmpty ? _get(data, key) : true"
        :key="`item-${key}-${i}`"
        v-bind="{ border: 'b', ...itemAttrs }"
      >
        <template #prepend>
          <slot
            :name="`item.${key}.prepend`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          />
        </template>
        <slot
          :name="`item.${key}`"
          v-bind="
            objectSlotData({
              rawKey: key,
              key: `attributes.${key}`,
              value: key,
            })
          "
        >
          <slot
            :name="`item.${key}.title`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          >
            <v-list-item-subtitle
              :style="'flex-basis:' + titleWidth + ' !important'"
              :class="titleClass"
              v-text="$t('attributes.' + key)"
            />
          </slot>
          <slot
            :name="`item.${key}.value`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          >
            <slot
              name="item-value"
              v-bind="
                objectSlotData({
                  rawKey: key,
                  key: `attributes.${key}`,
                  value: key,
                })
              "
            >
              <v-list-item-title
                :style="
                  valueWidth ? 'flex-basis:' + valueWidth + ' !important' : ''
                "
                :class="valueClass"
                v-text="_get(data, key, '-')"
              />
            </slot>
          </slot>
        </slot>
        <template #append>
          <slot
            :name="`item.${key}.append`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          />
        </template>
      </v-list-item>
    </template>
    <template
      v-else-if="
        data &&
        typeof data === 'object' &&
        typeof details === 'object' &&
        Object.keys(details).length
      "
    >
      <v-list-item
        v-for="(value, key, i) in details"
        v-show="hideEmpty ? _get(data, value) : true"
        :key="`item-${key}-${i}`"
        v-bind="{ border: 'b', ...itemAttrs }"
      >
        <template #prepend>
          <slot
            :name="`item.${key}.prepend`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          />
        </template>
        <slot
          :name="`item.${key}`"
          v-bind="{
            title: $t('attributes.' + key),
            value: _get(data, key, '-'),
          }"
        >
          <slot
            :name="`item.${key}.title`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          >
            <v-list-item-subtitle
              :style="'flex-basis:' + titleWidth + ' !important'"
              :class="titleClass"
              v-text="$t(`attributes.${key}`)"
            />
          </slot>
          <slot
            :name="`item.${key}.value`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          >
            <slot
              name="item-value"
              v-bind="
                objectSlotData({
                  rawKey: key,
                  key: `attributes.${key}`,
                  value: key,
                })
              "
            >
              <v-list-item-title
                :style="
                  valueWidth ? 'flex-basis:' + valueWidth + ' !important' : ''
                "
                :class="valueClass"
                v-text="_get(data, value, '-')"
              />
            </slot>
          </slot>
        </slot>
        <template #append>
          <slot
            :name="`item.${key}.append`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          />
        </template>
      </v-list-item>
    </template>
    <template v-else-if="typeof data === 'object' && Object.keys(data).length">
      <v-list-item
        v-for="(value, key, i) in data"
        v-show="hideEmpty ? _get(data, key) : true"
        :key="`item-${key}-${i}`"
        v-bind="{ border: 'b', ...itemAttrs }"
      >
        <template #prepend>
          <slot
            :name="`item.${key}.prepend`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          />
        </template>
        <slot
          :name="`item.${key}`"
          v-bind="{
            title: $t('attributes.' + key),
            value: _get(data, key, '-'),
          }"
        >
          <slot
            :name="`item.${key}.title`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          >
            <v-list-item-subtitle
              :style="'flex-basis:' + titleWidth + ' !important'"
              :class="titleClass"
              v-text="$t(`attributes.${key}`)"
            />
          </slot>
          <slot
            :name="`item.${key}.value`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          >
            <slot
              name="item-value"
              v-bind="
                objectSlotData({
                  rawKey: key,
                  key: `attributes.${key}`,
                  value: key,
                })
              "
            >
              <v-list-item-title
                :style="
                  valueWidth ? 'flex-basis:' + valueWidth + ' !important' : ''
                "
                :class="valueClass"
                v-text="_get(data, key, '-')"
              />
            </slot>
          </slot>
        </slot>
        <template #append>
          <slot
            :name="`item.${key}.append`"
            v-bind="
              objectSlotData({
                rawKey: key,
                key: `attributes.${key}`,
                value: key,
              })
            "
          />
        </template>
      </v-list-item>
    </template>
    <slot name="append" />
  </v-list>
</template>

<script>
import _get from 'lodash/get'
export default {
  props: {
    data: {
      type: [Object, Array],
      default: () => ({}),
    },
    details: {
      type: [Object, Array],
      default: () => ({}),
    },
    itemAttrs: {
      type: Object,
      default: () => ({}),
    },
    valueClass: {
      type: String,
      default: '',
    },
    titleClass: {
      type: String,
      default: '',
    },
    itemTitle: {
      type: String,
      default: 'title',
    },
    itemValue: {
      type: String,
      default: 'value',
    },
    itemKey: {
      type: String,
      default: '',
    },
    titleWidth: {
      type: String,
      default: '156px',
    },
    valueWidth: {
      type: String,
      default: '328px',
    },
    hideEmpty: Boolean,
  },
  methods: {
    arrayItemKey(item) {
      return this.itemKey
        ? _get(item, this.itemKey)
        : _get(item, this.itemTitle, '-')
    },
    arrayItemTitle(item) {
      return _get(item, this.itemTitle, '-')
    },
    arrayItemValue(item) {
      return _get(item, this.itemValue, '-')
    },
    arrayItemSlotData(item) {
      return {
        title: _get(item, this.itemTitle, '-'),
        value: _get(item, this.itemValue, '-'),
      }
    },
    objectSlotData(item) {
      return {
        key: item.rawKey,
        title: this.$t(item.key),
        value: _get(this.data, item.value, '-'),
      }
    },
    _get,
  },
}
</script>

<style></style>
