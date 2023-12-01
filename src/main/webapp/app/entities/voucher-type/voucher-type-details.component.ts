import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import VoucherTypeService from './voucher-type.service';
import { type IVoucherType } from '@/shared/model/voucher-type.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'VoucherTypeDetails',
  setup() {
    const voucherTypeService = inject('voucherTypeService', () => new VoucherTypeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const voucherType: Ref<IVoucherType> = ref({});

    const retrieveVoucherType = async voucherTypeId => {
      try {
        const res = await voucherTypeService().find(voucherTypeId);
        voucherType.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.voucherTypeId) {
      retrieveVoucherType(route.params.voucherTypeId);
    }

    return {
      alertService,
      voucherType,

      previousState,
      t$: useI18n().t,
    };
  },
});
