package ly.post.dinar.repository;

import ly.post.dinar.domain.Slider;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Slider entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SliderRepository extends JpaRepository<Slider, Long>, JpaSpecificationExecutor<Slider> {}
