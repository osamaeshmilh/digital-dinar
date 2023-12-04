export interface IViewLog {
  id?: number;
  entityName?: string | null;
  entityId?: number | null;
}

export class ViewLog implements IViewLog {
  constructor(
    public id?: number,
    public entityName?: string | null,
    public entityId?: number | null,
  ) {}
}
