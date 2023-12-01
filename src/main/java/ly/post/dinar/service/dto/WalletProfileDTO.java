package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link ly.post.dinar.domain.WalletProfile} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WalletProfileDTO implements Serializable {

    private Long id;

    private String nameAr;

    private String nameEn;

    private Boolean hasTransferAmountLimitPerTransaction;

    private Float transferAmountLimitPerTransaction;

    private Boolean hasDailyTransferAmountLimit;

    private Float dailyTransferAmountLimit;

    private Boolean canTransferToConsumerWallet;

    private Boolean canTransferToMerchantWallet;

    private Boolean canTransferToConsumer;

    private Boolean canTransferToBank;

    private Boolean canBuyVouchers;

    private Boolean transferToConsumerWalletFees;

    private Boolean transferToMerchantWalletFees;

    private Boolean transferToConsumerFees;

    private Boolean transferToBankFees;

    private Boolean buyVouchersFees;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Boolean getHasTransferAmountLimitPerTransaction() {
        return hasTransferAmountLimitPerTransaction;
    }

    public void setHasTransferAmountLimitPerTransaction(Boolean hasTransferAmountLimitPerTransaction) {
        this.hasTransferAmountLimitPerTransaction = hasTransferAmountLimitPerTransaction;
    }

    public Float getTransferAmountLimitPerTransaction() {
        return transferAmountLimitPerTransaction;
    }

    public void setTransferAmountLimitPerTransaction(Float transferAmountLimitPerTransaction) {
        this.transferAmountLimitPerTransaction = transferAmountLimitPerTransaction;
    }

    public Boolean getHasDailyTransferAmountLimit() {
        return hasDailyTransferAmountLimit;
    }

    public void setHasDailyTransferAmountLimit(Boolean hasDailyTransferAmountLimit) {
        this.hasDailyTransferAmountLimit = hasDailyTransferAmountLimit;
    }

    public Float getDailyTransferAmountLimit() {
        return dailyTransferAmountLimit;
    }

    public void setDailyTransferAmountLimit(Float dailyTransferAmountLimit) {
        this.dailyTransferAmountLimit = dailyTransferAmountLimit;
    }

    public Boolean getCanTransferToConsumerWallet() {
        return canTransferToConsumerWallet;
    }

    public void setCanTransferToConsumerWallet(Boolean canTransferToConsumerWallet) {
        this.canTransferToConsumerWallet = canTransferToConsumerWallet;
    }

    public Boolean getCanTransferToMerchantWallet() {
        return canTransferToMerchantWallet;
    }

    public void setCanTransferToMerchantWallet(Boolean canTransferToMerchantWallet) {
        this.canTransferToMerchantWallet = canTransferToMerchantWallet;
    }

    public Boolean getCanTransferToConsumer() {
        return canTransferToConsumer;
    }

    public void setCanTransferToConsumer(Boolean canTransferToConsumer) {
        this.canTransferToConsumer = canTransferToConsumer;
    }

    public Boolean getCanTransferToBank() {
        return canTransferToBank;
    }

    public void setCanTransferToBank(Boolean canTransferToBank) {
        this.canTransferToBank = canTransferToBank;
    }

    public Boolean getCanBuyVouchers() {
        return canBuyVouchers;
    }

    public void setCanBuyVouchers(Boolean canBuyVouchers) {
        this.canBuyVouchers = canBuyVouchers;
    }

    public Boolean getTransferToConsumerWalletFees() {
        return transferToConsumerWalletFees;
    }

    public void setTransferToConsumerWalletFees(Boolean transferToConsumerWalletFees) {
        this.transferToConsumerWalletFees = transferToConsumerWalletFees;
    }

    public Boolean getTransferToMerchantWalletFees() {
        return transferToMerchantWalletFees;
    }

    public void setTransferToMerchantWalletFees(Boolean transferToMerchantWalletFees) {
        this.transferToMerchantWalletFees = transferToMerchantWalletFees;
    }

    public Boolean getTransferToConsumerFees() {
        return transferToConsumerFees;
    }

    public void setTransferToConsumerFees(Boolean transferToConsumerFees) {
        this.transferToConsumerFees = transferToConsumerFees;
    }

    public Boolean getTransferToBankFees() {
        return transferToBankFees;
    }

    public void setTransferToBankFees(Boolean transferToBankFees) {
        this.transferToBankFees = transferToBankFees;
    }

    public Boolean getBuyVouchersFees() {
        return buyVouchersFees;
    }

    public void setBuyVouchersFees(Boolean buyVouchersFees) {
        this.buyVouchersFees = buyVouchersFees;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletProfileDTO)) {
            return false;
        }

        WalletProfileDTO walletProfileDTO = (WalletProfileDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, walletProfileDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletProfileDTO{" +
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
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
