package ly.post.dinar.repository;

import ly.post.dinar.domain.VoucherCompany;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the VoucherCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoucherCompanyRepository extends JpaRepository<VoucherCompany, Long>, JpaSpecificationExecutor<VoucherCompany> {}
