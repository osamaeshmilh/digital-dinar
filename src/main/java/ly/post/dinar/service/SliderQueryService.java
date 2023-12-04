package ly.post.dinar.service;

import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.Slider;
import ly.post.dinar.repository.SliderRepository;
import ly.post.dinar.service.criteria.SliderCriteria;
import ly.post.dinar.service.dto.SliderDTO;
import ly.post.dinar.service.mapper.SliderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Slider} entities in the database.
 * The main input is a {@link SliderCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SliderDTO} or a {@link Page} of {@link SliderDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SliderQueryService extends QueryService<Slider> {

    private final Logger log = LoggerFactory.getLogger(SliderQueryService.class);

    private final SliderRepository sliderRepository;

    private final SliderMapper sliderMapper;

    public SliderQueryService(SliderRepository sliderRepository, SliderMapper sliderMapper) {
        this.sliderRepository = sliderRepository;
        this.sliderMapper = sliderMapper;
    }

    /**
     * Return a {@link List} of {@link SliderDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SliderDTO> findByCriteria(SliderCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Slider> specification = createSpecification(criteria);
        return sliderMapper.toDto(sliderRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SliderDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SliderDTO> findByCriteria(SliderCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Slider> specification = createSpecification(criteria);
        return sliderRepository.findAll(specification, page).map(sliderMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SliderCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Slider> specification = createSpecification(criteria);
        return sliderRepository.count(specification);
    }

    /**
     * Function to convert {@link SliderCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Slider> createSpecification(SliderCriteria criteria) {
        Specification<Slider> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Slider_.id));
            }
            if (criteria.getDetails() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDetails(), Slider_.details));
            }
            if (criteria.getMenuOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMenuOrder(), Slider_.menuOrder));
            }
            if (criteria.getImageFileUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageFileUrl(), Slider_.imageFileUrl));
            }
            if (criteria.getUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrl(), Slider_.url));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Slider_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Slider_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Slider_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Slider_.lastModifiedDate));
            }
        }
        return specification;
    }
}
