/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import WalletTransactionUpdate from './wallet-transaction-update.vue';
import WalletTransactionService from './wallet-transaction.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

import TransactionService from '@/entities/transaction/transaction.service';

type WalletTransactionUpdateComponentType = InstanceType<typeof WalletTransactionUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const walletTransactionSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<WalletTransactionUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('WalletTransaction Management Update Component', () => {
    let comp: WalletTransactionUpdateComponentType;
    let walletTransactionServiceStub: SinonStubbedInstance<WalletTransactionService>;

    beforeEach(() => {
      route = {};
      walletTransactionServiceStub = sinon.createStubInstance<WalletTransactionService>(WalletTransactionService);
      walletTransactionServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          walletTransactionService: () => walletTransactionServiceStub,
          transactionService: () =>
            sinon.createStubInstance<TransactionService>(TransactionService, {
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
        const wrapper = shallowMount(WalletTransactionUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(WalletTransactionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.walletTransaction = walletTransactionSample;
        walletTransactionServiceStub.update.resolves(walletTransactionSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(walletTransactionServiceStub.update.calledWith(walletTransactionSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        walletTransactionServiceStub.create.resolves(entity);
        const wrapper = shallowMount(WalletTransactionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.walletTransaction = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(walletTransactionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        walletTransactionServiceStub.find.resolves(walletTransactionSample);
        walletTransactionServiceStub.retrieve.resolves([walletTransactionSample]);

        // WHEN
        route = {
          params: {
            walletTransactionId: '' + walletTransactionSample.id,
          },
        };
        const wrapper = shallowMount(WalletTransactionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.walletTransaction).toMatchObject(walletTransactionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        walletTransactionServiceStub.find.resolves(walletTransactionSample);
        const wrapper = shallowMount(WalletTransactionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
