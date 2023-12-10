<template>
  <BpModelIndexPage entity="transactions" filter>
    <template #[`item.data.paymentType`]="{ item }">
      <div class="my-3">
        <div class="d-flex mb-2" style="gap: 4px">
          <v-chip
            size="small"
            variant="flat"
            color="white"
            border
            class="text-primary font-weight-bold pt-1"
          >
            {{ item.data.paymentType }}
          </v-chip>
          <v-chip
            size="small"
            class="pt-1 font-weight-bold"
            :color="
              item.data.transactionStatus === 'PAYED'
                ? 'success'
                : item.data.transactionStatus === 'ERROR'
                  ? 'error'
                  : item.data.transactionStatus === 'PENDING_PAYMENT'
                    ? 'info'
                    : item.data.transactionStatus === 'CANCELLED'
                      ? 'warning'
                      : ''
            "
          >
            {{ item.data.transactionStatus }}
          </v-chip>
        </div>
        <p v-if="item.data.notes" class="fs-1_2 mb-0">
          {{ item.data.notes }}
        </p>
        <span class="font-weight-bold">
          {{
            item.data.amount
              ? formatMoney(item.data.amount, { currency: true })
              : ''
          }}
        </span>
      </div>
    </template>

    <template #[`item.data.total`]="{ item }">
      ({{ item.data.amount }} + {{ item.data.fees }})
      <span class="d-block fs-1_2 font-weight-bold">{{
        formatMoney(item.data.total, { currency: true })
      }}</span>
    </template>
  </BpModelIndexPage>
</template>

<script setup>
definePageMeta({
  middleware: 'auth',
  breadcrumb: [{ title: 'navigation.transactions' }],
})
</script>

<style></style>
