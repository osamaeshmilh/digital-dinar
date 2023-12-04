package ly.post.dinar.repository;

import java.util.List;
import java.util.Optional;
import ly.post.dinar.domain.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Voucher entity.
 */
@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long>, JpaSpecificationExecutor<Voucher> {
    default Optional<Voucher> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Voucher> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Voucher> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select voucher from Voucher voucher left join fetch voucher.voucherType left join fetch voucher.voucherCompany",
        countQuery = "select count(voucher) from Voucher voucher"
    )
    Page<Voucher> findAllWithToOneRelationships(Pageable pageable);

    @Query("select voucher from Voucher voucher left join fetch voucher.voucherType left join fetch voucher.voucherCompany")
    List<Voucher> findAllWithToOneRelationships();

    @Query(
        "select voucher from Voucher voucher left join fetch voucher.voucherType left join fetch voucher.voucherCompany where voucher.id =:id"
    )
    Optional<Voucher> findOneWithToOneRelationships(@Param("id") Long id);
}
