package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.Slider;
import ly.post.dinar.domain.criteria.SliderCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.SliderRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Slider entity.
 */
@SuppressWarnings("unused")
class SliderRepositoryInternalImpl extends SimpleR2dbcRepository<Slider, Long> implements SliderRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final SliderRowMapper sliderMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("slider", EntityManager.ENTITY_ALIAS);

    public SliderRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        SliderRowMapper sliderMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Slider.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.sliderMapper = sliderMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<Slider> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Slider> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = SliderSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Slider.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Slider> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Slider> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Slider process(Row row, RowMetadata metadata) {
        Slider entity = sliderMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends Slider> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<Slider> findByCriteria(SliderCriteria sliderCriteria, Pageable page) {
        return createQuery(page, buildConditions(sliderCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(SliderCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(SliderCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getDetails() != null) {
                builder.buildFilterConditionForField(criteria.getDetails(), entityTable.column("details"));
            }
            if (criteria.getMenuOrder() != null) {
                builder.buildFilterConditionForField(criteria.getMenuOrder(), entityTable.column("menu_order"));
            }
            if (criteria.getImageFileUrl() != null) {
                builder.buildFilterConditionForField(criteria.getImageFileUrl(), entityTable.column("image_file_url"));
            }
            if (criteria.getUrl() != null) {
                builder.buildFilterConditionForField(criteria.getUrl(), entityTable.column("url"));
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
