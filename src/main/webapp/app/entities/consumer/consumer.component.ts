import { defineComponent, inject, onMounted, ref, type Ref, watch } from 'vue';
import { useI18n } from 'vue-i18n';

import ConsumerService from './consumer.service';
import { type IConsumer } from '@/shared/model/consumer.model';
import useDataUtils from '@/shared/data/data-utils.service';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Consumer',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const dataUtils = useDataUtils();
    const consumerService = inject('consumerService', () => new ConsumerService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const itemsPerPage = ref(20);
    const queryCount: Ref<number> = ref(null);
    const page: Ref<number> = ref(1);
    const propOrder = ref('id');
    const reverse = ref(false);
    const totalItems = ref(0);

    const consumers: Ref<IConsumer[]> = ref([]);

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

    const retrieveConsumers = async () => {
      isFetching.value = true;
      try {
        const paginationQuery = {
          page: page.value - 1,
          size: itemsPerPage.value,
          sort: sort(),
        };
        const res = await consumerService().retrieve(paginationQuery);
        totalItems.value = Number(res.headers['x-total-count']);
        queryCount.value = totalItems.value;
        consumers.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveConsumers();
    };

    onMounted(async () => {
      await retrieveConsumers();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IConsumer) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeConsumer = async () => {
      try {
        await consumerService().delete(removeId.value);
        const message = t$('digitalDinarApp.consumer.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveConsumers();
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
        await retrieveConsumers();
      } else {
        // reset the pagination
        clear();
      }
    });

    // Whenever page changes, switch to the new page.
    watch(page, async () => {
      await retrieveConsumers();
    });

    return {
      consumers,
      handleSyncList,
      isFetching,
      retrieveConsumers,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeConsumer,
      itemsPerPage,
      queryCount,
      page,
      propOrder,
      reverse,
      totalItems,
      changeOrder,
      t$,
      ...dataUtils,
    };
  },
});
