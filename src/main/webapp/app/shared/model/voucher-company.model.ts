import { type IUser } from '@/shared/model/user.model';

export interface IVoucherCompany {
  id?: number;
  nameAr?: string | null;
  nameEn?: string | null;
  mobileNo?: string | null;
  email?: string | null;
  imageFileContentType?: string | null;
  imageFile?: string | null;
  imageUrlFile?: string | null;
  postCode?: string | null;
  address?: string | null;
  notes?: string | null;
  user?: IUser | null;
}

export class VoucherCompany implements IVoucherCompany {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public nameEn?: string | null,
    public mobileNo?: string | null,
    public email?: string | null,
    public imageFileContentType?: string | null,
    public imageFile?: string | null,
    public imageUrlFile?: string | null,
    public postCode?: string | null,
    public address?: string | null,
    public notes?: string | null,
    public user?: IUser | null,
  ) {}
}
