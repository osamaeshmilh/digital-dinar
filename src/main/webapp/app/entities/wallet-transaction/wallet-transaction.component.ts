import { defineComponent, inject, onMounted, ref, type Ref, watch } from 'vue';
import { useI18n } from 'vue-i18n';

import WalletTransactionService from './wallet-transaction.service';
import { type IWalletTransaction } from '@/shared/model/wallet-transaction.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WalletTransaction',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const walletTransactionService = inject('walletTransactionService', () => new WalletTransactionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const itemsPerPage = ref(20);
    const queryCount: Ref<number> = ref(null);
    const page: Ref<number> = ref(1);
    const propOrder = ref('id');
    const reverse = ref(false);
    const totalItems = ref(0);

    const walletTransactions: Ref<IWalletTransaction[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {
      page.value = 1;
    };

    const sort = (): Array<any> => {
      const result = [propOrder.value + ',' + (reverse.value ? 'desc' : 'asc')];
      if (propOrder.value !== 'id') {
        result.push('id');
      }
      return result;
    };

    const retrieveWalletTransactions = async () => {
      isFetching.value = true;
      try {
        const paginationQuery = {
          page: page.value - 1,
          size: itemsPerPage.value,
          sort: sort(),
        };
        const res = await walletTransactionService().retrieve(paginationQuery);
        totalItems.value = Number(res.headers['x-total-count']);
        queryCount.value = totalItems.value;
        walletTransactions.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveWalletTransactions();
    };

    onMounted(async () => {
      await retrieveWalletTransactions();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IWalletTransaction) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeWalletTransaction = async () => {
      try {
        await walletTransactionService().delete(removeId.value);
        const message = t$('digitalDinarApp.walletTransaction.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveWalletTransactions();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    const changeOrder = (newOrder: string) => {
      if (propOrder.value === newOrder) {
        reverse.value = !reverse.value;
      } else {
        reverse.value = false;
      }
      propOrder.value = newOrder;
    };

    // Whenever order changes, reset the pagination
    watch([propOrder, reverse], async () => {
      if (page.value === 1) {
        // first page, retrieve new data
        await retrieveWalletTransactions();
      } else {
        // reset the pagination
        clear();
      }
    });

    // Whenever page changes, switch to the new page.
    watch(page, async () => {
      await retrieveWalletTransactions();
    });

    return {
      walletTransactions,
      handleSyncList,
      isFetching,
      retrieveWalletTransactions,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeWalletTransaction,
      itemsPerPage,
      queryCount,
      page,
      propOrder,
      reverse,
      totalItems,
      changeOrder,
      t$,
    };
  },
});
