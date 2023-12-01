package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.BeneficiaryCriteria;
import ly.post.dinar.repository.BeneficiaryRepository;
import ly.post.dinar.service.dto.BeneficiaryDTO;
import ly.post.dinar.service.mapper.BeneficiaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Beneficiary}.
 */
@Service
@Transactional
public class BeneficiaryService {

    private final Logger log = LoggerFactory.getLogger(BeneficiaryService.class);

    private final BeneficiaryRepository beneficiaryRepository;

    private final BeneficiaryMapper beneficiaryMapper;

    public BeneficiaryService(BeneficiaryRepository beneficiaryRepository, BeneficiaryMapper beneficiaryMapper) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.beneficiaryMapper = beneficiaryMapper;
    }

    /**
     * Save a beneficiary.
     *
     * @param beneficiaryDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<BeneficiaryDTO> save(BeneficiaryDTO beneficiaryDTO) {
        log.debug("Request to save Beneficiary : {}", beneficiaryDTO);
        return beneficiaryRepository.save(beneficiaryMapper.toEntity(beneficiaryDTO)).map(beneficiaryMapper::toDto);
    }

    /**
     * Update a beneficiary.
     *
     * @param beneficiaryDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<BeneficiaryDTO> update(BeneficiaryDTO beneficiaryDTO) {
        log.debug("Request to update Beneficiary : {}", beneficiaryDTO);
        return beneficiaryRepository.save(beneficiaryMapper.toEntity(beneficiaryDTO)).map(beneficiaryMapper::toDto);
    }

    /**
     * Partially update a beneficiary.
     *
     * @param beneficiaryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<BeneficiaryDTO> partialUpdate(BeneficiaryDTO beneficiaryDTO) {
        log.debug("Request to partially update Beneficiary : {}", beneficiaryDTO);

        return beneficiaryRepository
            .findById(beneficiaryDTO.getId())
            .map(existingBeneficiary -> {
                beneficiaryMapper.partialUpdate(existingBeneficiary, beneficiaryDTO);

                return existingBeneficiary;
            })
            .flatMap(beneficiaryRepository::save)
            .map(beneficiaryMapper::toDto);
    }

    /**
     * Get all the beneficiaries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<BeneficiaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Beneficiaries");
        return beneficiaryRepository.findAllBy(pageable).map(beneficiaryMapper::toDto);
    }

    /**
     * Find beneficiaries by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<BeneficiaryDTO> findByCriteria(BeneficiaryCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Beneficiaries by Criteria");
        return beneficiaryRepository.findByCriteria(criteria, pageable).map(beneficiaryMapper::toDto);
    }

    /**
     * Find the count of beneficiaries by criteria.
     * @param criteria filtering criteria
     * @return the count of beneficiaries
     */
    public Mono<Long> countByCriteria(BeneficiaryCriteria criteria) {
        log.debug("Request to get the count of all Beneficiaries by Criteria");
        return beneficiaryRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of beneficiaries available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return beneficiaryRepository.count();
    }

    /**
     * Get one beneficiary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<BeneficiaryDTO> findOne(Long id) {
        log.debug("Request to get Beneficiary : {}", id);
        return beneficiaryRepository.findById(id).map(beneficiaryMapper::toDto);
    }

    /**
     * Delete the beneficiary by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Beneficiary : {}", id);
        return beneficiaryRepository.deleteById(id);
    }
}
