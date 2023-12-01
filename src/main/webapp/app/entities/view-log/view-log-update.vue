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
