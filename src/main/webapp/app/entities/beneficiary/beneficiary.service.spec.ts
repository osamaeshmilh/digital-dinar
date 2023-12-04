/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import BeneficiaryService from './beneficiary.service';
import { Beneficiary } from '@/shared/model/beneficiary.model';

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
  describe('Beneficiary Service', () => {
    let service: BeneficiaryService;
    let elemDefault;

    beforeEach(() => {
      service = new BeneficiaryService();
      elemDefault = new Beneficiary(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'DRIVING_LICENCE',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
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

      it('should create a Beneficiary', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Beneficiary', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Beneficiary', async () => {
        const returnedFromService = Object.assign(
          {
            arabicFirstName: 'BBBBBB',
            arabicLastName: 'BBBBBB',
            englishFirstName: 'BBBBBB',
            englishLastName: 'BBBBBB',
            bankAccountName: 'BBBBBB',
            bankAccountNumber: 'BBBBBB',
            idType: 'BBBBBB',
            idNo: 'BBBBBB',
            mobileNo: 'BBBBBB',
            email: 'BBBBBB',
            notes: 'BBBBBB',
            isVerified: true,
            hasTransferred: true,
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Beneficiary', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Beneficiary', async () => {
        const patchObject = Object.assign(
          {
            arabicFirstName: 'BBBBBB',
            englishFirstName: 'BBBBBB',
            englishLastName: 'BBBBBB',
            bankAccountNumber: 'BBBBBB',
            idType: 'BBBBBB',
            idNo: 'BBBBBB',
            mobileNo: 'BBBBBB',
            isVerified: true,
          },
          new Beneficiary(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Beneficiary', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Beneficiary', async () => {
        const returnedFromService = Object.assign(
          {
            arabicFirstName: 'BBBBBB',
            arabicLastName: 'BBBBBB',
            englishFirstName: 'BBBBBB',
            englishLastName: 'BBBBBB',
            bankAccountName: 'BBBBBB',
            bankAccountNumber: 'BBBBBB',
            idType: 'BBBBBB',
            idNo: 'BBBBBB',
            mobileNo: 'BBBBBB',
            email: 'BBBBBB',
            notes: 'BBBBBB',
            isVerified: true,
            hasTransferred: true,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Beneficiary', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Beneficiary', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Beneficiary', async () => {
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
