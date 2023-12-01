import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import VoucherCompanyService from './voucher-company.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';
import { type IVoucherCompany, VoucherCompany } from '@/shared/model/voucher-company.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'VoucherCompanyUpdate',
  setup() {
    const voucherCompanyService = inject('voucherCompanyService', () => new VoucherCompanyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const voucherCompany: Ref<IVoucherCompany> = ref(new VoucherCompany());
    const userService = inject('userService', () => new UserService());
    const users: Ref<Array<any>> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveVoucherCompany = async voucherCompanyId => {
      try {
        const res = await voucherCompanyService().find(voucherCompanyId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        voucherCompany.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.voucherCompanyId) {
      retrieveVoucherCompany(route.params.voucherCompanyId);
    }

    const initRelationships = () => {
      userService()
        .retrieve()
        .then(res => {
          users.value = res.data;
        });
    };

    initRelationships();

    const dataUtils = useDataUtils();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      nameAr: {},
      nameEn: {},
      mobileNo: {},
      email: {},
      imageFile: {},
      imageUrlFile: {},
      postCode: {},
      address: {},
      notes: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
      user: {},
    };
    const v$ = useVuelidate(validationRules, voucherCompany as any);
    v$.value.$validate();

    return {
      voucherCompanyService,
      alertService,
      voucherCompany,
      previousState,
      isSaving,
      currentLanguage,
      users,
      ...dataUtils,
      v$,
      ...useDateFormat({ entityRef: voucherCompany }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.voucherCompany.id) {
        this.voucherCompanyService()
          .update(this.voucherCompany)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.voucherCompany.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.voucherCompanyService()
          .create(this.voucherCompany)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.voucherCompany.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
