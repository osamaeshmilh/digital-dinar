/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WalletProfileUpdate from './wallet-profile-update.vue';
import WalletProfileService from './wallet-profile.service';
import AlertService from '@/shared/alert/alert.service';

type WalletProfileUpdateComponentType = InstanceType<typeof WalletProfileUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const walletProfileSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<WalletProfileUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('WalletProfile Management Update Component', () => {
    let comp: WalletProfileUpdateComponentType;
    let walletProfileServiceStub: SinonStubbedInstance<WalletProfileService>;

    beforeEach(() => {
      route = {};
      walletProfileServiceStub = sinon.createStubInstance<WalletProfileService>(WalletProfileService);
      walletProfileServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          walletProfileService: () => walletProfileServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(WalletProfileUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.walletProfile = walletProfileSample;
        walletProfileServiceStub.update.resolves(walletProfileSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(walletProfileServiceStub.update.calledWith(walletProfileSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        walletProfileServiceStub.create.resolves(entity);
        const wrapper = shallowMount(WalletProfileUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.walletProfile = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(walletProfileServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        walletProfileServiceStub.find.resolves(walletProfileSample);
        walletProfileServiceStub.retrieve.resolves([walletProfileSample]);

        // WHEN
        route = {
          params: {
            walletProfileId: '' + walletProfileSample.id,
          },
        };
        const wrapper = shallowMount(WalletProfileUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.walletProfile).toMatchObject(walletProfileSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        walletProfileServiceStub.find.resolves(walletProfileSample);
        const wrapper = shallowMount(WalletProfileUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
