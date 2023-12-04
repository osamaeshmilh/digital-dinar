export interface IBank {
  id?: number;
  nameAr?: string | null;
  namEn?: string | null;
  swiftCode?: string | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}

export class Bank implements IBank {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public namEn?: string | null,
    public swiftCode?: string | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
  ) {}
}
