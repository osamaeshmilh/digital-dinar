/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import WalletTransactionService from './wallet-transaction.service';
import { WalletTransaction } from '@/shared/model/wallet-transaction.model';

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
  describe('WalletTransaction Service', () => {
    let service: WalletTransactionService;
    let elemDefault;

    beforeEach(() => {
      service = new WalletTransactionService();
      elemDefault = new WalletTransaction(123, 'AAAAAAA', 0, 'DEPOSIT', 'AAAAAAA', 0, 0, 'ADFALI', 'AAAAAAA', 'AAAAAAA', 0, 'CONSUMER');
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

      it('should create a WalletTransaction', async () => {
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

      it('should not create a WalletTransaction', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a WalletTransaction', async () => {
        const returnedFromService = Object.assign(
          {
            transactionNo: 'BBBBBB',
            amount: 1,
            walletAction: 'BBBBBB',
            userPreviousTransactionNo: 'BBBBBB',
            totalBeforeAction: 1,
            totalAfterAction: 1,
            paymentType: 'BBBBBB',
            paymentReference: 'BBBBBB',
            notes: 'BBBBBB',
            ownerId: 1,
            walletOwnerType: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a WalletTransaction', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a WalletTransaction', async () => {
        const patchObject = Object.assign(
          {
            amount: 1,
            paymentType: 'BBBBBB',
            paymentReference: 'BBBBBB',
            ownerId: 1,
            walletOwnerType: 'BBBBBB',
          },
          new WalletTransaction(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a WalletTransaction', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of WalletTransaction', async () => {
        const returnedFromService = Object.assign(
          {
            transactionNo: 'BBBBBB',
            amount: 1,
            walletAction: 'BBBBBB',
            userPreviousTransactionNo: 'BBBBBB',
            totalBeforeAction: 1,
            totalAfterAction: 1,
            paymentType: 'BBBBBB',
            paymentReference: 'BBBBBB',
            notes: 'BBBBBB',
            ownerId: 1,
            walletOwnerType: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of WalletTransaction', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a WalletTransaction', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a WalletTransaction', async () => {
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
