import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import NotificationService from './notification.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type INotification, Notification } from '@/shared/model/notification.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'NotificationUpdate',
  setup() {
    const notificationService = inject('notificationService', () => new NotificationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const notification: Ref<INotification> = ref(new Notification());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveNotification = async notificationId => {
      try {
        const res = await notificationService().find(notificationId);
        notification.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.notificationId) {
      retrieveNotification(route.params.notificationId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      title: {},
      description: {},
      userId: {},
    };
    const v$ = useVuelidate(validationRules, notification as any);
    v$.value.$validate();

    return {
      notificationService,
      alertService,
      notification,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.notification.id) {
        this.notificationService()
          .update(this.notification)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.notification.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.notificationService()
          .create(this.notification)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.notification.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
