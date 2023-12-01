package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import ly.post.dinar.domain.Activation;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Activation}, with proper type conversions.
 */
@Service
public class ActivationRowMapper implements BiFunction<Row, String, Activation> {

    private final ColumnConverter converter;

    public ActivationRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Activation} stored in the database.
     */
    @Override
    public Activation apply(Row row, String prefix) {
        Activation entity = new Activation();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setMobileNo(converter.fromRow(row, prefix + "_mobile_no", String.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setCode(converter.fromRow(row, prefix + "_code", String.class));
        entity.setSentOn(converter.fromRow(row, prefix + "_sent_on", Instant.class));
        entity.setValidUntil(converter.fromRow(row, prefix + "_valid_until", Instant.class));
        entity.setIsUsed(converter.fromRow(row, prefix + "_is_used", Boolean.class));
        return entity;
    }
}
