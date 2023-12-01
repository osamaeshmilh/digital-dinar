package ly.post.dinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.WalletAction;
import ly.post.dinar.domain.enumeration.WalletOwnerType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A WalletTransaction.
 */
@Table("wallet_transaction")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WalletTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("transaction_no")
    private String transactionNo;

    @Column("amount")
    private Float amount;

    @Column("wallet_action")
    private WalletAction walletAction;

    @Column("user_previous_transaction_no")
    private String userPreviousTransactionNo;

    @Column("total_before_action")
    private Float totalBeforeAction;

    @Column("total_after_action")
    private Float totalAfterAction;

    @Column("payment_type")
    private PaymentType paymentType;

    @Column("payment_reference")
    private String paymentReference;

    @Column("notes")
    private String notes;

    @Column("owner_id")
    private Long ownerId;

    @Column("wallet_owner_type")
    private WalletOwnerType walletOwnerType;

    @Transient
    @JsonIgnoreProperties(value = { "walletTransactions" }, allowSetters = true)
    private Transaction transaction;

    @Column("transaction_id")
    private Long transactionId;

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

    public Long getOwnerId() {
        return this.ownerId;
    }

    public WalletTransaction ownerId(Long ownerId) {
        this.setOwnerId(ownerId);
        return this;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public WalletOwnerType getWalletOwnerType() {
        return this.walletOwnerType;
    }

    public WalletTransaction walletOwnerType(WalletOwnerType walletOwnerType) {
        this.setWalletOwnerType(walletOwnerType);
        return this;
    }

    public void setWalletOwnerType(WalletOwnerType walletOwnerType) {
        this.walletOwnerType = walletOwnerType;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        this.transactionId = transaction != null ? transaction.getId() : null;
    }

    public WalletTransaction transaction(Transaction transaction) {
        this.setTransaction(transaction);
        return this;
    }

    public Long getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(Long transaction) {
        this.transactionId = transaction;
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
            ", ownerId=" + getOwnerId() +
            ", walletOwnerType='" + getWalletOwnerType() + "'" +
            "}";
    }
}
