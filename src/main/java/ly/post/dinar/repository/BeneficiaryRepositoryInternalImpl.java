package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.Beneficiary;
import ly.post.dinar.domain.criteria.BeneficiaryCriteria;
import ly.post.dinar.repository.rowmapper.BeneficiaryRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Beneficiary entity.
 */
@SuppressWarnings("unused")
class BeneficiaryRepositoryInternalImpl extends SimpleR2dbcRepository<Beneficiary, Long> implements BeneficiaryRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final BeneficiaryRowMapper beneficiaryMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("beneficiary", EntityManager.ENTITY_ALIAS);

    public BeneficiaryRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        BeneficiaryRowMapper beneficiaryMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Beneficiary.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.beneficiaryMapper = beneficiaryMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<Beneficiary> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Beneficiary> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = BeneficiarySqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Beneficiary.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Beneficiary> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Beneficiary> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Beneficiary process(Row row, RowMetadata metadata) {
        Beneficiary entity = beneficiaryMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends Beneficiary> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<Beneficiary> findByCriteria(BeneficiaryCriteria beneficiaryCriteria, Pageable page) {
        return createQuery(page, buildConditions(beneficiaryCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(BeneficiaryCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(BeneficiaryCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getArabicFirstName() != null) {
                builder.buildFilterConditionForField(criteria.getArabicFirstName(), entityTable.column("arabic_first_name"));
            }
            if (criteria.getArabicLastName() != null) {
                builder.buildFilterConditionForField(criteria.getArabicLastName(), entityTable.column("arabic_last_name"));
            }
            if (criteria.getEnglishFirstName() != null) {
                builder.buildFilterConditionForField(criteria.getEnglishFirstName(), entityTable.column("english_first_name"));
            }
            if (criteria.getEnglishLastName() != null) {
                builder.buildFilterConditionForField(criteria.getEnglishLastName(), entityTable.column("english_last_name"));
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
            if (criteria.getEmail() != null) {
                builder.buildFilterConditionForField(criteria.getEmail(), entityTable.column("email"));
            }
            if (criteria.getNotes() != null) {
                builder.buildFilterConditionForField(criteria.getNotes(), entityTable.column("notes"));
            }
            if (criteria.getCreatedByUserId() != null) {
                builder.buildFilterConditionForField(criteria.getCreatedByUserId(), entityTable.column("created_by_user_id"));
            }
            if (criteria.getIsVerified() != null) {
                builder.buildFilterConditionForField(criteria.getIsVerified(), entityTable.column("is_verified"));
            }
            if (criteria.getHasTransferred() != null) {
                builder.buildFilterConditionForField(criteria.getHasTransferred(), entityTable.column("has_transferred"));
            }
        }
        return builder.buildConditions();
    }
}
