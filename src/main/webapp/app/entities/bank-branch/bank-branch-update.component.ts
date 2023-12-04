import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import BankBranchService from './bank-branch.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import BankService from '@/entities/bank/bank.service';
import { type IBank } from '@/shared/model/bank.model';
import { type IBankBranch, BankBranch } from '@/shared/model/bank-branch.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'BankBranchUpdate',
  setup() {
    const bankBranchService = inject('bankBranchService', () => new BankBranchService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const bankBranch: Ref<IBankBranch> = ref(new BankBranch());

    const bankService = inject('bankService', () => new BankService());

    const banks: Ref<IBank[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveBankBranch = async bankBranchId => {
      try {
        const res = await bankBranchService().find(bankBranchId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        bankBranch.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.bankBranchId) {
      retrieveBankBranch(route.params.bankBranchId);
    }

    const initRelationships = () => {
      bankService()
        .retrieve()
        .then(res => {
          banks.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      nameAr: {},
      namEn: {},
      address: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
      bank: {},
    };
    const v$ = useVuelidate(validationRules, bankBranch as any);
    v$.value.$validate();

    return {
      bankBranchService,
      alertService,
      bankBranch,
      previousState,
      isSaving,
      currentLanguage,
      banks,
      v$,
      ...useDateFormat({ entityRef: bankBranch }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.bankBranch.id) {
        this.bankBranchService()
          .update(this.bankBranch)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.bankBranch.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.bankBranchService()
          .create(this.bankBranch)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.bankBranch.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
