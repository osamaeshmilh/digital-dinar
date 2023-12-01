<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.beneficiary.home.createOrEditLabel"
          data-cy="BeneficiaryCreateUpdateHeading"
          v-text="t$('digitalDinarApp.beneficiary.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="beneficiary.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="beneficiary.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.beneficiary.arabicFirstName')"
              for="beneficiary-arabicFirstName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="arabicFirstName"
              id="beneficiary-arabicFirstName"
              data-cy="arabicFirstName"
              :class="{ valid: !v$.arabicFirstName.$invalid, invalid: v$.arabicFirstName.$invalid }"
              v-model="v$.arabicFirstName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.beneficiary.arabicLastName')"
              for="beneficiary-arabicLastName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="arabicLastName"
              id="beneficiary-arabicLastName"
              data-cy="arabicLastName"
              :class="{ valid: !v$.arabicLastName.$invalid, invalid: v$.arabicLastName.$invalid }"
              v-model="v$.arabicLastName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.beneficiary.englishFirstName')"
              for="beneficiary-englishFirstName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishFirstName"
              id="beneficiary-englishFirstName"
              data-cy="englishFirstName"
              :class="{ valid: !v$.englishFirstName.$invalid, invalid: v$.englishFirstName.$invalid }"
              v-model="v$.englishFirstName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.beneficiary.englishLastName')"
              for="beneficiary-englishLastName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishLastName"
              id="beneficiary-englishLastName"
              data-cy="englishLastName"
              :class="{ valid: !v$.englishLastName.$invalid, invalid: v$.englishLastName.$invalid }"
              v-model="v$.englishLastName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.beneficiary.idType')" for="beneficiary-idType"></label>
            <select
              class="form-control"
              name="idType"
              :class="{ valid: !v$.idType.$invalid, invalid: v$.idType.$invalid }"
              v-model="v$.idType.$model"
              id="beneficiary-idType"
              data-cy="idType"
            >
              <option
                v-for="idType in idTypeValues"
                :key="idType"
                v-bind:value="idType"
                v-bind:label="t$('digitalDinarApp.IdType.' + idType)"
              >
                {{ idType }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.beneficiary.idNo')" for="beneficiary-idNo"></label>
            <input
              type="text"
              class="form-control"
              name="idNo"
              id="beneficiary-idNo"
              data-cy="idNo"
              :class="{ valid: !v$.idNo.$invalid, invalid: v$.idNo.$invalid }"
              v-model="v$.idNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.beneficiary.mobileNo')" for="beneficiary-mobileNo"></label>
            <input
              type="text"
              class="form-control"
              name="mobileNo"
              id="beneficiary-mobileNo"
              data-cy="mobileNo"
              :class="{ valid: !v$.mobileNo.$invalid, invalid: v$.mobileNo.$invalid }"
              v-model="v$.mobileNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.beneficiary.email')" for="beneficiary-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="beneficiary-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.beneficiary.notes')" for="beneficiary-notes"></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="beneficiary-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.beneficiary.createdByUserId')"
              for="beneficiary-createdByUserId"
            ></label>
            <input
              type="number"
              class="form-control"
              name="createdByUserId"
              id="beneficiary-createdByUserId"
              data-cy="createdByUserId"
              :class="{ valid: !v$.createdByUserId.$invalid, invalid: v$.createdByUserId.$invalid }"
              v-model.number="v$.createdByUserId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.beneficiary.isVerified')" for="beneficiary-isVerified"></label>
            <input
              type="checkbox"
              class="form-check"
              name="isVerified"
              id="beneficiary-isVerified"
              data-cy="isVerified"
              :class="{ valid: !v$.isVerified.$invalid, invalid: v$.isVerified.$invalid }"
              v-model="v$.isVerified.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.beneficiary.hasTransferred')"
              for="beneficiary-hasTransferred"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="hasTransferred"
              id="beneficiary-hasTransferred"
              data-cy="hasTransferred"
              :class="{ valid: !v$.hasTransferred.$invalid, invalid: v$.hasTransferred.$invalid }"
              v-model="v$.hasTransferred.$model"
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
<script lang="ts" src="./beneficiary-update.component.ts"></script>
