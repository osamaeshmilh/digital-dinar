<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.country.home.createOrEditLabel"
          data-cy="CountryCreateUpdateHeading"
          v-text="t$('digitalDinarApp.country.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="country.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="country.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.country.nameAr')" for="country-nameAr"></label>
            <input
              type="text"
              class="form-control"
              name="nameAr"
              id="country-nameAr"
              data-cy="nameAr"
              :class="{ valid: !v$.nameAr.$invalid, invalid: v$.nameAr.$invalid }"
              v-model="v$.nameAr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.country.nameEn')" for="country-nameEn"></label>
            <input
              type="text"
              class="form-control"
              name="nameEn"
              id="country-nameEn"
              data-cy="nameEn"
              :class="{ valid: !v$.nameEn.$invalid, invalid: v$.nameEn.$invalid }"
              v-model="v$.nameEn.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.country.iso2')" for="country-iso2"></label>
            <input
              type="text"
              class="form-control"
              name="iso2"
              id="country-iso2"
              data-cy="iso2"
              :class="{ valid: !v$.iso2.$invalid, invalid: v$.iso2.$invalid }"
              v-model="v$.iso2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.country.iso3')" for="country-iso3"></label>
            <input
              type="text"
              class="form-control"
              name="iso3"
              id="country-iso3"
              data-cy="iso3"
              :class="{ valid: !v$.iso3.$invalid, invalid: v$.iso3.$invalid }"
              v-model="v$.iso3.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.country.isoNo')" for="country-isoNo"></label>
            <input
              type="number"
              class="form-control"
              name="isoNo"
              id="country-isoNo"
              data-cy="isoNo"
              :class="{ valid: !v$.isoNo.$invalid, invalid: v$.isoNo.$invalid }"
              v-model.number="v$.isoNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.country.createdBy')" for="country-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="country-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.country.createdDate')" for="country-createdDate"></label>
            <div class="d-flex">
              <input
                id="country-createdDate"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.country.lastModifiedBy')" for="country-lastModifiedBy"></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="country-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.country.lastModifiedDate')"
              for="country-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="country-lastModifiedDate"
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
<script lang="ts" src="./country-update.component.ts"></script>
