/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import TransactionService from './transaction.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
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
    let currentDate: Date;

    beforeEach(() => {
      service = new TransactionService();
      currentDate = new Date();
      elemDefault = new Transaction(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'ADFALI',
        'PENDING_PAYMENT',
        'CONSUMER_WALLET_TO_CONSUMER_WALLET',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'CONSUMER',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'CONSUMER',
        0,
        'AAAAAAA',
        0,
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

      it('should create a Transaction', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
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
            senderType: 'BBBBBB',
            senderIdNo: 'BBBBBB',
            senderId: 1,
            receiverName: 'BBBBBB',
            receiverMobileNo: 'BBBBBB',
            receiverType: 'BBBBBB',
            receiverId: 1,
            receiverIdNo: 'BBBBBB',
            createdByUserId: 1,
            createdBy: 'BBBBBB',
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB',
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
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
            transactionReference: 'BBBBBB',
            transactionSecret: 'BBBBBB',
            transactionType: 'BBBBBB',
            fees: 1,
            senderMobileNo: 'BBBBBB',
            senderId: 1,
            receiverName: 'BBBBBB',
            receiverMobileNo: 'BBBBBB',
            receiverType: 'BBBBBB',
            receiverId: 1,
            receiverIdNo: 'BBBBBB',
            createdByUserId: 1,
          },
          new Transaction(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
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
            senderType: 'BBBBBB',
            senderIdNo: 'BBBBBB',
            senderId: 1,
            receiverName: 'BBBBBB',
            receiverMobileNo: 'BBBBBB',
            receiverType: 'BBBBBB',
            receiverId: 1,
            receiverIdNo: 'BBBBBB',
            createdByUserId: 1,
            createdBy: 'BBBBBB',
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB',
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
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
