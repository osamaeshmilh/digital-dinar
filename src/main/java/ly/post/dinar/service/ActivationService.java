package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.ActivationCriteria;
import ly.post.dinar.repository.ActivationRepository;
import ly.post.dinar.service.dto.ActivationDTO;
import ly.post.dinar.service.mapper.ActivationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Activation}.
 */
@Service
@Transactional
public class ActivationService {

    private final Logger log = LoggerFactory.getLogger(ActivationService.class);

    private final ActivationRepository activationRepository;

    private final ActivationMapper activationMapper;

    public ActivationService(ActivationRepository activationRepository, ActivationMapper activationMapper) {
        this.activationRepository = activationRepository;
        this.activationMapper = activationMapper;
    }

    /**
     * Save a activation.
     *
     * @param activationDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ActivationDTO> save(ActivationDTO activationDTO) {
        log.debug("Request to save Activation : {}", activationDTO);
        return activationRepository.save(activationMapper.toEntity(activationDTO)).map(activationMapper::toDto);
    }

    /**
     * Update a activation.
     *
     * @param activationDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ActivationDTO> update(ActivationDTO activationDTO) {
        log.debug("Request to update Activation : {}", activationDTO);
        return activationRepository.save(activationMapper.toEntity(activationDTO)).map(activationMapper::toDto);
    }

    /**
     * Partially update a activation.
     *
     * @param activationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ActivationDTO> partialUpdate(ActivationDTO activationDTO) {
        log.debug("Request to partially update Activation : {}", activationDTO);

        return activationRepository
            .findById(activationDTO.getId())
            .map(existingActivation -> {
                activationMapper.partialUpdate(existingActivation, activationDTO);

                return existingActivation;
            })
            .flatMap(activationRepository::save)
            .map(activationMapper::toDto);
    }

    /**
     * Get all the activations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ActivationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Activations");
        return activationRepository.findAllBy(pageable).map(activationMapper::toDto);
    }

    /**
     * Find activations by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ActivationDTO> findByCriteria(ActivationCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Activations by Criteria");
        return activationRepository.findByCriteria(criteria, pageable).map(activationMapper::toDto);
    }

    /**
     * Find the count of activations by criteria.
     * @param criteria filtering criteria
     * @return the count of activations
     */
    public Mono<Long> countByCriteria(ActivationCriteria criteria) {
        log.debug("Request to get the count of all Activations by Criteria");
        return activationRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of activations available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return activationRepository.count();
    }

    /**
     * Get one activation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ActivationDTO> findOne(Long id) {
        log.debug("Request to get Activation : {}", id);
        return activationRepository.findById(id).map(activationMapper::toDto);
    }

    /**
     * Delete the activation by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Activation : {}", id);
        return activationRepository.deleteById(id);
    }
}
