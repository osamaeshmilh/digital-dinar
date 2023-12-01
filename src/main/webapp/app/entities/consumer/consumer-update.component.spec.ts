/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ConsumerUpdate from './consumer-update.vue';
import ConsumerService from './consumer.service';
import AlertService from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';
import WalletProfileService from '@/entities/wallet-profile/wallet-profile.service';

type ConsumerUpdateComponentType = InstanceType<typeof ConsumerUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const consumerSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ConsumerUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Consumer Management Update Component', () => {
    let comp: ConsumerUpdateComponentType;
    let consumerServiceStub: SinonStubbedInstance<ConsumerService>;

    beforeEach(() => {
      route = {};
      consumerServiceStub = sinon.createStubInstance<ConsumerService>(ConsumerService);
      consumerServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          consumerService: () => consumerServiceStub,

          userService: () =>
            sinon.createStubInstance<UserService>(UserService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          walletProfileService: () =>
            sinon.createStubInstance<WalletProfileService>(WalletProfileService, {
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
        const wrapper = shallowMount(ConsumerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.consumer = consumerSample;
        consumerServiceStub.update.resolves(consumerSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(consumerServiceStub.update.calledWith(consumerSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        consumerServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ConsumerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.consumer = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(consumerServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        consumerServiceStub.find.resolves(consumerSample);
        consumerServiceStub.retrieve.resolves([consumerSample]);

        // WHEN
        route = {
          params: {
            consumerId: '' + consumerSample.id,
          },
        };
        const wrapper = shallowMount(ConsumerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.consumer).toMatchObject(consumerSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        consumerServiceStub.find.resolves(consumerSample);
        const wrapper = shallowMount(ConsumerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
