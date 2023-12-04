<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="walletProfile">
        <h2 class="jh-entity-heading" data-cy="walletProfileDetailsHeading">
          <span v-text="t$('digitalDinarApp.walletProfile.detail.title')"></span> {{ walletProfile.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.nameAr')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.nameAr }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.nameEn')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.nameEn }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.hasTransferAmountLimitPerTransaction')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.hasTransferAmountLimitPerTransaction }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.transferAmountLimitPerTransaction')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.transferAmountLimitPerTransaction }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.hasDailyTransferAmountLimit')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.hasDailyTransferAmountLimit }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.dailyTransferAmountLimit')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.dailyTransferAmountLimit }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.canTransferToConsumerWallet')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.canTransferToConsumerWallet }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.canTransferToMerchantWallet')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.canTransferToMerchantWallet }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.canTransferToConsumer')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.canTransferToConsumer }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.canTransferToBank')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.canTransferToBank }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.canBuyVouchers')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.canBuyVouchers }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.transferToConsumerWalletFees')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.transferToConsumerWalletFees }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.transferToMerchantWalletFees')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.transferToMerchantWalletFees }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.transferToConsumerFees')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.transferToConsumerFees }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.transferToBankFees')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.transferToBankFees }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.buyVouchersFees')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.buyVouchersFees }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="walletProfile.createdDate">{{ formatDateLong(walletProfile.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ walletProfile.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletProfile.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="walletProfile.lastModifiedDate">{{ formatDateLong(walletProfile.lastModifiedDate) }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="walletProfile.id"
          :to="{ name: 'WalletProfileEdit', params: { walletProfileId: walletProfile.id } }"
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

<script lang="ts" src="./wallet-profile-details.component.ts"></script>
