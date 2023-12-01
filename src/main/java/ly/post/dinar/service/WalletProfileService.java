package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.WalletProfileCriteria;
import ly.post.dinar.repository.WalletProfileRepository;
import ly.post.dinar.service.dto.WalletProfileDTO;
import ly.post.dinar.service.mapper.WalletProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.WalletProfile}.
 */
@Service
@Transactional
public class WalletProfileService {

    private final Logger log = LoggerFactory.getLogger(WalletProfileService.class);

    private final WalletProfileRepository walletProfileRepository;

    private final WalletProfileMapper walletProfileMapper;

    public WalletProfileService(WalletProfileRepository walletProfileRepository, WalletProfileMapper walletProfileMapper) {
        this.walletProfileRepository = walletProfileRepository;
        this.walletProfileMapper = walletProfileMapper;
    }

    /**
     * Save a walletProfile.
     *
     * @param walletProfileDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<WalletProfileDTO> save(WalletProfileDTO walletProfileDTO) {
        log.debug("Request to save WalletProfile : {}", walletProfileDTO);
        return walletProfileRepository.save(walletProfileMapper.toEntity(walletProfileDTO)).map(walletProfileMapper::toDto);
    }

    /**
     * Update a walletProfile.
     *
     * @param walletProfileDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<WalletProfileDTO> update(WalletProfileDTO walletProfileDTO) {
        log.debug("Request to update WalletProfile : {}", walletProfileDTO);
        return walletProfileRepository.save(walletProfileMapper.toEntity(walletProfileDTO)).map(walletProfileMapper::toDto);
    }

    /**
     * Partially update a walletProfile.
     *
     * @param walletProfileDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<WalletProfileDTO> partialUpdate(WalletProfileDTO walletProfileDTO) {
        log.debug("Request to partially update WalletProfile : {}", walletProfileDTO);

        return walletProfileRepository
            .findById(walletProfileDTO.getId())
            .map(existingWalletProfile -> {
                walletProfileMapper.partialUpdate(existingWalletProfile, walletProfileDTO);

                return existingWalletProfile;
            })
            .flatMap(walletProfileRepository::save)
            .map(walletProfileMapper::toDto);
    }

    /**
     * Get all the walletProfiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<WalletProfileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WalletProfiles");
        return walletProfileRepository.findAllBy(pageable).map(walletProfileMapper::toDto);
    }

    /**
     * Find walletProfiles by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<WalletProfileDTO> findByCriteria(WalletProfileCriteria criteria, Pageable pageable) {
        log.debug("Request to get all WalletProfiles by Criteria");
        return walletProfileRepository.findByCriteria(criteria, pageable).map(walletProfileMapper::toDto);
    }

    /**
     * Find the count of walletProfiles by criteria.
     * @param criteria filtering criteria
     * @return the count of walletProfiles
     */
    public Mono<Long> countByCriteria(WalletProfileCriteria criteria) {
        log.debug("Request to get the count of all WalletProfiles by Criteria");
        return walletProfileRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of walletProfiles available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return walletProfileRepository.count();
    }

    /**
     * Get one walletProfile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<WalletProfileDTO> findOne(Long id) {
        log.debug("Request to get WalletProfile : {}", id);
        return walletProfileRepository.findById(id).map(walletProfileMapper::toDto);
    }

    /**
     * Delete the walletProfile by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete WalletProfile : {}", id);
        return walletProfileRepository.deleteById(id);
    }
}
