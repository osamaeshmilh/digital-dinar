<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="voucherCompany">
        <h2 class="jh-entity-heading" data-cy="voucherCompanyDetailsHeading">
          <span v-text="t$('digitalDinarApp.voucherCompany.detail.title')"></span> {{ voucherCompany.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.voucherCompany.nameAr')"></span>
          </dt>
          <dd>
            <span>{{ voucherCompany.nameAr }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherCompany.nameEn')"></span>
          </dt>
          <dd>
            <span>{{ voucherCompany.nameEn }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherCompany.mobileNo')"></span>
          </dt>
          <dd>
            <span>{{ voucherCompany.mobileNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherCompany.email')"></span>
          </dt>
          <dd>
            <span>{{ voucherCompany.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherCompany.imageFile')"></span>
          </dt>
          <dd>
            <div v-if="voucherCompany.imageFile">
              <a v-on:click="openFile(voucherCompany.imageFileContentType, voucherCompany.imageFile)" v-text="t$('entity.action.open')"></a>
              {{ voucherCompany.imageFileContentType }}, {{ byteSize(voucherCompany.imageFile) }}
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherCompany.imageUrlFile')"></span>
          </dt>
          <dd>
            <span>{{ voucherCompany.imageUrlFile }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherCompany.postCode')"></span>
          </dt>
          <dd>
            <span>{{ voucherCompany.postCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherCompany.address')"></span>
          </dt>
          <dd>
            <span>{{ voucherCompany.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherCompany.notes')"></span>
          </dt>
          <dd>
            <span>{{ voucherCompany.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherCompany.user')"></span>
          </dt>
          <dd>
            {{ voucherCompany.user ? voucherCompany.user.id : '' }}
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="voucherCompany.id"
          :to="{ name: 'VoucherCompanyEdit', params: { voucherCompanyId: voucherCompany.id } }"
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

<script lang="ts" src="./voucher-company-details.component.ts"></script>
