package ly.post.dinar.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.WalletAction;
import ly.post.dinar.domain.enumeration.WalletType;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.WalletTransaction} entity. This class is used
 * in {@link ly.post.dinar.web.rest.WalletTransactionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /wallet-transactions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WalletTransactionCriteria implements Serializable, Criteria {

    /**
     * Class for filtering WalletAction
     */
    public static class WalletActionFilter extends Filter<WalletAction> {

        public WalletActionFilter() {}

        public WalletActionFilter(WalletActionFilter filter) {
            super(filter);
        }

        @Override
        public WalletActionFilter copy() {
            return new WalletActionFilter(this);
        }
    }

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

    /**
     * Class for filtering WalletType
     */
    public static class WalletTypeFilter extends Filter<WalletType> {

        public WalletTypeFilter() {}

        public WalletTypeFilter(WalletTypeFilter filter) {
            super(filter);
        }

        @Override
        public WalletTypeFilter copy() {
            return new WalletTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter transactionNo;

    private FloatFilter amount;

    private WalletActionFilter walletAction;

    private StringFilter userPreviousTransactionNo;

    private FloatFilter totalBeforeAction;

    private FloatFilter totalAfterAction;

    private PaymentTypeFilter paymentType;

    private StringFilter paymentReference;

    private StringFilter notes;

    private WalletTypeFilter walletOwnerType;

    private LongFilter transactionId;

    private LongFilter walletUserId;

    private Boolean distinct;

    public WalletTransactionCriteria() {}

    public WalletTransactionCriteria(WalletTransactionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.transactionNo = other.transactionNo == null ? null : other.transactionNo.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.walletAction = other.walletAction == null ? null : other.walletAction.copy();
        this.userPreviousTransactionNo = other.userPreviousTransactionNo == null ? null : other.userPreviousTransactionNo.copy();
        this.totalBeforeAction = other.totalBeforeAction == null ? null : other.totalBeforeAction.copy();
        this.totalAfterAction = other.totalAfterAction == null ? null : other.totalAfterAction.copy();
        this.paymentType = other.paymentType == null ? null : other.paymentType.copy();
        this.paymentReference = other.paymentReference == null ? null : other.paymentReference.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.walletOwnerType = other.walletOwnerType == null ? null : other.walletOwnerType.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.walletUserId = other.walletUserId == null ? null : other.walletUserId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public WalletTransactionCriteria copy() {
        return new WalletTransactionCriteria(this);
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

    public StringFilter getTransactionNo() {
        return transactionNo;
    }

    public StringFilter transactionNo() {
        if (transactionNo == null) {
            transactionNo = new StringFilter();
        }
        return transactionNo;
    }

    public void setTransactionNo(StringFilter transactionNo) {
        this.transactionNo = transactionNo;
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

    public WalletActionFilter getWalletAction() {
        return walletAction;
    }

    public WalletActionFilter walletAction() {
        if (walletAction == null) {
            walletAction = new WalletActionFilter();
        }
        return walletAction;
    }

    public void setWalletAction(WalletActionFilter walletAction) {
        this.walletAction = walletAction;
    }

    public StringFilter getUserPreviousTransactionNo() {
        return userPreviousTransactionNo;
    }

    public StringFilter userPreviousTransactionNo() {
        if (userPreviousTransactionNo == null) {
            userPreviousTransactionNo = new StringFilter();
        }
        return userPreviousTransactionNo;
    }

    public void setUserPreviousTransactionNo(StringFilter userPreviousTransactionNo) {
        this.userPreviousTransactionNo = userPreviousTransactionNo;
    }

    public FloatFilter getTotalBeforeAction() {
        return totalBeforeAction;
    }

    public FloatFilter totalBeforeAction() {
        if (totalBeforeAction == null) {
            totalBeforeAction = new FloatFilter();
        }
        return totalBeforeAction;
    }

    public void setTotalBeforeAction(FloatFilter totalBeforeAction) {
        this.totalBeforeAction = totalBeforeAction;
    }

    public FloatFilter getTotalAfterAction() {
        return totalAfterAction;
    }

    public FloatFilter totalAfterAction() {
        if (totalAfterAction == null) {
            totalAfterAction = new FloatFilter();
        }
        return totalAfterAction;
    }

    public void setTotalAfterAction(FloatFilter totalAfterAction) {
        this.totalAfterAction = totalAfterAction;
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

    public StringFilter getPaymentReference() {
        return paymentReference;
    }

    public StringFilter paymentReference() {
        if (paymentReference == null) {
            paymentReference = new StringFilter();
        }
        return paymentReference;
    }

    public void setPaymentReference(StringFilter paymentReference) {
        this.paymentReference = paymentReference;
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

    public WalletTypeFilter getWalletOwnerType() {
        return walletOwnerType;
    }

    public WalletTypeFilter walletOwnerType() {
        if (walletOwnerType == null) {
            walletOwnerType = new WalletTypeFilter();
        }
        return walletOwnerType;
    }

    public void setWalletOwnerType(WalletTypeFilter walletOwnerType) {
        this.walletOwnerType = walletOwnerType;
    }

    public LongFilter getTransactionId() {
        return transactionId;
    }

    public LongFilter transactionId() {
        if (transactionId == null) {
            transactionId = new LongFilter();
        }
        return transactionId;
    }

    public void setTransactionId(LongFilter transactionId) {
        this.transactionId = transactionId;
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
        final WalletTransactionCriteria that = (WalletTransactionCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(transactionNo, that.transactionNo) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(walletAction, that.walletAction) &&
            Objects.equals(userPreviousTransactionNo, that.userPreviousTransactionNo) &&
            Objects.equals(totalBeforeAction, that.totalBeforeAction) &&
            Objects.equals(totalAfterAction, that.totalAfterAction) &&
            Objects.equals(paymentType, that.paymentType) &&
            Objects.equals(paymentReference, that.paymentReference) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(walletOwnerType, that.walletOwnerType) &&
            Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(walletUserId, that.walletUserId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            transactionNo,
            amount,
            walletAction,
            userPreviousTransactionNo,
            totalBeforeAction,
            totalAfterAction,
            paymentType,
            paymentReference,
            notes,
            walletOwnerType,
            transactionId,
            walletUserId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletTransactionCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (transactionNo != null ? "transactionNo=" + transactionNo + ", " : "") +
            (amount != null ? "amount=" + amount + ", " : "") +
            (walletAction != null ? "walletAction=" + walletAction + ", " : "") +
            (userPreviousTransactionNo != null ? "userPreviousTransactionNo=" + userPreviousTransactionNo + ", " : "") +
            (totalBeforeAction != null ? "totalBeforeAction=" + totalBeforeAction + ", " : "") +
            (totalAfterAction != null ? "totalAfterAction=" + totalAfterAction + ", " : "") +
            (paymentType != null ? "paymentType=" + paymentType + ", " : "") +
            (paymentReference != null ? "paymentReference=" + paymentReference + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (walletOwnerType != null ? "walletOwnerType=" + walletOwnerType + ", " : "") +
            (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
            (walletUserId != null ? "walletUserId=" + walletUserId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
