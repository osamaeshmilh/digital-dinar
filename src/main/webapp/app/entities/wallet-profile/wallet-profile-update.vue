<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.walletProfile.home.createOrEditLabel"
          data-cy="WalletProfileCreateUpdateHeading"
          v-text="t$('digitalDinarApp.walletProfile.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="walletProfile.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="walletProfile.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletProfile.nameAr')" for="wallet-profile-nameAr"></label>
            <input
              type="text"
              class="form-control"
              name="nameAr"
              id="wallet-profile-nameAr"
              data-cy="nameAr"
              :class="{ valid: !v$.nameAr.$invalid, invalid: v$.nameAr.$invalid }"
              v-model="v$.nameAr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletProfile.nameEn')" for="wallet-profile-nameEn"></label>
            <input
              type="text"
              class="form-control"
              name="nameEn"
              id="wallet-profile-nameEn"
              data-cy="nameEn"
              :class="{ valid: !v$.nameEn.$invalid, invalid: v$.nameEn.$invalid }"
              v-model="v$.nameEn.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.hasTransferAmountLimitPerTransaction')"
              for="wallet-profile-hasTransferAmountLimitPerTransaction"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="hasTransferAmountLimitPerTransaction"
              id="wallet-profile-hasTransferAmountLimitPerTransaction"
              data-cy="hasTransferAmountLimitPerTransaction"
              :class="{
                valid: !v$.hasTransferAmountLimitPerTransaction.$invalid,
                invalid: v$.hasTransferAmountLimitPerTransaction.$invalid,
              }"
              v-model="v$.hasTransferAmountLimitPerTransaction.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.transferAmountLimitPerTransaction')"
              for="wallet-profile-transferAmountLimitPerTransaction"
            ></label>
            <input
              type="number"
              class="form-control"
              name="transferAmountLimitPerTransaction"
              id="wallet-profile-transferAmountLimitPerTransaction"
              data-cy="transferAmountLimitPerTransaction"
              :class="{ valid: !v$.transferAmountLimitPerTransaction.$invalid, invalid: v$.transferAmountLimitPerTransaction.$invalid }"
              v-model.number="v$.transferAmountLimitPerTransaction.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.hasDailyTransferAmountLimit')"
              for="wallet-profile-hasDailyTransferAmountLimit"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="hasDailyTransferAmountLimit"
              id="wallet-profile-hasDailyTransferAmountLimit"
              data-cy="hasDailyTransferAmountLimit"
              :class="{ valid: !v$.hasDailyTransferAmountLimit.$invalid, invalid: v$.hasDailyTransferAmountLimit.$invalid }"
              v-model="v$.hasDailyTransferAmountLimit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.dailyTransferAmountLimit')"
              for="wallet-profile-dailyTransferAmountLimit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="dailyTransferAmountLimit"
              id="wallet-profile-dailyTransferAmountLimit"
              data-cy="dailyTransferAmountLimit"
              :class="{ valid: !v$.dailyTransferAmountLimit.$invalid, invalid: v$.dailyTransferAmountLimit.$invalid }"
              v-model.number="v$.dailyTransferAmountLimit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.canTransferToConsumerWallet')"
              for="wallet-profile-canTransferToConsumerWallet"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="canTransferToConsumerWallet"
              id="wallet-profile-canTransferToConsumerWallet"
              data-cy="canTransferToConsumerWallet"
              :class="{ valid: !v$.canTransferToConsumerWallet.$invalid, invalid: v$.canTransferToConsumerWallet.$invalid }"
              v-model="v$.canTransferToConsumerWallet.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.canTransferToMerchantWallet')"
              for="wallet-profile-canTransferToMerchantWallet"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="canTransferToMerchantWallet"
              id="wallet-profile-canTransferToMerchantWallet"
              data-cy="canTransferToMerchantWallet"
              :class="{ valid: !v$.canTransferToMerchantWallet.$invalid, invalid: v$.canTransferToMerchantWallet.$invalid }"
              v-model="v$.canTransferToMerchantWallet.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.canTransferToConsumer')"
              for="wallet-profile-canTransferToConsumer"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="canTransferToConsumer"
              id="wallet-profile-canTransferToConsumer"
              data-cy="canTransferToConsumer"
              :class="{ valid: !v$.canTransferToConsumer.$invalid, invalid: v$.canTransferToConsumer.$invalid }"
              v-model="v$.canTransferToConsumer.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.canTransferToBank')"
              for="wallet-profile-canTransferToBank"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="canTransferToBank"
              id="wallet-profile-canTransferToBank"
              data-cy="canTransferToBank"
              :class="{ valid: !v$.canTransferToBank.$invalid, invalid: v$.canTransferToBank.$invalid }"
              v-model="v$.canTransferToBank.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.canBuyVouchers')"
              for="wallet-profile-canBuyVouchers"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="canBuyVouchers"
              id="wallet-profile-canBuyVouchers"
              data-cy="canBuyVouchers"
              :class="{ valid: !v$.canBuyVouchers.$invalid, invalid: v$.canBuyVouchers.$invalid }"
              v-model="v$.canBuyVouchers.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.transferToConsumerWalletFees')"
              for="wallet-profile-transferToConsumerWalletFees"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="transferToConsumerWalletFees"
              id="wallet-profile-transferToConsumerWalletFees"
              data-cy="transferToConsumerWalletFees"
              :class="{ valid: !v$.transferToConsumerWalletFees.$invalid, invalid: v$.transferToConsumerWalletFees.$invalid }"
              v-model="v$.transferToConsumerWalletFees.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.transferToMerchantWalletFees')"
              for="wallet-profile-transferToMerchantWalletFees"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="transferToMerchantWalletFees"
              id="wallet-profile-transferToMerchantWalletFees"
              data-cy="transferToMerchantWalletFees"
              :class="{ valid: !v$.transferToMerchantWalletFees.$invalid, invalid: v$.transferToMerchantWalletFees.$invalid }"
              v-model="v$.transferToMerchantWalletFees.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.transferToConsumerFees')"
              for="wallet-profile-transferToConsumerFees"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="transferToConsumerFees"
              id="wallet-profile-transferToConsumerFees"
              data-cy="transferToConsumerFees"
              :class="{ valid: !v$.transferToConsumerFees.$invalid, invalid: v$.transferToConsumerFees.$invalid }"
              v-model="v$.transferToConsumerFees.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.transferToBankFees')"
              for="wallet-profile-transferToBankFees"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="transferToBankFees"
              id="wallet-profile-transferToBankFees"
              data-cy="transferToBankFees"
              :class="{ valid: !v$.transferToBankFees.$invalid, invalid: v$.transferToBankFees.$invalid }"
              v-model="v$.transferToBankFees.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.buyVouchersFees')"
              for="wallet-profile-buyVouchersFees"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="buyVouchersFees"
              id="wallet-profile-buyVouchersFees"
              data-cy="buyVouchersFees"
              :class="{ valid: !v$.buyVouchersFees.$invalid, invalid: v$.buyVouchersFees.$invalid }"
              v-model="v$.buyVouchersFees.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletProfile.createdBy')" for="wallet-profile-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="wallet-profile-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.createdDate')"
              for="wallet-profile-createdDate"
            ></label>
            <div class="d-flex">
              <input
                id="wallet-profile-createdDate"
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
              v-text="t$('digitalDinarApp.walletProfile.lastModifiedBy')"
              for="wallet-profile-lastModifiedBy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="wallet-profile-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletProfile.lastModifiedDate')"
              for="wallet-profile-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="wallet-profile-lastModifiedDate"
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
<script lang="ts" src="./wallet-profile-update.component.ts"></script>
