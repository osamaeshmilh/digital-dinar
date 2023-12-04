export interface ICategory {
  id?: number;
  nameAr?: string | null;
  nameEn?: string | null;
  code?: string | null;
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public nameEn?: string | null,
    public code?: string | null,
  ) {}
}
