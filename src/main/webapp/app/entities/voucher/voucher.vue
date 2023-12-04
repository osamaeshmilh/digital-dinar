<template>
  <div>
    <h2 id="page-heading" data-cy="VoucherHeading">
      <span v-text="t$('digitalDinarApp.voucher.home.title')" id="voucher-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.voucher.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'VoucherCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-voucher"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.voucher.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && vouchers && vouchers.length === 0">
      <span v-text="t$('digitalDinarApp.voucher.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="vouchers && vouchers.length > 0">
      <table class="table table-striped" aria-describedby="vouchers">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('code')">
              <span v-text="t$('digitalDinarApp.voucher.code')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'code'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('amount')">
              <span v-text="t$('digitalDinarApp.voucher.amount')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('serialNumber')">
              <span v-text="t$('digitalDinarApp.voucher.serialNumber')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'serialNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isSold')">
              <span v-text="t$('digitalDinarApp.voucher.isSold')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isSold'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellTransactionId')">
              <span v-text="t$('digitalDinarApp.voucher.sellTransactionId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sellTransactionId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdBy')">
              <span v-text="t$('digitalDinarApp.voucher.createdBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdDate')">
              <span v-text="t$('digitalDinarApp.voucher.createdDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedBy')">
              <span v-text="t$('digitalDinarApp.voucher.lastModifiedBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedDate')">
              <span v-text="t$('digitalDinarApp.voucher.lastModifiedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('voucherType.nameAr')">
              <span v-text="t$('digitalDinarApp.voucher.voucherType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'voucherType.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('voucherCompany.nameAr')">
              <span v-text="t$('digitalDinarApp.voucher.voucherCompany')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'voucherCompany.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="voucher in vouchers" :key="voucher.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'VoucherView', params: { voucherId: voucher.id } }">{{ voucher.id }}</router-link>
            </td>
            <td>{{ voucher.code }}</td>
            <td>{{ voucher.amount }}</td>
            <td>{{ voucher.serialNumber }}</td>
            <td>{{ voucher.isSold }}</td>
            <td>{{ voucher.sellTransactionId }}</td>
            <td>{{ voucher.createdBy }}</td>
            <td>{{ formatDateShort(voucher.createdDate) || '' }}</td>
            <td>{{ voucher.lastModifiedBy }}</td>
            <td>{{ formatDateShort(voucher.lastModifiedDate) || '' }}</td>
            <td>
              <div v-if="voucher.voucherType">
                <router-link :to="{ name: 'VoucherTypeView', params: { voucherTypeId: voucher.voucherType.id } }">{{
                  voucher.voucherType.nameAr
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="voucher.voucherCompany">
                <router-link :to="{ name: 'VoucherCompanyView', params: { voucherCompanyId: voucher.voucherCompany.id } }">{{
                  voucher.voucherCompany.nameAr
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'VoucherView', params: { voucherId: voucher.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'VoucherEdit', params: { voucherId: voucher.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(voucher)"
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
        <span id="digitalDinarApp.voucher.delete.question" data-cy="voucherDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-voucher-heading" v-text="t$('digitalDinarApp.voucher.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-voucher"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeVoucher()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="vouchers && vouchers.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./voucher.component.ts"></script>
