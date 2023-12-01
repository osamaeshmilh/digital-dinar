package ly.post.dinar.repository;

import ly.post.dinar.domain.Merchant;
import ly.post.dinar.domain.criteria.MerchantCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Merchant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MerchantRepository extends ReactiveCrudRepository<Merchant, Long>, MerchantRepositoryInternal {
    Flux<Merchant> findAllBy(Pageable pageable);

    @Override
    Mono<Merchant> findOneWithEagerRelationships(Long id);

    @Override
    Flux<Merchant> findAllWithEagerRelationships();

    @Override
    Flux<Merchant> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM merchant entity WHERE entity.user_id = :id")
    Flux<Merchant> findByUser(Long id);

    @Query("SELECT * FROM merchant entity WHERE entity.user_id IS NULL")
    Flux<Merchant> findAllWhereUserIsNull();

    @Query("SELECT * FROM merchant entity WHERE entity.category_id = :id")
    Flux<Merchant> findByCategory(Long id);

    @Query("SELECT * FROM merchant entity WHERE entity.category_id IS NULL")
    Flux<Merchant> findAllWhereCategoryIsNull();

    @Query("SELECT * FROM merchant entity WHERE entity.city_id = :id")
    Flux<Merchant> findByCity(Long id);

    @Query("SELECT * FROM merchant entity WHERE entity.city_id IS NULL")
    Flux<Merchant> findAllWhereCityIsNull();

    @Query("SELECT * FROM merchant entity WHERE entity.wallet_profile_id = :id")
    Flux<Merchant> findByWalletProfile(Long id);

    @Query("SELECT * FROM merchant entity WHERE entity.wallet_profile_id IS NULL")
    Flux<Merchant> findAllWhereWalletProfileIsNull();

    @Override
    <S extends Merchant> Mono<S> save(S entity);

    @Override
    Flux<Merchant> findAll();

    @Override
    Mono<Merchant> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface MerchantRepositoryInternal {
    <S extends Merchant> Mono<S> save(S entity);

    Flux<Merchant> findAllBy(Pageable pageable);

    Flux<Merchant> findAll();

    Mono<Merchant> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Merchant> findAllBy(Pageable pageable, Criteria criteria);
    Flux<Merchant> findByCriteria(MerchantCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(MerchantCriteria criteria);

    Mono<Merchant> findOneWithEagerRelationships(Long id);

    Flux<Merchant> findAllWithEagerRelationships();

    Flux<Merchant> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
