/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import WalletUserService from './wallet-user.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { WalletUser } from '@/shared/model/wallet-user.model';

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
  describe('WalletUser Service', () => {
    let service: WalletUserService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new WalletUserService();
      currentDate = new Date();
      elemDefault = new WalletUser(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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
        'FEMALE',
        'DRIVING_LICENCE',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        false,
        'CONSUMER',
        'ACTIVE',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a WalletUser', async () => {
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

      it('should not create a WalletUser', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a WalletUser', async () => {
        const returnedFromService = Object.assign(
          {
            arabicFirstName: 'BBBBBB',
            arabicMiddleName: 'BBBBBB',
            arabicLastName: 'BBBBBB',
            englishFirstName: 'BBBBBB',
            englishMiddleName: 'BBBBBB',
            englishLastName: 'BBBBBB',
            commercialRegistryNo: 'BBBBBB',
            commercialLicenceNo: 'BBBBBB',
            commercialName: 'BBBBBB',
            birthDate: dayjs(currentDate).format(DATE_FORMAT),
            birthPlace: 'BBBBBB',
            nationalNo: 'BBBBBB',
            registerNo: 'BBBBBB',
            gender: 'BBBBBB',
            idType: 'BBBBBB',
            idNo: 'BBBBBB',
            mobileNo: 'BBBBBB',
            verifiedByEmailOtp: true,
            verifiedByMobileOtp: true,
            email: 'BBBBBB',
            nidStatus: 'BBBBBB',
            isForeign: true,
            isMobileConnectedWithNid: true,
            isKYCVerified: true,
            walletType: 'BBBBBB',
            walletStatus: 'BBBBBB',
            imageFile: 'BBBBBB',
            imageUrlFile: 'BBBBBB',
            postCode: 'BBBBBB',
            address: 'BBBBBB',
            lat: 1,
            lng: 1,
            notes: 'BBBBBB',
            bankAccountName: 'BBBBBB',
            bankAccountNumber: 'BBBBBB',
            bankAccountIBAN: 'BBBBBB',
            bankAccountSWIFT: 'BBBBBB',
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

      it('should not update a WalletUser', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a WalletUser', async () => {
        const patchObject = Object.assign(
          {
            arabicFirstName: 'BBBBBB',
            arabicMiddleName: 'BBBBBB',
            englishLastName: 'BBBBBB',
            commercialRegistryNo: 'BBBBBB',
            commercialLicenceNo: 'BBBBBB',
            birthPlace: 'BBBBBB',
            nationalNo: 'BBBBBB',
            idType: 'BBBBBB',
            idNo: 'BBBBBB',
            verifiedByEmailOtp: true,
            email: 'BBBBBB',
            nidStatus: 'BBBBBB',
            isForeign: true,
            isKYCVerified: true,
            walletStatus: 'BBBBBB',
            imageUrlFile: 'BBBBBB',
            postCode: 'BBBBBB',
            address: 'BBBBBB',
            bankAccountName: 'BBBBBB',
            bankAccountNumber: 'BBBBBB',
            bankAccountSWIFT: 'BBBBBB',
          },
          new WalletUser(),
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

      it('should not partial update a WalletUser', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of WalletUser', async () => {
        const returnedFromService = Object.assign(
          {
            arabicFirstName: 'BBBBBB',
            arabicMiddleName: 'BBBBBB',
            arabicLastName: 'BBBBBB',
            englishFirstName: 'BBBBBB',
            englishMiddleName: 'BBBBBB',
            englishLastName: 'BBBBBB',
            commercialRegistryNo: 'BBBBBB',
            commercialLicenceNo: 'BBBBBB',
            commercialName: 'BBBBBB',
            birthDate: dayjs(currentDate).format(DATE_FORMAT),
            birthPlace: 'BBBBBB',
            nationalNo: 'BBBBBB',
            registerNo: 'BBBBBB',
            gender: 'BBBBBB',
            idType: 'BBBBBB',
            idNo: 'BBBBBB',
            mobileNo: 'BBBBBB',
            verifiedByEmailOtp: true,
            verifiedByMobileOtp: true,
            email: 'BBBBBB',
            nidStatus: 'BBBBBB',
            isForeign: true,
            isMobileConnectedWithNid: true,
            isKYCVerified: true,
            walletType: 'BBBBBB',
            walletStatus: 'BBBBBB',
            imageFile: 'BBBBBB',
            imageUrlFile: 'BBBBBB',
            postCode: 'BBBBBB',
            address: 'BBBBBB',
            lat: 1,
            lng: 1,
            notes: 'BBBBBB',
            bankAccountName: 'BBBBBB',
            bankAccountNumber: 'BBBBBB',
            bankAccountIBAN: 'BBBBBB',
            bankAccountSWIFT: 'BBBBBB',
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

      it('should not return a list of WalletUser', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a WalletUser', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a WalletUser', async () => {
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
