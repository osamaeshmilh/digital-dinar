<template>
  <div>
    <h2 id="page-heading" data-cy="ConsumerHeading">
      <span v-text="t$('digitalDinarApp.consumer.home.title')" id="consumer-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.consumer.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ConsumerCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-consumer"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.consumer.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && consumers && consumers.length === 0">
      <span v-text="t$('digitalDinarApp.consumer.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="consumers && consumers.length > 0">
      <table class="table table-striped" aria-describedby="consumers">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicFirstName')">
              <span v-text="t$('digitalDinarApp.consumer.arabicFirstName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicFirstName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicMiddleName')">
              <span v-text="t$('digitalDinarApp.consumer.arabicMiddleName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicMiddleName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arabicLastName')">
              <span v-text="t$('digitalDinarApp.consumer.arabicLastName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arabicLastName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishFirstName')">
              <span v-text="t$('digitalDinarApp.consumer.englishFirstName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishFirstName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishMiddleName')">
              <span v-text="t$('digitalDinarApp.consumer.englishMiddleName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishMiddleName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('englishLastName')">
              <span v-text="t$('digitalDinarApp.consumer.englishLastName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'englishLastName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthDate')">
              <span v-text="t$('digitalDinarApp.consumer.birthDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthPlace')">
              <span v-text="t$('digitalDinarApp.consumer.birthPlace')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthPlace'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nationalNo')">
              <span v-text="t$('digitalDinarApp.consumer.nationalNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nationalNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('registerNo')">
              <span v-text="t$('digitalDinarApp.consumer.registerNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'registerNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('gender')">
              <span v-text="t$('digitalDinarApp.consumer.gender')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'gender'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idType')">
              <span v-text="t$('digitalDinarApp.consumer.idType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idNo')">
              <span v-text="t$('digitalDinarApp.consumer.idNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mobileNo')">
              <span v-text="t$('digitalDinarApp.consumer.mobileNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mobileNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('verifiedByEmailOtp')">
              <span v-text="t$('digitalDinarApp.consumer.verifiedByEmailOtp')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'verifiedByEmailOtp'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('verifiedByMobileNoOtp')">
              <span v-text="t$('digitalDinarApp.consumer.verifiedByMobileNoOtp')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'verifiedByMobileNoOtp'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('email')">
              <span v-text="t$('digitalDinarApp.consumer.email')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nidStatus')">
              <span v-text="t$('digitalDinarApp.consumer.nidStatus')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nidStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isForeign')">
              <span v-text="t$('digitalDinarApp.consumer.isForeign')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isForeign'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isMobileConnectedWithNid')">
              <span v-text="t$('digitalDinarApp.consumer.isMobileConnectedWithNid')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'isMobileConnectedWithNid'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletOwnerStatus')">
              <span v-text="t$('digitalDinarApp.consumer.walletOwnerStatus')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletOwnerStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageFile')">
              <span v-text="t$('digitalDinarApp.consumer.imageFile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageFile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageUrlFile')">
              <span v-text="t$('digitalDinarApp.consumer.imageUrlFile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageUrlFile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('postCode')">
              <span v-text="t$('digitalDinarApp.consumer.postCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'postCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address')">
              <span v-text="t$('digitalDinarApp.consumer.address')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lat')">
              <span v-text="t$('digitalDinarApp.consumer.lat')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lat'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lng')">
              <span v-text="t$('digitalDinarApp.consumer.lng')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lng'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('digitalDinarApp.consumer.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdBy')">
              <span v-text="t$('digitalDinarApp.consumer.createdBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdDate')">
              <span v-text="t$('digitalDinarApp.consumer.createdDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedBy')">
              <span v-text="t$('digitalDinarApp.consumer.lastModifiedBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedDate')">
              <span v-text="t$('digitalDinarApp.consumer.lastModifiedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('user.id')">
              <span v-text="t$('digitalDinarApp.consumer.user')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'user.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletProfile.nameAr')">
              <span v-text="t$('digitalDinarApp.consumer.walletProfile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletProfile.nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="consumer in consumers" :key="consumer.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ConsumerView', params: { consumerId: consumer.id } }">{{ consumer.id }}</router-link>
            </td>
            <td>{{ consumer.arabicFirstName }}</td>
            <td>{{ consumer.arabicMiddleName }}</td>
            <td>{{ consumer.arabicLastName }}</td>
            <td>{{ consumer.englishFirstName }}</td>
            <td>{{ consumer.englishMiddleName }}</td>
            <td>{{ consumer.englishLastName }}</td>
            <td>{{ consumer.birthDate }}</td>
            <td>{{ consumer.birthPlace }}</td>
            <td>{{ consumer.nationalNo }}</td>
            <td>{{ consumer.registerNo }}</td>
            <td v-text="t$('digitalDinarApp.Gender.' + consumer.gender)"></td>
            <td v-text="t$('digitalDinarApp.IdType.' + consumer.idType)"></td>
            <td>{{ consumer.idNo }}</td>
            <td>{{ consumer.mobileNo }}</td>
            <td>{{ consumer.verifiedByEmailOtp }}</td>
            <td>{{ consumer.verifiedByMobileNoOtp }}</td>
            <td>{{ consumer.email }}</td>
            <td>{{ consumer.nidStatus }}</td>
            <td>{{ consumer.isForeign }}</td>
            <td>{{ consumer.isMobileConnectedWithNid }}</td>
            <td v-text="t$('digitalDinarApp.WalletOwnerStatus.' + consumer.walletOwnerStatus)"></td>
            <td>
              <a v-if="consumer.imageFile" v-on:click="openFile(consumer.imageFileContentType, consumer.imageFile)">
                <img
                  v-bind:src="'data:' + consumer.imageFileContentType + ';base64,' + consumer.imageFile"
                  style="max-height: 30px"
                  alt="consumer image"
                />
              </a>
              <span v-if="consumer.imageFile">{{ consumer.imageFileContentType }}, {{ byteSize(consumer.imageFile) }}</span>
            </td>
            <td>{{ consumer.imageUrlFile }}</td>
            <td>{{ consumer.postCode }}</td>
            <td>{{ consumer.address }}</td>
            <td>{{ consumer.lat }}</td>
            <td>{{ consumer.lng }}</td>
            <td>{{ consumer.notes }}</td>
            <td>{{ consumer.createdBy }}</td>
            <td>{{ formatDateShort(consumer.createdDate) || '' }}</td>
            <td>{{ consumer.lastModifiedBy }}</td>
            <td>{{ formatDateShort(consumer.lastModifiedDate) || '' }}</td>
            <td>
              {{ consumer.user ? consumer.user.id : '' }}
            </td>
            <td>
              <div v-if="consumer.walletProfile">
                <router-link :to="{ name: 'WalletProfileView', params: { walletProfileId: consumer.walletProfile.id } }">{{
                  consumer.walletProfile.nameAr
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ConsumerView', params: { consumerId: consumer.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ConsumerEdit', params: { consumerId: consumer.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(consumer)"
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
        <span id="digitalDinarApp.consumer.delete.question" data-cy="consumerDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-consumer-heading" v-text="t$('digitalDinarApp.consumer.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-consumer"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeConsumer()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="consumers && consumers.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./consumer.component.ts"></script>
