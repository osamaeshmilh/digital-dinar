<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="slider">
        <h2 class="jh-entity-heading" data-cy="sliderDetailsHeading">
          <span v-text="t$('digitalDinarApp.slider.detail.title')"></span> {{ slider.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.slider.details')"></span>
          </dt>
          <dd>
            <span>{{ slider.details }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.slider.menuOrder')"></span>
          </dt>
          <dd>
            <span>{{ slider.menuOrder }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.slider.imageFileUrl')"></span>
          </dt>
          <dd>
            <span>{{ slider.imageFileUrl }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.slider.imageFile')"></span>
          </dt>
          <dd>
            <div v-if="slider.imageFile">
              <a v-on:click="openFile(slider.imageFileContentType, slider.imageFile)">
                <img
                  v-bind:src="'data:' + slider.imageFileContentType + ';base64,' + slider.imageFile"
                  style="max-width: 100%"
                  alt="slider image"
                />
              </a>
              {{ slider.imageFileContentType }}, {{ byteSize(slider.imageFile) }}
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.slider.url')"></span>
          </dt>
          <dd>
            <span>{{ slider.url }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.slider.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ slider.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.slider.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="slider.createdDate">{{ formatDateLong(slider.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.slider.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ slider.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.slider.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="slider.lastModifiedDate">{{ formatDateLong(slider.lastModifiedDate) }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="slider.id" :to="{ name: 'SliderEdit', params: { sliderId: slider.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./slider-details.component.ts"></script>
