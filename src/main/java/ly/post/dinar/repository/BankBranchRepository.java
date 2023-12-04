package ly.post.dinar.repository;

import java.util.List;
import java.util.Optional;
import ly.post.dinar.domain.BankBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BankBranch entity.
 */
@Repository
public interface BankBranchRepository extends JpaRepository<BankBranch, Long>, JpaSpecificationExecutor<BankBranch> {
    default Optional<BankBranch> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<BankBranch> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<BankBranch> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select bankBranch from BankBranch bankBranch left join fetch bankBranch.bank",
        countQuery = "select count(bankBranch) from BankBranch bankBranch"
    )
    Page<BankBranch> findAllWithToOneRelationships(Pageable pageable);

    @Query("select bankBranch from BankBranch bankBranch left join fetch bankBranch.bank")
    List<BankBranch> findAllWithToOneRelationships();

    @Query("select bankBranch from BankBranch bankBranch left join fetch bankBranch.bank where bankBranch.id =:id")
    Optional<BankBranch> findOneWithToOneRelationships(@Param("id") Long id);
}
