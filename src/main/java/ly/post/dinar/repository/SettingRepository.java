package ly.post.dinar.repository;

import ly.post.dinar.domain.Setting;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Setting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SettingRepository extends JpaRepository<Setting, Long>, JpaSpecificationExecutor<Setting> {}
