package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class WalletTransactionSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("transaction_no", table, columnPrefix + "_transaction_no"));
        columns.add(Column.aliased("amount", table, columnPrefix + "_amount"));
        columns.add(Column.aliased("wallet_action", table, columnPrefix + "_wallet_action"));
        columns.add(Column.aliased("user_previous_transaction_no", table, columnPrefix + "_user_previous_transaction_no"));
        columns.add(Column.aliased("total_before_action", table, columnPrefix + "_total_before_action"));
        columns.add(Column.aliased("total_after_action", table, columnPrefix + "_total_after_action"));
        columns.add(Column.aliased("payment_type", table, columnPrefix + "_payment_type"));
        columns.add(Column.aliased("payment_reference", table, columnPrefix + "_payment_reference"));
        columns.add(Column.aliased("notes", table, columnPrefix + "_notes"));
        columns.add(Column.aliased("owner_id", table, columnPrefix + "_owner_id"));
        columns.add(Column.aliased("wallet_owner_type", table, columnPrefix + "_wallet_owner_type"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("last_modified_by", table, columnPrefix + "_last_modified_by"));
        columns.add(Column.aliased("last_modified_date", table, columnPrefix + "_last_modified_date"));

        columns.add(Column.aliased("transaction_id", table, columnPrefix + "_transaction_id"));
        return columns;
    }
}
