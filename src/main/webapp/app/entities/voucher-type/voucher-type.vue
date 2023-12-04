<template>
  <div>
    <h2 id="page-heading" data-cy="VoucherTypeHeading">
      <span v-text="t$('digitalDinarApp.voucherType.home.title')" id="voucher-type-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.voucherType.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'VoucherTypeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-voucher-type"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.voucherType.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && voucherTypes && voucherTypes.length === 0">
      <span v-text="t$('digitalDinarApp.voucherType.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="voucherTypes && voucherTypes.length > 0">
      <table class="table table-striped" aria-describedby="voucherTypes">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameAr')">
              <span v-text="t$('digitalDinarApp.voucherType.nameAr')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameEn')">
              <span v-text="t$('digitalDinarApp.voucherType.nameEn')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameEn'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('slug')">
              <span v-text="t$('digitalDinarApp.voucherType.slug')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'slug'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('amount')">
              <span v-text="t$('digitalDinarApp.voucherType.amount')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('menuOrder')">
              <span v-text="t$('digitalDinarApp.voucherType.menuOrder')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'menuOrder'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('voucherCompany.nameAr')">
              <span v-text="t$('digitalDinarApp.voucherType.voucherCompany')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'voucherCompany.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="voucherType in voucherTypes" :key="voucherType.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'VoucherTypeView', params: { voucherTypeId: voucherType.id } }">{{ voucherType.id }}</router-link>
            </td>
            <td>{{ voucherType.nameAr }}</td>
            <td>{{ voucherType.nameEn }}</td>
            <td>{{ voucherType.slug }}</td>
            <td>{{ voucherType.amount }}</td>
            <td>{{ voucherType.menuOrder }}</td>
            <td>
              <div v-if="voucherType.voucherCompany">
                <router-link :to="{ name: 'VoucherCompanyView', params: { voucherCompanyId: voucherType.voucherCompany.id } }">{{
                  voucherType.voucherCompany.nameAr
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'VoucherTypeView', params: { voucherTypeId: voucherType.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'VoucherTypeEdit', params: { voucherTypeId: voucherType.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(voucherType)"
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
          id="digitalDinarApp.voucherType.delete.question"
          data-cy="voucherTypeDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-voucherType-heading" v-text="t$('digitalDinarApp.voucherType.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-voucherType"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeVoucherType()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="voucherTypes && voucherTypes.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./voucher-type.component.ts"></script>
