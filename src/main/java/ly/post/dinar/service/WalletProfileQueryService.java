package ly.post.dinar.service;

import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.WalletProfile;
import ly.post.dinar.repository.WalletProfileRepository;
import ly.post.dinar.service.criteria.WalletProfileCriteria;
import ly.post.dinar.service.dto.WalletProfileDTO;
import ly.post.dinar.service.mapper.WalletProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link WalletProfile} entities in the database.
 * The main input is a {@link WalletProfileCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WalletProfileDTO} or a {@link Page} of {@link WalletProfileDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WalletProfileQueryService extends QueryService<WalletProfile> {

    private final Logger log = LoggerFactory.getLogger(WalletProfileQueryService.class);

    private final WalletProfileRepository walletProfileRepository;

    private final WalletProfileMapper walletProfileMapper;

    public WalletProfileQueryService(WalletProfileRepository walletProfileRepository, WalletProfileMapper walletProfileMapper) {
        this.walletProfileRepository = walletProfileRepository;
        this.walletProfileMapper = walletProfileMapper;
    }

    /**
     * Return a {@link List} of {@link WalletProfileDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WalletProfileDTO> findByCriteria(WalletProfileCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WalletProfile> specification = createSpecification(criteria);
        return walletProfileMapper.toDto(walletProfileRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WalletProfileDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WalletProfileDTO> findByCriteria(WalletProfileCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WalletProfile> specification = createSpecification(criteria);
        return walletProfileRepository.findAll(specification, page).map(walletProfileMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WalletProfileCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WalletProfile> specification = createSpecification(criteria);
        return walletProfileRepository.count(specification);
    }

    /**
     * Function to convert {@link WalletProfileCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<WalletProfile> createSpecification(WalletProfileCriteria criteria) {
        Specification<WalletProfile> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), WalletProfile_.id));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), WalletProfile_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), WalletProfile_.nameEn));
            }
            if (criteria.getHasTransferAmountLimitPerTransaction() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getHasTransferAmountLimitPerTransaction(),
                            WalletProfile_.hasTransferAmountLimitPerTransaction
                        )
                    );
            }
            if (criteria.getTransferAmountLimitPerTransaction() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(
                            criteria.getTransferAmountLimitPerTransaction(),
                            WalletProfile_.transferAmountLimitPerTransaction
                        )
                    );
            }
            if (criteria.getHasDailyTransferAmountLimit() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getHasDailyTransferAmountLimit(), WalletProfile_.hasDailyTransferAmountLimit)
                    );
            }
            if (criteria.getDailyTransferAmountLimit() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getDailyTransferAmountLimit(), WalletProfile_.dailyTransferAmountLimit)
                    );
            }
            if (criteria.getCanTransferToConsumerWallet() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCanTransferToConsumerWallet(), WalletProfile_.canTransferToConsumerWallet)
                    );
            }
            if (criteria.getCanTransferToMerchantWallet() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCanTransferToMerchantWallet(), WalletProfile_.canTransferToMerchantWallet)
                    );
            }
            if (criteria.getCanTransferToConsumer() != null) {
                specification =
                    specification.and(buildSpecification(criteria.getCanTransferToConsumer(), WalletProfile_.canTransferToConsumer));
            }
            if (criteria.getCanTransferToBank() != null) {
                specification = specification.and(buildSpecification(criteria.getCanTransferToBank(), WalletProfile_.canTransferToBank));
            }
            if (criteria.getCanBuyVouchers() != null) {
                specification = specification.and(buildSpecification(criteria.getCanBuyVouchers(), WalletProfile_.canBuyVouchers));
            }
            if (criteria.getTransferToConsumerWalletFees() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getTransferToConsumerWalletFees(), WalletProfile_.transferToConsumerWalletFees)
                    );
            }
            if (criteria.getTransferToMerchantWalletFees() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getTransferToMerchantWalletFees(), WalletProfile_.transferToMerchantWalletFees)
                    );
            }
            if (criteria.getTransferToConsumerFees() != null) {
                specification =
                    specification.and(buildSpecification(criteria.getTransferToConsumerFees(), WalletProfile_.transferToConsumerFees));
            }
            if (criteria.getTransferToBankFees() != null) {
                specification = specification.and(buildSpecification(criteria.getTransferToBankFees(), WalletProfile_.transferToBankFees));
            }
            if (criteria.getBuyVouchersFees() != null) {
                specification = specification.and(buildSpecification(criteria.getBuyVouchersFees(), WalletProfile_.buyVouchersFees));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), WalletProfile_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), WalletProfile_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), WalletProfile_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), WalletProfile_.lastModifiedDate));
            }
        }
        return specification;
    }
}
