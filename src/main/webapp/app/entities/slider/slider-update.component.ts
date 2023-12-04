import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SliderService from './slider.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ISlider, Slider } from '@/shared/model/slider.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SliderUpdate',
  setup() {
    const sliderService = inject('sliderService', () => new SliderService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const slider: Ref<ISlider> = ref(new Slider());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSlider = async sliderId => {
      try {
        const res = await sliderService().find(sliderId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        slider.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sliderId) {
      retrieveSlider(route.params.sliderId);
    }

    const dataUtils = useDataUtils();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      details: {},
      menuOrder: {},
      imageFileUrl: {},
      imageFile: {},
      url: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
    };
    const v$ = useVuelidate(validationRules, slider as any);
    v$.value.$validate();

    return {
      sliderService,
      alertService,
      slider,
      previousState,
      isSaving,
      currentLanguage,
      ...dataUtils,
      v$,
      ...useDateFormat({ entityRef: slider }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.slider.id) {
        this.sliderService()
          .update(this.slider)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.slider.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.sliderService()
          .create(this.slider)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.slider.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    clearInputImage(field, fieldContentType, idInput): void {
      if (this.slider && field && fieldContentType) {
        if (Object.prototype.hasOwnProperty.call(this.slider, field)) {
          this.slider[field] = null;
        }
        if (Object.prototype.hasOwnProperty.call(this.slider, fieldContentType)) {
          this.slider[fieldContentType] = null;
        }
        if (idInput) {
          (<any>this).$refs[idInput] = null;
        }
      }
    },
  },
});
