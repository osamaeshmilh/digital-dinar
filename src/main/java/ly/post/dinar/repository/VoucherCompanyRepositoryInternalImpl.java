package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.VoucherCompany;
import ly.post.dinar.domain.criteria.VoucherCompanyCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.UserRowMapper;
import ly.post.dinar.repository.rowmapper.VoucherCompanyRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the VoucherCompany entity.
 */
@SuppressWarnings("unused")
class VoucherCompanyRepositoryInternalImpl extends SimpleR2dbcRepository<VoucherCompany, Long> implements VoucherCompanyRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final UserRowMapper userMapper;
    private final VoucherCompanyRowMapper vouchercompanyMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("voucher_company", EntityManager.ENTITY_ALIAS);
    private static final Table userTable = Table.aliased("jhi_user", "e_user");

    public VoucherCompanyRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        UserRowMapper userMapper,
        VoucherCompanyRowMapper vouchercompanyMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(VoucherCompany.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.userMapper = userMapper;
        this.vouchercompanyMapper = vouchercompanyMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<VoucherCompany> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<VoucherCompany> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = VoucherCompanySqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(UserSqlHelper.getColumns(userTable, "user"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(userTable)
            .on(Column.create("user_id", entityTable))
            .equals(Column.create("id", userTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, VoucherCompany.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<VoucherCompany> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<VoucherCompany> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private VoucherCompany process(Row row, RowMetadata metadata) {
        VoucherCompany entity = vouchercompanyMapper.apply(row, "e");
        entity.setUser(userMapper.apply(row, "user"));
        return entity;
    }

    @Override
    public <S extends VoucherCompany> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<VoucherCompany> findByCriteria(VoucherCompanyCriteria voucherCompanyCriteria, Pageable page) {
        return createQuery(page, buildConditions(voucherCompanyCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(VoucherCompanyCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(VoucherCompanyCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getNameAr() != null) {
                builder.buildFilterConditionForField(criteria.getNameAr(), entityTable.column("name_ar"));
            }
            if (criteria.getNameEn() != null) {
                builder.buildFilterConditionForField(criteria.getNameEn(), entityTable.column("name_en"));
            }
            if (criteria.getMobileNo() != null) {
                builder.buildFilterConditionForField(criteria.getMobileNo(), entityTable.column("mobile_no"));
            }
            if (criteria.getEmail() != null) {
                builder.buildFilterConditionForField(criteria.getEmail(), entityTable.column("email"));
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
            if (criteria.getNotes() != null) {
                builder.buildFilterConditionForField(criteria.getNotes(), entityTable.column("notes"));
            }
            if (criteria.getCreatedBy() != null) {
                builder.buildFilterConditionForField(criteria.getCreatedBy(), entityTable.column("created_by"));
            }
            if (criteria.getCreatedDate() != null) {
                builder.buildFilterConditionForField(criteria.getCreatedDate(), entityTable.column("created_date"));
            }
            if (criteria.getLastModifiedBy() != null) {
                builder.buildFilterConditionForField(criteria.getLastModifiedBy(), entityTable.column("last_modified_by"));
            }
            if (criteria.getLastModifiedDate() != null) {
                builder.buildFilterConditionForField(criteria.getLastModifiedDate(), entityTable.column("last_modified_date"));
            }
            if (criteria.getUserId() != null) {
                builder.buildFilterConditionForField(criteria.getUserId(), userTable.column("id"));
            }
        }
        return builder.buildConditions();
    }
}
