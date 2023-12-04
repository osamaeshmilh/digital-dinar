<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.notification.home.createOrEditLabel"
          data-cy="NotificationCreateUpdateHeading"
          v-text="t$('digitalDinarApp.notification.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="notification.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="notification.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.notification.title')" for="notification-title"></label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="notification-title"
              data-cy="title"
              :class="{ valid: !v$.title.$invalid, invalid: v$.title.$invalid }"
              v-model="v$.title.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.notification.description')"
              for="notification-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="notification-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.notification.userId')" for="notification-userId"></label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="notification-userId"
              data-cy="userId"
              :class="{ valid: !v$.userId.$invalid, invalid: v$.userId.$invalid }"
              v-model.number="v$.userId.$model"
            />
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
<script lang="ts" src="./notification-update.component.ts"></script>
