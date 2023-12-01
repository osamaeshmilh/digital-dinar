package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import ly.post.dinar.domain.Setting;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Setting}, with proper type conversions.
 */
@Service
public class SettingRowMapper implements BiFunction<Row, String, Setting> {

    private final ColumnConverter converter;

    public SettingRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Setting} stored in the database.
     */
    @Override
    public Setting apply(Row row, String prefix) {
        Setting entity = new Setting();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setKey(converter.fromRow(row, prefix + "_jhi_key", String.class));
        entity.setValue(converter.fromRow(row, prefix + "_value", String.class));
        return entity;
    }
}
