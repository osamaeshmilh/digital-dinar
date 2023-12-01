package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.VoucherType;
import ly.post.dinar.domain.criteria.VoucherTypeCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.VoucherCompanyRowMapper;
import ly.post.dinar.repository.rowmapper.VoucherTypeRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the VoucherType entity.
 */
@SuppressWarnings("unused")
class VoucherTypeRepositoryInternalImpl extends SimpleR2dbcRepository<VoucherType, Long> implements VoucherTypeRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final VoucherCompanyRowMapper vouchercompanyMapper;
    private final VoucherTypeRowMapper vouchertypeMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("voucher_type", EntityManager.ENTITY_ALIAS);
    private static final Table voucherCompanyTable = Table.aliased("voucher_company", "voucherCompany");

    public VoucherTypeRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        VoucherCompanyRowMapper vouchercompanyMapper,
        VoucherTypeRowMapper vouchertypeMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(VoucherType.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.vouchercompanyMapper = vouchercompanyMapper;
        this.vouchertypeMapper = vouchertypeMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<VoucherType> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<VoucherType> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = VoucherTypeSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(VoucherCompanySqlHelper.getColumns(voucherCompanyTable, "voucherCompany"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(voucherCompanyTable)
            .on(Column.create("voucher_company_id", entityTable))
            .equals(Column.create("id", voucherCompanyTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, VoucherType.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<VoucherType> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<VoucherType> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<VoucherType> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<VoucherType> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<VoucherType> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private VoucherType process(Row row, RowMetadata metadata) {
        VoucherType entity = vouchertypeMapper.apply(row, "e");
        entity.setVoucherCompany(vouchercompanyMapper.apply(row, "voucherCompany"));
        return entity;
    }

    @Override
    public <S extends VoucherType> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<VoucherType> findByCriteria(VoucherTypeCriteria voucherTypeCriteria, Pageable page) {
        return createQuery(page, buildConditions(voucherTypeCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(VoucherTypeCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(VoucherTypeCriteria criteria) {
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
            if (criteria.getSlug() != null) {
                builder.buildFilterConditionForField(criteria.getSlug(), entityTable.column("slug"));
            }
            if (criteria.getAmount() != null) {
                builder.buildFilterConditionForField(criteria.getAmount(), entityTable.column("amount"));
            }
            if (criteria.getMenuOrder() != null) {
                builder.buildFilterConditionForField(criteria.getMenuOrder(), entityTable.column("menu_order"));
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
            if (criteria.getVoucherCompanyId() != null) {
                builder.buildFilterConditionForField(criteria.getVoucherCompanyId(), voucherCompanyTable.column("id"));
            }
        }
        return builder.buildConditions();
    }
}
