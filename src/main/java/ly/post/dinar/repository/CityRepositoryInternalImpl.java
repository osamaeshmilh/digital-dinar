package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.City;
import ly.post.dinar.domain.criteria.CityCriteria;
import ly.post.dinar.repository.rowmapper.CityRowMapper;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.CountryRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the City entity.
 */
@SuppressWarnings("unused")
class CityRepositoryInternalImpl extends SimpleR2dbcRepository<City, Long> implements CityRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final CountryRowMapper countryMapper;
    private final CityRowMapper cityMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("city", EntityManager.ENTITY_ALIAS);
    private static final Table countryTable = Table.aliased("country", "country");

    public CityRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        CountryRowMapper countryMapper,
        CityRowMapper cityMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(City.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.countryMapper = countryMapper;
        this.cityMapper = cityMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<City> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<City> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = CitySqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(CountrySqlHelper.getColumns(countryTable, "country"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(countryTable)
            .on(Column.create("country_id", entityTable))
            .equals(Column.create("id", countryTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, City.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<City> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<City> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<City> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<City> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<City> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private City process(Row row, RowMetadata metadata) {
        City entity = cityMapper.apply(row, "e");
        entity.setCountry(countryMapper.apply(row, "country"));
        return entity;
    }

    @Override
    public <S extends City> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<City> findByCriteria(CityCriteria cityCriteria, Pageable page) {
        return createQuery(page, buildConditions(cityCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(CityCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(CityCriteria criteria) {
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
            if (criteria.getCountryId() != null) {
                builder.buildFilterConditionForField(criteria.getCountryId(), countryTable.column("id"));
            }
        }
        return builder.buildConditions();
    }
}
