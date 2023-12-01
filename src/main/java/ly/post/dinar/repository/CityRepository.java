package ly.post.dinar.repository;

import ly.post.dinar.domain.City;
import ly.post.dinar.domain.criteria.CityCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the City entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CityRepository extends ReactiveCrudRepository<City, Long>, CityRepositoryInternal {
    Flux<City> findAllBy(Pageable pageable);

    @Override
    Mono<City> findOneWithEagerRelationships(Long id);

    @Override
    Flux<City> findAllWithEagerRelationships();

    @Override
    Flux<City> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM city entity WHERE entity.country_id = :id")
    Flux<City> findByCountry(Long id);

    @Query("SELECT * FROM city entity WHERE entity.country_id IS NULL")
    Flux<City> findAllWhereCountryIsNull();

    @Override
    <S extends City> Mono<S> save(S entity);

    @Override
    Flux<City> findAll();

    @Override
    Mono<City> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CityRepositoryInternal {
    <S extends City> Mono<S> save(S entity);

    Flux<City> findAllBy(Pageable pageable);

    Flux<City> findAll();

    Mono<City> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<City> findAllBy(Pageable pageable, Criteria criteria);
    Flux<City> findByCriteria(CityCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(CityCriteria criteria);

    Mono<City> findOneWithEagerRelationships(Long id);

    Flux<City> findAllWithEagerRelationships();

    Flux<City> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
