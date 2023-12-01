package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.IdType;

/**
 * A DTO for the {@link ly.post.dinar.domain.Beneficiary} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BeneficiaryDTO implements Serializable {

    private Long id;

    private String arabicFirstName;

    private String arabicLastName;

    private String englishFirstName;

    private String englishLastName;

    private IdType idType;

    private String idNo;

    private String mobileNo;

    private String email;

    private String notes;

    private Long createdByUserId;

    private Boolean isVerified;

    private Boolean hasTransferred;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArabicFirstName() {
        return arabicFirstName;
    }

    public void setArabicFirstName(String arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
    }

    public String getArabicLastName() {
        return arabicLastName;
    }

    public void setArabicLastName(String arabicLastName) {
        this.arabicLastName = arabicLastName;
    }

    public String getEnglishFirstName() {
        return englishFirstName;
    }

    public void setEnglishFirstName(String englishFirstName) {
        this.englishFirstName = englishFirstName;
    }

    public String getEnglishLastName() {
        return englishLastName;
    }

    public void setEnglishLastName(String englishLastName) {
        this.englishLastName = englishLastName;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Boolean getHasTransferred() {
        return hasTransferred;
    }

    public void setHasTransferred(Boolean hasTransferred) {
        this.hasTransferred = hasTransferred;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BeneficiaryDTO)) {
            return false;
        }

        BeneficiaryDTO beneficiaryDTO = (BeneficiaryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, beneficiaryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BeneficiaryDTO{" +
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
