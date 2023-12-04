<template>
  <div>
    <h2 id="page-heading" data-cy="BankBranchHeading">
      <span v-text="t$('digitalDinarApp.bankBranch.home.title')" id="bank-branch-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.bankBranch.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'BankBranchCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-bank-branch"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.bankBranch.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && bankBranches && bankBranches.length === 0">
      <span v-text="t$('digitalDinarApp.bankBranch.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="bankBranches && bankBranches.length > 0">
      <table class="table table-striped" aria-describedby="bankBranches">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameAr')">
              <span v-text="t$('digitalDinarApp.bankBranch.nameAr')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('namEn')">
              <span v-text="t$('digitalDinarApp.bankBranch.namEn')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'namEn'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address')">
              <span v-text="t$('digitalDinarApp.bankBranch.address')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bank.nameAr')">
              <span v-text="t$('digitalDinarApp.bankBranch.bank')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bank.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="bankBranch in bankBranches" :key="bankBranch.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'BankBranchView', params: { bankBranchId: bankBranch.id } }">{{ bankBranch.id }}</router-link>
            </td>
            <td>{{ bankBranch.nameAr }}</td>
            <td>{{ bankBranch.namEn }}</td>
            <td>{{ bankBranch.address }}</td>
            <td>
              <div v-if="bankBranch.bank">
                <router-link :to="{ name: 'BankView', params: { bankId: bankBranch.bank.id } }">{{ bankBranch.bank.nameAr }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'BankBranchView', params: { bankBranchId: bankBranch.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'BankBranchEdit', params: { bankBranchId: bankBranch.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(bankBranch)"
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
          id="digitalDinarApp.bankBranch.delete.question"
          data-cy="bankBranchDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-bankBranch-heading" v-text="t$('digitalDinarApp.bankBranch.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-bankBranch"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeBankBranch()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="bankBranches && bankBranches.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./bank-branch.component.ts"></script>
