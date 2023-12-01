package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class CategorySqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("name_ar", table, columnPrefix + "_name_ar"));
        columns.add(Column.aliased("name_en", table, columnPrefix + "_name_en"));
        columns.add(Column.aliased("code", table, columnPrefix + "_code"));

        return columns;
    }
}
