<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.attachment.home.createOrEditLabel"
          data-cy="AttachmentCreateUpdateHeading"
          v-text="t$('digitalDinarApp.attachment.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="attachment.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="attachment.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.attachment.name')" for="attachment-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="attachment-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.attachment.details')" for="attachment-details"></label>
            <input
              type="text"
              class="form-control"
              name="details"
              id="attachment-details"
              data-cy="details"
              :class="{ valid: !v$.details.$invalid, invalid: v$.details.$invalid }"
              v-model="v$.details.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.attachment.notes')" for="attachment-notes"></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="attachment-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.attachment.attachmentType')"
              for="attachment-attachmentType"
            ></label>
            <select
              class="form-control"
              name="attachmentType"
              :class="{ valid: !v$.attachmentType.$invalid, invalid: v$.attachmentType.$invalid }"
              v-model="v$.attachmentType.$model"
              id="attachment-attachmentType"
              data-cy="attachmentType"
            >
              <option
                v-for="attachmentType in attachmentTypeValues"
                :key="attachmentType"
                v-bind:value="attachmentType"
                v-bind:label="t$('digitalDinarApp.AttachmentType.' + attachmentType)"
              >
                {{ attachmentType }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.attachment.file')" for="attachment-file"></label>
            <div>
              <div v-if="attachment.file" class="form-text text-danger clearfix">
                <a
                  class="pull-left"
                  v-on:click="openFile(attachment.fileContentType, attachment.file)"
                  v-text="t$('entity.action.open')"
                ></a
                ><br />
                <span class="pull-left">{{ attachment.fileContentType }}, {{ byteSize(attachment.file) }}</span>
                <button
                  type="button"
                  v-on:click="
                    attachment.file = null;
                    attachment.fileContentType = null;
                  "
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <label for="file_file" v-text="t$('entity.action.addblob')" class="btn btn-primary pull-right"></label>
              <input
                type="file"
                ref="file_file"
                id="file_file"
                style="display: none"
                data-cy="file"
                v-on:change="setFileData($event, attachment, 'file', false)"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="file"
              id="attachment-file"
              data-cy="file"
              :class="{ valid: !v$.file.$invalid, invalid: v$.file.$invalid }"
              v-model="v$.file.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="fileContentType"
              id="attachment-fileContentType"
              v-model="attachment.fileContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.attachment.fileUrl')" for="attachment-fileUrl"></label>
            <input
              type="text"
              class="form-control"
              name="fileUrl"
              id="attachment-fileUrl"
              data-cy="fileUrl"
              :class="{ valid: !v$.fileUrl.$invalid, invalid: v$.fileUrl.$invalid }"
              v-model="v$.fileUrl.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.attachment.referenceType')"
              for="attachment-referenceType"
            ></label>
            <select
              class="form-control"
              name="referenceType"
              :class="{ valid: !v$.referenceType.$invalid, invalid: v$.referenceType.$invalid }"
              v-model="v$.referenceType.$model"
              id="attachment-referenceType"
              data-cy="referenceType"
            >
              <option
                v-for="referenceType in referenceTypeValues"
                :key="referenceType"
                v-bind:value="referenceType"
                v-bind:label="t$('digitalDinarApp.ReferenceType.' + referenceType)"
              >
                {{ referenceType }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.attachment.referenceId')" for="attachment-referenceId"></label>
            <input
              type="number"
              class="form-control"
              name="referenceId"
              id="attachment-referenceId"
              data-cy="referenceId"
              :class="{ valid: !v$.referenceId.$invalid, invalid: v$.referenceId.$invalid }"
              v-model.number="v$.referenceId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.attachment.createdBy')" for="attachment-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="attachment-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.attachment.createdDate')" for="attachment-createdDate"></label>
            <div class="d-flex">
              <input
                id="attachment-createdDate"
                data-cy="createdDate"
                type="datetime-local"
                class="form-control"
                name="createdDate"
                :class="{ valid: !v$.createdDate.$invalid, invalid: v$.createdDate.$invalid }"
                :value="convertDateTimeFromServer(v$.createdDate.$model)"
                @change="updateInstantField('createdDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.attachment.lastModifiedBy')"
              for="attachment-lastModifiedBy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="attachment-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.attachment.lastModifiedDate')"
              for="attachment-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="attachment-lastModifiedDate"
                data-cy="lastModifiedDate"
                type="datetime-local"
                class="form-control"
                name="lastModifiedDate"
                :class="{ valid: !v$.lastModifiedDate.$invalid, invalid: v$.lastModifiedDate.$invalid }"
                :value="convertDateTimeFromServer(v$.lastModifiedDate.$model)"
                @change="updateInstantField('lastModifiedDate', $event)"
              />
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./attachment-update.component.ts"></script>
