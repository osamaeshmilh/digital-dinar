package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.WalletTransaction;
import ly.post.dinar.domain.criteria.WalletTransactionCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.TransactionRowMapper;
import ly.post.dinar.repository.rowmapper.WalletTransactionRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the WalletTransaction entity.
 */
@SuppressWarnings("unused")
class WalletTransactionRepositoryInternalImpl
    extends SimpleR2dbcRepository<WalletTransaction, Long>
    implements WalletTransactionRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final TransactionRowMapper transactionMapper;
    private final WalletTransactionRowMapper wallettransactionMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("wallet_transaction", EntityManager.ENTITY_ALIAS);
    private static final Table transactionTable = Table.aliased("transaction", "e_transaction");

    public WalletTransactionRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        TransactionRowMapper transactionMapper,
        WalletTransactionRowMapper wallettransactionMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(WalletTransaction.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.transactionMapper = transactionMapper;
        this.wallettransactionMapper = wallettransactionMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<WalletTransaction> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<WalletTransaction> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = WalletTransactionSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(TransactionSqlHelper.getColumns(transactionTable, "transaction"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(transactionTable)
            .on(Column.create("transaction_id", entityTable))
            .equals(Column.create("id", transactionTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, WalletTransaction.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<WalletTransaction> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<WalletTransaction> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private WalletTransaction process(Row row, RowMetadata metadata) {
        WalletTransaction entity = wallettransactionMapper.apply(row, "e");
        entity.setTransaction(transactionMapper.apply(row, "transaction"));
        return entity;
    }

    @Override
    public <S extends WalletTransaction> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<WalletTransaction> findByCriteria(WalletTransactionCriteria walletTransactionCriteria, Pageable page) {
        return createQuery(page, buildConditions(walletTransactionCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(WalletTransactionCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(WalletTransactionCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getTransactionNo() != null) {
                builder.buildFilterConditionForField(criteria.getTransactionNo(), entityTable.column("transaction_no"));
            }
            if (criteria.getAmount() != null) {
                builder.buildFilterConditionForField(criteria.getAmount(), entityTable.column("amount"));
            }
            if (criteria.getWalletAction() != null) {
                builder.buildFilterConditionForField(criteria.getWalletAction(), entityTable.column("wallet_action"));
            }
            if (criteria.getUserPreviousTransactionNo() != null) {
                builder.buildFilterConditionForField(
                    criteria.getUserPreviousTransactionNo(),
                    entityTable.column("user_previous_transaction_no")
                );
            }
            if (criteria.getTotalBeforeAction() != null) {
                builder.buildFilterConditionForField(criteria.getTotalBeforeAction(), entityTable.column("total_before_action"));
            }
            if (criteria.getTotalAfterAction() != null) {
                builder.buildFilterConditionForField(criteria.getTotalAfterAction(), entityTable.column("total_after_action"));
            }
            if (criteria.getPaymentType() != null) {
                builder.buildFilterConditionForField(criteria.getPaymentType(), entityTable.column("payment_type"));
            }
            if (criteria.getPaymentReference() != null) {
                builder.buildFilterConditionForField(criteria.getPaymentReference(), entityTable.column("payment_reference"));
            }
            if (criteria.getNotes() != null) {
                builder.buildFilterConditionForField(criteria.getNotes(), entityTable.column("notes"));
            }
            if (criteria.getOwnerId() != null) {
                builder.buildFilterConditionForField(criteria.getOwnerId(), entityTable.column("owner_id"));
            }
            if (criteria.getWalletOwnerType() != null) {
                builder.buildFilterConditionForField(criteria.getWalletOwnerType(), entityTable.column("wallet_owner_type"));
            }
            if (criteria.getTransactionId() != null) {
                builder.buildFilterConditionForField(criteria.getTransactionId(), transactionTable.column("id"));
            }
        }
        return builder.buildConditions();
    }
}
