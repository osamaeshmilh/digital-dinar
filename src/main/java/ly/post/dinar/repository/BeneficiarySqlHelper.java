package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class BeneficiarySqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("arabic_first_name", table, columnPrefix + "_arabic_first_name"));
        columns.add(Column.aliased("arabic_last_name", table, columnPrefix + "_arabic_last_name"));
        columns.add(Column.aliased("english_first_name", table, columnPrefix + "_english_first_name"));
        columns.add(Column.aliased("english_last_name", table, columnPrefix + "_english_last_name"));
        columns.add(Column.aliased("id_type", table, columnPrefix + "_id_type"));
        columns.add(Column.aliased("id_no", table, columnPrefix + "_id_no"));
        columns.add(Column.aliased("mobile_no", table, columnPrefix + "_mobile_no"));
        columns.add(Column.aliased("email", table, columnPrefix + "_email"));
        columns.add(Column.aliased("notes", table, columnPrefix + "_notes"));
        columns.add(Column.aliased("created_by_user_id", table, columnPrefix + "_created_by_user_id"));
        columns.add(Column.aliased("is_verified", table, columnPrefix + "_is_verified"));
        columns.add(Column.aliased("has_transferred", table, columnPrefix + "_has_transferred"));

        return columns;
    }
}
