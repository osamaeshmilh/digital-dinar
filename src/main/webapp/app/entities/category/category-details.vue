<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="category">
        <h2 class="jh-entity-heading" data-cy="categoryDetailsHeading">
          <span v-text="t$('digitalDinarApp.category.detail.title')"></span> {{ category.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('digitalDinarApp.category.nameAr')"></span>
          </dt>
          <dd>
            <span>{{ category.nameAr }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.category.nameEn')"></span>
          </dt>
          <dd>
            <span>{{ category.nameEn }}</span>
          </dd>
          <dt>
            <span v-text="t$('digitalDinarApp.category.code')"></span>
          </dt>
          <dd>
            <span>{{ category.code }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="category.id" :to="{ name: 'CategoryEdit', params: { categoryId: category.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./category-details.component.ts"></script>
