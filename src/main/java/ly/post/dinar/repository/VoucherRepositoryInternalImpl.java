package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.Voucher;
import ly.post.dinar.domain.criteria.VoucherCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.VoucherCompanyRowMapper;
import ly.post.dinar.repository.rowmapper.VoucherRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Voucher entity.
 */
@SuppressWarnings("unused")
class VoucherRepositoryInternalImpl extends SimpleR2dbcRepository<Voucher, Long> implements VoucherRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final VoucherTypeRowMapper vouchertypeMapper;
    private final VoucherCompanyRowMapper vouchercompanyMapper;
    private final VoucherRowMapper voucherMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("voucher", EntityManager.ENTITY_ALIAS);
    private static final Table voucherTypeTable = Table.aliased("voucher_type", "voucherType");
    private static final Table voucherCompanyTable = Table.aliased("voucher_company", "voucherCompany");

    public VoucherRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        VoucherTypeRowMapper vouchertypeMapper,
        VoucherCompanyRowMapper vouchercompanyMapper,
        VoucherRowMapper voucherMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Voucher.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.vouchertypeMapper = vouchertypeMapper;
        this.vouchercompanyMapper = vouchercompanyMapper;
        this.voucherMapper = voucherMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<Voucher> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Voucher> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = VoucherSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(VoucherTypeSqlHelper.getColumns(voucherTypeTable, "voucherType"));
        columns.addAll(VoucherCompanySqlHelper.getColumns(voucherCompanyTable, "voucherCompany"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(voucherTypeTable)
            .on(Column.create("voucher_type_id", entityTable))
            .equals(Column.create("id", voucherTypeTable))
            .leftOuterJoin(voucherCompanyTable)
            .on(Column.create("voucher_company_id", entityTable))
            .equals(Column.create("id", voucherCompanyTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Voucher.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Voucher> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Voucher> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<Voucher> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<Voucher> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<Voucher> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private Voucher process(Row row, RowMetadata metadata) {
        Voucher entity = voucherMapper.apply(row, "e");
        entity.setVoucherType(vouchertypeMapper.apply(row, "voucherType"));
        entity.setVoucherCompany(vouchercompanyMapper.apply(row, "voucherCompany"));
        return entity;
    }

    @Override
    public <S extends Voucher> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<Voucher> findByCriteria(VoucherCriteria voucherCriteria, Pageable page) {
        return createQuery(page, buildConditions(voucherCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(VoucherCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(VoucherCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getCode() != null) {
                builder.buildFilterConditionForField(criteria.getCode(), entityTable.column("code"));
            }
            if (criteria.getAmount() != null) {
                builder.buildFilterConditionForField(criteria.getAmount(), entityTable.column("amount"));
            }
            if (criteria.getSerialNumber() != null) {
                builder.buildFilterConditionForField(criteria.getSerialNumber(), entityTable.column("serial_number"));
            }
            if (criteria.getIsSold() != null) {
                builder.buildFilterConditionForField(criteria.getIsSold(), entityTable.column("is_sold"));
            }
            if (criteria.getSellTransactionId() != null) {
                builder.buildFilterConditionForField(criteria.getSellTransactionId(), entityTable.column("sell_transaction_id"));
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
            if (criteria.getVoucherTypeId() != null) {
                builder.buildFilterConditionForField(criteria.getVoucherTypeId(), voucherTypeTable.column("id"));
            }
            if (criteria.getVoucherCompanyId() != null) {
                builder.buildFilterConditionForField(criteria.getVoucherCompanyId(), voucherCompanyTable.column("id"));
            }
        }
        return builder.buildConditions();
    }
}
