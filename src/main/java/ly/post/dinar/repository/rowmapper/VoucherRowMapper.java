package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import ly.post.dinar.domain.Voucher;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Voucher}, with proper type conversions.
 */
@Service
public class VoucherRowMapper implements BiFunction<Row, String, Voucher> {

    private final ColumnConverter converter;

    public VoucherRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Voucher} stored in the database.
     */
    @Override
    public Voucher apply(Row row, String prefix) {
        Voucher entity = new Voucher();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setCode(converter.fromRow(row, prefix + "_code", String.class));
        entity.setAmount(converter.fromRow(row, prefix + "_amount", Float.class));
        entity.setSerialNumber(converter.fromRow(row, prefix + "_serial_number", String.class));
        entity.setIsSold(converter.fromRow(row, prefix + "_is_sold", Boolean.class));
        entity.setSellTransactionId(converter.fromRow(row, prefix + "_sell_transaction_id", Long.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", Instant.class));
        entity.setLastModifiedBy(converter.fromRow(row, prefix + "_last_modified_by", String.class));
        entity.setLastModifiedDate(converter.fromRow(row, prefix + "_last_modified_date", Instant.class));
        entity.setVoucherTypeId(converter.fromRow(row, prefix + "_voucher_type_id", Long.class));
        entity.setVoucherCompanyId(converter.fromRow(row, prefix + "_voucher_company_id", Long.class));
        return entity;
    }
}
