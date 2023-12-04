<template>
  <div>
    <h2 id="page-heading" data-cy="ActivationHeading">
      <span v-text="t$('digitalDinarApp.activation.home.title')" id="activation-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.activation.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ActivationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-activation"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.activation.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && activations && activations.length === 0">
      <span v-text="t$('digitalDinarApp.activation.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="activations && activations.length > 0">
      <table class="table table-striped" aria-describedby="activations">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mobileNo')">
              <span v-text="t$('digitalDinarApp.activation.mobileNo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mobileNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('email')">
              <span v-text="t$('digitalDinarApp.activation.email')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('code')">
              <span v-text="t$('digitalDinarApp.activation.code')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'code'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sentOn')">
              <span v-text="t$('digitalDinarApp.activation.sentOn')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sentOn'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('validUntil')">
              <span v-text="t$('digitalDinarApp.activation.validUntil')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'validUntil'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isUsed')">
              <span v-text="t$('digitalDinarApp.activation.isUsed')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isUsed'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="activation in activations" :key="activation.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ActivationView', params: { activationId: activation.id } }">{{ activation.id }}</router-link>
            </td>
            <td>{{ activation.mobileNo }}</td>
            <td>{{ activation.email }}</td>
            <td>{{ activation.code }}</td>
            <td>{{ formatDateShort(activation.sentOn) || '' }}</td>
            <td>{{ formatDateShort(activation.validUntil) || '' }}</td>
            <td>{{ activation.isUsed }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ActivationView', params: { activationId: activation.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ActivationEdit', params: { activationId: activation.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(activation)"
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
          id="digitalDinarApp.activation.delete.question"
          data-cy="activationDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-activation-heading" v-text="t$('digitalDinarApp.activation.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-activation"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeActivation()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="activations && activations.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./activation.component.ts"></script>
