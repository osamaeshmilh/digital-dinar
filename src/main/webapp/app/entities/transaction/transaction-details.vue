<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="transaction">
        <h2 class="jh-entity-heading" data-cy="transactionDetailsHeading">
          <span v-text="t$('digitalDinarApp.transaction.detail.title')"></span> {{ transaction.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.transactionReference')"></span>
          </dt>
          <dd>
            <span>{{ transaction.transactionReference }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.transactionSecret')"></span>
          </dt>
          <dd>
            <span>{{ transaction.transactionSecret }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.transactionOtp')"></span>
          </dt>
          <dd>
            <span>{{ transaction.transactionOtp }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.paymentType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.PaymentType.' + transaction.paymentType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.transactionStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.TransactionStatus.' + transaction.transactionStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.transactionType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.TransactionType.' + transaction.transactionType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.vendorReference')"></span>
          </dt>
          <dd>
            <span>{{ transaction.vendorReference }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.vendorMessage')"></span>
          </dt>
          <dd>
            <span>{{ transaction.vendorMessage }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.amount')"></span>
          </dt>
          <dd>
            <span>{{ transaction.amount }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.fees')"></span>
          </dt>
          <dd>
            <span>{{ transaction.fees }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.total')"></span>
          </dt>
          <dd>
            <span>{{ transaction.total }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.notes')"></span>
          </dt>
          <dd>
            <span>{{ transaction.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.senderMobileNo')"></span>
          </dt>
          <dd>
            <span>{{ transaction.senderMobileNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.senderName')"></span>
          </dt>
          <dd>
            <span>{{ transaction.senderName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.senderIdNo')"></span>
          </dt>
          <dd>
            <span>{{ transaction.senderIdNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.receiverName')"></span>
          </dt>
          <dd>
            <span>{{ transaction.receiverName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.receiverMobileNo')"></span>
          </dt>
          <dd>
            <span>{{ transaction.receiverMobileNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.receiverIdNo')"></span>
          </dt>
          <dd>
            <span>{{ transaction.receiverIdNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ transaction.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="transaction.createdDate">{{ formatDateLong(transaction.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ transaction.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="transaction.lastModifiedDate">{{ formatDateLong(transaction.lastModifiedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.sender')"></span>
          </dt>
          <dd>
            <div v-if="transaction.sender">
              <router-link :to="{ name: 'WalletUserView', params: { walletUserId: transaction.sender.id } }">{{
                transaction.sender.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.transaction.receiver')"></span>
          </dt>
          <dd>
            <div v-if="transaction.receiver">
              <router-link :to="{ name: 'WalletUserView', params: { walletUserId: transaction.receiver.id } }">{{
                transaction.receiver.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="transaction.id"
          :to="{ name: 'TransactionEdit', params: { transactionId: transaction.id } }"
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

<script lang="ts" src="./transaction-details.component.ts"></script>
