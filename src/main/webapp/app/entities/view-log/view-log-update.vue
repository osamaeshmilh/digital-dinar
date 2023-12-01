<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.viewLog.home.createOrEditLabel"
          data-cy="ViewLogCreateUpdateHeading"
          v-text="t$('digitalDinarApp.viewLog.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="viewLog.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="viewLog.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.viewLog.entityName')" for="view-log-entityName"></label>
            <input
              type="text"
              class="form-control"
              name="entityName"
              id="view-log-entityName"
              data-cy="entityName"
              :class="{ valid: !v$.entityName.$invalid, invalid: v$.entityName.$invalid }"
              v-model="v$.entityName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.viewLog.entityId')" for="view-log-entityId"></label>
            <input
              type="number"
              class="form-control"
              name="entityId"
              id="view-log-entityId"
              data-cy="entityId"
              :class="{ valid: !v$.entityId.$invalid, invalid: v$.entityId.$invalid }"
              v-model.number="v$.entityId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.viewLog.createdBy')" for="view-log-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="view-log-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.viewLog.createdDate')" for="view-log-createdDate"></label>
            <div class="d-flex">
              <input
                id="view-log-createdDate"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.viewLog.lastModifiedBy')" for="view-log-lastModifiedBy"></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="view-log-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.viewLog.lastModifiedDate')"
              for="view-log-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="view-log-lastModifiedDate"
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
<script lang="ts" src="./view-log-update.component.ts"></script>
