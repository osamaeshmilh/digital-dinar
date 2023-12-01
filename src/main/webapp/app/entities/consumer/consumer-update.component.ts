import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ConsumerService from './consumer.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';
import WalletProfileService from '@/entities/wallet-profile/wallet-profile.service';
import { type IWalletProfile } from '@/shared/model/wallet-profile.model';
import { type IConsumer, Consumer } from '@/shared/model/consumer.model';
import { Gender } from '@/shared/model/enumerations/gender.model';
import { IdType } from '@/shared/model/enumerations/id-type.model';
import { WalletOwnerStatus } from '@/shared/model/enumerations/wallet-owner-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ConsumerUpdate',
  setup() {
    const consumerService = inject('consumerService', () => new ConsumerService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const consumer: Ref<IConsumer> = ref(new Consumer());
    const userService = inject('userService', () => new UserService());
    const users: Ref<Array<any>> = ref([]);

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

    const retrieveConsumer = async consumerId => {
      try {
        const res = await consumerService().find(consumerId);
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        consumer.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.consumerId) {
      retrieveConsumer(route.params.consumerId);
    }

    const initRelationships = () => {
      userService()
        .retrieve()
        .then(res => {
          users.value = res.data;
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
      walletProfile: {},
    };
    const v$ = useVuelidate(validationRules, consumer as any);
    v$.value.$validate();

    return {
      consumerService,
      alertService,
      consumer,
      previousState,
      genderValues,
      idTypeValues,
      walletOwnerStatusValues,
      isSaving,
      currentLanguage,
      users,
      walletProfiles,
      ...dataUtils,
      v$,
      ...useDateFormat({ entityRef: consumer }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.consumer.id) {
        this.consumerService()
          .update(this.consumer)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('digitalDinarApp.consumer.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.consumerService()
          .create(this.consumer)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('digitalDinarApp.consumer.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    clearInputImage(field, fieldContentType, idInput): void {
      if (this.consumer && field && fieldContentType) {
        if (Object.prototype.hasOwnProperty.call(this.consumer, field)) {
          this.consumer[field] = null;
        }
        if (Object.prototype.hasOwnProperty.call(this.consumer, fieldContentType)) {
          this.consumer[fieldContentType] = null;
        }
        if (idInput) {
          (<any>this).$refs[idInput] = null;
        }
      }
    },
  },
});
