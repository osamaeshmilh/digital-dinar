export interface ICategory {
  id?: number;
  nameAr?: string | null;
  nameEn?: string | null;
  code?: string | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public nameEn?: string | null,
    public code?: string | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
  ) {}
}
