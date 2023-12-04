import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TransactionService from './transaction.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import WalletUserService from '@/entities/wallet-user/wallet-user.service';
import { type IWalletUser } from '@/shared/model/wallet-user.model';
import { type ITransaction, Transaction } from '@/shared/model/transaction.model';
import { PaymentType } from '@/shared/model/enumerations/payment-type.model';
import { TransactionStatus } from '@/shared/model/enumerations/transaction-status.model';
import { TransactionType } from '@/shared/model/enumerations/transaction-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TransactionUpdate',
  setup() {
    const transactionService = inject('transactionService', () => new TransactionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const transaction: Ref<ITransaction> = ref(new Transaction());

    const walletUserService = inject('walletUserService', () => new WalletUserService());

    const walletUsers: Ref<IWalletUser[]> = ref([]);
    const paymentTypeValues: Ref<string[]> = ref(Object.keys(PaymentType));
    const transactionStatusValues: Ref<string[]> = ref(Object.keys(TransactionStatus));
    const transactionTypeValues: Ref<string[]> = ref(Object.keys(TransactionType));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveTransaction = async transactionId => {
      try {
        const res = await transactionService().find(transactionId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        transaction.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.transactionId) {
      retrieveTransaction(route.params.transactionId);
    }

    const initRelationships = () => {
      walletUserService()
        .retrieve()
        .then(res => {
          walletUsers.value = res.data;
        });
    };

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
      senderIdNo: {},
      receiverName: {},
      receiverMobileNo: {},
      receiverIdNo: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
      sender: {},
      receiver: {},
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
      isSaving,
      currentLanguage,
      walletUsers,
      v$,
      ...useDateFormat({ entityRef: transaction }),
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
