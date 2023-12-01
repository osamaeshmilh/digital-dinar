/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import VoucherTypeUpdate from './voucher-type-update.vue';
import VoucherTypeService from './voucher-type.service';
import AlertService from '@/shared/alert/alert.service';

import VoucherCompanyService from '@/entities/voucher-company/voucher-company.service';

type VoucherTypeUpdateComponentType = InstanceType<typeof VoucherTypeUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const voucherTypeSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<VoucherTypeUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('VoucherType Management Update Component', () => {
    let comp: VoucherTypeUpdateComponentType;
    let voucherTypeServiceStub: SinonStubbedInstance<VoucherTypeService>;

    beforeEach(() => {
      route = {};
      voucherTypeServiceStub = sinon.createStubInstance<VoucherTypeService>(VoucherTypeService);
      voucherTypeServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          voucherTypeService: () => voucherTypeServiceStub,
          voucherCompanyService: () =>
            sinon.createStubInstance<VoucherCompanyService>(VoucherCompanyService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(VoucherTypeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.voucherType = voucherTypeSample;
        voucherTypeServiceStub.update.resolves(voucherTypeSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(voucherTypeServiceStub.update.calledWith(voucherTypeSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        voucherTypeServiceStub.create.resolves(entity);
        const wrapper = shallowMount(VoucherTypeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.voucherType = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(voucherTypeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        voucherTypeServiceStub.find.resolves(voucherTypeSample);
        voucherTypeServiceStub.retrieve.resolves([voucherTypeSample]);

        // WHEN
        route = {
          params: {
            voucherTypeId: '' + voucherTypeSample.id,
          },
        };
        const wrapper = shallowMount(VoucherTypeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.voucherType).toMatchObject(voucherTypeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        voucherTypeServiceStub.find.resolves(voucherTypeSample);
        const wrapper = shallowMount(VoucherTypeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
