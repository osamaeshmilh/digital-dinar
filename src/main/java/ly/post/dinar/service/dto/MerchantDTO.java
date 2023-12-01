package ly.post.dinar.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.Gender;
import ly.post.dinar.domain.enumeration.IdType;
import ly.post.dinar.domain.enumeration.WalletOwnerStatus;

/**
 * A DTO for the {@link ly.post.dinar.domain.Merchant} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MerchantDTO implements Serializable {

    private Long id;

    private String arabicFirstName;

    private String arabicMiddleName;

    private String arabicLastName;

    private String englishFirstName;

    private String englishMiddleName;

    private String englishLastName;

    private String commercialRegistryNo;

    private String commercialLicenceNo;

    private LocalDate birthDate;

    private String birthPlace;

    private String nationalNo;

    private String registerNo;

    private Gender gender;

    private IdType idType;

    private String idNo;

    @NotNull(message = "must not be null")
    private String mobileNo;

    private Boolean verifiedByEmailOtp;

    private Boolean verifiedByMobileNoOtp;

    private String email;

    private String nidStatus;

    private Boolean isForeign;

    private Boolean isMobileConnectedWithNid;

    private WalletOwnerStatus walletOwnerStatus;

    @Lob
    private byte[] imageFile;

    private String imageFileContentType;
    private String imageUrlFile;

    private String postCode;

    private String address;

    private Float lat;

    private Float lng;

    private String notes;

    private UserDTO user;

    private CategoryDTO category;

    private CityDTO city;

    private WalletProfileDTO walletProfile;

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

    public String getArabicMiddleName() {
        return arabicMiddleName;
    }

    public void setArabicMiddleName(String arabicMiddleName) {
        this.arabicMiddleName = arabicMiddleName;
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

    public String getEnglishMiddleName() {
        return englishMiddleName;
    }

    public void setEnglishMiddleName(String englishMiddleName) {
        this.englishMiddleName = englishMiddleName;
    }

    public String getEnglishLastName() {
        return englishLastName;
    }

    public void setEnglishLastName(String englishLastName) {
        this.englishLastName = englishLastName;
    }

    public String getCommercialRegistryNo() {
        return commercialRegistryNo;
    }

    public void setCommercialRegistryNo(String commercialRegistryNo) {
        this.commercialRegistryNo = commercialRegistryNo;
    }

    public String getCommercialLicenceNo() {
        return commercialLicenceNo;
    }

    public void setCommercialLicenceNo(String commercialLicenceNo) {
        this.commercialLicenceNo = commercialLicenceNo;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationalNo() {
        return nationalNo;
    }

    public void setNationalNo(String nationalNo) {
        this.nationalNo = nationalNo;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public Boolean getVerifiedByEmailOtp() {
        return verifiedByEmailOtp;
    }

    public void setVerifiedByEmailOtp(Boolean verifiedByEmailOtp) {
        this.verifiedByEmailOtp = verifiedByEmailOtp;
    }

    public Boolean getVerifiedByMobileNoOtp() {
        return verifiedByMobileNoOtp;
    }

    public void setVerifiedByMobileNoOtp(Boolean verifiedByMobileNoOtp) {
        this.verifiedByMobileNoOtp = verifiedByMobileNoOtp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNidStatus() {
        return nidStatus;
    }

    public void setNidStatus(String nidStatus) {
        this.nidStatus = nidStatus;
    }

    public Boolean getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(Boolean isForeign) {
        this.isForeign = isForeign;
    }

    public Boolean getIsMobileConnectedWithNid() {
        return isMobileConnectedWithNid;
    }

    public void setIsMobileConnectedWithNid(Boolean isMobileConnectedWithNid) {
        this.isMobileConnectedWithNid = isMobileConnectedWithNid;
    }

    public WalletOwnerStatus getWalletOwnerStatus() {
        return walletOwnerStatus;
    }

    public void setWalletOwnerStatus(WalletOwnerStatus walletOwnerStatus) {
        this.walletOwnerStatus = walletOwnerStatus;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileContentType() {
        return imageFileContentType;
    }

    public void setImageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
    }

    public String getImageUrlFile() {
        return imageUrlFile;
    }

    public void setImageUrlFile(String imageUrlFile) {
        this.imageUrlFile = imageUrlFile;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public WalletProfileDTO getWalletProfile() {
        return walletProfile;
    }

    public void setWalletProfile(WalletProfileDTO walletProfile) {
        this.walletProfile = walletProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MerchantDTO)) {
            return false;
        }

        MerchantDTO merchantDTO = (MerchantDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, merchantDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MerchantDTO{" +
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
            ", imageUrlFile='" + getImageUrlFile() + "'" +
            ", postCode='" + getPostCode() + "'" +
            ", address='" + getAddress() + "'" +
            ", lat=" + getLat() +
            ", lng=" + getLng() +
            ", notes='" + getNotes() + "'" +
            ", user=" + getUser() +
            ", category=" + getCategory() +
            ", city=" + getCity() +
            ", walletProfile=" + getWalletProfile() +
            "}";
    }
}
