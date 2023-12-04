package ly.post.dinar.repository;

import ly.post.dinar.domain.Activation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Activation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivationRepository extends JpaRepository<Activation, Long>, JpaSpecificationExecutor<Activation> {}
