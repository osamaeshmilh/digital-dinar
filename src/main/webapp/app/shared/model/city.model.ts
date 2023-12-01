import { type ICountry } from '@/shared/model/country.model';

export interface ICity {
  id?: number;
  nameAr?: string | null;
  nameEn?: string | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  country?: ICountry | null;
}

export class City implements ICity {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public nameEn?: string | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public country?: ICountry | null,
  ) {}
}
