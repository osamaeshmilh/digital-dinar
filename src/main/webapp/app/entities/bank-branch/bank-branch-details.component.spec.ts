/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import BankBranchDetails from './bank-branch-details.vue';
import BankBranchService from './bank-branch.service';
import AlertService from '@/shared/alert/alert.service';

type BankBranchDetailsComponentType = InstanceType<typeof BankBranchDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const bankBranchSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('BankBranch Management Detail Component', () => {
    let bankBranchServiceStub: SinonStubbedInstance<BankBranchService>;
    let mountOptions: MountingOptions<BankBranchDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      bankBranchServiceStub = sinon.createStubInstance<BankBranchService>(BankBranchService);

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
          bankBranchService: () => bankBranchServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        bankBranchServiceStub.find.resolves(bankBranchSample);
        route = {
          params: {
            bankBranchId: '' + 123,
          },
        };
        const wrapper = shallowMount(BankBranchDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.bankBranch).toMatchObject(bankBranchSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        bankBranchServiceStub.find.resolves(bankBranchSample);
        const wrapper = shallowMount(BankBranchDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
