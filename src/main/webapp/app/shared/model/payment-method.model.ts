import { type PaymentType } from '@/shared/model/enumerations/payment-type.model';
export interface IPaymentMethod {
  id?: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  menuOrder?: number | null;
  imageFileUrl?: string | null;
  imageFileContentType?: string | null;
  imageFile?: string | null;
  details?: string | null;
  feePercentage?: number | null;
  paymentType?: keyof typeof PaymentType | null;
  notes?: string | null;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}

export class PaymentMethod implements IPaymentMethod {
  constructor(
    public id?: number,
    public name?: string | null,
    public nameAr?: string | null,
    public nameEn?: string | null,
    public menuOrder?: number | null,
    public imageFileUrl?: string | null,
    public imageFileContentType?: string | null,
    public imageFile?: string | null,
    public details?: string | null,
    public feePercentage?: number | null,
    public paymentType?: keyof typeof PaymentType | null,
    public notes?: string | null,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
  ) {}
}
