package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ActivationSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("mobile_no", table, columnPrefix + "_mobile_no"));
        columns.add(Column.aliased("email", table, columnPrefix + "_email"));
        columns.add(Column.aliased("code", table, columnPrefix + "_code"));
        columns.add(Column.aliased("sent_on", table, columnPrefix + "_sent_on"));
        columns.add(Column.aliased("valid_until", table, columnPrefix + "_valid_until"));
        columns.add(Column.aliased("is_used", table, columnPrefix + "_is_used"));

        return columns;
    }
}
