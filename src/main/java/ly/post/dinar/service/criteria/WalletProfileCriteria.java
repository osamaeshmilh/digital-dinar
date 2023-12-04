package ly.post.dinar.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.WalletProfile} entity. This class is used
 * in {@link ly.post.dinar.web.rest.WalletProfileResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /wallet-profiles?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WalletProfileCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nameAr;

    private StringFilter nameEn;

    private BooleanFilter hasTransferAmountLimitPerTransaction;

    private FloatFilter transferAmountLimitPerTransaction;

    private BooleanFilter hasDailyTransferAmountLimit;

    private FloatFilter dailyTransferAmountLimit;

    private BooleanFilter canTransferToConsumerWallet;

    private BooleanFilter canTransferToMerchantWallet;

    private BooleanFilter canTransferToConsumer;

    private BooleanFilter canTransferToBank;

    private BooleanFilter canBuyVouchers;

    private BooleanFilter transferToConsumerWalletFees;

    private BooleanFilter transferToMerchantWalletFees;

    private BooleanFilter transferToConsumerFees;

    private BooleanFilter transferToBankFees;

    private BooleanFilter buyVouchersFees;

    private Boolean distinct;

    public WalletProfileCriteria() {}

    public WalletProfileCriteria(WalletProfileCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nameAr = other.nameAr == null ? null : other.nameAr.copy();
        this.nameEn = other.nameEn == null ? null : other.nameEn.copy();
        this.hasTransferAmountLimitPerTransaction =
            other.hasTransferAmountLimitPerTransaction == null ? null : other.hasTransferAmountLimitPerTransaction.copy();
        this.transferAmountLimitPerTransaction =
            other.transferAmountLimitPerTransaction == null ? null : other.transferAmountLimitPerTransaction.copy();
        this.hasDailyTransferAmountLimit = other.hasDailyTransferAmountLimit == null ? null : other.hasDailyTransferAmountLimit.copy();
        this.dailyTransferAmountLimit = other.dailyTransferAmountLimit == null ? null : other.dailyTransferAmountLimit.copy();
        this.canTransferToConsumerWallet = other.canTransferToConsumerWallet == null ? null : other.canTransferToConsumerWallet.copy();
        this.canTransferToMerchantWallet = other.canTransferToMerchantWallet == null ? null : other.canTransferToMerchantWallet.copy();
        this.canTransferToConsumer = other.canTransferToConsumer == null ? null : other.canTransferToConsumer.copy();
        this.canTransferToBank = other.canTransferToBank == null ? null : other.canTransferToBank.copy();
        this.canBuyVouchers = other.canBuyVouchers == null ? null : other.canBuyVouchers.copy();
        this.transferToConsumerWalletFees = other.transferToConsumerWalletFees == null ? null : other.transferToConsumerWalletFees.copy();
        this.transferToMerchantWalletFees = other.transferToMerchantWalletFees == null ? null : other.transferToMerchantWalletFees.copy();
        this.transferToConsumerFees = other.transferToConsumerFees == null ? null : other.transferToConsumerFees.copy();
        this.transferToBankFees = other.transferToBankFees == null ? null : other.transferToBankFees.copy();
        this.buyVouchersFees = other.buyVouchersFees == null ? null : other.buyVouchersFees.copy();
        this.distinct = other.distinct;
    }

    @Override
    public WalletProfileCriteria copy() {
        return new WalletProfileCriteria(this);
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

    public StringFilter getNameAr() {
        return nameAr;
    }

    public StringFilter nameAr() {
        if (nameAr == null) {
            nameAr = new StringFilter();
        }
        return nameAr;
    }

    public void setNameAr(StringFilter nameAr) {
        this.nameAr = nameAr;
    }

    public StringFilter getNameEn() {
        return nameEn;
    }

    public StringFilter nameEn() {
        if (nameEn == null) {
            nameEn = new StringFilter();
        }
        return nameEn;
    }

    public void setNameEn(StringFilter nameEn) {
        this.nameEn = nameEn;
    }

    public BooleanFilter getHasTransferAmountLimitPerTransaction() {
        return hasTransferAmountLimitPerTransaction;
    }

    public BooleanFilter hasTransferAmountLimitPerTransaction() {
        if (hasTransferAmountLimitPerTransaction == null) {
            hasTransferAmountLimitPerTransaction = new BooleanFilter();
        }
        return hasTransferAmountLimitPerTransaction;
    }

    public void setHasTransferAmountLimitPerTransaction(BooleanFilter hasTransferAmountLimitPerTransaction) {
        this.hasTransferAmountLimitPerTransaction = hasTransferAmountLimitPerTransaction;
    }

    public FloatFilter getTransferAmountLimitPerTransaction() {
        return transferAmountLimitPerTransaction;
    }

    public FloatFilter transferAmountLimitPerTransaction() {
        if (transferAmountLimitPerTransaction == null) {
            transferAmountLimitPerTransaction = new FloatFilter();
        }
        return transferAmountLimitPerTransaction;
    }

    public void setTransferAmountLimitPerTransaction(FloatFilter transferAmountLimitPerTransaction) {
        this.transferAmountLimitPerTransaction = transferAmountLimitPerTransaction;
    }

    public BooleanFilter getHasDailyTransferAmountLimit() {
        return hasDailyTransferAmountLimit;
    }

    public BooleanFilter hasDailyTransferAmountLimit() {
        if (hasDailyTransferAmountLimit == null) {
            hasDailyTransferAmountLimit = new BooleanFilter();
        }
        return hasDailyTransferAmountLimit;
    }

    public void setHasDailyTransferAmountLimit(BooleanFilter hasDailyTransferAmountLimit) {
        this.hasDailyTransferAmountLimit = hasDailyTransferAmountLimit;
    }

    public FloatFilter getDailyTransferAmountLimit() {
        return dailyTransferAmountLimit;
    }

    public FloatFilter dailyTransferAmountLimit() {
        if (dailyTransferAmountLimit == null) {
            dailyTransferAmountLimit = new FloatFilter();
        }
        return dailyTransferAmountLimit;
    }

    public void setDailyTransferAmountLimit(FloatFilter dailyTransferAmountLimit) {
        this.dailyTransferAmountLimit = dailyTransferAmountLimit;
    }

    public BooleanFilter getCanTransferToConsumerWallet() {
        return canTransferToConsumerWallet;
    }

    public BooleanFilter canTransferToConsumerWallet() {
        if (canTransferToConsumerWallet == null) {
            canTransferToConsumerWallet = new BooleanFilter();
        }
        return canTransferToConsumerWallet;
    }

    public void setCanTransferToConsumerWallet(BooleanFilter canTransferToConsumerWallet) {
        this.canTransferToConsumerWallet = canTransferToConsumerWallet;
    }

    public BooleanFilter getCanTransferToMerchantWallet() {
        return canTransferToMerchantWallet;
    }

    public BooleanFilter canTransferToMerchantWallet() {
        if (canTransferToMerchantWallet == null) {
            canTransferToMerchantWallet = new BooleanFilter();
        }
        return canTransferToMerchantWallet;
    }

    public void setCanTransferToMerchantWallet(BooleanFilter canTransferToMerchantWallet) {
        this.canTransferToMerchantWallet = canTransferToMerchantWallet;
    }

    public BooleanFilter getCanTransferToConsumer() {
        return canTransferToConsumer;
    }

    public BooleanFilter canTransferToConsumer() {
        if (canTransferToConsumer == null) {
            canTransferToConsumer = new BooleanFilter();
        }
        return canTransferToConsumer;
    }

    public void setCanTransferToConsumer(BooleanFilter canTransferToConsumer) {
        this.canTransferToConsumer = canTransferToConsumer;
    }

    public BooleanFilter getCanTransferToBank() {
        return canTransferToBank;
    }

    public BooleanFilter canTransferToBank() {
        if (canTransferToBank == null) {
            canTransferToBank = new BooleanFilter();
        }
        return canTransferToBank;
    }

    public void setCanTransferToBank(BooleanFilter canTransferToBank) {
        this.canTransferToBank = canTransferToBank;
    }

    public BooleanFilter getCanBuyVouchers() {
        return canBuyVouchers;
    }

    public BooleanFilter canBuyVouchers() {
        if (canBuyVouchers == null) {
            canBuyVouchers = new BooleanFilter();
        }
        return canBuyVouchers;
    }

    public void setCanBuyVouchers(BooleanFilter canBuyVouchers) {
        this.canBuyVouchers = canBuyVouchers;
    }

    public BooleanFilter getTransferToConsumerWalletFees() {
        return transferToConsumerWalletFees;
    }

    public BooleanFilter transferToConsumerWalletFees() {
        if (transferToConsumerWalletFees == null) {
            transferToConsumerWalletFees = new BooleanFilter();
        }
        return transferToConsumerWalletFees;
    }

    public void setTransferToConsumerWalletFees(BooleanFilter transferToConsumerWalletFees) {
        this.transferToConsumerWalletFees = transferToConsumerWalletFees;
    }

    public BooleanFilter getTransferToMerchantWalletFees() {
        return transferToMerchantWalletFees;
    }

    public BooleanFilter transferToMerchantWalletFees() {
        if (transferToMerchantWalletFees == null) {
            transferToMerchantWalletFees = new BooleanFilter();
        }
        return transferToMerchantWalletFees;
    }

    public void setTransferToMerchantWalletFees(BooleanFilter transferToMerchantWalletFees) {
        this.transferToMerchantWalletFees = transferToMerchantWalletFees;
    }

    public BooleanFilter getTransferToConsumerFees() {
        return transferToConsumerFees;
    }

    public BooleanFilter transferToConsumerFees() {
        if (transferToConsumerFees == null) {
            transferToConsumerFees = new BooleanFilter();
        }
        return transferToConsumerFees;
    }

    public void setTransferToConsumerFees(BooleanFilter transferToConsumerFees) {
        this.transferToConsumerFees = transferToConsumerFees;
    }

    public BooleanFilter getTransferToBankFees() {
        return transferToBankFees;
    }

    public BooleanFilter transferToBankFees() {
        if (transferToBankFees == null) {
            transferToBankFees = new BooleanFilter();
        }
        return transferToBankFees;
    }

    public void setTransferToBankFees(BooleanFilter transferToBankFees) {
        this.transferToBankFees = transferToBankFees;
    }

    public BooleanFilter getBuyVouchersFees() {
        return buyVouchersFees;
    }

    public BooleanFilter buyVouchersFees() {
        if (buyVouchersFees == null) {
            buyVouchersFees = new BooleanFilter();
        }
        return buyVouchersFees;
    }

    public void setBuyVouchersFees(BooleanFilter buyVouchersFees) {
        this.buyVouchersFees = buyVouchersFees;
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
        final WalletProfileCriteria that = (WalletProfileCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(nameAr, that.nameAr) &&
            Objects.equals(nameEn, that.nameEn) &&
            Objects.equals(hasTransferAmountLimitPerTransaction, that.hasTransferAmountLimitPerTransaction) &&
            Objects.equals(transferAmountLimitPerTransaction, that.transferAmountLimitPerTransaction) &&
            Objects.equals(hasDailyTransferAmountLimit, that.hasDailyTransferAmountLimit) &&
            Objects.equals(dailyTransferAmountLimit, that.dailyTransferAmountLimit) &&
            Objects.equals(canTransferToConsumerWallet, that.canTransferToConsumerWallet) &&
            Objects.equals(canTransferToMerchantWallet, that.canTransferToMerchantWallet) &&
            Objects.equals(canTransferToConsumer, that.canTransferToConsumer) &&
            Objects.equals(canTransferToBank, that.canTransferToBank) &&
            Objects.equals(canBuyVouchers, that.canBuyVouchers) &&
            Objects.equals(transferToConsumerWalletFees, that.transferToConsumerWalletFees) &&
            Objects.equals(transferToMerchantWalletFees, that.transferToMerchantWalletFees) &&
            Objects.equals(transferToConsumerFees, that.transferToConsumerFees) &&
            Objects.equals(transferToBankFees, that.transferToBankFees) &&
            Objects.equals(buyVouchersFees, that.buyVouchersFees) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            nameAr,
            nameEn,
            hasTransferAmountLimitPerTransaction,
            transferAmountLimitPerTransaction,
            hasDailyTransferAmountLimit,
            dailyTransferAmountLimit,
            canTransferToConsumerWallet,
            canTransferToMerchantWallet,
            canTransferToConsumer,
            canTransferToBank,
            canBuyVouchers,
            transferToConsumerWalletFees,
            transferToMerchantWalletFees,
            transferToConsumerFees,
            transferToBankFees,
            buyVouchersFees,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletProfileCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (nameAr != null ? "nameAr=" + nameAr + ", " : "") +
            (nameEn != null ? "nameEn=" + nameEn + ", " : "") +
            (hasTransferAmountLimitPerTransaction != null ? "hasTransferAmountLimitPerTransaction=" + hasTransferAmountLimitPerTransaction + ", " : "") +
            (transferAmountLimitPerTransaction != null ? "transferAmountLimitPerTransaction=" + transferAmountLimitPerTransaction + ", " : "") +
            (hasDailyTransferAmountLimit != null ? "hasDailyTransferAmountLimit=" + hasDailyTransferAmountLimit + ", " : "") +
            (dailyTransferAmountLimit != null ? "dailyTransferAmountLimit=" + dailyTransferAmountLimit + ", " : "") +
            (canTransferToConsumerWallet != null ? "canTransferToConsumerWallet=" + canTransferToConsumerWallet + ", " : "") +
            (canTransferToMerchantWallet != null ? "canTransferToMerchantWallet=" + canTransferToMerchantWallet + ", " : "") +
            (canTransferToConsumer != null ? "canTransferToConsumer=" + canTransferToConsumer + ", " : "") +
            (canTransferToBank != null ? "canTransferToBank=" + canTransferToBank + ", " : "") +
            (canBuyVouchers != null ? "canBuyVouchers=" + canBuyVouchers + ", " : "") +
            (transferToConsumerWalletFees != null ? "transferToConsumerWalletFees=" + transferToConsumerWalletFees + ", " : "") +
            (transferToMerchantWalletFees != null ? "transferToMerchantWalletFees=" + transferToMerchantWalletFees + ", " : "") +
            (transferToConsumerFees != null ? "transferToConsumerFees=" + transferToConsumerFees + ", " : "") +
            (transferToBankFees != null ? "transferToBankFees=" + transferToBankFees + ", " : "") +
            (buyVouchersFees != null ? "buyVouchersFees=" + buyVouchersFees + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
