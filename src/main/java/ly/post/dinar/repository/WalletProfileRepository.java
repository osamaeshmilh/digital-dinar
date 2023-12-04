package ly.post.dinar.repository;

import ly.post.dinar.domain.WalletProfile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the WalletProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletProfileRepository extends JpaRepository<WalletProfile, Long>, JpaSpecificationExecutor<WalletProfile> {}
