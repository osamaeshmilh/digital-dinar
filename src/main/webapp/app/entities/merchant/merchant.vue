<template>
  <div>
    <h2 id="page-heading" data-cy="MerchantHeading">
      <span v-text="t$('digitalDinarApp.merchant.home.title')" id="merchant-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.merchant.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'MerchantCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-merchant"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.merchant.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && merchants && merchants.length === 0">
      <span v-text="t$('digitalDinarApp.merchant.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="merchants && merchants.length > 0">
      <table class="table table-striped" aria-describedby="merchants">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicFirstName')">
              <span v-text="t$('digitalDinarApp.merchant.arabicFirstName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicFirstName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicMiddleName')">
              <span v-text="t$('digitalDinarApp.merchant.arabicMiddleName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicMiddleName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicLastName')">
              <span v-text="t$('digitalDinarApp.merchant.arabicLastName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicLastName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishFirstName')">
              <span v-text="t$('digitalDinarApp.merchant.englishFirstName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishFirstName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishMiddleName')">
              <span v-text="t$('digitalDinarApp.merchant.englishMiddleName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishMiddleName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishLastName')">
              <span v-text="t$('digitalDinarApp.merchant.englishLastName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishLastName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('commercialRegistryNo')">
              <span v-text="t$('digitalDinarApp.merchant.commercialRegistryNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'commercialRegistryNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('commercialLicenceNo')">
              <span v-text="t$('digitalDinarApp.merchant.commercialLicenceNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'commercialLicenceNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthDate')">
              <span v-text="t$('digitalDinarApp.merchant.birthDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthPlace')">
              <span v-text="t$('digitalDinarApp.merchant.birthPlace')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthPlace'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nationalNo')">
              <span v-text="t$('digitalDinarApp.merchant.nationalNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nationalNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('registerNo')">
              <span v-text="t$('digitalDinarApp.merchant.registerNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'registerNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('gender')">
              <span v-text="t$('digitalDinarApp.merchant.gender')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'gender'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idType')">
              <span v-text="t$('digitalDinarApp.merchant.idType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idNo')">
              <span v-text="t$('digitalDinarApp.merchant.idNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mobileNo')">
              <span v-text="t$('digitalDinarApp.merchant.mobileNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mobileNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('verifiedByEmailOtp')">
              <span v-text="t$('digitalDinarApp.merchant.verifiedByEmailOtp')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'verifiedByEmailOtp'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('verifiedByMobileNoOtp')">
              <span v-text="t$('digitalDinarApp.merchant.verifiedByMobileNoOtp')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'verifiedByMobileNoOtp'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('email')">
              <span v-text="t$('digitalDinarApp.merchant.email')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nidStatus')">
              <span v-text="t$('digitalDinarApp.merchant.nidStatus')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nidStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isForeign')">
              <span v-text="t$('digitalDinarApp.merchant.isForeign')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isForeign'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isMobileConnectedWithNid')">
              <span v-text="t$('digitalDinarApp.merchant.isMobileConnectedWithNid')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'isMobileConnectedWithNid'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletOwnerStatus')">
              <span v-text="t$('digitalDinarApp.merchant.walletOwnerStatus')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletOwnerStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageFile')">
              <span v-text="t$('digitalDinarApp.merchant.imageFile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageFile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageUrlFile')">
              <span v-text="t$('digitalDinarApp.merchant.imageUrlFile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageUrlFile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('postCode')">
              <span v-text="t$('digitalDinarApp.merchant.postCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'postCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address')">
              <span v-text="t$('digitalDinarApp.merchant.address')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lat')">
              <span v-text="t$('digitalDinarApp.merchant.lat')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lat'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lng')">
              <span v-text="t$('digitalDinarApp.merchant.lng')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lng'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('digitalDinarApp.merchant.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdBy')">
              <span v-text="t$('digitalDinarApp.merchant.createdBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdDate')">
              <span v-text="t$('digitalDinarApp.merchant.createdDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedBy')">
              <span v-text="t$('digitalDinarApp.merchant.lastModifiedBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedDate')">
              <span v-text="t$('digitalDinarApp.merchant.lastModifiedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('user.id')">
              <span v-text="t$('digitalDinarApp.merchant.user')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'user.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('category.nameAr')">
              <span v-text="t$('digitalDinarApp.merchant.category')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'category.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('city.nameAr')">
              <span v-text="t$('digitalDinarApp.merchant.city')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'city.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletProfile.nameAr')">
              <span v-text="t$('digitalDinarApp.merchant.walletProfile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletProfile.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="merchant in merchants" :key="merchant.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MerchantView', params: { merchantId: merchant.id } }">{{ merchant.id }}</router-link>
            </td>
            <td>{{ merchant.arabicFirstName }}</td>
            <td>{{ merchant.arabicMiddleName }}</td>
            <td>{{ merchant.arabicLastName }}</td>
            <td>{{ merchant.englishFirstName }}</td>
            <td>{{ merchant.englishMiddleName }}</td>
            <td>{{ merchant.englishLastName }}</td>
            <td>{{ merchant.commercialRegistryNo }}</td>
            <td>{{ merchant.commercialLicenceNo }}</td>
            <td>{{ merchant.birthDate }}</td>
            <td>{{ merchant.birthPlace }}</td>
            <td>{{ merchant.nationalNo }}</td>
            <td>{{ merchant.registerNo }}</td>
            <td v-text="t$('digitalDinarApp.Gender.' + merchant.gender)"></td>
            <td v-text="t$('digitalDinarApp.IdType.' + merchant.idType)"></td>
            <td>{{ merchant.idNo }}</td>
            <td>{{ merchant.mobileNo }}</td>
            <td>{{ merchant.verifiedByEmailOtp }}</td>
            <td>{{ merchant.verifiedByMobileNoOtp }}</td>
            <td>{{ merchant.email }}</td>
            <td>{{ merchant.nidStatus }}</td>
            <td>{{ merchant.isForeign }}</td>
            <td>{{ merchant.isMobileConnectedWithNid }}</td>
            <td v-text="t$('digitalDinarApp.WalletOwnerStatus.' + merchant.walletOwnerStatus)"></td>
            <td>
              <a v-if="merchant.imageFile" v-on:click="openFile(merchant.imageFileContentType, merchant.imageFile)">
                <img
                  v-bind:src="'data:' + merchant.imageFileContentType + ';base64,' + merchant.imageFile"
                  style="max-height: 30px"
                  alt="merchant image"
                />
              </a>
              <span v-if="merchant.imageFile">{{ merchant.imageFileContentType }}, {{ byteSize(merchant.imageFile) }}</span>
            </td>
            <td>{{ merchant.imageUrlFile }}</td>
            <td>{{ merchant.postCode }}</td>
            <td>{{ merchant.address }}</td>
            <td>{{ merchant.lat }}</td>
            <td>{{ merchant.lng }}</td>
            <td>{{ merchant.notes }}</td>
            <td>{{ merchant.createdBy }}</td>
            <td>{{ formatDateShort(merchant.createdDate) || '' }}</td>
            <td>{{ merchant.lastModifiedBy }}</td>
            <td>{{ formatDateShort(merchant.lastModifiedDate) || '' }}</td>
            <td>
              {{ merchant.user ? merchant.user.id : '' }}
            </td>
            <td>
              <div v-if="merchant.category">
                <router-link :to="{ name: 'CategoryView', params: { categoryId: merchant.category.id } }">{{
                  merchant.category.nameAr
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="merchant.city">
                <router-link :to="{ name: 'CityView', params: { cityId: merchant.city.id } }">{{ merchant.city.nameAr }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="merchant.walletProfile">
                <router-link :to="{ name: 'WalletProfileView', params: { walletProfileId: merchant.walletProfile.id } }">{{
                  merchant.walletProfile.nameAr
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'MerchantView', params: { merchantId: merchant.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'MerchantEdit', params: { merchantId: merchant.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(merchant)"
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
        <span id="digitalDinarApp.merchant.delete.question" data-cy="merchantDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-merchant-heading" v-text="t$('digitalDinarApp.merchant.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-merchant"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeMerchant()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="merchants && merchants.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./merchant.component.ts"></script>
