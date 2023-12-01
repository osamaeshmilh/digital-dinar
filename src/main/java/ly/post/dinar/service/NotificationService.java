package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.NotificationCriteria;
import ly.post.dinar.repository.NotificationRepository;
import ly.post.dinar.service.dto.NotificationDTO;
import ly.post.dinar.service.mapper.NotificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Notification}.
 */
@Service
@Transactional
public class NotificationService {

    private final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    /**
     * Save a notification.
     *
     * @param notificationDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<NotificationDTO> save(NotificationDTO notificationDTO) {
        log.debug("Request to save Notification : {}", notificationDTO);
        return notificationRepository.save(notificationMapper.toEntity(notificationDTO)).map(notificationMapper::toDto);
    }

    /**
     * Update a notification.
     *
     * @param notificationDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<NotificationDTO> update(NotificationDTO notificationDTO) {
        log.debug("Request to update Notification : {}", notificationDTO);
        return notificationRepository.save(notificationMapper.toEntity(notificationDTO)).map(notificationMapper::toDto);
    }

    /**
     * Partially update a notification.
     *
     * @param notificationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<NotificationDTO> partialUpdate(NotificationDTO notificationDTO) {
        log.debug("Request to partially update Notification : {}", notificationDTO);

        return notificationRepository
            .findById(notificationDTO.getId())
            .map(existingNotification -> {
                notificationMapper.partialUpdate(existingNotification, notificationDTO);

                return existingNotification;
            })
            .flatMap(notificationRepository::save)
            .map(notificationMapper::toDto);
    }

    /**
     * Get all the notifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<NotificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Notifications");
        return notificationRepository.findAllBy(pageable).map(notificationMapper::toDto);
    }

    /**
     * Find notifications by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<NotificationDTO> findByCriteria(NotificationCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Notifications by Criteria");
        return notificationRepository.findByCriteria(criteria, pageable).map(notificationMapper::toDto);
    }

    /**
     * Find the count of notifications by criteria.
     * @param criteria filtering criteria
     * @return the count of notifications
     */
    public Mono<Long> countByCriteria(NotificationCriteria criteria) {
        log.debug("Request to get the count of all Notifications by Criteria");
        return notificationRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of notifications available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return notificationRepository.count();
    }

    /**
     * Get one notification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<NotificationDTO> findOne(Long id) {
        log.debug("Request to get Notification : {}", id);
        return notificationRepository.findById(id).map(notificationMapper::toDto);
    }

    /**
     * Delete the notification by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Notification : {}", id);
        return notificationRepository.deleteById(id);
    }
}
