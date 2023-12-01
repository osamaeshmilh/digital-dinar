package ly.post.dinar.domain;

import java.io.Serializable;
import ly.post.dinar.domain.enumeration.IdType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Beneficiary.
 */
@Table("beneficiary")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Beneficiary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("arabic_first_name")
    private String arabicFirstName;

    @Column("arabic_last_name")
    private String arabicLastName;

    @Column("english_first_name")
    private String englishFirstName;

    @Column("english_last_name")
    private String englishLastName;

    @Column("id_type")
    private IdType idType;

    @Column("id_no")
    private String idNo;

    @Column("mobile_no")
    private String mobileNo;

    @Column("email")
    private String email;

    @Column("notes")
    private String notes;

    @Column("created_by_user_id")
    private Long createdByUserId;

    @Column("is_verified")
    private Boolean isVerified;

    @Column("has_transferred")
    private Boolean hasTransferred;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Beneficiary id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArabicFirstName() {
        return this.arabicFirstName;
    }

    public Beneficiary arabicFirstName(String arabicFirstName) {
        this.setArabicFirstName(arabicFirstName);
        return this;
    }

    public void setArabicFirstName(String arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
    }

    public String getArabicLastName() {
        return this.arabicLastName;
    }

    public Beneficiary arabicLastName(String arabicLastName) {
        this.setArabicLastName(arabicLastName);
        return this;
    }

    public void setArabicLastName(String arabicLastName) {
        this.arabicLastName = arabicLastName;
    }

    public String getEnglishFirstName() {
        return this.englishFirstName;
    }

    public Beneficiary englishFirstName(String englishFirstName) {
        this.setEnglishFirstName(englishFirstName);
        return this;
    }

    public void setEnglishFirstName(String englishFirstName) {
        this.englishFirstName = englishFirstName;
    }

    public String getEnglishLastName() {
        return this.englishLastName;
    }

    public Beneficiary englishLastName(String englishLastName) {
        this.setEnglishLastName(englishLastName);
        return this;
    }

    public void setEnglishLastName(String englishLastName) {
        this.englishLastName = englishLastName;
    }

    public IdType getIdType() {
        return this.idType;
    }

    public Beneficiary idType(IdType idType) {
        this.setIdType(idType);
        return this;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return this.idNo;
    }

    public Beneficiary idNo(String idNo) {
        this.setIdNo(idNo);
        return this;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Beneficiary mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return this.email;
    }

    public Beneficiary email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return this.notes;
    }

    public Beneficiary notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getCreatedByUserId() {
        return this.createdByUserId;
    }

    public Beneficiary createdByUserId(Long createdByUserId) {
        this.setCreatedByUserId(createdByUserId);
        return this;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Boolean getIsVerified() {
        return this.isVerified;
    }

    public Beneficiary isVerified(Boolean isVerified) {
        this.setIsVerified(isVerified);
        return this;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Boolean getHasTransferred() {
        return this.hasTransferred;
    }

    public Beneficiary hasTransferred(Boolean hasTransferred) {
        this.setHasTransferred(hasTransferred);
        return this;
    }

    public void setHasTransferred(Boolean hasTransferred) {
        this.hasTransferred = hasTransferred;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Beneficiary)) {
            return false;
        }
        return getId() != null && getId().equals(((Beneficiary) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Beneficiary{" +
            "id=" + getId() +
            ", arabicFirstName='" + getArabicFirstName() + "'" +
            ", arabicLastName='" + getArabicLastName() + "'" +
            ", englishFirstName='" + getEnglishFirstName() + "'" +
            ", englishLastName='" + getEnglishLastName() + "'" +
            ", idType='" + getIdType() + "'" +
            ", idNo='" + getIdNo() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", notes='" + getNotes() + "'" +
            ", createdByUserId=" + getCreatedByUserId() +
            ", isVerified='" + getIsVerified() + "'" +
            ", hasTransferred='" + getHasTransferred() + "'" +
            "}";
    }
}
