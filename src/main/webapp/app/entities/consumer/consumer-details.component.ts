import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ConsumerService from './consumer.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { type IConsumer } from '@/shared/model/consumer.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ConsumerDetails',
  setup() {
    const consumerService = inject('consumerService', () => new ConsumerService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const dataUtils = useDataUtils();

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const consumer: Ref<IConsumer> = ref({});

    const retrieveConsumer = async consumerId => {
      try {
        const res = await consumerService().find(consumerId);
        consumer.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.consumerId) {
      retrieveConsumer(route.params.consumerId);
    }

    return {
      alertService,
      consumer,

      ...dataUtils,

      previousState,
      t$: useI18n().t,
    };
  },
});
