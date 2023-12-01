/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import VoucherTypeDetails from './voucher-type-details.vue';
import VoucherTypeService from './voucher-type.service';
import AlertService from '@/shared/alert/alert.service';

type VoucherTypeDetailsComponentType = InstanceType<typeof VoucherTypeDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const voucherTypeSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('VoucherType Management Detail Component', () => {
    let voucherTypeServiceStub: SinonStubbedInstance<VoucherTypeService>;
    let mountOptions: MountingOptions<VoucherTypeDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      voucherTypeServiceStub = sinon.createStubInstance<VoucherTypeService>(VoucherTypeService);

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
          voucherTypeService: () => voucherTypeServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        voucherTypeServiceStub.find.resolves(voucherTypeSample);
        route = {
          params: {
            voucherTypeId: '' + 123,
          },
        };
        const wrapper = shallowMount(VoucherTypeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.voucherType).toMatchObject(voucherTypeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        voucherTypeServiceStub.find.resolves(voucherTypeSample);
        const wrapper = shallowMount(VoucherTypeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
