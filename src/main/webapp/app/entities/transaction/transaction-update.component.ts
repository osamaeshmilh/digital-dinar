import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TransactionService from './transaction.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ITransaction, Transaction } from '@/shared/model/transaction.model';
import { PaymentType } from '@/shared/model/enumerations/payment-type.model';
import { TransactionStatus } from '@/shared/model/enumerations/transaction-status.model';
import { TransactionType } from '@/shared/model/enumerations/transaction-type.model';
import { WalletOwnerType } from '@/shared/model/enumerations/wallet-owner-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TransactionUpdate',
  setup() {
    const transactionService = inject('transactionService', () => new TransactionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const transaction: Ref<ITransaction> = ref(new Transaction());
    const paymentTypeValues: Ref<string[]> = ref(Object.keys(PaymentType));
    const transactionStatusValues: Ref<string[]> = ref(Object.keys(TransactionStatus));
    const transactionTypeValues: Ref<string[]> = ref(Object.keys(TransactionType));
    const walletOwnerTypeValues: Ref<string[]> = ref(Object.keys(WalletOwnerType));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveTransaction = async transactionId => {
      try {
        const res = await transactionService().find(transactionId);
        transaction.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.transactionId) {
      retrieveTransaction(route.params.transactionId);
    }

    const initRelationships = () => {};

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      transactionReference: {},
      transactionSecret: {},
      transactionOtp: {},
      paymentType: {},
      transactionStatus: {},
      transactionType: {},
      vendorReference: {},
      vendorMessage: {},
      amount: {},
      fees: {},
      total: {},
      notes: {},
      senderMobileNo: {},
      senderName: {},
      senderType: {},
      senderIdNo: {},
      senderId: {},
      receiverName: {},
      receiverMobileNo: {},
      receiverType: {},
      receiverId: {},
      receiverIdNo: {},
      createdByUserId: {},
      walletTransactions: {},
    };
    const v$ = useVuelidate(validationRules, transaction as any);
    v$.value.$validate();

    return {
      transactionService,
      alertService,
      transaction,
      previousState,
      paymentTypeValues,
      transactionStatusValues,
      transactionTypeValues,
      walletOwnerTypeValues,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.transaction.id) {
        this.transactionService()
          .update(this.transaction)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.transaction.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.transactionService()
          .create(this.transaction)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.transaction.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
