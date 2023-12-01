<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.voucher.home.createOrEditLabel"
          data-cy="VoucherCreateUpdateHeading"
          v-text="t$('digitalDinarApp.voucher.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="voucher.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="voucher.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucher.code')" for="voucher-code"></label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="voucher-code"
              data-cy="code"
              :class="{ valid: !v$.code.$invalid, invalid: v$.code.$invalid }"
              v-model="v$.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucher.amount')" for="voucher-amount"></label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="voucher-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucher.serialNumber')" for="voucher-serialNumber"></label>
            <input
              type="text"
              class="form-control"
              name="serialNumber"
              id="voucher-serialNumber"
              data-cy="serialNumber"
              :class="{ valid: !v$.serialNumber.$invalid, invalid: v$.serialNumber.$invalid }"
              v-model="v$.serialNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucher.isSold')" for="voucher-isSold"></label>
            <input
              type="checkbox"
              class="form-check"
              name="isSold"
              id="voucher-isSold"
              data-cy="isSold"
              :class="{ valid: !v$.isSold.$invalid, invalid: v$.isSold.$invalid }"
              v-model="v$.isSold.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.voucher.sellTransactionId')"
              for="voucher-sellTransactionId"
            ></label>
            <input
              type="number"
              class="form-control"
              name="sellTransactionId"
              id="voucher-sellTransactionId"
              data-cy="sellTransactionId"
              :class="{ valid: !v$.sellTransactionId.$invalid, invalid: v$.sellTransactionId.$invalid }"
              v-model.number="v$.sellTransactionId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucher.createdBy')" for="voucher-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="voucher-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucher.createdDate')" for="voucher-createdDate"></label>
            <div class="d-flex">
              <input
                id="voucher-createdDate"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucher.lastModifiedBy')" for="voucher-lastModifiedBy"></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="voucher-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.voucher.lastModifiedDate')"
              for="voucher-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="voucher-lastModifiedDate"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucher.voucherType')" for="voucher-voucherType"></label>
            <select class="form-control" id="voucher-voucherType" data-cy="voucherType" name="voucherType" v-model="voucher.voucherType">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  voucher.voucherType && voucherTypeOption.id === voucher.voucherType.id ? voucher.voucherType : voucherTypeOption
                "
                v-for="voucherTypeOption in voucherTypes"
                :key="voucherTypeOption.id"
              >
                {{ voucherTypeOption.nameAr }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucher.voucherCompany')" for="voucher-voucherCompany"></label>
            <select
              class="form-control"
              id="voucher-voucherCompany"
              data-cy="voucherCompany"
              name="voucherCompany"
              v-model="voucher.voucherCompany"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  voucher.voucherCompany && voucherCompanyOption.id === voucher.voucherCompany.id
                    ? voucher.voucherCompany
                    : voucherCompanyOption
                "
                v-for="voucherCompanyOption in voucherCompanies"
                :key="voucherCompanyOption.id"
              >
                {{ voucherCompanyOption.nameAr }}
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
<script lang="ts" src="./voucher-update.component.ts"></script>
