<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.transaction.home.createOrEditLabel"
          data-cy="TransactionCreateUpdateHeading"
          v-text="t$('digitalDinarApp.transaction.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="transaction.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="transaction.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.transactionReference')"
              for="transaction-transactionReference"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transactionReference"
              id="transaction-transactionReference"
              data-cy="transactionReference"
              :class="{ valid: !v$.transactionReference.$invalid, invalid: v$.transactionReference.$invalid }"
              v-model="v$.transactionReference.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.transactionSecret')"
              for="transaction-transactionSecret"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transactionSecret"
              id="transaction-transactionSecret"
              data-cy="transactionSecret"
              :class="{ valid: !v$.transactionSecret.$invalid, invalid: v$.transactionSecret.$invalid }"
              v-model="v$.transactionSecret.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.transactionOtp')"
              for="transaction-transactionOtp"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transactionOtp"
              id="transaction-transactionOtp"
              data-cy="transactionOtp"
              :class="{ valid: !v$.transactionOtp.$invalid, invalid: v$.transactionOtp.$invalid }"
              v-model="v$.transactionOtp.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.paymentType')" for="transaction-paymentType"></label>
            <select
              class="form-control"
              name="paymentType"
              :class="{ valid: !v$.paymentType.$invalid, invalid: v$.paymentType.$invalid }"
              v-model="v$.paymentType.$model"
              id="transaction-paymentType"
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
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.transactionStatus')"
              for="transaction-transactionStatus"
            ></label>
            <select
              class="form-control"
              name="transactionStatus"
              :class="{ valid: !v$.transactionStatus.$invalid, invalid: v$.transactionStatus.$invalid }"
              v-model="v$.transactionStatus.$model"
              id="transaction-transactionStatus"
              data-cy="transactionStatus"
            >
              <option
                v-for="transactionStatus in transactionStatusValues"
                :key="transactionStatus"
                v-bind:value="transactionStatus"
                v-bind:label="t$('digitalDinarApp.TransactionStatus.' + transactionStatus)"
              >
                {{ transactionStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.transactionType')"
              for="transaction-transactionType"
            ></label>
            <select
              class="form-control"
              name="transactionType"
              :class="{ valid: !v$.transactionType.$invalid, invalid: v$.transactionType.$invalid }"
              v-model="v$.transactionType.$model"
              id="transaction-transactionType"
              data-cy="transactionType"
            >
              <option
                v-for="transactionType in transactionTypeValues"
                :key="transactionType"
                v-bind:value="transactionType"
                v-bind:label="t$('digitalDinarApp.TransactionType.' + transactionType)"
              >
                {{ transactionType }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.vendorReference')"
              for="transaction-vendorReference"
            ></label>
            <input
              type="text"
              class="form-control"
              name="vendorReference"
              id="transaction-vendorReference"
              data-cy="vendorReference"
              :class="{ valid: !v$.vendorReference.$invalid, invalid: v$.vendorReference.$invalid }"
              v-model="v$.vendorReference.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.vendorMessage')"
              for="transaction-vendorMessage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="vendorMessage"
              id="transaction-vendorMessage"
              data-cy="vendorMessage"
              :class="{ valid: !v$.vendorMessage.$invalid, invalid: v$.vendorMessage.$invalid }"
              v-model="v$.vendorMessage.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.amount')" for="transaction-amount"></label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="transaction-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.fees')" for="transaction-fees"></label>
            <input
              type="number"
              class="form-control"
              name="fees"
              id="transaction-fees"
              data-cy="fees"
              :class="{ valid: !v$.fees.$invalid, invalid: v$.fees.$invalid }"
              v-model.number="v$.fees.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.total')" for="transaction-total"></label>
            <input
              type="number"
              class="form-control"
              name="total"
              id="transaction-total"
              data-cy="total"
              :class="{ valid: !v$.total.$invalid, invalid: v$.total.$invalid }"
              v-model.number="v$.total.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.notes')" for="transaction-notes"></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="transaction-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.senderMobileNo')"
              for="transaction-senderMobileNo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="senderMobileNo"
              id="transaction-senderMobileNo"
              data-cy="senderMobileNo"
              :class="{ valid: !v$.senderMobileNo.$invalid, invalid: v$.senderMobileNo.$invalid }"
              v-model="v$.senderMobileNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.senderName')" for="transaction-senderName"></label>
            <input
              type="text"
              class="form-control"
              name="senderName"
              id="transaction-senderName"
              data-cy="senderName"
              :class="{ valid: !v$.senderName.$invalid, invalid: v$.senderName.$invalid }"
              v-model="v$.senderName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.senderType')" for="transaction-senderType"></label>
            <select
              class="form-control"
              name="senderType"
              :class="{ valid: !v$.senderType.$invalid, invalid: v$.senderType.$invalid }"
              v-model="v$.senderType.$model"
              id="transaction-senderType"
              data-cy="senderType"
            >
              <option
                v-for="walletOwnerType in walletOwnerTypeValues"
                :key="walletOwnerType"
                v-bind:value="walletOwnerType"
                v-bind:label="t$('digitalDinarApp.WalletOwnerType.' + walletOwnerType)"
              >
                {{ walletOwnerType }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.senderIdNo')" for="transaction-senderIdNo"></label>
            <input
              type="text"
              class="form-control"
              name="senderIdNo"
              id="transaction-senderIdNo"
              data-cy="senderIdNo"
              :class="{ valid: !v$.senderIdNo.$invalid, invalid: v$.senderIdNo.$invalid }"
              v-model="v$.senderIdNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.senderId')" for="transaction-senderId"></label>
            <input
              type="number"
              class="form-control"
              name="senderId"
              id="transaction-senderId"
              data-cy="senderId"
              :class="{ valid: !v$.senderId.$invalid, invalid: v$.senderId.$invalid }"
              v-model.number="v$.senderId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.receiverName')"
              for="transaction-receiverName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="receiverName"
              id="transaction-receiverName"
              data-cy="receiverName"
              :class="{ valid: !v$.receiverName.$invalid, invalid: v$.receiverName.$invalid }"
              v-model="v$.receiverName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.receiverMobileNo')"
              for="transaction-receiverMobileNo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="receiverMobileNo"
              id="transaction-receiverMobileNo"
              data-cy="receiverMobileNo"
              :class="{ valid: !v$.receiverMobileNo.$invalid, invalid: v$.receiverMobileNo.$invalid }"
              v-model="v$.receiverMobileNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.receiverType')"
              for="transaction-receiverType"
            ></label>
            <select
              class="form-control"
              name="receiverType"
              :class="{ valid: !v$.receiverType.$invalid, invalid: v$.receiverType.$invalid }"
              v-model="v$.receiverType.$model"
              id="transaction-receiverType"
              data-cy="receiverType"
            >
              <option
                v-for="walletOwnerType in walletOwnerTypeValues"
                :key="walletOwnerType"
                v-bind:value="walletOwnerType"
                v-bind:label="t$('digitalDinarApp.WalletOwnerType.' + walletOwnerType)"
              >
                {{ walletOwnerType }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.receiverId')" for="transaction-receiverId"></label>
            <input
              type="number"
              class="form-control"
              name="receiverId"
              id="transaction-receiverId"
              data-cy="receiverId"
              :class="{ valid: !v$.receiverId.$invalid, invalid: v$.receiverId.$invalid }"
              v-model.number="v$.receiverId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.receiverIdNo')"
              for="transaction-receiverIdNo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="receiverIdNo"
              id="transaction-receiverIdNo"
              data-cy="receiverIdNo"
              :class="{ valid: !v$.receiverIdNo.$invalid, invalid: v$.receiverIdNo.$invalid }"
              v-model="v$.receiverIdNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.createdByUserId')"
              for="transaction-createdByUserId"
            ></label>
            <input
              type="number"
              class="form-control"
              name="createdByUserId"
              id="transaction-createdByUserId"
              data-cy="createdByUserId"
              :class="{ valid: !v$.createdByUserId.$invalid, invalid: v$.createdByUserId.$invalid }"
              v-model.number="v$.createdByUserId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.createdBy')" for="transaction-createdBy"></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="transaction-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.transaction.createdDate')" for="transaction-createdDate"></label>
            <div class="d-flex">
              <input
                id="transaction-createdDate"
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
              v-text="t$('digitalDinarApp.transaction.lastModifiedBy')"
              for="transaction-lastModifiedBy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="transaction-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.transaction.lastModifiedDate')"
              for="transaction-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="transaction-lastModifiedDate"
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
<script lang="ts" src="./transaction-update.component.ts"></script>
