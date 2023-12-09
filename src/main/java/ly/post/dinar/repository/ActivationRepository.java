package ly.post.dinar.repository;

import java.util.Optional;
import ly.post.dinar.domain.Activation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Activation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivationRepository extends JpaRepository<Activation, Long>, JpaSpecificationExecutor<Activation> {
    Optional<Activation> findByEmail(String email);

    Optional<Activation> findFirstByMobileNoContains(String mobileNo);

    Optional<Activation> findByEmailAndCode(String email, String otp);

    Optional<Activation> findFirstByMobileNoContainsAndCode(String mobileNo, String otp);
}
