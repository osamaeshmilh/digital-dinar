package ly.post.dinar.repository;

import java.util.List;
import java.util.Optional;
import ly.post.dinar.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the City entity.
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    default Optional<City> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<City> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<City> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(value = "select city from City city left join fetch city.country", countQuery = "select count(city) from City city")
    Page<City> findAllWithToOneRelationships(Pageable pageable);

    @Query("select city from City city left join fetch city.country")
    List<City> findAllWithToOneRelationships();

    @Query("select city from City city left join fetch city.country where city.id =:id")
    Optional<City> findOneWithToOneRelationships(@Param("id") Long id);
}
