<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.walletTransaction.home.createOrEditLabel"
          data-cy="WalletTransactionCreateUpdateHeading"
          v-text="t$('digitalDinarApp.walletTransaction.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="walletTransaction.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="walletTransaction.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.transactionNo')"
              for="wallet-transaction-transactionNo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transactionNo"
              id="wallet-transaction-transactionNo"
              data-cy="transactionNo"
              :class="{ valid: !v$.transactionNo.$invalid, invalid: v$.transactionNo.$invalid }"
              v-model="v$.transactionNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.amount')"
              for="wallet-transaction-amount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="wallet-transaction-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.walletAction')"
              for="wallet-transaction-walletAction"
            ></label>
            <select
              class="form-control"
              name="walletAction"
              :class="{ valid: !v$.walletAction.$invalid, invalid: v$.walletAction.$invalid }"
              v-model="v$.walletAction.$model"
              id="wallet-transaction-walletAction"
              data-cy="walletAction"
            >
              <option
                v-for="walletAction in walletActionValues"
                :key="walletAction"
                v-bind:value="walletAction"
                v-bind:label="t$('digitalDinarApp.WalletAction.' + walletAction)"
              >
                {{ walletAction }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.userPreviousTransactionNo')"
              for="wallet-transaction-userPreviousTransactionNo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="userPreviousTransactionNo"
              id="wallet-transaction-userPreviousTransactionNo"
              data-cy="userPreviousTransactionNo"
              :class="{ valid: !v$.userPreviousTransactionNo.$invalid, invalid: v$.userPreviousTransactionNo.$invalid }"
              v-model="v$.userPreviousTransactionNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.totalBeforeAction')"
              for="wallet-transaction-totalBeforeAction"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalBeforeAction"
              id="wallet-transaction-totalBeforeAction"
              data-cy="totalBeforeAction"
              :class="{ valid: !v$.totalBeforeAction.$invalid, invalid: v$.totalBeforeAction.$invalid }"
              v-model.number="v$.totalBeforeAction.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.totalAfterAction')"
              for="wallet-transaction-totalAfterAction"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalAfterAction"
              id="wallet-transaction-totalAfterAction"
              data-cy="totalAfterAction"
              :class="{ valid: !v$.totalAfterAction.$invalid, invalid: v$.totalAfterAction.$invalid }"
              v-model.number="v$.totalAfterAction.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.paymentType')"
              for="wallet-transaction-paymentType"
            ></label>
            <select
              class="form-control"
              name="paymentType"
              :class="{ valid: !v$.paymentType.$invalid, invalid: v$.paymentType.$invalid }"
              v-model="v$.paymentType.$model"
              id="wallet-transaction-paymentType"
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
              v-text="t$('digitalDinarApp.walletTransaction.paymentReference')"
              for="wallet-transaction-paymentReference"
            ></label>
            <input
              type="text"
              class="form-control"
              name="paymentReference"
              id="wallet-transaction-paymentReference"
              data-cy="paymentReference"
              :class="{ valid: !v$.paymentReference.$invalid, invalid: v$.paymentReference.$invalid }"
              v-model="v$.paymentReference.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.walletTransaction.notes')" for="wallet-transaction-notes"></label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="wallet-transaction-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.ownerId')"
              for="wallet-transaction-ownerId"
            ></label>
            <input
              type="number"
              class="form-control"
              name="ownerId"
              id="wallet-transaction-ownerId"
              data-cy="ownerId"
              :class="{ valid: !v$.ownerId.$invalid, invalid: v$.ownerId.$invalid }"
              v-model.number="v$.ownerId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.walletOwnerType')"
              for="wallet-transaction-walletOwnerType"
            ></label>
            <select
              class="form-control"
              name="walletOwnerType"
              :class="{ valid: !v$.walletOwnerType.$invalid, invalid: v$.walletOwnerType.$invalid }"
              v-model="v$.walletOwnerType.$model"
              id="wallet-transaction-walletOwnerType"
              data-cy="walletOwnerType"
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
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.createdBy')"
              for="wallet-transaction-createdBy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="wallet-transaction-createdBy"
              data-cy="createdBy"
              :class="{ valid: !v$.createdBy.$invalid, invalid: v$.createdBy.$invalid }"
              v-model="v$.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.createdDate')"
              for="wallet-transaction-createdDate"
            ></label>
            <div class="d-flex">
              <input
                id="wallet-transaction-createdDate"
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
              v-text="t$('digitalDinarApp.walletTransaction.lastModifiedBy')"
              for="wallet-transaction-lastModifiedBy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastModifiedBy"
              id="wallet-transaction-lastModifiedBy"
              data-cy="lastModifiedBy"
              :class="{ valid: !v$.lastModifiedBy.$invalid, invalid: v$.lastModifiedBy.$invalid }"
              v-model="v$.lastModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('digitalDinarApp.walletTransaction.lastModifiedDate')"
              for="wallet-transaction-lastModifiedDate"
            ></label>
            <div class="d-flex">
              <input
                id="wallet-transaction-lastModifiedDate"
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
              v-text="t$('digitalDinarApp.walletTransaction.transaction')"
              for="wallet-transaction-transaction"
            ></label>
            <select
              class="form-control"
              id="wallet-transaction-transaction"
              data-cy="transaction"
              name="transaction"
              v-model="walletTransaction.transaction"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  walletTransaction.transaction && transactionOption.id === walletTransaction.transaction.id
                    ? walletTransaction.transaction
                    : transactionOption
                "
                v-for="transactionOption in transactions"
                :key="transactionOption.id"
              >
                {{ transactionOption.id }}
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
<script lang="ts" src="./wallet-transaction-update.component.ts"></script>
