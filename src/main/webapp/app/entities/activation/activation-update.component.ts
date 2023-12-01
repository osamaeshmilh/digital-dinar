import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ActivationService from './activation.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IActivation, Activation } from '@/shared/model/activation.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ActivationUpdate',
  setup() {
    const activationService = inject('activationService', () => new ActivationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const activation: Ref<IActivation> = ref(new Activation());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveActivation = async activationId => {
      try {
        const res = await activationService().find(activationId);
        res.sentOn = new Date(res.sentOn);
        res.validUntil = new Date(res.validUntil);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        activation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.activationId) {
      retrieveActivation(route.params.activationId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      mobileNo: {},
      email: {},
      code: {},
      sentOn: {},
      validUntil: {},
      isUsed: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
    };
    const v$ = useVuelidate(validationRules, activation as any);
    v$.value.$validate();

    return {
      activationService,
      alertService,
      activation,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: activation }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.activation.id) {
        this.activationService()
          .update(this.activation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.activation.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.activationService()
          .create(this.activation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.activation.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
