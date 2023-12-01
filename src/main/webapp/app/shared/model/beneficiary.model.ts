import { type IdType } from '@/shared/model/enumerations/id-type.model';
export interface IBeneficiary {
  id?: number;
  arabicFirstName?: string | null;
  arabicLastName?: string | null;
  englishFirstName?: string | null;
  englishLastName?: string | null;
  idType?: keyof typeof IdType | null;
  idNo?: string | null;
  mobileNo?: string | null;
  email?: string | null;
  notes?: string | null;
  createdByUserId?: number | null;
  isVerified?: boolean | null;
  hasTransferred?: boolean | null;
}

export class Beneficiary implements IBeneficiary {
  constructor(
    public id?: number,
    public arabicFirstName?: string | null,
    public arabicLastName?: string | null,
    public englishFirstName?: string | null,
    public englishLastName?: string | null,
    public idType?: keyof typeof IdType | null,
    public idNo?: string | null,
    public mobileNo?: string | null,
    public email?: string | null,
    public notes?: string | null,
    public createdByUserId?: number | null,
    public isVerified?: boolean | null,
    public hasTransferred?: boolean | null,
  ) {
    this.isVerified = this.isVerified ?? false;
    this.hasTransferred = this.hasTransferred ?? false;
  }
}
