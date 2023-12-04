package ly.post.dinar.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.Beneficiary;
import ly.post.dinar.repository.BeneficiaryRepository;
import ly.post.dinar.service.criteria.BeneficiaryCriteria;
import ly.post.dinar.service.dto.BeneficiaryDTO;
import ly.post.dinar.service.mapper.BeneficiaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Beneficiary} entities in the database.
 * The main input is a {@link BeneficiaryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BeneficiaryDTO} or a {@link Page} of {@link BeneficiaryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BeneficiaryQueryService extends QueryService<Beneficiary> {

    private final Logger log = LoggerFactory.getLogger(BeneficiaryQueryService.class);

    private final BeneficiaryRepository beneficiaryRepository;

    private final BeneficiaryMapper beneficiaryMapper;

    public BeneficiaryQueryService(BeneficiaryRepository beneficiaryRepository, BeneficiaryMapper beneficiaryMapper) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.beneficiaryMapper = beneficiaryMapper;
    }

    /**
     * Return a {@link List} of {@link BeneficiaryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BeneficiaryDTO> findByCriteria(BeneficiaryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Beneficiary> specification = createSpecification(criteria);
        return beneficiaryMapper.toDto(beneficiaryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BeneficiaryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BeneficiaryDTO> findByCriteria(BeneficiaryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Beneficiary> specification = createSpecification(criteria);
        return beneficiaryRepository.findAll(specification, page).map(beneficiaryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BeneficiaryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Beneficiary> specification = createSpecification(criteria);
        return beneficiaryRepository.count(specification);
    }

    /**
     * Function to convert {@link BeneficiaryCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Beneficiary> createSpecification(BeneficiaryCriteria criteria) {
        Specification<Beneficiary> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Beneficiary_.id));
            }
            if (criteria.getArabicFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArabicFirstName(), Beneficiary_.arabicFirstName));
            }
            if (criteria.getArabicLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArabicLastName(), Beneficiary_.arabicLastName));
            }
            if (criteria.getEnglishFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEnglishFirstName(), Beneficiary_.englishFirstName));
            }
            if (criteria.getEnglishLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEnglishLastName(), Beneficiary_.englishLastName));
            }
            if (criteria.getBankAccountName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankAccountName(), Beneficiary_.bankAccountName));
            }
            if (criteria.getBankAccountNumber() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getBankAccountNumber(), Beneficiary_.bankAccountNumber));
            }
            if (criteria.getIdType() != null) {
                specification = specification.and(buildSpecification(criteria.getIdType(), Beneficiary_.idType));
            }
            if (criteria.getIdNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdNo(), Beneficiary_.idNo));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileNo(), Beneficiary_.mobileNo));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Beneficiary_.email));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Beneficiary_.notes));
            }
            if (criteria.getIsVerified() != null) {
                specification = specification.and(buildSpecification(criteria.getIsVerified(), Beneficiary_.isVerified));
            }
            if (criteria.getHasTransferred() != null) {
                specification = specification.and(buildSpecification(criteria.getHasTransferred(), Beneficiary_.hasTransferred));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Beneficiary_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Beneficiary_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Beneficiary_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Beneficiary_.lastModifiedDate));
            }
            if (criteria.getBankBranchId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getBankBranchId(),
                            root -> root.join(Beneficiary_.bankBranch, JoinType.LEFT).get(BankBranch_.id)
                        )
                    );
            }
            if (criteria.getWalletUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getWalletUserId(),
                            root -> root.join(Beneficiary_.walletUser, JoinType.LEFT).get(WalletUser_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
