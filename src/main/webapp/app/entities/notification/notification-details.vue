<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="notification">
        <h2 class="jh-entity-heading" data-cy="notificationDetailsHeading">
          <span v-text="t$('digitalDinarApp.notification.detail.title')"></span> {{ notification.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.notification.title')"></span>
          </dt>
          <dd>
            <span>{{ notification.title }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.notification.description')"></span>
          </dt>
          <dd>
            <span>{{ notification.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.notification.userId')"></span>
          </dt>
          <dd>
            <span>{{ notification.userId }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.notification.createdBy')"></span>
          </dt>
          <dd>
            <span>{{ notification.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.notification.createdDate')"></span>
          </dt>
          <dd>
            <span v-if="notification.createdDate">{{ formatDateLong(notification.createdDate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.notification.lastModifiedBy')"></span>
          </dt>
          <dd>
            <span>{{ notification.lastModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.notification.lastModifiedDate')"></span>
          </dt>
          <dd>
            <span v-if="notification.lastModifiedDate">{{ formatDateLong(notification.lastModifiedDate) }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="notification.id"
          :to="{ name: 'NotificationEdit', params: { notificationId: notification.id } }"
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

<script lang="ts" src="./notification-details.component.ts"></script>
