package ly.post.dinar.repository;

import ly.post.dinar.domain.Bank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Bank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankRepository extends JpaRepository<Bank, Long>, JpaSpecificationExecutor<Bank> {}
