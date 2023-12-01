export interface INotification {
  id?: number;
  title?: string | null;
  description?: string | null;
  userId?: number | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}

export class Notification implements INotification {
  constructor(
    public id?: number,
    public title?: string | null,
    public description?: string | null,
    public userId?: number | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
  ) {}
}
