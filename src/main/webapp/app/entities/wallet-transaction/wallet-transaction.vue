<template>
  <div>
    <h2 id="page-heading" data-cy="WalletTransactionHeading">
      <span v-text="t$('digitalDinarApp.walletTransaction.home.title')" id="wallet-transaction-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.walletTransaction.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'WalletTransactionCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-wallet-transaction"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.walletTransaction.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && walletTransactions && walletTransactions.length === 0">
      <span v-text="t$('digitalDinarApp.walletTransaction.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="walletTransactions && walletTransactions.length > 0">
      <table class="table table-striped" aria-describedby="walletTransactions">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transactionNo')">
              <span v-text="t$('digitalDinarApp.walletTransaction.transactionNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('amount')">
              <span v-text="t$('digitalDinarApp.walletTransaction.amount')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletAction')">
              <span v-text="t$('digitalDinarApp.walletTransaction.walletAction')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletAction'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('userPreviousTransactionNo')">
              <span v-text="t$('digitalDinarApp.walletTransaction.userPreviousTransactionNo')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'userPreviousTransactionNo'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalBeforeAction')">
              <span v-text="t$('digitalDinarApp.walletTransaction.totalBeforeAction')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalBeforeAction'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalAfterAction')">
              <span v-text="t$('digitalDinarApp.walletTransaction.totalAfterAction')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalAfterAction'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('paymentType')">
              <span v-text="t$('digitalDinarApp.walletTransaction.paymentType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'paymentType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('paymentReference')">
              <span v-text="t$('digitalDinarApp.walletTransaction.paymentReference')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'paymentReference'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('digitalDinarApp.walletTransaction.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletOwnerType')">
              <span v-text="t$('digitalDinarApp.walletTransaction.walletOwnerType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletOwnerType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdBy')">
              <span v-text="t$('digitalDinarApp.walletTransaction.createdBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdDate')">
              <span v-text="t$('digitalDinarApp.walletTransaction.createdDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedBy')">
              <span v-text="t$('digitalDinarApp.walletTransaction.lastModifiedBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedDate')">
              <span v-text="t$('digitalDinarApp.walletTransaction.lastModifiedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transaction.id')">
              <span v-text="t$('digitalDinarApp.walletTransaction.transaction')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transaction.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletUser.id')">
              <span v-text="t$('digitalDinarApp.walletTransaction.walletUser')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletUser.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="walletTransaction in walletTransactions" :key="walletTransaction.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'WalletTransactionView', params: { walletTransactionId: walletTransaction.id } }">{{
                walletTransaction.id
              }}</router-link>
            </td>
            <td>{{ walletTransaction.transactionNo }}</td>
            <td>{{ walletTransaction.amount }}</td>
            <td v-text="t$('digitalDinarApp.WalletAction.' + walletTransaction.walletAction)"></td>
            <td>{{ walletTransaction.userPreviousTransactionNo }}</td>
            <td>{{ walletTransaction.totalBeforeAction }}</td>
            <td>{{ walletTransaction.totalAfterAction }}</td>
            <td v-text="t$('digitalDinarApp.PaymentType.' + walletTransaction.paymentType)"></td>
            <td>{{ walletTransaction.paymentReference }}</td>
            <td>{{ walletTransaction.notes }}</td>
            <td v-text="t$('digitalDinarApp.WalletType.' + walletTransaction.walletOwnerType)"></td>
            <td>{{ walletTransaction.createdBy }}</td>
            <td>{{ formatDateShort(walletTransaction.createdDate) || '' }}</td>
            <td>{{ walletTransaction.lastModifiedBy }}</td>
            <td>{{ formatDateShort(walletTransaction.lastModifiedDate) || '' }}</td>
            <td>
              <div v-if="walletTransaction.transaction">
                <router-link :to="{ name: 'TransactionView', params: { transactionId: walletTransaction.transaction.id } }">{{
                  walletTransaction.transaction.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="walletTransaction.walletUser">
                <router-link :to="{ name: 'WalletUserView', params: { walletUserId: walletTransaction.walletUser.id } }">{{
                  walletTransaction.walletUser.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'WalletTransactionView', params: { walletTransactionId: walletTransaction.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'WalletTransactionEdit', params: { walletTransactionId: walletTransaction.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(walletTransaction)"
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
          id="digitalDinarApp.walletTransaction.delete.question"
          data-cy="walletTransactionDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-walletTransaction-heading" v-text="t$('digitalDinarApp.walletTransaction.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-walletTransaction"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeWalletTransaction()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="walletTransactions && walletTransactions.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./wallet-transaction.component.ts"></script>
