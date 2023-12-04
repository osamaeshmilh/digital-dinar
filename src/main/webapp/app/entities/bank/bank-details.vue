<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="bank">
        <h2 class="jh-entity-heading" data-cy="bankDetailsHeading">
          <span v-text="t$('digitalDinarApp.bank.detail.title')"></span> {{ bank.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.bank.nameAr')"></span>
          </dt>
          <dd>
            <span>{{ bank.nameAr }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.bank.namEn')"></span>
          </dt>
          <dd>
            <span>{{ bank.namEn }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.bank.swiftCode')"></span>
          </dt>
          <dd>
            <span>{{ bank.swiftCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.bank.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ bank.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.bank.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="bank.createdDate">{{ formatDateLong(bank.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.bank.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ bank.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.bank.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="bank.lastModifiedDate">{{ formatDateLong(bank.lastModifiedDate) }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="bank.id" :to="{ name: 'BankEdit', params: { bankId: bank.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./bank-details.component.ts"></script>
