export interface IBank {
  id?: number;
  nameAr?: string | null;
  namEn?: string | null;
  swiftCode?: string | null;
}

export class Bank implements IBank {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public namEn?: string | null,
    public swiftCode?: string | null,
  ) {}
}
