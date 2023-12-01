package ly.post.dinar.repository;

import ly.post.dinar.domain.WalletProfile;
import ly.post.dinar.domain.criteria.WalletProfileCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the WalletProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletProfileRepository extends ReactiveCrudRepository<WalletProfile, Long>, WalletProfileRepositoryInternal {
    Flux<WalletProfile> findAllBy(Pageable pageable);

    @Override
    <S extends WalletProfile> Mono<S> save(S entity);

    @Override
    Flux<WalletProfile> findAll();

    @Override
    Mono<WalletProfile> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface WalletProfileRepositoryInternal {
    <S extends WalletProfile> Mono<S> save(S entity);

    Flux<WalletProfile> findAllBy(Pageable pageable);

    Flux<WalletProfile> findAll();

    Mono<WalletProfile> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<WalletProfile> findAllBy(Pageable pageable, Criteria criteria);
    Flux<WalletProfile> findByCriteria(WalletProfileCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(WalletProfileCriteria criteria);
}
