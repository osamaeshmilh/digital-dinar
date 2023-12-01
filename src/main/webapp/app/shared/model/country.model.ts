export interface ICountry {
  id?: number;
  nameAr?: string | null;
  nameEn?: string | null;
  iso2?: string | null;
  iso3?: string | null;
  isoNo?: number | null;
}

export class Country implements ICountry {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public nameEn?: string | null,
    public iso2?: string | null,
    public iso3?: string | null,
    public isoNo?: number | null,
  ) {}
}
