import { type IVoucherType } from '@/shared/model/voucher-type.model';
import { type IVoucherCompany } from '@/shared/model/voucher-company.model';

export interface IVoucher {
  id?: number;
  code?: string | null;
  amount?: number | null;
  serialNumber?: string | null;
  isSold?: boolean | null;
  sellTransactionId?: number | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  voucherType?: IVoucherType | null;
  voucherCompany?: IVoucherCompany | null;
}

export class Voucher implements IVoucher {
  constructor(
    public id?: number,
    public code?: string | null,
    public amount?: number | null,
    public serialNumber?: string | null,
    public isSold?: boolean | null,
    public sellTransactionId?: number | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public voucherType?: IVoucherType | null,
    public voucherCompany?: IVoucherCompany | null,
  ) {
    this.isSold = this.isSold ?? false;
  }
}
