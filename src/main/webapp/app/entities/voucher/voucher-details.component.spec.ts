/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import VoucherDetails from './voucher-details.vue';
import VoucherService from './voucher.service';
import AlertService from '@/shared/alert/alert.service';

type VoucherDetailsComponentType = InstanceType<typeof VoucherDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const voucherSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Voucher Management Detail Component', () => {
    let voucherServiceStub: SinonStubbedInstance<VoucherService>;
    let mountOptions: MountingOptions<VoucherDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      voucherServiceStub = sinon.createStubInstance<VoucherService>(VoucherService);

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
          voucherService: () => voucherServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        voucherServiceStub.find.resolves(voucherSample);
        route = {
          params: {
            voucherId: '' + 123,
          },
        };
        const wrapper = shallowMount(VoucherDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.voucher).toMatchObject(voucherSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        voucherServiceStub.find.resolves(voucherSample);
        const wrapper = shallowMount(VoucherDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
