import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SettingService from './setting.service';
import { useDateFormat } from '@/shared/composables';
import { type ISetting } from '@/shared/model/setting.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SettingDetails',
  setup() {
    const dateFormat = useDateFormat();
    const settingService = inject('settingService', () => new SettingService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const setting: Ref<ISetting> = ref({});

    const retrieveSetting = async settingId => {
      try {
        const res = await settingService().find(settingId);
        setting.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.settingId) {
      retrieveSetting(route.params.settingId);
    }

    return {
      ...dateFormat,
      alertService,
      setting,

      previousState,
      t$: useI18n().t,
    };
  },
});
