import { type IUser } from '@/shared/model/user.model';
import { type IWalletProfile } from '@/shared/model/wallet-profile.model';

import { type Gender } from '@/shared/model/enumerations/gender.model';
import { type IdType } from '@/shared/model/enumerations/id-type.model';
import { type WalletOwnerStatus } from '@/shared/model/enumerations/wallet-owner-status.model';
export interface IConsumer {
  id?: number;
  arabicFirstName?: string | null;
  arabicMiddleName?: string | null;
  arabicLastName?: string | null;
  englishFirstName?: string | null;
  englishMiddleName?: string | null;
  englishLastName?: string | null;
  birthDate?: Date | null;
  birthPlace?: string | null;
  nationalNo?: string | null;
  registerNo?: string | null;
  gender?: keyof typeof Gender | null;
  idType?: keyof typeof IdType | null;
  idNo?: string | null;
  mobileNo?: string;
  verifiedByEmailOtp?: boolean | null;
  verifiedByMobileNoOtp?: boolean | null;
  email?: string | null;
  nidStatus?: string | null;
  isForeign?: boolean | null;
  isMobileConnectedWithNid?: boolean | null;
  walletOwnerStatus?: keyof typeof WalletOwnerStatus | null;
  imageFileContentType?: string | null;
  imageFile?: string | null;
  imageUrlFile?: string | null;
  postCode?: string | null;
  address?: string | null;
  lat?: number | null;
  lng?: number | null;
  notes?: string | null;
  user?: IUser | null;
  walletProfile?: IWalletProfile | null;
}

export class Consumer implements IConsumer {
  constructor(
    public id?: number,
    public arabicFirstName?: string | null,
    public arabicMiddleName?: string | null,
    public arabicLastName?: string | null,
    public englishFirstName?: string | null,
    public englishMiddleName?: string | null,
    public englishLastName?: string | null,
    public birthDate?: Date | null,
    public birthPlace?: string | null,
    public nationalNo?: string | null,
    public registerNo?: string | null,
    public gender?: keyof typeof Gender | null,
    public idType?: keyof typeof IdType | null,
    public idNo?: string | null,
    public mobileNo?: string,
    public verifiedByEmailOtp?: boolean | null,
    public verifiedByMobileNoOtp?: boolean | null,
    public email?: string | null,
    public nidStatus?: string | null,
    public isForeign?: boolean | null,
    public isMobileConnectedWithNid?: boolean | null,
    public walletOwnerStatus?: keyof typeof WalletOwnerStatus | null,
    public imageFileContentType?: string | null,
    public imageFile?: string | null,
    public imageUrlFile?: string | null,
    public postCode?: string | null,
    public address?: string | null,
    public lat?: number | null,
    public lng?: number | null,
    public notes?: string | null,
    public user?: IUser | null,
    public walletProfile?: IWalletProfile | null,
  ) {
    this.verifiedByEmailOtp = this.verifiedByEmailOtp ?? false;
    this.verifiedByMobileNoOtp = this.verifiedByMobileNoOtp ?? false;
    this.isForeign = this.isForeign ?? false;
    this.isMobileConnectedWithNid = this.isMobileConnectedWithNid ?? false;
  }
}
