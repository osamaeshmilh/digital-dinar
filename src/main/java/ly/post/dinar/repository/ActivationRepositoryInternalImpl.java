package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.Activation;
import ly.post.dinar.domain.criteria.ActivationCriteria;
import ly.post.dinar.repository.rowmapper.ActivationRowMapper;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
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
 * Spring Data R2DBC custom repository implementation for the Activation entity.
 */
@SuppressWarnings("unused")
class ActivationRepositoryInternalImpl extends SimpleR2dbcRepository<Activation, Long> implements ActivationRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final ActivationRowMapper activationMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("activation", EntityManager.ENTITY_ALIAS);

    public ActivationRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        ActivationRowMapper activationMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Activation.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.activationMapper = activationMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<Activation> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Activation> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = ActivationSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Activation.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Activation> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Activation> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Activation process(Row row, RowMetadata metadata) {
        Activation entity = activationMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends Activation> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<Activation> findByCriteria(ActivationCriteria activationCriteria, Pageable page) {
        return createQuery(page, buildConditions(activationCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(ActivationCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(ActivationCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getMobileNo() != null) {
                builder.buildFilterConditionForField(criteria.getMobileNo(), entityTable.column("mobile_no"));
            }
            if (criteria.getEmail() != null) {
                builder.buildFilterConditionForField(criteria.getEmail(), entityTable.column("email"));
            }
            if (criteria.getCode() != null) {
                builder.buildFilterConditionForField(criteria.getCode(), entityTable.column("code"));
            }
            if (criteria.getSentOn() != null) {
                builder.buildFilterConditionForField(criteria.getSentOn(), entityTable.column("sent_on"));
            }
            if (criteria.getValidUntil() != null) {
                builder.buildFilterConditionForField(criteria.getValidUntil(), entityTable.column("valid_until"));
            }
            if (criteria.getIsUsed() != null) {
                builder.buildFilterConditionForField(criteria.getIsUsed(), entityTable.column("is_used"));
            }
        }
        return builder.buildConditions();
    }
}
