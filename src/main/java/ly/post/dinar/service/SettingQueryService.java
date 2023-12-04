package ly.post.dinar.service;

import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.Setting;
import ly.post.dinar.repository.SettingRepository;
import ly.post.dinar.service.criteria.SettingCriteria;
import ly.post.dinar.service.dto.SettingDTO;
import ly.post.dinar.service.mapper.SettingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Setting} entities in the database.
 * The main input is a {@link SettingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SettingDTO} or a {@link Page} of {@link SettingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SettingQueryService extends QueryService<Setting> {

    private final Logger log = LoggerFactory.getLogger(SettingQueryService.class);

    private final SettingRepository settingRepository;

    private final SettingMapper settingMapper;

    public SettingQueryService(SettingRepository settingRepository, SettingMapper settingMapper) {
        this.settingRepository = settingRepository;
        this.settingMapper = settingMapper;
    }

    /**
     * Return a {@link List} of {@link SettingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SettingDTO> findByCriteria(SettingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Setting> specification = createSpecification(criteria);
        return settingMapper.toDto(settingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SettingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SettingDTO> findByCriteria(SettingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Setting> specification = createSpecification(criteria);
        return settingRepository.findAll(specification, page).map(settingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SettingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Setting> specification = createSpecification(criteria);
        return settingRepository.count(specification);
    }

    /**
     * Function to convert {@link SettingCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Setting> createSpecification(SettingCriteria criteria) {
        Specification<Setting> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Setting_.id));
            }
            if (criteria.getKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKey(), Setting_.key));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValue(), Setting_.value));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Setting_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Setting_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Setting_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Setting_.lastModifiedDate));
            }
        }
        return specification;
    }
}
