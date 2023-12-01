package ly.post.dinar.repository;

import ly.post.dinar.domain.Activation;
import ly.post.dinar.domain.criteria.ActivationCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Activation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivationRepository extends ReactiveCrudRepository<Activation, Long>, ActivationRepositoryInternal {
    Flux<Activation> findAllBy(Pageable pageable);

    @Override
    <S extends Activation> Mono<S> save(S entity);

    @Override
    Flux<Activation> findAll();

    @Override
    Mono<Activation> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ActivationRepositoryInternal {
    <S extends Activation> Mono<S> save(S entity);

    Flux<Activation> findAllBy(Pageable pageable);

    Flux<Activation> findAll();

    Mono<Activation> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Activation> findAllBy(Pageable pageable, Criteria criteria);
    Flux<Activation> findByCriteria(ActivationCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(ActivationCriteria criteria);
}
