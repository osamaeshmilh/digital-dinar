export interface IActivation {
  id?: number;
  mobileNo?: string | null;
  email?: string | null;
  code?: string | null;
  sentOn?: Date | null;
  validUntil?: Date | null;
  isUsed?: boolean | null;
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
  ) {
    this.isUsed = this.isUsed ?? false;
  }
}
