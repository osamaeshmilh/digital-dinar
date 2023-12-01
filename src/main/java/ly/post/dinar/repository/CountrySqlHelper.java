package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class CountrySqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("name_ar", table, columnPrefix + "_name_ar"));
        columns.add(Column.aliased("name_en", table, columnPrefix + "_name_en"));
        columns.add(Column.aliased("iso_2", table, columnPrefix + "_iso_2"));
        columns.add(Column.aliased("iso_3", table, columnPrefix + "_iso_3"));
        columns.add(Column.aliased("iso_no", table, columnPrefix + "_iso_no"));

        return columns;
    }
}
