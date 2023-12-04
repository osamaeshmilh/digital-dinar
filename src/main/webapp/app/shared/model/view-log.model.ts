export interface IViewLog {
  id?: number;
  entityName?: string | null;
  entityId?: number | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}

export class ViewLog implements IViewLog {
  constructor(
    public id?: number,
    public entityName?: string | null,
    public entityId?: number | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
  ) {}
}
