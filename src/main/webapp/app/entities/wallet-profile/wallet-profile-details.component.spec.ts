/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WalletProfileDetails from './wallet-profile-details.vue';
import WalletProfileService from './wallet-profile.service';
import AlertService from '@/shared/alert/alert.service';

type WalletProfileDetailsComponentType = InstanceType<typeof WalletProfileDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const walletProfileSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('WalletProfile Management Detail Component', () => {
    let walletProfileServiceStub: SinonStubbedInstance<WalletProfileService>;
    let mountOptions: MountingOptions<WalletProfileDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      walletProfileServiceStub = sinon.createStubInstance<WalletProfileService>(WalletProfileService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          walletProfileService: () => walletProfileServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        walletProfileServiceStub.find.resolves(walletProfileSample);
        route = {
          params: {
            walletProfileId: '' + 123,
          },
        };
        const wrapper = shallowMount(WalletProfileDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.walletProfile).toMatchObject(walletProfileSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        walletProfileServiceStub.find.resolves(walletProfileSample);
        const wrapper = shallowMount(WalletProfileDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
