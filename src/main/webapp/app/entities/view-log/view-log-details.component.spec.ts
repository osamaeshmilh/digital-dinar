/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ViewLogDetails from './view-log-details.vue';
import ViewLogService from './view-log.service';
import AlertService from '@/shared/alert/alert.service';

type ViewLogDetailsComponentType = InstanceType<typeof ViewLogDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const viewLogSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ViewLog Management Detail Component', () => {
    let viewLogServiceStub: SinonStubbedInstance<ViewLogService>;
    let mountOptions: MountingOptions<ViewLogDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      viewLogServiceStub = sinon.createStubInstance<ViewLogService>(ViewLogService);

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
          viewLogService: () => viewLogServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        viewLogServiceStub.find.resolves(viewLogSample);
        route = {
          params: {
            viewLogId: '' + 123,
          },
        };
        const wrapper = shallowMount(ViewLogDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.viewLog).toMatchObject(viewLogSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        viewLogServiceStub.find.resolves(viewLogSample);
        const wrapper = shallowMount(ViewLogDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
