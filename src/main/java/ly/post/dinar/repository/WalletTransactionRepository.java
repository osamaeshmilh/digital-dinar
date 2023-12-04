package ly.post.dinar.repository;

import ly.post.dinar.domain.WalletTransaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the WalletTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long>, JpaSpecificationExecutor<WalletTransaction> {}
