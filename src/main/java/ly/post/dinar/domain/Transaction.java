package ly.post.dinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.TransactionStatus;
import ly.post.dinar.domain.enumeration.TransactionType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Column(name = "transaction_secret")
    private String transactionSecret;

    @Column(name = "transaction_otp")
    private String transactionOtp;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "vendor_reference")
    private String vendorReference;

    @Column(name = "vendor_message")
    private String vendorMessage;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "fees")
    private Float fees;

    @Column(name = "total")
    private Float total;

    @Column(name = "notes")
    private String notes;

    @Column(name = "sender_mobile_no")
    private String senderMobileNo;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "sender_id_no")
    private String senderIdNo;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_mobile_no")
    private String receiverMobileNo;

    @Column(name = "receiver_id_no")
    private String receiverIdNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "user", "category", "city", "walletProfile", "bankBranch", "walletTransactions", "beneficiaries" },
        allowSetters = true
    )
    private WalletUser sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "user", "category", "city", "walletProfile", "bankBranch", "walletTransactions", "beneficiaries" },
        allowSetters = true
    )
    private WalletUser receiver;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "transaction", "walletUser" }, allowSetters = true)
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

    public WalletUser getSender() {
        return this.sender;
    }

    public void setSender(WalletUser walletUser) {
        this.sender = walletUser;
    }

    public Transaction sender(WalletUser walletUser) {
        this.setSender(walletUser);
        return this;
    }

    public WalletUser getReceiver() {
        return this.receiver;
    }

    public void setReceiver(WalletUser walletUser) {
        this.receiver = walletUser;
    }

    public Transaction receiver(WalletUser walletUser) {
        this.setReceiver(walletUser);
        return this;
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
            ", senderIdNo='" + getSenderIdNo() + "'" +
            ", receiverName='" + getReceiverName() + "'" +
            ", receiverMobileNo='" + getReceiverMobileNo() + "'" +
            ", receiverIdNo='" + getReceiverIdNo() + "'" +
            "}";
    }
}
