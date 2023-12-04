package ly.post.dinar.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.IdType;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.Beneficiary} entity. This class is used
 * in {@link ly.post.dinar.web.rest.BeneficiaryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /beneficiaries?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BeneficiaryCriteria implements Serializable, Criteria {

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

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter arabicFirstName;

    private StringFilter arabicLastName;

    private StringFilter englishFirstName;

    private StringFilter englishLastName;

    private StringFilter bankAccountName;

    private StringFilter bankAccountNumber;

    private IdTypeFilter idType;

    private StringFilter idNo;

    private StringFilter mobileNo;

    private StringFilter email;

    private StringFilter notes;

    private BooleanFilter isVerified;

    private BooleanFilter hasTransferred;

    private StringFilter createdBy;

    private InstantFilter createdDate;

    private StringFilter lastModifiedBy;

    private InstantFilter lastModifiedDate;

    private LongFilter bankBranchId;

    private LongFilter walletUserId;

    private Boolean distinct;

    public BeneficiaryCriteria() {}

    public BeneficiaryCriteria(BeneficiaryCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.arabicFirstName = other.arabicFirstName == null ? null : other.arabicFirstName.copy();
        this.arabicLastName = other.arabicLastName == null ? null : other.arabicLastName.copy();
        this.englishFirstName = other.englishFirstName == null ? null : other.englishFirstName.copy();
        this.englishLastName = other.englishLastName == null ? null : other.englishLastName.copy();
        this.bankAccountName = other.bankAccountName == null ? null : other.bankAccountName.copy();
        this.bankAccountNumber = other.bankAccountNumber == null ? null : other.bankAccountNumber.copy();
        this.idType = other.idType == null ? null : other.idType.copy();
        this.idNo = other.idNo == null ? null : other.idNo.copy();
        this.mobileNo = other.mobileNo == null ? null : other.mobileNo.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.isVerified = other.isVerified == null ? null : other.isVerified.copy();
        this.hasTransferred = other.hasTransferred == null ? null : other.hasTransferred.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.lastModifiedDate = other.lastModifiedDate == null ? null : other.lastModifiedDate.copy();
        this.bankBranchId = other.bankBranchId == null ? null : other.bankBranchId.copy();
        this.walletUserId = other.walletUserId == null ? null : other.walletUserId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BeneficiaryCriteria copy() {
        return new BeneficiaryCriteria(this);
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

    public StringFilter getBankAccountName() {
        return bankAccountName;
    }

    public StringFilter bankAccountName() {
        if (bankAccountName == null) {
            bankAccountName = new StringFilter();
        }
        return bankAccountName;
    }

    public void setBankAccountName(StringFilter bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public StringFilter getBankAccountNumber() {
        return bankAccountNumber;
    }

    public StringFilter bankAccountNumber() {
        if (bankAccountNumber == null) {
            bankAccountNumber = new StringFilter();
        }
        return bankAccountNumber;
    }

    public void setBankAccountNumber(StringFilter bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
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

    public BooleanFilter getIsVerified() {
        return isVerified;
    }

    public BooleanFilter isVerified() {
        if (isVerified == null) {
            isVerified = new BooleanFilter();
        }
        return isVerified;
    }

    public void setIsVerified(BooleanFilter isVerified) {
        this.isVerified = isVerified;
    }

    public BooleanFilter getHasTransferred() {
        return hasTransferred;
    }

    public BooleanFilter hasTransferred() {
        if (hasTransferred == null) {
            hasTransferred = new BooleanFilter();
        }
        return hasTransferred;
    }

    public void setHasTransferred(BooleanFilter hasTransferred) {
        this.hasTransferred = hasTransferred;
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

    public LongFilter getBankBranchId() {
        return bankBranchId;
    }

    public LongFilter bankBranchId() {
        if (bankBranchId == null) {
            bankBranchId = new LongFilter();
        }
        return bankBranchId;
    }

    public void setBankBranchId(LongFilter bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public LongFilter getWalletUserId() {
        return walletUserId;
    }

    public LongFilter walletUserId() {
        if (walletUserId == null) {
            walletUserId = new LongFilter();
        }
        return walletUserId;
    }

    public void setWalletUserId(LongFilter walletUserId) {
        this.walletUserId = walletUserId;
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
        final BeneficiaryCriteria that = (BeneficiaryCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(arabicFirstName, that.arabicFirstName) &&
            Objects.equals(arabicLastName, that.arabicLastName) &&
            Objects.equals(englishFirstName, that.englishFirstName) &&
            Objects.equals(englishLastName, that.englishLastName) &&
            Objects.equals(bankAccountName, that.bankAccountName) &&
            Objects.equals(bankAccountNumber, that.bankAccountNumber) &&
            Objects.equals(idType, that.idType) &&
            Objects.equals(idNo, that.idNo) &&
            Objects.equals(mobileNo, that.mobileNo) &&
            Objects.equals(email, that.email) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(isVerified, that.isVerified) &&
            Objects.equals(hasTransferred, that.hasTransferred) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(bankBranchId, that.bankBranchId) &&
            Objects.equals(walletUserId, that.walletUserId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            arabicFirstName,
            arabicLastName,
            englishFirstName,
            englishLastName,
            bankAccountName,
            bankAccountNumber,
            idType,
            idNo,
            mobileNo,
            email,
            notes,
            isVerified,
            hasTransferred,
            createdBy,
            createdDate,
            lastModifiedBy,
            lastModifiedDate,
            bankBranchId,
            walletUserId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BeneficiaryCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (arabicFirstName != null ? "arabicFirstName=" + arabicFirstName + ", " : "") +
            (arabicLastName != null ? "arabicLastName=" + arabicLastName + ", " : "") +
            (englishFirstName != null ? "englishFirstName=" + englishFirstName + ", " : "") +
            (englishLastName != null ? "englishLastName=" + englishLastName + ", " : "") +
            (bankAccountName != null ? "bankAccountName=" + bankAccountName + ", " : "") +
            (bankAccountNumber != null ? "bankAccountNumber=" + bankAccountNumber + ", " : "") +
            (idType != null ? "idType=" + idType + ", " : "") +
            (idNo != null ? "idNo=" + idNo + ", " : "") +
            (mobileNo != null ? "mobileNo=" + mobileNo + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (isVerified != null ? "isVerified=" + isVerified + ", " : "") +
            (hasTransferred != null ? "hasTransferred=" + hasTransferred + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (lastModifiedDate != null ? "lastModifiedDate=" + lastModifiedDate + ", " : "") +
            (bankBranchId != null ? "bankBranchId=" + bankBranchId + ", " : "") +
            (walletUserId != null ? "walletUserId=" + walletUserId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
