import { type ICountry } from '@/shared/model/country.model';

export interface ICity {
  id?: number;
  nameAr?: string | null;
  nameEn?: string | null;
  country?: ICountry | null;
}

export class City implements ICity {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public nameEn?: string | null,
    public country?: ICountry | null,
  ) {}
}
