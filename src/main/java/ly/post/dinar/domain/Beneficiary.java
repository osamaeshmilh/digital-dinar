package ly.post.dinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import ly.post.dinar.domain.enumeration.IdType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Beneficiary.
 */
@Entity
@Table(name = "beneficiary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Beneficiary extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "arabic_first_name")
    private String arabicFirstName;

    @Column(name = "arabic_last_name")
    private String arabicLastName;

    @Column(name = "english_first_name")
    private String englishFirstName;

    @Column(name = "english_last_name")
    private String englishLastName;

    @Column(name = "bank_account_name")
    private String bankAccountName;

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_type")
    private IdType idType;

    @Column(name = "id_no")
    private String idNo;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "notes")
    private String notes;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "has_transferred")
    private Boolean hasTransferred;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "bank" }, allowSetters = true)
    private BankBranch bankBranch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "user", "category", "city", "walletProfile", "bankBranch", "walletTransactions", "beneficiaries" },
        allowSetters = true
    )
    private WalletUser walletUser;

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

    public String getBankAccountName() {
        return this.bankAccountName;
    }

    public Beneficiary bankAccountName(String bankAccountName) {
        this.setBankAccountName(bankAccountName);
        return this;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNumber() {
        return this.bankAccountNumber;
    }

    public Beneficiary bankAccountNumber(String bankAccountNumber) {
        this.setBankAccountNumber(bankAccountNumber);
        return this;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
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

    // Inherited createdBy methods
    public Beneficiary createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public Beneficiary createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public Beneficiary lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public Beneficiary lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public BankBranch getBankBranch() {
        return this.bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Beneficiary bankBranch(BankBranch bankBranch) {
        this.setBankBranch(bankBranch);
        return this;
    }

    public WalletUser getWalletUser() {
        return this.walletUser;
    }

    public void setWalletUser(WalletUser walletUser) {
        this.walletUser = walletUser;
    }

    public Beneficiary walletUser(WalletUser walletUser) {
        this.setWalletUser(walletUser);
        return this;
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
            "}";
    }
}
