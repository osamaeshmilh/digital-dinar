/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import WalletProfile from './wallet-profile.vue';
import WalletProfileService from './wallet-profile.service';
import AlertService from '@/shared/alert/alert.service';

type WalletProfileComponentType = InstanceType<typeof WalletProfile>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('WalletProfile Management Component', () => {
    let walletProfileServiceStub: SinonStubbedInstance<WalletProfileService>;
    let mountOptions: MountingOptions<WalletProfileComponentType>['global'];

    beforeEach(() => {
      walletProfileServiceStub = sinon.createStubInstance<WalletProfileService>(WalletProfileService);
      walletProfileServiceStub.retrieve.resolves({ headers: {} });

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
          walletProfileService: () => walletProfileServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        walletProfileServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(WalletProfile, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(walletProfileServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.walletProfiles[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(WalletProfile, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(walletProfileServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: WalletProfileComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(WalletProfile, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        walletProfileServiceStub.retrieve.reset();
        walletProfileServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        walletProfileServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(walletProfileServiceStub.retrieve.called).toBeTruthy();
        expect(comp.walletProfiles[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(walletProfileServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        walletProfileServiceStub.retrieve.reset();
        walletProfileServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(walletProfileServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.walletProfiles[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(walletProfileServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        walletProfileServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeWalletProfile();
        await comp.$nextTick(); // clear components

        // THEN
        expect(walletProfileServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(walletProfileServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
