package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.ViewLog;
import ly.post.dinar.domain.criteria.ViewLogCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.ViewLogRowMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoin;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.service.ConditionBuilder;

/**
 * Spring Data R2DBC custom repository implementation for the ViewLog entity.
 */
@SuppressWarnings("unused")
class ViewLogRepositoryInternalImpl extends SimpleR2dbcRepository<ViewLog, Long> implements ViewLogRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final ViewLogRowMapper viewlogMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("view_log", EntityManager.ENTITY_ALIAS);

    public ViewLogRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        ViewLogRowMapper viewlogMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(ViewLog.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.viewlogMapper = viewlogMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<ViewLog> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<ViewLog> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = ViewLogSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, ViewLog.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<ViewLog> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<ViewLog> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private ViewLog process(Row row, RowMetadata metadata) {
        ViewLog entity = viewlogMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends ViewLog> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<ViewLog> findByCriteria(ViewLogCriteria viewLogCriteria, Pageable page) {
        return createQuery(page, buildConditions(viewLogCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(ViewLogCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(ViewLogCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getEntityName() != null) {
                builder.buildFilterConditionForField(criteria.getEntityName(), entityTable.column("entity_name"));
            }
            if (criteria.getEntityId() != null) {
                builder.buildFilterConditionForField(criteria.getEntityId(), entityTable.column("entity_id"));
            }
        }
        return builder.buildConditions();
    }
}
