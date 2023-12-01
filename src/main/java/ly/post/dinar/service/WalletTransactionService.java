package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.WalletTransactionCriteria;
import ly.post.dinar.repository.WalletTransactionRepository;
import ly.post.dinar.service.dto.WalletTransactionDTO;
import ly.post.dinar.service.mapper.WalletTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<WalletTransactionDTO> save(WalletTransactionDTO walletTransactionDTO) {
        log.debug("Request to save WalletTransaction : {}", walletTransactionDTO);
        return walletTransactionRepository.save(walletTransactionMapper.toEntity(walletTransactionDTO)).map(walletTransactionMapper::toDto);
    }

    /**
     * Update a walletTransaction.
     *
     * @param walletTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<WalletTransactionDTO> update(WalletTransactionDTO walletTransactionDTO) {
        log.debug("Request to update WalletTransaction : {}", walletTransactionDTO);
        return walletTransactionRepository.save(walletTransactionMapper.toEntity(walletTransactionDTO)).map(walletTransactionMapper::toDto);
    }

    /**
     * Partially update a walletTransaction.
     *
     * @param walletTransactionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<WalletTransactionDTO> partialUpdate(WalletTransactionDTO walletTransactionDTO) {
        log.debug("Request to partially update WalletTransaction : {}", walletTransactionDTO);

        return walletTransactionRepository
            .findById(walletTransactionDTO.getId())
            .map(existingWalletTransaction -> {
                walletTransactionMapper.partialUpdate(existingWalletTransaction, walletTransactionDTO);

                return existingWalletTransaction;
            })
            .flatMap(walletTransactionRepository::save)
            .map(walletTransactionMapper::toDto);
    }

    /**
     * Get all the walletTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<WalletTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WalletTransactions");
        return walletTransactionRepository.findAllBy(pageable).map(walletTransactionMapper::toDto);
    }

    /**
     * Find walletTransactions by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<WalletTransactionDTO> findByCriteria(WalletTransactionCriteria criteria, Pageable pageable) {
        log.debug("Request to get all WalletTransactions by Criteria");
        return walletTransactionRepository.findByCriteria(criteria, pageable).map(walletTransactionMapper::toDto);
    }

    /**
     * Find the count of walletTransactions by criteria.
     * @param criteria filtering criteria
     * @return the count of walletTransactions
     */
    public Mono<Long> countByCriteria(WalletTransactionCriteria criteria) {
        log.debug("Request to get the count of all WalletTransactions by Criteria");
        return walletTransactionRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of walletTransactions available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return walletTransactionRepository.count();
    }

    /**
     * Get one walletTransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<WalletTransactionDTO> findOne(Long id) {
        log.debug("Request to get WalletTransaction : {}", id);
        return walletTransactionRepository.findById(id).map(walletTransactionMapper::toDto);
    }

    /**
     * Delete the walletTransaction by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete WalletTransaction : {}", id);
        return walletTransactionRepository.deleteById(id);
    }
}
