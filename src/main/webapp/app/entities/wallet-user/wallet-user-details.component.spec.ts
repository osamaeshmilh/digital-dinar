/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WalletUserDetails from './wallet-user-details.vue';
import WalletUserService from './wallet-user.service';
import AlertService from '@/shared/alert/alert.service';

type WalletUserDetailsComponentType = InstanceType<typeof WalletUserDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const walletUserSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('WalletUser Management Detail Component', () => {
    let walletUserServiceStub: SinonStubbedInstance<WalletUserService>;
    let mountOptions: MountingOptions<WalletUserDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      walletUserServiceStub = sinon.createStubInstance<WalletUserService>(WalletUserService);

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
          walletUserService: () => walletUserServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        walletUserServiceStub.find.resolves(walletUserSample);
        route = {
          params: {
            walletUserId: '' + 123,
          },
        };
        const wrapper = shallowMount(WalletUserDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.walletUser).toMatchObject(walletUserSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        walletUserServiceStub.find.resolves(walletUserSample);
        const wrapper = shallowMount(WalletUserDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
