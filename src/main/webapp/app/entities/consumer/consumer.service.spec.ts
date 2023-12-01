/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ConsumerService from './consumer.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Consumer } from '@/shared/model/consumer.model';

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
  describe('Consumer Service', () => {
    let service: ConsumerService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ConsumerService();
      currentDate = new Date();
      elemDefault = new Consumer(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'MALE',
        'PASSPORT',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        'ACTIVE',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            birthDate: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Consumer', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            birthDate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            birthDate: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Consumer', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Consumer', async () => {
        const returnedFromService = Object.assign(
          {
            arabicFirstName: 'BBBBBB',
            arabicMiddleName: 'BBBBBB',
            arabicLastName: 'BBBBBB',
            englishFirstName: 'BBBBBB',
            englishMiddleName: 'BBBBBB',
            englishLastName: 'BBBBBB',
            birthDate: dayjs(currentDate).format(DATE_FORMAT),
            birthPlace: 'BBBBBB',
            nationalNo: 'BBBBBB',
            registerNo: 'BBBBBB',
            gender: 'BBBBBB',
            idType: 'BBBBBB',
            idNo: 'BBBBBB',
            mobileNo: 'BBBBBB',
            verifiedByEmailOtp: true,
            verifiedByMobileNoOtp: true,
            email: 'BBBBBB',
            nidStatus: 'BBBBBB',
            isForeign: true,
            isMobileConnectedWithNid: true,
            walletOwnerStatus: 'BBBBBB',
            imageFile: 'BBBBBB',
            imageUrlFile: 'BBBBBB',
            postCode: 'BBBBBB',
            address: 'BBBBBB',
            lat: 1,
            lng: 1,
            notes: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            birthDate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Consumer', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Consumer', async () => {
        const patchObject = Object.assign(
          {
            arabicFirstName: 'BBBBBB',
            englishLastName: 'BBBBBB',
            birthPlace: 'BBBBBB',
            registerNo: 'BBBBBB',
            gender: 'BBBBBB',
            verifiedByEmailOtp: true,
            verifiedByMobileNoOtp: true,
            walletOwnerStatus: 'BBBBBB',
            imageFile: 'BBBBBB',
            lat: 1,
            notes: 'BBBBBB',
          },
          new Consumer(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            birthDate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Consumer', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Consumer', async () => {
        const returnedFromService = Object.assign(
          {
            arabicFirstName: 'BBBBBB',
            arabicMiddleName: 'BBBBBB',
            arabicLastName: 'BBBBBB',
            englishFirstName: 'BBBBBB',
            englishMiddleName: 'BBBBBB',
            englishLastName: 'BBBBBB',
            birthDate: dayjs(currentDate).format(DATE_FORMAT),
            birthPlace: 'BBBBBB',
            nationalNo: 'BBBBBB',
            registerNo: 'BBBBBB',
            gender: 'BBBBBB',
            idType: 'BBBBBB',
            idNo: 'BBBBBB',
            mobileNo: 'BBBBBB',
            verifiedByEmailOtp: true,
            verifiedByMobileNoOtp: true,
            email: 'BBBBBB',
            nidStatus: 'BBBBBB',
            isForeign: true,
            isMobileConnectedWithNid: true,
            walletOwnerStatus: 'BBBBBB',
            imageFile: 'BBBBBB',
            imageUrlFile: 'BBBBBB',
            postCode: 'BBBBBB',
            address: 'BBBBBB',
            lat: 1,
            lng: 1,
            notes: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            birthDate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Consumer', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Consumer', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Consumer', async () => {
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
