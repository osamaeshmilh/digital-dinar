package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import ly.post.dinar.domain.Beneficiary;
import ly.post.dinar.domain.enumeration.IdType;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Beneficiary}, with proper type conversions.
 */
@Service
public class BeneficiaryRowMapper implements BiFunction<Row, String, Beneficiary> {

    private final ColumnConverter converter;

    public BeneficiaryRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Beneficiary} stored in the database.
     */
    @Override
    public Beneficiary apply(Row row, String prefix) {
        Beneficiary entity = new Beneficiary();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setArabicFirstName(converter.fromRow(row, prefix + "_arabic_first_name", String.class));
        entity.setArabicLastName(converter.fromRow(row, prefix + "_arabic_last_name", String.class));
        entity.setEnglishFirstName(converter.fromRow(row, prefix + "_english_first_name", String.class));
        entity.setEnglishLastName(converter.fromRow(row, prefix + "_english_last_name", String.class));
        entity.setIdType(converter.fromRow(row, prefix + "_id_type", IdType.class));
        entity.setIdNo(converter.fromRow(row, prefix + "_id_no", String.class));
        entity.setMobileNo(converter.fromRow(row, prefix + "_mobile_no", String.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setNotes(converter.fromRow(row, prefix + "_notes", String.class));
        entity.setCreatedByUserId(converter.fromRow(row, prefix + "_created_by_user_id", Long.class));
        entity.setIsVerified(converter.fromRow(row, prefix + "_is_verified", Boolean.class));
        entity.setHasTransferred(converter.fromRow(row, prefix + "_has_transferred", Boolean.class));
        return entity;
    }
}
