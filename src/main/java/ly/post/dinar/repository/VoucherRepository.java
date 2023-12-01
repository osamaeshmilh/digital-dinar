package ly.post.dinar.repository;

import ly.post.dinar.domain.Voucher;
import ly.post.dinar.domain.criteria.VoucherCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Voucher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoucherRepository extends ReactiveCrudRepository<Voucher, Long>, VoucherRepositoryInternal {
    Flux<Voucher> findAllBy(Pageable pageable);

    @Override
    Mono<Voucher> findOneWithEagerRelationships(Long id);

    @Override
    Flux<Voucher> findAllWithEagerRelationships();

    @Override
    Flux<Voucher> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM voucher entity WHERE entity.voucher_type_id = :id")
    Flux<Voucher> findByVoucherType(Long id);

    @Query("SELECT * FROM voucher entity WHERE entity.voucher_type_id IS NULL")
    Flux<Voucher> findAllWhereVoucherTypeIsNull();

    @Query("SELECT * FROM voucher entity WHERE entity.voucher_company_id = :id")
    Flux<Voucher> findByVoucherCompany(Long id);

    @Query("SELECT * FROM voucher entity WHERE entity.voucher_company_id IS NULL")
    Flux<Voucher> findAllWhereVoucherCompanyIsNull();

    @Override
    <S extends Voucher> Mono<S> save(S entity);

    @Override
    Flux<Voucher> findAll();

    @Override
    Mono<Voucher> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface VoucherRepositoryInternal {
    <S extends Voucher> Mono<S> save(S entity);

    Flux<Voucher> findAllBy(Pageable pageable);

    Flux<Voucher> findAll();

    Mono<Voucher> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Voucher> findAllBy(Pageable pageable, Criteria criteria);
    Flux<Voucher> findByCriteria(VoucherCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(VoucherCriteria criteria);

    Mono<Voucher> findOneWithEagerRelationships(Long id);

    Flux<Voucher> findAllWithEagerRelationships();

    Flux<Voucher> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
