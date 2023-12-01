<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.activation.home.createOrEditLabel"
          data-cy="ActivationCreateUpdateHeading"
          v-text="t$('digitalDinarApp.activation.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="activation.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="activation.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.activation.mobileNo')" for="activation-mobileNo"></label>
            <input
              type="text"
              class="form-control"
              name="mobileNo"
              id="activation-mobileNo"
              data-cy="mobileNo"
              :class="{ valid: !v$.mobileNo.$invalid, invalid: v$.mobileNo.$invalid }"
              v-model="v$.mobileNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.activation.email')" for="activation-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="activation-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.activation.code')" for="activation-code"></label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="activation-code"
              data-cy="code"
              :class="{ valid: !v$.code.$invalid, invalid: v$.code.$invalid }"
              v-model="v$.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.activation.sentOn')" for="activation-sentOn"></label>
            <div class="d-flex">
              <input
                id="activation-sentOn"
                data-cy="sentOn"
                type="datetime-local"
                class="form-control"
                name="sentOn"
                :class="{ valid: !v$.sentOn.$invalid, invalid: v$.sentOn.$invalid }"
                :value="convertDateTimeFromServer(v$.sentOn.$model)"
                @change="updateInstantField('sentOn', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.activation.validUntil')" for="activation-validUntil"></label>
            <div class="d-flex">
              <input
                id="activation-validUntil"
                data-cy="validUntil"
                type="datetime-local"
                class="form-control"
                name="validUntil"
                :class="{ valid: !v$.validUntil.$invalid, invalid: v$.validUntil.$invalid }"
                :value="convertDateTimeFromServer(v$.validUntil.$model)"
                @change="updateInstantField('validUntil', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.activation.isUsed')" for="activation-isUsed"></label>
            <input
              type="checkbox"
              class="form-check"
              name="isUsed"
              id="activation-isUsed"
              data-cy="isUsed"
              :class="{ valid: !v$.isUsed.$invalid, invalid: v$.isUsed.$invalid }"
              v-model="v$.isUsed.$model"
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
<script lang="ts" src="./activation-update.component.ts"></script>
