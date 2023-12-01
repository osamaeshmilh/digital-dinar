package ly.post.dinar.repository;

import ly.post.dinar.domain.ViewLog;
import ly.post.dinar.domain.criteria.ViewLogCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ViewLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewLogRepository extends ReactiveCrudRepository<ViewLog, Long>, ViewLogRepositoryInternal {
    Flux<ViewLog> findAllBy(Pageable pageable);

    @Override
    <S extends ViewLog> Mono<S> save(S entity);

    @Override
    Flux<ViewLog> findAll();

    @Override
    Mono<ViewLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ViewLogRepositoryInternal {
    <S extends ViewLog> Mono<S> save(S entity);

    Flux<ViewLog> findAllBy(Pageable pageable);

    Flux<ViewLog> findAll();

    Mono<ViewLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ViewLog> findAllBy(Pageable pageable, Criteria criteria);
    Flux<ViewLog> findByCriteria(ViewLogCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(ViewLogCriteria criteria);
}
