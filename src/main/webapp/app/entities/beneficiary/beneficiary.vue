<template>
  <div>
    <h2 id="page-heading" data-cy="BeneficiaryHeading">
      <span v-text="t$('digitalDinarApp.beneficiary.home.title')" id="beneficiary-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.beneficiary.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'BeneficiaryCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-beneficiary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.beneficiary.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && beneficiaries && beneficiaries.length === 0">
      <span v-text="t$('digitalDinarApp.beneficiary.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="beneficiaries && beneficiaries.length > 0">
      <table class="table table-striped" aria-describedby="beneficiaries">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicFirstName')">
              <span v-text="t$('digitalDinarApp.beneficiary.arabicFirstName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicFirstName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicLastName')">
              <span v-text="t$('digitalDinarApp.beneficiary.arabicLastName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicLastName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishFirstName')">
              <span v-text="t$('digitalDinarApp.beneficiary.englishFirstName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishFirstName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishLastName')">
              <span v-text="t$('digitalDinarApp.beneficiary.englishLastName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishLastName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bankAccountName')">
              <span v-text="t$('digitalDinarApp.beneficiary.bankAccountName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bankAccountName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bankAccountNumber')">
              <span v-text="t$('digitalDinarApp.beneficiary.bankAccountNumber')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bankAccountNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idType')">
              <span v-text="t$('digitalDinarApp.beneficiary.idType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idNo')">
              <span v-text="t$('digitalDinarApp.beneficiary.idNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mobileNo')">
              <span v-text="t$('digitalDinarApp.beneficiary.mobileNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mobileNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('email')">
              <span v-text="t$('digitalDinarApp.beneficiary.email')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('digitalDinarApp.beneficiary.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isVerified')">
              <span v-text="t$('digitalDinarApp.beneficiary.isVerified')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isVerified'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('hasTransferred')">
              <span v-text="t$('digitalDinarApp.beneficiary.hasTransferred')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'hasTransferred'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdBy')">
              <span v-text="t$('digitalDinarApp.beneficiary.createdBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdDate')">
              <span v-text="t$('digitalDinarApp.beneficiary.createdDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedBy')">
              <span v-text="t$('digitalDinarApp.beneficiary.lastModifiedBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedDate')">
              <span v-text="t$('digitalDinarApp.beneficiary.lastModifiedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bankBranch.nameAr')">
              <span v-text="t$('digitalDinarApp.beneficiary.bankBranch')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bankBranch.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletUser.id')">
              <span v-text="t$('digitalDinarApp.beneficiary.walletUser')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletUser.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="beneficiary in beneficiaries" :key="beneficiary.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'BeneficiaryView', params: { beneficiaryId: beneficiary.id } }">{{ beneficiary.id }}</router-link>
            </td>
            <td>{{ beneficiary.arabicFirstName }}</td>
            <td>{{ beneficiary.arabicLastName }}</td>
            <td>{{ beneficiary.englishFirstName }}</td>
            <td>{{ beneficiary.englishLastName }}</td>
            <td>{{ beneficiary.bankAccountName }}</td>
            <td>{{ beneficiary.bankAccountNumber }}</td>
            <td v-text="t$('digitalDinarApp.IdType.' + beneficiary.idType)"></td>
            <td>{{ beneficiary.idNo }}</td>
            <td>{{ beneficiary.mobileNo }}</td>
            <td>{{ beneficiary.email }}</td>
            <td>{{ beneficiary.notes }}</td>
            <td>{{ beneficiary.isVerified }}</td>
            <td>{{ beneficiary.hasTransferred }}</td>
            <td>{{ beneficiary.createdBy }}</td>
            <td>{{ formatDateShort(beneficiary.createdDate) || '' }}</td>
            <td>{{ beneficiary.lastModifiedBy }}</td>
            <td>{{ formatDateShort(beneficiary.lastModifiedDate) || '' }}</td>
            <td>
              <div v-if="beneficiary.bankBranch">
                <router-link :to="{ name: 'BankBranchView', params: { bankBranchId: beneficiary.bankBranch.id } }">{{
                  beneficiary.bankBranch.nameAr
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="beneficiary.walletUser">
                <router-link :to="{ name: 'WalletUserView', params: { walletUserId: beneficiary.walletUser.id } }">{{
                  beneficiary.walletUser.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'BeneficiaryView', params: { beneficiaryId: beneficiary.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'BeneficiaryEdit', params: { beneficiaryId: beneficiary.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(beneficiary)"
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
          id="digitalDinarApp.beneficiary.delete.question"
          data-cy="beneficiaryDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-beneficiary-heading" v-text="t$('digitalDinarApp.beneficiary.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-beneficiary"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeBeneficiary()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="beneficiaries && beneficiaries.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./beneficiary.component.ts"></script>
