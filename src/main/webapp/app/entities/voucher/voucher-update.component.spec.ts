/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import VoucherUpdate from './voucher-update.vue';
import VoucherService from './voucher.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

import VoucherTypeService from '@/entities/voucher-type/voucher-type.service';
import VoucherCompanyService from '@/entities/voucher-company/voucher-company.service';

type VoucherUpdateComponentType = InstanceType<typeof VoucherUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const voucherSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<VoucherUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Voucher Management Update Component', () => {
    let comp: VoucherUpdateComponentType;
    let voucherServiceStub: SinonStubbedInstance<VoucherService>;

    beforeEach(() => {
      route = {};
      voucherServiceStub = sinon.createStubInstance<VoucherService>(VoucherService);
      voucherServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          voucherService: () => voucherServiceStub,
          voucherTypeService: () =>
            sinon.createStubInstance<VoucherTypeService>(VoucherTypeService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
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

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(VoucherUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(VoucherUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.voucher = voucherSample;
        voucherServiceStub.update.resolves(voucherSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(voucherServiceStub.update.calledWith(voucherSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        voucherServiceStub.create.resolves(entity);
        const wrapper = shallowMount(VoucherUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.voucher = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(voucherServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        voucherServiceStub.find.resolves(voucherSample);
        voucherServiceStub.retrieve.resolves([voucherSample]);

        // WHEN
        route = {
          params: {
            voucherId: '' + voucherSample.id,
          },
        };
        const wrapper = shallowMount(VoucherUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.voucher).toMatchObject(voucherSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        voucherServiceStub.find.resolves(voucherSample);
        const wrapper = shallowMount(VoucherUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
