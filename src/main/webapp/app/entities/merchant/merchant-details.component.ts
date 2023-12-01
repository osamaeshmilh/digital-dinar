import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import MerchantService from './merchant.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { type IMerchant } from '@/shared/model/merchant.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MerchantDetails',
  setup() {
    const merchantService = inject('merchantService', () => new MerchantService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const dataUtils = useDataUtils();

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const merchant: Ref<IMerchant> = ref({});

    const retrieveMerchant = async merchantId => {
      try {
        const res = await merchantService().find(merchantId);
        merchant.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.merchantId) {
      retrieveMerchant(route.params.merchantId);
    }

    return {
      alertService,
      merchant,

      ...dataUtils,

      previousState,
      t$: useI18n().t,
    };
  },
});
