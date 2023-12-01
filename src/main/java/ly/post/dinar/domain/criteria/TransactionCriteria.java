package ly.post.dinar.domain.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.TransactionStatus;
import ly.post.dinar.domain.enumeration.TransactionType;
import ly.post.dinar.domain.enumeration.WalletOwnerType;
import ly.post.dinar.domain.enumeration.WalletOwnerType;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.Transaction} entity. This class is used
 * in {@link ly.post.dinar.web.rest.TransactionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /transactions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransactionCriteria implements Serializable, Criteria {

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
     * Class for filtering TransactionStatus
     */
    public static class TransactionStatusFilter extends Filter<TransactionStatus> {

        public TransactionStatusFilter() {}

        public TransactionStatusFilter(TransactionStatusFilter filter) {
            super(filter);
        }

        @Override
        public TransactionStatusFilter copy() {
            return new TransactionStatusFilter(this);
        }
    }

    /**
     * Class for filtering TransactionType
     */
    public static class TransactionTypeFilter extends Filter<TransactionType> {

        public TransactionTypeFilter() {}

        public TransactionTypeFilter(TransactionTypeFilter filter) {
            super(filter);
        }

        @Override
        public TransactionTypeFilter copy() {
            return new TransactionTypeFilter(this);
        }
    }

    /**
     * Class for filtering WalletOwnerType
     */
    public static class WalletOwnerTypeFilter extends Filter<WalletOwnerType> {

        public WalletOwnerTypeFilter() {}

        public WalletOwnerTypeFilter(WalletOwnerTypeFilter filter) {
            super(filter);
        }

        @Override
        public WalletOwnerTypeFilter copy() {
            return new WalletOwnerTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter transactionReference;

    private StringFilter transactionSecret;

    private StringFilter transactionOtp;

    private PaymentTypeFilter paymentType;

    private TransactionStatusFilter transactionStatus;

    private TransactionTypeFilter transactionType;

    private StringFilter vendorReference;

    private StringFilter vendorMessage;

    private FloatFilter amount;

    private FloatFilter fees;

    private FloatFilter total;

    private StringFilter notes;

    private StringFilter senderMobileNo;

    private StringFilter senderName;

    private WalletOwnerTypeFilter senderType;

    private StringFilter senderIdNo;

    private LongFilter senderId;

    private StringFilter receiverName;

    private StringFilter receiverMobileNo;

    private WalletOwnerTypeFilter receiverType;

    private LongFilter receiverId;

    private StringFilter receiverIdNo;

    private LongFilter createdByUserId;

    private StringFilter createdBy;

    private InstantFilter createdDate;

    private StringFilter lastModifiedBy;

    private InstantFilter lastModifiedDate;

    private Boolean distinct;

    public TransactionCriteria() {}

    public TransactionCriteria(TransactionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.transactionReference = other.transactionReference == null ? null : other.transactionReference.copy();
        this.transactionSecret = other.transactionSecret == null ? null : other.transactionSecret.copy();
        this.transactionOtp = other.transactionOtp == null ? null : other.transactionOtp.copy();
        this.paymentType = other.paymentType == null ? null : other.paymentType.copy();
        this.transactionStatus = other.transactionStatus == null ? null : other.transactionStatus.copy();
        this.transactionType = other.transactionType == null ? null : other.transactionType.copy();
        this.vendorReference = other.vendorReference == null ? null : other.vendorReference.copy();
        this.vendorMessage = other.vendorMessage == null ? null : other.vendorMessage.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.fees = other.fees == null ? null : other.fees.copy();
        this.total = other.total == null ? null : other.total.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.senderMobileNo = other.senderMobileNo == null ? null : other.senderMobileNo.copy();
        this.senderName = other.senderName == null ? null : other.senderName.copy();
        this.senderType = other.senderType == null ? null : other.senderType.copy();
        this.senderIdNo = other.senderIdNo == null ? null : other.senderIdNo.copy();
        this.senderId = other.senderId == null ? null : other.senderId.copy();
        this.receiverName = other.receiverName == null ? null : other.receiverName.copy();
        this.receiverMobileNo = other.receiverMobileNo == null ? null : other.receiverMobileNo.copy();
        this.receiverType = other.receiverType == null ? null : other.receiverType.copy();
        this.receiverId = other.receiverId == null ? null : other.receiverId.copy();
        this.receiverIdNo = other.receiverIdNo == null ? null : other.receiverIdNo.copy();
        this.createdByUserId = other.createdByUserId == null ? null : other.createdByUserId.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.lastModifiedDate = other.lastModifiedDate == null ? null : other.lastModifiedDate.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TransactionCriteria copy() {
        return new TransactionCriteria(this);
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

    public StringFilter getTransactionReference() {
        return transactionReference;
    }

    public StringFilter transactionReference() {
        if (transactionReference == null) {
            transactionReference = new StringFilter();
        }
        return transactionReference;
    }

    public void setTransactionReference(StringFilter transactionReference) {
        this.transactionReference = transactionReference;
    }

    public StringFilter getTransactionSecret() {
        return transactionSecret;
    }

    public StringFilter transactionSecret() {
        if (transactionSecret == null) {
            transactionSecret = new StringFilter();
        }
        return transactionSecret;
    }

    public void setTransactionSecret(StringFilter transactionSecret) {
        this.transactionSecret = transactionSecret;
    }

    public StringFilter getTransactionOtp() {
        return transactionOtp;
    }

    public StringFilter transactionOtp() {
        if (transactionOtp == null) {
            transactionOtp = new StringFilter();
        }
        return transactionOtp;
    }

    public void setTransactionOtp(StringFilter transactionOtp) {
        this.transactionOtp = transactionOtp;
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

    public TransactionStatusFilter getTransactionStatus() {
        return transactionStatus;
    }

    public TransactionStatusFilter transactionStatus() {
        if (transactionStatus == null) {
            transactionStatus = new TransactionStatusFilter();
        }
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatusFilter transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionTypeFilter getTransactionType() {
        return transactionType;
    }

    public TransactionTypeFilter transactionType() {
        if (transactionType == null) {
            transactionType = new TransactionTypeFilter();
        }
        return transactionType;
    }

    public void setTransactionType(TransactionTypeFilter transactionType) {
        this.transactionType = transactionType;
    }

    public StringFilter getVendorReference() {
        return vendorReference;
    }

    public StringFilter vendorReference() {
        if (vendorReference == null) {
            vendorReference = new StringFilter();
        }
        return vendorReference;
    }

    public void setVendorReference(StringFilter vendorReference) {
        this.vendorReference = vendorReference;
    }

    public StringFilter getVendorMessage() {
        return vendorMessage;
    }

    public StringFilter vendorMessage() {
        if (vendorMessage == null) {
            vendorMessage = new StringFilter();
        }
        return vendorMessage;
    }

    public void setVendorMessage(StringFilter vendorMessage) {
        this.vendorMessage = vendorMessage;
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

    public FloatFilter getFees() {
        return fees;
    }

    public FloatFilter fees() {
        if (fees == null) {
            fees = new FloatFilter();
        }
        return fees;
    }

    public void setFees(FloatFilter fees) {
        this.fees = fees;
    }

    public FloatFilter getTotal() {
        return total;
    }

    public FloatFilter total() {
        if (total == null) {
            total = new FloatFilter();
        }
        return total;
    }

    public void setTotal(FloatFilter total) {
        this.total = total;
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

    public StringFilter getSenderMobileNo() {
        return senderMobileNo;
    }

    public StringFilter senderMobileNo() {
        if (senderMobileNo == null) {
            senderMobileNo = new StringFilter();
        }
        return senderMobileNo;
    }

    public void setSenderMobileNo(StringFilter senderMobileNo) {
        this.senderMobileNo = senderMobileNo;
    }

    public StringFilter getSenderName() {
        return senderName;
    }

    public StringFilter senderName() {
        if (senderName == null) {
            senderName = new StringFilter();
        }
        return senderName;
    }

    public void setSenderName(StringFilter senderName) {
        this.senderName = senderName;
    }

    public WalletOwnerTypeFilter getSenderType() {
        return senderType;
    }

    public WalletOwnerTypeFilter senderType() {
        if (senderType == null) {
            senderType = new WalletOwnerTypeFilter();
        }
        return senderType;
    }

    public void setSenderType(WalletOwnerTypeFilter senderType) {
        this.senderType = senderType;
    }

    public StringFilter getSenderIdNo() {
        return senderIdNo;
    }

    public StringFilter senderIdNo() {
        if (senderIdNo == null) {
            senderIdNo = new StringFilter();
        }
        return senderIdNo;
    }

    public void setSenderIdNo(StringFilter senderIdNo) {
        this.senderIdNo = senderIdNo;
    }

    public LongFilter getSenderId() {
        return senderId;
    }

    public LongFilter senderId() {
        if (senderId == null) {
            senderId = new LongFilter();
        }
        return senderId;
    }

    public void setSenderId(LongFilter senderId) {
        this.senderId = senderId;
    }

    public StringFilter getReceiverName() {
        return receiverName;
    }

    public StringFilter receiverName() {
        if (receiverName == null) {
            receiverName = new StringFilter();
        }
        return receiverName;
    }

    public void setReceiverName(StringFilter receiverName) {
        this.receiverName = receiverName;
    }

    public StringFilter getReceiverMobileNo() {
        return receiverMobileNo;
    }

    public StringFilter receiverMobileNo() {
        if (receiverMobileNo == null) {
            receiverMobileNo = new StringFilter();
        }
        return receiverMobileNo;
    }

    public void setReceiverMobileNo(StringFilter receiverMobileNo) {
        this.receiverMobileNo = receiverMobileNo;
    }

    public WalletOwnerTypeFilter getReceiverType() {
        return receiverType;
    }

    public WalletOwnerTypeFilter receiverType() {
        if (receiverType == null) {
            receiverType = new WalletOwnerTypeFilter();
        }
        return receiverType;
    }

    public void setReceiverType(WalletOwnerTypeFilter receiverType) {
        this.receiverType = receiverType;
    }

    public LongFilter getReceiverId() {
        return receiverId;
    }

    public LongFilter receiverId() {
        if (receiverId == null) {
            receiverId = new LongFilter();
        }
        return receiverId;
    }

    public void setReceiverId(LongFilter receiverId) {
        this.receiverId = receiverId;
    }

    public StringFilter getReceiverIdNo() {
        return receiverIdNo;
    }

    public StringFilter receiverIdNo() {
        if (receiverIdNo == null) {
            receiverIdNo = new StringFilter();
        }
        return receiverIdNo;
    }

    public void setReceiverIdNo(StringFilter receiverIdNo) {
        this.receiverIdNo = receiverIdNo;
    }

    public LongFilter getCreatedByUserId() {
        return createdByUserId;
    }

    public LongFilter createdByUserId() {
        if (createdByUserId == null) {
            createdByUserId = new LongFilter();
        }
        return createdByUserId;
    }

    public void setCreatedByUserId(LongFilter createdByUserId) {
        this.createdByUserId = createdByUserId;
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
        final TransactionCriteria that = (TransactionCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(transactionReference, that.transactionReference) &&
            Objects.equals(transactionSecret, that.transactionSecret) &&
            Objects.equals(transactionOtp, that.transactionOtp) &&
            Objects.equals(paymentType, that.paymentType) &&
            Objects.equals(transactionStatus, that.transactionStatus) &&
            Objects.equals(transactionType, that.transactionType) &&
            Objects.equals(vendorReference, that.vendorReference) &&
            Objects.equals(vendorMessage, that.vendorMessage) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(fees, that.fees) &&
            Objects.equals(total, that.total) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(senderMobileNo, that.senderMobileNo) &&
            Objects.equals(senderName, that.senderName) &&
            Objects.equals(senderType, that.senderType) &&
            Objects.equals(senderIdNo, that.senderIdNo) &&
            Objects.equals(senderId, that.senderId) &&
            Objects.equals(receiverName, that.receiverName) &&
            Objects.equals(receiverMobileNo, that.receiverMobileNo) &&
            Objects.equals(receiverType, that.receiverType) &&
            Objects.equals(receiverId, that.receiverId) &&
            Objects.equals(receiverIdNo, that.receiverIdNo) &&
            Objects.equals(createdByUserId, that.createdByUserId) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            transactionReference,
            transactionSecret,
            transactionOtp,
            paymentType,
            transactionStatus,
            transactionType,
            vendorReference,
            vendorMessage,
            amount,
            fees,
            total,
            notes,
            senderMobileNo,
            senderName,
            senderType,
            senderIdNo,
            senderId,
            receiverName,
            receiverMobileNo,
            receiverType,
            receiverId,
            receiverIdNo,
            createdByUserId,
            createdBy,
            createdDate,
            lastModifiedBy,
            lastModifiedDate,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (transactionReference != null ? "transactionReference=" + transactionReference + ", " : "") +
            (transactionSecret != null ? "transactionSecret=" + transactionSecret + ", " : "") +
            (transactionOtp != null ? "transactionOtp=" + transactionOtp + ", " : "") +
            (paymentType != null ? "paymentType=" + paymentType + ", " : "") +
            (transactionStatus != null ? "transactionStatus=" + transactionStatus + ", " : "") +
            (transactionType != null ? "transactionType=" + transactionType + ", " : "") +
            (vendorReference != null ? "vendorReference=" + vendorReference + ", " : "") +
            (vendorMessage != null ? "vendorMessage=" + vendorMessage + ", " : "") +
            (amount != null ? "amount=" + amount + ", " : "") +
            (fees != null ? "fees=" + fees + ", " : "") +
            (total != null ? "total=" + total + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (senderMobileNo != null ? "senderMobileNo=" + senderMobileNo + ", " : "") +
            (senderName != null ? "senderName=" + senderName + ", " : "") +
            (senderType != null ? "senderType=" + senderType + ", " : "") +
            (senderIdNo != null ? "senderIdNo=" + senderIdNo + ", " : "") +
            (senderId != null ? "senderId=" + senderId + ", " : "") +
            (receiverName != null ? "receiverName=" + receiverName + ", " : "") +
            (receiverMobileNo != null ? "receiverMobileNo=" + receiverMobileNo + ", " : "") +
            (receiverType != null ? "receiverType=" + receiverType + ", " : "") +
            (receiverId != null ? "receiverId=" + receiverId + ", " : "") +
            (receiverIdNo != null ? "receiverIdNo=" + receiverIdNo + ", " : "") +
            (createdByUserId != null ? "createdByUserId=" + createdByUserId + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (lastModifiedDate != null ? "lastModifiedDate=" + lastModifiedDate + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
