package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.Country;
import ly.post.dinar.domain.criteria.CountryCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.CountryRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Country entity.
 */
@SuppressWarnings("unused")
class CountryRepositoryInternalImpl extends SimpleR2dbcRepository<Country, Long> implements CountryRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final CountryRowMapper countryMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("country", EntityManager.ENTITY_ALIAS);

    public CountryRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        CountryRowMapper countryMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Country.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.countryMapper = countryMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<Country> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Country> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = CountrySqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Country.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Country> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Country> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Country process(Row row, RowMetadata metadata) {
        Country entity = countryMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends Country> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<Country> findByCriteria(CountryCriteria countryCriteria, Pageable page) {
        return createQuery(page, buildConditions(countryCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(CountryCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(CountryCriteria criteria) {
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
            if (criteria.getIso2() != null) {
                builder.buildFilterConditionForField(criteria.getIso2(), entityTable.column("iso_2"));
            }
            if (criteria.getIso3() != null) {
                builder.buildFilterConditionForField(criteria.getIso3(), entityTable.column("iso_3"));
            }
            if (criteria.getIsoNo() != null) {
                builder.buildFilterConditionForField(criteria.getIsoNo(), entityTable.column("iso_no"));
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
