/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import ViewLogUpdate from './view-log-update.vue';
import ViewLogService from './view-log.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type ViewLogUpdateComponentType = InstanceType<typeof ViewLogUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const viewLogSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ViewLogUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ViewLog Management Update Component', () => {
    let comp: ViewLogUpdateComponentType;
    let viewLogServiceStub: SinonStubbedInstance<ViewLogService>;

    beforeEach(() => {
      route = {};
      viewLogServiceStub = sinon.createStubInstance<ViewLogService>(ViewLogService);
      viewLogServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          viewLogService: () => viewLogServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(ViewLogUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(ViewLogUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.viewLog = viewLogSample;
        viewLogServiceStub.update.resolves(viewLogSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(viewLogServiceStub.update.calledWith(viewLogSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        viewLogServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ViewLogUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.viewLog = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(viewLogServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        viewLogServiceStub.find.resolves(viewLogSample);
        viewLogServiceStub.retrieve.resolves([viewLogSample]);

        // WHEN
        route = {
          params: {
            viewLogId: '' + viewLogSample.id,
          },
        };
        const wrapper = shallowMount(ViewLogUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.viewLog).toMatchObject(viewLogSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        viewLogServiceStub.find.resolves(viewLogSample);
        const wrapper = shallowMount(ViewLogUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
