package ly.post.dinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import ly.post.dinar.domain.enumeration.Gender;
import ly.post.dinar.domain.enumeration.IdType;
import ly.post.dinar.domain.enumeration.WalletStatus;
import ly.post.dinar.domain.enumeration.WalletType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A WalletUser.
 */
@Entity
@Table(name = "wallet_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WalletUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "arabic_first_name")
    private String arabicFirstName;

    @Column(name = "arabic_middle_name")
    private String arabicMiddleName;

    @Column(name = "arabic_last_name")
    private String arabicLastName;

    @Column(name = "english_first_name")
    private String englishFirstName;

    @Column(name = "english_middle_name")
    private String englishMiddleName;

    @Column(name = "english_last_name")
    private String englishLastName;

    @Column(name = "commercial_registry_no")
    private String commercialRegistryNo;

    @Column(name = "commercial_licence_no")
    private String commercialLicenceNo;

    @Column(name = "commercial_name")
    private String commercialName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "national_no")
    private String nationalNo;

    @Column(name = "register_no")
    private String registerNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_type")
    private IdType idType;

    @Column(name = "id_no")
    private String idNo;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "verified_by_email_otp")
    private Boolean verifiedByEmailOtp;

    @Column(name = "verified_by_mobile_otp")
    private Boolean verifiedByMobileOtp;

    @Column(name = "email")
    private String email;

    @Column(name = "nid_status")
    private String nidStatus;

    @Column(name = "is_foreign")
    private Boolean isForeign;

    @Column(name = "is_mobile_connected_with_nid")
    private Boolean isMobileConnectedWithNid;

    @Column(name = "is_kyc_verified")
    private Boolean isKYCVerified;

    @Enumerated(EnumType.STRING)
    @Column(name = "wallet_type")
    private WalletType walletType;

    @Enumerated(EnumType.STRING)
    @Column(name = "wallet_status")
    private WalletStatus walletStatus;

    @Lob
    @Column(name = "image_file")
    private byte[] imageFile;

    @Column(name = "image_file_content_type")
    private String imageFileContentType;

    @Column(name = "image_url_file")
    private String imageUrlFile;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "address")
    private String address;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lng")
    private Float lng;

    @Column(name = "notes")
    private String notes;

    @Column(name = "bank_account_name")
    private String bankAccountName;

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    @Column(name = "bank_account_iban")
    private String bankAccountIBAN;

    @Column(name = "bank_account_swift")
    private String bankAccountSWIFT;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "country" }, allowSetters = true)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    private WalletProfile walletProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "bank" }, allowSetters = true)
    private BankBranch bankBranch;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "walletUser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "transaction", "walletUser" }, allowSetters = true)
    private Set<WalletTransaction> walletTransactions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "walletUser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "bankBranch", "walletUser" }, allowSetters = true)
    private Set<Beneficiary> beneficiaries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public WalletUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArabicFirstName() {
        return this.arabicFirstName;
    }

    public WalletUser arabicFirstName(String arabicFirstName) {
        this.setArabicFirstName(arabicFirstName);
        return this;
    }

    public void setArabicFirstName(String arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
    }

    public String getArabicMiddleName() {
        return this.arabicMiddleName;
    }

    public WalletUser arabicMiddleName(String arabicMiddleName) {
        this.setArabicMiddleName(arabicMiddleName);
        return this;
    }

    public void setArabicMiddleName(String arabicMiddleName) {
        this.arabicMiddleName = arabicMiddleName;
    }

    public String getArabicLastName() {
        return this.arabicLastName;
    }

    public WalletUser arabicLastName(String arabicLastName) {
        this.setArabicLastName(arabicLastName);
        return this;
    }

    public void setArabicLastName(String arabicLastName) {
        this.arabicLastName = arabicLastName;
    }

    public String getEnglishFirstName() {
        return this.englishFirstName;
    }

    public WalletUser englishFirstName(String englishFirstName) {
        this.setEnglishFirstName(englishFirstName);
        return this;
    }

    public void setEnglishFirstName(String englishFirstName) {
        this.englishFirstName = englishFirstName;
    }

    public String getEnglishMiddleName() {
        return this.englishMiddleName;
    }

    public WalletUser englishMiddleName(String englishMiddleName) {
        this.setEnglishMiddleName(englishMiddleName);
        return this;
    }

    public void setEnglishMiddleName(String englishMiddleName) {
        this.englishMiddleName = englishMiddleName;
    }

    public String getEnglishLastName() {
        return this.englishLastName;
    }

    public WalletUser englishLastName(String englishLastName) {
        this.setEnglishLastName(englishLastName);
        return this;
    }

    public void setEnglishLastName(String englishLastName) {
        this.englishLastName = englishLastName;
    }

    public String getCommercialRegistryNo() {
        return this.commercialRegistryNo;
    }

    public WalletUser commercialRegistryNo(String commercialRegistryNo) {
        this.setCommercialRegistryNo(commercialRegistryNo);
        return this;
    }

    public void setCommercialRegistryNo(String commercialRegistryNo) {
        this.commercialRegistryNo = commercialRegistryNo;
    }

    public String getCommercialLicenceNo() {
        return this.commercialLicenceNo;
    }

    public WalletUser commercialLicenceNo(String commercialLicenceNo) {
        this.setCommercialLicenceNo(commercialLicenceNo);
        return this;
    }

    public void setCommercialLicenceNo(String commercialLicenceNo) {
        this.commercialLicenceNo = commercialLicenceNo;
    }

    public String getCommercialName() {
        return this.commercialName;
    }

    public WalletUser commercialName(String commercialName) {
        this.setCommercialName(commercialName);
        return this;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public WalletUser birthDate(LocalDate birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public WalletUser birthPlace(String birthPlace) {
        this.setBirthPlace(birthPlace);
        return this;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationalNo() {
        return this.nationalNo;
    }

    public WalletUser nationalNo(String nationalNo) {
        this.setNationalNo(nationalNo);
        return this;
    }

    public void setNationalNo(String nationalNo) {
        this.nationalNo = nationalNo;
    }

    public String getRegisterNo() {
        return this.registerNo;
    }

    public WalletUser registerNo(String registerNo) {
        this.setRegisterNo(registerNo);
        return this;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Gender getGender() {
        return this.gender;
    }

    public WalletUser gender(Gender gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public IdType getIdType() {
        return this.idType;
    }

    public WalletUser idType(IdType idType) {
        this.setIdType(idType);
        return this;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return this.idNo;
    }

    public WalletUser idNo(String idNo) {
        this.setIdNo(idNo);
        return this;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public WalletUser mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Boolean getVerifiedByEmailOtp() {
        return this.verifiedByEmailOtp;
    }

    public WalletUser verifiedByEmailOtp(Boolean verifiedByEmailOtp) {
        this.setVerifiedByEmailOtp(verifiedByEmailOtp);
        return this;
    }

    public void setVerifiedByEmailOtp(Boolean verifiedByEmailOtp) {
        this.verifiedByEmailOtp = verifiedByEmailOtp;
    }

    public Boolean getVerifiedByMobileOtp() {
        return this.verifiedByMobileOtp;
    }

    public WalletUser verifiedByMobileOtp(Boolean verifiedByMobileOtp) {
        this.setVerifiedByMobileOtp(verifiedByMobileOtp);
        return this;
    }

    public void setVerifiedByMobileOtp(Boolean verifiedByMobileOtp) {
        this.verifiedByMobileOtp = verifiedByMobileOtp;
    }

    public String getEmail() {
        return this.email;
    }

    public WalletUser email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNidStatus() {
        return this.nidStatus;
    }

    public WalletUser nidStatus(String nidStatus) {
        this.setNidStatus(nidStatus);
        return this;
    }

    public void setNidStatus(String nidStatus) {
        this.nidStatus = nidStatus;
    }

    public Boolean getIsForeign() {
        return this.isForeign;
    }

    public WalletUser isForeign(Boolean isForeign) {
        this.setIsForeign(isForeign);
        return this;
    }

    public void setIsForeign(Boolean isForeign) {
        this.isForeign = isForeign;
    }

    public Boolean getIsMobileConnectedWithNid() {
        return this.isMobileConnectedWithNid;
    }

    public WalletUser isMobileConnectedWithNid(Boolean isMobileConnectedWithNid) {
        this.setIsMobileConnectedWithNid(isMobileConnectedWithNid);
        return this;
    }

    public void setIsMobileConnectedWithNid(Boolean isMobileConnectedWithNid) {
        this.isMobileConnectedWithNid = isMobileConnectedWithNid;
    }

    public Boolean getIsKYCVerified() {
        return this.isKYCVerified;
    }

    public WalletUser isKYCVerified(Boolean isKYCVerified) {
        this.setIsKYCVerified(isKYCVerified);
        return this;
    }

    public void setIsKYCVerified(Boolean isKYCVerified) {
        this.isKYCVerified = isKYCVerified;
    }

    public WalletType getWalletType() {
        return this.walletType;
    }

    public WalletUser walletType(WalletType walletType) {
        this.setWalletType(walletType);
        return this;
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }

    public WalletStatus getWalletStatus() {
        return this.walletStatus;
    }

    public WalletUser walletStatus(WalletStatus walletStatus) {
        this.setWalletStatus(walletStatus);
        return this;
    }

    public void setWalletStatus(WalletStatus walletStatus) {
        this.walletStatus = walletStatus;
    }

    public byte[] getImageFile() {
        return this.imageFile;
    }

    public WalletUser imageFile(byte[] imageFile) {
        this.setImageFile(imageFile);
        return this;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileContentType() {
        return this.imageFileContentType;
    }

    public WalletUser imageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
        return this;
    }

    public void setImageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
    }

    public String getImageUrlFile() {
        return this.imageUrlFile;
    }

    public WalletUser imageUrlFile(String imageUrlFile) {
        this.setImageUrlFile(imageUrlFile);
        return this;
    }

    public void setImageUrlFile(String imageUrlFile) {
        this.imageUrlFile = imageUrlFile;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public WalletUser postCode(String postCode) {
        this.setPostCode(postCode);
        return this;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return this.address;
    }

    public WalletUser address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLat() {
        return this.lat;
    }

    public WalletUser lat(Float lat) {
        this.setLat(lat);
        return this;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return this.lng;
    }

    public WalletUser lng(Float lng) {
        this.setLng(lng);
        return this;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public String getNotes() {
        return this.notes;
    }

    public WalletUser notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBankAccountName() {
        return this.bankAccountName;
    }

    public WalletUser bankAccountName(String bankAccountName) {
        this.setBankAccountName(bankAccountName);
        return this;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNumber() {
        return this.bankAccountNumber;
    }

    public WalletUser bankAccountNumber(String bankAccountNumber) {
        this.setBankAccountNumber(bankAccountNumber);
        return this;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountIBAN() {
        return this.bankAccountIBAN;
    }

    public WalletUser bankAccountIBAN(String bankAccountIBAN) {
        this.setBankAccountIBAN(bankAccountIBAN);
        return this;
    }

    public void setBankAccountIBAN(String bankAccountIBAN) {
        this.bankAccountIBAN = bankAccountIBAN;
    }

    public String getBankAccountSWIFT() {
        return this.bankAccountSWIFT;
    }

    public WalletUser bankAccountSWIFT(String bankAccountSWIFT) {
        this.setBankAccountSWIFT(bankAccountSWIFT);
        return this;
    }

    public void setBankAccountSWIFT(String bankAccountSWIFT) {
        this.bankAccountSWIFT = bankAccountSWIFT;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WalletUser user(User user) {
        this.setUser(user);
        return this;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public WalletUser category(Category category) {
        this.setCategory(category);
        return this;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public WalletUser city(City city) {
        this.setCity(city);
        return this;
    }

    public WalletProfile getWalletProfile() {
        return this.walletProfile;
    }

    public void setWalletProfile(WalletProfile walletProfile) {
        this.walletProfile = walletProfile;
    }

    public WalletUser walletProfile(WalletProfile walletProfile) {
        this.setWalletProfile(walletProfile);
        return this;
    }

    public BankBranch getBankBranch() {
        return this.bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public WalletUser bankBranch(BankBranch bankBranch) {
        this.setBankBranch(bankBranch);
        return this;
    }

    public Set<WalletTransaction> getWalletTransactions() {
        return this.walletTransactions;
    }

    public void setWalletTransactions(Set<WalletTransaction> walletTransactions) {
        if (this.walletTransactions != null) {
            this.walletTransactions.forEach(i -> i.setWalletUser(null));
        }
        if (walletTransactions != null) {
            walletTransactions.forEach(i -> i.setWalletUser(this));
        }
        this.walletTransactions = walletTransactions;
    }

    public WalletUser walletTransactions(Set<WalletTransaction> walletTransactions) {
        this.setWalletTransactions(walletTransactions);
        return this;
    }

    public WalletUser addWalletTransaction(WalletTransaction walletTransaction) {
        this.walletTransactions.add(walletTransaction);
        walletTransaction.setWalletUser(this);
        return this;
    }

    public WalletUser removeWalletTransaction(WalletTransaction walletTransaction) {
        this.walletTransactions.remove(walletTransaction);
        walletTransaction.setWalletUser(null);
        return this;
    }

    public Set<Beneficiary> getBeneficiaries() {
        return this.beneficiaries;
    }

    public void setBeneficiaries(Set<Beneficiary> beneficiaries) {
        if (this.beneficiaries != null) {
            this.beneficiaries.forEach(i -> i.setWalletUser(null));
        }
        if (beneficiaries != null) {
            beneficiaries.forEach(i -> i.setWalletUser(this));
        }
        this.beneficiaries = beneficiaries;
    }

    public WalletUser beneficiaries(Set<Beneficiary> beneficiaries) {
        this.setBeneficiaries(beneficiaries);
        return this;
    }

    public WalletUser addBeneficiary(Beneficiary beneficiary) {
        this.beneficiaries.add(beneficiary);
        beneficiary.setWalletUser(this);
        return this;
    }

    public WalletUser removeBeneficiary(Beneficiary beneficiary) {
        this.beneficiaries.remove(beneficiary);
        beneficiary.setWalletUser(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletUser)) {
            return false;
        }
        return getId() != null && getId().equals(((WalletUser) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletUser{" +
            "id=" + getId() +
            ", arabicFirstName='" + getArabicFirstName() + "'" +
            ", arabicMiddleName='" + getArabicMiddleName() + "'" +
            ", arabicLastName='" + getArabicLastName() + "'" +
            ", englishFirstName='" + getEnglishFirstName() + "'" +
            ", englishMiddleName='" + getEnglishMiddleName() + "'" +
            ", englishLastName='" + getEnglishLastName() + "'" +
            ", commercialRegistryNo='" + getCommercialRegistryNo() + "'" +
            ", commercialLicenceNo='" + getCommercialLicenceNo() + "'" +
            ", commercialName='" + getCommercialName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", birthPlace='" + getBirthPlace() + "'" +
            ", nationalNo='" + getNationalNo() + "'" +
            ", registerNo='" + getRegisterNo() + "'" +
            ", gender='" + getGender() + "'" +
            ", idType='" + getIdType() + "'" +
            ", idNo='" + getIdNo() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", verifiedByEmailOtp='" + getVerifiedByEmailOtp() + "'" +
            ", verifiedByMobileOtp='" + getVerifiedByMobileOtp() + "'" +
            ", email='" + getEmail() + "'" +
            ", nidStatus='" + getNidStatus() + "'" +
            ", isForeign='" + getIsForeign() + "'" +
            ", isMobileConnectedWithNid='" + getIsMobileConnectedWithNid() + "'" +
            ", isKYCVerified='" + getIsKYCVerified() + "'" +
            ", walletType='" + getWalletType() + "'" +
            ", walletStatus='" + getWalletStatus() + "'" +
            ", imageFile='" + getImageFile() + "'" +
            ", imageFileContentType='" + getImageFileContentType() + "'" +
            ", imageUrlFile='" + getImageUrlFile() + "'" +
            ", postCode='" + getPostCode() + "'" +
            ", address='" + getAddress() + "'" +
            ", lat=" + getLat() +
            ", lng=" + getLng() +
            ", notes='" + getNotes() + "'" +
            ", bankAccountName='" + getBankAccountName() + "'" +
            ", bankAccountNumber='" + getBankAccountNumber() + "'" +
            ", bankAccountIBAN='" + getBankAccountIBAN() + "'" +
            ", bankAccountSWIFT='" + getBankAccountSWIFT() + "'" +
            "}";
    }
}
