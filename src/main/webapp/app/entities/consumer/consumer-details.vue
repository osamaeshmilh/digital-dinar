<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="consumer">
        <h2 class="jh-entity-heading" data-cy="consumerDetailsHeading">
          <span v-text="t$('digitalDinarApp.consumer.detail.title')"></span> {{ consumer.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.arabicFirstName')"></span>
          </dt>
          <dd>
            <span>{{ consumer.arabicFirstName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.arabicMiddleName')"></span>
          </dt>
          <dd>
            <span>{{ consumer.arabicMiddleName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.arabicLastName')"></span>
          </dt>
          <dd>
            <span>{{ consumer.arabicLastName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.englishFirstName')"></span>
          </dt>
          <dd>
            <span>{{ consumer.englishFirstName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.englishMiddleName')"></span>
          </dt>
          <dd>
            <span>{{ consumer.englishMiddleName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.englishLastName')"></span>
          </dt>
          <dd>
            <span>{{ consumer.englishLastName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.birthDate')"></span>
          </dt>
          <dd>
            <span>{{ consumer.birthDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.birthPlace')"></span>
          </dt>
          <dd>
            <span>{{ consumer.birthPlace }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.nationalNo')"></span>
          </dt>
          <dd>
            <span>{{ consumer.nationalNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.registerNo')"></span>
          </dt>
          <dd>
            <span>{{ consumer.registerNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.gender')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.Gender.' + consumer.gender)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.idType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.IdType.' + consumer.idType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.idNo')"></span>
          </dt>
          <dd>
            <span>{{ consumer.idNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.mobileNo')"></span>
          </dt>
          <dd>
            <span>{{ consumer.mobileNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.verifiedByEmailOtp')"></span>
          </dt>
          <dd>
            <span>{{ consumer.verifiedByEmailOtp }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.verifiedByMobileNoOtp')"></span>
          </dt>
          <dd>
            <span>{{ consumer.verifiedByMobileNoOtp }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.email')"></span>
          </dt>
          <dd>
            <span>{{ consumer.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.nidStatus')"></span>
          </dt>
          <dd>
            <span>{{ consumer.nidStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.isForeign')"></span>
          </dt>
          <dd>
            <span>{{ consumer.isForeign }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.isMobileConnectedWithNid')"></span>
          </dt>
          <dd>
            <span>{{ consumer.isMobileConnectedWithNid }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.walletOwnerStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.WalletOwnerStatus.' + consumer.walletOwnerStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.imageFile')"></span>
          </dt>
          <dd>
            <div v-if="consumer.imageFile">
              <a v-on:click="openFile(consumer.imageFileContentType, consumer.imageFile)">
                <img
                  v-bind:src="'data:' + consumer.imageFileContentType + ';base64,' + consumer.imageFile"
                  style="max-width: 100%"
                  alt="consumer image"
                />
              </a>
              {{ consumer.imageFileContentType }}, {{ byteSize(consumer.imageFile) }}
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.imageUrlFile')"></span>
          </dt>
          <dd>
            <span>{{ consumer.imageUrlFile }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.postCode')"></span>
          </dt>
          <dd>
            <span>{{ consumer.postCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.address')"></span>
          </dt>
          <dd>
            <span>{{ consumer.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.lat')"></span>
          </dt>
          <dd>
            <span>{{ consumer.lat }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.lng')"></span>
          </dt>
          <dd>
            <span>{{ consumer.lng }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.notes')"></span>
          </dt>
          <dd>
            <span>{{ consumer.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.user')"></span>
          </dt>
          <dd>
            {{ consumer.user ? consumer.user.id : '' }}
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.consumer.walletProfile')"></span>
          </dt>
          <dd>
            <div v-if="consumer.walletProfile">
              <router-link :to="{ name: 'WalletProfileView', params: { walletProfileId: consumer.walletProfile.id } }">{{
                consumer.walletProfile.nameAr
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="consumer.id" :to="{ name: 'ConsumerEdit', params: { consumerId: consumer.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./consumer-details.component.ts"></script>
