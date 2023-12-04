import { type IBank } from '@/shared/model/bank.model';

export interface IBankBranch {
  id?: number;
  nameAr?: string | null;
  namEn?: string | null;
  address?: string | null;
  bank?: IBank | null;
}

export class BankBranch implements IBankBranch {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public namEn?: string | null,
    public address?: string | null,
    public bank?: IBank | null,
  ) {}
}
