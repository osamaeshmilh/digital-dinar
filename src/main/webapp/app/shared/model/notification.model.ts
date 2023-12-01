export interface INotification {
  id?: number;
  title?: string | null;
  description?: string | null;
  userId?: number | null;
}

export class Notification implements INotification {
  constructor(
    public id?: number,
    public title?: string | null,
    public description?: string | null,
    public userId?: number | null,
  ) {}
}
