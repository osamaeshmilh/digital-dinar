import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SliderService from './slider.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useDateFormat } from '@/shared/composables';
import { type ISlider } from '@/shared/model/slider.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SliderDetails',
  setup() {
    const dateFormat = useDateFormat();
    const sliderService = inject('sliderService', () => new SliderService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const dataUtils = useDataUtils();

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const slider: Ref<ISlider> = ref({});

    const retrieveSlider = async sliderId => {
      try {
        const res = await sliderService().find(sliderId);
        slider.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sliderId) {
      retrieveSlider(route.params.sliderId);
    }

    return {
      ...dateFormat,
      alertService,
      slider,

      ...dataUtils,

      previousState,
      t$: useI18n().t,
    };
  },
});
