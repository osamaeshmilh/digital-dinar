<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="attachment">
        <h2 class="jh-entity-heading" data-cy="attachmentDetailsHeading">
          <span v-text="t$('digitalDinarApp.attachment.detail.title')"></span> {{ attachment.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.name')"></span>
          </dt>
          <dd>
            <span>{{ attachment.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.details')"></span>
          </dt>
          <dd>
            <span>{{ attachment.details }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.notes')"></span>
          </dt>
          <dd>
            <span>{{ attachment.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.attachmentType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.AttachmentType.' + attachment.attachmentType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.file')"></span>
          </dt>
          <dd>
            <div v-if="attachment.file">
              <a v-on:click="openFile(attachment.fileContentType, attachment.file)" v-text="t$('entity.action.open')"></a>
              {{ attachment.fileContentType }}, {{ byteSize(attachment.file) }}
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.fileUrl')"></span>
          </dt>
          <dd>
            <span>{{ attachment.fileUrl }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.referenceType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.ReferenceType.' + attachment.referenceType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.referenceId')"></span>
          </dt>
          <dd>
            <span>{{ attachment.referenceId }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ attachment.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="attachment.createdDate">{{ formatDateLong(attachment.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ attachment.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.attachment.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="attachment.lastModifiedDate">{{ formatDateLong(attachment.lastModifiedDate) }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="attachment.id"
          :to="{ name: 'AttachmentEdit', params: { attachmentId: attachment.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./attachment-details.component.ts"></script>
