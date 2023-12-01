import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import WalletProfileService from './wallet-profile.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IWalletProfile, WalletProfile } from '@/shared/model/wallet-profile.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WalletProfileUpdate',
  setup() {
    const walletProfileService = inject('walletProfileService', () => new WalletProfileService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const walletProfile: Ref<IWalletProfile> = ref(new WalletProfile());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      nameAr: {},
      nameEn: {},
      hasTransferAmountLimitPerTransaction: {},
      transferAmountLimitPerTransaction: {},
      hasDailyTransferAmountLimit: {},
      dailyTransferAmountLimit: {},
      canTransferToConsumerWallet: {},
      canTransferToMerchantWallet: {},
      canTransferToConsumer: {},
      canTransferToBank: {},
      canBuyVouchers: {},
      transferToConsumerWalletFees: {},
      transferToMerchantWalletFees: {},
      transferToConsumerFees: {},
      transferToBankFees: {},
      buyVouchersFees: {},
    };
    const v$ = useVuelidate(validationRules, walletProfile as any);
    v$.value.$validate();

    return {
      walletProfileService,
      alertService,
      walletProfile,
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
      if (this.walletProfile.id) {
        this.walletProfileService()
          .update(this.walletProfile)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.walletProfile.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.walletProfileService()
          .create(this.walletProfile)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.walletProfile.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
