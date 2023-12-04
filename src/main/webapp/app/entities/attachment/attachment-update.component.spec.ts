/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import AttachmentUpdate from './attachment-update.vue';
import AttachmentService from './attachment.service';
import AlertService from '@/shared/alert/alert.service';

type AttachmentUpdateComponentType = InstanceType<typeof AttachmentUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const attachmentSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<AttachmentUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Attachment Management Update Component', () => {
    let comp: AttachmentUpdateComponentType;
    let attachmentServiceStub: SinonStubbedInstance<AttachmentService>;

    beforeEach(() => {
      route = {};
      attachmentServiceStub = sinon.createStubInstance<AttachmentService>(AttachmentService);
      attachmentServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          attachmentService: () => attachmentServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(AttachmentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.attachment = attachmentSample;
        attachmentServiceStub.update.resolves(attachmentSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(attachmentServiceStub.update.calledWith(attachmentSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        attachmentServiceStub.create.resolves(entity);
        const wrapper = shallowMount(AttachmentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.attachment = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(attachmentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        attachmentServiceStub.find.resolves(attachmentSample);
        attachmentServiceStub.retrieve.resolves([attachmentSample]);

        // WHEN
        route = {
          params: {
            attachmentId: '' + attachmentSample.id,
          },
        };
        const wrapper = shallowMount(AttachmentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.attachment).toMatchObject(attachmentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        attachmentServiceStub.find.resolves(attachmentSample);
        const wrapper = shallowMount(AttachmentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
