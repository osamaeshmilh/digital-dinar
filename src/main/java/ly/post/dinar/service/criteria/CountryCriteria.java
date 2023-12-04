package ly.post.dinar.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.Country} entity. This class is used
 * in {@link ly.post.dinar.web.rest.CountryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /countries?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CountryCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nameAr;

    private StringFilter nameEn;

    private StringFilter iso2;

    private StringFilter iso3;

    private IntegerFilter isoNo;

    private Boolean distinct;

    public CountryCriteria() {}

    public CountryCriteria(CountryCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nameAr = other.nameAr == null ? null : other.nameAr.copy();
        this.nameEn = other.nameEn == null ? null : other.nameEn.copy();
        this.iso2 = other.iso2 == null ? null : other.iso2.copy();
        this.iso3 = other.iso3 == null ? null : other.iso3.copy();
        this.isoNo = other.isoNo == null ? null : other.isoNo.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CountryCriteria copy() {
        return new CountryCriteria(this);
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

    public StringFilter getIso2() {
        return iso2;
    }

    public StringFilter iso2() {
        if (iso2 == null) {
            iso2 = new StringFilter();
        }
        return iso2;
    }

    public void setIso2(StringFilter iso2) {
        this.iso2 = iso2;
    }

    public StringFilter getIso3() {
        return iso3;
    }

    public StringFilter iso3() {
        if (iso3 == null) {
            iso3 = new StringFilter();
        }
        return iso3;
    }

    public void setIso3(StringFilter iso3) {
        this.iso3 = iso3;
    }

    public IntegerFilter getIsoNo() {
        return isoNo;
    }

    public IntegerFilter isoNo() {
        if (isoNo == null) {
            isoNo = new IntegerFilter();
        }
        return isoNo;
    }

    public void setIsoNo(IntegerFilter isoNo) {
        this.isoNo = isoNo;
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
        final CountryCriteria that = (CountryCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(nameAr, that.nameAr) &&
            Objects.equals(nameEn, that.nameEn) &&
            Objects.equals(iso2, that.iso2) &&
            Objects.equals(iso3, that.iso3) &&
            Objects.equals(isoNo, that.isoNo) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameAr, nameEn, iso2, iso3, isoNo, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CountryCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (nameAr != null ? "nameAr=" + nameAr + ", " : "") +
            (nameEn != null ? "nameEn=" + nameEn + ", " : "") +
            (iso2 != null ? "iso2=" + iso2 + ", " : "") +
            (iso3 != null ? "iso3=" + iso3 + ", " : "") +
            (isoNo != null ? "isoNo=" + isoNo + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
