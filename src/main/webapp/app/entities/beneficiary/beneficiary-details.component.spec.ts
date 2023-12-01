/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import BeneficiaryDetails from './beneficiary-details.vue';
import BeneficiaryService from './beneficiary.service';
import AlertService from '@/shared/alert/alert.service';

type BeneficiaryDetailsComponentType = InstanceType<typeof BeneficiaryDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const beneficiarySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Beneficiary Management Detail Component', () => {
    let beneficiaryServiceStub: SinonStubbedInstance<BeneficiaryService>;
    let mountOptions: MountingOptions<BeneficiaryDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      beneficiaryServiceStub = sinon.createStubInstance<BeneficiaryService>(BeneficiaryService);

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
          beneficiaryService: () => beneficiaryServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        beneficiaryServiceStub.find.resolves(beneficiarySample);
        route = {
          params: {
            beneficiaryId: '' + 123,
          },
        };
        const wrapper = shallowMount(BeneficiaryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.beneficiary).toMatchObject(beneficiarySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        beneficiaryServiceStub.find.resolves(beneficiarySample);
        const wrapper = shallowMount(BeneficiaryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
