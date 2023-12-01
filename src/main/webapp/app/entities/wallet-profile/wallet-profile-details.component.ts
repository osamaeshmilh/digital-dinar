import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import WalletProfileService from './wallet-profile.service';
import { useDateFormat } from '@/shared/composables';
import { type IWalletProfile } from '@/shared/model/wallet-profile.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WalletProfileDetails',
  setup() {
    const dateFormat = useDateFormat();
    const walletProfileService = inject('walletProfileService', () => new WalletProfileService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const walletProfile: Ref<IWalletProfile> = ref({});

    const retrieveWalletProfile = async walletProfileId => {
      try {
        const res = await walletProfileService().find(walletProfileId);
        walletProfile.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.walletProfileId) {
      retrieveWalletProfile(route.params.walletProfileId);
    }

    return {
      ...dateFormat,
      alertService,
      walletProfile,

      previousState,
      t$: useI18n().t,
    };
  },
});
