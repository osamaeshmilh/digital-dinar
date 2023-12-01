package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class WalletProfileSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("name_ar", table, columnPrefix + "_name_ar"));
        columns.add(Column.aliased("name_en", table, columnPrefix + "_name_en"));
        columns.add(
            Column.aliased("has_transfer_amount_limit_per_transaction", table, columnPrefix + "_has_transfer_amount_limit_per_transaction")
        );
        columns.add(
            Column.aliased("transfer_amount_limit_per_transaction", table, columnPrefix + "_transfer_amount_limit_per_transaction")
        );
        columns.add(Column.aliased("has_daily_transfer_amount_limit", table, columnPrefix + "_has_daily_transfer_amount_limit"));
        columns.add(Column.aliased("daily_transfer_amount_limit", table, columnPrefix + "_daily_transfer_amount_limit"));
        columns.add(Column.aliased("can_transfer_to_consumer_wallet", table, columnPrefix + "_can_transfer_to_consumer_wallet"));
        columns.add(Column.aliased("can_transfer_to_merchant_wallet", table, columnPrefix + "_can_transfer_to_merchant_wallet"));
        columns.add(Column.aliased("can_transfer_to_consumer", table, columnPrefix + "_can_transfer_to_consumer"));
        columns.add(Column.aliased("can_transfer_to_bank", table, columnPrefix + "_can_transfer_to_bank"));
        columns.add(Column.aliased("can_buy_vouchers", table, columnPrefix + "_can_buy_vouchers"));
        columns.add(Column.aliased("transfer_to_consumer_wallet_fees", table, columnPrefix + "_transfer_to_consumer_wallet_fees"));
        columns.add(Column.aliased("transfer_to_merchant_wallet_fees", table, columnPrefix + "_transfer_to_merchant_wallet_fees"));
        columns.add(Column.aliased("transfer_to_consumer_fees", table, columnPrefix + "_transfer_to_consumer_fees"));
        columns.add(Column.aliased("transfer_to_bank_fees", table, columnPrefix + "_transfer_to_bank_fees"));
        columns.add(Column.aliased("buy_vouchers_fees", table, columnPrefix + "_buy_vouchers_fees"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("last_modified_by", table, columnPrefix + "_last_modified_by"));
        columns.add(Column.aliased("last_modified_date", table, columnPrefix + "_last_modified_date"));

        return columns;
    }
}
