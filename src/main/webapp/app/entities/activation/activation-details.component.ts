import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ActivationService from './activation.service';
import { useDateFormat } from '@/shared/composables';
import { type IActivation } from '@/shared/model/activation.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ActivationDetails',
  setup() {
    const dateFormat = useDateFormat();
    const activationService = inject('activationService', () => new ActivationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const activation: Ref<IActivation> = ref({});

    const retrieveActivation = async activationId => {
      try {
        const res = await activationService().find(activationId);
        activation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.activationId) {
      retrieveActivation(route.params.activationId);
    }

    return {
      ...dateFormat,
      alertService,
      activation,

      previousState,
      t$: useI18n().t,
    };
  },
});
