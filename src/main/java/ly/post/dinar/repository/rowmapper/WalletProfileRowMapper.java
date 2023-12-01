package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import ly.post.dinar.domain.WalletProfile;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link WalletProfile}, with proper type conversions.
 */
@Service
public class WalletProfileRowMapper implements BiFunction<Row, String, WalletProfile> {

    private final ColumnConverter converter;

    public WalletProfileRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link WalletProfile} stored in the database.
     */
    @Override
    public WalletProfile apply(Row row, String prefix) {
        WalletProfile entity = new WalletProfile();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setNameAr(converter.fromRow(row, prefix + "_name_ar", String.class));
        entity.setNameEn(converter.fromRow(row, prefix + "_name_en", String.class));
        entity.setHasTransferAmountLimitPerTransaction(
            converter.fromRow(row, prefix + "_has_transfer_amount_limit_per_transaction", Boolean.class)
        );
        entity.setTransferAmountLimitPerTransaction(converter.fromRow(row, prefix + "_transfer_amount_limit_per_transaction", Float.class));
        entity.setHasDailyTransferAmountLimit(converter.fromRow(row, prefix + "_has_daily_transfer_amount_limit", Boolean.class));
        entity.setDailyTransferAmountLimit(converter.fromRow(row, prefix + "_daily_transfer_amount_limit", Float.class));
        entity.setCanTransferToConsumerWallet(converter.fromRow(row, prefix + "_can_transfer_to_consumer_wallet", Boolean.class));
        entity.setCanTransferToMerchantWallet(converter.fromRow(row, prefix + "_can_transfer_to_merchant_wallet", Boolean.class));
        entity.setCanTransferToConsumer(converter.fromRow(row, prefix + "_can_transfer_to_consumer", Boolean.class));
        entity.setCanTransferToBank(converter.fromRow(row, prefix + "_can_transfer_to_bank", Boolean.class));
        entity.setCanBuyVouchers(converter.fromRow(row, prefix + "_can_buy_vouchers", Boolean.class));
        entity.setTransferToConsumerWalletFees(converter.fromRow(row, prefix + "_transfer_to_consumer_wallet_fees", Boolean.class));
        entity.setTransferToMerchantWalletFees(converter.fromRow(row, prefix + "_transfer_to_merchant_wallet_fees", Boolean.class));
        entity.setTransferToConsumerFees(converter.fromRow(row, prefix + "_transfer_to_consumer_fees", Boolean.class));
        entity.setTransferToBankFees(converter.fromRow(row, prefix + "_transfer_to_bank_fees", Boolean.class));
        entity.setBuyVouchersFees(converter.fromRow(row, prefix + "_buy_vouchers_fees", Boolean.class));
        return entity;
    }
}
