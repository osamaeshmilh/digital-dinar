export interface ISlider {
  id?: number;
  details?: string | null;
  menuOrder?: number | null;
  imageFileUrl?: string | null;
  imageFileContentType?: string | null;
  imageFile?: string | null;
  url?: string | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}

export class Slider implements ISlider {
  constructor(
    public id?: number,
    public details?: string | null,
    public menuOrder?: number | null,
    public imageFileUrl?: string | null,
    public imageFileContentType?: string | null,
    public imageFile?: string | null,
    public url?: string | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
  ) {}
}
