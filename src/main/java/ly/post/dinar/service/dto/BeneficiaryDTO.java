package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.time.Instant;
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

    private String bankAccountName;

    private String bankAccountNumber;

    private IdType idType;

    private String idNo;

    private String mobileNo;

    private String email;

    private String notes;

    private Boolean isVerified;

    private Boolean hasTransferred;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private BankBranchDTO bankBranch;

    private WalletUserDTO walletUser;

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

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
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

    public BankBranchDTO getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranchDTO bankBranch) {
        this.bankBranch = bankBranch;
    }

    public WalletUserDTO getWalletUser() {
        return walletUser;
    }

    public void setWalletUser(WalletUserDTO walletUser) {
        this.walletUser = walletUser;
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
            ", bankAccountName='" + getBankAccountName() + "'" +
            ", bankAccountNumber='" + getBankAccountNumber() + "'" +
            ", idType='" + getIdType() + "'" +
            ", idNo='" + getIdNo() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", notes='" + getNotes() + "'" +
            ", isVerified='" + getIsVerified() + "'" +
            ", hasTransferred='" + getHasTransferred() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", bankBranch=" + getBankBranch() +
            ", walletUser=" + getWalletUser() +
            "}";
    }
}
