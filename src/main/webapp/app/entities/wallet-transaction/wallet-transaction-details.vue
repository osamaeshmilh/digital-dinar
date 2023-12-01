<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="walletTransaction">
        <h2 class="jh-entity-heading" data-cy="walletTransactionDetailsHeading">
          <span v-text="t$('digitalDinarApp.walletTransaction.detail.title')"></span> {{ walletTransaction.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.transactionNo')"></span>
          </dt>
          <dd>
            <span>{{ walletTransaction.transactionNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.amount')"></span>
          </dt>
          <dd>
            <span>{{ walletTransaction.amount }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.walletAction')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.WalletAction.' + walletTransaction.walletAction)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.userPreviousTransactionNo')"></span>
          </dt>
          <dd>
            <span>{{ walletTransaction.userPreviousTransactionNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.totalBeforeAction')"></span>
          </dt>
          <dd>
            <span>{{ walletTransaction.totalBeforeAction }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.totalAfterAction')"></span>
          </dt>
          <dd>
            <span>{{ walletTransaction.totalAfterAction }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.paymentType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.PaymentType.' + walletTransaction.paymentType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.paymentReference')"></span>
          </dt>
          <dd>
            <span>{{ walletTransaction.paymentReference }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.notes')"></span>
          </dt>
          <dd>
            <span>{{ walletTransaction.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.ownerId')"></span>
          </dt>
          <dd>
            <span>{{ walletTransaction.ownerId }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.walletOwnerType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.WalletOwnerType.' + walletTransaction.walletOwnerType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ walletTransaction.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="walletTransaction.createdDate">{{ formatDateLong(walletTransaction.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ walletTransaction.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="walletTransaction.lastModifiedDate">{{ formatDateLong(walletTransaction.lastModifiedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletTransaction.transaction')"></span>
          </dt>
          <dd>
            <div v-if="walletTransaction.transaction.id">
              <router-link :to="{ name: 'TransactionView', params: { transactionId: walletTransaction.transaction.id } }">{{
                walletTransaction.transaction.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="walletTransaction.id"
          :to="{ name: 'WalletTransactionEdit', params: { walletTransactionId: walletTransaction.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./wallet-transaction-details.component.ts"></script>
