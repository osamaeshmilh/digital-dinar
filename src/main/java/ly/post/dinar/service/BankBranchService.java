package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.BankBranch;
import ly.post.dinar.repository.BankBranchRepository;
import ly.post.dinar.service.dto.BankBranchDTO;
import ly.post.dinar.service.mapper.BankBranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.BankBranch}.
 */
@Service
@Transactional
public class BankBranchService {

    private final Logger log = LoggerFactory.getLogger(BankBranchService.class);

    private final BankBranchRepository bankBranchRepository;

    private final BankBranchMapper bankBranchMapper;

    public BankBranchService(BankBranchRepository bankBranchRepository, BankBranchMapper bankBranchMapper) {
        this.bankBranchRepository = bankBranchRepository;
        this.bankBranchMapper = bankBranchMapper;
    }

    /**
     * Save a bankBranch.
     *
     * @param bankBranchDTO the entity to save.
     * @return the persisted entity.
     */
    public BankBranchDTO save(BankBranchDTO bankBranchDTO) {
        log.debug("Request to save BankBranch : {}", bankBranchDTO);
        BankBranch bankBranch = bankBranchMapper.toEntity(bankBranchDTO);
        bankBranch = bankBranchRepository.save(bankBranch);
        return bankBranchMapper.toDto(bankBranch);
    }

    /**
     * Update a bankBranch.
     *
     * @param bankBranchDTO the entity to save.
     * @return the persisted entity.
     */
    public BankBranchDTO update(BankBranchDTO bankBranchDTO) {
        log.debug("Request to update BankBranch : {}", bankBranchDTO);
        BankBranch bankBranch = bankBranchMapper.toEntity(bankBranchDTO);
        bankBranch = bankBranchRepository.save(bankBranch);
        return bankBranchMapper.toDto(bankBranch);
    }

    /**
     * Partially update a bankBranch.
     *
     * @param bankBranchDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BankBranchDTO> partialUpdate(BankBranchDTO bankBranchDTO) {
        log.debug("Request to partially update BankBranch : {}", bankBranchDTO);

        return bankBranchRepository
            .findById(bankBranchDTO.getId())
            .map(existingBankBranch -> {
                bankBranchMapper.partialUpdate(existingBankBranch, bankBranchDTO);

                return existingBankBranch;
            })
            .map(bankBranchRepository::save)
            .map(bankBranchMapper::toDto);
    }

    /**
     * Get all the bankBranches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BankBranchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BankBranches");
        return bankBranchRepository.findAll(pageable).map(bankBranchMapper::toDto);
    }

    /**
     * Get all the bankBranches with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<BankBranchDTO> findAllWithEagerRelationships(Pageable pageable) {
        return bankBranchRepository.findAllWithEagerRelationships(pageable).map(bankBranchMapper::toDto);
    }

    /**
     * Get one bankBranch by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BankBranchDTO> findOne(Long id) {
        log.debug("Request to get BankBranch : {}", id);
        return bankBranchRepository.findOneWithEagerRelationships(id).map(bankBranchMapper::toDto);
    }

    /**
     * Delete the bankBranch by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BankBranch : {}", id);
        bankBranchRepository.deleteById(id);
    }
}
