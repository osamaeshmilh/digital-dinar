package ly.post.dinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import ly.post.dinar.domain.enumeration.Gender;
import ly.post.dinar.domain.enumeration.IdType;
import ly.post.dinar.domain.enumeration.WalletOwnerStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Merchant.
 */
@Table("merchant")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Merchant extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("arabic_first_name")
    private String arabicFirstName;

    @Column("arabic_middle_name")
    private String arabicMiddleName;

    @Column("arabic_last_name")
    private String arabicLastName;

    @Column("english_first_name")
    private String englishFirstName;

    @Column("english_middle_name")
    private String englishMiddleName;

    @Column("english_last_name")
    private String englishLastName;

    @Column("commercial_registry_no")
    private String commercialRegistryNo;

    @Column("commercial_licence_no")
    private String commercialLicenceNo;

    @Column("birth_date")
    private LocalDate birthDate;

    @Column("birth_place")
    private String birthPlace;

    @Column("national_no")
    private String nationalNo;

    @Column("register_no")
    private String registerNo;

    @Column("gender")
    private Gender gender;

    @Column("id_type")
    private IdType idType;

    @Column("id_no")
    private String idNo;

    @NotNull(message = "must not be null")
    @Column("mobile_no")
    private String mobileNo;

    @Column("verified_by_email_otp")
    private Boolean verifiedByEmailOtp;

    @Column("verified_by_mobile_no_otp")
    private Boolean verifiedByMobileNoOtp;

    @Column("email")
    private String email;

    @Column("nid_status")
    private String nidStatus;

    @Column("is_foreign")
    private Boolean isForeign;

    @Column("is_mobile_connected_with_nid")
    private Boolean isMobileConnectedWithNid;

    @Column("wallet_owner_status")
    private WalletOwnerStatus walletOwnerStatus;

    @Column("image_file")
    private byte[] imageFile;

    @Column("image_file_content_type")
    private String imageFileContentType;

    @Column("image_url_file")
    private String imageUrlFile;

    @Column("post_code")
    private String postCode;

    @Column("address")
    private String address;

    @Column("lat")
    private Float lat;

    @Column("lng")
    private Float lng;

    @Column("notes")
    private String notes;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition

    @Transient
    private User user;

    @Transient
    private Category category;

    @Transient
    @JsonIgnoreProperties(value = { "country" }, allowSetters = true)
    private City city;

    @Transient
    private WalletProfile walletProfile;

    @Column("user_id")
    private Long userId;

    @Column("category_id")
    private Long categoryId;

    @Column("city_id")
    private Long cityId;

    @Column("wallet_profile_id")
    private Long walletProfileId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Merchant id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArabicFirstName() {
        return this.arabicFirstName;
    }

    public Merchant arabicFirstName(String arabicFirstName) {
        this.setArabicFirstName(arabicFirstName);
        return this;
    }

    public void setArabicFirstName(String arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
    }

    public String getArabicMiddleName() {
        return this.arabicMiddleName;
    }

    public Merchant arabicMiddleName(String arabicMiddleName) {
        this.setArabicMiddleName(arabicMiddleName);
        return this;
    }

    public void setArabicMiddleName(String arabicMiddleName) {
        this.arabicMiddleName = arabicMiddleName;
    }

    public String getArabicLastName() {
        return this.arabicLastName;
    }

    public Merchant arabicLastName(String arabicLastName) {
        this.setArabicLastName(arabicLastName);
        return this;
    }

    public void setArabicLastName(String arabicLastName) {
        this.arabicLastName = arabicLastName;
    }

    public String getEnglishFirstName() {
        return this.englishFirstName;
    }

    public Merchant englishFirstName(String englishFirstName) {
        this.setEnglishFirstName(englishFirstName);
        return this;
    }

    public void setEnglishFirstName(String englishFirstName) {
        this.englishFirstName = englishFirstName;
    }

    public String getEnglishMiddleName() {
        return this.englishMiddleName;
    }

    public Merchant englishMiddleName(String englishMiddleName) {
        this.setEnglishMiddleName(englishMiddleName);
        return this;
    }

    public void setEnglishMiddleName(String englishMiddleName) {
        this.englishMiddleName = englishMiddleName;
    }

    public String getEnglishLastName() {
        return this.englishLastName;
    }

    public Merchant englishLastName(String englishLastName) {
        this.setEnglishLastName(englishLastName);
        return this;
    }

    public void setEnglishLastName(String englishLastName) {
        this.englishLastName = englishLastName;
    }

    public String getCommercialRegistryNo() {
        return this.commercialRegistryNo;
    }

    public Merchant commercialRegistryNo(String commercialRegistryNo) {
        this.setCommercialRegistryNo(commercialRegistryNo);
        return this;
    }

    public void setCommercialRegistryNo(String commercialRegistryNo) {
        this.commercialRegistryNo = commercialRegistryNo;
    }

    public String getCommercialLicenceNo() {
        return this.commercialLicenceNo;
    }

    public Merchant commercialLicenceNo(String commercialLicenceNo) {
        this.setCommercialLicenceNo(commercialLicenceNo);
        return this;
    }

    public void setCommercialLicenceNo(String commercialLicenceNo) {
        this.commercialLicenceNo = commercialLicenceNo;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public Merchant birthDate(LocalDate birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public Merchant birthPlace(String birthPlace) {
        this.setBirthPlace(birthPlace);
        return this;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationalNo() {
        return this.nationalNo;
    }

    public Merchant nationalNo(String nationalNo) {
        this.setNationalNo(nationalNo);
        return this;
    }

    public void setNationalNo(String nationalNo) {
        this.nationalNo = nationalNo;
    }

    public String getRegisterNo() {
        return this.registerNo;
    }

    public Merchant registerNo(String registerNo) {
        this.setRegisterNo(registerNo);
        return this;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Merchant gender(Gender gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public IdType getIdType() {
        return this.idType;
    }

    public Merchant idType(IdType idType) {
        this.setIdType(idType);
        return this;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return this.idNo;
    }

    public Merchant idNo(String idNo) {
        this.setIdNo(idNo);
        return this;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Merchant mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Boolean getVerifiedByEmailOtp() {
        return this.verifiedByEmailOtp;
    }

    public Merchant verifiedByEmailOtp(Boolean verifiedByEmailOtp) {
        this.setVerifiedByEmailOtp(verifiedByEmailOtp);
        return this;
    }

    public void setVerifiedByEmailOtp(Boolean verifiedByEmailOtp) {
        this.verifiedByEmailOtp = verifiedByEmailOtp;
    }

    public Boolean getVerifiedByMobileNoOtp() {
        return this.verifiedByMobileNoOtp;
    }

    public Merchant verifiedByMobileNoOtp(Boolean verifiedByMobileNoOtp) {
        this.setVerifiedByMobileNoOtp(verifiedByMobileNoOtp);
        return this;
    }

    public void setVerifiedByMobileNoOtp(Boolean verifiedByMobileNoOtp) {
        this.verifiedByMobileNoOtp = verifiedByMobileNoOtp;
    }

    public String getEmail() {
        return this.email;
    }

    public Merchant email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNidStatus() {
        return this.nidStatus;
    }

    public Merchant nidStatus(String nidStatus) {
        this.setNidStatus(nidStatus);
        return this;
    }

    public void setNidStatus(String nidStatus) {
        this.nidStatus = nidStatus;
    }

    public Boolean getIsForeign() {
        return this.isForeign;
    }

    public Merchant isForeign(Boolean isForeign) {
        this.setIsForeign(isForeign);
        return this;
    }

    public void setIsForeign(Boolean isForeign) {
        this.isForeign = isForeign;
    }

    public Boolean getIsMobileConnectedWithNid() {
        return this.isMobileConnectedWithNid;
    }

    public Merchant isMobileConnectedWithNid(Boolean isMobileConnectedWithNid) {
        this.setIsMobileConnectedWithNid(isMobileConnectedWithNid);
        return this;
    }

    public void setIsMobileConnectedWithNid(Boolean isMobileConnectedWithNid) {
        this.isMobileConnectedWithNid = isMobileConnectedWithNid;
    }

    public WalletOwnerStatus getWalletOwnerStatus() {
        return this.walletOwnerStatus;
    }

    public Merchant walletOwnerStatus(WalletOwnerStatus walletOwnerStatus) {
        this.setWalletOwnerStatus(walletOwnerStatus);
        return this;
    }

    public void setWalletOwnerStatus(WalletOwnerStatus walletOwnerStatus) {
        this.walletOwnerStatus = walletOwnerStatus;
    }

    public byte[] getImageFile() {
        return this.imageFile;
    }

    public Merchant imageFile(byte[] imageFile) {
        this.setImageFile(imageFile);
        return this;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileContentType() {
        return this.imageFileContentType;
    }

    public Merchant imageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
        return this;
    }

    public void setImageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
    }

    public String getImageUrlFile() {
        return this.imageUrlFile;
    }

    public Merchant imageUrlFile(String imageUrlFile) {
        this.setImageUrlFile(imageUrlFile);
        return this;
    }

    public void setImageUrlFile(String imageUrlFile) {
        this.imageUrlFile = imageUrlFile;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public Merchant postCode(String postCode) {
        this.setPostCode(postCode);
        return this;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return this.address;
    }

    public Merchant address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLat() {
        return this.lat;
    }

    public Merchant lat(Float lat) {
        this.setLat(lat);
        return this;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return this.lng;
    }

    public Merchant lng(Float lng) {
        this.setLng(lng);
        return this;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public String getNotes() {
        return this.notes;
    }

    public Merchant notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Inherited createdBy methods
    public Merchant createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public Merchant createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public Merchant lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public Merchant lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user != null ? user.getId() : null;
    }

    public Merchant user(User user) {
        this.setUser(user);
        return this;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.categoryId = category != null ? category.getId() : null;
    }

    public Merchant category(Category category) {
        this.setCategory(category);
        return this;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
        this.cityId = city != null ? city.getId() : null;
    }

    public Merchant city(City city) {
        this.setCity(city);
        return this;
    }

    public WalletProfile getWalletProfile() {
        return this.walletProfile;
    }

    public void setWalletProfile(WalletProfile walletProfile) {
        this.walletProfile = walletProfile;
        this.walletProfileId = walletProfile != null ? walletProfile.getId() : null;
    }

    public Merchant walletProfile(WalletProfile walletProfile) {
        this.setWalletProfile(walletProfile);
        return this;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long user) {
        this.userId = user;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long category) {
        this.categoryId = category;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long city) {
        this.cityId = city;
    }

    public Long getWalletProfileId() {
        return this.walletProfileId;
    }

    public void setWalletProfileId(Long walletProfile) {
        this.walletProfileId = walletProfile;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Merchant)) {
            return false;
        }
        return getId() != null && getId().equals(((Merchant) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Merchant{" +
            "id=" + getId() +
            ", arabicFirstName='" + getArabicFirstName() + "'" +
            ", arabicMiddleName='" + getArabicMiddleName() + "'" +
            ", arabicLastName='" + getArabicLastName() + "'" +
            ", englishFirstName='" + getEnglishFirstName() + "'" +
            ", englishMiddleName='" + getEnglishMiddleName() + "'" +
            ", englishLastName='" + getEnglishLastName() + "'" +
            ", commercialRegistryNo='" + getCommercialRegistryNo() + "'" +
            ", commercialLicenceNo='" + getCommercialLicenceNo() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", birthPlace='" + getBirthPlace() + "'" +
            ", nationalNo='" + getNationalNo() + "'" +
            ", registerNo='" + getRegisterNo() + "'" +
            ", gender='" + getGender() + "'" +
            ", idType='" + getIdType() + "'" +
            ", idNo='" + getIdNo() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", verifiedByEmailOtp='" + getVerifiedByEmailOtp() + "'" +
            ", verifiedByMobileNoOtp='" + getVerifiedByMobileNoOtp() + "'" +
            ", email='" + getEmail() + "'" +
            ", nidStatus='" + getNidStatus() + "'" +
            ", isForeign='" + getIsForeign() + "'" +
            ", isMobileConnectedWithNid='" + getIsMobileConnectedWithNid() + "'" +
            ", walletOwnerStatus='" + getWalletOwnerStatus() + "'" +
            ", imageFile='" + getImageFile() + "'" +
            ", imageFileContentType='" + getImageFileContentType() + "'" +
            ", imageUrlFile='" + getImageUrlFile() + "'" +
            ", postCode='" + getPostCode() + "'" +
            ", address='" + getAddress() + "'" +
            ", lat=" + getLat() +
            ", lng=" + getLng() +
            ", notes='" + getNotes() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
