/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WalletTransactionDetails from './wallet-transaction-details.vue';
import WalletTransactionService from './wallet-transaction.service';
import AlertService from '@/shared/alert/alert.service';

type WalletTransactionDetailsComponentType = InstanceType<typeof WalletTransactionDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const walletTransactionSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('WalletTransaction Management Detail Component', () => {
    let walletTransactionServiceStub: SinonStubbedInstance<WalletTransactionService>;
    let mountOptions: MountingOptions<WalletTransactionDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      walletTransactionServiceStub = sinon.createStubInstance<WalletTransactionService>(WalletTransactionService);

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
          walletTransactionService: () => walletTransactionServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        walletTransactionServiceStub.find.resolves(walletTransactionSample);
        route = {
          params: {
            walletTransactionId: '' + 123,
          },
        };
        const wrapper = shallowMount(WalletTransactionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.walletTransaction).toMatchObject(walletTransactionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        walletTransactionServiceStub.find.resolves(walletTransactionSample);
        const wrapper = shallowMount(WalletTransactionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
