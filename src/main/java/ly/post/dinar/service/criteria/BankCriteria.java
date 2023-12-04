package ly.post.dinar.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.Bank} entity. This class is used
 * in {@link ly.post.dinar.web.rest.BankResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /banks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BankCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nameAr;

    private StringFilter namEn;

    private StringFilter swiftCode;

    private Boolean distinct;

    public BankCriteria() {}

    public BankCriteria(BankCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nameAr = other.nameAr == null ? null : other.nameAr.copy();
        this.namEn = other.namEn == null ? null : other.namEn.copy();
        this.swiftCode = other.swiftCode == null ? null : other.swiftCode.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BankCriteria copy() {
        return new BankCriteria(this);
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

    public StringFilter getNamEn() {
        return namEn;
    }

    public StringFilter namEn() {
        if (namEn == null) {
            namEn = new StringFilter();
        }
        return namEn;
    }

    public void setNamEn(StringFilter namEn) {
        this.namEn = namEn;
    }

    public StringFilter getSwiftCode() {
        return swiftCode;
    }

    public StringFilter swiftCode() {
        if (swiftCode == null) {
            swiftCode = new StringFilter();
        }
        return swiftCode;
    }

    public void setSwiftCode(StringFilter swiftCode) {
        this.swiftCode = swiftCode;
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
        final BankCriteria that = (BankCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(nameAr, that.nameAr) &&
            Objects.equals(namEn, that.namEn) &&
            Objects.equals(swiftCode, that.swiftCode) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameAr, namEn, swiftCode, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (nameAr != null ? "nameAr=" + nameAr + ", " : "") +
            (namEn != null ? "namEn=" + namEn + ", " : "") +
            (swiftCode != null ? "swiftCode=" + swiftCode + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
