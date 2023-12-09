package ly.post.dinar.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.WalletUser;
import ly.post.dinar.repository.WalletUserRepository;
import ly.post.dinar.service.criteria.WalletUserCriteria;
import ly.post.dinar.service.dto.WalletUserDTO;
import ly.post.dinar.service.mapper.WalletUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link WalletUser} entities in the database.
 * The main input is a {@link WalletUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WalletUserDTO} or a {@link Page} of {@link WalletUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WalletUserQueryService extends QueryService<WalletUser> {

    private final Logger log = LoggerFactory.getLogger(WalletUserQueryService.class);

    private final WalletUserRepository walletUserRepository;

    private final WalletUserMapper walletUserMapper;

    public WalletUserQueryService(WalletUserRepository walletUserRepository, WalletUserMapper walletUserMapper) {
        this.walletUserRepository = walletUserRepository;
        this.walletUserMapper = walletUserMapper;
    }

    /**
     * Return a {@link List} of {@link WalletUserDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WalletUserDTO> findByCriteria(WalletUserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WalletUser> specification = createSpecification(criteria);
        return walletUserMapper.toDto(walletUserRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WalletUserDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WalletUserDTO> findByCriteria(WalletUserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WalletUser> specification = createSpecification(criteria);
        return walletUserRepository.findAll(specification, page).map(walletUserMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WalletUserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WalletUser> specification = createSpecification(criteria);
        return walletUserRepository.count(specification);
    }

    /**
     * Function to convert {@link WalletUserCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<WalletUser> createSpecification(WalletUserCriteria criteria) {
        Specification<WalletUser> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), WalletUser_.id));
            }
            if (criteria.getArabicFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArabicFirstName(), WalletUser_.arabicFirstName));
            }
            if (criteria.getArabicMiddleName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArabicMiddleName(), WalletUser_.arabicMiddleName));
            }
            if (criteria.getArabicLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArabicLastName(), WalletUser_.arabicLastName));
            }
            if (criteria.getEnglishFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEnglishFirstName(), WalletUser_.englishFirstName));
            }
            if (criteria.getEnglishMiddleName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEnglishMiddleName(), WalletUser_.englishMiddleName));
            }
            if (criteria.getEnglishLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEnglishLastName(), WalletUser_.englishLastName));
            }
            if (criteria.getCommercialRegistryNo() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getCommercialRegistryNo(), WalletUser_.commercialRegistryNo));
            }
            if (criteria.getCommercialLicenceNo() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getCommercialLicenceNo(), WalletUser_.commercialLicenceNo));
            }
            if (criteria.getCommercialName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCommercialName(), WalletUser_.commercialName));
            }
            if (criteria.getBirthDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthDate(), WalletUser_.birthDate));
            }
            if (criteria.getBirthPlace() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBirthPlace(), WalletUser_.birthPlace));
            }
            if (criteria.getNationalNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNationalNo(), WalletUser_.nationalNo));
            }
            if (criteria.getRegisterNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegisterNo(), WalletUser_.registerNo));
            }
            if (criteria.getGender() != null) {
                specification = specification.and(buildSpecification(criteria.getGender(), WalletUser_.gender));
            }
            if (criteria.getIdType() != null) {
                specification = specification.and(buildSpecification(criteria.getIdType(), WalletUser_.idType));
            }
            if (criteria.getIdNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdNo(), WalletUser_.idNo));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileNo(), WalletUser_.mobileNo));
            }
            if (criteria.getVerifiedByEmailOtp() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedByEmailOtp(), WalletUser_.verifiedByEmailOtp));
            }
            if (criteria.getVerifiedByMobileOtp() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedByMobileOtp(), WalletUser_.verifiedByMobileOtp));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), WalletUser_.email));
            }
            if (criteria.getNidStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNidStatus(), WalletUser_.nidStatus));
            }
            if (criteria.getIsForeign() != null) {
                specification = specification.and(buildSpecification(criteria.getIsForeign(), WalletUser_.isForeign));
            }
            if (criteria.getIsMobileConnectedWithNid() != null) {
                specification =
                    specification.and(buildSpecification(criteria.getIsMobileConnectedWithNid(), WalletUser_.isMobileConnectedWithNid));
            }
            if (criteria.getIsKYCVerified() != null) {
                specification = specification.and(buildSpecification(criteria.getIsKYCVerified(), WalletUser_.isKYCVerified));
            }
            if (criteria.getWalletType() != null) {
                specification = specification.and(buildSpecification(criteria.getWalletType(), WalletUser_.walletType));
            }
            if (criteria.getWalletStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getWalletStatus(), WalletUser_.walletStatus));
            }
            if (criteria.getImageUrlFile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrlFile(), WalletUser_.imageUrlFile));
            }
            if (criteria.getPostCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPostCode(), WalletUser_.postCode));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), WalletUser_.address));
            }
            if (criteria.getLat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLat(), WalletUser_.lat));
            }
            if (criteria.getLng() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLng(), WalletUser_.lng));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), WalletUser_.notes));
            }
            if (criteria.getBankAccountName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankAccountName(), WalletUser_.bankAccountName));
            }
            if (criteria.getBankAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankAccountNumber(), WalletUser_.bankAccountNumber));
            }
            if (criteria.getBankAccountIBAN() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankAccountIBAN(), WalletUser_.bankAccountIBAN));
            }
            if (criteria.getBankAccountSWIFT() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankAccountSWIFT(), WalletUser_.bankAccountSWIFT));
            }
            if (criteria.getWalletPublicKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWalletPublicKey(), WalletUser_.walletPublicKey));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), WalletUser_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), WalletUser_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), WalletUser_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), WalletUser_.lastModifiedDate));
            }
            if (criteria.getUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUserId(), root -> root.join(WalletUser_.user, JoinType.LEFT).get(User_.id))
                    );
            }
            if (criteria.getCategoryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCategoryId(),
                            root -> root.join(WalletUser_.category, JoinType.LEFT).get(Category_.id)
                        )
                    );
            }
            if (criteria.getCityId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCityId(), root -> root.join(WalletUser_.city, JoinType.LEFT).get(City_.id))
                    );
            }
            if (criteria.getWalletProfileId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getWalletProfileId(),
                            root -> root.join(WalletUser_.walletProfile, JoinType.LEFT).get(WalletProfile_.id)
                        )
                    );
            }
            if (criteria.getBankBranchId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getBankBranchId(),
                            root -> root.join(WalletUser_.bankBranch, JoinType.LEFT).get(BankBranch_.id)
                        )
                    );
            }
            if (criteria.getWalletTransactionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getWalletTransactionId(),
                            root -> root.join(WalletUser_.walletTransactions, JoinType.LEFT).get(WalletTransaction_.id)
                        )
                    );
            }
            if (criteria.getBeneficiaryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getBeneficiaryId(),
                            root -> root.join(WalletUser_.beneficiaries, JoinType.LEFT).get(Beneficiary_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
