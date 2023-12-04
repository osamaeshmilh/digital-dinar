import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PaymentMethodService from './payment-method.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IPaymentMethod, PaymentMethod } from '@/shared/model/payment-method.model';
import { PaymentType } from '@/shared/model/enumerations/payment-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PaymentMethodUpdate',
  setup() {
    const paymentMethodService = inject('paymentMethodService', () => new PaymentMethodService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const paymentMethod: Ref<IPaymentMethod> = ref(new PaymentMethod());
    const paymentTypeValues: Ref<string[]> = ref(Object.keys(PaymentType));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const dataUtils = useDataUtils();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      nameAr: {},
      nameEn: {},
      menuOrder: {},
      imageFileUrl: {},
      imageFile: {},
      details: {},
      feePercentage: {},
      paymentType: {},
      notes: {},
    };
    const v$ = useVuelidate(validationRules, paymentMethod as any);
    v$.value.$validate();

    return {
      paymentMethodService,
      alertService,
      paymentMethod,
      previousState,
      paymentTypeValues,
      isSaving,
      currentLanguage,
      ...dataUtils,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.paymentMethod.id) {
        this.paymentMethodService()
          .update(this.paymentMethod)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.paymentMethod.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.paymentMethodService()
          .create(this.paymentMethod)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.paymentMethod.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    clearInputImage(field, fieldContentType, idInput): void {
      if (this.paymentMethod && field && fieldContentType) {
        if (Object.prototype.hasOwnProperty.call(this.paymentMethod, field)) {
          this.paymentMethod[field] = null;
        }
        if (Object.prototype.hasOwnProperty.call(this.paymentMethod, fieldContentType)) {
          this.paymentMethod[fieldContentType] = null;
        }
        if (idInput) {
          (<any>this).$refs[idInput] = null;
        }
      }
    },
  },
});
