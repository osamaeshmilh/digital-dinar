import { type IBankBranch } from '@/shared/model/bank-branch.model';
import { type IWalletUser } from '@/shared/model/wallet-user.model';

import { type IdType } from '@/shared/model/enumerations/id-type.model';
export interface IBeneficiary {
  id?: number;
  arabicFirstName?: string | null;
  arabicLastName?: string | null;
  englishFirstName?: string | null;
  englishLastName?: string | null;
  bankAccountName?: string | null;
  bankAccountNumber?: string | null;
  idType?: keyof typeof IdType | null;
  idNo?: string | null;
  mobileNo?: string | null;
  email?: string | null;
  notes?: string | null;
  isVerified?: boolean | null;
  hasTransferred?: boolean | null;
  bankBranch?: IBankBranch | null;
  walletUser?: IWalletUser | null;
}

export class Beneficiary implements IBeneficiary {
  constructor(
    public id?: number,
    public arabicFirstName?: string | null,
    public arabicLastName?: string | null,
    public englishFirstName?: string | null,
    public englishLastName?: string | null,
    public bankAccountName?: string | null,
    public bankAccountNumber?: string | null,
    public idType?: keyof typeof IdType | null,
    public idNo?: string | null,
    public mobileNo?: string | null,
    public email?: string | null,
    public notes?: string | null,
    public isVerified?: boolean | null,
    public hasTransferred?: boolean | null,
    public bankBranch?: IBankBranch | null,
    public walletUser?: IWalletUser | null,
  ) {
    this.isVerified = this.isVerified ?? false;
    this.hasTransferred = this.hasTransferred ?? false;
  }
}
