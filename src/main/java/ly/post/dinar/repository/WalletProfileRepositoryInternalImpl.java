package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.WalletProfile;
import ly.post.dinar.domain.criteria.WalletProfileCriteria;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import ly.post.dinar.repository.rowmapper.WalletProfileRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the WalletProfile entity.
 */
@SuppressWarnings("unused")
class WalletProfileRepositoryInternalImpl extends SimpleR2dbcRepository<WalletProfile, Long> implements WalletProfileRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final WalletProfileRowMapper walletprofileMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("wallet_profile", EntityManager.ENTITY_ALIAS);

    public WalletProfileRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        WalletProfileRowMapper walletprofileMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(WalletProfile.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.walletprofileMapper = walletprofileMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<WalletProfile> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<WalletProfile> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = WalletProfileSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, WalletProfile.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<WalletProfile> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<WalletProfile> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private WalletProfile process(Row row, RowMetadata metadata) {
        WalletProfile entity = walletprofileMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends WalletProfile> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<WalletProfile> findByCriteria(WalletProfileCriteria walletProfileCriteria, Pageable page) {
        return createQuery(page, buildConditions(walletProfileCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(WalletProfileCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(WalletProfileCriteria criteria) {
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
            if (criteria.getHasTransferAmountLimitPerTransaction() != null) {
                builder.buildFilterConditionForField(
                    criteria.getHasTransferAmountLimitPerTransaction(),
                    entityTable.column("has_transfer_amount_limit_per_transaction")
                );
            }
            if (criteria.getTransferAmountLimitPerTransaction() != null) {
                builder.buildFilterConditionForField(
                    criteria.getTransferAmountLimitPerTransaction(),
                    entityTable.column("transfer_amount_limit_per_transaction")
                );
            }
            if (criteria.getHasDailyTransferAmountLimit() != null) {
                builder.buildFilterConditionForField(
                    criteria.getHasDailyTransferAmountLimit(),
                    entityTable.column("has_daily_transfer_amount_limit")
                );
            }
            if (criteria.getDailyTransferAmountLimit() != null) {
                builder.buildFilterConditionForField(
                    criteria.getDailyTransferAmountLimit(),
                    entityTable.column("daily_transfer_amount_limit")
                );
            }
            if (criteria.getCanTransferToConsumerWallet() != null) {
                builder.buildFilterConditionForField(
                    criteria.getCanTransferToConsumerWallet(),
                    entityTable.column("can_transfer_to_consumer_wallet")
                );
            }
            if (criteria.getCanTransferToMerchantWallet() != null) {
                builder.buildFilterConditionForField(
                    criteria.getCanTransferToMerchantWallet(),
                    entityTable.column("can_transfer_to_merchant_wallet")
                );
            }
            if (criteria.getCanTransferToConsumer() != null) {
                builder.buildFilterConditionForField(criteria.getCanTransferToConsumer(), entityTable.column("can_transfer_to_consumer"));
            }
            if (criteria.getCanTransferToBank() != null) {
                builder.buildFilterConditionForField(criteria.getCanTransferToBank(), entityTable.column("can_transfer_to_bank"));
            }
            if (criteria.getCanBuyVouchers() != null) {
                builder.buildFilterConditionForField(criteria.getCanBuyVouchers(), entityTable.column("can_buy_vouchers"));
            }
            if (criteria.getTransferToConsumerWalletFees() != null) {
                builder.buildFilterConditionForField(
                    criteria.getTransferToConsumerWalletFees(),
                    entityTable.column("transfer_to_consumer_wallet_fees")
                );
            }
            if (criteria.getTransferToMerchantWalletFees() != null) {
                builder.buildFilterConditionForField(
                    criteria.getTransferToMerchantWalletFees(),
                    entityTable.column("transfer_to_merchant_wallet_fees")
                );
            }
            if (criteria.getTransferToConsumerFees() != null) {
                builder.buildFilterConditionForField(criteria.getTransferToConsumerFees(), entityTable.column("transfer_to_consumer_fees"));
            }
            if (criteria.getTransferToBankFees() != null) {
                builder.buildFilterConditionForField(criteria.getTransferToBankFees(), entityTable.column("transfer_to_bank_fees"));
            }
            if (criteria.getBuyVouchersFees() != null) {
                builder.buildFilterConditionForField(criteria.getBuyVouchersFees(), entityTable.column("buy_vouchers_fees"));
            }
        }
        return builder.buildConditions();
    }
}
