package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import ly.post.dinar.domain.PaymentMethod;
import ly.post.dinar.domain.enumeration.PaymentType;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PaymentMethod}, with proper type conversions.
 */
@Service
public class PaymentMethodRowMapper implements BiFunction<Row, String, PaymentMethod> {

    private final ColumnConverter converter;

    public PaymentMethodRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PaymentMethod} stored in the database.
     */
    @Override
    public PaymentMethod apply(Row row, String prefix) {
        PaymentMethod entity = new PaymentMethod();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setNameAr(converter.fromRow(row, prefix + "_name_ar", String.class));
        entity.setNameEn(converter.fromRow(row, prefix + "_name_en", String.class));
        entity.setMenuOrder(converter.fromRow(row, prefix + "_menu_order", Integer.class));
        entity.setImageFileUrl(converter.fromRow(row, prefix + "_image_file_url", String.class));
        entity.setImageFileContentType(converter.fromRow(row, prefix + "_image_file_content_type", String.class));
        entity.setImageFile(converter.fromRow(row, prefix + "_image_file", byte[].class));
        entity.setDetails(converter.fromRow(row, prefix + "_details", String.class));
        entity.setFeePercentage(converter.fromRow(row, prefix + "_fee_percentage", Float.class));
        entity.setPaymentType(converter.fromRow(row, prefix + "_payment_type", PaymentType.class));
        entity.setNotes(converter.fromRow(row, prefix + "_notes", String.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", Instant.class));
        entity.setLastModifiedBy(converter.fromRow(row, prefix + "_last_modified_by", String.class));
        entity.setLastModifiedDate(converter.fromRow(row, prefix + "_last_modified_date", Instant.class));
        return entity;
    }
}
