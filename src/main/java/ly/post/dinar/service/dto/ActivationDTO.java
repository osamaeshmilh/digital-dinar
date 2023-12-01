package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link ly.post.dinar.domain.Activation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ActivationDTO implements Serializable {

    private Long id;

    private String mobileNo;

    private String email;

    private String code;

    private Instant sentOn;

    private Instant validUntil;

    private Boolean isUsed;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getSentOn() {
        return sentOn;
    }

    public void setSentOn(Instant sentOn) {
        this.sentOn = sentOn;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActivationDTO)) {
            return false;
        }

        ActivationDTO activationDTO = (ActivationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, activationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActivationDTO{" +
            "id=" + getId() +
            ", mobileNo='" + getMobileNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", code='" + getCode() + "'" +
            ", sentOn='" + getSentOn() + "'" +
            ", validUntil='" + getValidUntil() + "'" +
            ", isUsed='" + getIsUsed() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
