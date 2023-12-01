import { type ITransaction } from '@/shared/model/transaction.model';

import { type WalletAction } from '@/shared/model/enumerations/wallet-action.model';
import { type PaymentType } from '@/shared/model/enumerations/payment-type.model';
import { type WalletOwnerType } from '@/shared/model/enumerations/wallet-owner-type.model';
export interface IWalletTransaction {
  id?: number;
  transactionNo?: string | null;
  amount?: number | null;
  walletAction?: keyof typeof WalletAction | null;
  userPreviousTransactionNo?: string | null;
  totalBeforeAction?: number | null;
  totalAfterAction?: number | null;
  paymentType?: keyof typeof PaymentType | null;
  paymentReference?: string | null;
  notes?: string | null;
  ownerId?: number | null;
  walletOwnerType?: keyof typeof WalletOwnerType | null;
  transaction?: ITransaction | null;
}

export class WalletTransaction implements IWalletTransaction {
  constructor(
    public id?: number,
    public transactionNo?: string | null,
    public amount?: number | null,
    public walletAction?: keyof typeof WalletAction | null,
    public userPreviousTransactionNo?: string | null,
    public totalBeforeAction?: number | null,
    public totalAfterAction?: number | null,
    public paymentType?: keyof typeof PaymentType | null,
    public paymentReference?: string | null,
    public notes?: string | null,
    public ownerId?: number | null,
    public walletOwnerType?: keyof typeof WalletOwnerType | null,
    public transaction?: ITransaction | null,
  ) {}
}
