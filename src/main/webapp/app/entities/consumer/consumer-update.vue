<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.consumer.home.createOrEditLabel"
          data-cy="ConsumerCreateUpdateHeading"
          v-text="t$('digitalDinarApp.consumer.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="consumer.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="consumer.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.consumer.arabicFirstName')"
              for="consumer-arabicFirstName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="arabicFirstName"
              id="consumer-arabicFirstName"
              data-cy="arabicFirstName"
              :class="{ valid: !v$.arabicFirstName.$invalid, invalid: v$.arabicFirstName.$invalid }"
              v-model="v$.arabicFirstName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.consumer.arabicMiddleName')"
              for="consumer-arabicMiddleName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="arabicMiddleName"
              id="consumer-arabicMiddleName"
              data-cy="arabicMiddleName"
              :class="{ valid: !v$.arabicMiddleName.$invalid, invalid: v$.arabicMiddleName.$invalid }"
              v-model="v$.arabicMiddleName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.arabicLastName')" for="consumer-arabicLastName"></label>
            <input
              type="text"
              class="form-control"
              name="arabicLastName"
              id="consumer-arabicLastName"
              data-cy="arabicLastName"
              :class="{ valid: !v$.arabicLastName.$invalid, invalid: v$.arabicLastName.$invalid }"
              v-model="v$.arabicLastName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.consumer.englishFirstName')"
              for="consumer-englishFirstName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishFirstName"
              id="consumer-englishFirstName"
              data-cy="englishFirstName"
              :class="{ valid: !v$.englishFirstName.$invalid, invalid: v$.englishFirstName.$invalid }"
              v-model="v$.englishFirstName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.consumer.englishMiddleName')"
              for="consumer-englishMiddleName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishMiddleName"
              id="consumer-englishMiddleName"
              data-cy="englishMiddleName"
              :class="{ valid: !v$.englishMiddleName.$invalid, invalid: v$.englishMiddleName.$invalid }"
              v-model="v$.englishMiddleName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.consumer.englishLastName')"
              for="consumer-englishLastName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="englishLastName"
              id="consumer-englishLastName"
              data-cy="englishLastName"
              :class="{ valid: !v$.englishLastName.$invalid, invalid: v$.englishLastName.$invalid }"
              v-model="v$.englishLastName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.birthDate')" for="consumer-birthDate"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="consumer-birthDate"
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
                id="consumer-birthDate"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.birthPlace')" for="consumer-birthPlace"></label>
            <input
              type="text"
              class="form-control"
              name="birthPlace"
              id="consumer-birthPlace"
              data-cy="birthPlace"
              :class="{ valid: !v$.birthPlace.$invalid, invalid: v$.birthPlace.$invalid }"
              v-model="v$.birthPlace.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.nationalNo')" for="consumer-nationalNo"></label>
            <input
              type="text"
              class="form-control"
              name="nationalNo"
              id="consumer-nationalNo"
              data-cy="nationalNo"
              :class="{ valid: !v$.nationalNo.$invalid, invalid: v$.nationalNo.$invalid }"
              v-model="v$.nationalNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.registerNo')" for="consumer-registerNo"></label>
            <input
              type="text"
              class="form-control"
              name="registerNo"
              id="consumer-registerNo"
              data-cy="registerNo"
              :class="{ valid: !v$.registerNo.$invalid, invalid: v$.registerNo.$invalid }"
              v-model="v$.registerNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.gender')" for="consumer-gender"></label>
            <select
              class="form-control"
              name="gender"
              :class="{ valid: !v$.gender.$invalid, invalid: v$.gender.$invalid }"
              v-model="v$.gender.$model"
              id="consumer-gender"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.idType')" for="consumer-idType"></label>
            <select
              class="form-control"
              name="idType"
              :class="{ valid: !v$.idType.$invalid, invalid: v$.idType.$invalid }"
              v-model="v$.idType.$model"
              id="consumer-idType"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.idNo')" for="consumer-idNo"></label>
            <input
              type="text"
              class="form-control"
              name="idNo"
              id="consumer-idNo"
              data-cy="idNo"
              :class="{ valid: !v$.idNo.$invalid, invalid: v$.idNo.$invalid }"
              v-model="v$.idNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.mobileNo')" for="consumer-mobileNo"></label>
            <input
              type="text"
              class="form-control"
              name="mobileNo"
              id="consumer-mobileNo"
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
              v-text="t$('digitalDinarApp.consumer.verifiedByEmailOtp')"
              for="consumer-verifiedByEmailOtp"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="verifiedByEmailOtp"
              id="consumer-verifiedByEmailOtp"
              data-cy="verifiedByEmailOtp"
              :class="{ valid: !v$.verifiedByEmailOtp.$invalid, invalid: v$.verifiedByEmailOtp.$invalid }"
              v-model="v$.verifiedByEmailOtp.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.consumer.verifiedByMobileNoOtp')"
              for="consumer-verifiedByMobileNoOtp"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="verifiedByMobileNoOtp"
              id="consumer-verifiedByMobileNoOtp"
              data-cy="verifiedByMobileNoOtp"
              :class="{ valid: !v$.verifiedByMobileNoOtp.$invalid, invalid: v$.verifiedByMobileNoOtp.$invalid }"
              v-model="v$.verifiedByMobileNoOtp.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.email')" for="consumer-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="consumer-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.nidStatus')" for="consumer-nidStatus"></label>
            <input
              type="text"
              class="form-control"
              name="nidStatus"
              id="consumer-nidStatus"
              data-cy="nidStatus"
              :class="{ valid: !v$.nidStatus.$invalid, invalid: v$.nidStatus.$invalid }"
              v-model="v$.nidStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.isForeign')" for="consumer-isForeign"></label>
            <input
              type="checkbox"
              class="form-check"
              name="isForeign"
              id="consumer-isForeign"
              data-cy="isForeign"
              :class="{ valid: !v$.isForeign.$invalid, invalid: v$.isForeign.$invalid }"
              v-model="v$.isForeign.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.consumer.isMobileConnectedWithNid')"
              for="consumer-isMobileConnectedWithNid"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="isMobileConnectedWithNid"
              id="consumer-isMobileConnectedWithNid"
              data-cy="isMobileConnectedWithNid"
              :class="{ valid: !v$.isMobileConnectedWithNid.$invalid, invalid: v$.isMobileConnectedWithNid.$invalid }"
              v-model="v$.isMobileConnectedWithNid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.consumer.walletOwnerStatus')"
              for="consumer-walletOwnerStatus"
            ></label>
            <select
              class="form-control"
              name="walletOwnerStatus"
              :class="{ valid: !v$.walletOwnerStatus.$invalid, invalid: v$.walletOwnerStatus.$invalid }"
              v-model="v$.walletOwnerStatus.$model"
              id="consumer-walletOwnerStatus"
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
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.imageFile')" for="consumer-imageFile"></label>
            <div>
              <img
                v-bind:src="'data:' + consumer.imageFileContentType + ';base64,' + consumer.imageFile"
                style="max-height: 100px"
                v-if="consumer.imageFile"
                alt="consumer image"
              />
              <div v-if="consumer.imageFile" class="form-text text-danger clearfix">
                <span class="pull-left">{{ consumer.imageFileContentType }}, {{ byteSize(consumer.imageFile) }}</span>
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
                v-on:change="setFileData($event, consumer, 'imageFile', true)"
                accept="image/*"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="imageFile"
              id="consumer-imageFile"
              data-cy="imageFile"
              :class="{ valid: !v$.imageFile.$invalid, invalid: v$.imageFile.$invalid }"
              v-model="v$.imageFile.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="imageFileContentType"
              id="consumer-imageFileContentType"
              v-model="consumer.imageFileContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.imageUrlFile')" for="consumer-imageUrlFile"></label>
            <input
              type="text"
              class="form-control"
              name="imageUrlFile"
              id="consumer-imageUrlFile"
              data-cy="imageUrlFile"
              :class="{ valid: !v$.imageUrlFile.$invalid, invalid: v$.imageUrlFile.$invalid }"
              v-model="v$.imageUrlFile.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.postCode')" for="consumer-postCode"></label>
            <input
              type="text"
              class="form-control"
              name="postCode"
              id="consumer-postCode"
              data-cy="postCode"
              :class="{ valid: !v$.postCode.$invalid, invalid: v$.postCode.$invalid }"
              v-model="v$.postCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.address')" for="consumer-address"></label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="consumer-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.lat')" for="consumer-lat"></label>
            <input
              type="number"
              class="form-control"
              name="lat"
              id="consumer-lat"
              data-cy="lat"
              :class="{ valid: !v$.lat.$invalid, invalid: v$.lat.$invalid }"
              v-model.number="v$.lat.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.lng')" for="consumer-lng"></label>
            <input
              type="number"
              class="form-control"
              name="lng"
              id="consumer-lng"
              data-cy="lng"
              :class="{ valid: !v$.lng.$invalid, invalid: v$.lng.$invalid }"
              v-model.number="v$.lng.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.notes')" for="consumer-notes"></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="consumer-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.user')" for="consumer-user"></label>
            <select class="form-control" id="consumer-user" data-cy="user" name="user" v-model="consumer.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="consumer.user && userOption.id === consumer.user.id ? consumer.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.consumer.walletProfile')" for="consumer-walletProfile"></label>
            <select
              class="form-control"
              id="consumer-walletProfile"
              data-cy="walletProfile"
              name="walletProfile"
              v-model="consumer.walletProfile"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  consumer.walletProfile && walletProfileOption.id === consumer.walletProfile.id
                    ? consumer.walletProfile
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
<script lang="ts" src="./consumer-update.component.ts"></script>
