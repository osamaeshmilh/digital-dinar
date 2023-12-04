package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.WalletAction;
import ly.post.dinar.domain.enumeration.WalletType;

/**
 * A DTO for the {@link ly.post.dinar.domain.WalletTransaction} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WalletTransactionDTO implements Serializable {

    private Long id;

    private String transactionNo;

    private Float amount;

    private WalletAction walletAction;

    private String userPreviousTransactionNo;

    private Float totalBeforeAction;

    private Float totalAfterAction;

    private PaymentType paymentType;

    private String paymentReference;

    private String notes;

    private WalletType walletOwnerType;

    private TransactionDTO transaction;

    private WalletUserDTO walletUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public WalletAction getWalletAction() {
        return walletAction;
    }

    public void setWalletAction(WalletAction walletAction) {
        this.walletAction = walletAction;
    }

    public String getUserPreviousTransactionNo() {
        return userPreviousTransactionNo;
    }

    public void setUserPreviousTransactionNo(String userPreviousTransactionNo) {
        this.userPreviousTransactionNo = userPreviousTransactionNo;
    }

    public Float getTotalBeforeAction() {
        return totalBeforeAction;
    }

    public void setTotalBeforeAction(Float totalBeforeAction) {
        this.totalBeforeAction = totalBeforeAction;
    }

    public Float getTotalAfterAction() {
        return totalAfterAction;
    }

    public void setTotalAfterAction(Float totalAfterAction) {
        this.totalAfterAction = totalAfterAction;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public WalletType getWalletOwnerType() {
        return walletOwnerType;
    }

    public void setWalletOwnerType(WalletType walletOwnerType) {
        this.walletOwnerType = walletOwnerType;
    }

    public TransactionDTO getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDTO transaction) {
        this.transaction = transaction;
    }

    public WalletUserDTO getWalletUser() {
        return walletUser;
    }

    public void setWalletUser(WalletUserDTO walletUser) {
        this.walletUser = walletUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletTransactionDTO)) {
            return false;
        }

        WalletTransactionDTO walletTransactionDTO = (WalletTransactionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, walletTransactionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletTransactionDTO{" +
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
            ", transaction=" + getTransaction() +
            ", walletUser=" + getWalletUser() +
            "}";
    }
}
