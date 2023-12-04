package ly.post.dinar.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.VoucherType} entity. This class is used
 * in {@link ly.post.dinar.web.rest.VoucherTypeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /voucher-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VoucherTypeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nameAr;

    private StringFilter nameEn;

    private StringFilter slug;

    private FloatFilter amount;

    private IntegerFilter menuOrder;

    private LongFilter voucherCompanyId;

    private Boolean distinct;

    public VoucherTypeCriteria() {}

    public VoucherTypeCriteria(VoucherTypeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nameAr = other.nameAr == null ? null : other.nameAr.copy();
        this.nameEn = other.nameEn == null ? null : other.nameEn.copy();
        this.slug = other.slug == null ? null : other.slug.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.menuOrder = other.menuOrder == null ? null : other.menuOrder.copy();
        this.voucherCompanyId = other.voucherCompanyId == null ? null : other.voucherCompanyId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public VoucherTypeCriteria copy() {
        return new VoucherTypeCriteria(this);
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

    public StringFilter getSlug() {
        return slug;
    }

    public StringFilter slug() {
        if (slug == null) {
            slug = new StringFilter();
        }
        return slug;
    }

    public void setSlug(StringFilter slug) {
        this.slug = slug;
    }

    public FloatFilter getAmount() {
        return amount;
    }

    public FloatFilter amount() {
        if (amount == null) {
            amount = new FloatFilter();
        }
        return amount;
    }

    public void setAmount(FloatFilter amount) {
        this.amount = amount;
    }

    public IntegerFilter getMenuOrder() {
        return menuOrder;
    }

    public IntegerFilter menuOrder() {
        if (menuOrder == null) {
            menuOrder = new IntegerFilter();
        }
        return menuOrder;
    }

    public void setMenuOrder(IntegerFilter menuOrder) {
        this.menuOrder = menuOrder;
    }

    public LongFilter getVoucherCompanyId() {
        return voucherCompanyId;
    }

    public LongFilter voucherCompanyId() {
        if (voucherCompanyId == null) {
            voucherCompanyId = new LongFilter();
        }
        return voucherCompanyId;
    }

    public void setVoucherCompanyId(LongFilter voucherCompanyId) {
        this.voucherCompanyId = voucherCompanyId;
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
        final VoucherTypeCriteria that = (VoucherTypeCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(nameAr, that.nameAr) &&
            Objects.equals(nameEn, that.nameEn) &&
            Objects.equals(slug, that.slug) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(menuOrder, that.menuOrder) &&
            Objects.equals(voucherCompanyId, that.voucherCompanyId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameAr, nameEn, slug, amount, menuOrder, voucherCompanyId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VoucherTypeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (nameAr != null ? "nameAr=" + nameAr + ", " : "") +
            (nameEn != null ? "nameEn=" + nameEn + ", " : "") +
            (slug != null ? "slug=" + slug + ", " : "") +
            (amount != null ? "amount=" + amount + ", " : "") +
            (menuOrder != null ? "menuOrder=" + menuOrder + ", " : "") +
            (voucherCompanyId != null ? "voucherCompanyId=" + voucherCompanyId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
