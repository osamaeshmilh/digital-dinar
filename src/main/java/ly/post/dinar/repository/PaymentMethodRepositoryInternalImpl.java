package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.PaymentMethod;
import ly.post.dinar.domain.criteria.PaymentMethodCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.PaymentMethodRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the PaymentMethod entity.
 */
@SuppressWarnings("unused")
class PaymentMethodRepositoryInternalImpl extends SimpleR2dbcRepository<PaymentMethod, Long> implements PaymentMethodRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final PaymentMethodRowMapper paymentmethodMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("payment_method", EntityManager.ENTITY_ALIAS);

    public PaymentMethodRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        PaymentMethodRowMapper paymentmethodMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(PaymentMethod.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.paymentmethodMapper = paymentmethodMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<PaymentMethod> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<PaymentMethod> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = PaymentMethodSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, PaymentMethod.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<PaymentMethod> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<PaymentMethod> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private PaymentMethod process(Row row, RowMetadata metadata) {
        PaymentMethod entity = paymentmethodMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends PaymentMethod> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<PaymentMethod> findByCriteria(PaymentMethodCriteria paymentMethodCriteria, Pageable page) {
        return createQuery(page, buildConditions(paymentMethodCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(PaymentMethodCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(PaymentMethodCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getName() != null) {
                builder.buildFilterConditionForField(criteria.getName(), entityTable.column("name"));
            }
            if (criteria.getNameAr() != null) {
                builder.buildFilterConditionForField(criteria.getNameAr(), entityTable.column("name_ar"));
            }
            if (criteria.getNameEn() != null) {
                builder.buildFilterConditionForField(criteria.getNameEn(), entityTable.column("name_en"));
            }
            if (criteria.getMenuOrder() != null) {
                builder.buildFilterConditionForField(criteria.getMenuOrder(), entityTable.column("menu_order"));
            }
            if (criteria.getImageFileUrl() != null) {
                builder.buildFilterConditionForField(criteria.getImageFileUrl(), entityTable.column("image_file_url"));
            }
            if (criteria.getDetails() != null) {
                builder.buildFilterConditionForField(criteria.getDetails(), entityTable.column("details"));
            }
            if (criteria.getFeePercentage() != null) {
                builder.buildFilterConditionForField(criteria.getFeePercentage(), entityTable.column("fee_percentage"));
            }
            if (criteria.getPaymentType() != null) {
                builder.buildFilterConditionForField(criteria.getPaymentType(), entityTable.column("payment_type"));
            }
            if (criteria.getNotes() != null) {
                builder.buildFilterConditionForField(criteria.getNotes(), entityTable.column("notes"));
            }
        }
        return builder.buildConditions();
    }
}
