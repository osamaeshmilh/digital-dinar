/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import WalletTransaction from './wallet-transaction.vue';
import WalletTransactionService from './wallet-transaction.service';
import AlertService from '@/shared/alert/alert.service';

type WalletTransactionComponentType = InstanceType<typeof WalletTransaction>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('WalletTransaction Management Component', () => {
    let walletTransactionServiceStub: SinonStubbedInstance<WalletTransactionService>;
    let mountOptions: MountingOptions<WalletTransactionComponentType>['global'];

    beforeEach(() => {
      walletTransactionServiceStub = sinon.createStubInstance<WalletTransactionService>(WalletTransactionService);
      walletTransactionServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          jhiItemCount: true,
          bPagination: true,
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'jhi-sort-indicator': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          walletTransactionService: () => walletTransactionServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        walletTransactionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(WalletTransaction, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(walletTransactionServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.walletTransactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(WalletTransaction, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(walletTransactionServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: WalletTransactionComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(WalletTransaction, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        walletTransactionServiceStub.retrieve.reset();
        walletTransactionServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        walletTransactionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(walletTransactionServiceStub.retrieve.called).toBeTruthy();
        expect(comp.walletTransactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(walletTransactionServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        walletTransactionServiceStub.retrieve.reset();
        walletTransactionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(walletTransactionServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.walletTransactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(walletTransactionServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        walletTransactionServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeWalletTransaction();
        await comp.$nextTick(); // clear components

        // THEN
        expect(walletTransactionServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(walletTransactionServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
