package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.WalletTransaction;
import ly.post.dinar.domain.enumeration.TransactionStatus;
import ly.post.dinar.domain.enumeration.TransactionType;
import ly.post.dinar.domain.enumeration.WalletAction;
import ly.post.dinar.repository.WalletTransactionRepository;
import ly.post.dinar.service.criteria.WalletTransactionCriteria;
import ly.post.dinar.service.dto.TransactionDTO;
import ly.post.dinar.service.dto.WalletTransactionDTO;
import ly.post.dinar.service.dto.WalletUserDTO;
import ly.post.dinar.service.mapper.WalletTransactionMapper;
import ly.post.dinar.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.filter.LongFilter;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.WalletTransaction}.
 */
@Service
@Transactional
public class WalletTransactionService {

    private final Logger log = LoggerFactory.getLogger(WalletTransactionService.class);

    private final WalletTransactionRepository walletTransactionRepository;

    private final WalletTransactionMapper walletTransactionMapper;

    private final WalletTransactionQueryService walletTransactionQueryService;

    private final TransactionService transactionService;

    public WalletTransactionService(
        WalletTransactionRepository walletTransactionRepository,
        WalletTransactionMapper walletTransactionMapper,
        WalletTransactionQueryService walletTransactionQueryService,
        TransactionService transactionService
    ) {
        this.walletTransactionRepository = walletTransactionRepository;
        this.walletTransactionMapper = walletTransactionMapper;
        this.walletTransactionQueryService = walletTransactionQueryService;
        this.transactionService = transactionService;
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

    public WalletTransactionDTO walletTransfer(WalletUserDTO fromCustomer, WalletUserDTO toCustomer, Float amount) {
        WalletTransactionCriteria criteria = new WalletTransactionCriteria();
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(fromCustomer.getId());
        criteria.setWalletUserId(longFilter);

        Float transferFee = 0F;

        //TODO :: WALLET FEES
        //        if (amount >= 1 && amount <= 1000) {
        //            transferFee = Float.valueOf(settingService.getSettingByKey("w2w1000").get().getValue());
        //        } else if (amount >= 1000 && amount <= 3000) {
        //            transferFee = Float.valueOf(settingService.getSettingByKey("w2w3000").get().getValue());
        //        } else if (amount >= 3000 && amount <= 10000) {
        //            transferFee = Float.valueOf(settingService.getSettingByKey("w2w10000").get().getValue());
        //        } else {
        //            throw new BadRequestAlertException("Transfer Amount Not Allowed !", "", "TRANSFER_NOT_ALLOWED");
        //        }

        // if (amount > customerWalletQueryService.sumAmountByCriteria(criteria)) {
        //     throw new BadRequestAlertException("No Credit in Wallet !", "", "NO_CREDIT");
        // }

        if (walletTransactionQueryService.sumTotalByCriteria(criteria) >= amount + transferFee) {
            WalletTransactionDTO walletTransactionDTOPlus = new WalletTransactionDTO();
            WalletUserDTO toCustomerDto = new WalletUserDTO();
            toCustomerDto.setId(toCustomer.getId());
            walletTransactionDTOPlus.setWalletUser(toCustomerDto);
            walletTransactionDTOPlus.setWalletAction(WalletAction.DEPOSIT);
            walletTransactionDTOPlus.setAmount(amount);
            walletTransactionDTOPlus.setNotes(
                "from Customer " + fromCustomer.getEnglishFirstName() + "" + fromCustomer.getEnglishLastName()
            );
            save(walletTransactionDTOPlus);

            WalletTransactionDTO walletTransactionDTOMinus = new WalletTransactionDTO();
            walletTransactionDTOMinus.setWalletUser(fromCustomer);
            walletTransactionDTOMinus.setWalletAction(WalletAction.WITHDRAW);
            walletTransactionDTOMinus.setAmount(amount);
            walletTransactionDTOMinus.setNotes("to Customer " + toCustomer.getEnglishFirstName() + "" + toCustomer.getEnglishLastName());
            save(walletTransactionDTOMinus);

            return customerWalletAction(
                amount,
                transferFee,
                fromCustomer,
                "wallet transfer " + "to Customer " + toCustomer.getId() + " (" + toCustomer.getEnglishFirstName() + ")",
                "Transfer Fees"
            );
        } else {
            throw new BadRequestAlertException("No Credit in Wallet !", "", "NO_CREDIT");
        }
    }

    public WalletTransactionDTO customerWalletAction(
        Float amount,
        Float fees,
        WalletUserDTO walletUserDTO,
        String transactionNotes,
        String walletActionNotes
    ) {
        //TransactionDTO transactionDTO = new TransactionDTO();

        WalletTransactionCriteria criteria = new WalletTransactionCriteria();
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(walletUserDTO.getId());
        criteria.setWalletUserId(longFilter);
        if (walletTransactionQueryService.sumTotalByCriteria(criteria) <= fees) {
            throw new BadRequestAlertException("No Credit in Wallet !", "", "NO_CREDIT");
        }

        WalletTransactionDTO customerWalletDTOTransferFee = new WalletTransactionDTO();
        customerWalletDTOTransferFee.setWalletUser(walletUserDTO);
        customerWalletDTOTransferFee.setWalletAction(WalletAction.WITHDRAW);
        customerWalletDTOTransferFee.setAmount(fees);
        customerWalletDTOTransferFee.setNotes(walletActionNotes);

        //notificationService.sendNotificationToCustomer(walletUserDTO.getId(), walletActionNotes, transactionNotes);

        return save(customerWalletDTOTransferFee);
    }

    public WalletTransactionDTO topUpWallet(WalletTransactionDTO walletTransactionDTO) {
        WalletTransaction customerWallet = walletTransactionMapper.toEntity(walletTransactionDTO);
        customerWallet = walletTransactionRepository.save(customerWallet);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setFees(0F);
        transactionDTO.setTotal(walletTransactionDTO.getAmount());
        transactionDTO.setAmount(walletTransactionDTO.getAmount());
        transactionDTO.setTransactionStatus(TransactionStatus.PAYED);
        transactionDTO.setTransactionType(TransactionType.CONSUMER_WALLET_TOP_UP);
        transactionDTO.setPaymentType(walletTransactionDTO.getPaymentType());
        transactionDTO.setNotes(walletTransactionDTO.getNotes() != null ? walletTransactionDTO.getNotes() : "Top up");
        transactionDTO.setReceiver(walletTransactionDTO.getWalletUser());
        transactionService.save(transactionDTO);

        //notificationService.sendNotificationToCustomer(walletTransactionDTO.getWalletUser().getId(), "Wallet Add", walletTransactionDTO.getNotes());

        return walletTransactionMapper.toDto(customerWallet);
    }
}
