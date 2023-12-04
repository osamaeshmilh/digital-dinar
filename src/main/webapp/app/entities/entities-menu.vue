<template>
  <div>
    <b-dropdown-item to="/activation">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.activation')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/attachment">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.attachment')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/wallet-user">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.walletUser')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/wallet-transaction">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.walletTransaction')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/beneficiary">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.beneficiary')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/wallet-profile">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.walletProfile')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/payment-method">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.paymentMethod')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/notification">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.notification')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/view-log">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.viewLog')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/setting">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.setting')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/slider">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.slider')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/country">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.country')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/city">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.city')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/category">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.category')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/transaction">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.transaction')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/voucher-company">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.voucherCompany')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/voucher-type">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.voucherType')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/voucher">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.voucher')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/bank">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.bank')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/bank-branch">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.bankBranch')"></span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
