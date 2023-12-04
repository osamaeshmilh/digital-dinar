package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.WalletTransaction;
import ly.post.dinar.repository.WalletTransactionRepository;
import ly.post.dinar.service.dto.WalletTransactionDTO;
import ly.post.dinar.service.mapper.WalletTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.WalletTransaction}.
 */
@Service
@Transactional
public class WalletTransactionService {

    private final Logger log = LoggerFactory.getLogger(WalletTransactionService.class);

    private final WalletTransactionRepository walletTransactionRepository;

    private final WalletTransactionMapper walletTransactionMapper;

    public WalletTransactionService(
        WalletTransactionRepository walletTransactionRepository,
        WalletTransactionMapper walletTransactionMapper
    ) {
        this.walletTransactionRepository = walletTransactionRepository;
        this.walletTransactionMapper = walletTransactionMapper;
    }

    /**
     * Save a walletTransaction.
     *
     * @param walletTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    public WalletTransactionDTO save(WalletTransactionDTO walletTransactionDTO) {
        log.debug("Request to save WalletTransaction : {}", walletTransactionDTO);
        WalletTransaction walletTransaction = walletTransactionMapper.toEntity(walletTransactionDTO);
        walletTransaction = walletTransactionRepository.save(walletTransaction);
        return walletTransactionMapper.toDto(walletTransaction);
    }

    /**
     * Update a walletTransaction.
     *
     * @param walletTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    public WalletTransactionDTO update(WalletTransactionDTO walletTransactionDTO) {
        log.debug("Request to update WalletTransaction : {}", walletTransactionDTO);
        WalletTransaction walletTransaction = walletTransactionMapper.toEntity(walletTransactionDTO);
        walletTransaction = walletTransactionRepository.save(walletTransaction);
        return walletTransactionMapper.toDto(walletTransaction);
    }

    /**
     * Partially update a walletTransaction.
     *
     * @param walletTransactionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WalletTransactionDTO> partialUpdate(WalletTransactionDTO walletTransactionDTO) {
        log.debug("Request to partially update WalletTransaction : {}", walletTransactionDTO);

        return walletTransactionRepository
            .findById(walletTransactionDTO.getId())
            .map(existingWalletTransaction -> {
                walletTransactionMapper.partialUpdate(existingWalletTransaction, walletTransactionDTO);

                return existingWalletTransaction;
            })
            .map(walletTransactionRepository::save)
            .map(walletTransactionMapper::toDto);
    }

    /**
     * Get all the walletTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WalletTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WalletTransactions");
        return walletTransactionRepository.findAll(pageable).map(walletTransactionMapper::toDto);
    }

    /**
     * Get one walletTransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WalletTransactionDTO> findOne(Long id) {
        log.debug("Request to get WalletTransaction : {}", id);
        return walletTransactionRepository.findById(id).map(walletTransactionMapper::toDto);
    }

    /**
     * Delete the walletTransaction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WalletTransaction : {}", id);
        walletTransactionRepository.deleteById(id);
    }
}
