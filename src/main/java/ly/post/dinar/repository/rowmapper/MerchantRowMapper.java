package ly.post.dinar.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.Instant;
import java.time.LocalDate;
import java.util.function.BiFunction;
import ly.post.dinar.domain.Merchant;
import ly.post.dinar.domain.enumeration.Gender;
import ly.post.dinar.domain.enumeration.IdType;
import ly.post.dinar.domain.enumeration.WalletOwnerStatus;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Merchant}, with proper type conversions.
 */
@Service
public class MerchantRowMapper implements BiFunction<Row, String, Merchant> {

    private final ColumnConverter converter;

    public MerchantRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Merchant} stored in the database.
     */
    @Override
    public Merchant apply(Row row, String prefix) {
        Merchant entity = new Merchant();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setArabicFirstName(converter.fromRow(row, prefix + "_arabic_first_name", String.class));
        entity.setArabicMiddleName(converter.fromRow(row, prefix + "_arabic_middle_name", String.class));
        entity.setArabicLastName(converter.fromRow(row, prefix + "_arabic_last_name", String.class));
        entity.setEnglishFirstName(converter.fromRow(row, prefix + "_english_first_name", String.class));
        entity.setEnglishMiddleName(converter.fromRow(row, prefix + "_english_middle_name", String.class));
        entity.setEnglishLastName(converter.fromRow(row, prefix + "_english_last_name", String.class));
        entity.setCommercialRegistryNo(converter.fromRow(row, prefix + "_commercial_registry_no", String.class));
        entity.setCommercialLicenceNo(converter.fromRow(row, prefix + "_commercial_licence_no", String.class));
        entity.setBirthDate(converter.fromRow(row, prefix + "_birth_date", LocalDate.class));
        entity.setBirthPlace(converter.fromRow(row, prefix + "_birth_place", String.class));
        entity.setNationalNo(converter.fromRow(row, prefix + "_national_no", String.class));
        entity.setRegisterNo(converter.fromRow(row, prefix + "_register_no", String.class));
        entity.setGender(converter.fromRow(row, prefix + "_gender", Gender.class));
        entity.setIdType(converter.fromRow(row, prefix + "_id_type", IdType.class));
        entity.setIdNo(converter.fromRow(row, prefix + "_id_no", String.class));
        entity.setMobileNo(converter.fromRow(row, prefix + "_mobile_no", String.class));
        entity.setVerifiedByEmailOtp(converter.fromRow(row, prefix + "_verified_by_email_otp", Boolean.class));
        entity.setVerifiedByMobileNoOtp(converter.fromRow(row, prefix + "_verified_by_mobile_no_otp", Boolean.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setNidStatus(converter.fromRow(row, prefix + "_nid_status", String.class));
        entity.setIsForeign(converter.fromRow(row, prefix + "_is_foreign", Boolean.class));
        entity.setIsMobileConnectedWithNid(converter.fromRow(row, prefix + "_is_mobile_connected_with_nid", Boolean.class));
        entity.setWalletOwnerStatus(converter.fromRow(row, prefix + "_wallet_owner_status", WalletOwnerStatus.class));
        entity.setImageFileContentType(converter.fromRow(row, prefix + "_image_file_content_type", String.class));
        entity.setImageFile(converter.fromRow(row, prefix + "_image_file", byte[].class));
        entity.setImageUrlFile(converter.fromRow(row, prefix + "_image_url_file", String.class));
        entity.setPostCode(converter.fromRow(row, prefix + "_post_code", String.class));
        entity.setAddress(converter.fromRow(row, prefix + "_address", String.class));
        entity.setLat(converter.fromRow(row, prefix + "_lat", Float.class));
        entity.setLng(converter.fromRow(row, prefix + "_lng", Float.class));
        entity.setNotes(converter.fromRow(row, prefix + "_notes", String.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", Instant.class));
        entity.setLastModifiedBy(converter.fromRow(row, prefix + "_last_modified_by", String.class));
        entity.setLastModifiedDate(converter.fromRow(row, prefix + "_last_modified_date", Instant.class));
        entity.setUserId(converter.fromRow(row, prefix + "_user_id", Long.class));
        entity.setCategoryId(converter.fromRow(row, prefix + "_category_id", Long.class));
        entity.setCityId(converter.fromRow(row, prefix + "_city_id", Long.class));
        entity.setWalletProfileId(converter.fromRow(row, prefix + "_wallet_profile_id", Long.class));
        return entity;
    }
}