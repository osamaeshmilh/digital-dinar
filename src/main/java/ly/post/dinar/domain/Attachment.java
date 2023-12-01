package ly.post.dinar.domain;

import java.io.Serializable;
import ly.post.dinar.domain.enumeration.AttachmentType;
import ly.post.dinar.domain.enumeration.ReferenceType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Attachment.
 */
@Table("attachment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("details")
    private String details;

    @Column("notes")
    private String notes;

    @Column("attachment_type")
    private AttachmentType attachmentType;

    @Column("file")
    private byte[] file;

    @Column("file_content_type")
    private String fileContentType;

    @Column("file_url")
    private String fileUrl;

    @Column("reference_type")
    private ReferenceType referenceType;

    @Column("reference_id")
    private Long referenceId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Attachment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Attachment name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return this.details;
    }

    public Attachment details(String details) {
        this.setDetails(details);
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNotes() {
        return this.notes;
    }

    public Attachment notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public AttachmentType getAttachmentType() {
        return this.attachmentType;
    }

    public Attachment attachmentType(AttachmentType attachmentType) {
        this.setAttachmentType(attachmentType);
        return this;
    }

    public void setAttachmentType(AttachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }

    public byte[] getFile() {
        return this.file;
    }

    public Attachment file(byte[] file) {
        this.setFile(file);
        return this;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return this.fileContentType;
    }

    public Attachment fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public Attachment fileUrl(String fileUrl) {
        this.setFileUrl(fileUrl);
        return this;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public ReferenceType getReferenceType() {
        return this.referenceType;
    }

    public Attachment referenceType(ReferenceType referenceType) {
        this.setReferenceType(referenceType);
        return this;
    }

    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType = referenceType;
    }

    public Long getReferenceId() {
        return this.referenceId;
    }

    public Attachment referenceId(Long referenceId) {
        this.setReferenceId(referenceId);
        return this;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Attachment)) {
            return false;
        }
        return getId() != null && getId().equals(((Attachment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Attachment{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", details='" + getDetails() + "'" +
            ", notes='" + getNotes() + "'" +
            ", attachmentType='" + getAttachmentType() + "'" +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            ", fileUrl='" + getFileUrl() + "'" +
            ", referenceType='" + getReferenceType() + "'" +
            ", referenceId=" + getReferenceId() +
            "}";
    }
}
