<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.category.home.createOrEditLabel"
          data-cy="CategoryCreateUpdateHeading"
          v-text="t$('digitalDinarApp.category.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="category.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="category.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.category.nameAr')" for="category-nameAr"></label>
            <input
              type="text"
              class="form-control"
              name="nameAr"
              id="category-nameAr"
              data-cy="nameAr"
              :class="{ valid: !v$.nameAr.$invalid, invalid: v$.nameAr.$invalid }"
              v-model="v$.nameAr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.category.nameEn')" for="category-nameEn"></label>
            <input
              type="text"
              class="form-control"
              name="nameEn"
              id="category-nameEn"
              data-cy="nameEn"
              :class="{ valid: !v$.nameEn.$invalid, invalid: v$.nameEn.$invalid }"
              v-model="v$.nameEn.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.category.code')" for="category-code"></label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="category-code"
              data-cy="code"
              :class="{ valid: !v$.code.$invalid, invalid: v$.code.$invalid }"
              v-model="v$.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.category.createdBy')" for="category-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="category-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.category.createdDate')" for="category-createdDate"></label>
            <div class="d-flex">
              <input
                id="category-createdDate"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.category.lastModifiedBy')" for="category-lastModifiedBy"></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="category-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.category.lastModifiedDate')"
              for="category-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="category-lastModifiedDate"
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
<script lang="ts" src="./category-update.component.ts"></script>
