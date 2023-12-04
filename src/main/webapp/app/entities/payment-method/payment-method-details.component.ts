import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PaymentMethodService from './payment-method.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useDateFormat } from '@/shared/composables';
import { type IPaymentMethod } from '@/shared/model/payment-method.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PaymentMethodDetails',
  setup() {
    const dateFormat = useDateFormat();
    const paymentMethodService = inject('paymentMethodService', () => new PaymentMethodService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const dataUtils = useDataUtils();

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const paymentMethod: Ref<IPaymentMethod> = ref({});

    const retrievePaymentMethod = async paymentMethodId => {
      try {
        const res = await paymentMethodService().find(paymentMethodId);
        paymentMethod.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.paymentMethodId) {
      retrievePaymentMethod(route.params.paymentMethodId);
    }

    return {
      ...dateFormat,
      alertService,
      paymentMethod,

      ...dataUtils,

      previousState,
      t$: useI18n().t,
    };
  },
});
