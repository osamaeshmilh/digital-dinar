package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.Bank;
import ly.post.dinar.repository.BankRepository;
import ly.post.dinar.service.dto.BankDTO;
import ly.post.dinar.service.mapper.BankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Bank}.
 */
@Service
@Transactional
public class BankService {

    private final Logger log = LoggerFactory.getLogger(BankService.class);

    private final BankRepository bankRepository;

    private final BankMapper bankMapper;

    public BankService(BankRepository bankRepository, BankMapper bankMapper) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    /**
     * Save a bank.
     *
     * @param bankDTO the entity to save.
     * @return the persisted entity.
     */
    public BankDTO save(BankDTO bankDTO) {
        log.debug("Request to save Bank : {}", bankDTO);
        Bank bank = bankMapper.toEntity(bankDTO);
        bank = bankRepository.save(bank);
        return bankMapper.toDto(bank);
    }

    /**
     * Update a bank.
     *
     * @param bankDTO the entity to save.
     * @return the persisted entity.
     */
    public BankDTO update(BankDTO bankDTO) {
        log.debug("Request to update Bank : {}", bankDTO);
        Bank bank = bankMapper.toEntity(bankDTO);
        bank = bankRepository.save(bank);
        return bankMapper.toDto(bank);
    }

    /**
     * Partially update a bank.
     *
     * @param bankDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BankDTO> partialUpdate(BankDTO bankDTO) {
        log.debug("Request to partially update Bank : {}", bankDTO);

        return bankRepository
            .findById(bankDTO.getId())
            .map(existingBank -> {
                bankMapper.partialUpdate(existingBank, bankDTO);

                return existingBank;
            })
            .map(bankRepository::save)
            .map(bankMapper::toDto);
    }

    /**
     * Get all the banks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BankDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Banks");
        return bankRepository.findAll(pageable).map(bankMapper::toDto);
    }

    /**
     * Get one bank by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BankDTO> findOne(Long id) {
        log.debug("Request to get Bank : {}", id);
        return bankRepository.findById(id).map(bankMapper::toDto);
    }

    /**
     * Delete the bank by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bank : {}", id);
        bankRepository.deleteById(id);
    }
}
