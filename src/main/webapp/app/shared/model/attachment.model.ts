import { type AttachmentType } from '@/shared/model/enumerations/attachment-type.model';
import { type ReferenceType } from '@/shared/model/enumerations/reference-type.model';
export interface IAttachment {
  id?: number;
  name?: string | null;
  details?: string | null;
  notes?: string | null;
  attachmentType?: keyof typeof AttachmentType | null;
  fileContentType?: string | null;
  file?: string | null;
  fileUrl?: string | null;
  referenceType?: keyof typeof ReferenceType | null;
  referenceId?: number | null;
}

export class Attachment implements IAttachment {
  constructor(
    public id?: number,
    public name?: string | null,
    public details?: string | null,
    public notes?: string | null,
    public attachmentType?: keyof typeof AttachmentType | null,
    public fileContentType?: string | null,
    public file?: string | null,
    public fileUrl?: string | null,
    public referenceType?: keyof typeof ReferenceType | null,
    public referenceId?: number | null,
  ) {}
}
