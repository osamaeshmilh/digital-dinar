package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class VoucherCompanySqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("name_ar", table, columnPrefix + "_name_ar"));
        columns.add(Column.aliased("name_en", table, columnPrefix + "_name_en"));
        columns.add(Column.aliased("mobile_no", table, columnPrefix + "_mobile_no"));
        columns.add(Column.aliased("email", table, columnPrefix + "_email"));
        columns.add(Column.aliased("image_file", table, columnPrefix + "_image_file"));
        columns.add(Column.aliased("image_file_content_type", table, columnPrefix + "_image_file_content_type"));
        columns.add(Column.aliased("image_url_file", table, columnPrefix + "_image_url_file"));
        columns.add(Column.aliased("post_code", table, columnPrefix + "_post_code"));
        columns.add(Column.aliased("address", table, columnPrefix + "_address"));
        columns.add(Column.aliased("notes", table, columnPrefix + "_notes"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("last_modified_by", table, columnPrefix + "_last_modified_by"));
        columns.add(Column.aliased("last_modified_date", table, columnPrefix + "_last_modified_date"));

        columns.add(Column.aliased("user_id", table, columnPrefix + "_user_id"));
        return columns;
    }
}
