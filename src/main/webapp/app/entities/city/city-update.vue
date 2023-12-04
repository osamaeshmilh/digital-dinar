<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.city.home.createOrEditLabel"
          data-cy="CityCreateUpdateHeading"
          v-text="t$('digitalDinarApp.city.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="city.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="city.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.city.nameAr')" for="city-nameAr"></label>
            <input
              type="text"
              class="form-control"
              name="nameAr"
              id="city-nameAr"
              data-cy="nameAr"
              :class="{ valid: !v$.nameAr.$invalid, invalid: v$.nameAr.$invalid }"
              v-model="v$.nameAr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.city.nameEn')" for="city-nameEn"></label>
            <input
              type="text"
              class="form-control"
              name="nameEn"
              id="city-nameEn"
              data-cy="nameEn"
              :class="{ valid: !v$.nameEn.$invalid, invalid: v$.nameEn.$invalid }"
              v-model="v$.nameEn.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.city.country')" for="city-country"></label>
            <select class="form-control" id="city-country" data-cy="country" name="country" v-model="city.country">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="city.country && countryOption.id === city.country.id ? city.country : countryOption"
                v-for="countryOption in countries"
                :key="countryOption.id"
              >
                {{ countryOption.nameAr }}
              </option>
            </select>
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
<script lang="ts" src="./city-update.component.ts"></script>
