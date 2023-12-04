import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import BankBranchService from './bank-branch.service';
import { type IBankBranch } from '@/shared/model/bank-branch.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'BankBranchDetails',
  setup() {
    const bankBranchService = inject('bankBranchService', () => new BankBranchService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const bankBranch: Ref<IBankBranch> = ref({});

    const retrieveBankBranch = async bankBranchId => {
      try {
        const res = await bankBranchService().find(bankBranchId);
        bankBranch.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.bankBranchId) {
      retrieveBankBranch(route.params.bankBranchId);
    }

    return {
      alertService,
      bankBranch,

      previousState,
      t$: useI18n().t,
    };
  },
});
