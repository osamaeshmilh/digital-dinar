<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.setting.home.createOrEditLabel"
          data-cy="SettingCreateUpdateHeading"
          v-text="t$('digitalDinarApp.setting.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="setting.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="setting.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.setting.key')" for="setting-key"></label>
            <input
              type="text"
              class="form-control"
              name="key"
              id="setting-key"
              data-cy="key"
              :class="{ valid: !v$.key.$invalid, invalid: v$.key.$invalid }"
              v-model="v$.key.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.setting.value')" for="setting-value"></label>
            <input
              type="text"
              class="form-control"
              name="value"
              id="setting-value"
              data-cy="value"
              :class="{ valid: !v$.value.$invalid, invalid: v$.value.$invalid }"
              v-model="v$.value.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.setting.createdBy')" for="setting-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="setting-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.setting.createdDate')" for="setting-createdDate"></label>
            <div class="d-flex">
              <input
                id="setting-createdDate"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.setting.lastModifiedBy')" for="setting-lastModifiedBy"></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="setting-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.setting.lastModifiedDate')"
              for="setting-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="setting-lastModifiedDate"
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
<script lang="ts" src="./setting-update.component.ts"></script>
