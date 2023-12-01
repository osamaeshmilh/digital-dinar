<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="voucher">
        <h2 class="jh-entity-heading" data-cy="voucherDetailsHeading">
          <span v-text="t$('digitalDinarApp.voucher.detail.title')"></span> {{ voucher.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.code')"></span>
          </dt>
          <dd>
            <span>{{ voucher.code }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.amount')"></span>
          </dt>
          <dd>
            <span>{{ voucher.amount }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.serialNumber')"></span>
          </dt>
          <dd>
            <span>{{ voucher.serialNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.isSold')"></span>
          </dt>
          <dd>
            <span>{{ voucher.isSold }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.sellTransactionId')"></span>
          </dt>
          <dd>
            <span>{{ voucher.sellTransactionId }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ voucher.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="voucher.createdDate">{{ formatDateLong(voucher.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ voucher.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="voucher.lastModifiedDate">{{ formatDateLong(voucher.lastModifiedDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.voucherType')"></span>
          </dt>
          <dd>
            <div v-if="voucher.voucherType.id">
              <router-link :to="{ name: 'VoucherTypeView', params: { voucherTypeId: voucher.voucherType.id } }">{{
                voucher.voucherType.nameAr
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucher.voucherCompany')"></span>
          </dt>
          <dd>
            <div v-if="voucher.voucherCompany.id">
              <router-link :to="{ name: 'VoucherCompanyView', params: { voucherCompanyId: voucher.voucherCompany.id } }">{{
                voucher.voucherCompany.nameAr
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="voucher.id" :to="{ name: 'VoucherEdit', params: { voucherId: voucher.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./voucher-details.component.ts"></script>
