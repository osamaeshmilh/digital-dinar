package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import ly.post.dinar.domain.Country;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Country}, with proper type conversions.
 */
@Service
public class CountryRowMapper implements BiFunction<Row, String, Country> {

    private final ColumnConverter converter;

    public CountryRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Country} stored in the database.
     */
    @Override
    public Country apply(Row row, String prefix) {
        Country entity = new Country();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setNameAr(converter.fromRow(row, prefix + "_name_ar", String.class));
        entity.setNameEn(converter.fromRow(row, prefix + "_name_en", String.class));
        entity.setIso2(converter.fromRow(row, prefix + "_iso_2", String.class));
        entity.setIso3(converter.fromRow(row, prefix + "_iso_3", String.class));
        entity.setIsoNo(converter.fromRow(row, prefix + "_iso_no", Integer.class));
        return entity;
    }
}
