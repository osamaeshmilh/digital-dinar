package ly.post.dinar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class MerchantSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("arabic_first_name", table, columnPrefix + "_arabic_first_name"));
        columns.add(Column.aliased("arabic_middle_name", table, columnPrefix + "_arabic_middle_name"));
        columns.add(Column.aliased("arabic_last_name", table, columnPrefix + "_arabic_last_name"));
        columns.add(Column.aliased("english_first_name", table, columnPrefix + "_english_first_name"));
        columns.add(Column.aliased("english_middle_name", table, columnPrefix + "_english_middle_name"));
        columns.add(Column.aliased("english_last_name", table, columnPrefix + "_english_last_name"));
        columns.add(Column.aliased("commercial_registry_no", table, columnPrefix + "_commercial_registry_no"));
        columns.add(Column.aliased("commercial_licence_no", table, columnPrefix + "_commercial_licence_no"));
        columns.add(Column.aliased("birth_date", table, columnPrefix + "_birth_date"));
        columns.add(Column.aliased("birth_place", table, columnPrefix + "_birth_place"));
        columns.add(Column.aliased("national_no", table, columnPrefix + "_national_no"));
        columns.add(Column.aliased("register_no", table, columnPrefix + "_register_no"));
        columns.add(Column.aliased("gender", table, columnPrefix + "_gender"));
        columns.add(Column.aliased("id_type", table, columnPrefix + "_id_type"));
        columns.add(Column.aliased("id_no", table, columnPrefix + "_id_no"));
        columns.add(Column.aliased("mobile_no", table, columnPrefix + "_mobile_no"));
        columns.add(Column.aliased("verified_by_email_otp", table, columnPrefix + "_verified_by_email_otp"));
        columns.add(Column.aliased("verified_by_mobile_no_otp", table, columnPrefix + "_verified_by_mobile_no_otp"));
        columns.add(Column.aliased("email", table, columnPrefix + "_email"));
        columns.add(Column.aliased("nid_status", table, columnPrefix + "_nid_status"));
        columns.add(Column.aliased("is_foreign", table, columnPrefix + "_is_foreign"));
        columns.add(Column.aliased("is_mobile_connected_with_nid", table, columnPrefix + "_is_mobile_connected_with_nid"));
        columns.add(Column.aliased("wallet_owner_status", table, columnPrefix + "_wallet_owner_status"));
        columns.add(Column.aliased("image_file", table, columnPrefix + "_image_file"));
        columns.add(Column.aliased("image_file_content_type", table, columnPrefix + "_image_file_content_type"));
        columns.add(Column.aliased("image_url_file", table, columnPrefix + "_image_url_file"));
        columns.add(Column.aliased("post_code", table, columnPrefix + "_post_code"));
        columns.add(Column.aliased("address", table, columnPrefix + "_address"));
        columns.add(Column.aliased("lat", table, columnPrefix + "_lat"));
        columns.add(Column.aliased("lng", table, columnPrefix + "_lng"));
        columns.add(Column.aliased("notes", table, columnPrefix + "_notes"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("last_modified_by", table, columnPrefix + "_last_modified_by"));
        columns.add(Column.aliased("last_modified_date", table, columnPrefix + "_last_modified_date"));

        columns.add(Column.aliased("user_id", table, columnPrefix + "_user_id"));
        columns.add(Column.aliased("category_id", table, columnPrefix + "_category_id"));
        columns.add(Column.aliased("city_id", table, columnPrefix + "_city_id"));
        columns.add(Column.aliased("wallet_profile_id", table, columnPrefix + "_wallet_profile_id"));
        return columns;
    }
}
