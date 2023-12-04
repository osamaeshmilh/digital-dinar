/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WalletUserUpdate from './wallet-user-update.vue';
import WalletUserService from './wallet-user.service';
import AlertService from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';
import CategoryService from '@/entities/category/category.service';
import CityService from '@/entities/city/city.service';
import WalletProfileService from '@/entities/wallet-profile/wallet-profile.service';
import BankBranchService from '@/entities/bank-branch/bank-branch.service';

type WalletUserUpdateComponentType = InstanceType<typeof WalletUserUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const walletUserSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<WalletUserUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('WalletUser Management Update Component', () => {
    let comp: WalletUserUpdateComponentType;
    let walletUserServiceStub: SinonStubbedInstance<WalletUserService>;

    beforeEach(() => {
      route = {};
      walletUserServiceStub = sinon.createStubInstance<WalletUserService>(WalletUserService);
      walletUserServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          walletUserService: () => walletUserServiceStub,

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
          bankBranchService: () =>
            sinon.createStubInstance<BankBranchService>(BankBranchService, {
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
        const wrapper = shallowMount(WalletUserUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.walletUser = walletUserSample;
        walletUserServiceStub.update.resolves(walletUserSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(walletUserServiceStub.update.calledWith(walletUserSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        walletUserServiceStub.create.resolves(entity);
        const wrapper = shallowMount(WalletUserUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.walletUser = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(walletUserServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        walletUserServiceStub.find.resolves(walletUserSample);
        walletUserServiceStub.retrieve.resolves([walletUserSample]);

        // WHEN
        route = {
          params: {
            walletUserId: '' + walletUserSample.id,
          },
        };
        const wrapper = shallowMount(WalletUserUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.walletUser).toMatchObject(walletUserSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        walletUserServiceStub.find.resolves(walletUserSample);
        const wrapper = shallowMount(WalletUserUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
