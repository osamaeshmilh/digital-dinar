/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import CityUpdate from './city-update.vue';
import CityService from './city.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

import CountryService from '@/entities/country/country.service';

type CityUpdateComponentType = InstanceType<typeof CityUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const citySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CityUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('City Management Update Component', () => {
    let comp: CityUpdateComponentType;
    let cityServiceStub: SinonStubbedInstance<CityService>;

    beforeEach(() => {
      route = {};
      cityServiceStub = sinon.createStubInstance<CityService>(CityService);
      cityServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          cityService: () => cityServiceStub,
          countryService: () =>
            sinon.createStubInstance<CountryService>(CountryService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(CityUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(CityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.city = citySample;
        cityServiceStub.update.resolves(citySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(cityServiceStub.update.calledWith(citySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        cityServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.city = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(cityServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        cityServiceStub.find.resolves(citySample);
        cityServiceStub.retrieve.resolves([citySample]);

        // WHEN
        route = {
          params: {
            cityId: '' + citySample.id,
          },
        };
        const wrapper = shallowMount(CityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.city).toMatchObject(citySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        cityServiceStub.find.resolves(citySample);
        const wrapper = shallowMount(CityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
