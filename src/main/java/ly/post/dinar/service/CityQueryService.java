package ly.post.dinar.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.City;
import ly.post.dinar.repository.CityRepository;
import ly.post.dinar.service.criteria.CityCriteria;
import ly.post.dinar.service.dto.CityDTO;
import ly.post.dinar.service.mapper.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link City} entities in the database.
 * The main input is a {@link CityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CityDTO} or a {@link Page} of {@link CityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CityQueryService extends QueryService<City> {

    private final Logger log = LoggerFactory.getLogger(CityQueryService.class);

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    public CityQueryService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    /**
     * Return a {@link List} of {@link CityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CityDTO> findByCriteria(CityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<City> specification = createSpecification(criteria);
        return cityMapper.toDto(cityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CityDTO> findByCriteria(CityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<City> specification = createSpecification(criteria);
        return cityRepository.findAll(specification, page).map(cityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<City> specification = createSpecification(criteria);
        return cityRepository.count(specification);
    }

    /**
     * Function to convert {@link CityCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<City> createSpecification(CityCriteria criteria) {
        Specification<City> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), City_.id));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), City_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), City_.nameEn));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), City_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), City_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), City_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), City_.lastModifiedDate));
            }
            if (criteria.getCountryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCountryId(), root -> root.join(City_.country, JoinType.LEFT).get(Country_.id))
                    );
            }
        }
        return specification;
    }
}
