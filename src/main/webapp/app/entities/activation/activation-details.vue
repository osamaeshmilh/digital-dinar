<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="activation">
        <h2 class="jh-entity-heading" data-cy="activationDetailsHeading">
          <span v-text="t$('digitalDinarApp.activation.detail.title')"></span> {{ activation.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.activation.mobileNo')"></span>
          </dt>
          <dd>
            <span>{{ activation.mobileNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.activation.email')"></span>
          </dt>
          <dd>
            <span>{{ activation.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.activation.code')"></span>
          </dt>
          <dd>
            <span>{{ activation.code }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.activation.sentOn')"></span>
          </dt>
          <dd>
            <span v-if="activation.sentOn">{{ formatDateLong(activation.sentOn) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.activation.validUntil')"></span>
          </dt>
          <dd>
            <span v-if="activation.validUntil">{{ formatDateLong(activation.validUntil) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.activation.isUsed')"></span>
          </dt>
          <dd>
            <span>{{ activation.isUsed }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="activation.id"
          :to="{ name: 'ActivationEdit', params: { activationId: activation.id } }"
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

<script lang="ts" src="./activation-details.component.ts"></script>
