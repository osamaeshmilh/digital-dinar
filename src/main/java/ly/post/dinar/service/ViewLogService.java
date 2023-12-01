package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.ViewLogCriteria;
import ly.post.dinar.repository.ViewLogRepository;
import ly.post.dinar.service.dto.ViewLogDTO;
import ly.post.dinar.service.mapper.ViewLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.ViewLog}.
 */
@Service
@Transactional
public class ViewLogService {

    private final Logger log = LoggerFactory.getLogger(ViewLogService.class);

    private final ViewLogRepository viewLogRepository;

    private final ViewLogMapper viewLogMapper;

    public ViewLogService(ViewLogRepository viewLogRepository, ViewLogMapper viewLogMapper) {
        this.viewLogRepository = viewLogRepository;
        this.viewLogMapper = viewLogMapper;
    }

    /**
     * Save a viewLog.
     *
     * @param viewLogDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ViewLogDTO> save(ViewLogDTO viewLogDTO) {
        log.debug("Request to save ViewLog : {}", viewLogDTO);
        return viewLogRepository.save(viewLogMapper.toEntity(viewLogDTO)).map(viewLogMapper::toDto);
    }

    /**
     * Update a viewLog.
     *
     * @param viewLogDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ViewLogDTO> update(ViewLogDTO viewLogDTO) {
        log.debug("Request to update ViewLog : {}", viewLogDTO);
        return viewLogRepository.save(viewLogMapper.toEntity(viewLogDTO)).map(viewLogMapper::toDto);
    }

    /**
     * Partially update a viewLog.
     *
     * @param viewLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ViewLogDTO> partialUpdate(ViewLogDTO viewLogDTO) {
        log.debug("Request to partially update ViewLog : {}", viewLogDTO);

        return viewLogRepository
            .findById(viewLogDTO.getId())
            .map(existingViewLog -> {
                viewLogMapper.partialUpdate(existingViewLog, viewLogDTO);

                return existingViewLog;
            })
            .flatMap(viewLogRepository::save)
            .map(viewLogMapper::toDto);
    }

    /**
     * Get all the viewLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ViewLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ViewLogs");
        return viewLogRepository.findAllBy(pageable).map(viewLogMapper::toDto);
    }

    /**
     * Find viewLogs by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ViewLogDTO> findByCriteria(ViewLogCriteria criteria, Pageable pageable) {
        log.debug("Request to get all ViewLogs by Criteria");
        return viewLogRepository.findByCriteria(criteria, pageable).map(viewLogMapper::toDto);
    }

    /**
     * Find the count of viewLogs by criteria.
     * @param criteria filtering criteria
     * @return the count of viewLogs
     */
    public Mono<Long> countByCriteria(ViewLogCriteria criteria) {
        log.debug("Request to get the count of all ViewLogs by Criteria");
        return viewLogRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of viewLogs available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return viewLogRepository.count();
    }

    /**
     * Get one viewLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ViewLogDTO> findOne(Long id) {
        log.debug("Request to get ViewLog : {}", id);
        return viewLogRepository.findById(id).map(viewLogMapper::toDto);
    }

    /**
     * Delete the viewLog by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ViewLog : {}", id);
        return viewLogRepository.deleteById(id);
    }
}
