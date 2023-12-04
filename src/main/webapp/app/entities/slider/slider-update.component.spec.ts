/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import SliderUpdate from './slider-update.vue';
import SliderService from './slider.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type SliderUpdateComponentType = InstanceType<typeof SliderUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sliderSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SliderUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Slider Management Update Component', () => {
    let comp: SliderUpdateComponentType;
    let sliderServiceStub: SinonStubbedInstance<SliderService>;

    beforeEach(() => {
      route = {};
      sliderServiceStub = sinon.createStubInstance<SliderService>(SliderService);
      sliderServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          sliderService: () => sliderServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(SliderUpdate, { global: mountOptions });
        comp = wrapper.vm;
      });
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(dayjs(date).format(DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(SliderUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.slider = sliderSample;
        sliderServiceStub.update.resolves(sliderSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sliderServiceStub.update.calledWith(sliderSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        sliderServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SliderUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.slider = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sliderServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        sliderServiceStub.find.resolves(sliderSample);
        sliderServiceStub.retrieve.resolves([sliderSample]);

        // WHEN
        route = {
          params: {
            sliderId: '' + sliderSample.id,
          },
        };
        const wrapper = shallowMount(SliderUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.slider).toMatchObject(sliderSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sliderServiceStub.find.resolves(sliderSample);
        const wrapper = shallowMount(SliderUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
