<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="voucherType">
        <h2 class="jh-entity-heading" data-cy="voucherTypeDetailsHeading">
          <span v-text="t$('digitalDinarApp.voucherType.detail.title')"></span> {{ voucherType.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.voucherType.nameAr')"></span>
          </dt>
          <dd>
            <span>{{ voucherType.nameAr }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherType.nameEn')"></span>
          </dt>
          <dd>
            <span>{{ voucherType.nameEn }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherType.slug')"></span>
          </dt>
          <dd>
            <span>{{ voucherType.slug }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherType.amount')"></span>
          </dt>
          <dd>
            <span>{{ voucherType.amount }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherType.menuOrder')"></span>
          </dt>
          <dd>
            <span>{{ voucherType.menuOrder }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.voucherType.voucherCompany')"></span>
          </dt>
          <dd>
            <div v-if="voucherType.voucherCompany">
              <router-link :to="{ name: 'VoucherCompanyView', params: { voucherCompanyId: voucherType.voucherCompany.id } }">{{
                voucherType.voucherCompany.nameAr
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="voucherType.id"
          :to="{ name: 'VoucherTypeEdit', params: { voucherTypeId: voucherType.id } }"
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

<script lang="ts" src="./voucher-type-details.component.ts"></script>
