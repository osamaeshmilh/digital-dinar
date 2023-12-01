package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.MerchantCriteria;
import ly.post.dinar.repository.MerchantRepository;
import ly.post.dinar.service.dto.MerchantDTO;
import ly.post.dinar.service.mapper.MerchantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Merchant}.
 */
@Service
@Transactional
public class MerchantService {

    private final Logger log = LoggerFactory.getLogger(MerchantService.class);

    private final MerchantRepository merchantRepository;

    private final MerchantMapper merchantMapper;

    public MerchantService(MerchantRepository merchantRepository, MerchantMapper merchantMapper) {
        this.merchantRepository = merchantRepository;
        this.merchantMapper = merchantMapper;
    }

    /**
     * Save a merchant.
     *
     * @param merchantDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<MerchantDTO> save(MerchantDTO merchantDTO) {
        log.debug("Request to save Merchant : {}", merchantDTO);
        return merchantRepository.save(merchantMapper.toEntity(merchantDTO)).map(merchantMapper::toDto);
    }

    /**
     * Update a merchant.
     *
     * @param merchantDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<MerchantDTO> update(MerchantDTO merchantDTO) {
        log.debug("Request to update Merchant : {}", merchantDTO);
        return merchantRepository.save(merchantMapper.toEntity(merchantDTO)).map(merchantMapper::toDto);
    }

    /**
     * Partially update a merchant.
     *
     * @param merchantDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<MerchantDTO> partialUpdate(MerchantDTO merchantDTO) {
        log.debug("Request to partially update Merchant : {}", merchantDTO);

        return merchantRepository
            .findById(merchantDTO.getId())
            .map(existingMerchant -> {
                merchantMapper.partialUpdate(existingMerchant, merchantDTO);

                return existingMerchant;
            })
            .flatMap(merchantRepository::save)
            .map(merchantMapper::toDto);
    }

    /**
     * Get all the merchants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<MerchantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Merchants");
        return merchantRepository.findAllBy(pageable).map(merchantMapper::toDto);
    }

    /**
     * Find merchants by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<MerchantDTO> findByCriteria(MerchantCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Merchants by Criteria");
        return merchantRepository.findByCriteria(criteria, pageable).map(merchantMapper::toDto);
    }

    /**
     * Find the count of merchants by criteria.
     * @param criteria filtering criteria
     * @return the count of merchants
     */
    public Mono<Long> countByCriteria(MerchantCriteria criteria) {
        log.debug("Request to get the count of all Merchants by Criteria");
        return merchantRepository.countByCriteria(criteria);
    }

    /**
     * Get all the merchants with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<MerchantDTO> findAllWithEagerRelationships(Pageable pageable) {
        return merchantRepository.findAllWithEagerRelationships(pageable).map(merchantMapper::toDto);
    }

    /**
     * Returns the number of merchants available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return merchantRepository.count();
    }

    /**
     * Get one merchant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<MerchantDTO> findOne(Long id) {
        log.debug("Request to get Merchant : {}", id);
        return merchantRepository.findOneWithEagerRelationships(id).map(merchantMapper::toDto);
    }

    /**
     * Delete the merchant by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Merchant : {}", id);
        return merchantRepository.deleteById(id);
    }
}
