<template>
  <div>
    <h2 id="page-heading" data-cy="WalletUserHeading">
      <span v-text="t$('digitalDinarApp.walletUser.home.title')" id="wallet-user-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.walletUser.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'WalletUserCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-wallet-user"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.walletUser.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && walletUsers && walletUsers.length === 0">
      <span v-text="t$('digitalDinarApp.walletUser.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="walletUsers && walletUsers.length > 0">
      <table class="table table-striped" aria-describedby="walletUsers">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicFirstName')">
              <span v-text="t$('digitalDinarApp.walletUser.arabicFirstName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicFirstName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicMiddleName')">
              <span v-text="t$('digitalDinarApp.walletUser.arabicMiddleName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicMiddleName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicLastName')">
              <span v-text="t$('digitalDinarApp.walletUser.arabicLastName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicLastName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishFirstName')">
              <span v-text="t$('digitalDinarApp.walletUser.englishFirstName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishFirstName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishMiddleName')">
              <span v-text="t$('digitalDinarApp.walletUser.englishMiddleName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishMiddleName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishLastName')">
              <span v-text="t$('digitalDinarApp.walletUser.englishLastName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishLastName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('commercialRegistryNo')">
              <span v-text="t$('digitalDinarApp.walletUser.commercialRegistryNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'commercialRegistryNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('commercialLicenceNo')">
              <span v-text="t$('digitalDinarApp.walletUser.commercialLicenceNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'commercialLicenceNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('commercialName')">
              <span v-text="t$('digitalDinarApp.walletUser.commercialName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'commercialName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthDate')">
              <span v-text="t$('digitalDinarApp.walletUser.birthDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthPlace')">
              <span v-text="t$('digitalDinarApp.walletUser.birthPlace')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthPlace'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nationalNo')">
              <span v-text="t$('digitalDinarApp.walletUser.nationalNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nationalNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('registerNo')">
              <span v-text="t$('digitalDinarApp.walletUser.registerNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'registerNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('gender')">
              <span v-text="t$('digitalDinarApp.walletUser.gender')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'gender'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idType')">
              <span v-text="t$('digitalDinarApp.walletUser.idType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idNo')">
              <span v-text="t$('digitalDinarApp.walletUser.idNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mobileNo')">
              <span v-text="t$('digitalDinarApp.walletUser.mobileNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mobileNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('verifiedByEmailOtp')">
              <span v-text="t$('digitalDinarApp.walletUser.verifiedByEmailOtp')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'verifiedByEmailOtp'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('verifiedByMobileOtp')">
              <span v-text="t$('digitalDinarApp.walletUser.verifiedByMobileOtp')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'verifiedByMobileOtp'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('email')">
              <span v-text="t$('digitalDinarApp.walletUser.email')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nidStatus')">
              <span v-text="t$('digitalDinarApp.walletUser.nidStatus')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nidStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isForeign')">
              <span v-text="t$('digitalDinarApp.walletUser.isForeign')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isForeign'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isMobileConnectedWithNid')">
              <span v-text="t$('digitalDinarApp.walletUser.isMobileConnectedWithNid')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'isMobileConnectedWithNid'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isKYCVerified')">
              <span v-text="t$('digitalDinarApp.walletUser.isKYCVerified')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isKYCVerified'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletType')">
              <span v-text="t$('digitalDinarApp.walletUser.walletType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletStatus')">
              <span v-text="t$('digitalDinarApp.walletUser.walletStatus')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageFile')">
              <span v-text="t$('digitalDinarApp.walletUser.imageFile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageFile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageUrlFile')">
              <span v-text="t$('digitalDinarApp.walletUser.imageUrlFile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageUrlFile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('postCode')">
              <span v-text="t$('digitalDinarApp.walletUser.postCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'postCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address')">
              <span v-text="t$('digitalDinarApp.walletUser.address')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lat')">
              <span v-text="t$('digitalDinarApp.walletUser.lat')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lat'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lng')">
              <span v-text="t$('digitalDinarApp.walletUser.lng')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lng'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('digitalDinarApp.walletUser.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bankAccountName')">
              <span v-text="t$('digitalDinarApp.walletUser.bankAccountName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bankAccountName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bankAccountNumber')">
              <span v-text="t$('digitalDinarApp.walletUser.bankAccountNumber')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bankAccountNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bankAccountIBAN')">
              <span v-text="t$('digitalDinarApp.walletUser.bankAccountIBAN')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bankAccountIBAN'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bankAccountSWIFT')">
              <span v-text="t$('digitalDinarApp.walletUser.bankAccountSWIFT')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bankAccountSWIFT'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('user.id')">
              <span v-text="t$('digitalDinarApp.walletUser.user')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'user.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('category.nameAr')">
              <span v-text="t$('digitalDinarApp.walletUser.category')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'category.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('city.nameAr')">
              <span v-text="t$('digitalDinarApp.walletUser.city')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'city.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletProfile.nameAr')">
              <span v-text="t$('digitalDinarApp.walletUser.walletProfile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletProfile.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bankBranch.nameAr')">
              <span v-text="t$('digitalDinarApp.walletUser.bankBranch')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bankBranch.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="walletUser in walletUsers" :key="walletUser.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'WalletUserView', params: { walletUserId: walletUser.id } }">{{ walletUser.id }}</router-link>
            </td>
            <td>{{ walletUser.arabicFirstName }}</td>
            <td>{{ walletUser.arabicMiddleName }}</td>
            <td>{{ walletUser.arabicLastName }}</td>
            <td>{{ walletUser.englishFirstName }}</td>
            <td>{{ walletUser.englishMiddleName }}</td>
            <td>{{ walletUser.englishLastName }}</td>
            <td>{{ walletUser.commercialRegistryNo }}</td>
            <td>{{ walletUser.commercialLicenceNo }}</td>
            <td>{{ walletUser.commercialName }}</td>
            <td>{{ walletUser.birthDate }}</td>
            <td>{{ walletUser.birthPlace }}</td>
            <td>{{ walletUser.nationalNo }}</td>
            <td>{{ walletUser.registerNo }}</td>
            <td v-text="t$('digitalDinarApp.Gender.' + walletUser.gender)"></td>
            <td v-text="t$('digitalDinarApp.IdType.' + walletUser.idType)"></td>
            <td>{{ walletUser.idNo }}</td>
            <td>{{ walletUser.mobileNo }}</td>
            <td>{{ walletUser.verifiedByEmailOtp }}</td>
            <td>{{ walletUser.verifiedByMobileOtp }}</td>
            <td>{{ walletUser.email }}</td>
            <td>{{ walletUser.nidStatus }}</td>
            <td>{{ walletUser.isForeign }}</td>
            <td>{{ walletUser.isMobileConnectedWithNid }}</td>
            <td>{{ walletUser.isKYCVerified }}</td>
            <td v-text="t$('digitalDinarApp.WalletType.' + walletUser.walletType)"></td>
            <td v-text="t$('digitalDinarApp.WalletStatus.' + walletUser.walletStatus)"></td>
            <td>
              <a v-if="walletUser.imageFile" v-on:click="openFile(walletUser.imageFileContentType, walletUser.imageFile)">
                <img
                  v-bind:src="'data:' + walletUser.imageFileContentType + ';base64,' + walletUser.imageFile"
                  style="max-height: 30px"
                  alt="walletUser image"
                />
              </a>
              <span v-if="walletUser.imageFile">{{ walletUser.imageFileContentType }}, {{ byteSize(walletUser.imageFile) }}</span>
            </td>
            <td>{{ walletUser.imageUrlFile }}</td>
            <td>{{ walletUser.postCode }}</td>
            <td>{{ walletUser.address }}</td>
            <td>{{ walletUser.lat }}</td>
            <td>{{ walletUser.lng }}</td>
            <td>{{ walletUser.notes }}</td>
            <td>{{ walletUser.bankAccountName }}</td>
            <td>{{ walletUser.bankAccountNumber }}</td>
            <td>{{ walletUser.bankAccountIBAN }}</td>
            <td>{{ walletUser.bankAccountSWIFT }}</td>
            <td>
              {{ walletUser.user ? walletUser.user.id : '' }}
            </td>
            <td>
              <div v-if="walletUser.category">
                <router-link :to="{ name: 'CategoryView', params: { categoryId: walletUser.category.id } }">{{
                  walletUser.category.nameAr
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="walletUser.city">
                <router-link :to="{ name: 'CityView', params: { cityId: walletUser.city.id } }">{{ walletUser.city.nameAr }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="walletUser.walletProfile">
                <router-link :to="{ name: 'WalletProfileView', params: { walletProfileId: walletUser.walletProfile.id } }">{{
                  walletUser.walletProfile.nameAr
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="walletUser.bankBranch">
                <router-link :to="{ name: 'BankBranchView', params: { bankBranchId: walletUser.bankBranch.id } }">{{
                  walletUser.bankBranch.nameAr
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'WalletUserView', params: { walletUserId: walletUser.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'WalletUserEdit', params: { walletUserId: walletUser.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(walletUser)"
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
          id="digitalDinarApp.walletUser.delete.question"
          data-cy="walletUserDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-walletUser-heading" v-text="t$('digitalDinarApp.walletUser.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-walletUser"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeWalletUser()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="walletUsers && walletUsers.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./wallet-user.component.ts"></script>
