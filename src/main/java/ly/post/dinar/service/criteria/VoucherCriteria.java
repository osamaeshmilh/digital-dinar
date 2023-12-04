package ly.post.dinar.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.Voucher} entity. This class is used
 * in {@link ly.post.dinar.web.rest.VoucherResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /vouchers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VoucherCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private FloatFilter amount;

    private StringFilter serialNumber;

    private BooleanFilter isSold;

    private LongFilter sellTransactionId;

    private LongFilter voucherTypeId;

    private LongFilter voucherCompanyId;

    private Boolean distinct;

    public VoucherCriteria() {}

    public VoucherCriteria(VoucherCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.serialNumber = other.serialNumber == null ? null : other.serialNumber.copy();
        this.isSold = other.isSold == null ? null : other.isSold.copy();
        this.sellTransactionId = other.sellTransactionId == null ? null : other.sellTransactionId.copy();
        this.voucherTypeId = other.voucherTypeId == null ? null : other.voucherTypeId.copy();
        this.voucherCompanyId = other.voucherCompanyId == null ? null : other.voucherCompanyId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public VoucherCriteria copy() {
        return new VoucherCriteria(this);
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

    public StringFilter getCode() {
        return code;
    }

    public StringFilter code() {
        if (code == null) {
            code = new StringFilter();
        }
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
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

    public StringFilter getSerialNumber() {
        return serialNumber;
    }

    public StringFilter serialNumber() {
        if (serialNumber == null) {
            serialNumber = new StringFilter();
        }
        return serialNumber;
    }

    public void setSerialNumber(StringFilter serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BooleanFilter getIsSold() {
        return isSold;
    }

    public BooleanFilter isSold() {
        if (isSold == null) {
            isSold = new BooleanFilter();
        }
        return isSold;
    }

    public void setIsSold(BooleanFilter isSold) {
        this.isSold = isSold;
    }

    public LongFilter getSellTransactionId() {
        return sellTransactionId;
    }

    public LongFilter sellTransactionId() {
        if (sellTransactionId == null) {
            sellTransactionId = new LongFilter();
        }
        return sellTransactionId;
    }

    public void setSellTransactionId(LongFilter sellTransactionId) {
        this.sellTransactionId = sellTransactionId;
    }

    public LongFilter getVoucherTypeId() {
        return voucherTypeId;
    }

    public LongFilter voucherTypeId() {
        if (voucherTypeId == null) {
            voucherTypeId = new LongFilter();
        }
        return voucherTypeId;
    }

    public void setVoucherTypeId(LongFilter voucherTypeId) {
        this.voucherTypeId = voucherTypeId;
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
        final VoucherCriteria that = (VoucherCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(serialNumber, that.serialNumber) &&
            Objects.equals(isSold, that.isSold) &&
            Objects.equals(sellTransactionId, that.sellTransactionId) &&
            Objects.equals(voucherTypeId, that.voucherTypeId) &&
            Objects.equals(voucherCompanyId, that.voucherCompanyId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, amount, serialNumber, isSold, sellTransactionId, voucherTypeId, voucherCompanyId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VoucherCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (amount != null ? "amount=" + amount + ", " : "") +
            (serialNumber != null ? "serialNumber=" + serialNumber + ", " : "") +
            (isSold != null ? "isSold=" + isSold + ", " : "") +
            (sellTransactionId != null ? "sellTransactionId=" + sellTransactionId + ", " : "") +
            (voucherTypeId != null ? "voucherTypeId=" + voucherTypeId + ", " : "") +
            (voucherCompanyId != null ? "voucherCompanyId=" + voucherCompanyId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
