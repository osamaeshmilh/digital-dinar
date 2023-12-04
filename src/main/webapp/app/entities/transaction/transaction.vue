<template>
  <div>
    <h2 id="page-heading" data-cy="TransactionHeading">
      <span v-text="t$('digitalDinarApp.transaction.home.title')" id="transaction-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.transaction.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'TransactionCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-transaction"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.transaction.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && transactions && transactions.length === 0">
      <span v-text="t$('digitalDinarApp.transaction.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="transactions && transactions.length > 0">
      <table class="table table-striped" aria-describedby="transactions">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transactionReference')">
              <span v-text="t$('digitalDinarApp.transaction.transactionReference')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionReference'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transactionSecret')">
              <span v-text="t$('digitalDinarApp.transaction.transactionSecret')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionSecret'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transactionOtp')">
              <span v-text="t$('digitalDinarApp.transaction.transactionOtp')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionOtp'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('paymentType')">
              <span v-text="t$('digitalDinarApp.transaction.paymentType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'paymentType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transactionStatus')">
              <span v-text="t$('digitalDinarApp.transaction.transactionStatus')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transactionType')">
              <span v-text="t$('digitalDinarApp.transaction.transactionType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('vendorReference')">
              <span v-text="t$('digitalDinarApp.transaction.vendorReference')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'vendorReference'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('vendorMessage')">
              <span v-text="t$('digitalDinarApp.transaction.vendorMessage')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'vendorMessage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('amount')">
              <span v-text="t$('digitalDinarApp.transaction.amount')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fees')">
              <span v-text="t$('digitalDinarApp.transaction.fees')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fees'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('total')">
              <span v-text="t$('digitalDinarApp.transaction.total')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'total'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('digitalDinarApp.transaction.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('senderMobileNo')">
              <span v-text="t$('digitalDinarApp.transaction.senderMobileNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'senderMobileNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('senderName')">
              <span v-text="t$('digitalDinarApp.transaction.senderName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'senderName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('senderIdNo')">
              <span v-text="t$('digitalDinarApp.transaction.senderIdNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'senderIdNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('receiverName')">
              <span v-text="t$('digitalDinarApp.transaction.receiverName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'receiverName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('receiverMobileNo')">
              <span v-text="t$('digitalDinarApp.transaction.receiverMobileNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'receiverMobileNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('receiverIdNo')">
              <span v-text="t$('digitalDinarApp.transaction.receiverIdNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'receiverIdNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdBy')">
              <span v-text="t$('digitalDinarApp.transaction.createdBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdDate')">
              <span v-text="t$('digitalDinarApp.transaction.createdDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedBy')">
              <span v-text="t$('digitalDinarApp.transaction.lastModifiedBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedDate')">
              <span v-text="t$('digitalDinarApp.transaction.lastModifiedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sender.id')">
              <span v-text="t$('digitalDinarApp.transaction.sender')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sender.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('receiver.id')">
              <span v-text="t$('digitalDinarApp.transaction.receiver')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'receiver.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="transaction in transactions" :key="transaction.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TransactionView', params: { transactionId: transaction.id } }">{{ transaction.id }}</router-link>
            </td>
            <td>{{ transaction.transactionReference }}</td>
            <td>{{ transaction.transactionSecret }}</td>
            <td>{{ transaction.transactionOtp }}</td>
            <td v-text="t$('digitalDinarApp.PaymentType.' + transaction.paymentType)"></td>
            <td v-text="t$('digitalDinarApp.TransactionStatus.' + transaction.transactionStatus)"></td>
            <td v-text="t$('digitalDinarApp.TransactionType.' + transaction.transactionType)"></td>
            <td>{{ transaction.vendorReference }}</td>
            <td>{{ transaction.vendorMessage }}</td>
            <td>{{ transaction.amount }}</td>
            <td>{{ transaction.fees }}</td>
            <td>{{ transaction.total }}</td>
            <td>{{ transaction.notes }}</td>
            <td>{{ transaction.senderMobileNo }}</td>
            <td>{{ transaction.senderName }}</td>
            <td>{{ transaction.senderIdNo }}</td>
            <td>{{ transaction.receiverName }}</td>
            <td>{{ transaction.receiverMobileNo }}</td>
            <td>{{ transaction.receiverIdNo }}</td>
            <td>{{ transaction.createdBy }}</td>
            <td>{{ formatDateShort(transaction.createdDate) || '' }}</td>
            <td>{{ transaction.lastModifiedBy }}</td>
            <td>{{ formatDateShort(transaction.lastModifiedDate) || '' }}</td>
            <td>
              <div v-if="transaction.sender">
                <router-link :to="{ name: 'WalletUserView', params: { walletUserId: transaction.sender.id } }">{{
                  transaction.sender.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="transaction.receiver">
                <router-link :to="{ name: 'WalletUserView', params: { walletUserId: transaction.receiver.id } }">{{
                  transaction.receiver.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TransactionView', params: { transactionId: transaction.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TransactionEdit', params: { transactionId: transaction.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(transaction)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="digitalDinarApp.transaction.delete.question"
          data-cy="transactionDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-transaction-heading" v-text="t$('digitalDinarApp.transaction.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-transaction"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTransaction()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="transactions && transactions.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./transaction.component.ts"></script>
