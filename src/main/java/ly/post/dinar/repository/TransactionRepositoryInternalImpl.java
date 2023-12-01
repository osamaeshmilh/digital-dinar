package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.Transaction;
import ly.post.dinar.domain.criteria.TransactionCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.TransactionRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Transaction entity.
 */
@SuppressWarnings("unused")
class TransactionRepositoryInternalImpl extends SimpleR2dbcRepository<Transaction, Long> implements TransactionRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final TransactionRowMapper transactionMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("transaction", EntityManager.ENTITY_ALIAS);

    public TransactionRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        TransactionRowMapper transactionMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Transaction.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.transactionMapper = transactionMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<Transaction> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Transaction> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = TransactionSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Transaction.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Transaction> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Transaction> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Transaction process(Row row, RowMetadata metadata) {
        Transaction entity = transactionMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends Transaction> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<Transaction> findByCriteria(TransactionCriteria transactionCriteria, Pageable page) {
        return createQuery(page, buildConditions(transactionCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(TransactionCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(TransactionCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getTransactionReference() != null) {
                builder.buildFilterConditionForField(criteria.getTransactionReference(), entityTable.column("transaction_reference"));
            }
            if (criteria.getTransactionSecret() != null) {
                builder.buildFilterConditionForField(criteria.getTransactionSecret(), entityTable.column("transaction_secret"));
            }
            if (criteria.getTransactionOtp() != null) {
                builder.buildFilterConditionForField(criteria.getTransactionOtp(), entityTable.column("transaction_otp"));
            }
            if (criteria.getPaymentType() != null) {
                builder.buildFilterConditionForField(criteria.getPaymentType(), entityTable.column("payment_type"));
            }
            if (criteria.getTransactionStatus() != null) {
                builder.buildFilterConditionForField(criteria.getTransactionStatus(), entityTable.column("transaction_status"));
            }
            if (criteria.getTransactionType() != null) {
                builder.buildFilterConditionForField(criteria.getTransactionType(), entityTable.column("transaction_type"));
            }
            if (criteria.getVendorReference() != null) {
                builder.buildFilterConditionForField(criteria.getVendorReference(), entityTable.column("vendor_reference"));
            }
            if (criteria.getVendorMessage() != null) {
                builder.buildFilterConditionForField(criteria.getVendorMessage(), entityTable.column("vendor_message"));
            }
            if (criteria.getAmount() != null) {
                builder.buildFilterConditionForField(criteria.getAmount(), entityTable.column("amount"));
            }
            if (criteria.getFees() != null) {
                builder.buildFilterConditionForField(criteria.getFees(), entityTable.column("fees"));
            }
            if (criteria.getTotal() != null) {
                builder.buildFilterConditionForField(criteria.getTotal(), entityTable.column("total"));
            }
            if (criteria.getNotes() != null) {
                builder.buildFilterConditionForField(criteria.getNotes(), entityTable.column("notes"));
            }
            if (criteria.getSenderMobileNo() != null) {
                builder.buildFilterConditionForField(criteria.getSenderMobileNo(), entityTable.column("sender_mobile_no"));
            }
            if (criteria.getSenderName() != null) {
                builder.buildFilterConditionForField(criteria.getSenderName(), entityTable.column("sender_name"));
            }
            if (criteria.getSenderType() != null) {
                builder.buildFilterConditionForField(criteria.getSenderType(), entityTable.column("sender_type"));
            }
            if (criteria.getSenderIdNo() != null) {
                builder.buildFilterConditionForField(criteria.getSenderIdNo(), entityTable.column("sender_id_no"));
            }
            if (criteria.getSenderId() != null) {
                builder.buildFilterConditionForField(criteria.getSenderId(), entityTable.column("sender_id"));
            }
            if (criteria.getReceiverName() != null) {
                builder.buildFilterConditionForField(criteria.getReceiverName(), entityTable.column("receiver_name"));
            }
            if (criteria.getReceiverMobileNo() != null) {
                builder.buildFilterConditionForField(criteria.getReceiverMobileNo(), entityTable.column("receiver_mobile_no"));
            }
            if (criteria.getReceiverType() != null) {
                builder.buildFilterConditionForField(criteria.getReceiverType(), entityTable.column("receiver_type"));
            }
            if (criteria.getReceiverId() != null) {
                builder.buildFilterConditionForField(criteria.getReceiverId(), entityTable.column("receiver_id"));
            }
            if (criteria.getReceiverIdNo() != null) {
                builder.buildFilterConditionForField(criteria.getReceiverIdNo(), entityTable.column("receiver_id_no"));
            }
            if (criteria.getCreatedByUserId() != null) {
                builder.buildFilterConditionForField(criteria.getCreatedByUserId(), entityTable.column("created_by_user_id"));
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
