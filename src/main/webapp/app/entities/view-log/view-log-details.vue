<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="viewLog">
        <h2 class="jh-entity-heading" data-cy="viewLogDetailsHeading">
          <span v-text="t$('digitalDinarApp.viewLog.detail.title')"></span> {{ viewLog.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.viewLog.entityName')"></span>
          </dt>
          <dd>
            <span>{{ viewLog.entityName }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.viewLog.entityId')"></span>
          </dt>
          <dd>
            <span>{{ viewLog.entityId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="viewLog.id" :to="{ name: 'ViewLogEdit', params: { viewLogId: viewLog.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./view-log-details.component.ts"></script>
