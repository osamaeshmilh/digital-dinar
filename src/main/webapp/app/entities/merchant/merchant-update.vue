<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.merchant.home.createOrEditLabel"
          data-cy="MerchantCreateUpdateHeading"
          v-text="t$('digitalDinarApp.merchant.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="merchant.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="merchant.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.arabicFirstName')"
              for="merchant-arabicFirstName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="arabicFirstName"
              id="merchant-arabicFirstName"
              data-cy="arabicFirstName"
              :class="{ valid: !v$.arabicFirstName.$invalid, invalid: v$.arabicFirstName.$invalid }"
              v-model="v$.arabicFirstName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.arabicMiddleName')"
              for="merchant-arabicMiddleName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="arabicMiddleName"
              id="merchant-arabicMiddleName"
              data-cy="arabicMiddleName"
              :class="{ valid: !v$.arabicMiddleName.$invalid, invalid: v$.arabicMiddleName.$invalid }"
              v-model="v$.arabicMiddleName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.arabicLastName')" for="merchant-arabicLastName"></label>
            <input
              type="text"
              class="form-control"
              name="arabicLastName"
              id="merchant-arabicLastName"
              data-cy="arabicLastName"
              :class="{ valid: !v$.arabicLastName.$invalid, invalid: v$.arabicLastName.$invalid }"
              v-model="v$.arabicLastName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.englishFirstName')"
              for="merchant-englishFirstName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishFirstName"
              id="merchant-englishFirstName"
              data-cy="englishFirstName"
              :class="{ valid: !v$.englishFirstName.$invalid, invalid: v$.englishFirstName.$invalid }"
              v-model="v$.englishFirstName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.englishMiddleName')"
              for="merchant-englishMiddleName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishMiddleName"
              id="merchant-englishMiddleName"
              data-cy="englishMiddleName"
              :class="{ valid: !v$.englishMiddleName.$invalid, invalid: v$.englishMiddleName.$invalid }"
              v-model="v$.englishMiddleName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.englishLastName')"
              for="merchant-englishLastName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishLastName"
              id="merchant-englishLastName"
              data-cy="englishLastName"
              :class="{ valid: !v$.englishLastName.$invalid, invalid: v$.englishLastName.$invalid }"
              v-model="v$.englishLastName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.commercialRegistryNo')"
              for="merchant-commercialRegistryNo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="commercialRegistryNo"
              id="merchant-commercialRegistryNo"
              data-cy="commercialRegistryNo"
              :class="{ valid: !v$.commercialRegistryNo.$invalid, invalid: v$.commercialRegistryNo.$invalid }"
              v-model="v$.commercialRegistryNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.commercialLicenceNo')"
              for="merchant-commercialLicenceNo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="commercialLicenceNo"
              id="merchant-commercialLicenceNo"
              data-cy="commercialLicenceNo"
              :class="{ valid: !v$.commercialLicenceNo.$invalid, invalid: v$.commercialLicenceNo.$invalid }"
              v-model="v$.commercialLicenceNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.birthDate')" for="merchant-birthDate"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="merchant-birthDate"
                  v-model="v$.birthDate.$model"
                  name="birthDate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="merchant-birthDate"
                data-cy="birthDate"
                type="text"
                class="form-control"
                name="birthDate"
                :class="{ valid: !v$.birthDate.$invalid, invalid: v$.birthDate.$invalid }"
                v-model="v$.birthDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.birthPlace')" for="merchant-birthPlace"></label>
            <input
              type="text"
              class="form-control"
              name="birthPlace"
              id="merchant-birthPlace"
              data-cy="birthPlace"
              :class="{ valid: !v$.birthPlace.$invalid, invalid: v$.birthPlace.$invalid }"
              v-model="v$.birthPlace.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.nationalNo')" for="merchant-nationalNo"></label>
            <input
              type="text"
              class="form-control"
              name="nationalNo"
              id="merchant-nationalNo"
              data-cy="nationalNo"
              :class="{ valid: !v$.nationalNo.$invalid, invalid: v$.nationalNo.$invalid }"
              v-model="v$.nationalNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.registerNo')" for="merchant-registerNo"></label>
            <input
              type="text"
              class="form-control"
              name="registerNo"
              id="merchant-registerNo"
              data-cy="registerNo"
              :class="{ valid: !v$.registerNo.$invalid, invalid: v$.registerNo.$invalid }"
              v-model="v$.registerNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.gender')" for="merchant-gender"></label>
            <select
              class="form-control"
              name="gender"
              :class="{ valid: !v$.gender.$invalid, invalid: v$.gender.$invalid }"
              v-model="v$.gender.$model"
              id="merchant-gender"
              data-cy="gender"
            >
              <option
                v-for="gender in genderValues"
                :key="gender"
                v-bind:value="gender"
                v-bind:label="t$('digitalDinarApp.Gender.' + gender)"
              >
                {{ gender }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.idType')" for="merchant-idType"></label>
            <select
              class="form-control"
              name="idType"
              :class="{ valid: !v$.idType.$invalid, invalid: v$.idType.$invalid }"
              v-model="v$.idType.$model"
              id="merchant-idType"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.idNo')" for="merchant-idNo"></label>
            <input
              type="text"
              class="form-control"
              name="idNo"
              id="merchant-idNo"
              data-cy="idNo"
              :class="{ valid: !v$.idNo.$invalid, invalid: v$.idNo.$invalid }"
              v-model="v$.idNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.mobileNo')" for="merchant-mobileNo"></label>
            <input
              type="text"
              class="form-control"
              name="mobileNo"
              id="merchant-mobileNo"
              data-cy="mobileNo"
              :class="{ valid: !v$.mobileNo.$invalid, invalid: v$.mobileNo.$invalid }"
              v-model="v$.mobileNo.$model"
              required
            />
            <div v-if="v$.mobileNo.$anyDirty && v$.mobileNo.$invalid">
              <small class="form-text text-danger" v-for="error of v$.mobileNo.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.verifiedByEmailOtp')"
              for="merchant-verifiedByEmailOtp"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="verifiedByEmailOtp"
              id="merchant-verifiedByEmailOtp"
              data-cy="verifiedByEmailOtp"
              :class="{ valid: !v$.verifiedByEmailOtp.$invalid, invalid: v$.verifiedByEmailOtp.$invalid }"
              v-model="v$.verifiedByEmailOtp.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.verifiedByMobileNoOtp')"
              for="merchant-verifiedByMobileNoOtp"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="verifiedByMobileNoOtp"
              id="merchant-verifiedByMobileNoOtp"
              data-cy="verifiedByMobileNoOtp"
              :class="{ valid: !v$.verifiedByMobileNoOtp.$invalid, invalid: v$.verifiedByMobileNoOtp.$invalid }"
              v-model="v$.verifiedByMobileNoOtp.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.email')" for="merchant-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="merchant-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.nidStatus')" for="merchant-nidStatus"></label>
            <input
              type="text"
              class="form-control"
              name="nidStatus"
              id="merchant-nidStatus"
              data-cy="nidStatus"
              :class="{ valid: !v$.nidStatus.$invalid, invalid: v$.nidStatus.$invalid }"
              v-model="v$.nidStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.isForeign')" for="merchant-isForeign"></label>
            <input
              type="checkbox"
              class="form-check"
              name="isForeign"
              id="merchant-isForeign"
              data-cy="isForeign"
              :class="{ valid: !v$.isForeign.$invalid, invalid: v$.isForeign.$invalid }"
              v-model="v$.isForeign.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.isMobileConnectedWithNid')"
              for="merchant-isMobileConnectedWithNid"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="isMobileConnectedWithNid"
              id="merchant-isMobileConnectedWithNid"
              data-cy="isMobileConnectedWithNid"
              :class="{ valid: !v$.isMobileConnectedWithNid.$invalid, invalid: v$.isMobileConnectedWithNid.$invalid }"
              v-model="v$.isMobileConnectedWithNid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.merchant.walletOwnerStatus')"
              for="merchant-walletOwnerStatus"
            ></label>
            <select
              class="form-control"
              name="walletOwnerStatus"
              :class="{ valid: !v$.walletOwnerStatus.$invalid, invalid: v$.walletOwnerStatus.$invalid }"
              v-model="v$.walletOwnerStatus.$model"
              id="merchant-walletOwnerStatus"
              data-cy="walletOwnerStatus"
            >
              <option
                v-for="walletOwnerStatus in walletOwnerStatusValues"
                :key="walletOwnerStatus"
                v-bind:value="walletOwnerStatus"
                v-bind:label="t$('digitalDinarApp.WalletOwnerStatus.' + walletOwnerStatus)"
              >
                {{ walletOwnerStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.imageFile')" for="merchant-imageFile"></label>
            <div>
              <img
                v-bind:src="'data:' + merchant.imageFileContentType + ';base64,' + merchant.imageFile"
                style="max-height: 100px"
                v-if="merchant.imageFile"
                alt="merchant image"
              />
              <div v-if="merchant.imageFile" class="form-text text-danger clearfix">
                <span class="pull-left">{{ merchant.imageFileContentType }}, {{ byteSize(merchant.imageFile) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('imageFile', 'imageFileContentType', 'file_imageFile')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <label for="file_imageFile" v-text="t$('entity.action.addimage')" class="btn btn-primary pull-right"></label>
              <input
                type="file"
                ref="file_imageFile"
                id="file_imageFile"
                style="display: none"
                data-cy="imageFile"
                v-on:change="setFileData($event, merchant, 'imageFile', true)"
                accept="image/*"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="imageFile"
              id="merchant-imageFile"
              data-cy="imageFile"
              :class="{ valid: !v$.imageFile.$invalid, invalid: v$.imageFile.$invalid }"
              v-model="v$.imageFile.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="imageFileContentType"
              id="merchant-imageFileContentType"
              v-model="merchant.imageFileContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.imageUrlFile')" for="merchant-imageUrlFile"></label>
            <input
              type="text"
              class="form-control"
              name="imageUrlFile"
              id="merchant-imageUrlFile"
              data-cy="imageUrlFile"
              :class="{ valid: !v$.imageUrlFile.$invalid, invalid: v$.imageUrlFile.$invalid }"
              v-model="v$.imageUrlFile.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.postCode')" for="merchant-postCode"></label>
            <input
              type="text"
              class="form-control"
              name="postCode"
              id="merchant-postCode"
              data-cy="postCode"
              :class="{ valid: !v$.postCode.$invalid, invalid: v$.postCode.$invalid }"
              v-model="v$.postCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.address')" for="merchant-address"></label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="merchant-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.lat')" for="merchant-lat"></label>
            <input
              type="number"
              class="form-control"
              name="lat"
              id="merchant-lat"
              data-cy="lat"
              :class="{ valid: !v$.lat.$invalid, invalid: v$.lat.$invalid }"
              v-model.number="v$.lat.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.lng')" for="merchant-lng"></label>
            <input
              type="number"
              class="form-control"
              name="lng"
              id="merchant-lng"
              data-cy="lng"
              :class="{ valid: !v$.lng.$invalid, invalid: v$.lng.$invalid }"
              v-model.number="v$.lng.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.notes')" for="merchant-notes"></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="merchant-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.user')" for="merchant-user"></label>
            <select class="form-control" id="merchant-user" data-cy="user" name="user" v-model="merchant.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="merchant.user && userOption.id === merchant.user.id ? merchant.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.category')" for="merchant-category"></label>
            <select class="form-control" id="merchant-category" data-cy="category" name="category" v-model="merchant.category">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="merchant.category && categoryOption.id === merchant.category.id ? merchant.category : categoryOption"
                v-for="categoryOption in categories"
                :key="categoryOption.id"
              >
                {{ categoryOption.nameAr }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.city')" for="merchant-city"></label>
            <select class="form-control" id="merchant-city" data-cy="city" name="city" v-model="merchant.city">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="merchant.city && cityOption.id === merchant.city.id ? merchant.city : cityOption"
                v-for="cityOption in cities"
                :key="cityOption.id"
              >
                {{ cityOption.nameAr }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.merchant.walletProfile')" for="merchant-walletProfile"></label>
            <select
              class="form-control"
              id="merchant-walletProfile"
              data-cy="walletProfile"
              name="walletProfile"
              v-model="merchant.walletProfile"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  merchant.walletProfile && walletProfileOption.id === merchant.walletProfile.id
                    ? merchant.walletProfile
                    : walletProfileOption
                "
                v-for="walletProfileOption in walletProfiles"
                :key="walletProfileOption.id"
              >
                {{ walletProfileOption.nameAr }}
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
<script lang="ts" src="./merchant-update.component.ts"></script>
