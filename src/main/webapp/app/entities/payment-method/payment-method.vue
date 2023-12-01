<template>
  <div>
    <h2 id="page-heading" data-cy="PaymentMethodHeading">
      <span v-text="t$('digitalDinarApp.paymentMethod.home.title')" id="payment-method-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.paymentMethod.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'PaymentMethodCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-payment-method"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.paymentMethod.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && paymentMethods && paymentMethods.length === 0">
      <span v-text="t$('digitalDinarApp.paymentMethod.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="paymentMethods && paymentMethods.length > 0">
      <table class="table table-striped" aria-describedby="paymentMethods">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="t$('digitalDinarApp.paymentMethod.name')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameAr')">
              <span v-text="t$('digitalDinarApp.paymentMethod.nameAr')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameAr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameEn')">
              <span v-text="t$('digitalDinarApp.paymentMethod.nameEn')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameEn'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('menuOrder')">
              <span v-text="t$('digitalDinarApp.paymentMethod.menuOrder')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'menuOrder'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageFileUrl')">
              <span v-text="t$('digitalDinarApp.paymentMethod.imageFileUrl')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageFileUrl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageFile')">
              <span v-text="t$('digitalDinarApp.paymentMethod.imageFile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageFile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('details')">
              <span v-text="t$('digitalDinarApp.paymentMethod.details')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'details'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('feePercentage')">
              <span v-text="t$('digitalDinarApp.paymentMethod.feePercentage')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'feePercentage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('paymentType')">
              <span v-text="t$('digitalDinarApp.paymentMethod.paymentType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'paymentType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span v-text="t$('digitalDinarApp.paymentMethod.notes')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdBy')">
              <span v-text="t$('digitalDinarApp.paymentMethod.createdBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdDate')">
              <span v-text="t$('digitalDinarApp.paymentMethod.createdDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedBy')">
              <span v-text="t$('digitalDinarApp.paymentMethod.lastModifiedBy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedBy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastModifiedDate')">
              <span v-text="t$('digitalDinarApp.paymentMethod.lastModifiedDate')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="paymentMethod in paymentMethods" :key="paymentMethod.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PaymentMethodView', params: { paymentMethodId: paymentMethod.id } }">{{
                paymentMethod.id
              }}</router-link>
            </td>
            <td>{{ paymentMethod.name }}</td>
            <td>{{ paymentMethod.nameAr }}</td>
            <td>{{ paymentMethod.nameEn }}</td>
            <td>{{ paymentMethod.menuOrder }}</td>
            <td>{{ paymentMethod.imageFileUrl }}</td>
            <td>
              <a v-if="paymentMethod.imageFile" v-on:click="openFile(paymentMethod.imageFileContentType, paymentMethod.imageFile)">
                <img
                  v-bind:src="'data:' + paymentMethod.imageFileContentType + ';base64,' + paymentMethod.imageFile"
                  style="max-height: 30px"
                  alt="paymentMethod image"
                />
              </a>
              <span v-if="paymentMethod.imageFile">{{ paymentMethod.imageFileContentType }}, {{ byteSize(paymentMethod.imageFile) }}</span>
            </td>
            <td>{{ paymentMethod.details }}</td>
            <td>{{ paymentMethod.feePercentage }}</td>
            <td v-text="t$('digitalDinarApp.PaymentType.' + paymentMethod.paymentType)"></td>
            <td>{{ paymentMethod.notes }}</td>
            <td>{{ paymentMethod.createdBy }}</td>
            <td>{{ formatDateShort(paymentMethod.createdDate) || '' }}</td>
            <td>{{ paymentMethod.lastModifiedBy }}</td>
            <td>{{ formatDateShort(paymentMethod.lastModifiedDate) || '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'PaymentMethodView', params: { paymentMethodId: paymentMethod.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'PaymentMethodEdit', params: { paymentMethodId: paymentMethod.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(paymentMethod)"
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
          id="digitalDinarApp.paymentMethod.delete.question"
          data-cy="paymentMethodDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-paymentMethod-heading" v-text="t$('digitalDinarApp.paymentMethod.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-paymentMethod"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePaymentMethod()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="paymentMethods && paymentMethods.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./payment-method.component.ts"></script>
