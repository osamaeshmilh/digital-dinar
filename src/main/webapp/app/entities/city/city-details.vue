<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="city">
        <h2 class="jh-entity-heading" data-cy="cityDetailsHeading">
          <span v-text="t$('digitalDinarApp.city.detail.title')"></span> {{ city.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.city.nameAr')"></span>
          </dt>
          <dd>
            <span>{{ city.nameAr }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.city.nameEn')"></span>
          </dt>
          <dd>
            <span>{{ city.nameEn }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.city.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ city.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.city.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="city.createdDate">{{ formatDateLong(city.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.city.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ city.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.city.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="city.lastModifiedDate">{{ formatDateLong(city.lastModifiedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.city.country')"></span>
          </dt>
          <dd>
            <div v-if="city.country">
              <router-link :to="{ name: 'CountryView', params: { countryId: city.country.id } }">{{ city.country.nameAr }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="city.id" :to="{ name: 'CityEdit', params: { cityId: city.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./city-details.component.ts"></script>
