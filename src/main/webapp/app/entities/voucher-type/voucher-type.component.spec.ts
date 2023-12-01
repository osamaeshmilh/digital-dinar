/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import VoucherType from './voucher-type.vue';
import VoucherTypeService from './voucher-type.service';
import AlertService from '@/shared/alert/alert.service';

type VoucherTypeComponentType = InstanceType<typeof VoucherType>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('VoucherType Management Component', () => {
    let voucherTypeServiceStub: SinonStubbedInstance<VoucherTypeService>;
    let mountOptions: MountingOptions<VoucherTypeComponentType>['global'];

    beforeEach(() => {
      voucherTypeServiceStub = sinon.createStubInstance<VoucherTypeService>(VoucherTypeService);
      voucherTypeServiceStub.retrieve.resolves({ headers: {} });

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
          voucherTypeService: () => voucherTypeServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        voucherTypeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(VoucherType, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(voucherTypeServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.voucherTypes[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(VoucherType, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(voucherTypeServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: VoucherTypeComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(VoucherType, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        voucherTypeServiceStub.retrieve.reset();
        voucherTypeServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        voucherTypeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(voucherTypeServiceStub.retrieve.called).toBeTruthy();
        expect(comp.voucherTypes[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(voucherTypeServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        voucherTypeServiceStub.retrieve.reset();
        voucherTypeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(voucherTypeServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.voucherTypes[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(voucherTypeServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        voucherTypeServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeVoucherType();
        await comp.$nextTick(); // clear components

        // THEN
        expect(voucherTypeServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(voucherTypeServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
