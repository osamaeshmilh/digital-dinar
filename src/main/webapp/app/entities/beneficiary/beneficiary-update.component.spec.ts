/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import BeneficiaryUpdate from './beneficiary-update.vue';
import BeneficiaryService from './beneficiary.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type BeneficiaryUpdateComponentType = InstanceType<typeof BeneficiaryUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const beneficiarySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<BeneficiaryUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Beneficiary Management Update Component', () => {
    let comp: BeneficiaryUpdateComponentType;
    let beneficiaryServiceStub: SinonStubbedInstance<BeneficiaryService>;

    beforeEach(() => {
      route = {};
      beneficiaryServiceStub = sinon.createStubInstance<BeneficiaryService>(BeneficiaryService);
      beneficiaryServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          beneficiaryService: () => beneficiaryServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(BeneficiaryUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(BeneficiaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.beneficiary = beneficiarySample;
        beneficiaryServiceStub.update.resolves(beneficiarySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(beneficiaryServiceStub.update.calledWith(beneficiarySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        beneficiaryServiceStub.create.resolves(entity);
        const wrapper = shallowMount(BeneficiaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.beneficiary = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(beneficiaryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        beneficiaryServiceStub.find.resolves(beneficiarySample);
        beneficiaryServiceStub.retrieve.resolves([beneficiarySample]);

        // WHEN
        route = {
          params: {
            beneficiaryId: '' + beneficiarySample.id,
          },
        };
        const wrapper = shallowMount(BeneficiaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.beneficiary).toMatchObject(beneficiarySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        beneficiaryServiceStub.find.resolves(beneficiarySample);
        const wrapper = shallowMount(BeneficiaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
