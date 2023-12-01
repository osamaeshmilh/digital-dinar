package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import ly.post.dinar.domain.ViewLog;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ViewLog}, with proper type conversions.
 */
@Service
public class ViewLogRowMapper implements BiFunction<Row, String, ViewLog> {

    private final ColumnConverter converter;

    public ViewLogRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ViewLog} stored in the database.
     */
    @Override
    public ViewLog apply(Row row, String prefix) {
        ViewLog entity = new ViewLog();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setEntityName(converter.fromRow(row, prefix + "_entity_name", String.class));
        entity.setEntityId(converter.fromRow(row, prefix + "_entity_id", Long.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", Instant.class));
        entity.setLastModifiedBy(converter.fromRow(row, prefix + "_last_modified_by", String.class));
        entity.setLastModifiedDate(converter.fromRow(row, prefix + "_last_modified_date", Instant.class));
        return entity;
    }
}