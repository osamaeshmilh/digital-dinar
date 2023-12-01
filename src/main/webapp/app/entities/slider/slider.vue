<template>
  <div>
    <h2 id="page-heading" data-cy="SliderHeading">
      <span v-text="t$('digitalDinarApp.slider.home.title')" id="slider-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('digitalDinarApp.slider.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SliderCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-slider"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('digitalDinarApp.slider.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sliders && sliders.length === 0">
      <span v-text="t$('digitalDinarApp.slider.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="sliders && sliders.length > 0">
      <table class="table table-striped" aria-describedby="sliders">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('details')">
              <span v-text="t$('digitalDinarApp.slider.details')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'details'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('menuOrder')">
              <span v-text="t$('digitalDinarApp.slider.menuOrder')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'menuOrder'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageFileUrl')">
              <span v-text="t$('digitalDinarApp.slider.imageFileUrl')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageFileUrl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('imageFile')">
              <span v-text="t$('digitalDinarApp.slider.imageFile')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'imageFile'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('url')">
              <span v-text="t$('digitalDinarApp.slider.url')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'url'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="slider in sliders" :key="slider.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SliderView', params: { sliderId: slider.id } }">{{ slider.id }}</router-link>
            </td>
            <td>{{ slider.details }}</td>
            <td>{{ slider.menuOrder }}</td>
            <td>{{ slider.imageFileUrl }}</td>
            <td>
              <a v-if="slider.imageFile" v-on:click="openFile(slider.imageFileContentType, slider.imageFile)">
                <img
                  v-bind:src="'data:' + slider.imageFileContentType + ';base64,' + slider.imageFile"
                  style="max-height: 30px"
                  alt="slider image"
                />
              </a>
              <span v-if="slider.imageFile">{{ slider.imageFileContentType }}, {{ byteSize(slider.imageFile) }}</span>
            </td>
            <td>{{ slider.url }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'SliderView', params: { sliderId: slider.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'SliderEdit', params: { sliderId: slider.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(slider)"
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
        <span id="digitalDinarApp.slider.delete.question" data-cy="sliderDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-slider-heading" v-text="t$('digitalDinarApp.slider.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-slider"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSlider()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="sliders && sliders.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./slider.component.ts"></script>
