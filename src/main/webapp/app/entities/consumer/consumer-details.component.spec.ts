/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ConsumerDetails from './consumer-details.vue';
import ConsumerService from './consumer.service';
import AlertService from '@/shared/alert/alert.service';

type ConsumerDetailsComponentType = InstanceType<typeof ConsumerDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const consumerSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Consumer Management Detail Component', () => {
    let consumerServiceStub: SinonStubbedInstance<ConsumerService>;
    let mountOptions: MountingOptions<ConsumerDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      consumerServiceStub = sinon.createStubInstance<ConsumerService>(ConsumerService);

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
          consumerService: () => consumerServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        consumerServiceStub.find.resolves(consumerSample);
        route = {
          params: {
            consumerId: '' + 123,
          },
        };
        const wrapper = shallowMount(ConsumerDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.consumer).toMatchObject(consumerSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        consumerServiceStub.find.resolves(consumerSample);
        const wrapper = shallowMount(ConsumerDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
