/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import TransactionService from './transaction.service';
import { Transaction } from '@/shared/model/transaction.model';

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
  describe('Transaction Service', () => {
    let service: TransactionService;
    let elemDefault;

    beforeEach(() => {
      service = new TransactionService();
      elemDefault = new Transaction(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'ADFALI',
        'CANCELLED',
        'CONSUMER_BUY_VOUCHERS',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a Transaction', async () => {
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

      it('should not create a Transaction', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Transaction', async () => {
        const returnedFromService = Object.assign(
          {
            transactionReference: 'BBBBBB',
            transactionSecret: 'BBBBBB',
            transactionOtp: 'BBBBBB',
            paymentType: 'BBBBBB',
            transactionStatus: 'BBBBBB',
            transactionType: 'BBBBBB',
            vendorReference: 'BBBBBB',
            vendorMessage: 'BBBBBB',
            amount: 1,
            fees: 1,
            total: 1,
            notes: 'BBBBBB',
            senderMobileNo: 'BBBBBB',
            senderName: 'BBBBBB',
            senderIdNo: 'BBBBBB',
            receiverName: 'BBBBBB',
            receiverMobileNo: 'BBBBBB',
            receiverIdNo: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Transaction', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Transaction', async () => {
        const patchObject = Object.assign(
          {
            transactionSecret: 'BBBBBB',
            transactionOtp: 'BBBBBB',
            vendorMessage: 'BBBBBB',
            notes: 'BBBBBB',
            receiverMobileNo: 'BBBBBB',
          },
          new Transaction(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Transaction', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Transaction', async () => {
        const returnedFromService = Object.assign(
          {
            transactionReference: 'BBBBBB',
            transactionSecret: 'BBBBBB',
            transactionOtp: 'BBBBBB',
            paymentType: 'BBBBBB',
            transactionStatus: 'BBBBBB',
            transactionType: 'BBBBBB',
            vendorReference: 'BBBBBB',
            vendorMessage: 'BBBBBB',
            amount: 1,
            fees: 1,
            total: 1,
            notes: 'BBBBBB',
            senderMobileNo: 'BBBBBB',
            senderName: 'BBBBBB',
            senderIdNo: 'BBBBBB',
            receiverName: 'BBBBBB',
            receiverMobileNo: 'BBBBBB',
            receiverIdNo: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Transaction', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Transaction', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Transaction', async () => {
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
