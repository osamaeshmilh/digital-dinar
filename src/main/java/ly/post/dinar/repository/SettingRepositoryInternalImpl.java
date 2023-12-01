package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.Setting;
import ly.post.dinar.domain.criteria.SettingCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.SettingRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Setting entity.
 */
@SuppressWarnings("unused")
class SettingRepositoryInternalImpl extends SimpleR2dbcRepository<Setting, Long> implements SettingRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final SettingRowMapper settingMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("setting", EntityManager.ENTITY_ALIAS);

    public SettingRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        SettingRowMapper settingMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Setting.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.settingMapper = settingMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<Setting> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Setting> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = SettingSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Setting.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Setting> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Setting> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Setting process(Row row, RowMetadata metadata) {
        Setting entity = settingMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends Setting> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<Setting> findByCriteria(SettingCriteria settingCriteria, Pageable page) {
        return createQuery(page, buildConditions(settingCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(SettingCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(SettingCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getKey() != null) {
                builder.buildFilterConditionForField(criteria.getKey(), entityTable.column("jhi_key"));
            }
            if (criteria.getValue() != null) {
                builder.buildFilterConditionForField(criteria.getValue(), entityTable.column("value"));
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
        }
        return builder.buildConditions();
    }
}
