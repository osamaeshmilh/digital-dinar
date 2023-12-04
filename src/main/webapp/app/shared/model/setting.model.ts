export interface ISetting {
  id?: number;
  key?: string | null;
  value?: string | null;
}

export class Setting implements ISetting {
  constructor(
    public id?: number,
    public key?: string | null,
    public value?: string | null,
  ) {}
}
