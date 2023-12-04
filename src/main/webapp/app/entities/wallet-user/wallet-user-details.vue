<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="walletUser">
        <h2 class="jh-entity-heading" data-cy="walletUserDetailsHeading">
          <span v-text="t$('digitalDinarApp.walletUser.detail.title')"></span> {{ walletUser.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.arabicFirstName')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.arabicFirstName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.arabicMiddleName')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.arabicMiddleName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.arabicLastName')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.arabicLastName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.englishFirstName')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.englishFirstName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.englishMiddleName')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.englishMiddleName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.englishLastName')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.englishLastName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.commercialRegistryNo')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.commercialRegistryNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.commercialLicenceNo')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.commercialLicenceNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.commercialName')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.commercialName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.birthDate')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.birthDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.birthPlace')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.birthPlace }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.nationalNo')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.nationalNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.registerNo')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.registerNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.gender')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.Gender.' + walletUser.gender)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.idType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.IdType.' + walletUser.idType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.idNo')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.idNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.mobileNo')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.mobileNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.verifiedByEmailOtp')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.verifiedByEmailOtp }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.verifiedByMobileOtp')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.verifiedByMobileOtp }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.email')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.nidStatus')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.nidStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.isForeign')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.isForeign }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.isMobileConnectedWithNid')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.isMobileConnectedWithNid }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.isKYCVerified')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.isKYCVerified }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.walletType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.WalletType.' + walletUser.walletType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.walletStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.WalletStatus.' + walletUser.walletStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.imageFile')"></span>
          </dt>
          <dd>
            <div v-if="walletUser.imageFile">
              <a v-on:click="openFile(walletUser.imageFileContentType, walletUser.imageFile)">
                <img
                  v-bind:src="'data:' + walletUser.imageFileContentType + ';base64,' + walletUser.imageFile"
                  style="max-width: 100%"
                  alt="walletUser image"
                />
              </a>
              {{ walletUser.imageFileContentType }}, {{ byteSize(walletUser.imageFile) }}
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.imageUrlFile')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.imageUrlFile }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.postCode')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.postCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.address')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.lat')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.lat }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.lng')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.lng }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.notes')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.bankAccountName')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.bankAccountName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.bankAccountNumber')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.bankAccountNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.bankAccountIBAN')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.bankAccountIBAN }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.bankAccountSWIFT')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.bankAccountSWIFT }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="walletUser.createdDate">{{ formatDateLong(walletUser.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ walletUser.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="walletUser.lastModifiedDate">{{ formatDateLong(walletUser.lastModifiedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.user')"></span>
          </dt>
          <dd>
            {{ walletUser.user ? walletUser.user.id : '' }}
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.category')"></span>
          </dt>
          <dd>
            <div v-if="walletUser.category">
              <router-link :to="{ name: 'CategoryView', params: { categoryId: walletUser.category.id } }">{{
                walletUser.category.nameAr
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.city')"></span>
          </dt>
          <dd>
            <div v-if="walletUser.city">
              <router-link :to="{ name: 'CityView', params: { cityId: walletUser.city.id } }">{{ walletUser.city.nameAr }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.walletProfile')"></span>
          </dt>
          <dd>
            <div v-if="walletUser.walletProfile">
              <router-link :to="{ name: 'WalletProfileView', params: { walletProfileId: walletUser.walletProfile.id } }">{{
                walletUser.walletProfile.nameAr
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.walletUser.bankBranch')"></span>
          </dt>
          <dd>
            <div v-if="walletUser.bankBranch">
              <router-link :to="{ name: 'BankBranchView', params: { bankBranchId: walletUser.bankBranch.id } }">{{
                walletUser.bankBranch.nameAr
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="walletUser.id"
          :to="{ name: 'WalletUserEdit', params: { walletUserId: walletUser.id } }"
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

<script lang="ts" src="./wallet-user-details.component.ts"></script>
