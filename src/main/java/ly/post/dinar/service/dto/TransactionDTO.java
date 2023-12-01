package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.TransactionStatus;
import ly.post.dinar.domain.enumeration.TransactionType;
import ly.post.dinar.domain.enumeration.WalletOwnerType;

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

    private WalletOwnerType senderType;

    private String senderIdNo;

    private Long senderId;

    private String receiverName;

    private String receiverMobileNo;

    private WalletOwnerType receiverType;

    private Long receiverId;

    private String receiverIdNo;

    private Long createdByUserId;

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

    public WalletOwnerType getSenderType() {
        return senderType;
    }

    public void setSenderType(WalletOwnerType senderType) {
        this.senderType = senderType;
    }

    public String getSenderIdNo() {
        return senderIdNo;
    }

    public void setSenderIdNo(String senderIdNo) {
        this.senderIdNo = senderIdNo;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
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

    public WalletOwnerType getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(WalletOwnerType receiverType) {
        this.receiverType = receiverType;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverIdNo() {
        return receiverIdNo;
    }

    public void setReceiverIdNo(String receiverIdNo) {
        this.receiverIdNo = receiverIdNo;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
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
            ", senderType='" + getSenderType() + "'" +
            ", senderIdNo='" + getSenderIdNo() + "'" +
            ", senderId=" + getSenderId() +
            ", receiverName='" + getReceiverName() + "'" +
            ", receiverMobileNo='" + getReceiverMobileNo() + "'" +
            ", receiverType='" + getReceiverType() + "'" +
            ", receiverId=" + getReceiverId() +
            ", receiverIdNo='" + getReceiverIdNo() + "'" +
            ", createdByUserId=" + getCreatedByUserId() +
            "}";
    }
}
