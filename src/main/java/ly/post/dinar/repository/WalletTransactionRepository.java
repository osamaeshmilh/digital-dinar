package ly.post.dinar.repository;

import ly.post.dinar.domain.WalletTransaction;
import ly.post.dinar.domain.criteria.WalletTransactionCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the WalletTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletTransactionRepository extends ReactiveCrudRepository<WalletTransaction, Long>, WalletTransactionRepositoryInternal {
    Flux<WalletTransaction> findAllBy(Pageable pageable);

    @Query("SELECT * FROM wallet_transaction entity WHERE entity.transaction_id = :id")
    Flux<WalletTransaction> findByTransaction(Long id);

    @Query("SELECT * FROM wallet_transaction entity WHERE entity.transaction_id IS NULL")
    Flux<WalletTransaction> findAllWhereTransactionIsNull();

    @Override
    <S extends WalletTransaction> Mono<S> save(S entity);

    @Override
    Flux<WalletTransaction> findAll();

    @Override
    Mono<WalletTransaction> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface WalletTransactionRepositoryInternal {
    <S extends WalletTransaction> Mono<S> save(S entity);

    Flux<WalletTransaction> findAllBy(Pageable pageable);

    Flux<WalletTransaction> findAll();

    Mono<WalletTransaction> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<WalletTransaction> findAllBy(Pageable pageable, Criteria criteria);
    Flux<WalletTransaction> findByCriteria(WalletTransactionCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(WalletTransactionCriteria criteria);
}
