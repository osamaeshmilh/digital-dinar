package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.TransactionStatus;
import ly.post.dinar.domain.enumeration.TransactionType;

/**
 * A DTO for the {@link ly.post.dinar.domain.Transaction} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransactionDTO implements Serializable {

    private Long id;

    private String transactionReference;

    private String transactionSecret;

    private String transactionOtp;

    private PaymentType paymentType;

    private TransactionStatus transactionStatus;

    private TransactionType transactionType;

    private String vendorReference;

    private String vendorMessage;

    private Float amount;

    private Float fees;

    private Float total;

    private String notes;

    private String senderMobileNo;

    private String senderName;

    private String senderIdNo;

    private String receiverName;

    private String receiverMobileNo;

    private String receiverIdNo;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private WalletUserDTO sender;

    private WalletUserDTO receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getTransactionSecret() {
        return transactionSecret;
    }

    public void setTransactionSecret(String transactionSecret) {
        this.transactionSecret = transactionSecret;
    }

    public String getTransactionOtp() {
        return transactionOtp;
    }

    public void setTransactionOtp(String transactionOtp) {
        this.transactionOtp = transactionOtp;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getVendorReference() {
        return vendorReference;
    }

    public void setVendorReference(String vendorReference) {
        this.vendorReference = vendorReference;
    }

    public String getVendorMessage() {
        return vendorMessage;
    }

    public void setVendorMessage(String vendorMessage) {
        this.vendorMessage = vendorMessage;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getFees() {
        return fees;
    }

    public void setFees(Float fees) {
        this.fees = fees;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSenderMobileNo() {
        return senderMobileNo;
    }

    public void setSenderMobileNo(String senderMobileNo) {
        this.senderMobileNo = senderMobileNo;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderIdNo() {
        return senderIdNo;
    }

    public void setSenderIdNo(String senderIdNo) {
        this.senderIdNo = senderIdNo;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobileNo() {
        return receiverMobileNo;
    }

    public void setReceiverMobileNo(String receiverMobileNo) {
        this.receiverMobileNo = receiverMobileNo;
    }

    public String getReceiverIdNo() {
        return receiverIdNo;
    }

    public void setReceiverIdNo(String receiverIdNo) {
        this.receiverIdNo = receiverIdNo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public WalletUserDTO getSender() {
        return sender;
    }

    public void setSender(WalletUserDTO sender) {
        this.sender = sender;
    }

    public WalletUserDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(WalletUserDTO receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionDTO)) {
            return false;
        }

        TransactionDTO transactionDTO = (TransactionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, transactionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionDTO{" +
            "id=" + getId() +
            ", transactionReference='" + getTransactionReference() + "'" +
            ", transactionSecret='" + getTransactionSecret() + "'" +
            ", transactionOtp='" + getTransactionOtp() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            ", transactionStatus='" + getTransactionStatus() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", vendorReference='" + getVendorReference() + "'" +
            ", vendorMessage='" + getVendorMessage() + "'" +
            ", amount=" + getAmount() +
            ", fees=" + getFees() +
            ", total=" + getTotal() +
            ", notes='" + getNotes() + "'" +
            ", senderMobileNo='" + getSenderMobileNo() + "'" +
            ", senderName='" + getSenderName() + "'" +
            ", senderIdNo='" + getSenderIdNo() + "'" +
            ", receiverName='" + getReceiverName() + "'" +
            ", receiverMobileNo='" + getReceiverMobileNo() + "'" +
            ", receiverIdNo='" + getReceiverIdNo() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", sender=" + getSender() +
            ", receiver=" + getReceiver() +
            "}";
    }
}
