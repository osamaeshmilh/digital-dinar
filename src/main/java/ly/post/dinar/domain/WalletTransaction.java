package ly.post.dinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.WalletAction;
import ly.post.dinar.domain.enumeration.WalletType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A WalletTransaction.
 */
@Entity
@Table(name = "wallet_transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WalletTransaction extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_no")
    private String transactionNo;

    @Column(name = "amount")
    private Float amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "wallet_action")
    private WalletAction walletAction;

    @Column(name = "user_previous_transaction_no")
    private String userPreviousTransactionNo;

    @Column(name = "total_before_action")
    private Float totalBeforeAction;

    @Column(name = "total_after_action")
    private Float totalAfterAction;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "payment_reference")
    private String paymentReference;

    @Column(name = "notes")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "wallet_owner_type")
    private WalletType walletOwnerType;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "sender", "receiver", "walletTransactions" }, allowSetters = true)
    private Transaction transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "user", "category", "city", "walletProfile", "bankBranch", "walletTransactions", "beneficiaries" },
        allowSetters = true
    )
    private WalletUser walletUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public WalletTransaction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionNo() {
        return this.transactionNo;
    }

    public WalletTransaction transactionNo(String transactionNo) {
        this.setTransactionNo(transactionNo);
        return this;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Float getAmount() {
        return this.amount;
    }

    public WalletTransaction amount(Float amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public WalletAction getWalletAction() {
        return this.walletAction;
    }

    public WalletTransaction walletAction(WalletAction walletAction) {
        this.setWalletAction(walletAction);
        return this;
    }

    public void setWalletAction(WalletAction walletAction) {
        this.walletAction = walletAction;
    }

    public String getUserPreviousTransactionNo() {
        return this.userPreviousTransactionNo;
    }

    public WalletTransaction userPreviousTransactionNo(String userPreviousTransactionNo) {
        this.setUserPreviousTransactionNo(userPreviousTransactionNo);
        return this;
    }

    public void setUserPreviousTransactionNo(String userPreviousTransactionNo) {
        this.userPreviousTransactionNo = userPreviousTransactionNo;
    }

    public Float getTotalBeforeAction() {
        return this.totalBeforeAction;
    }

    public WalletTransaction totalBeforeAction(Float totalBeforeAction) {
        this.setTotalBeforeAction(totalBeforeAction);
        return this;
    }

    public void setTotalBeforeAction(Float totalBeforeAction) {
        this.totalBeforeAction = totalBeforeAction;
    }

    public Float getTotalAfterAction() {
        return this.totalAfterAction;
    }

    public WalletTransaction totalAfterAction(Float totalAfterAction) {
        this.setTotalAfterAction(totalAfterAction);
        return this;
    }

    public void setTotalAfterAction(Float totalAfterAction) {
        this.totalAfterAction = totalAfterAction;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public WalletTransaction paymentType(PaymentType paymentType) {
        this.setPaymentType(paymentType);
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentReference() {
        return this.paymentReference;
    }

    public WalletTransaction paymentReference(String paymentReference) {
        this.setPaymentReference(paymentReference);
        return this;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getNotes() {
        return this.notes;
    }

    public WalletTransaction notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public WalletType getWalletOwnerType() {
        return this.walletOwnerType;
    }

    public WalletTransaction walletOwnerType(WalletType walletOwnerType) {
        this.setWalletOwnerType(walletOwnerType);
        return this;
    }

    public void setWalletOwnerType(WalletType walletOwnerType) {
        this.walletOwnerType = walletOwnerType;
    }

    // Inherited createdBy methods
    public WalletTransaction createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public WalletTransaction createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public WalletTransaction lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public WalletTransaction lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public WalletTransaction transaction(Transaction transaction) {
        this.setTransaction(transaction);
        return this;
    }

    public WalletUser getWalletUser() {
        return this.walletUser;
    }

    public void setWalletUser(WalletUser walletUser) {
        this.walletUser = walletUser;
    }

    public WalletTransaction walletUser(WalletUser walletUser) {
        this.setWalletUser(walletUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletTransaction)) {
            return false;
        }
        return getId() != null && getId().equals(((WalletTransaction) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletTransaction{" +
            "id=" + getId() +
            ", transactionNo='" + getTransactionNo() + "'" +
            ", amount=" + getAmount() +
            ", walletAction='" + getWalletAction() + "'" +
            ", userPreviousTransactionNo='" + getUserPreviousTransactionNo() + "'" +
            ", totalBeforeAction=" + getTotalBeforeAction() +
            ", totalAfterAction=" + getTotalAfterAction() +
            ", paymentType='" + getPaymentType() + "'" +
            ", paymentReference='" + getPaymentReference() + "'" +
            ", notes='" + getNotes() + "'" +
            ", walletOwnerType='" + getWalletOwnerType() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
