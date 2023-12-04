package ly.post.dinar.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.WalletTransaction;
import ly.post.dinar.repository.WalletTransactionRepository;
import ly.post.dinar.service.criteria.WalletTransactionCriteria;
import ly.post.dinar.service.dto.WalletTransactionDTO;
import ly.post.dinar.service.mapper.WalletTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link WalletTransaction} entities in the database.
 * The main input is a {@link WalletTransactionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WalletTransactionDTO} or a {@link Page} of {@link WalletTransactionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WalletTransactionQueryService extends QueryService<WalletTransaction> {

    private final Logger log = LoggerFactory.getLogger(WalletTransactionQueryService.class);

    private final WalletTransactionRepository walletTransactionRepository;

    private final WalletTransactionMapper walletTransactionMapper;

    public WalletTransactionQueryService(
        WalletTransactionRepository walletTransactionRepository,
        WalletTransactionMapper walletTransactionMapper
    ) {
        this.walletTransactionRepository = walletTransactionRepository;
        this.walletTransactionMapper = walletTransactionMapper;
    }

    /**
     * Return a {@link List} of {@link WalletTransactionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WalletTransactionDTO> findByCriteria(WalletTransactionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WalletTransaction> specification = createSpecification(criteria);
        return walletTransactionMapper.toDto(walletTransactionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WalletTransactionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WalletTransactionDTO> findByCriteria(WalletTransactionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WalletTransaction> specification = createSpecification(criteria);
        return walletTransactionRepository.findAll(specification, page).map(walletTransactionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WalletTransactionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WalletTransaction> specification = createSpecification(criteria);
        return walletTransactionRepository.count(specification);
    }

    /**
     * Function to convert {@link WalletTransactionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<WalletTransaction> createSpecification(WalletTransactionCriteria criteria) {
        Specification<WalletTransaction> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), WalletTransaction_.id));
            }
            if (criteria.getTransactionNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTransactionNo(), WalletTransaction_.transactionNo));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), WalletTransaction_.amount));
            }
            if (criteria.getWalletAction() != null) {
                specification = specification.and(buildSpecification(criteria.getWalletAction(), WalletTransaction_.walletAction));
            }
            if (criteria.getUserPreviousTransactionNo() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getUserPreviousTransactionNo(), WalletTransaction_.userPreviousTransactionNo)
                    );
            }
            if (criteria.getTotalBeforeAction() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalBeforeAction(), WalletTransaction_.totalBeforeAction));
            }
            if (criteria.getTotalAfterAction() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalAfterAction(), WalletTransaction_.totalAfterAction));
            }
            if (criteria.getPaymentType() != null) {
                specification = specification.and(buildSpecification(criteria.getPaymentType(), WalletTransaction_.paymentType));
            }
            if (criteria.getPaymentReference() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getPaymentReference(), WalletTransaction_.paymentReference));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), WalletTransaction_.notes));
            }
            if (criteria.getWalletOwnerType() != null) {
                specification = specification.and(buildSpecification(criteria.getWalletOwnerType(), WalletTransaction_.walletOwnerType));
            }
            if (criteria.getTransactionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTransactionId(),
                            root -> root.join(WalletTransaction_.transaction, JoinType.LEFT).get(Transaction_.id)
                        )
                    );
            }
            if (criteria.getWalletUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getWalletUserId(),
                            root -> root.join(WalletTransaction_.walletUser, JoinType.LEFT).get(WalletUser_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
