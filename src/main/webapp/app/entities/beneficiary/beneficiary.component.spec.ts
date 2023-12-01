/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Beneficiary from './beneficiary.vue';
import BeneficiaryService from './beneficiary.service';
import AlertService from '@/shared/alert/alert.service';

type BeneficiaryComponentType = InstanceType<typeof Beneficiary>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Beneficiary Management Component', () => {
    let beneficiaryServiceStub: SinonStubbedInstance<BeneficiaryService>;
    let mountOptions: MountingOptions<BeneficiaryComponentType>['global'];

    beforeEach(() => {
      beneficiaryServiceStub = sinon.createStubInstance<BeneficiaryService>(BeneficiaryService);
      beneficiaryServiceStub.retrieve.resolves({ headers: {} });

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
          beneficiaryService: () => beneficiaryServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        beneficiaryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Beneficiary, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(beneficiaryServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.beneficiaries[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(Beneficiary, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(beneficiaryServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: BeneficiaryComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Beneficiary, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        beneficiaryServiceStub.retrieve.reset();
        beneficiaryServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        beneficiaryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(beneficiaryServiceStub.retrieve.called).toBeTruthy();
        expect(comp.beneficiaries[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(beneficiaryServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        beneficiaryServiceStub.retrieve.reset();
        beneficiaryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(beneficiaryServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.beneficiaries[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(beneficiaryServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        beneficiaryServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeBeneficiary();
        await comp.$nextTick(); // clear components

        // THEN
        expect(beneficiaryServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(beneficiaryServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
