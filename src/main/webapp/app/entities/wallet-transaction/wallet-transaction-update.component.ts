import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import WalletTransactionService from './wallet-transaction.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import TransactionService from '@/entities/transaction/transaction.service';
import { type ITransaction } from '@/shared/model/transaction.model';
import { type IWalletTransaction, WalletTransaction } from '@/shared/model/wallet-transaction.model';
import { WalletAction } from '@/shared/model/enumerations/wallet-action.model';
import { PaymentType } from '@/shared/model/enumerations/payment-type.model';
import { WalletOwnerType } from '@/shared/model/enumerations/wallet-owner-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WalletTransactionUpdate',
  setup() {
    const walletTransactionService = inject('walletTransactionService', () => new WalletTransactionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const walletTransaction: Ref<IWalletTransaction> = ref(new WalletTransaction());

    const transactionService = inject('transactionService', () => new TransactionService());

    const transactions: Ref<ITransaction[]> = ref([]);
    const walletActionValues: Ref<string[]> = ref(Object.keys(WalletAction));
    const paymentTypeValues: Ref<string[]> = ref(Object.keys(PaymentType));
    const walletOwnerTypeValues: Ref<string[]> = ref(Object.keys(WalletOwnerType));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveWalletTransaction = async walletTransactionId => {
      try {
        const res = await walletTransactionService().find(walletTransactionId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        walletTransaction.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.walletTransactionId) {
      retrieveWalletTransaction(route.params.walletTransactionId);
    }

    const initRelationships = () => {
      transactionService()
        .retrieve()
        .then(res => {
          transactions.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      transactionNo: {},
      amount: {},
      walletAction: {},
      userPreviousTransactionNo: {},
      totalBeforeAction: {},
      totalAfterAction: {},
      paymentType: {},
      paymentReference: {},
      notes: {},
      ownerId: {},
      walletOwnerType: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
      transaction: {},
    };
    const v$ = useVuelidate(validationRules, walletTransaction as any);
    v$.value.$validate();

    return {
      walletTransactionService,
      alertService,
      walletTransaction,
      previousState,
      walletActionValues,
      paymentTypeValues,
      walletOwnerTypeValues,
      isSaving,
      currentLanguage,
      transactions,
      v$,
      ...useDateFormat({ entityRef: walletTransaction }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.walletTransaction.id) {
        this.walletTransactionService()
          .update(this.walletTransaction)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.walletTransaction.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.walletTransactionService()
          .create(this.walletTransaction)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.walletTransaction.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
