import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import VoucherService from './voucher.service';
import { type IVoucher } from '@/shared/model/voucher.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'VoucherDetails',
  setup() {
    const voucherService = inject('voucherService', () => new VoucherService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const voucher: Ref<IVoucher> = ref({});

    const retrieveVoucher = async voucherId => {
      try {
        const res = await voucherService().find(voucherId);
        voucher.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.voucherId) {
      retrieveVoucher(route.params.voucherId);
    }

    return {
      alertService,
      voucher,

      previousState,
      t$: useI18n().t,
    };
  },
});
