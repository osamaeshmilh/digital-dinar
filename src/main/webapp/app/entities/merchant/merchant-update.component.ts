import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import MerchantService from './merchant.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';
import CategoryService from '@/entities/category/category.service';
import { type ICategory } from '@/shared/model/category.model';
import CityService from '@/entities/city/city.service';
import { type ICity } from '@/shared/model/city.model';
import WalletProfileService from '@/entities/wallet-profile/wallet-profile.service';
import { type IWalletProfile } from '@/shared/model/wallet-profile.model';
import { type IMerchant, Merchant } from '@/shared/model/merchant.model';
import { Gender } from '@/shared/model/enumerations/gender.model';
import { IdType } from '@/shared/model/enumerations/id-type.model';
import { WalletOwnerStatus } from '@/shared/model/enumerations/wallet-owner-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MerchantUpdate',
  setup() {
    const merchantService = inject('merchantService', () => new MerchantService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const merchant: Ref<IMerchant> = ref(new Merchant());
    const userService = inject('userService', () => new UserService());
    const users: Ref<Array<any>> = ref([]);

    const categoryService = inject('categoryService', () => new CategoryService());

    const categories: Ref<ICategory[]> = ref([]);

    const cityService = inject('cityService', () => new CityService());

    const cities: Ref<ICity[]> = ref([]);

    const walletProfileService = inject('walletProfileService', () => new WalletProfileService());

    const walletProfiles: Ref<IWalletProfile[]> = ref([]);
    const genderValues: Ref<string[]> = ref(Object.keys(Gender));
    const idTypeValues: Ref<string[]> = ref(Object.keys(IdType));
    const walletOwnerStatusValues: Ref<string[]> = ref(Object.keys(WalletOwnerStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveMerchant = async merchantId => {
      try {
        const res = await merchantService().find(merchantId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        merchant.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.merchantId) {
      retrieveMerchant(route.params.merchantId);
    }

    const initRelationships = () => {
      userService()
        .retrieve()
        .then(res => {
          users.value = res.data;
        });
      categoryService()
        .retrieve()
        .then(res => {
          categories.value = res.data;
        });
      cityService()
        .retrieve()
        .then(res => {
          cities.value = res.data;
        });
      walletProfileService()
        .retrieve()
        .then(res => {
          walletProfiles.value = res.data;
        });
    };

    initRelationships();

    const dataUtils = useDataUtils();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      arabicFirstName: {},
      arabicMiddleName: {},
      arabicLastName: {},
      englishFirstName: {},
      englishMiddleName: {},
      englishLastName: {},
      commercialRegistryNo: {},
      commercialLicenceNo: {},
      birthDate: {},
      birthPlace: {},
      nationalNo: {},
      registerNo: {},
      gender: {},
      idType: {},
      idNo: {},
      mobileNo: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      verifiedByEmailOtp: {},
      verifiedByMobileNoOtp: {},
      email: {},
      nidStatus: {},
      isForeign: {},
      isMobileConnectedWithNid: {},
      walletOwnerStatus: {},
      imageFile: {},
      imageUrlFile: {},
      postCode: {},
      address: {},
      lat: {},
      lng: {},
      notes: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
      user: {},
      category: {},
      city: {},
      walletProfile: {},
    };
    const v$ = useVuelidate(validationRules, merchant as any);
    v$.value.$validate();

    return {
      merchantService,
      alertService,
      merchant,
      previousState,
      genderValues,
      idTypeValues,
      walletOwnerStatusValues,
      isSaving,
      currentLanguage,
      users,
      categories,
      cities,
      walletProfiles,
      ...dataUtils,
      v$,
      ...useDateFormat({ entityRef: merchant }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.merchant.id) {
        this.merchantService()
          .update(this.merchant)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.merchant.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.merchantService()
          .create(this.merchant)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.merchant.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    clearInputImage(field, fieldContentType, idInput): void {
      if (this.merchant && field && fieldContentType) {
        if (Object.prototype.hasOwnProperty.call(this.merchant, field)) {
          this.merchant[field] = null;
        }
        if (Object.prototype.hasOwnProperty.call(this.merchant, fieldContentType)) {
          this.merchant[fieldContentType] = null;
        }
        if (idInput) {
          (<any>this).$refs[idInput] = null;
        }
      }
    },
  },
});
