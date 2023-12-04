package ly.post.dinar.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.VoucherCompany} entity. This class is used
 * in {@link ly.post.dinar.web.rest.VoucherCompanyResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /voucher-companies?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VoucherCompanyCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nameAr;

    private StringFilter nameEn;

    private StringFilter mobileNo;

    private StringFilter email;

    private StringFilter imageUrlFile;

    private StringFilter postCode;

    private StringFilter address;

    private StringFilter notes;

    private LongFilter userId;

    private Boolean distinct;

    public VoucherCompanyCriteria() {}

    public VoucherCompanyCriteria(VoucherCompanyCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nameAr = other.nameAr == null ? null : other.nameAr.copy();
        this.nameEn = other.nameEn == null ? null : other.nameEn.copy();
        this.mobileNo = other.mobileNo == null ? null : other.mobileNo.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.imageUrlFile = other.imageUrlFile == null ? null : other.imageUrlFile.copy();
        this.postCode = other.postCode == null ? null : other.postCode.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public VoucherCompanyCriteria copy() {
        return new VoucherCompanyCriteria(this);
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

    public StringFilter getNameAr() {
        return nameAr;
    }

    public StringFilter nameAr() {
        if (nameAr == null) {
            nameAr = new StringFilter();
        }
        return nameAr;
    }

    public void setNameAr(StringFilter nameAr) {
        this.nameAr = nameAr;
    }

    public StringFilter getNameEn() {
        return nameEn;
    }

    public StringFilter nameEn() {
        if (nameEn == null) {
            nameEn = new StringFilter();
        }
        return nameEn;
    }

    public void setNameEn(StringFilter nameEn) {
        this.nameEn = nameEn;
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
        final VoucherCompanyCriteria that = (VoucherCompanyCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(nameAr, that.nameAr) &&
            Objects.equals(nameEn, that.nameEn) &&
            Objects.equals(mobileNo, that.mobileNo) &&
            Objects.equals(email, that.email) &&
            Objects.equals(imageUrlFile, that.imageUrlFile) &&
            Objects.equals(postCode, that.postCode) &&
            Objects.equals(address, that.address) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameAr, nameEn, mobileNo, email, imageUrlFile, postCode, address, notes, userId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VoucherCompanyCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (nameAr != null ? "nameAr=" + nameAr + ", " : "") +
            (nameEn != null ? "nameEn=" + nameEn + ", " : "") +
            (mobileNo != null ? "mobileNo=" + mobileNo + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (imageUrlFile != null ? "imageUrlFile=" + imageUrlFile + ", " : "") +
            (postCode != null ? "postCode=" + postCode + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
