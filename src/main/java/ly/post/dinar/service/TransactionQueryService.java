package ly.post.dinar.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.Transaction;
import ly.post.dinar.repository.TransactionRepository;
import ly.post.dinar.service.criteria.TransactionCriteria;
import ly.post.dinar.service.dto.TransactionDTO;
import ly.post.dinar.service.mapper.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Transaction} entities in the database.
 * The main input is a {@link TransactionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TransactionDTO} or a {@link Page} of {@link TransactionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TransactionQueryService extends QueryService<Transaction> {

    private final Logger log = LoggerFactory.getLogger(TransactionQueryService.class);

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public TransactionQueryService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    /**
     * Return a {@link List} of {@link TransactionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TransactionDTO> findByCriteria(TransactionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Transaction> specification = createSpecification(criteria);
        return transactionMapper.toDto(transactionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TransactionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TransactionDTO> findByCriteria(TransactionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Transaction> specification = createSpecification(criteria);
        return transactionRepository.findAll(specification, page).map(transactionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TransactionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Transaction> specification = createSpecification(criteria);
        return transactionRepository.count(specification);
    }

    /**
     * Function to convert {@link TransactionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Transaction> createSpecification(TransactionCriteria criteria) {
        Specification<Transaction> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Transaction_.id));
            }
            if (criteria.getTransactionReference() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getTransactionReference(), Transaction_.transactionReference));
            }
            if (criteria.getTransactionSecret() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getTransactionSecret(), Transaction_.transactionSecret));
            }
            if (criteria.getTransactionOtp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTransactionOtp(), Transaction_.transactionOtp));
            }
            if (criteria.getPaymentType() != null) {
                specification = specification.and(buildSpecification(criteria.getPaymentType(), Transaction_.paymentType));
            }
            if (criteria.getTransactionStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionStatus(), Transaction_.transactionStatus));
            }
            if (criteria.getTransactionType() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionType(), Transaction_.transactionType));
            }
            if (criteria.getVendorReference() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVendorReference(), Transaction_.vendorReference));
            }
            if (criteria.getVendorMessage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVendorMessage(), Transaction_.vendorMessage));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), Transaction_.amount));
            }
            if (criteria.getFees() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFees(), Transaction_.fees));
            }
            if (criteria.getTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotal(), Transaction_.total));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Transaction_.notes));
            }
            if (criteria.getSenderMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSenderMobileNo(), Transaction_.senderMobileNo));
            }
            if (criteria.getSenderName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSenderName(), Transaction_.senderName));
            }
            if (criteria.getSenderIdNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSenderIdNo(), Transaction_.senderIdNo));
            }
            if (criteria.getReceiverName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReceiverName(), Transaction_.receiverName));
            }
            if (criteria.getReceiverMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReceiverMobileNo(), Transaction_.receiverMobileNo));
            }
            if (criteria.getReceiverIdNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReceiverIdNo(), Transaction_.receiverIdNo));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Transaction_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Transaction_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Transaction_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Transaction_.lastModifiedDate));
            }
            if (criteria.getSenderId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getSenderId(),
                            root -> root.join(Transaction_.sender, JoinType.LEFT).get(WalletUser_.id)
                        )
                    );
            }
            if (criteria.getReceiverId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getReceiverId(),
                            root -> root.join(Transaction_.receiver, JoinType.LEFT).get(WalletUser_.id)
                        )
                    );
            }
            if (criteria.getWalletTransactionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getWalletTransactionId(),
                            root -> root.join(Transaction_.walletTransactions, JoinType.LEFT).get(WalletTransaction_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
