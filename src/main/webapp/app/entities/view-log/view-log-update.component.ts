import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ViewLogService from './view-log.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IViewLog, ViewLog } from '@/shared/model/view-log.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ViewLogUpdate',
  setup() {
    const viewLogService = inject('viewLogService', () => new ViewLogService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const viewLog: Ref<IViewLog> = ref(new ViewLog());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveViewLog = async viewLogId => {
      try {
        const res = await viewLogService().find(viewLogId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        viewLog.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.viewLogId) {
      retrieveViewLog(route.params.viewLogId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      entityName: {},
      entityId: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
    };
    const v$ = useVuelidate(validationRules, viewLog as any);
    v$.value.$validate();

    return {
      viewLogService,
      alertService,
      viewLog,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: viewLog }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.viewLog.id) {
        this.viewLogService()
          .update(this.viewLog)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.viewLog.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.viewLogService()
          .create(this.viewLog)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.viewLog.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
