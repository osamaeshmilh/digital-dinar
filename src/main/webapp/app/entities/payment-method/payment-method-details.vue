<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="paymentMethod">
        <h2 class="jh-entity-heading" data-cy="paymentMethodDetailsHeading">
          <span v-text="t$('digitalDinarApp.paymentMethod.detail.title')"></span> {{ paymentMethod.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.name')"></span>
          </dt>
          <dd>
            <span>{{ paymentMethod.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.nameAr')"></span>
          </dt>
          <dd>
            <span>{{ paymentMethod.nameAr }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.nameEn')"></span>
          </dt>
          <dd>
            <span>{{ paymentMethod.nameEn }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.menuOrder')"></span>
          </dt>
          <dd>
            <span>{{ paymentMethod.menuOrder }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.imageFileUrl')"></span>
          </dt>
          <dd>
            <span>{{ paymentMethod.imageFileUrl }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.imageFile')"></span>
          </dt>
          <dd>
            <div v-if="paymentMethod.imageFile">
              <a v-on:click="openFile(paymentMethod.imageFileContentType, paymentMethod.imageFile)">
                <img
                  v-bind:src="'data:' + paymentMethod.imageFileContentType + ';base64,' + paymentMethod.imageFile"
                  style="max-width: 100%"
                  alt="paymentMethod image"
                />
              </a>
              {{ paymentMethod.imageFileContentType }}, {{ byteSize(paymentMethod.imageFile) }}
            </div>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.details')"></span>
          </dt>
          <dd>
            <span>{{ paymentMethod.details }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.feePercentage')"></span>
          </dt>
          <dd>
            <span>{{ paymentMethod.feePercentage }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.paymentType')"></span>
          </dt>
          <dd>
            <span v-text="t$('digitalDinarApp.PaymentType.' + paymentMethod.paymentType)"></span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.notes')"></span>
          </dt>
          <dd>
            <span>{{ paymentMethod.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ paymentMethod.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="paymentMethod.createdDate">{{ formatDateLong(paymentMethod.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ paymentMethod.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.paymentMethod.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="paymentMethod.lastModifiedDate">{{ formatDateLong(paymentMethod.lastModifiedDate) }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="paymentMethod.id"
          :to="{ name: 'PaymentMethodEdit', params: { paymentMethodId: paymentMethod.id } }"
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

<script lang="ts" src="./payment-method-details.component.ts"></script>
