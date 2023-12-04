<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="beneficiary">
        <h2 class="jh-entity-heading" data-cy="beneficiaryDetailsHeading">
          <span v-text="t$('digitalDinarApp.beneficiary.detail.title')"></span> {{ beneficiary.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.arabicFirstName')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.arabicFirstName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.arabicLastName')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.arabicLastName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.englishFirstName')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.englishFirstName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.englishLastName')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.englishLastName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.bankAccountName')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.bankAccountName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.bankAccountNumber')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.bankAccountNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.idType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.IdType.' + beneficiary.idType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.idNo')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.idNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.mobileNo')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.mobileNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.email')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.notes')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.isVerified')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.isVerified }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.hasTransferred')"></span>
          </dt>
          <dd>
            <span>{{ beneficiary.hasTransferred }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.bankBranch')"></span>
          </dt>
          <dd>
            <div v-if="beneficiary.bankBranch">
              <router-link :to="{ name: 'BankBranchView', params: { bankBranchId: beneficiary.bankBranch.id } }">{{
                beneficiary.bankBranch.nameAr
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.beneficiary.walletUser')"></span>
          </dt>
          <dd>
            <div v-if="beneficiary.walletUser">
              <router-link :to="{ name: 'WalletUserView', params: { walletUserId: beneficiary.walletUser.id } }">{{
                beneficiary.walletUser.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="beneficiary.id"
          :to="{ name: 'BeneficiaryEdit', params: { beneficiaryId: beneficiary.id } }"
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

<script lang="ts" src="./beneficiary-details.component.ts"></script>
