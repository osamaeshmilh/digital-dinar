package ly.post.dinar.repository;

import ly.post.dinar.domain.Consumer;
import ly.post.dinar.domain.criteria.ConsumerCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Consumer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsumerRepository extends ReactiveCrudRepository<Consumer, Long>, ConsumerRepositoryInternal {
    Flux<Consumer> findAllBy(Pageable pageable);

    @Override
    Mono<Consumer> findOneWithEagerRelationships(Long id);

    @Override
    Flux<Consumer> findAllWithEagerRelationships();

    @Override
    Flux<Consumer> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM consumer entity WHERE entity.user_id = :id")
    Flux<Consumer> findByUser(Long id);

    @Query("SELECT * FROM consumer entity WHERE entity.user_id IS NULL")
    Flux<Consumer> findAllWhereUserIsNull();

    @Query("SELECT * FROM consumer entity WHERE entity.wallet_profile_id = :id")
    Flux<Consumer> findByWalletProfile(Long id);

    @Query("SELECT * FROM consumer entity WHERE entity.wallet_profile_id IS NULL")
    Flux<Consumer> findAllWhereWalletProfileIsNull();

    @Override
    <S extends Consumer> Mono<S> save(S entity);

    @Override
    Flux<Consumer> findAll();

    @Override
    Mono<Consumer> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ConsumerRepositoryInternal {
    <S extends Consumer> Mono<S> save(S entity);

    Flux<Consumer> findAllBy(Pageable pageable);

    Flux<Consumer> findAll();

    Mono<Consumer> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Consumer> findAllBy(Pageable pageable, Criteria criteria);
    Flux<Consumer> findByCriteria(ConsumerCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(ConsumerCriteria criteria);

    Mono<Consumer> findOneWithEagerRelationships(Long id);

    Flux<Consumer> findAllWithEagerRelationships();

    Flux<Consumer> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
