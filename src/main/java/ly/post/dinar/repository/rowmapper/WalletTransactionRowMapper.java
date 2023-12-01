package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import ly.post.dinar.domain.WalletTransaction;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.WalletAction;
import ly.post.dinar.domain.enumeration.WalletOwnerType;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link WalletTransaction}, with proper type conversions.
 */
@Service
public class WalletTransactionRowMapper implements BiFunction<Row, String, WalletTransaction> {

    private final ColumnConverter converter;

    public WalletTransactionRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link WalletTransaction} stored in the database.
     */
    @Override
    public WalletTransaction apply(Row row, String prefix) {
        WalletTransaction entity = new WalletTransaction();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTransactionNo(converter.fromRow(row, prefix + "_transaction_no", String.class));
        entity.setAmount(converter.fromRow(row, prefix + "_amount", Float.class));
        entity.setWalletAction(converter.fromRow(row, prefix + "_wallet_action", WalletAction.class));
        entity.setUserPreviousTransactionNo(converter.fromRow(row, prefix + "_user_previous_transaction_no", String.class));
        entity.setTotalBeforeAction(converter.fromRow(row, prefix + "_total_before_action", Float.class));
        entity.setTotalAfterAction(converter.fromRow(row, prefix + "_total_after_action", Float.class));
        entity.setPaymentType(converter.fromRow(row, prefix + "_payment_type", PaymentType.class));
        entity.setPaymentReference(converter.fromRow(row, prefix + "_payment_reference", String.class));
        entity.setNotes(converter.fromRow(row, prefix + "_notes", String.class));
        entity.setOwnerId(converter.fromRow(row, prefix + "_owner_id", Long.class));
        entity.setWalletOwnerType(converter.fromRow(row, prefix + "_wallet_owner_type", WalletOwnerType.class));
        entity.setTransactionId(converter.fromRow(row, prefix + "_transaction_id", Long.class));
        return entity;
    }
}
