import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ViewLogService from './view-log.service';
import { useDateFormat } from '@/shared/composables';
import { type IViewLog } from '@/shared/model/view-log.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ViewLogDetails',
  setup() {
    const dateFormat = useDateFormat();
    const viewLogService = inject('viewLogService', () => new ViewLogService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const viewLog: Ref<IViewLog> = ref({});

    const retrieveViewLog = async viewLogId => {
      try {
        const res = await viewLogService().find(viewLogId);
        viewLog.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.viewLogId) {
      retrieveViewLog(route.params.viewLogId);
    }

    return {
      ...dateFormat,
      alertService,
      viewLog,

      previousState,
      t$: useI18n().t,
    };
  },
});
