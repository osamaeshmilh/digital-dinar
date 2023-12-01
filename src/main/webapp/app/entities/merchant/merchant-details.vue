<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="merchant">
        <h2 class="jh-entity-heading" data-cy="merchantDetailsHeading">
          <span v-text="t$('digitalDinarApp.merchant.detail.title')"></span> {{ merchant.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.arabicFirstName')"></span>
          </dt>
          <dd>
            <span>{{ merchant.arabicFirstName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.arabicMiddleName')"></span>
          </dt>
          <dd>
            <span>{{ merchant.arabicMiddleName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.arabicLastName')"></span>
          </dt>
          <dd>
            <span>{{ merchant.arabicLastName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.englishFirstName')"></span>
          </dt>
          <dd>
            <span>{{ merchant.englishFirstName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.englishMiddleName')"></span>
          </dt>
          <dd>
            <span>{{ merchant.englishMiddleName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.englishLastName')"></span>
          </dt>
          <dd>
            <span>{{ merchant.englishLastName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.commercialRegistryNo')"></span>
          </dt>
          <dd>
            <span>{{ merchant.commercialRegistryNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.commercialLicenceNo')"></span>
          </dt>
          <dd>
            <span>{{ merchant.commercialLicenceNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.birthDate')"></span>
          </dt>
          <dd>
            <span>{{ merchant.birthDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.birthPlace')"></span>
          </dt>
          <dd>
            <span>{{ merchant.birthPlace }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.nationalNo')"></span>
          </dt>
          <dd>
            <span>{{ merchant.nationalNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.registerNo')"></span>
          </dt>
          <dd>
            <span>{{ merchant.registerNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.gender')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.Gender.' + merchant.gender)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.idType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.IdType.' + merchant.idType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.idNo')"></span>
          </dt>
          <dd>
            <span>{{ merchant.idNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.mobileNo')"></span>
          </dt>
          <dd>
            <span>{{ merchant.mobileNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.verifiedByEmailOtp')"></span>
          </dt>
          <dd>
            <span>{{ merchant.verifiedByEmailOtp }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.verifiedByMobileNoOtp')"></span>
          </dt>
          <dd>
            <span>{{ merchant.verifiedByMobileNoOtp }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.email')"></span>
          </dt>
          <dd>
            <span>{{ merchant.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.nidStatus')"></span>
          </dt>
          <dd>
            <span>{{ merchant.nidStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.isForeign')"></span>
          </dt>
          <dd>
            <span>{{ merchant.isForeign }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.isMobileConnectedWithNid')"></span>
          </dt>
          <dd>
            <span>{{ merchant.isMobileConnectedWithNid }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.walletOwnerStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.WalletOwnerStatus.' + merchant.walletOwnerStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.imageFile')"></span>
          </dt>
          <dd>
            <div v-if="merchant.imageFile">
              <a v-on:click="openFile(merchant.imageFileContentType, merchant.imageFile)">
                <img
                  v-bind:src="'data:' + merchant.imageFileContentType + ';base64,' + merchant.imageFile"
                  style="max-width: 100%"
                  alt="merchant image"
                />
              </a>
              {{ merchant.imageFileContentType }}, {{ byteSize(merchant.imageFile) }}
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.imageUrlFile')"></span>
          </dt>
          <dd>
            <span>{{ merchant.imageUrlFile }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.postCode')"></span>
          </dt>
          <dd>
            <span>{{ merchant.postCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.address')"></span>
          </dt>
          <dd>
            <span>{{ merchant.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.lat')"></span>
          </dt>
          <dd>
            <span>{{ merchant.lat }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.lng')"></span>
          </dt>
          <dd>
            <span>{{ merchant.lng }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.notes')"></span>
          </dt>
          <dd>
            <span>{{ merchant.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ merchant.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="merchant.createdDate">{{ formatDateLong(merchant.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ merchant.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="merchant.lastModifiedDate">{{ formatDateLong(merchant.lastModifiedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.user')"></span>
          </dt>
          <dd>
            {{ merchant.user ? merchant.user.id : '' }}
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.category')"></span>
          </dt>
          <dd>
            <div v-if="merchant.category">
              <router-link :to="{ name: 'CategoryView', params: { categoryId: merchant.category.id } }">{{
                merchant.category.nameAr
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.city')"></span>
          </dt>
          <dd>
            <div v-if="merchant.city">
              <router-link :to="{ name: 'CityView', params: { cityId: merchant.city.id } }">{{ merchant.city.nameAr }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.merchant.walletProfile')"></span>
          </dt>
          <dd>
            <div v-if="merchant.walletProfile">
              <router-link :to="{ name: 'WalletProfileView', params: { walletProfileId: merchant.walletProfile.id } }">{{
                merchant.walletProfile.nameAr
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="merchant.id" :to="{ name: 'MerchantEdit', params: { merchantId: merchant.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./merchant-details.component.ts"></script>
