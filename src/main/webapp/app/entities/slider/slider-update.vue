<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="digitalDinarApp.slider.home.createOrEditLabel"
          data-cy="SliderCreateUpdateHeading"
          v-text="t$('digitalDinarApp.slider.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="slider.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="slider.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.slider.details')" for="slider-details"></label>
            <input
              type="text"
              class="form-control"
              name="details"
              id="slider-details"
              data-cy="details"
              :class="{ valid: !v$.details.$invalid, invalid: v$.details.$invalid }"
              v-model="v$.details.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.slider.menuOrder')" for="slider-menuOrder"></label>
            <input
              type="number"
              class="form-control"
              name="menuOrder"
              id="slider-menuOrder"
              data-cy="menuOrder"
              :class="{ valid: !v$.menuOrder.$invalid, invalid: v$.menuOrder.$invalid }"
              v-model.number="v$.menuOrder.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.slider.imageFileUrl')" for="slider-imageFileUrl"></label>
            <input
              type="text"
              class="form-control"
              name="imageFileUrl"
              id="slider-imageFileUrl"
              data-cy="imageFileUrl"
              :class="{ valid: !v$.imageFileUrl.$invalid, invalid: v$.imageFileUrl.$invalid }"
              v-model="v$.imageFileUrl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.slider.imageFile')" for="slider-imageFile"></label>
            <div>
              <img
                v-bind:src="'data:' + slider.imageFileContentType + ';base64,' + slider.imageFile"
                style="max-height: 100px"
                v-if="slider.imageFile"
                alt="slider image"
              />
              <div v-if="slider.imageFile" class="form-text text-danger clearfix">
                <span class="pull-left">{{ slider.imageFileContentType }}, {{ byteSize(slider.imageFile) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('imageFile', 'imageFileContentType', 'file_imageFile')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <label for="file_imageFile" v-text="t$('entity.action.addimage')" class="btn btn-primary pull-right"></label>
              <input
                type="file"
                ref="file_imageFile"
                id="file_imageFile"
                style="display: none"
                data-cy="imageFile"
                v-on:change="setFileData($event, slider, 'imageFile', true)"
                accept="image/*"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="imageFile"
              id="slider-imageFile"
              data-cy="imageFile"
              :class="{ valid: !v$.imageFile.$invalid, invalid: v$.imageFile.$invalid }"
              v-model="v$.imageFile.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="imageFileContentType"
              id="slider-imageFileContentType"
              v-model="slider.imageFileContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('digitalDinarApp.slider.url')" for="slider-url"></label>
            <input
              type="text"
              class="form-control"
              name="url"
              id="slider-url"
              data-cy="url"
              :class="{ valid: !v$.url.$invalid, invalid: v$.url.$invalid }"
              v-model="v$.url.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./slider-update.component.ts"></script>
