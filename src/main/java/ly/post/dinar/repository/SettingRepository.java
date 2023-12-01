package ly.post.dinar.repository;

import ly.post.dinar.domain.Setting;
import ly.post.dinar.domain.criteria.SettingCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Setting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SettingRepository extends ReactiveCrudRepository<Setting, Long>, SettingRepositoryInternal {
    Flux<Setting> findAllBy(Pageable pageable);

    @Override
    <S extends Setting> Mono<S> save(S entity);

    @Override
    Flux<Setting> findAll();

    @Override
    Mono<Setting> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SettingRepositoryInternal {
    <S extends Setting> Mono<S> save(S entity);

    Flux<Setting> findAllBy(Pageable pageable);

    Flux<Setting> findAll();

    Mono<Setting> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Setting> findAllBy(Pageable pageable, Criteria criteria);
    Flux<Setting> findByCriteria(SettingCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(SettingCriteria criteria);
}
