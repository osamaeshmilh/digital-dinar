/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import BankBranchUpdate from './bank-branch-update.vue';
import BankBranchService from './bank-branch.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

import BankService from '@/entities/bank/bank.service';

type BankBranchUpdateComponentType = InstanceType<typeof BankBranchUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const bankBranchSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<BankBranchUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('BankBranch Management Update Component', () => {
    let comp: BankBranchUpdateComponentType;
    let bankBranchServiceStub: SinonStubbedInstance<BankBranchService>;

    beforeEach(() => {
      route = {};
      bankBranchServiceStub = sinon.createStubInstance<BankBranchService>(BankBranchService);
      bankBranchServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          bankBranchService: () => bankBranchServiceStub,
          bankService: () =>
            sinon.createStubInstance<BankService>(BankService, {
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
        const wrapper = shallowMount(BankBranchUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(BankBranchUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.bankBranch = bankBranchSample;
        bankBranchServiceStub.update.resolves(bankBranchSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bankBranchServiceStub.update.calledWith(bankBranchSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        bankBranchServiceStub.create.resolves(entity);
        const wrapper = shallowMount(BankBranchUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.bankBranch = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bankBranchServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        bankBranchServiceStub.find.resolves(bankBranchSample);
        bankBranchServiceStub.retrieve.resolves([bankBranchSample]);

        // WHEN
        route = {
          params: {
            bankBranchId: '' + bankBranchSample.id,
          },
        };
        const wrapper = shallowMount(BankBranchUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.bankBranch).toMatchObject(bankBranchSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        bankBranchServiceStub.find.resolves(bankBranchSample);
        const wrapper = shallowMount(BankBranchUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
