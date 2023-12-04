/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import WalletProfileService from './wallet-profile.service';
import { WalletProfile } from '@/shared/model/wallet-profile.model';

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
  describe('WalletProfile Service', () => {
    let service: WalletProfileService;
    let elemDefault;

    beforeEach(() => {
      service = new WalletProfileService();
      elemDefault = new WalletProfile(
        123,
        'AAAAAAA',
        'AAAAAAA',
        false,
        0,
        false,
        0,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
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

      it('should create a WalletProfile', async () => {
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

      it('should not create a WalletProfile', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a WalletProfile', async () => {
        const returnedFromService = Object.assign(
          {
            nameAr: 'BBBBBB',
            nameEn: 'BBBBBB',
            hasTransferAmountLimitPerTransaction: true,
            transferAmountLimitPerTransaction: 1,
            hasDailyTransferAmountLimit: true,
            dailyTransferAmountLimit: 1,
            canTransferToConsumerWallet: true,
            canTransferToMerchantWallet: true,
            canTransferToConsumer: true,
            canTransferToBank: true,
            canBuyVouchers: true,
            transferToConsumerWalletFees: true,
            transferToMerchantWalletFees: true,
            transferToConsumerFees: true,
            transferToBankFees: true,
            buyVouchersFees: true,
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a WalletProfile', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a WalletProfile', async () => {
        const patchObject = Object.assign(
          {
            nameEn: 'BBBBBB',
            transferAmountLimitPerTransaction: 1,
            dailyTransferAmountLimit: 1,
            canTransferToConsumerWallet: true,
            canTransferToConsumer: true,
            canTransferToBank: true,
            canBuyVouchers: true,
            transferToMerchantWalletFees: true,
            transferToBankFees: true,
            buyVouchersFees: true,
          },
          new WalletProfile(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a WalletProfile', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of WalletProfile', async () => {
        const returnedFromService = Object.assign(
          {
            nameAr: 'BBBBBB',
            nameEn: 'BBBBBB',
            hasTransferAmountLimitPerTransaction: true,
            transferAmountLimitPerTransaction: 1,
            hasDailyTransferAmountLimit: true,
            dailyTransferAmountLimit: 1,
            canTransferToConsumerWallet: true,
            canTransferToMerchantWallet: true,
            canTransferToConsumer: true,
            canTransferToBank: true,
            canBuyVouchers: true,
            transferToConsumerWalletFees: true,
            transferToMerchantWalletFees: true,
            transferToConsumerFees: true,
            transferToBankFees: true,
            buyVouchersFees: true,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of WalletProfile', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a WalletProfile', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a WalletProfile', async () => {
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
