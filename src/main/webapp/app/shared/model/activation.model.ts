export interface IActivation {
  id?: number;
  mobileNo?: string | null;
  email?: string | null;
  code?: string | null;
  sentOn?: Date | null;
  validUntil?: Date | null;
  isUsed?: boolean | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}

export class Activation implements IActivation {
  constructor(
    public id?: number,
    public mobileNo?: string | null,
    public email?: string | null,
    public code?: string | null,
    public sentOn?: Date | null,
    public validUntil?: Date | null,
    public isUsed?: boolean | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
  ) {
    this.isUsed = this.isUsed ?? false;
  }
}
