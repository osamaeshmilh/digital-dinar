import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import VoucherCompanyService from './voucher-company.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { type IVoucherCompany } from '@/shared/model/voucher-company.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'VoucherCompanyDetails',
  setup() {
    const voucherCompanyService = inject('voucherCompanyService', () => new VoucherCompanyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const dataUtils = useDataUtils();

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const voucherCompany: Ref<IVoucherCompany> = ref({});

    const retrieveVoucherCompany = async voucherCompanyId => {
      try {
        const res = await voucherCompanyService().find(voucherCompanyId);
        voucherCompany.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.voucherCompanyId) {
      retrieveVoucherCompany(route.params.voucherCompanyId);
    }

    return {
      alertService,
      voucherCompany,

      ...dataUtils,

      previousState,
      t$: useI18n().t,
    };
  },
});
