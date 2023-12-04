<template>
  <div>
    <h2 id="page-heading" data-cy="AttachmentHeading">
      <span v-text="t$('digitalDinarApp.attachment.home.title')" id="attachment-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.attachment.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'AttachmentCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-attachment"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.attachment.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && attachments && attachments.length === 0">
      <span v-text="t$('digitalDinarApp.attachment.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="attachments && attachments.length > 0">
      <table class="table table-striped" aria-describedby="attachments">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="t$('digitalDinarApp.attachment.name')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('details')">
              <span v-text="t$('digitalDinarApp.attachment.details')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'details'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('digitalDinarApp.attachment.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('attachmentType')">
              <span v-text="t$('digitalDinarApp.attachment.attachmentType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'attachmentType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('file')">
              <span v-text="t$('digitalDinarApp.attachment.file')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'file'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fileUrl')">
              <span v-text="t$('digitalDinarApp.attachment.fileUrl')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fileUrl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('referenceType')">
              <span v-text="t$('digitalDinarApp.attachment.referenceType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'referenceType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('referenceId')">
              <span v-text="t$('digitalDinarApp.attachment.referenceId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'referenceId'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="attachment in attachments" :key="attachment.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'AttachmentView', params: { attachmentId: attachment.id } }">{{ attachment.id }}</router-link>
            </td>
            <td>{{ attachment.name }}</td>
            <td>{{ attachment.details }}</td>
            <td>{{ attachment.notes }}</td>
            <td v-text="t$('digitalDinarApp.AttachmentType.' + attachment.attachmentType)"></td>
            <td>
              <a
                v-if="attachment.file"
                v-on:click="openFile(attachment.fileContentType, attachment.file)"
                v-text="t$('entity.action.open')"
              ></a>
              <span v-if="attachment.file">{{ attachment.fileContentType }}, {{ byteSize(attachment.file) }}</span>
            </td>
            <td>{{ attachment.fileUrl }}</td>
            <td v-text="t$('digitalDinarApp.ReferenceType.' + attachment.referenceType)"></td>
            <td>{{ attachment.referenceId }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'AttachmentView', params: { attachmentId: attachment.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'AttachmentEdit', params: { attachmentId: attachment.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(attachment)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="digitalDinarApp.attachment.delete.question"
          data-cy="attachmentDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-attachment-heading" v-text="t$('digitalDinarApp.attachment.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-attachment"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeAttachment()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="attachments && attachments.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./attachment.component.ts"></script>
