/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SettingDetails from './setting-details.vue';
import SettingService from './setting.service';
import AlertService from '@/shared/alert/alert.service';

type SettingDetailsComponentType = InstanceType<typeof SettingDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const settingSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Setting Management Detail Component', () => {
    let settingServiceStub: SinonStubbedInstance<SettingService>;
    let mountOptions: MountingOptions<SettingDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      settingServiceStub = sinon.createStubInstance<SettingService>(SettingService);

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
          settingService: () => settingServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        settingServiceStub.find.resolves(settingSample);
        route = {
          params: {
            settingId: '' + 123,
          },
        };
        const wrapper = shallowMount(SettingDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.setting).toMatchObject(settingSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        settingServiceStub.find.resolves(settingSample);
        const wrapper = shallowMount(SettingDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
