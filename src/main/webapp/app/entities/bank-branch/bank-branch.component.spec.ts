/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import BankBranch from './bank-branch.vue';
import BankBranchService from './bank-branch.service';
import AlertService from '@/shared/alert/alert.service';

type BankBranchComponentType = InstanceType<typeof BankBranch>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('BankBranch Management Component', () => {
    let bankBranchServiceStub: SinonStubbedInstance<BankBranchService>;
    let mountOptions: MountingOptions<BankBranchComponentType>['global'];

    beforeEach(() => {
      bankBranchServiceStub = sinon.createStubInstance<BankBranchService>(BankBranchService);
      bankBranchServiceStub.retrieve.resolves({ headers: {} });

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
          bankBranchService: () => bankBranchServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        bankBranchServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(BankBranch, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(bankBranchServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.bankBranches[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(BankBranch, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(bankBranchServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: BankBranchComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(BankBranch, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        bankBranchServiceStub.retrieve.reset();
        bankBranchServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        bankBranchServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(bankBranchServiceStub.retrieve.called).toBeTruthy();
        expect(comp.bankBranches[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(bankBranchServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        bankBranchServiceStub.retrieve.reset();
        bankBranchServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(bankBranchServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.bankBranches[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(bankBranchServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        bankBranchServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeBankBranch();
        await comp.$nextTick(); // clear components

        // THEN
        expect(bankBranchServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(bankBranchServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
