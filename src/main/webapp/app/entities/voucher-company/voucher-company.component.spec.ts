/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import VoucherCompany from './voucher-company.vue';
import VoucherCompanyService from './voucher-company.service';
import AlertService from '@/shared/alert/alert.service';

type VoucherCompanyComponentType = InstanceType<typeof VoucherCompany>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('VoucherCompany Management Component', () => {
    let voucherCompanyServiceStub: SinonStubbedInstance<VoucherCompanyService>;
    let mountOptions: MountingOptions<VoucherCompanyComponentType>['global'];

    beforeEach(() => {
      voucherCompanyServiceStub = sinon.createStubInstance<VoucherCompanyService>(VoucherCompanyService);
      voucherCompanyServiceStub.retrieve.resolves({ headers: {} });

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
          voucherCompanyService: () => voucherCompanyServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        voucherCompanyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(VoucherCompany, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(voucherCompanyServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.voucherCompanies[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(VoucherCompany, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(voucherCompanyServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: VoucherCompanyComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(VoucherCompany, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        voucherCompanyServiceStub.retrieve.reset();
        voucherCompanyServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        voucherCompanyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(voucherCompanyServiceStub.retrieve.called).toBeTruthy();
        expect(comp.voucherCompanies[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(voucherCompanyServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        voucherCompanyServiceStub.retrieve.reset();
        voucherCompanyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(voucherCompanyServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.voucherCompanies[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(voucherCompanyServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        voucherCompanyServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeVoucherCompany();
        await comp.$nextTick(); // clear components

        // THEN
        expect(voucherCompanyServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(voucherCompanyServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
