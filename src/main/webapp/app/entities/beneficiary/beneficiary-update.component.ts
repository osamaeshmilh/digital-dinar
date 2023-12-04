import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import BeneficiaryService from './beneficiary.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import BankBranchService from '@/entities/bank-branch/bank-branch.service';
import { type IBankBranch } from '@/shared/model/bank-branch.model';
import WalletUserService from '@/entities/wallet-user/wallet-user.service';
import { type IWalletUser } from '@/shared/model/wallet-user.model';
import { type IBeneficiary, Beneficiary } from '@/shared/model/beneficiary.model';
import { IdType } from '@/shared/model/enumerations/id-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'BeneficiaryUpdate',
  setup() {
    const beneficiaryService = inject('beneficiaryService', () => new BeneficiaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const beneficiary: Ref<IBeneficiary> = ref(new Beneficiary());

    const bankBranchService = inject('bankBranchService', () => new BankBranchService());

    const bankBranches: Ref<IBankBranch[]> = ref([]);

    const walletUserService = inject('walletUserService', () => new WalletUserService());

    const walletUsers: Ref<IWalletUser[]> = ref([]);
    const idTypeValues: Ref<string[]> = ref(Object.keys(IdType));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveBeneficiary = async beneficiaryId => {
      try {
        const res = await beneficiaryService().find(beneficiaryId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        beneficiary.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.beneficiaryId) {
      retrieveBeneficiary(route.params.beneficiaryId);
    }

    const initRelationships = () => {
      bankBranchService()
        .retrieve()
        .then(res => {
          bankBranches.value = res.data;
        });
      walletUserService()
        .retrieve()
        .then(res => {
          walletUsers.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      arabicFirstName: {},
      arabicLastName: {},
      englishFirstName: {},
      englishLastName: {},
      bankAccountName: {},
      bankAccountNumber: {},
      idType: {},
      idNo: {},
      mobileNo: {},
      email: {},
      notes: {},
      isVerified: {},
      hasTransferred: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
      bankBranch: {},
      walletUser: {},
    };
    const v$ = useVuelidate(validationRules, beneficiary as any);
    v$.value.$validate();

    return {
      beneficiaryService,
      alertService,
      beneficiary,
      previousState,
      idTypeValues,
      isSaving,
      currentLanguage,
      bankBranches,
      walletUsers,
      v$,
      ...useDateFormat({ entityRef: beneficiary }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.beneficiary.id) {
        this.beneficiaryService()
          .update(this.beneficiary)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.beneficiary.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.beneficiaryService()
          .create(this.beneficiary)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.beneficiary.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
