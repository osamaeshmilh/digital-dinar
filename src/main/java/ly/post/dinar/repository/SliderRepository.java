package ly.post.dinar.repository;

import ly.post.dinar.domain.Slider;
import ly.post.dinar.domain.criteria.SliderCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Slider entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SliderRepository extends ReactiveCrudRepository<Slider, Long>, SliderRepositoryInternal {
    Flux<Slider> findAllBy(Pageable pageable);

    @Override
    <S extends Slider> Mono<S> save(S entity);

    @Override
    Flux<Slider> findAll();

    @Override
    Mono<Slider> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SliderRepositoryInternal {
    <S extends Slider> Mono<S> save(S entity);

    Flux<Slider> findAllBy(Pageable pageable);

    Flux<Slider> findAll();

    Mono<Slider> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Slider> findAllBy(Pageable pageable, Criteria criteria);
    Flux<Slider> findByCriteria(SliderCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(SliderCriteria criteria);
}
