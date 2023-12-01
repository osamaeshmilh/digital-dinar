/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MerchantUpdate from './merchant-update.vue';
import MerchantService from './merchant.service';
import AlertService from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';
import CategoryService from '@/entities/category/category.service';
import CityService from '@/entities/city/city.service';
import WalletProfileService from '@/entities/wallet-profile/wallet-profile.service';

type MerchantUpdateComponentType = InstanceType<typeof MerchantUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const merchantSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MerchantUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Merchant Management Update Component', () => {
    let comp: MerchantUpdateComponentType;
    let merchantServiceStub: SinonStubbedInstance<MerchantService>;

    beforeEach(() => {
      route = {};
      merchantServiceStub = sinon.createStubInstance<MerchantService>(MerchantService);
      merchantServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          merchantService: () => merchantServiceStub,

          userService: () =>
            sinon.createStubInstance<UserService>(UserService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          categoryService: () =>
            sinon.createStubInstance<CategoryService>(CategoryService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          cityService: () =>
            sinon.createStubInstance<CityService>(CityService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          walletProfileService: () =>
            sinon.createStubInstance<WalletProfileService>(WalletProfileService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(MerchantUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.merchant = merchantSample;
        merchantServiceStub.update.resolves(merchantSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(merchantServiceStub.update.calledWith(merchantSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        merchantServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MerchantUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.merchant = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(merchantServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        merchantServiceStub.find.resolves(merchantSample);
        merchantServiceStub.retrieve.resolves([merchantSample]);

        // WHEN
        route = {
          params: {
            merchantId: '' + merchantSample.id,
          },
        };
        const wrapper = shallowMount(MerchantUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.merchant).toMatchObject(merchantSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        merchantServiceStub.find.resolves(merchantSample);
        const wrapper = shallowMount(MerchantUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
