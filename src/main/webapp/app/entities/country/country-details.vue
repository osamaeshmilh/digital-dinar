<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="country">
        <h2 class="jh-entity-heading" data-cy="countryDetailsHeading">
          <span v-text="t$('digitalDinarApp.country.detail.title')"></span> {{ country.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.country.nameAr')"></span>
          </dt>
          <dd>
            <span>{{ country.nameAr }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.country.nameEn')"></span>
          </dt>
          <dd>
            <span>{{ country.nameEn }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.country.iso2')"></span>
          </dt>
          <dd>
            <span>{{ country.iso2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.country.iso3')"></span>
          </dt>
          <dd>
            <span>{{ country.iso3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.country.isoNo')"></span>
          </dt>
          <dd>
            <span>{{ country.isoNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.country.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ country.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.country.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="country.createdDate">{{ formatDateLong(country.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.country.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ country.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.country.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="country.lastModifiedDate">{{ formatDateLong(country.lastModifiedDate) }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="country.id" :to="{ name: 'CountryEdit', params: { countryId: country.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./country-details.component.ts"></script>
