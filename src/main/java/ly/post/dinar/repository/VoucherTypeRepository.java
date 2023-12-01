package ly.post.dinar.repository;

import ly.post.dinar.domain.VoucherType;
import ly.post.dinar.domain.criteria.VoucherTypeCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the VoucherType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoucherTypeRepository extends ReactiveCrudRepository<VoucherType, Long>, VoucherTypeRepositoryInternal {
    Flux<VoucherType> findAllBy(Pageable pageable);

    @Override
    Mono<VoucherType> findOneWithEagerRelationships(Long id);

    @Override
    Flux<VoucherType> findAllWithEagerRelationships();

    @Override
    Flux<VoucherType> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM voucher_type entity WHERE entity.voucher_company_id = :id")
    Flux<VoucherType> findByVoucherCompany(Long id);

    @Query("SELECT * FROM voucher_type entity WHERE entity.voucher_company_id IS NULL")
    Flux<VoucherType> findAllWhereVoucherCompanyIsNull();

    @Override
    <S extends VoucherType> Mono<S> save(S entity);

    @Override
    Flux<VoucherType> findAll();

    @Override
    Mono<VoucherType> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface VoucherTypeRepositoryInternal {
    <S extends VoucherType> Mono<S> save(S entity);

    Flux<VoucherType> findAllBy(Pageable pageable);

    Flux<VoucherType> findAll();

    Mono<VoucherType> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<VoucherType> findAllBy(Pageable pageable, Criteria criteria);
    Flux<VoucherType> findByCriteria(VoucherTypeCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(VoucherTypeCriteria criteria);

    Mono<VoucherType> findOneWithEagerRelationships(Long id);

    Flux<VoucherType> findAllWithEagerRelationships();

    Flux<VoucherType> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
