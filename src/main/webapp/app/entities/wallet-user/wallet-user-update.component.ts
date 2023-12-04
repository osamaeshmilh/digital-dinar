import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import WalletUserService from './wallet-user.service';
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
import BankBranchService from '@/entities/bank-branch/bank-branch.service';
import { type IBankBranch } from '@/shared/model/bank-branch.model';
import { type IWalletUser, WalletUser } from '@/shared/model/wallet-user.model';
import { Gender } from '@/shared/model/enumerations/gender.model';
import { IdType } from '@/shared/model/enumerations/id-type.model';
import { WalletType } from '@/shared/model/enumerations/wallet-type.model';
import { WalletStatus } from '@/shared/model/enumerations/wallet-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WalletUserUpdate',
  setup() {
    const walletUserService = inject('walletUserService', () => new WalletUserService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const walletUser: Ref<IWalletUser> = ref(new WalletUser());
    const userService = inject('userService', () => new UserService());
    const users: Ref<Array<any>> = ref([]);

    const categoryService = inject('categoryService', () => new CategoryService());

    const categories: Ref<ICategory[]> = ref([]);

    const cityService = inject('cityService', () => new CityService());

    const cities: Ref<ICity[]> = ref([]);

    const walletProfileService = inject('walletProfileService', () => new WalletProfileService());

    const walletProfiles: Ref<IWalletProfile[]> = ref([]);

    const bankBranchService = inject('bankBranchService', () => new BankBranchService());

    const bankBranches: Ref<IBankBranch[]> = ref([]);
    const genderValues: Ref<string[]> = ref(Object.keys(Gender));
    const idTypeValues: Ref<string[]> = ref(Object.keys(IdType));
    const walletTypeValues: Ref<string[]> = ref(Object.keys(WalletType));
    const walletStatusValues: Ref<string[]> = ref(Object.keys(WalletStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveWalletUser = async walletUserId => {
      try {
        const res = await walletUserService().find(walletUserId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        walletUser.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.walletUserId) {
      retrieveWalletUser(route.params.walletUserId);
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
      bankBranchService()
        .retrieve()
        .then(res => {
          bankBranches.value = res.data;
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
      commercialName: {},
      birthDate: {},
      birthPlace: {},
      nationalNo: {},
      registerNo: {},
      gender: {},
      idType: {},
      idNo: {},
      mobileNo: {},
      verifiedByEmailOtp: {},
      verifiedByMobileOtp: {},
      email: {},
      nidStatus: {},
      isForeign: {},
      isMobileConnectedWithNid: {},
      isKYCVerified: {},
      walletType: {},
      walletStatus: {},
      imageFile: {},
      imageUrlFile: {},
      postCode: {},
      address: {},
      lat: {},
      lng: {},
      notes: {},
      bankAccountName: {},
      bankAccountNumber: {},
      bankAccountIBAN: {},
      bankAccountSWIFT: {},
      createdBy: {},
      createdDate: {},
      lastModifiedBy: {},
      lastModifiedDate: {},
      user: {},
      category: {},
      city: {},
      walletProfile: {},
      bankBranch: {},
      walletTransactions: {},
      beneficiaries: {},
    };
    const v$ = useVuelidate(validationRules, walletUser as any);
    v$.value.$validate();

    return {
      walletUserService,
      alertService,
      walletUser,
      previousState,
      genderValues,
      idTypeValues,
      walletTypeValues,
      walletStatusValues,
      isSaving,
      currentLanguage,
      users,
      categories,
      cities,
      walletProfiles,
      bankBranches,
      ...dataUtils,
      v$,
      ...useDateFormat({ entityRef: walletUser }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.walletUser.id) {
        this.walletUserService()
          .update(this.walletUser)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.walletUser.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.walletUserService()
          .create(this.walletUser)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.walletUser.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    clearInputImage(field, fieldContentType, idInput): void {
      if (this.walletUser && field && fieldContentType) {
        if (Object.prototype.hasOwnProperty.call(this.walletUser, field)) {
          this.walletUser[field] = null;
        }
        if (Object.prototype.hasOwnProperty.call(this.walletUser, fieldContentType)) {
          this.walletUser[fieldContentType] = null;
        }
        if (idInput) {
          (<any>this).$refs[idInput] = null;
        }
      }
    },
  },
});
