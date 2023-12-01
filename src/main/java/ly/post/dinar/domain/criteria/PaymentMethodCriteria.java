package ly.post.dinar.domain.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.PaymentType;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.PaymentMethod} entity. This class is used
 * in {@link ly.post.dinar.web.rest.PaymentMethodResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /payment-methods?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentMethodCriteria implements Serializable, Criteria {

    /**
     * Class for filtering PaymentType
     */
    public static class PaymentTypeFilter extends Filter<PaymentType> {

        public PaymentTypeFilter() {}

        public PaymentTypeFilter(PaymentTypeFilter filter) {
            super(filter);
        }

        @Override
        public PaymentTypeFilter copy() {
            return new PaymentTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter nameAr;

    private StringFilter nameEn;

    private IntegerFilter menuOrder;

    private StringFilter imageFileUrl;

    private StringFilter details;

    private FloatFilter feePercentage;

    private PaymentTypeFilter paymentType;

    private StringFilter notes;

    private Boolean distinct;

    public PaymentMethodCriteria() {}

    public PaymentMethodCriteria(PaymentMethodCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.nameAr = other.nameAr == null ? null : other.nameAr.copy();
        this.nameEn = other.nameEn == null ? null : other.nameEn.copy();
        this.menuOrder = other.menuOrder == null ? null : other.menuOrder.copy();
        this.imageFileUrl = other.imageFileUrl == null ? null : other.imageFileUrl.copy();
        this.details = other.details == null ? null : other.details.copy();
        this.feePercentage = other.feePercentage == null ? null : other.feePercentage.copy();
        this.paymentType = other.paymentType == null ? null : other.paymentType.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.distinct = other.distinct;
    }

    @Override
    public PaymentMethodCriteria copy() {
        return new PaymentMethodCriteria(this);
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

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
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

    public StringFilter getImageFileUrl() {
        return imageFileUrl;
    }

    public StringFilter imageFileUrl() {
        if (imageFileUrl == null) {
            imageFileUrl = new StringFilter();
        }
        return imageFileUrl;
    }

    public void setImageFileUrl(StringFilter imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

    public StringFilter getDetails() {
        return details;
    }

    public StringFilter details() {
        if (details == null) {
            details = new StringFilter();
        }
        return details;
    }

    public void setDetails(StringFilter details) {
        this.details = details;
    }

    public FloatFilter getFeePercentage() {
        return feePercentage;
    }

    public FloatFilter feePercentage() {
        if (feePercentage == null) {
            feePercentage = new FloatFilter();
        }
        return feePercentage;
    }

    public void setFeePercentage(FloatFilter feePercentage) {
        this.feePercentage = feePercentage;
    }

    public PaymentTypeFilter getPaymentType() {
        return paymentType;
    }

    public PaymentTypeFilter paymentType() {
        if (paymentType == null) {
            paymentType = new PaymentTypeFilter();
        }
        return paymentType;
    }

    public void setPaymentType(PaymentTypeFilter paymentType) {
        this.paymentType = paymentType;
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
        final PaymentMethodCriteria that = (PaymentMethodCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(nameAr, that.nameAr) &&
            Objects.equals(nameEn, that.nameEn) &&
            Objects.equals(menuOrder, that.menuOrder) &&
            Objects.equals(imageFileUrl, that.imageFileUrl) &&
            Objects.equals(details, that.details) &&
            Objects.equals(feePercentage, that.feePercentage) &&
            Objects.equals(paymentType, that.paymentType) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameAr, nameEn, menuOrder, imageFileUrl, details, feePercentage, paymentType, notes, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentMethodCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (nameAr != null ? "nameAr=" + nameAr + ", " : "") +
            (nameEn != null ? "nameEn=" + nameEn + ", " : "") +
            (menuOrder != null ? "menuOrder=" + menuOrder + ", " : "") +
            (imageFileUrl != null ? "imageFileUrl=" + imageFileUrl + ", " : "") +
            (details != null ? "details=" + details + ", " : "") +
            (feePercentage != null ? "feePercentage=" + feePercentage + ", " : "") +
            (paymentType != null ? "paymentType=" + paymentType + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
