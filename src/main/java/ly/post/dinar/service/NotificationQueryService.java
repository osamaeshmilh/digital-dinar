package ly.post.dinar.service;

import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.Notification;
import ly.post.dinar.repository.NotificationRepository;
import ly.post.dinar.service.criteria.NotificationCriteria;
import ly.post.dinar.service.dto.NotificationDTO;
import ly.post.dinar.service.mapper.NotificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Notification} entities in the database.
 * The main input is a {@link NotificationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NotificationDTO} or a {@link Page} of {@link NotificationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NotificationQueryService extends QueryService<Notification> {

    private final Logger log = LoggerFactory.getLogger(NotificationQueryService.class);

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    public NotificationQueryService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    /**
     * Return a {@link List} of {@link NotificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NotificationDTO> findByCriteria(NotificationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Notification> specification = createSpecification(criteria);
        return notificationMapper.toDto(notificationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NotificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NotificationDTO> findByCriteria(NotificationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Notification> specification = createSpecification(criteria);
        return notificationRepository.findAll(specification, page).map(notificationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NotificationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Notification> specification = createSpecification(criteria);
        return notificationRepository.count(specification);
    }

    /**
     * Function to convert {@link NotificationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Notification> createSpecification(NotificationCriteria criteria) {
        Specification<Notification> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Notification_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Notification_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Notification_.description));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserId(), Notification_.userId));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Notification_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Notification_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Notification_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Notification_.lastModifiedDate));
            }
        }
        return specification;
    }
}
