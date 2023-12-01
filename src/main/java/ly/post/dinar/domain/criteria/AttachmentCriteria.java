package ly.post.dinar.domain.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.AttachmentType;
import ly.post.dinar.domain.enumeration.ReferenceType;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.Attachment} entity. This class is used
 * in {@link ly.post.dinar.web.rest.AttachmentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /attachments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttachmentCriteria implements Serializable, Criteria {

    /**
     * Class for filtering AttachmentType
     */
    public static class AttachmentTypeFilter extends Filter<AttachmentType> {

        public AttachmentTypeFilter() {}

        public AttachmentTypeFilter(AttachmentTypeFilter filter) {
            super(filter);
        }

        @Override
        public AttachmentTypeFilter copy() {
            return new AttachmentTypeFilter(this);
        }
    }

    /**
     * Class for filtering ReferenceType
     */
    public static class ReferenceTypeFilter extends Filter<ReferenceType> {

        public ReferenceTypeFilter() {}

        public ReferenceTypeFilter(ReferenceTypeFilter filter) {
            super(filter);
        }

        @Override
        public ReferenceTypeFilter copy() {
            return new ReferenceTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter details;

    private StringFilter notes;

    private AttachmentTypeFilter attachmentType;

    private StringFilter fileUrl;

    private ReferenceTypeFilter referenceType;

    private LongFilter referenceId;

    private StringFilter createdBy;

    private InstantFilter createdDate;

    private StringFilter lastModifiedBy;

    private InstantFilter lastModifiedDate;

    private Boolean distinct;

    public AttachmentCriteria() {}

    public AttachmentCriteria(AttachmentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.details = other.details == null ? null : other.details.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.attachmentType = other.attachmentType == null ? null : other.attachmentType.copy();
        this.fileUrl = other.fileUrl == null ? null : other.fileUrl.copy();
        this.referenceType = other.referenceType == null ? null : other.referenceType.copy();
        this.referenceId = other.referenceId == null ? null : other.referenceId.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.lastModifiedDate = other.lastModifiedDate == null ? null : other.lastModifiedDate.copy();
        this.distinct = other.distinct;
    }

    @Override
    public AttachmentCriteria copy() {
        return new AttachmentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getDetails() {
        return details;
    }

    public StringFilter details() {
        if (details == null) {
            details = new StringFilter();
        }
        return details;
    }

    public void setDetails(StringFilter details) {
        this.details = details;
    }

    public StringFilter getNotes() {
        return notes;
    }

    public StringFilter notes() {
        if (notes == null) {
            notes = new StringFilter();
        }
        return notes;
    }

    public void setNotes(StringFilter notes) {
        this.notes = notes;
    }

    public AttachmentTypeFilter getAttachmentType() {
        return attachmentType;
    }

    public AttachmentTypeFilter attachmentType() {
        if (attachmentType == null) {
            attachmentType = new AttachmentTypeFilter();
        }
        return attachmentType;
    }

    public void setAttachmentType(AttachmentTypeFilter attachmentType) {
        this.attachmentType = attachmentType;
    }

    public StringFilter getFileUrl() {
        return fileUrl;
    }

    public StringFilter fileUrl() {
        if (fileUrl == null) {
            fileUrl = new StringFilter();
        }
        return fileUrl;
    }

    public void setFileUrl(StringFilter fileUrl) {
        this.fileUrl = fileUrl;
    }

    public ReferenceTypeFilter getReferenceType() {
        return referenceType;
    }

    public ReferenceTypeFilter referenceType() {
        if (referenceType == null) {
            referenceType = new ReferenceTypeFilter();
        }
        return referenceType;
    }

    public void setReferenceType(ReferenceTypeFilter referenceType) {
        this.referenceType = referenceType;
    }

    public LongFilter getReferenceId() {
        return referenceId;
    }

    public LongFilter referenceId() {
        if (referenceId == null) {
            referenceId = new LongFilter();
        }
        return referenceId;
    }

    public void setReferenceId(LongFilter referenceId) {
        this.referenceId = referenceId;
    }

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public StringFilter createdBy() {
        if (createdBy == null) {
            createdBy = new StringFilter();
        }
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
    }

    public InstantFilter getCreatedDate() {
        return createdDate;
    }

    public InstantFilter createdDate() {
        if (createdDate == null) {
            createdDate = new InstantFilter();
        }
        return createdDate;
    }

    public void setCreatedDate(InstantFilter createdDate) {
        this.createdDate = createdDate;
    }

    public StringFilter getLastModifiedBy() {
        return lastModifiedBy;
    }

    public StringFilter lastModifiedBy() {
        if (lastModifiedBy == null) {
            lastModifiedBy = new StringFilter();
        }
        return lastModifiedBy;
    }

    public void setLastModifiedBy(StringFilter lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public InstantFilter getLastModifiedDate() {
        return lastModifiedDate;
    }

    public InstantFilter lastModifiedDate() {
        if (lastModifiedDate == null) {
            lastModifiedDate = new InstantFilter();
        }
        return lastModifiedDate;
    }

    public void setLastModifiedDate(InstantFilter lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AttachmentCriteria that = (AttachmentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(details, that.details) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(attachmentType, that.attachmentType) &&
            Objects.equals(fileUrl, that.fileUrl) &&
            Objects.equals(referenceType, that.referenceType) &&
            Objects.equals(referenceId, that.referenceId) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            details,
            notes,
            attachmentType,
            fileUrl,
            referenceType,
            referenceId,
            createdBy,
            createdDate,
            lastModifiedBy,
            lastModifiedDate,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttachmentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (details != null ? "details=" + details + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (attachmentType != null ? "attachmentType=" + attachmentType + ", " : "") +
            (fileUrl != null ? "fileUrl=" + fileUrl + ", " : "") +
            (referenceType != null ? "referenceType=" + referenceType + ", " : "") +
            (referenceId != null ? "referenceId=" + referenceId + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (lastModifiedDate != null ? "lastModifiedDate=" + lastModifiedDate + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
