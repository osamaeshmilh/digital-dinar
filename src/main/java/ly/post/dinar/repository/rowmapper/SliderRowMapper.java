package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import ly.post.dinar.domain.Slider;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Slider}, with proper type conversions.
 */
@Service
public class SliderRowMapper implements BiFunction<Row, String, Slider> {

    private final ColumnConverter converter;

    public SliderRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Slider} stored in the database.
     */
    @Override
    public Slider apply(Row row, String prefix) {
        Slider entity = new Slider();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setDetails(converter.fromRow(row, prefix + "_details", String.class));
        entity.setMenuOrder(converter.fromRow(row, prefix + "_menu_order", Integer.class));
        entity.setImageFileUrl(converter.fromRow(row, prefix + "_image_file_url", String.class));
        entity.setImageFileContentType(converter.fromRow(row, prefix + "_image_file_content_type", String.class));
        entity.setImageFile(converter.fromRow(row, prefix + "_image_file", byte[].class));
        entity.setUrl(converter.fromRow(row, prefix + "_url", String.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", Instant.class));
        entity.setLastModifiedBy(converter.fromRow(row, prefix + "_last_modified_by", String.class));
        entity.setLastModifiedDate(converter.fromRow(row, prefix + "_last_modified_date", Instant.class));
        return entity;
    }
}
