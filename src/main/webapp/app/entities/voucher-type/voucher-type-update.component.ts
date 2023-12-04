import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import VoucherTypeService from './voucher-type.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import VoucherCompanyService from '@/entities/voucher-company/voucher-company.service';
import { type IVoucherCompany } from '@/shared/model/voucher-company.model';
import { type IVoucherType, VoucherType } from '@/shared/model/voucher-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'VoucherTypeUpdate',
  setup() {
    const voucherTypeService = inject('voucherTypeService', () => new VoucherTypeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const voucherType: Ref<IVoucherType> = ref(new VoucherType());

    const voucherCompanyService = inject('voucherCompanyService', () => new VoucherCompanyService());

    const voucherCompanies: Ref<IVoucherCompany[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveVoucherType = async voucherTypeId => {
      try {
        const res = await voucherTypeService().find(voucherTypeId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        voucherType.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.voucherTypeId) {
      retrieveVoucherType(route.params.voucherTypeId);
    }

    const initRelationships = () => {
      voucherCompanyService()
        .retrieve()
        .then(res => {
          voucherCompanies.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      nameAr: {},
      nameEn: {},
      slug: {},
      amount: {},
      menuOrder: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
      voucherCompany: {},
    };
    const v$ = useVuelidate(validationRules, voucherType as any);
    v$.value.$validate();

    return {
      voucherTypeService,
      alertService,
      voucherType,
      previousState,
      isSaving,
      currentLanguage,
      voucherCompanies,
      v$,
      ...useDateFormat({ entityRef: voucherType }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.voucherType.id) {
        this.voucherTypeService()
          .update(this.voucherType)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.voucherType.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.voucherTypeService()
          .create(this.voucherType)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.voucherType.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
