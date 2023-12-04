import { type IWalletUser } from '@/shared/model/wallet-user.model';
import { type IWalletTransaction } from '@/shared/model/wallet-transaction.model';

import { type PaymentType } from '@/shared/model/enumerations/payment-type.model';
import { type TransactionStatus } from '@/shared/model/enumerations/transaction-status.model';
import { type TransactionType } from '@/shared/model/enumerations/transaction-type.model';
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
  senderIdNo?: string | null;
  receiverName?: string | null;
  receiverMobileNo?: string | null;
  receiverIdNo?: string | null;
  sender?: IWalletUser | null;
  receiver?: IWalletUser | null;
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
    public senderIdNo?: string | null,
    public receiverName?: string | null,
    public receiverMobileNo?: string | null,
    public receiverIdNo?: string | null,
    public sender?: IWalletUser | null,
    public receiver?: IWalletUser | null,
    public walletTransactions?: IWalletTransaction[] | null,
  ) {}
}
