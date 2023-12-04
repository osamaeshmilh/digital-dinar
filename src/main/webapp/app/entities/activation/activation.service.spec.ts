/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ActivationService from './activation.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { Activation } from '@/shared/model/activation.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('Activation Service', () => {
    let service: ActivationService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ActivationService();
      currentDate = new Date();
      elemDefault = new Activation(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        false,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            sentOn: dayjs(currentDate).format(DATE_TIME_FORMAT),
            validUntil: dayjs(currentDate).format(DATE_TIME_FORMAT),
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a Activation', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            sentOn: dayjs(currentDate).format(DATE_TIME_FORMAT),
            validUntil: dayjs(currentDate).format(DATE_TIME_FORMAT),
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            sentOn: currentDate,
            validUntil: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Activation', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Activation', async () => {
        const returnedFromService = Object.assign(
          {
            mobileNo: 'BBBBBB',
            email: 'BBBBBB',
            code: 'BBBBBB',
            sentOn: dayjs(currentDate).format(DATE_TIME_FORMAT),
            validUntil: dayjs(currentDate).format(DATE_TIME_FORMAT),
            isUsed: true,
            createdBy: 'BBBBBB',
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB',
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            sentOn: currentDate,
            validUntil: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Activation', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Activation', async () => {
        const patchObject = Object.assign(
          {
            code: 'BBBBBB',
            sentOn: dayjs(currentDate).format(DATE_TIME_FORMAT),
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          new Activation(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            sentOn: currentDate,
            validUntil: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Activation', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Activation', async () => {
        const returnedFromService = Object.assign(
          {
            mobileNo: 'BBBBBB',
            email: 'BBBBBB',
            code: 'BBBBBB',
            sentOn: dayjs(currentDate).format(DATE_TIME_FORMAT),
            validUntil: dayjs(currentDate).format(DATE_TIME_FORMAT),
            isUsed: true,
            createdBy: 'BBBBBB',
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB',
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            sentOn: currentDate,
            validUntil: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Activation', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Activation', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Activation', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
