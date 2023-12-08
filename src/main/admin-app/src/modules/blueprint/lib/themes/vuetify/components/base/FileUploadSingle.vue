<script>
import _get from 'lodash/get'
export default {
  props: {
    name: { type: String, default: '' },
    label: { type: [String, Boolean], default: '' },
    errors: { type: [Object, Array, String], default: () => [] },
    accept: { type: String, default: '' },
    modelValue: { type: [String, FileList], default: '' },
    existingFile: { type: [String, Array, Object], default: '' },
    existingFileName: { type: String, default: '' },
    existingFileUrl: { type: String, default: '' },
    avatarAttrs: { type: Object, default: () => ({}) },
    cardAttrs: { type: Object, default: () => ({}) },
    previewDialogAttrs: { type: Object, default: () => ({}) },
    htmlInputAttrs: { type: Object, default: () => ({}) },
    image: Boolean,
    multiple: Boolean,
  },
  setup() {
    const { getError } = useValidation()
    return { getError }
  },
  data() {
    return {
      uploadedFile: '',
      dragover: false,
      preview: '',
      dialog: false,
    }
  },
  methods: {
    removeExistingFile() {
      this.$emit('update:existingFile', '')
      this.$emit('update:modelValue', '_delete')
    },
    onClick() {
      this.$refs.upload.click()
    },
    onFileChange(e) {
      const files = e.target.files
      if (!files) return
      this.setUploadedFile(files)
    },
    setUploadedFile(file) {
      this.uploadedFile = file && file.length ? file : ''
      this.$emit('update:modelValue', this.uploadedFile)
    },
    onDrop(e) {
      this.removeFile()
      const files = e.dataTransfer.files
      this.setUploadedFile(files)
    },
    removeFile() {
      this.uploadedFile = ''
      this.removeAllFromInput()
      this.$emit('update:modelValue', this.uploadedFile)
      this.$emit('update:errors', {})
    },
    removeAllFromInput() {
      this.$refs.upload.files = new DataTransfer().files
    },
    isFileImage(file) {
      return file && file.type.split('/')[0] === 'image'
    },
    getPreviewImage(file) {
      return typeof file === 'string' ? file : URL.createObjectURL(file)
    },
    onPreviewImage(image) {
      this.preview = image
      this.dialog = true
    },
    _get,
  },
}
</script>

<template>
  <div class="bp-base-file-upload-single">
    <v-label
      class="mb-1 bp-base-file-upload-single--label"
      v-text="
        label ? label : name && label !== false ? $t(`attributes.${name}`) : ''
      "
    />
    <v-card
      class="bp-base-file-upload-single--card"
      v-bind="{
        border: true,
        flat: true,
        ripple: false,
        class: 'border-dashed',
        ...cardAttrs,
        ...(!existingFile && !uploadedFile ? { onClick } : {}),
      }"
      @drop.prevent="onDrop($event)"
      @dragover.prevent="dragover = true"
      @dragenter.prevent="dragover = true"
      @dragleave.prevent="dragover = false"
    >
      <input
        v-show="false"
        ref="upload"
        v-bind="{ type: 'file', accept, ...htmlInputAttrs }"
        @input="onFileChange($event)"
      />
      <!-- Upload UI -->
      <div
        v-if="!uploadedFile && !existingFile"
        class="align-center d-flex flex-column pa-5"
      >
        <span v-text="$t(`labels.uploadFiles`)" />
        <span
          class="fs-0_8 text-medium-emphasis font-weight-light"
          v-text="accept"
        ></span>
        <BpBaseButton
          v-bind="{
            variant: 'text',
            border: true,
            rounded: true,
            class: 'mt-2',
            title: $t('buttons.upload'),
            iconClass: 'mdi-upload',
          }"
          @click.stop="onClick"
        />
      </div>

      <!-- Preview Files UI -->
      <template v-if="uploadedFile || existingFile">
        <!-- Existing file UI -->
        <v-list v-if="existingFile">
          <v-list-item>
            <template #prepend>
              <v-avatar v-bind="{ color: 'grey-lighten-4', ...avatarAttrs }">
                <v-img
                  v-if="
                    typeof existingFile === 'object' &&
                    _get(existingFile, existingFileName, '').match(
                      /.(jpg|jpeg|png|gif|.svg)$/i
                    )
                  "
                  :src="_get(existingFile, existingFileUrl, '')"
                  eager
                  class="cursor-pointer"
                  cover
                  @click="
                    onPreviewImage(_get(existingFile, existingFileUrl, ''))
                  "
                />
                <v-img
                  v-else-if="
                    typeof existingFile === 'string' &&
                    existingFile.match(/.(jpg|jpeg|png|gif|.svg)$/i)
                  "
                  :src="existingFile"
                  eager
                  class="cursor-pointer"
                  cover
                  @click="onPreviewImage(existingFile)"
                />
                <v-icon v-else size="24"> mdi-attachment </v-icon>
              </v-avatar>
            </template>
            <v-list-item-title
              v-text="_get(existingFile, existingFileName, existingFile)"
            />
            <template #append>
              <BpBaseButton
                v-bind="{
                  icon: true,
                  iconClass: 'mdi-close',
                  variant: 'text',
                  color: 'error',
                  density: 'compact',
                }"
                @click.stop="removeExistingFile(existingFile)"
              />
            </template>
          </v-list-item>
        </v-list>
        <!-- Uploaded file UI -->
        <v-list v-else-if="uploadedFile.length">
          <v-list-item
            v-for="(file, index) in uploadedFile"
            :key="`uploaded-file-${index}`"
            border="b"
          >
            <template #prepend>
              <v-avatar
                v-if="file"
                v-bind="{ color: 'grey-lighten-4', ...avatarAttrs }"
              >
                <v-img
                  v-if="isFileImage(file)"
                  :src="getPreviewImage(file)"
                  eager
                  class="cursor-pointer"
                  cover
                  @click="onPreviewImage(file)"
                />
                <v-icon v-else size="24" icon="mdi-attachment" />
              </v-avatar>
            </template>
            <v-list-item-title v-text="file.name" />
            <v-list-item-subtitle v-text="formatFileSize(file.size)" />
            <template #append>
              <BpBaseButton
                v-bind="{
                  icon: true,
                  iconClass: 'mdi-close',
                  variant: 'text',
                  color: 'error',
                  density: 'compact',
                }"
                @click.stop="removeFile(file.name)"
              />
            </template>
          </v-list-item>
        </v-list>
      </template>

      <!-- Preview Dialog -->
      <BpBaseDialog
        v-model="dialog"
        v-bind="{
          width: '500px',
          cardAttrs: { noPadding: true },
          persistent: false,
          ...previewDialogAttrs,
        }"
      >
        <img
          :src="getPreviewImage(preview)"
          class="mx-auto"
          style="
            height: auto;
            width: 100%;
            max-height: 400px;
            object-fit: contain;
          "
        />
        <div
          v-if="preview.name"
          class="fs-0_9 text-center"
          v-text="preview.name"
        />
      </BpBaseDialog>
    </v-card>
    <v-input
      v-model="uploadedFile"
      v-bind="{
        name,
        class: 'mx-2',
        errorMessages:
          name && typeof errors === 'object'
            ? getError(name, errors)
            : Array.isArray(errors)
            ? errors
            : [],
        ...$attrs,
      }"
    >
    </v-input>
  </div>
</template>
