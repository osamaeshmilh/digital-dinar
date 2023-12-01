<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.voucherType.home.createOrEditLabel"
          data-cy="VoucherTypeCreateUpdateHeading"
          v-text="t$('digitalDinarApp.voucherType.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="voucherType.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="voucherType.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherType.nameAr')" for="voucher-type-nameAr"></label>
            <input
              type="text"
              class="form-control"
              name="nameAr"
              id="voucher-type-nameAr"
              data-cy="nameAr"
              :class="{ valid: !v$.nameAr.$invalid, invalid: v$.nameAr.$invalid }"
              v-model="v$.nameAr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherType.nameEn')" for="voucher-type-nameEn"></label>
            <input
              type="text"
              class="form-control"
              name="nameEn"
              id="voucher-type-nameEn"
              data-cy="nameEn"
              :class="{ valid: !v$.nameEn.$invalid, invalid: v$.nameEn.$invalid }"
              v-model="v$.nameEn.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherType.slug')" for="voucher-type-slug"></label>
            <input
              type="text"
              class="form-control"
              name="slug"
              id="voucher-type-slug"
              data-cy="slug"
              :class="{ valid: !v$.slug.$invalid, invalid: v$.slug.$invalid }"
              v-model="v$.slug.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherType.amount')" for="voucher-type-amount"></label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="voucher-type-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherType.menuOrder')" for="voucher-type-menuOrder"></label>
            <input
              type="number"
              class="form-control"
              name="menuOrder"
              id="voucher-type-menuOrder"
              data-cy="menuOrder"
              :class="{ valid: !v$.menuOrder.$invalid, invalid: v$.menuOrder.$invalid }"
              v-model.number="v$.menuOrder.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherType.createdBy')" for="voucher-type-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="voucher-type-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherType.createdDate')" for="voucher-type-createdDate"></label>
            <div class="d-flex">
              <input
                id="voucher-type-createdDate"
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
              v-text="t$('digitalDinarApp.voucherType.lastModifiedBy')"
              for="voucher-type-lastModifiedBy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="voucher-type-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.voucherType.lastModifiedDate')"
              for="voucher-type-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="voucher-type-lastModifiedDate"
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
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.voucherType.voucherCompany')"
              for="voucher-type-voucherCompany"
            ></label>
            <select
              class="form-control"
              id="voucher-type-voucherCompany"
              data-cy="voucherCompany"
              name="voucherCompany"
              v-model="voucherType.voucherCompany"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  voucherType.voucherCompany && voucherCompanyOption.id === voucherType.voucherCompany.id
                    ? voucherType.voucherCompany
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
<script lang="ts" src="./voucher-type-update.component.ts"></script>
