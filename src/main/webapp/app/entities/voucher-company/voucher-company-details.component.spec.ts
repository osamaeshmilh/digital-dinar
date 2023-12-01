/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import VoucherCompanyDetails from './voucher-company-details.vue';
import VoucherCompanyService from './voucher-company.service';
import AlertService from '@/shared/alert/alert.service';

type VoucherCompanyDetailsComponentType = InstanceType<typeof VoucherCompanyDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const voucherCompanySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('VoucherCompany Management Detail Component', () => {
    let voucherCompanyServiceStub: SinonStubbedInstance<VoucherCompanyService>;
    let mountOptions: MountingOptions<VoucherCompanyDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      voucherCompanyServiceStub = sinon.createStubInstance<VoucherCompanyService>(VoucherCompanyService);

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
          voucherCompanyService: () => voucherCompanyServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        voucherCompanyServiceStub.find.resolves(voucherCompanySample);
        route = {
          params: {
            voucherCompanyId: '' + 123,
          },
        };
        const wrapper = shallowMount(VoucherCompanyDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.voucherCompany).toMatchObject(voucherCompanySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        voucherCompanyServiceStub.find.resolves(voucherCompanySample);
        const wrapper = shallowMount(VoucherCompanyDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
