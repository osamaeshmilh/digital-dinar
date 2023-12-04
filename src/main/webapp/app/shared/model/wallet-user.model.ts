import { type IUser } from '@/shared/model/user.model';
import { type ICategory } from '@/shared/model/category.model';
import { type ICity } from '@/shared/model/city.model';
import { type IWalletProfile } from '@/shared/model/wallet-profile.model';
import { type IBankBranch } from '@/shared/model/bank-branch.model';
import { type IWalletTransaction } from '@/shared/model/wallet-transaction.model';
import { type IBeneficiary } from '@/shared/model/beneficiary.model';

import { type Gender } from '@/shared/model/enumerations/gender.model';
import { type IdType } from '@/shared/model/enumerations/id-type.model';
import { type WalletType } from '@/shared/model/enumerations/wallet-type.model';
import { type WalletStatus } from '@/shared/model/enumerations/wallet-status.model';
export interface IWalletUser {
  id?: number;
  arabicFirstName?: string | null;
  arabicMiddleName?: string | null;
  arabicLastName?: string | null;
  englishFirstName?: string | null;
  englishMiddleName?: string | null;
  englishLastName?: string | null;
  commercialRegistryNo?: string | null;
  commercialLicenceNo?: string | null;
  commercialName?: string | null;
  birthDate?: Date | null;
  birthPlace?: string | null;
  nationalNo?: string | null;
  registerNo?: string | null;
  gender?: keyof typeof Gender | null;
  idType?: keyof typeof IdType | null;
  idNo?: string | null;
  mobileNo?: string | null;
  verifiedByEmailOtp?: boolean | null;
  verifiedByMobileOtp?: boolean | null;
  email?: string | null;
  nidStatus?: string | null;
  isForeign?: boolean | null;
  isMobileConnectedWithNid?: boolean | null;
  isKYCVerified?: boolean | null;
  walletType?: keyof typeof WalletType | null;
  walletStatus?: keyof typeof WalletStatus | null;
  imageFileContentType?: string | null;
  imageFile?: string | null;
  imageUrlFile?: string | null;
  postCode?: string | null;
  address?: string | null;
  lat?: number | null;
  lng?: number | null;
  notes?: string | null;
  bankAccountName?: string | null;
  bankAccountNumber?: string | null;
  bankAccountIBAN?: string | null;
  bankAccountSWIFT?: string | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  user?: IUser | null;
  category?: ICategory | null;
  city?: ICity | null;
  walletProfile?: IWalletProfile | null;
  bankBranch?: IBankBranch | null;
  walletTransactions?: IWalletTransaction[] | null;
  beneficiaries?: IBeneficiary[] | null;
}

export class WalletUser implements IWalletUser {
  constructor(
    public id?: number,
    public arabicFirstName?: string | null,
    public arabicMiddleName?: string | null,
    public arabicLastName?: string | null,
    public englishFirstName?: string | null,
    public englishMiddleName?: string | null,
    public englishLastName?: string | null,
    public commercialRegistryNo?: string | null,
    public commercialLicenceNo?: string | null,
    public commercialName?: string | null,
    public birthDate?: Date | null,
    public birthPlace?: string | null,
    public nationalNo?: string | null,
    public registerNo?: string | null,
    public gender?: keyof typeof Gender | null,
    public idType?: keyof typeof IdType | null,
    public idNo?: string | null,
    public mobileNo?: string | null,
    public verifiedByEmailOtp?: boolean | null,
    public verifiedByMobileOtp?: boolean | null,
    public email?: string | null,
    public nidStatus?: string | null,
    public isForeign?: boolean | null,
    public isMobileConnectedWithNid?: boolean | null,
    public isKYCVerified?: boolean | null,
    public walletType?: keyof typeof WalletType | null,
    public walletStatus?: keyof typeof WalletStatus | null,
    public imageFileContentType?: string | null,
    public imageFile?: string | null,
    public imageUrlFile?: string | null,
    public postCode?: string | null,
    public address?: string | null,
    public lat?: number | null,
    public lng?: number | null,
    public notes?: string | null,
    public bankAccountName?: string | null,
    public bankAccountNumber?: string | null,
    public bankAccountIBAN?: string | null,
    public bankAccountSWIFT?: string | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public user?: IUser | null,
    public category?: ICategory | null,
    public city?: ICity | null,
    public walletProfile?: IWalletProfile | null,
    public bankBranch?: IBankBranch | null,
    public walletTransactions?: IWalletTransaction[] | null,
    public beneficiaries?: IBeneficiary[] | null,
  ) {
    this.verifiedByEmailOtp = this.verifiedByEmailOtp ?? false;
    this.verifiedByMobileOtp = this.verifiedByMobileOtp ?? false;
    this.isForeign = this.isForeign ?? false;
    this.isMobileConnectedWithNid = this.isMobileConnectedWithNid ?? false;
    this.isKYCVerified = this.isKYCVerified ?? false;
  }
}
