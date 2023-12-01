package ly.post.dinar.repository;

import ly.post.dinar.domain.Beneficiary;
import ly.post.dinar.domain.criteria.BeneficiaryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Beneficiary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeneficiaryRepository extends ReactiveCrudRepository<Beneficiary, Long>, BeneficiaryRepositoryInternal {
    Flux<Beneficiary> findAllBy(Pageable pageable);

    @Override
    <S extends Beneficiary> Mono<S> save(S entity);

    @Override
    Flux<Beneficiary> findAll();

    @Override
    Mono<Beneficiary> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface BeneficiaryRepositoryInternal {
    <S extends Beneficiary> Mono<S> save(S entity);

    Flux<Beneficiary> findAllBy(Pageable pageable);

    Flux<Beneficiary> findAll();

    Mono<Beneficiary> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Beneficiary> findAllBy(Pageable pageable, Criteria criteria);
    Flux<Beneficiary> findByCriteria(BeneficiaryCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(BeneficiaryCriteria criteria);
}
