package ly.post.dinar.repository;

import java.util.List;
import java.util.Optional;
import ly.post.dinar.domain.WalletUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the WalletUser entity.
 */
@Repository
public interface WalletUserRepository extends JpaRepository<WalletUser, Long>, JpaSpecificationExecutor<WalletUser> {
    default Optional<WalletUser> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<WalletUser> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<WalletUser> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select walletUser from WalletUser walletUser left join fetch walletUser.category left join fetch walletUser.city left join fetch walletUser.walletProfile left join fetch walletUser.bankBranch",
        countQuery = "select count(walletUser) from WalletUser walletUser"
    )
    Page<WalletUser> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select walletUser from WalletUser walletUser left join fetch walletUser.category left join fetch walletUser.city left join fetch walletUser.walletProfile left join fetch walletUser.bankBranch"
    )
    List<WalletUser> findAllWithToOneRelationships();

    @Query(
        "select walletUser from WalletUser walletUser left join fetch walletUser.category left join fetch walletUser.city left join fetch walletUser.walletProfile left join fetch walletUser.bankBranch where walletUser.id =:id"
    )
    Optional<WalletUser> findOneWithToOneRelationships(@Param("id") Long id);
}
