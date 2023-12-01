import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import WalletTransactionService from './wallet-transaction.service';
import { useDateFormat } from '@/shared/composables';
import { type IWalletTransaction } from '@/shared/model/wallet-transaction.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WalletTransactionDetails',
  setup() {
    const dateFormat = useDateFormat();
    const walletTransactionService = inject('walletTransactionService', () => new WalletTransactionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const walletTransaction: Ref<IWalletTransaction> = ref({});

    const retrieveWalletTransaction = async walletTransactionId => {
      try {
        const res = await walletTransactionService().find(walletTransactionId);
        walletTransaction.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.walletTransactionId) {
      retrieveWalletTransaction(route.params.walletTransactionId);
    }

    return {
      ...dateFormat,
      alertService,
      walletTransaction,

      previousState,
      t$: useI18n().t,
    };
  },
});
