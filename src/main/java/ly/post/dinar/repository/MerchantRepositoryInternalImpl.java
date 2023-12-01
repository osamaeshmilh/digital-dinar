package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.Merchant;
import ly.post.dinar.domain.criteria.MerchantCriteria;
import ly.post.dinar.repository.rowmapper.CategoryRowMapper;
import ly.post.dinar.repository.rowmapper.CityRowMapper;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.MerchantRowMapper;
import ly.post.dinar.repository.rowmapper.UserRowMapper;
import ly.post.dinar.repository.rowmapper.WalletProfileRowMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoinCondition;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.service.ConditionBuilder;

/**
 * Spring Data R2DBC custom repository implementation for the Merchant entity.
 */
@SuppressWarnings("unused")
class MerchantRepositoryInternalImpl extends SimpleR2dbcRepository<Merchant, Long> implements MerchantRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final UserRowMapper userMapper;
    private final CategoryRowMapper categoryMapper;
    private final CityRowMapper cityMapper;
    private final WalletProfileRowMapper walletprofileMapper;
    private final MerchantRowMapper merchantMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("merchant", EntityManager.ENTITY_ALIAS);
    private static final Table userTable = Table.aliased("jhi_user", "e_user");
    private static final Table categoryTable = Table.aliased("category", "category");
    private static final Table cityTable = Table.aliased("city", "city");
    private static final Table walletProfileTable = Table.aliased("wallet_profile", "walletProfile");

    public MerchantRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        UserRowMapper userMapper,
        CategoryRowMapper categoryMapper,
        CityRowMapper cityMapper,
        WalletProfileRowMapper walletprofileMapper,
        MerchantRowMapper merchantMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Merchant.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
        this.cityMapper = cityMapper;
        this.walletprofileMapper = walletprofileMapper;
        this.merchantMapper = merchantMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<Merchant> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Merchant> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = MerchantSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(UserSqlHelper.getColumns(userTable, "user"));
        columns.addAll(CategorySqlHelper.getColumns(categoryTable, "category"));
        columns.addAll(CitySqlHelper.getColumns(cityTable, "city"));
        columns.addAll(WalletProfileSqlHelper.getColumns(walletProfileTable, "walletProfile"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(userTable)
            .on(Column.create("user_id", entityTable))
            .equals(Column.create("id", userTable))
            .leftOuterJoin(categoryTable)
            .on(Column.create("category_id", entityTable))
            .equals(Column.create("id", categoryTable))
            .leftOuterJoin(cityTable)
            .on(Column.create("city_id", entityTable))
            .equals(Column.create("id", cityTable))
            .leftOuterJoin(walletProfileTable)
            .on(Column.create("wallet_profile_id", entityTable))
            .equals(Column.create("id", walletProfileTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Merchant.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Merchant> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Merchant> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<Merchant> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<Merchant> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<Merchant> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private Merchant process(Row row, RowMetadata metadata) {
        Merchant entity = merchantMapper.apply(row, "e");
        entity.setUser(userMapper.apply(row, "user"));
        entity.setCategory(categoryMapper.apply(row, "category"));
        entity.setCity(cityMapper.apply(row, "city"));
        entity.setWalletProfile(walletprofileMapper.apply(row, "walletProfile"));
        return entity;
    }

    @Override
    public <S extends Merchant> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<Merchant> findByCriteria(MerchantCriteria merchantCriteria, Pageable page) {
        return createQuery(page, buildConditions(merchantCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(MerchantCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(MerchantCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getArabicFirstName() != null) {
                builder.buildFilterConditionForField(criteria.getArabicFirstName(), entityTable.column("arabic_first_name"));
            }
            if (criteria.getArabicMiddleName() != null) {
                builder.buildFilterConditionForField(criteria.getArabicMiddleName(), entityTable.column("arabic_middle_name"));
            }
            if (criteria.getArabicLastName() != null) {
                builder.buildFilterConditionForField(criteria.getArabicLastName(), entityTable.column("arabic_last_name"));
            }
            if (criteria.getEnglishFirstName() != null) {
                builder.buildFilterConditionForField(criteria.getEnglishFirstName(), entityTable.column("english_first_name"));
            }
            if (criteria.getEnglishMiddleName() != null) {
                builder.buildFilterConditionForField(criteria.getEnglishMiddleName(), entityTable.column("english_middle_name"));
            }
            if (criteria.getEnglishLastName() != null) {
                builder.buildFilterConditionForField(criteria.getEnglishLastName(), entityTable.column("english_last_name"));
            }
            if (criteria.getCommercialRegistryNo() != null) {
                builder.buildFilterConditionForField(criteria.getCommercialRegistryNo(), entityTable.column("commercial_registry_no"));
            }
            if (criteria.getCommercialLicenceNo() != null) {
                builder.buildFilterConditionForField(criteria.getCommercialLicenceNo(), entityTable.column("commercial_licence_no"));
            }
            if (criteria.getBirthDate() != null) {
                builder.buildFilterConditionForField(criteria.getBirthDate(), entityTable.column("birth_date"));
            }
            if (criteria.getBirthPlace() != null) {
                builder.buildFilterConditionForField(criteria.getBirthPlace(), entityTable.column("birth_place"));
            }
            if (criteria.getNationalNo() != null) {
                builder.buildFilterConditionForField(criteria.getNationalNo(), entityTable.column("national_no"));
            }
            if (criteria.getRegisterNo() != null) {
                builder.buildFilterConditionForField(criteria.getRegisterNo(), entityTable.column("register_no"));
            }
            if (criteria.getGender() != null) {
                builder.buildFilterConditionForField(criteria.getGender(), entityTable.column("gender"));
            }
            if (criteria.getIdType() != null) {
                builder.buildFilterConditionForField(criteria.getIdType(), entityTable.column("id_type"));
            }
            if (criteria.getIdNo() != null) {
                builder.buildFilterConditionForField(criteria.getIdNo(), entityTable.column("id_no"));
            }
            if (criteria.getMobileNo() != null) {
                builder.buildFilterConditionForField(criteria.getMobileNo(), entityTable.column("mobile_no"));
            }
            if (criteria.getVerifiedByEmailOtp() != null) {
                builder.buildFilterConditionForField(criteria.getVerifiedByEmailOtp(), entityTable.column("verified_by_email_otp"));
            }
            if (criteria.getVerifiedByMobileNoOtp() != null) {
                builder.buildFilterConditionForField(criteria.getVerifiedByMobileNoOtp(), entityTable.column("verified_by_mobile_no_otp"));
            }
            if (criteria.getEmail() != null) {
                builder.buildFilterConditionForField(criteria.getEmail(), entityTable.column("email"));
            }
            if (criteria.getNidStatus() != null) {
                builder.buildFilterConditionForField(criteria.getNidStatus(), entityTable.column("nid_status"));
            }
            if (criteria.getIsForeign() != null) {
                builder.buildFilterConditionForField(criteria.getIsForeign(), entityTable.column("is_foreign"));
            }
            if (criteria.getIsMobileConnectedWithNid() != null) {
                builder.buildFilterConditionForField(
                    criteria.getIsMobileConnectedWithNid(),
                    entityTable.column("is_mobile_connected_with_nid")
                );
            }
            if (criteria.getWalletOwnerStatus() != null) {
                builder.buildFilterConditionForField(criteria.getWalletOwnerStatus(), entityTable.column("wallet_owner_status"));
            }
            if (criteria.getImageUrlFile() != null) {
                builder.buildFilterConditionForField(criteria.getImageUrlFile(), entityTable.column("image_url_file"));
            }
            if (criteria.getPostCode() != null) {
                builder.buildFilterConditionForField(criteria.getPostCode(), entityTable.column("post_code"));
            }
            if (criteria.getAddress() != null) {
                builder.buildFilterConditionForField(criteria.getAddress(), entityTable.column("address"));
            }
            if (criteria.getLat() != null) {
                builder.buildFilterConditionForField(criteria.getLat(), entityTable.column("lat"));
            }
            if (criteria.getLng() != null) {
                builder.buildFilterConditionForField(criteria.getLng(), entityTable.column("lng"));
            }
            if (criteria.getNotes() != null) {
                builder.buildFilterConditionForField(criteria.getNotes(), entityTable.column("notes"));
            }
            if (criteria.getUserId() != null) {
                builder.buildFilterConditionForField(criteria.getUserId(), userTable.column("id"));
            }
            if (criteria.getCategoryId() != null) {
                builder.buildFilterConditionForField(criteria.getCategoryId(), categoryTable.column("id"));
            }
            if (criteria.getCityId() != null) {
                builder.buildFilterConditionForField(criteria.getCityId(), cityTable.column("id"));
            }
            if (criteria.getWalletProfileId() != null) {
                builder.buildFilterConditionForField(criteria.getWalletProfileId(), walletProfileTable.column("id"));
            }
        }
        return builder.buildConditions();
    }
}
