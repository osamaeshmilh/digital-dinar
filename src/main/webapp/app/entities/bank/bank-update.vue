<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.bank.home.createOrEditLabel"
          data-cy="BankCreateUpdateHeading"
          v-text="t$('digitalDinarApp.bank.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="bank.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="bank.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.bank.nameAr')" for="bank-nameAr"></label>
            <input
              type="text"
              class="form-control"
              name="nameAr"
              id="bank-nameAr"
              data-cy="nameAr"
              :class="{ valid: !v$.nameAr.$invalid, invalid: v$.nameAr.$invalid }"
              v-model="v$.nameAr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.bank.namEn')" for="bank-namEn"></label>
            <input
              type="text"
              class="form-control"
              name="namEn"
              id="bank-namEn"
              data-cy="namEn"
              :class="{ valid: !v$.namEn.$invalid, invalid: v$.namEn.$invalid }"
              v-model="v$.namEn.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.bank.swiftCode')" for="bank-swiftCode"></label>
            <input
              type="text"
              class="form-control"
              name="swiftCode"
              id="bank-swiftCode"
              data-cy="swiftCode"
              :class="{ valid: !v$.swiftCode.$invalid, invalid: v$.swiftCode.$invalid }"
              v-model="v$.swiftCode.$model"
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
<script lang="ts" src="./bank-update.component.ts"></script>
