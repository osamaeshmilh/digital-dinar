package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import ly.post.dinar.domain.Transaction;
import ly.post.dinar.domain.enumeration.PaymentType;
import ly.post.dinar.domain.enumeration.TransactionStatus;
import ly.post.dinar.domain.enumeration.TransactionType;
import ly.post.dinar.domain.enumeration.WalletOwnerType;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Transaction}, with proper type conversions.
 */
@Service
public class TransactionRowMapper implements BiFunction<Row, String, Transaction> {

    private final ColumnConverter converter;

    public TransactionRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Transaction} stored in the database.
     */
    @Override
    public Transaction apply(Row row, String prefix) {
        Transaction entity = new Transaction();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTransactionReference(converter.fromRow(row, prefix + "_transaction_reference", String.class));
        entity.setTransactionSecret(converter.fromRow(row, prefix + "_transaction_secret", String.class));
        entity.setTransactionOtp(converter.fromRow(row, prefix + "_transaction_otp", String.class));
        entity.setPaymentType(converter.fromRow(row, prefix + "_payment_type", PaymentType.class));
        entity.setTransactionStatus(converter.fromRow(row, prefix + "_transaction_status", TransactionStatus.class));
        entity.setTransactionType(converter.fromRow(row, prefix + "_transaction_type", TransactionType.class));
        entity.setVendorReference(converter.fromRow(row, prefix + "_vendor_reference", String.class));
        entity.setVendorMessage(converter.fromRow(row, prefix + "_vendor_message", String.class));
        entity.setAmount(converter.fromRow(row, prefix + "_amount", Float.class));
        entity.setFees(converter.fromRow(row, prefix + "_fees", Float.class));
        entity.setTotal(converter.fromRow(row, prefix + "_total", Float.class));
        entity.setNotes(converter.fromRow(row, prefix + "_notes", String.class));
        entity.setSenderMobileNo(converter.fromRow(row, prefix + "_sender_mobile_no", String.class));
        entity.setSenderName(converter.fromRow(row, prefix + "_sender_name", String.class));
        entity.setSenderType(converter.fromRow(row, prefix + "_sender_type", WalletOwnerType.class));
        entity.setSenderIdNo(converter.fromRow(row, prefix + "_sender_id_no", String.class));
        entity.setSenderId(converter.fromRow(row, prefix + "_sender_id", Long.class));
        entity.setReceiverName(converter.fromRow(row, prefix + "_receiver_name", String.class));
        entity.setReceiverMobileNo(converter.fromRow(row, prefix + "_receiver_mobile_no", String.class));
        entity.setReceiverType(converter.fromRow(row, prefix + "_receiver_type", WalletOwnerType.class));
        entity.setReceiverId(converter.fromRow(row, prefix + "_receiver_id", Long.class));
        entity.setReceiverIdNo(converter.fromRow(row, prefix + "_receiver_id_no", String.class));
        entity.setCreatedByUserId(converter.fromRow(row, prefix + "_created_by_user_id", Long.class));
        return entity;
    }
}
