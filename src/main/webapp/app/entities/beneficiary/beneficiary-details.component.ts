import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import BeneficiaryService from './beneficiary.service';
import { useDateFormat } from '@/shared/composables';
import { type IBeneficiary } from '@/shared/model/beneficiary.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'BeneficiaryDetails',
  setup() {
    const dateFormat = useDateFormat();
    const beneficiaryService = inject('beneficiaryService', () => new BeneficiaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const beneficiary: Ref<IBeneficiary> = ref({});

    const retrieveBeneficiary = async beneficiaryId => {
      try {
        const res = await beneficiaryService().find(beneficiaryId);
        beneficiary.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.beneficiaryId) {
      retrieveBeneficiary(route.params.beneficiaryId);
    }

    return {
      ...dateFormat,
      alertService,
      beneficiary,

      previousState,
      t$: useI18n().t,
    };
  },
});
