<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.paymentMethod.home.createOrEditLabel"
          data-cy="PaymentMethodCreateUpdateHeading"
          v-text="t$('digitalDinarApp.paymentMethod.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="paymentMethod.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="paymentMethod.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.paymentMethod.name')" for="payment-method-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="payment-method-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.paymentMethod.nameAr')" for="payment-method-nameAr"></label>
            <input
              type="text"
              class="form-control"
              name="nameAr"
              id="payment-method-nameAr"
              data-cy="nameAr"
              :class="{ valid: !v$.nameAr.$invalid, invalid: v$.nameAr.$invalid }"
              v-model="v$.nameAr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.paymentMethod.nameEn')" for="payment-method-nameEn"></label>
            <input
              type="text"
              class="form-control"
              name="nameEn"
              id="payment-method-nameEn"
              data-cy="nameEn"
              :class="{ valid: !v$.nameEn.$invalid, invalid: v$.nameEn.$invalid }"
              v-model="v$.nameEn.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.paymentMethod.menuOrder')" for="payment-method-menuOrder"></label>
            <input
              type="number"
              class="form-control"
              name="menuOrder"
              id="payment-method-menuOrder"
              data-cy="menuOrder"
              :class="{ valid: !v$.menuOrder.$invalid, invalid: v$.menuOrder.$invalid }"
              v-model.number="v$.menuOrder.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.paymentMethod.imageFileUrl')"
              for="payment-method-imageFileUrl"
            ></label>
            <input
              type="text"
              class="form-control"
              name="imageFileUrl"
              id="payment-method-imageFileUrl"
              data-cy="imageFileUrl"
              :class="{ valid: !v$.imageFileUrl.$invalid, invalid: v$.imageFileUrl.$invalid }"
              v-model="v$.imageFileUrl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.paymentMethod.imageFile')" for="payment-method-imageFile"></label>
            <div>
              <img
                v-bind:src="'data:' + paymentMethod.imageFileContentType + ';base64,' + paymentMethod.imageFile"
                style="max-height: 100px"
                v-if="paymentMethod.imageFile"
                alt="paymentMethod image"
              />
              <div v-if="paymentMethod.imageFile" class="form-text text-danger clearfix">
                <span class="pull-left">{{ paymentMethod.imageFileContentType }}, {{ byteSize(paymentMethod.imageFile) }}</span>
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
                v-on:change="setFileData($event, paymentMethod, 'imageFile', true)"
                accept="image/*"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="imageFile"
              id="payment-method-imageFile"
              data-cy="imageFile"
              :class="{ valid: !v$.imageFile.$invalid, invalid: v$.imageFile.$invalid }"
              v-model="v$.imageFile.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="imageFileContentType"
              id="payment-method-imageFileContentType"
              v-model="paymentMethod.imageFileContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.paymentMethod.details')" for="payment-method-details"></label>
            <input
              type="text"
              class="form-control"
              name="details"
              id="payment-method-details"
              data-cy="details"
              :class="{ valid: !v$.details.$invalid, invalid: v$.details.$invalid }"
              v-model="v$.details.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.paymentMethod.feePercentage')"
              for="payment-method-feePercentage"
            ></label>
            <input
              type="number"
              class="form-control"
              name="feePercentage"
              id="payment-method-feePercentage"
              data-cy="feePercentage"
              :class="{ valid: !v$.feePercentage.$invalid, invalid: v$.feePercentage.$invalid }"
              v-model.number="v$.feePercentage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.paymentMethod.paymentType')"
              for="payment-method-paymentType"
            ></label>
            <select
              class="form-control"
              name="paymentType"
              :class="{ valid: !v$.paymentType.$invalid, invalid: v$.paymentType.$invalid }"
              v-model="v$.paymentType.$model"
              id="payment-method-paymentType"
              data-cy="paymentType"
            >
              <option
                v-for="paymentType in paymentTypeValues"
                :key="paymentType"
                v-bind:value="paymentType"
                v-bind:label="t$('digitalDinarApp.PaymentType.' + paymentType)"
              >
                {{ paymentType }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.paymentMethod.notes')" for="payment-method-notes"></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="payment-method-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.paymentMethod.createdBy')" for="payment-method-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="payment-method-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.paymentMethod.createdDate')"
              for="payment-method-createdDate"
            ></label>
            <div class="d-flex">
              <input
                id="payment-method-createdDate"
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
              v-text="t$('digitalDinarApp.paymentMethod.lastModifiedBy')"
              for="payment-method-lastModifiedBy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="payment-method-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.paymentMethod.lastModifiedDate')"
              for="payment-method-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="payment-method-lastModifiedDate"
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
<script lang="ts" src="./payment-method-update.component.ts"></script>
