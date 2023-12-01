<template>
  <div>
    <h2 id="page-heading" data-cy="WalletProfileHeading">
      <span v-text="t$('digitalDinarApp.walletProfile.home.title')" id="wallet-profile-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.walletProfile.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'WalletProfileCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-wallet-profile"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.walletProfile.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && walletProfiles && walletProfiles.length === 0">
      <span v-text="t$('digitalDinarApp.walletProfile.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="walletProfiles && walletProfiles.length > 0">
      <table class="table table-striped" aria-describedby="walletProfiles">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameAr')">
              <span v-text="t$('digitalDinarApp.walletProfile.nameAr')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameEn')">
              <span v-text="t$('digitalDinarApp.walletProfile.nameEn')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameEn'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('hasTransferAmountLimitPerTransaction')">
              <span v-text="t$('digitalDinarApp.walletProfile.hasTransferAmountLimitPerTransaction')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'hasTransferAmountLimitPerTransaction'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transferAmountLimitPerTransaction')">
              <span v-text="t$('digitalDinarApp.walletProfile.transferAmountLimitPerTransaction')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'transferAmountLimitPerTransaction'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('hasDailyTransferAmountLimit')">
              <span v-text="t$('digitalDinarApp.walletProfile.hasDailyTransferAmountLimit')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'hasDailyTransferAmountLimit'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('dailyTransferAmountLimit')">
              <span v-text="t$('digitalDinarApp.walletProfile.dailyTransferAmountLimit')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'dailyTransferAmountLimit'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('canTransferToConsumerWallet')">
              <span v-text="t$('digitalDinarApp.walletProfile.canTransferToConsumerWallet')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'canTransferToConsumerWallet'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('canTransferToMerchantWallet')">
              <span v-text="t$('digitalDinarApp.walletProfile.canTransferToMerchantWallet')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'canTransferToMerchantWallet'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('canTransferToConsumer')">
              <span v-text="t$('digitalDinarApp.walletProfile.canTransferToConsumer')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'canTransferToConsumer'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('canTransferToBank')">
              <span v-text="t$('digitalDinarApp.walletProfile.canTransferToBank')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'canTransferToBank'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('canBuyVouchers')">
              <span v-text="t$('digitalDinarApp.walletProfile.canBuyVouchers')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'canBuyVouchers'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transferToConsumerWalletFees')">
              <span v-text="t$('digitalDinarApp.walletProfile.transferToConsumerWalletFees')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'transferToConsumerWalletFees'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transferToMerchantWalletFees')">
              <span v-text="t$('digitalDinarApp.walletProfile.transferToMerchantWalletFees')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'transferToMerchantWalletFees'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transferToConsumerFees')">
              <span v-text="t$('digitalDinarApp.walletProfile.transferToConsumerFees')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transferToConsumerFees'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transferToBankFees')">
              <span v-text="t$('digitalDinarApp.walletProfile.transferToBankFees')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transferToBankFees'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('buyVouchersFees')">
              <span v-text="t$('digitalDinarApp.walletProfile.buyVouchersFees')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'buyVouchersFees'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="walletProfile in walletProfiles" :key="walletProfile.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'WalletProfileView', params: { walletProfileId: walletProfile.id } }">{{
                walletProfile.id
              }}</router-link>
            </td>
            <td>{{ walletProfile.nameAr }}</td>
            <td>{{ walletProfile.nameEn }}</td>
            <td>{{ walletProfile.hasTransferAmountLimitPerTransaction }}</td>
            <td>{{ walletProfile.transferAmountLimitPerTransaction }}</td>
            <td>{{ walletProfile.hasDailyTransferAmountLimit }}</td>
            <td>{{ walletProfile.dailyTransferAmountLimit }}</td>
            <td>{{ walletProfile.canTransferToConsumerWallet }}</td>
            <td>{{ walletProfile.canTransferToMerchantWallet }}</td>
            <td>{{ walletProfile.canTransferToConsumer }}</td>
            <td>{{ walletProfile.canTransferToBank }}</td>
            <td>{{ walletProfile.canBuyVouchers }}</td>
            <td>{{ walletProfile.transferToConsumerWalletFees }}</td>
            <td>{{ walletProfile.transferToMerchantWalletFees }}</td>
            <td>{{ walletProfile.transferToConsumerFees }}</td>
            <td>{{ walletProfile.transferToBankFees }}</td>
            <td>{{ walletProfile.buyVouchersFees }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'WalletProfileView', params: { walletProfileId: walletProfile.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'WalletProfileEdit', params: { walletProfileId: walletProfile.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(walletProfile)"
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
          id="digitalDinarApp.walletProfile.delete.question"
          data-cy="walletProfileDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-walletProfile-heading" v-text="t$('digitalDinarApp.walletProfile.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-walletProfile"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeWalletProfile()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="walletProfiles && walletProfiles.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./wallet-profile.component.ts"></script>
