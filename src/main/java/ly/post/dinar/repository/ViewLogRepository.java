package ly.post.dinar.repository;

import ly.post.dinar.domain.ViewLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ViewLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewLogRepository extends JpaRepository<ViewLog, Long>, JpaSpecificationExecutor<ViewLog> {}
