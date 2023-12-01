package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import ly.post.dinar.domain.VoucherCompany;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link VoucherCompany}, with proper type conversions.
 */
@Service
public class VoucherCompanyRowMapper implements BiFunction<Row, String, VoucherCompany> {

    private final ColumnConverter converter;

    public VoucherCompanyRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link VoucherCompany} stored in the database.
     */
    @Override
    public VoucherCompany apply(Row row, String prefix) {
        VoucherCompany entity = new VoucherCompany();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setNameAr(converter.fromRow(row, prefix + "_name_ar", String.class));
        entity.setNameEn(converter.fromRow(row, prefix + "_name_en", String.class));
        entity.setMobileNo(converter.fromRow(row, prefix + "_mobile_no", String.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setImageFileContentType(converter.fromRow(row, prefix + "_image_file_content_type", String.class));
        entity.setImageFile(converter.fromRow(row, prefix + "_image_file", byte[].class));
        entity.setImageUrlFile(converter.fromRow(row, prefix + "_image_url_file", String.class));
        entity.setPostCode(converter.fromRow(row, prefix + "_post_code", String.class));
        entity.setAddress(converter.fromRow(row, prefix + "_address", String.class));
        entity.setNotes(converter.fromRow(row, prefix + "_notes", String.class));
        entity.setUserId(converter.fromRow(row, prefix + "_user_id", Long.class));
        return entity;
    }
}
