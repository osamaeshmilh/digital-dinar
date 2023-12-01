package ly.post.dinar.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A WalletProfile.
 */
@Table("wallet_profile")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WalletProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("name_ar")
    private String nameAr;

    @Column("name_en")
    private String nameEn;

    @Column("has_transfer_amount_limit_per_transaction")
    private Boolean hasTransferAmountLimitPerTransaction;

    @Column("transfer_amount_limit_per_transaction")
    private Float transferAmountLimitPerTransaction;

    @Column("has_daily_transfer_amount_limit")
    private Boolean hasDailyTransferAmountLimit;

    @Column("daily_transfer_amount_limit")
    private Float dailyTransferAmountLimit;

    @Column("can_transfer_to_consumer_wallet")
    private Boolean canTransferToConsumerWallet;

    @Column("can_transfer_to_merchant_wallet")
    private Boolean canTransferToMerchantWallet;

    @Column("can_transfer_to_consumer")
    private Boolean canTransferToConsumer;

    @Column("can_transfer_to_bank")
    private Boolean canTransferToBank;

    @Column("can_buy_vouchers")
    private Boolean canBuyVouchers;

    @Column("transfer_to_consumer_wallet_fees")
    private Boolean transferToConsumerWalletFees;

    @Column("transfer_to_merchant_wallet_fees")
    private Boolean transferToMerchantWalletFees;

    @Column("transfer_to_consumer_fees")
    private Boolean transferToConsumerFees;

    @Column("transfer_to_bank_fees")
    private Boolean transferToBankFees;

    @Column("buy_vouchers_fees")
    private Boolean buyVouchersFees;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public WalletProfile id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public WalletProfile nameAr(String nameAr) {
        this.setNameAr(nameAr);
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public WalletProfile nameEn(String nameEn) {
        this.setNameEn(nameEn);
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Boolean getHasTransferAmountLimitPerTransaction() {
        return this.hasTransferAmountLimitPerTransaction;
    }

    public WalletProfile hasTransferAmountLimitPerTransaction(Boolean hasTransferAmountLimitPerTransaction) {
        this.setHasTransferAmountLimitPerTransaction(hasTransferAmountLimitPerTransaction);
        return this;
    }

    public void setHasTransferAmountLimitPerTransaction(Boolean hasTransferAmountLimitPerTransaction) {
        this.hasTransferAmountLimitPerTransaction = hasTransferAmountLimitPerTransaction;
    }

    public Float getTransferAmountLimitPerTransaction() {
        return this.transferAmountLimitPerTransaction;
    }

    public WalletProfile transferAmountLimitPerTransaction(Float transferAmountLimitPerTransaction) {
        this.setTransferAmountLimitPerTransaction(transferAmountLimitPerTransaction);
        return this;
    }

    public void setTransferAmountLimitPerTransaction(Float transferAmountLimitPerTransaction) {
        this.transferAmountLimitPerTransaction = transferAmountLimitPerTransaction;
    }

    public Boolean getHasDailyTransferAmountLimit() {
        return this.hasDailyTransferAmountLimit;
    }

    public WalletProfile hasDailyTransferAmountLimit(Boolean hasDailyTransferAmountLimit) {
        this.setHasDailyTransferAmountLimit(hasDailyTransferAmountLimit);
        return this;
    }

    public void setHasDailyTransferAmountLimit(Boolean hasDailyTransferAmountLimit) {
        this.hasDailyTransferAmountLimit = hasDailyTransferAmountLimit;
    }

    public Float getDailyTransferAmountLimit() {
        return this.dailyTransferAmountLimit;
    }

    public WalletProfile dailyTransferAmountLimit(Float dailyTransferAmountLimit) {
        this.setDailyTransferAmountLimit(dailyTransferAmountLimit);
        return this;
    }

    public void setDailyTransferAmountLimit(Float dailyTransferAmountLimit) {
        this.dailyTransferAmountLimit = dailyTransferAmountLimit;
    }

    public Boolean getCanTransferToConsumerWallet() {
        return this.canTransferToConsumerWallet;
    }

    public WalletProfile canTransferToConsumerWallet(Boolean canTransferToConsumerWallet) {
        this.setCanTransferToConsumerWallet(canTransferToConsumerWallet);
        return this;
    }

    public void setCanTransferToConsumerWallet(Boolean canTransferToConsumerWallet) {
        this.canTransferToConsumerWallet = canTransferToConsumerWallet;
    }

    public Boolean getCanTransferToMerchantWallet() {
        return this.canTransferToMerchantWallet;
    }

    public WalletProfile canTransferToMerchantWallet(Boolean canTransferToMerchantWallet) {
        this.setCanTransferToMerchantWallet(canTransferToMerchantWallet);
        return this;
    }

    public void setCanTransferToMerchantWallet(Boolean canTransferToMerchantWallet) {
        this.canTransferToMerchantWallet = canTransferToMerchantWallet;
    }

    public Boolean getCanTransferToConsumer() {
        return this.canTransferToConsumer;
    }

    public WalletProfile canTransferToConsumer(Boolean canTransferToConsumer) {
        this.setCanTransferToConsumer(canTransferToConsumer);
        return this;
    }

    public void setCanTransferToConsumer(Boolean canTransferToConsumer) {
        this.canTransferToConsumer = canTransferToConsumer;
    }

    public Boolean getCanTransferToBank() {
        return this.canTransferToBank;
    }

    public WalletProfile canTransferToBank(Boolean canTransferToBank) {
        this.setCanTransferToBank(canTransferToBank);
        return this;
    }

    public void setCanTransferToBank(Boolean canTransferToBank) {
        this.canTransferToBank = canTransferToBank;
    }

    public Boolean getCanBuyVouchers() {
        return this.canBuyVouchers;
    }

    public WalletProfile canBuyVouchers(Boolean canBuyVouchers) {
        this.setCanBuyVouchers(canBuyVouchers);
        return this;
    }

    public void setCanBuyVouchers(Boolean canBuyVouchers) {
        this.canBuyVouchers = canBuyVouchers;
    }

    public Boolean getTransferToConsumerWalletFees() {
        return this.transferToConsumerWalletFees;
    }

    public WalletProfile transferToConsumerWalletFees(Boolean transferToConsumerWalletFees) {
        this.setTransferToConsumerWalletFees(transferToConsumerWalletFees);
        return this;
    }

    public void setTransferToConsumerWalletFees(Boolean transferToConsumerWalletFees) {
        this.transferToConsumerWalletFees = transferToConsumerWalletFees;
    }

    public Boolean getTransferToMerchantWalletFees() {
        return this.transferToMerchantWalletFees;
    }

    public WalletProfile transferToMerchantWalletFees(Boolean transferToMerchantWalletFees) {
        this.setTransferToMerchantWalletFees(transferToMerchantWalletFees);
        return this;
    }

    public void setTransferToMerchantWalletFees(Boolean transferToMerchantWalletFees) {
        this.transferToMerchantWalletFees = transferToMerchantWalletFees;
    }

    public Boolean getTransferToConsumerFees() {
        return this.transferToConsumerFees;
    }

    public WalletProfile transferToConsumerFees(Boolean transferToConsumerFees) {
        this.setTransferToConsumerFees(transferToConsumerFees);
        return this;
    }

    public void setTransferToConsumerFees(Boolean transferToConsumerFees) {
        this.transferToConsumerFees = transferToConsumerFees;
    }

    public Boolean getTransferToBankFees() {
        return this.transferToBankFees;
    }

    public WalletProfile transferToBankFees(Boolean transferToBankFees) {
        this.setTransferToBankFees(transferToBankFees);
        return this;
    }

    public void setTransferToBankFees(Boolean transferToBankFees) {
        this.transferToBankFees = transferToBankFees;
    }

    public Boolean getBuyVouchersFees() {
        return this.buyVouchersFees;
    }

    public WalletProfile buyVouchersFees(Boolean buyVouchersFees) {
        this.setBuyVouchersFees(buyVouchersFees);
        return this;
    }

    public void setBuyVouchersFees(Boolean buyVouchersFees) {
        this.buyVouchersFees = buyVouchersFees;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletProfile)) {
            return false;
        }
        return getId() != null && getId().equals(((WalletProfile) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletProfile{" +
            "id=" + getId() +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", hasTransferAmountLimitPerTransaction='" + getHasTransferAmountLimitPerTransaction() + "'" +
            ", transferAmountLimitPerTransaction=" + getTransferAmountLimitPerTransaction() +
            ", hasDailyTransferAmountLimit='" + getHasDailyTransferAmountLimit() + "'" +
            ", dailyTransferAmountLimit=" + getDailyTransferAmountLimit() +
            ", canTransferToConsumerWallet='" + getCanTransferToConsumerWallet() + "'" +
            ", canTransferToMerchantWallet='" + getCanTransferToMerchantWallet() + "'" +
            ", canTransferToConsumer='" + getCanTransferToConsumer() + "'" +
            ", canTransferToBank='" + getCanTransferToBank() + "'" +
            ", canBuyVouchers='" + getCanBuyVouchers() + "'" +
            ", transferToConsumerWalletFees='" + getTransferToConsumerWalletFees() + "'" +
            ", transferToMerchantWalletFees='" + getTransferToMerchantWalletFees() + "'" +
            ", transferToConsumerFees='" + getTransferToConsumerFees() + "'" +
            ", transferToBankFees='" + getTransferToBankFees() + "'" +
            ", buyVouchersFees='" + getBuyVouchersFees() + "'" +
            "}";
    }
}
