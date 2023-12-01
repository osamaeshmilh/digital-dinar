package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class AttachmentSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("name", table, columnPrefix + "_name"));
        columns.add(Column.aliased("details", table, columnPrefix + "_details"));
        columns.add(Column.aliased("notes", table, columnPrefix + "_notes"));
        columns.add(Column.aliased("attachment_type", table, columnPrefix + "_attachment_type"));
        columns.add(Column.aliased("file", table, columnPrefix + "_file"));
        columns.add(Column.aliased("file_content_type", table, columnPrefix + "_file_content_type"));
        columns.add(Column.aliased("file_url", table, columnPrefix + "_file_url"));
        columns.add(Column.aliased("reference_type", table, columnPrefix + "_reference_type"));
        columns.add(Column.aliased("reference_id", table, columnPrefix + "_reference_id"));

        return columns;
    }
}
