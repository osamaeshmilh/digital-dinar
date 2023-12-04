<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.walletUser.home.createOrEditLabel"
          data-cy="WalletUserCreateUpdateHeading"
          v-text="t$('digitalDinarApp.walletUser.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="walletUser.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="walletUser.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.arabicFirstName')"
              for="wallet-user-arabicFirstName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="arabicFirstName"
              id="wallet-user-arabicFirstName"
              data-cy="arabicFirstName"
              :class="{ valid: !v$.arabicFirstName.$invalid, invalid: v$.arabicFirstName.$invalid }"
              v-model="v$.arabicFirstName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.arabicMiddleName')"
              for="wallet-user-arabicMiddleName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="arabicMiddleName"
              id="wallet-user-arabicMiddleName"
              data-cy="arabicMiddleName"
              :class="{ valid: !v$.arabicMiddleName.$invalid, invalid: v$.arabicMiddleName.$invalid }"
              v-model="v$.arabicMiddleName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.arabicLastName')"
              for="wallet-user-arabicLastName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="arabicLastName"
              id="wallet-user-arabicLastName"
              data-cy="arabicLastName"
              :class="{ valid: !v$.arabicLastName.$invalid, invalid: v$.arabicLastName.$invalid }"
              v-model="v$.arabicLastName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.englishFirstName')"
              for="wallet-user-englishFirstName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishFirstName"
              id="wallet-user-englishFirstName"
              data-cy="englishFirstName"
              :class="{ valid: !v$.englishFirstName.$invalid, invalid: v$.englishFirstName.$invalid }"
              v-model="v$.englishFirstName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.englishMiddleName')"
              for="wallet-user-englishMiddleName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishMiddleName"
              id="wallet-user-englishMiddleName"
              data-cy="englishMiddleName"
              :class="{ valid: !v$.englishMiddleName.$invalid, invalid: v$.englishMiddleName.$invalid }"
              v-model="v$.englishMiddleName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.englishLastName')"
              for="wallet-user-englishLastName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishLastName"
              id="wallet-user-englishLastName"
              data-cy="englishLastName"
              :class="{ valid: !v$.englishLastName.$invalid, invalid: v$.englishLastName.$invalid }"
              v-model="v$.englishLastName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.commercialRegistryNo')"
              for="wallet-user-commercialRegistryNo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="commercialRegistryNo"
              id="wallet-user-commercialRegistryNo"
              data-cy="commercialRegistryNo"
              :class="{ valid: !v$.commercialRegistryNo.$invalid, invalid: v$.commercialRegistryNo.$invalid }"
              v-model="v$.commercialRegistryNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.commercialLicenceNo')"
              for="wallet-user-commercialLicenceNo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="commercialLicenceNo"
              id="wallet-user-commercialLicenceNo"
              data-cy="commercialLicenceNo"
              :class="{ valid: !v$.commercialLicenceNo.$invalid, invalid: v$.commercialLicenceNo.$invalid }"
              v-model="v$.commercialLicenceNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.commercialName')"
              for="wallet-user-commercialName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="commercialName"
              id="wallet-user-commercialName"
              data-cy="commercialName"
              :class="{ valid: !v$.commercialName.$invalid, invalid: v$.commercialName.$invalid }"
              v-model="v$.commercialName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.birthDate')" for="wallet-user-birthDate"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="wallet-user-birthDate"
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
                id="wallet-user-birthDate"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.birthPlace')" for="wallet-user-birthPlace"></label>
            <input
              type="text"
              class="form-control"
              name="birthPlace"
              id="wallet-user-birthPlace"
              data-cy="birthPlace"
              :class="{ valid: !v$.birthPlace.$invalid, invalid: v$.birthPlace.$invalid }"
              v-model="v$.birthPlace.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.nationalNo')" for="wallet-user-nationalNo"></label>
            <input
              type="text"
              class="form-control"
              name="nationalNo"
              id="wallet-user-nationalNo"
              data-cy="nationalNo"
              :class="{ valid: !v$.nationalNo.$invalid, invalid: v$.nationalNo.$invalid }"
              v-model="v$.nationalNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.registerNo')" for="wallet-user-registerNo"></label>
            <input
              type="text"
              class="form-control"
              name="registerNo"
              id="wallet-user-registerNo"
              data-cy="registerNo"
              :class="{ valid: !v$.registerNo.$invalid, invalid: v$.registerNo.$invalid }"
              v-model="v$.registerNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.gender')" for="wallet-user-gender"></label>
            <select
              class="form-control"
              name="gender"
              :class="{ valid: !v$.gender.$invalid, invalid: v$.gender.$invalid }"
              v-model="v$.gender.$model"
              id="wallet-user-gender"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.idType')" for="wallet-user-idType"></label>
            <select
              class="form-control"
              name="idType"
              :class="{ valid: !v$.idType.$invalid, invalid: v$.idType.$invalid }"
              v-model="v$.idType.$model"
              id="wallet-user-idType"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.idNo')" for="wallet-user-idNo"></label>
            <input
              type="text"
              class="form-control"
              name="idNo"
              id="wallet-user-idNo"
              data-cy="idNo"
              :class="{ valid: !v$.idNo.$invalid, invalid: v$.idNo.$invalid }"
              v-model="v$.idNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.mobileNo')" for="wallet-user-mobileNo"></label>
            <input
              type="text"
              class="form-control"
              name="mobileNo"
              id="wallet-user-mobileNo"
              data-cy="mobileNo"
              :class="{ valid: !v$.mobileNo.$invalid, invalid: v$.mobileNo.$invalid }"
              v-model="v$.mobileNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.verifiedByEmailOtp')"
              for="wallet-user-verifiedByEmailOtp"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="verifiedByEmailOtp"
              id="wallet-user-verifiedByEmailOtp"
              data-cy="verifiedByEmailOtp"
              :class="{ valid: !v$.verifiedByEmailOtp.$invalid, invalid: v$.verifiedByEmailOtp.$invalid }"
              v-model="v$.verifiedByEmailOtp.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.verifiedByMobileOtp')"
              for="wallet-user-verifiedByMobileOtp"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="verifiedByMobileOtp"
              id="wallet-user-verifiedByMobileOtp"
              data-cy="verifiedByMobileOtp"
              :class="{ valid: !v$.verifiedByMobileOtp.$invalid, invalid: v$.verifiedByMobileOtp.$invalid }"
              v-model="v$.verifiedByMobileOtp.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.email')" for="wallet-user-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="wallet-user-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.nidStatus')" for="wallet-user-nidStatus"></label>
            <input
              type="text"
              class="form-control"
              name="nidStatus"
              id="wallet-user-nidStatus"
              data-cy="nidStatus"
              :class="{ valid: !v$.nidStatus.$invalid, invalid: v$.nidStatus.$invalid }"
              v-model="v$.nidStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.isForeign')" for="wallet-user-isForeign"></label>
            <input
              type="checkbox"
              class="form-check"
              name="isForeign"
              id="wallet-user-isForeign"
              data-cy="isForeign"
              :class="{ valid: !v$.isForeign.$invalid, invalid: v$.isForeign.$invalid }"
              v-model="v$.isForeign.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.isMobileConnectedWithNid')"
              for="wallet-user-isMobileConnectedWithNid"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="isMobileConnectedWithNid"
              id="wallet-user-isMobileConnectedWithNid"
              data-cy="isMobileConnectedWithNid"
              :class="{ valid: !v$.isMobileConnectedWithNid.$invalid, invalid: v$.isMobileConnectedWithNid.$invalid }"
              v-model="v$.isMobileConnectedWithNid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.isKYCVerified')"
              for="wallet-user-isKYCVerified"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="isKYCVerified"
              id="wallet-user-isKYCVerified"
              data-cy="isKYCVerified"
              :class="{ valid: !v$.isKYCVerified.$invalid, invalid: v$.isKYCVerified.$invalid }"
              v-model="v$.isKYCVerified.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.walletType')" for="wallet-user-walletType"></label>
            <select
              class="form-control"
              name="walletType"
              :class="{ valid: !v$.walletType.$invalid, invalid: v$.walletType.$invalid }"
              v-model="v$.walletType.$model"
              id="wallet-user-walletType"
              data-cy="walletType"
            >
              <option
                v-for="walletType in walletTypeValues"
                :key="walletType"
                v-bind:value="walletType"
                v-bind:label="t$('digitalDinarApp.WalletType.' + walletType)"
              >
                {{ walletType }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.walletStatus')" for="wallet-user-walletStatus"></label>
            <select
              class="form-control"
              name="walletStatus"
              :class="{ valid: !v$.walletStatus.$invalid, invalid: v$.walletStatus.$invalid }"
              v-model="v$.walletStatus.$model"
              id="wallet-user-walletStatus"
              data-cy="walletStatus"
            >
              <option
                v-for="walletStatus in walletStatusValues"
                :key="walletStatus"
                v-bind:value="walletStatus"
                v-bind:label="t$('digitalDinarApp.WalletStatus.' + walletStatus)"
              >
                {{ walletStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.imageFile')" for="wallet-user-imageFile"></label>
            <div>
              <img
                v-bind:src="'data:' + walletUser.imageFileContentType + ';base64,' + walletUser.imageFile"
                style="max-height: 100px"
                v-if="walletUser.imageFile"
                alt="walletUser image"
              />
              <div v-if="walletUser.imageFile" class="form-text text-danger clearfix">
                <span class="pull-left">{{ walletUser.imageFileContentType }}, {{ byteSize(walletUser.imageFile) }}</span>
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
                v-on:change="setFileData($event, walletUser, 'imageFile', true)"
                accept="image/*"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="imageFile"
              id="wallet-user-imageFile"
              data-cy="imageFile"
              :class="{ valid: !v$.imageFile.$invalid, invalid: v$.imageFile.$invalid }"
              v-model="v$.imageFile.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="imageFileContentType"
              id="wallet-user-imageFileContentType"
              v-model="walletUser.imageFileContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.imageUrlFile')" for="wallet-user-imageUrlFile"></label>
            <input
              type="text"
              class="form-control"
              name="imageUrlFile"
              id="wallet-user-imageUrlFile"
              data-cy="imageUrlFile"
              :class="{ valid: !v$.imageUrlFile.$invalid, invalid: v$.imageUrlFile.$invalid }"
              v-model="v$.imageUrlFile.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.postCode')" for="wallet-user-postCode"></label>
            <input
              type="text"
              class="form-control"
              name="postCode"
              id="wallet-user-postCode"
              data-cy="postCode"
              :class="{ valid: !v$.postCode.$invalid, invalid: v$.postCode.$invalid }"
              v-model="v$.postCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.address')" for="wallet-user-address"></label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="wallet-user-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.lat')" for="wallet-user-lat"></label>
            <input
              type="number"
              class="form-control"
              name="lat"
              id="wallet-user-lat"
              data-cy="lat"
              :class="{ valid: !v$.lat.$invalid, invalid: v$.lat.$invalid }"
              v-model.number="v$.lat.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.lng')" for="wallet-user-lng"></label>
            <input
              type="number"
              class="form-control"
              name="lng"
              id="wallet-user-lng"
              data-cy="lng"
              :class="{ valid: !v$.lng.$invalid, invalid: v$.lng.$invalid }"
              v-model.number="v$.lng.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.notes')" for="wallet-user-notes"></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="wallet-user-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.bankAccountName')"
              for="wallet-user-bankAccountName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bankAccountName"
              id="wallet-user-bankAccountName"
              data-cy="bankAccountName"
              :class="{ valid: !v$.bankAccountName.$invalid, invalid: v$.bankAccountName.$invalid }"
              v-model="v$.bankAccountName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.bankAccountNumber')"
              for="wallet-user-bankAccountNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bankAccountNumber"
              id="wallet-user-bankAccountNumber"
              data-cy="bankAccountNumber"
              :class="{ valid: !v$.bankAccountNumber.$invalid, invalid: v$.bankAccountNumber.$invalid }"
              v-model="v$.bankAccountNumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.bankAccountIBAN')"
              for="wallet-user-bankAccountIBAN"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bankAccountIBAN"
              id="wallet-user-bankAccountIBAN"
              data-cy="bankAccountIBAN"
              :class="{ valid: !v$.bankAccountIBAN.$invalid, invalid: v$.bankAccountIBAN.$invalid }"
              v-model="v$.bankAccountIBAN.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.bankAccountSWIFT')"
              for="wallet-user-bankAccountSWIFT"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bankAccountSWIFT"
              id="wallet-user-bankAccountSWIFT"
              data-cy="bankAccountSWIFT"
              :class="{ valid: !v$.bankAccountSWIFT.$invalid, invalid: v$.bankAccountSWIFT.$invalid }"
              v-model="v$.bankAccountSWIFT.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.user')" for="wallet-user-user"></label>
            <select class="form-control" id="wallet-user-user" data-cy="user" name="user" v-model="walletUser.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="walletUser.user && userOption.id === walletUser.user.id ? walletUser.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.category')" for="wallet-user-category"></label>
            <select class="form-control" id="wallet-user-category" data-cy="category" name="category" v-model="walletUser.category">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="walletUser.category && categoryOption.id === walletUser.category.id ? walletUser.category : categoryOption"
                v-for="categoryOption in categories"
                :key="categoryOption.id"
              >
                {{ categoryOption.nameAr }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.city')" for="wallet-user-city"></label>
            <select class="form-control" id="wallet-user-city" data-cy="city" name="city" v-model="walletUser.city">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="walletUser.city && cityOption.id === walletUser.city.id ? walletUser.city : cityOption"
                v-for="cityOption in cities"
                :key="cityOption.id"
              >
                {{ cityOption.nameAr }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletUser.walletProfile')"
              for="wallet-user-walletProfile"
            ></label>
            <select
              class="form-control"
              id="wallet-user-walletProfile"
              data-cy="walletProfile"
              name="walletProfile"
              v-model="walletUser.walletProfile"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  walletUser.walletProfile && walletProfileOption.id === walletUser.walletProfile.id
                    ? walletUser.walletProfile
                    : walletProfileOption
                "
                v-for="walletProfileOption in walletProfiles"
                :key="walletProfileOption.id"
              >
                {{ walletProfileOption.nameAr }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletUser.bankBranch')" for="wallet-user-bankBranch"></label>
            <select class="form-control" id="wallet-user-bankBranch" data-cy="bankBranch" name="bankBranch" v-model="walletUser.bankBranch">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  walletUser.bankBranch && bankBranchOption.id === walletUser.bankBranch.id ? walletUser.bankBranch : bankBranchOption
                "
                v-for="bankBranchOption in bankBranches"
                :key="bankBranchOption.id"
              >
                {{ bankBranchOption.nameAr }}
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
<script lang="ts" src="./wallet-user-update.component.ts"></script>
