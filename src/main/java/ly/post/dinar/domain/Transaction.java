package ly.post.dinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.TransactionStatus;
import ly.post.dinar.domain.enumeration.TransactionType;
import ly.post.dinar.domain.enumeration.WalletOwnerType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Transaction.
 */
@Table("transaction")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("transaction_reference")
    private String transactionReference;

    @Column("transaction_secret")
    private String transactionSecret;

    @Column("transaction_otp")
    private String transactionOtp;

    @Column("payment_type")
    private PaymentType paymentType;

    @Column("transaction_status")
    private TransactionStatus transactionStatus;

    @Column("transaction_type")
    private TransactionType transactionType;

    @Column("vendor_reference")
    private String vendorReference;

    @Column("vendor_message")
    private String vendorMessage;

    @Column("amount")
    private Float amount;

    @Column("fees")
    private Float fees;

    @Column("total")
    private Float total;

    @Column("notes")
    private String notes;

    @Column("sender_mobile_no")
    private String senderMobileNo;

    @Column("sender_name")
    private String senderName;

    @Column("sender_type")
    private WalletOwnerType senderType;

    @Column("sender_id_no")
    private String senderIdNo;

    @Column("sender_id")
    private Long senderId;

    @Column("receiver_name")
    private String receiverName;

    @Column("receiver_mobile_no")
    private String receiverMobileNo;

    @Column("receiver_type")
    private WalletOwnerType receiverType;

    @Column("receiver_id")
    private Long receiverId;

    @Column("receiver_id_no")
    private String receiverIdNo;

    @Column("created_by_user_id")
    private Long createdByUserId;

    @Transient
    @JsonIgnoreProperties(value = { "transaction" }, allowSetters = true)
    private Set<WalletTransaction> walletTransactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Transaction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionReference() {
        return this.transactionReference;
    }

    public Transaction transactionReference(String transactionReference) {
        this.setTransactionReference(transactionReference);
        return this;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getTransactionSecret() {
        return this.transactionSecret;
    }

    public Transaction transactionSecret(String transactionSecret) {
        this.setTransactionSecret(transactionSecret);
        return this;
    }

    public void setTransactionSecret(String transactionSecret) {
        this.transactionSecret = transactionSecret;
    }

    public String getTransactionOtp() {
        return this.transactionOtp;
    }

    public Transaction transactionOtp(String transactionOtp) {
        this.setTransactionOtp(transactionOtp);
        return this;
    }

    public void setTransactionOtp(String transactionOtp) {
        this.transactionOtp = transactionOtp;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public Transaction paymentType(PaymentType paymentType) {
        this.setPaymentType(paymentType);
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public TransactionStatus getTransactionStatus() {
        return this.transactionStatus;
    }

    public Transaction transactionStatus(TransactionStatus transactionStatus) {
        this.setTransactionStatus(transactionStatus);
        return this;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public Transaction transactionType(TransactionType transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getVendorReference() {
        return this.vendorReference;
    }

    public Transaction vendorReference(String vendorReference) {
        this.setVendorReference(vendorReference);
        return this;
    }

    public void setVendorReference(String vendorReference) {
        this.vendorReference = vendorReference;
    }

    public String getVendorMessage() {
        return this.vendorMessage;
    }

    public Transaction vendorMessage(String vendorMessage) {
        this.setVendorMessage(vendorMessage);
        return this;
    }

    public void setVendorMessage(String vendorMessage) {
        this.vendorMessage = vendorMessage;
    }

    public Float getAmount() {
        return this.amount;
    }

    public Transaction amount(Float amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getFees() {
        return this.fees;
    }

    public Transaction fees(Float fees) {
        this.setFees(fees);
        return this;
    }

    public void setFees(Float fees) {
        this.fees = fees;
    }

    public Float getTotal() {
        return this.total;
    }

    public Transaction total(Float total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getNotes() {
        return this.notes;
    }

    public Transaction notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSenderMobileNo() {
        return this.senderMobileNo;
    }

    public Transaction senderMobileNo(String senderMobileNo) {
        this.setSenderMobileNo(senderMobileNo);
        return this;
    }

    public void setSenderMobileNo(String senderMobileNo) {
        this.senderMobileNo = senderMobileNo;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public Transaction senderName(String senderName) {
        this.setSenderName(senderName);
        return this;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public WalletOwnerType getSenderType() {
        return this.senderType;
    }

    public Transaction senderType(WalletOwnerType senderType) {
        this.setSenderType(senderType);
        return this;
    }

    public void setSenderType(WalletOwnerType senderType) {
        this.senderType = senderType;
    }

    public String getSenderIdNo() {
        return this.senderIdNo;
    }

    public Transaction senderIdNo(String senderIdNo) {
        this.setSenderIdNo(senderIdNo);
        return this;
    }

    public void setSenderIdNo(String senderIdNo) {
        this.senderIdNo = senderIdNo;
    }

    public Long getSenderId() {
        return this.senderId;
    }

    public Transaction senderId(Long senderId) {
        this.setSenderId(senderId);
        return this;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getReceiverName() {
        return this.receiverName;
    }

    public Transaction receiverName(String receiverName) {
        this.setReceiverName(receiverName);
        return this;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobileNo() {
        return this.receiverMobileNo;
    }

    public Transaction receiverMobileNo(String receiverMobileNo) {
        this.setReceiverMobileNo(receiverMobileNo);
        return this;
    }

    public void setReceiverMobileNo(String receiverMobileNo) {
        this.receiverMobileNo = receiverMobileNo;
    }

    public WalletOwnerType getReceiverType() {
        return this.receiverType;
    }

    public Transaction receiverType(WalletOwnerType receiverType) {
        this.setReceiverType(receiverType);
        return this;
    }

    public void setReceiverType(WalletOwnerType receiverType) {
        this.receiverType = receiverType;
    }

    public Long getReceiverId() {
        return this.receiverId;
    }

    public Transaction receiverId(Long receiverId) {
        this.setReceiverId(receiverId);
        return this;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverIdNo() {
        return this.receiverIdNo;
    }

    public Transaction receiverIdNo(String receiverIdNo) {
        this.setReceiverIdNo(receiverIdNo);
        return this;
    }

    public void setReceiverIdNo(String receiverIdNo) {
        this.receiverIdNo = receiverIdNo;
    }

    public Long getCreatedByUserId() {
        return this.createdByUserId;
    }

    public Transaction createdByUserId(Long createdByUserId) {
        this.setCreatedByUserId(createdByUserId);
        return this;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Set<WalletTransaction> getWalletTransactions() {
        return this.walletTransactions;
    }

    public void setWalletTransactions(Set<WalletTransaction> walletTransactions) {
        if (this.walletTransactions != null) {
            this.walletTransactions.forEach(i -> i.setTransaction(null));
        }
        if (walletTransactions != null) {
            walletTransactions.forEach(i -> i.setTransaction(this));
        }
        this.walletTransactions = walletTransactions;
    }

    public Transaction walletTransactions(Set<WalletTransaction> walletTransactions) {
        this.setWalletTransactions(walletTransactions);
        return this;
    }

    public Transaction addWalletTransaction(WalletTransaction walletTransaction) {
        this.walletTransactions.add(walletTransaction);
        walletTransaction.setTransaction(this);
        return this;
    }

    public Transaction removeWalletTransaction(WalletTransaction walletTransaction) {
        this.walletTransactions.remove(walletTransaction);
        walletTransaction.setTransaction(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return getId() != null && getId().equals(((Transaction) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction{" +
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
