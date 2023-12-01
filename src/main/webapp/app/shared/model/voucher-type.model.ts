import { type IVoucherCompany } from '@/shared/model/voucher-company.model';

export interface IVoucherType {
  id?: number;
  nameAr?: string | null;
  nameEn?: string | null;
  slug?: string | null;
  amount?: number | null;
  menuOrder?: number | null;
  voucherCompany?: IVoucherCompany | null;
}

export class VoucherType implements IVoucherType {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public nameEn?: string | null,
    public slug?: string | null,
    public amount?: number | null,
    public menuOrder?: number | null,
    public voucherCompany?: IVoucherCompany | null,
  ) {}
}
