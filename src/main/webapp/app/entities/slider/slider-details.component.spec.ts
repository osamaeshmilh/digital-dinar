/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SliderDetails from './slider-details.vue';
import SliderService from './slider.service';
import AlertService from '@/shared/alert/alert.service';

type SliderDetailsComponentType = InstanceType<typeof SliderDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sliderSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Slider Management Detail Component', () => {
    let sliderServiceStub: SinonStubbedInstance<SliderService>;
    let mountOptions: MountingOptions<SliderDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      sliderServiceStub = sinon.createStubInstance<SliderService>(SliderService);

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
          sliderService: () => sliderServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sliderServiceStub.find.resolves(sliderSample);
        route = {
          params: {
            sliderId: '' + 123,
          },
        };
        const wrapper = shallowMount(SliderDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.slider).toMatchObject(sliderSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sliderServiceStub.find.resolves(sliderSample);
        const wrapper = shallowMount(SliderDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
