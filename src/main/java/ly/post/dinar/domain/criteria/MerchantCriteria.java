package ly.post.dinar.domain.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.Gender;
import ly.post.dinar.domain.enumeration.IdType;
import ly.post.dinar.domain.enumeration.WalletOwnerStatus;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.Merchant} entity. This class is used
 * in {@link ly.post.dinar.web.rest.MerchantResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /merchants?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MerchantCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Gender
     */
    public static class GenderFilter extends Filter<Gender> {

        public GenderFilter() {}

        public GenderFilter(GenderFilter filter) {
            super(filter);
        }

        @Override
        public GenderFilter copy() {
            return new GenderFilter(this);
        }
    }

    /**
     * Class for filtering IdType
     */
    public static class IdTypeFilter extends Filter<IdType> {

        public IdTypeFilter() {}

        public IdTypeFilter(IdTypeFilter filter) {
            super(filter);
        }

        @Override
        public IdTypeFilter copy() {
            return new IdTypeFilter(this);
        }
    }

    /**
     * Class for filtering WalletOwnerStatus
     */
    public static class WalletOwnerStatusFilter extends Filter<WalletOwnerStatus> {

        public WalletOwnerStatusFilter() {}

        public WalletOwnerStatusFilter(WalletOwnerStatusFilter filter) {
            super(filter);
        }

        @Override
        public WalletOwnerStatusFilter copy() {
            return new WalletOwnerStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter arabicFirstName;

    private StringFilter arabicMiddleName;

    private StringFilter arabicLastName;

    private StringFilter englishFirstName;

    private StringFilter englishMiddleName;

    private StringFilter englishLastName;

    private StringFilter commercialRegistryNo;

    private StringFilter commercialLicenceNo;

    private LocalDateFilter birthDate;

    private StringFilter birthPlace;

    private StringFilter nationalNo;

    private StringFilter registerNo;

    private GenderFilter gender;

    private IdTypeFilter idType;

    private StringFilter idNo;

    private StringFilter mobileNo;

    private BooleanFilter verifiedByEmailOtp;

    private BooleanFilter verifiedByMobileNoOtp;

    private StringFilter email;

    private StringFilter nidStatus;

    private BooleanFilter isForeign;

    private BooleanFilter isMobileConnectedWithNid;

    private WalletOwnerStatusFilter walletOwnerStatus;

    private StringFilter imageUrlFile;

    private StringFilter postCode;

    private StringFilter address;

    private FloatFilter lat;

    private FloatFilter lng;

    private StringFilter notes;

    private StringFilter createdBy;

    private InstantFilter createdDate;

    private StringFilter lastModifiedBy;

    private InstantFilter lastModifiedDate;

    private LongFilter userId;

    private LongFilter categoryId;

    private LongFilter cityId;

    private LongFilter walletProfileId;

    private Boolean distinct;

    public MerchantCriteria() {}

    public MerchantCriteria(MerchantCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.arabicFirstName = other.arabicFirstName == null ? null : other.arabicFirstName.copy();
        this.arabicMiddleName = other.arabicMiddleName == null ? null : other.arabicMiddleName.copy();
        this.arabicLastName = other.arabicLastName == null ? null : other.arabicLastName.copy();
        this.englishFirstName = other.englishFirstName == null ? null : other.englishFirstName.copy();
        this.englishMiddleName = other.englishMiddleName == null ? null : other.englishMiddleName.copy();
        this.englishLastName = other.englishLastName == null ? null : other.englishLastName.copy();
        this.commercialRegistryNo = other.commercialRegistryNo == null ? null : other.commercialRegistryNo.copy();
        this.commercialLicenceNo = other.commercialLicenceNo == null ? null : other.commercialLicenceNo.copy();
        this.birthDate = other.birthDate == null ? null : other.birthDate.copy();
        this.birthPlace = other.birthPlace == null ? null : other.birthPlace.copy();
        this.nationalNo = other.nationalNo == null ? null : other.nationalNo.copy();
        this.registerNo = other.registerNo == null ? null : other.registerNo.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.idType = other.idType == null ? null : other.idType.copy();
        this.idNo = other.idNo == null ? null : other.idNo.copy();
        this.mobileNo = other.mobileNo == null ? null : other.mobileNo.copy();
        this.verifiedByEmailOtp = other.verifiedByEmailOtp == null ? null : other.verifiedByEmailOtp.copy();
        this.verifiedByMobileNoOtp = other.verifiedByMobileNoOtp == null ? null : other.verifiedByMobileNoOtp.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.nidStatus = other.nidStatus == null ? null : other.nidStatus.copy();
        this.isForeign = other.isForeign == null ? null : other.isForeign.copy();
        this.isMobileConnectedWithNid = other.isMobileConnectedWithNid == null ? null : other.isMobileConnectedWithNid.copy();
        this.walletOwnerStatus = other.walletOwnerStatus == null ? null : other.walletOwnerStatus.copy();
        this.imageUrlFile = other.imageUrlFile == null ? null : other.imageUrlFile.copy();
        this.postCode = other.postCode == null ? null : other.postCode.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.lat = other.lat == null ? null : other.lat.copy();
        this.lng = other.lng == null ? null : other.lng.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.lastModifiedDate = other.lastModifiedDate == null ? null : other.lastModifiedDate.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.categoryId = other.categoryId == null ? null : other.categoryId.copy();
        this.cityId = other.cityId == null ? null : other.cityId.copy();
        this.walletProfileId = other.walletProfileId == null ? null : other.walletProfileId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public MerchantCriteria copy() {
        return new MerchantCriteria(this);
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

    public StringFilter getArabicFirstName() {
        return arabicFirstName;
    }

    public StringFilter arabicFirstName() {
        if (arabicFirstName == null) {
            arabicFirstName = new StringFilter();
        }
        return arabicFirstName;
    }

    public void setArabicFirstName(StringFilter arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
    }

    public StringFilter getArabicMiddleName() {
        return arabicMiddleName;
    }

    public StringFilter arabicMiddleName() {
        if (arabicMiddleName == null) {
            arabicMiddleName = new StringFilter();
        }
        return arabicMiddleName;
    }

    public void setArabicMiddleName(StringFilter arabicMiddleName) {
        this.arabicMiddleName = arabicMiddleName;
    }

    public StringFilter getArabicLastName() {
        return arabicLastName;
    }

    public StringFilter arabicLastName() {
        if (arabicLastName == null) {
            arabicLastName = new StringFilter();
        }
        return arabicLastName;
    }

    public void setArabicLastName(StringFilter arabicLastName) {
        this.arabicLastName = arabicLastName;
    }

    public StringFilter getEnglishFirstName() {
        return englishFirstName;
    }

    public StringFilter englishFirstName() {
        if (englishFirstName == null) {
            englishFirstName = new StringFilter();
        }
        return englishFirstName;
    }

    public void setEnglishFirstName(StringFilter englishFirstName) {
        this.englishFirstName = englishFirstName;
    }

    public StringFilter getEnglishMiddleName() {
        return englishMiddleName;
    }

    public StringFilter englishMiddleName() {
        if (englishMiddleName == null) {
            englishMiddleName = new StringFilter();
        }
        return englishMiddleName;
    }

    public void setEnglishMiddleName(StringFilter englishMiddleName) {
        this.englishMiddleName = englishMiddleName;
    }

    public StringFilter getEnglishLastName() {
        return englishLastName;
    }

    public StringFilter englishLastName() {
        if (englishLastName == null) {
            englishLastName = new StringFilter();
        }
        return englishLastName;
    }

    public void setEnglishLastName(StringFilter englishLastName) {
        this.englishLastName = englishLastName;
    }

    public StringFilter getCommercialRegistryNo() {
        return commercialRegistryNo;
    }

    public StringFilter commercialRegistryNo() {
        if (commercialRegistryNo == null) {
            commercialRegistryNo = new StringFilter();
        }
        return commercialRegistryNo;
    }

    public void setCommercialRegistryNo(StringFilter commercialRegistryNo) {
        this.commercialRegistryNo = commercialRegistryNo;
    }

    public StringFilter getCommercialLicenceNo() {
        return commercialLicenceNo;
    }

    public StringFilter commercialLicenceNo() {
        if (commercialLicenceNo == null) {
            commercialLicenceNo = new StringFilter();
        }
        return commercialLicenceNo;
    }

    public void setCommercialLicenceNo(StringFilter commercialLicenceNo) {
        this.commercialLicenceNo = commercialLicenceNo;
    }

    public LocalDateFilter getBirthDate() {
        return birthDate;
    }

    public LocalDateFilter birthDate() {
        if (birthDate == null) {
            birthDate = new LocalDateFilter();
        }
        return birthDate;
    }

    public void setBirthDate(LocalDateFilter birthDate) {
        this.birthDate = birthDate;
    }

    public StringFilter getBirthPlace() {
        return birthPlace;
    }

    public StringFilter birthPlace() {
        if (birthPlace == null) {
            birthPlace = new StringFilter();
        }
        return birthPlace;
    }

    public void setBirthPlace(StringFilter birthPlace) {
        this.birthPlace = birthPlace;
    }

    public StringFilter getNationalNo() {
        return nationalNo;
    }

    public StringFilter nationalNo() {
        if (nationalNo == null) {
            nationalNo = new StringFilter();
        }
        return nationalNo;
    }

    public void setNationalNo(StringFilter nationalNo) {
        this.nationalNo = nationalNo;
    }

    public StringFilter getRegisterNo() {
        return registerNo;
    }

    public StringFilter registerNo() {
        if (registerNo == null) {
            registerNo = new StringFilter();
        }
        return registerNo;
    }

    public void setRegisterNo(StringFilter registerNo) {
        this.registerNo = registerNo;
    }

    public GenderFilter getGender() {
        return gender;
    }

    public GenderFilter gender() {
        if (gender == null) {
            gender = new GenderFilter();
        }
        return gender;
    }

    public void setGender(GenderFilter gender) {
        this.gender = gender;
    }

    public IdTypeFilter getIdType() {
        return idType;
    }

    public IdTypeFilter idType() {
        if (idType == null) {
            idType = new IdTypeFilter();
        }
        return idType;
    }

    public void setIdType(IdTypeFilter idType) {
        this.idType = idType;
    }

    public StringFilter getIdNo() {
        return idNo;
    }

    public StringFilter idNo() {
        if (idNo == null) {
            idNo = new StringFilter();
        }
        return idNo;
    }

    public void setIdNo(StringFilter idNo) {
        this.idNo = idNo;
    }

    public StringFilter getMobileNo() {
        return mobileNo;
    }

    public StringFilter mobileNo() {
        if (mobileNo == null) {
            mobileNo = new StringFilter();
        }
        return mobileNo;
    }

    public void setMobileNo(StringFilter mobileNo) {
        this.mobileNo = mobileNo;
    }

    public BooleanFilter getVerifiedByEmailOtp() {
        return verifiedByEmailOtp;
    }

    public BooleanFilter verifiedByEmailOtp() {
        if (verifiedByEmailOtp == null) {
            verifiedByEmailOtp = new BooleanFilter();
        }
        return verifiedByEmailOtp;
    }

    public void setVerifiedByEmailOtp(BooleanFilter verifiedByEmailOtp) {
        this.verifiedByEmailOtp = verifiedByEmailOtp;
    }

    public BooleanFilter getVerifiedByMobileNoOtp() {
        return verifiedByMobileNoOtp;
    }

    public BooleanFilter verifiedByMobileNoOtp() {
        if (verifiedByMobileNoOtp == null) {
            verifiedByMobileNoOtp = new BooleanFilter();
        }
        return verifiedByMobileNoOtp;
    }

    public void setVerifiedByMobileNoOtp(BooleanFilter verifiedByMobileNoOtp) {
        this.verifiedByMobileNoOtp = verifiedByMobileNoOtp;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getNidStatus() {
        return nidStatus;
    }

    public StringFilter nidStatus() {
        if (nidStatus == null) {
            nidStatus = new StringFilter();
        }
        return nidStatus;
    }

    public void setNidStatus(StringFilter nidStatus) {
        this.nidStatus = nidStatus;
    }

    public BooleanFilter getIsForeign() {
        return isForeign;
    }

    public BooleanFilter isForeign() {
        if (isForeign == null) {
            isForeign = new BooleanFilter();
        }
        return isForeign;
    }

    public void setIsForeign(BooleanFilter isForeign) {
        this.isForeign = isForeign;
    }

    public BooleanFilter getIsMobileConnectedWithNid() {
        return isMobileConnectedWithNid;
    }

    public BooleanFilter isMobileConnectedWithNid() {
        if (isMobileConnectedWithNid == null) {
            isMobileConnectedWithNid = new BooleanFilter();
        }
        return isMobileConnectedWithNid;
    }

    public void setIsMobileConnectedWithNid(BooleanFilter isMobileConnectedWithNid) {
        this.isMobileConnectedWithNid = isMobileConnectedWithNid;
    }

    public WalletOwnerStatusFilter getWalletOwnerStatus() {
        return walletOwnerStatus;
    }

    public WalletOwnerStatusFilter walletOwnerStatus() {
        if (walletOwnerStatus == null) {
            walletOwnerStatus = new WalletOwnerStatusFilter();
        }
        return walletOwnerStatus;
    }

    public void setWalletOwnerStatus(WalletOwnerStatusFilter walletOwnerStatus) {
        this.walletOwnerStatus = walletOwnerStatus;
    }

    public StringFilter getImageUrlFile() {
        return imageUrlFile;
    }

    public StringFilter imageUrlFile() {
        if (imageUrlFile == null) {
            imageUrlFile = new StringFilter();
        }
        return imageUrlFile;
    }

    public void setImageUrlFile(StringFilter imageUrlFile) {
        this.imageUrlFile = imageUrlFile;
    }

    public StringFilter getPostCode() {
        return postCode;
    }

    public StringFilter postCode() {
        if (postCode == null) {
            postCode = new StringFilter();
        }
        return postCode;
    }

    public void setPostCode(StringFilter postCode) {
        this.postCode = postCode;
    }

    public StringFilter getAddress() {
        return address;
    }

    public StringFilter address() {
        if (address == null) {
            address = new StringFilter();
        }
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public FloatFilter getLat() {
        return lat;
    }

    public FloatFilter lat() {
        if (lat == null) {
            lat = new FloatFilter();
        }
        return lat;
    }

    public void setLat(FloatFilter lat) {
        this.lat = lat;
    }

    public FloatFilter getLng() {
        return lng;
    }

    public FloatFilter lng() {
        if (lng == null) {
            lng = new FloatFilter();
        }
        return lng;
    }

    public void setLng(FloatFilter lng) {
        this.lng = lng;
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

    public LongFilter getUserId() {
        return userId;
    }

    public LongFilter userId() {
        if (userId == null) {
            userId = new LongFilter();
        }
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getCategoryId() {
        return categoryId;
    }

    public LongFilter categoryId() {
        if (categoryId == null) {
            categoryId = new LongFilter();
        }
        return categoryId;
    }

    public void setCategoryId(LongFilter categoryId) {
        this.categoryId = categoryId;
    }

    public LongFilter getCityId() {
        return cityId;
    }

    public LongFilter cityId() {
        if (cityId == null) {
            cityId = new LongFilter();
        }
        return cityId;
    }

    public void setCityId(LongFilter cityId) {
        this.cityId = cityId;
    }

    public LongFilter getWalletProfileId() {
        return walletProfileId;
    }

    public LongFilter walletProfileId() {
        if (walletProfileId == null) {
            walletProfileId = new LongFilter();
        }
        return walletProfileId;
    }

    public void setWalletProfileId(LongFilter walletProfileId) {
        this.walletProfileId = walletProfileId;
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
        final MerchantCriteria that = (MerchantCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(arabicFirstName, that.arabicFirstName) &&
            Objects.equals(arabicMiddleName, that.arabicMiddleName) &&
            Objects.equals(arabicLastName, that.arabicLastName) &&
            Objects.equals(englishFirstName, that.englishFirstName) &&
            Objects.equals(englishMiddleName, that.englishMiddleName) &&
            Objects.equals(englishLastName, that.englishLastName) &&
            Objects.equals(commercialRegistryNo, that.commercialRegistryNo) &&
            Objects.equals(commercialLicenceNo, that.commercialLicenceNo) &&
            Objects.equals(birthDate, that.birthDate) &&
            Objects.equals(birthPlace, that.birthPlace) &&
            Objects.equals(nationalNo, that.nationalNo) &&
            Objects.equals(registerNo, that.registerNo) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(idType, that.idType) &&
            Objects.equals(idNo, that.idNo) &&
            Objects.equals(mobileNo, that.mobileNo) &&
            Objects.equals(verifiedByEmailOtp, that.verifiedByEmailOtp) &&
            Objects.equals(verifiedByMobileNoOtp, that.verifiedByMobileNoOtp) &&
            Objects.equals(email, that.email) &&
            Objects.equals(nidStatus, that.nidStatus) &&
            Objects.equals(isForeign, that.isForeign) &&
            Objects.equals(isMobileConnectedWithNid, that.isMobileConnectedWithNid) &&
            Objects.equals(walletOwnerStatus, that.walletOwnerStatus) &&
            Objects.equals(imageUrlFile, that.imageUrlFile) &&
            Objects.equals(postCode, that.postCode) &&
            Objects.equals(address, that.address) &&
            Objects.equals(lat, that.lat) &&
            Objects.equals(lng, that.lng) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(categoryId, that.categoryId) &&
            Objects.equals(cityId, that.cityId) &&
            Objects.equals(walletProfileId, that.walletProfileId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            arabicFirstName,
            arabicMiddleName,
            arabicLastName,
            englishFirstName,
            englishMiddleName,
            englishLastName,
            commercialRegistryNo,
            commercialLicenceNo,
            birthDate,
            birthPlace,
            nationalNo,
            registerNo,
            gender,
            idType,
            idNo,
            mobileNo,
            verifiedByEmailOtp,
            verifiedByMobileNoOtp,
            email,
            nidStatus,
            isForeign,
            isMobileConnectedWithNid,
            walletOwnerStatus,
            imageUrlFile,
            postCode,
            address,
            lat,
            lng,
            notes,
            createdBy,
            createdDate,
            lastModifiedBy,
            lastModifiedDate,
            userId,
            categoryId,
            cityId,
            walletProfileId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MerchantCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (arabicFirstName != null ? "arabicFirstName=" + arabicFirstName + ", " : "") +
            (arabicMiddleName != null ? "arabicMiddleName=" + arabicMiddleName + ", " : "") +
            (arabicLastName != null ? "arabicLastName=" + arabicLastName + ", " : "") +
            (englishFirstName != null ? "englishFirstName=" + englishFirstName + ", " : "") +
            (englishMiddleName != null ? "englishMiddleName=" + englishMiddleName + ", " : "") +
            (englishLastName != null ? "englishLastName=" + englishLastName + ", " : "") +
            (commercialRegistryNo != null ? "commercialRegistryNo=" + commercialRegistryNo + ", " : "") +
            (commercialLicenceNo != null ? "commercialLicenceNo=" + commercialLicenceNo + ", " : "") +
            (birthDate != null ? "birthDate=" + birthDate + ", " : "") +
            (birthPlace != null ? "birthPlace=" + birthPlace + ", " : "") +
            (nationalNo != null ? "nationalNo=" + nationalNo + ", " : "") +
            (registerNo != null ? "registerNo=" + registerNo + ", " : "") +
            (gender != null ? "gender=" + gender + ", " : "") +
            (idType != null ? "idType=" + idType + ", " : "") +
            (idNo != null ? "idNo=" + idNo + ", " : "") +
            (mobileNo != null ? "mobileNo=" + mobileNo + ", " : "") +
            (verifiedByEmailOtp != null ? "verifiedByEmailOtp=" + verifiedByEmailOtp + ", " : "") +
            (verifiedByMobileNoOtp != null ? "verifiedByMobileNoOtp=" + verifiedByMobileNoOtp + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (nidStatus != null ? "nidStatus=" + nidStatus + ", " : "") +
            (isForeign != null ? "isForeign=" + isForeign + ", " : "") +
            (isMobileConnectedWithNid != null ? "isMobileConnectedWithNid=" + isMobileConnectedWithNid + ", " : "") +
            (walletOwnerStatus != null ? "walletOwnerStatus=" + walletOwnerStatus + ", " : "") +
            (imageUrlFile != null ? "imageUrlFile=" + imageUrlFile + ", " : "") +
            (postCode != null ? "postCode=" + postCode + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (lat != null ? "lat=" + lat + ", " : "") +
            (lng != null ? "lng=" + lng + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (lastModifiedDate != null ? "lastModifiedDate=" + lastModifiedDate + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
            (cityId != null ? "cityId=" + cityId + ", " : "") +
            (walletProfileId != null ? "walletProfileId=" + walletProfileId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
