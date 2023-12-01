package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class TransactionSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("transaction_reference", table, columnPrefix + "_transaction_reference"));
        columns.add(Column.aliased("transaction_secret", table, columnPrefix + "_transaction_secret"));
        columns.add(Column.aliased("transaction_otp", table, columnPrefix + "_transaction_otp"));
        columns.add(Column.aliased("payment_type", table, columnPrefix + "_payment_type"));
        columns.add(Column.aliased("transaction_status", table, columnPrefix + "_transaction_status"));
        columns.add(Column.aliased("transaction_type", table, columnPrefix + "_transaction_type"));
        columns.add(Column.aliased("vendor_reference", table, columnPrefix + "_vendor_reference"));
        columns.add(Column.aliased("vendor_message", table, columnPrefix + "_vendor_message"));
        columns.add(Column.aliased("amount", table, columnPrefix + "_amount"));
        columns.add(Column.aliased("fees", table, columnPrefix + "_fees"));
        columns.add(Column.aliased("total", table, columnPrefix + "_total"));
        columns.add(Column.aliased("notes", table, columnPrefix + "_notes"));
        columns.add(Column.aliased("sender_mobile_no", table, columnPrefix + "_sender_mobile_no"));
        columns.add(Column.aliased("sender_name", table, columnPrefix + "_sender_name"));
        columns.add(Column.aliased("sender_type", table, columnPrefix + "_sender_type"));
        columns.add(Column.aliased("sender_id_no", table, columnPrefix + "_sender_id_no"));
        columns.add(Column.aliased("sender_id", table, columnPrefix + "_sender_id"));
        columns.add(Column.aliased("receiver_name", table, columnPrefix + "_receiver_name"));
        columns.add(Column.aliased("receiver_mobile_no", table, columnPrefix + "_receiver_mobile_no"));
        columns.add(Column.aliased("receiver_type", table, columnPrefix + "_receiver_type"));
        columns.add(Column.aliased("receiver_id", table, columnPrefix + "_receiver_id"));
        columns.add(Column.aliased("receiver_id_no", table, columnPrefix + "_receiver_id_no"));
        columns.add(Column.aliased("created_by_user_id", table, columnPrefix + "_created_by_user_id"));

        return columns;
    }
}
