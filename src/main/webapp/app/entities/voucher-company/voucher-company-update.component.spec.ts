/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import VoucherCompanyUpdate from './voucher-company-update.vue';
import VoucherCompanyService from './voucher-company.service';
import AlertService from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';

type VoucherCompanyUpdateComponentType = InstanceType<typeof VoucherCompanyUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const voucherCompanySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<VoucherCompanyUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('VoucherCompany Management Update Component', () => {
    let comp: VoucherCompanyUpdateComponentType;
    let voucherCompanyServiceStub: SinonStubbedInstance<VoucherCompanyService>;

    beforeEach(() => {
      route = {};
      voucherCompanyServiceStub = sinon.createStubInstance<VoucherCompanyService>(VoucherCompanyService);
      voucherCompanyServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          voucherCompanyService: () => voucherCompanyServiceStub,

          userService: () =>
            sinon.createStubInstance<UserService>(UserService, {
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
        const wrapper = shallowMount(VoucherCompanyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.voucherCompany = voucherCompanySample;
        voucherCompanyServiceStub.update.resolves(voucherCompanySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(voucherCompanyServiceStub.update.calledWith(voucherCompanySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        voucherCompanyServiceStub.create.resolves(entity);
        const wrapper = shallowMount(VoucherCompanyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.voucherCompany = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(voucherCompanyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        voucherCompanyServiceStub.find.resolves(voucherCompanySample);
        voucherCompanyServiceStub.retrieve.resolves([voucherCompanySample]);

        // WHEN
        route = {
          params: {
            voucherCompanyId: '' + voucherCompanySample.id,
          },
        };
        const wrapper = shallowMount(VoucherCompanyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.voucherCompany).toMatchObject(voucherCompanySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        voucherCompanyServiceStub.find.resolves(voucherCompanySample);
        const wrapper = shallowMount(VoucherCompanyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
