import { type IBank } from '@/shared/model/bank.model';

export interface IBankBranch {
  id?: number;
  nameAr?: string | null;
  namEn?: string | null;
  address?: string | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  bank?: IBank | null;
}

export class BankBranch implements IBankBranch {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public namEn?: string | null,
    public address?: string | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public bank?: IBank | null,
  ) {}
}
