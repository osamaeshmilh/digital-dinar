import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import VoucherService from './voucher.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import VoucherTypeService from '@/entities/voucher-type/voucher-type.service';
import { type IVoucherType } from '@/shared/model/voucher-type.model';
import VoucherCompanyService from '@/entities/voucher-company/voucher-company.service';
import { type IVoucherCompany } from '@/shared/model/voucher-company.model';
import { type IVoucher, Voucher } from '@/shared/model/voucher.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'VoucherUpdate',
  setup() {
    const voucherService = inject('voucherService', () => new VoucherService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const voucher: Ref<IVoucher> = ref(new Voucher());

    const voucherTypeService = inject('voucherTypeService', () => new VoucherTypeService());

    const voucherTypes: Ref<IVoucherType[]> = ref([]);

    const voucherCompanyService = inject('voucherCompanyService', () => new VoucherCompanyService());

    const voucherCompanies: Ref<IVoucherCompany[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveVoucher = async voucherId => {
      try {
        const res = await voucherService().find(voucherId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        voucher.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.voucherId) {
      retrieveVoucher(route.params.voucherId);
    }

    const initRelationships = () => {
      voucherTypeService()
        .retrieve()
        .then(res => {
          voucherTypes.value = res.data;
        });
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
      code: {},
      amount: {},
      serialNumber: {},
      isSold: {},
      sellTransactionId: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
      voucherType: {},
      voucherCompany: {},
    };
    const v$ = useVuelidate(validationRules, voucher as any);
    v$.value.$validate();

    return {
      voucherService,
      alertService,
      voucher,
      previousState,
      isSaving,
      currentLanguage,
      voucherTypes,
      voucherCompanies,
      v$,
      ...useDateFormat({ entityRef: voucher }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.voucher.id) {
        this.voucherService()
          .update(this.voucher)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.voucher.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.voucherService()
          .create(this.voucher)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.voucher.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
