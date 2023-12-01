package ly.post.dinar.repository;

import ly.post.dinar.domain.VoucherCompany;
import ly.post.dinar.domain.criteria.VoucherCompanyCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the VoucherCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoucherCompanyRepository extends ReactiveCrudRepository<VoucherCompany, Long>, VoucherCompanyRepositoryInternal {
    Flux<VoucherCompany> findAllBy(Pageable pageable);

    @Query("SELECT * FROM voucher_company entity WHERE entity.user_id = :id")
    Flux<VoucherCompany> findByUser(Long id);

    @Query("SELECT * FROM voucher_company entity WHERE entity.user_id IS NULL")
    Flux<VoucherCompany> findAllWhereUserIsNull();

    @Override
    <S extends VoucherCompany> Mono<S> save(S entity);

    @Override
    Flux<VoucherCompany> findAll();

    @Override
    Mono<VoucherCompany> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface VoucherCompanyRepositoryInternal {
    <S extends VoucherCompany> Mono<S> save(S entity);

    Flux<VoucherCompany> findAllBy(Pageable pageable);

    Flux<VoucherCompany> findAll();

    Mono<VoucherCompany> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<VoucherCompany> findAllBy(Pageable pageable, Criteria criteria);
    Flux<VoucherCompany> findByCriteria(VoucherCompanyCriteria criteria, Pageable pageable);

    Mono<Long> countByCriteria(VoucherCompanyCriteria criteria);
}
