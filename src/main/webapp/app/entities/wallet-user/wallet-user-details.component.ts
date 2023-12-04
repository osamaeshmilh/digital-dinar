import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import WalletUserService from './wallet-user.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { type IWalletUser } from '@/shared/model/wallet-user.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WalletUserDetails',
  setup() {
    const walletUserService = inject('walletUserService', () => new WalletUserService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const dataUtils = useDataUtils();

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const walletUser: Ref<IWalletUser> = ref({});

    const retrieveWalletUser = async walletUserId => {
      try {
        const res = await walletUserService().find(walletUserId);
        walletUser.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.walletUserId) {
      retrieveWalletUser(route.params.walletUserId);
    }

    return {
      alertService,
      walletUser,

      ...dataUtils,

      previousState,
      t$: useI18n().t,
    };
  },
});
