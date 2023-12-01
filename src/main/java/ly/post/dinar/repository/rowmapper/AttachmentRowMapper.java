package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import ly.post.dinar.domain.Attachment;
import ly.post.dinar.domain.enumeration.AttachmentType;
import ly.post.dinar.domain.enumeration.ReferenceType;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Attachment}, with proper type conversions.
 */
@Service
public class AttachmentRowMapper implements BiFunction<Row, String, Attachment> {

    private final ColumnConverter converter;

    public AttachmentRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Attachment} stored in the database.
     */
    @Override
    public Attachment apply(Row row, String prefix) {
        Attachment entity = new Attachment();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setDetails(converter.fromRow(row, prefix + "_details", String.class));
        entity.setNotes(converter.fromRow(row, prefix + "_notes", String.class));
        entity.setAttachmentType(converter.fromRow(row, prefix + "_attachment_type", AttachmentType.class));
        entity.setFileContentType(converter.fromRow(row, prefix + "_file_content_type", String.class));
        entity.setFile(converter.fromRow(row, prefix + "_file", byte[].class));
        entity.setFileUrl(converter.fromRow(row, prefix + "_file_url", String.class));
        entity.setReferenceType(converter.fromRow(row, prefix + "_reference_type", ReferenceType.class));
        entity.setReferenceId(converter.fromRow(row, prefix + "_reference_id", Long.class));
        return entity;
    }
}
