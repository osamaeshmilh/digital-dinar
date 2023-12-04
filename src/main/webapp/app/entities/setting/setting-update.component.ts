import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SettingService from './setting.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ISetting, Setting } from '@/shared/model/setting.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SettingUpdate',
  setup() {
    const settingService = inject('settingService', () => new SettingService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const setting: Ref<ISetting> = ref(new Setting());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSetting = async settingId => {
      try {
        const res = await settingService().find(settingId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        setting.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.settingId) {
      retrieveSetting(route.params.settingId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      key: {},
      value: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
    };
    const v$ = useVuelidate(validationRules, setting as any);
    v$.value.$validate();

    return {
      settingService,
      alertService,
      setting,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: setting }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.setting.id) {
        this.settingService()
          .update(this.setting)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.setting.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.settingService()
          .create(this.setting)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.setting.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
