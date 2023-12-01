/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SettingUpdate from './setting-update.vue';
import SettingService from './setting.service';
import AlertService from '@/shared/alert/alert.service';

type SettingUpdateComponentType = InstanceType<typeof SettingUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const settingSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SettingUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Setting Management Update Component', () => {
    let comp: SettingUpdateComponentType;
    let settingServiceStub: SinonStubbedInstance<SettingService>;

    beforeEach(() => {
      route = {};
      settingServiceStub = sinon.createStubInstance<SettingService>(SettingService);
      settingServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          settingService: () => settingServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(SettingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.setting = settingSample;
        settingServiceStub.update.resolves(settingSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(settingServiceStub.update.calledWith(settingSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        settingServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SettingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.setting = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(settingServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        settingServiceStub.find.resolves(settingSample);
        settingServiceStub.retrieve.resolves([settingSample]);

        // WHEN
        route = {
          params: {
            settingId: '' + settingSample.id,
          },
        };
        const wrapper = shallowMount(SettingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.setting).toMatchObject(settingSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        settingServiceStub.find.resolves(settingSample);
        const wrapper = shallowMount(SettingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
