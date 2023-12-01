package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.ConsumerCriteria;
import ly.post.dinar.repository.ConsumerRepository;
import ly.post.dinar.service.dto.ConsumerDTO;
import ly.post.dinar.service.mapper.ConsumerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Consumer}.
 */
@Service
@Transactional
public class ConsumerService {

    private final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    private final ConsumerRepository consumerRepository;

    private final ConsumerMapper consumerMapper;

    public ConsumerService(ConsumerRepository consumerRepository, ConsumerMapper consumerMapper) {
        this.consumerRepository = consumerRepository;
        this.consumerMapper = consumerMapper;
    }

    /**
     * Save a consumer.
     *
     * @param consumerDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ConsumerDTO> save(ConsumerDTO consumerDTO) {
        log.debug("Request to save Consumer : {}", consumerDTO);
        return consumerRepository.save(consumerMapper.toEntity(consumerDTO)).map(consumerMapper::toDto);
    }

    /**
     * Update a consumer.
     *
     * @param consumerDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ConsumerDTO> update(ConsumerDTO consumerDTO) {
        log.debug("Request to update Consumer : {}", consumerDTO);
        return consumerRepository.save(consumerMapper.toEntity(consumerDTO)).map(consumerMapper::toDto);
    }

    /**
     * Partially update a consumer.
     *
     * @param consumerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ConsumerDTO> partialUpdate(ConsumerDTO consumerDTO) {
        log.debug("Request to partially update Consumer : {}", consumerDTO);

        return consumerRepository
            .findById(consumerDTO.getId())
            .map(existingConsumer -> {
                consumerMapper.partialUpdate(existingConsumer, consumerDTO);

                return existingConsumer;
            })
            .flatMap(consumerRepository::save)
            .map(consumerMapper::toDto);
    }

    /**
     * Get all the consumers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ConsumerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Consumers");
        return consumerRepository.findAllBy(pageable).map(consumerMapper::toDto);
    }

    /**
     * Find consumers by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ConsumerDTO> findByCriteria(ConsumerCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Consumers by Criteria");
        return consumerRepository.findByCriteria(criteria, pageable).map(consumerMapper::toDto);
    }

    /**
     * Find the count of consumers by criteria.
     * @param criteria filtering criteria
     * @return the count of consumers
     */
    public Mono<Long> countByCriteria(ConsumerCriteria criteria) {
        log.debug("Request to get the count of all Consumers by Criteria");
        return consumerRepository.countByCriteria(criteria);
    }

    /**
     * Get all the consumers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<ConsumerDTO> findAllWithEagerRelationships(Pageable pageable) {
        return consumerRepository.findAllWithEagerRelationships(pageable).map(consumerMapper::toDto);
    }

    /**
     * Returns the number of consumers available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return consumerRepository.count();
    }

    /**
     * Get one consumer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ConsumerDTO> findOne(Long id) {
        log.debug("Request to get Consumer : {}", id);
        return consumerRepository.findOneWithEagerRelationships(id).map(consumerMapper::toDto);
    }

    /**
     * Delete the consumer by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Consumer : {}", id);
        return consumerRepository.deleteById(id);
    }
}
