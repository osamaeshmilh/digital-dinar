<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.voucherCompany.home.createOrEditLabel"
          data-cy="VoucherCompanyCreateUpdateHeading"
          v-text="t$('digitalDinarApp.voucherCompany.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="voucherCompany.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="voucherCompany.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherCompany.nameAr')" for="voucher-company-nameAr"></label>
            <input
              type="text"
              class="form-control"
              name="nameAr"
              id="voucher-company-nameAr"
              data-cy="nameAr"
              :class="{ valid: !v$.nameAr.$invalid, invalid: v$.nameAr.$invalid }"
              v-model="v$.nameAr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherCompany.nameEn')" for="voucher-company-nameEn"></label>
            <input
              type="text"
              class="form-control"
              name="nameEn"
              id="voucher-company-nameEn"
              data-cy="nameEn"
              :class="{ valid: !v$.nameEn.$invalid, invalid: v$.nameEn.$invalid }"
              v-model="v$.nameEn.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherCompany.mobileNo')" for="voucher-company-mobileNo"></label>
            <input
              type="text"
              class="form-control"
              name="mobileNo"
              id="voucher-company-mobileNo"
              data-cy="mobileNo"
              :class="{ valid: !v$.mobileNo.$invalid, invalid: v$.mobileNo.$invalid }"
              v-model="v$.mobileNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherCompany.email')" for="voucher-company-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="voucher-company-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.voucherCompany.imageFile')"
              for="voucher-company-imageFile"
            ></label>
            <div>
              <div v-if="voucherCompany.imageFile" class="form-text text-danger clearfix">
                <a
                  class="pull-left"
                  v-on:click="openFile(voucherCompany.imageFileContentType, voucherCompany.imageFile)"
                  v-text="t$('entity.action.open')"
                ></a
                ><br />
                <span class="pull-left">{{ voucherCompany.imageFileContentType }}, {{ byteSize(voucherCompany.imageFile) }}</span>
                <button
                  type="button"
                  v-on:click="
                    voucherCompany.imageFile = null;
                    voucherCompany.imageFileContentType = null;
                  "
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <label for="file_imageFile" v-text="t$('entity.action.addblob')" class="btn btn-primary pull-right"></label>
              <input
                type="file"
                ref="file_imageFile"
                id="file_imageFile"
                style="display: none"
                data-cy="imageFile"
                v-on:change="setFileData($event, voucherCompany, 'imageFile', false)"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="imageFile"
              id="voucher-company-imageFile"
              data-cy="imageFile"
              :class="{ valid: !v$.imageFile.$invalid, invalid: v$.imageFile.$invalid }"
              v-model="v$.imageFile.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="imageFileContentType"
              id="voucher-company-imageFileContentType"
              v-model="voucherCompany.imageFileContentType"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.voucherCompany.imageUrlFile')"
              for="voucher-company-imageUrlFile"
            ></label>
            <input
              type="text"
              class="form-control"
              name="imageUrlFile"
              id="voucher-company-imageUrlFile"
              data-cy="imageUrlFile"
              :class="{ valid: !v$.imageUrlFile.$invalid, invalid: v$.imageUrlFile.$invalid }"
              v-model="v$.imageUrlFile.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherCompany.postCode')" for="voucher-company-postCode"></label>
            <input
              type="text"
              class="form-control"
              name="postCode"
              id="voucher-company-postCode"
              data-cy="postCode"
              :class="{ valid: !v$.postCode.$invalid, invalid: v$.postCode.$invalid }"
              v-model="v$.postCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherCompany.address')" for="voucher-company-address"></label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="voucher-company-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherCompany.notes')" for="voucher-company-notes"></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="voucher-company-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.voucherCompany.user')" for="voucher-company-user"></label>
            <select class="form-control" id="voucher-company-user" data-cy="user" name="user" v-model="voucherCompany.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="voucherCompany.user && userOption.id === voucherCompany.user.id ? voucherCompany.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.id }}
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
<script lang="ts" src="./voucher-company-update.component.ts"></script>
