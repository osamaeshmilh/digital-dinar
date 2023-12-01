package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class SliderSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("details", table, columnPrefix + "_details"));
        columns.add(Column.aliased("menu_order", table, columnPrefix + "_menu_order"));
        columns.add(Column.aliased("image_file_url", table, columnPrefix + "_image_file_url"));
        columns.add(Column.aliased("image_file", table, columnPrefix + "_image_file"));
        columns.add(Column.aliased("image_file_content_type", table, columnPrefix + "_image_file_content_type"));
        columns.add(Column.aliased("url", table, columnPrefix + "_url"));

        return columns;
    }
}
