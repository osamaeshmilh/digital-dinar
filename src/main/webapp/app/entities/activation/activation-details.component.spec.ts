/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ActivationDetails from './activation-details.vue';
import ActivationService from './activation.service';
import AlertService from '@/shared/alert/alert.service';

type ActivationDetailsComponentType = InstanceType<typeof ActivationDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const activationSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Activation Management Detail Component', () => {
    let activationServiceStub: SinonStubbedInstance<ActivationService>;
    let mountOptions: MountingOptions<ActivationDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      activationServiceStub = sinon.createStubInstance<ActivationService>(ActivationService);

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
          activationService: () => activationServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        activationServiceStub.find.resolves(activationSample);
        route = {
          params: {
            activationId: '' + 123,
          },
        };
        const wrapper = shallowMount(ActivationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.activation).toMatchObject(activationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        activationServiceStub.find.resolves(activationSample);
        const wrapper = shallowMount(ActivationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
