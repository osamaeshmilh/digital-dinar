export interface ISetting {
  id?: number;
  key?: string | null;
  value?: string | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}

export class Setting implements ISetting {
  constructor(
    public id?: number,
    public key?: string | null,
    public value?: string | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
  ) {}
}
