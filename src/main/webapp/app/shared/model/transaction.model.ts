import { type IWalletTransaction } from '@/shared/model/wallet-transaction.model';

import { type PaymentType } from '@/shared/model/enumerations/payment-type.model';
import { type TransactionStatus } from '@/shared/model/enumerations/transaction-status.model';
import { type TransactionType } from '@/shared/model/enumerations/transaction-type.model';
import { type WalletOwnerType } from '@/shared/model/enumerations/wallet-owner-type.model';
export interface ITransaction {
  id?: number;
  transactionReference?: string | null;
  transactionSecret?: string | null;
  transactionOtp?: string | null;
  paymentType?: keyof typeof PaymentType | null;
  transactionStatus?: keyof typeof TransactionStatus | null;
  transactionType?: keyof typeof TransactionType | null;
  vendorReference?: string | null;
  vendorMessage?: string | null;
  amount?: number | null;
  fees?: number | null;
  total?: number | null;
  notes?: string | null;
  senderMobileNo?: string | null;
  senderName?: string | null;
  senderType?: keyof typeof WalletOwnerType | null;
  senderIdNo?: string | null;
  senderId?: number | null;
  receiverName?: string | null;
  receiverMobileNo?: string | null;
  receiverType?: keyof typeof WalletOwnerType | null;
  receiverId?: number | null;
  receiverIdNo?: string | null;
  createdByUserId?: number | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  walletTransactions?: IWalletTransaction[] | null;
}

export class Transaction implements ITransaction {
  constructor(
    public id?: number,
    public transactionReference?: string | null,
    public transactionSecret?: string | null,
    public transactionOtp?: string | null,
    public paymentType?: keyof typeof PaymentType | null,
    public transactionStatus?: keyof typeof TransactionStatus | null,
    public transactionType?: keyof typeof TransactionType | null,
    public vendorReference?: string | null,
    public vendorMessage?: string | null,
    public amount?: number | null,
    public fees?: number | null,
    public total?: number | null,
    public notes?: string | null,
    public senderMobileNo?: string | null,
    public senderName?: string | null,
    public senderType?: keyof typeof WalletOwnerType | null,
    public senderIdNo?: string | null,
    public senderId?: number | null,
    public receiverName?: string | null,
    public receiverMobileNo?: string | null,
    public receiverType?: keyof typeof WalletOwnerType | null,
    public receiverId?: number | null,
    public receiverIdNo?: string | null,
    public createdByUserId?: number | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public walletTransactions?: IWalletTransaction[] | null,
  ) {}
}
