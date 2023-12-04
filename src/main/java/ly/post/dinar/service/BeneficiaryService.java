package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.Beneficiary;
import ly.post.dinar.repository.BeneficiaryRepository;
import ly.post.dinar.service.dto.BeneficiaryDTO;
import ly.post.dinar.service.mapper.BeneficiaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public BeneficiaryDTO save(BeneficiaryDTO beneficiaryDTO) {
        log.debug("Request to save Beneficiary : {}", beneficiaryDTO);
        Beneficiary beneficiary = beneficiaryMapper.toEntity(beneficiaryDTO);
        beneficiary = beneficiaryRepository.save(beneficiary);
        return beneficiaryMapper.toDto(beneficiary);
    }

    /**
     * Update a beneficiary.
     *
     * @param beneficiaryDTO the entity to save.
     * @return the persisted entity.
     */
    public BeneficiaryDTO update(BeneficiaryDTO beneficiaryDTO) {
        log.debug("Request to update Beneficiary : {}", beneficiaryDTO);
        Beneficiary beneficiary = beneficiaryMapper.toEntity(beneficiaryDTO);
        beneficiary = beneficiaryRepository.save(beneficiary);
        return beneficiaryMapper.toDto(beneficiary);
    }

    /**
     * Partially update a beneficiary.
     *
     * @param beneficiaryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BeneficiaryDTO> partialUpdate(BeneficiaryDTO beneficiaryDTO) {
        log.debug("Request to partially update Beneficiary : {}", beneficiaryDTO);

        return beneficiaryRepository
            .findById(beneficiaryDTO.getId())
            .map(existingBeneficiary -> {
                beneficiaryMapper.partialUpdate(existingBeneficiary, beneficiaryDTO);

                return existingBeneficiary;
            })
            .map(beneficiaryRepository::save)
            .map(beneficiaryMapper::toDto);
    }

    /**
     * Get all the beneficiaries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BeneficiaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Beneficiaries");
        return beneficiaryRepository.findAll(pageable).map(beneficiaryMapper::toDto);
    }

    /**
     * Get all the beneficiaries with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<BeneficiaryDTO> findAllWithEagerRelationships(Pageable pageable) {
        return beneficiaryRepository.findAllWithEagerRelationships(pageable).map(beneficiaryMapper::toDto);
    }

    /**
     * Get one beneficiary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BeneficiaryDTO> findOne(Long id) {
        log.debug("Request to get Beneficiary : {}", id);
        return beneficiaryRepository.findOneWithEagerRelationships(id).map(beneficiaryMapper::toDto);
    }

    /**
     * Delete the beneficiary by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Beneficiary : {}", id);
        beneficiaryRepository.deleteById(id);
    }
}
