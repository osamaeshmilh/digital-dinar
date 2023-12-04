<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="bankBranch">
        <h2 class="jh-entity-heading" data-cy="bankBranchDetailsHeading">
          <span v-text="t$('digitalDinarApp.bankBranch.detail.title')"></span> {{ bankBranch.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.bankBranch.nameAr')"></span>
          </dt>
          <dd>
            <span>{{ bankBranch.nameAr }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.bankBranch.namEn')"></span>
          </dt>
          <dd>
            <span>{{ bankBranch.namEn }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.bankBranch.address')"></span>
          </dt>
          <dd>
            <span>{{ bankBranch.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.bankBranch.bank')"></span>
          </dt>
          <dd>
            <div v-if="bankBranch.bank">
              <router-link :to="{ name: 'BankView', params: { bankId: bankBranch.bank.id } }">{{ bankBranch.bank.nameAr }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="bankBranch.id"
          :to="{ name: 'BankBranchEdit', params: { bankBranchId: bankBranch.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./bank-branch-details.component.ts"></script>
