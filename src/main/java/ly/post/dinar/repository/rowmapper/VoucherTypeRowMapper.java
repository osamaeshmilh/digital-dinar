package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import ly.post.dinar.domain.VoucherType;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link VoucherType}, with proper type conversions.
 */
@Service
public class VoucherTypeRowMapper implements BiFunction<Row, String, VoucherType> {

    private final ColumnConverter converter;

    public VoucherTypeRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link VoucherType} stored in the database.
     */
    @Override
    public VoucherType apply(Row row, String prefix) {
        VoucherType entity = new VoucherType();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setNameAr(converter.fromRow(row, prefix + "_name_ar", String.class));
        entity.setNameEn(converter.fromRow(row, prefix + "_name_en", String.class));
        entity.setSlug(converter.fromRow(row, prefix + "_slug", String.class));
        entity.setAmount(converter.fromRow(row, prefix + "_amount", Float.class));
        entity.setMenuOrder(converter.fromRow(row, prefix + "_menu_order", Integer.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", Instant.class));
        entity.setLastModifiedBy(converter.fromRow(row, prefix + "_last_modified_by", String.class));
        entity.setLastModifiedDate(converter.fromRow(row, prefix + "_last_modified_date", Instant.class));
        entity.setVoucherCompanyId(converter.fromRow(row, prefix + "_voucher_company_id", Long.class));
        return entity;
    }
}
