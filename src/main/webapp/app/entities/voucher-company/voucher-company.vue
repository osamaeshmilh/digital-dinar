<template>
  <div>
    <h2 id="page-heading" data-cy="VoucherCompanyHeading">
      <span v-text="t$('digitalDinarApp.voucherCompany.home.title')" id="voucher-company-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.voucherCompany.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'VoucherCompanyCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-voucher-company"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.voucherCompany.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && voucherCompanies && voucherCompanies.length === 0">
      <span v-text="t$('digitalDinarApp.voucherCompany.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="voucherCompanies && voucherCompanies.length > 0">
      <table class="table table-striped" aria-describedby="voucherCompanies">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameAr')">
              <span v-text="t$('digitalDinarApp.voucherCompany.nameAr')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameEn')">
              <span v-text="t$('digitalDinarApp.voucherCompany.nameEn')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameEn'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mobileNo')">
              <span v-text="t$('digitalDinarApp.voucherCompany.mobileNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mobileNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('email')">
              <span v-text="t$('digitalDinarApp.voucherCompany.email')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageFile')">
              <span v-text="t$('digitalDinarApp.voucherCompany.imageFile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageFile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageUrlFile')">
              <span v-text="t$('digitalDinarApp.voucherCompany.imageUrlFile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageUrlFile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('postCode')">
              <span v-text="t$('digitalDinarApp.voucherCompany.postCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'postCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address')">
              <span v-text="t$('digitalDinarApp.voucherCompany.address')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('digitalDinarApp.voucherCompany.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('user.id')">
              <span v-text="t$('digitalDinarApp.voucherCompany.user')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'user.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="voucherCompany in voucherCompanies" :key="voucherCompany.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'VoucherCompanyView', params: { voucherCompanyId: voucherCompany.id } }">{{
                voucherCompany.id
              }}</router-link>
            </td>
            <td>{{ voucherCompany.nameAr }}</td>
            <td>{{ voucherCompany.nameEn }}</td>
            <td>{{ voucherCompany.mobileNo }}</td>
            <td>{{ voucherCompany.email }}</td>
            <td>
              <a
                v-if="voucherCompany.imageFile"
                v-on:click="openFile(voucherCompany.imageFileContentType, voucherCompany.imageFile)"
                v-text="t$('entity.action.open')"
              ></a>
              <span v-if="voucherCompany.imageFile"
                >{{ voucherCompany.imageFileContentType }}, {{ byteSize(voucherCompany.imageFile) }}</span
              >
            </td>
            <td>{{ voucherCompany.imageUrlFile }}</td>
            <td>{{ voucherCompany.postCode }}</td>
            <td>{{ voucherCompany.address }}</td>
            <td>{{ voucherCompany.notes }}</td>
            <td>
              {{ voucherCompany.user ? voucherCompany.user.id : '' }}
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'VoucherCompanyView', params: { voucherCompanyId: voucherCompany.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'VoucherCompanyEdit', params: { voucherCompanyId: voucherCompany.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(voucherCompany)"
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
          id="digitalDinarApp.voucherCompany.delete.question"
          data-cy="voucherCompanyDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-voucherCompany-heading" v-text="t$('digitalDinarApp.voucherCompany.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-voucherCompany"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeVoucherCompany()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="voucherCompanies && voucherCompanies.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./voucher-company.component.ts"></script>
