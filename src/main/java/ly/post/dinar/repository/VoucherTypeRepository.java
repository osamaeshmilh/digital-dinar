package ly.post.dinar.repository;

import java.util.List;
import java.util.Optional;
import ly.post.dinar.domain.VoucherType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the VoucherType entity.
 */
@Repository
public interface VoucherTypeRepository extends JpaRepository<VoucherType, Long>, JpaSpecificationExecutor<VoucherType> {
    default Optional<VoucherType> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<VoucherType> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<VoucherType> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select voucherType from VoucherType voucherType left join fetch voucherType.voucherCompany",
        countQuery = "select count(voucherType) from VoucherType voucherType"
    )
    Page<VoucherType> findAllWithToOneRelationships(Pageable pageable);

    @Query("select voucherType from VoucherType voucherType left join fetch voucherType.voucherCompany")
    List<VoucherType> findAllWithToOneRelationships();

    @Query("select voucherType from VoucherType voucherType left join fetch voucherType.voucherCompany where voucherType.id =:id")
    Optional<VoucherType> findOneWithToOneRelationships(@Param("id") Long id);
}
