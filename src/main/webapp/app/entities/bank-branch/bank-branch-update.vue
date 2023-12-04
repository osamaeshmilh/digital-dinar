<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.bankBranch.home.createOrEditLabel"
          data-cy="BankBranchCreateUpdateHeading"
          v-text="t$('digitalDinarApp.bankBranch.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="bankBranch.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="bankBranch.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.bankBranch.nameAr')" for="bank-branch-nameAr"></label>
            <input
              type="text"
              class="form-control"
              name="nameAr"
              id="bank-branch-nameAr"
              data-cy="nameAr"
              :class="{ valid: !v$.nameAr.$invalid, invalid: v$.nameAr.$invalid }"
              v-model="v$.nameAr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.bankBranch.namEn')" for="bank-branch-namEn"></label>
            <input
              type="text"
              class="form-control"
              name="namEn"
              id="bank-branch-namEn"
              data-cy="namEn"
              :class="{ valid: !v$.namEn.$invalid, invalid: v$.namEn.$invalid }"
              v-model="v$.namEn.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.bankBranch.address')" for="bank-branch-address"></label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="bank-branch-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.bankBranch.createdBy')" for="bank-branch-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="bank-branch-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.bankBranch.createdDate')" for="bank-branch-createdDate"></label>
            <div class="d-flex">
              <input
                id="bank-branch-createdDate"
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
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.bankBranch.lastModifiedBy')"
              for="bank-branch-lastModifiedBy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="bank-branch-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.bankBranch.lastModifiedDate')"
              for="bank-branch-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="bank-branch-lastModifiedDate"
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
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.bankBranch.bank')" for="bank-branch-bank"></label>
            <select class="form-control" id="bank-branch-bank" data-cy="bank" name="bank" v-model="bankBranch.bank">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="bankBranch.bank && bankOption.id === bankBranch.bank.id ? bankBranch.bank : bankOption"
                v-for="bankOption in banks"
                :key="bankOption.id"
              >
                {{ bankOption.nameAr }}
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
<script lang="ts" src="./bank-branch-update.component.ts"></script>
