package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class VoucherSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("code", table, columnPrefix + "_code"));
        columns.add(Column.aliased("amount", table, columnPrefix + "_amount"));
        columns.add(Column.aliased("serial_number", table, columnPrefix + "_serial_number"));
        columns.add(Column.aliased("is_sold", table, columnPrefix + "_is_sold"));
        columns.add(Column.aliased("sell_transaction_id", table, columnPrefix + "_sell_transaction_id"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("last_modified_by", table, columnPrefix + "_last_modified_by"));
        columns.add(Column.aliased("last_modified_date", table, columnPrefix + "_last_modified_date"));

        columns.add(Column.aliased("voucher_type_id", table, columnPrefix + "_voucher_type_id"));
        columns.add(Column.aliased("voucher_company_id", table, columnPrefix + "_voucher_company_id"));
        return columns;
    }
}
