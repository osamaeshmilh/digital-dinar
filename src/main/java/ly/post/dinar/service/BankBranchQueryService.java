package ly.post.dinar.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.BankBranch;
import ly.post.dinar.repository.BankBranchRepository;
import ly.post.dinar.service.criteria.BankBranchCriteria;
import ly.post.dinar.service.dto.BankBranchDTO;
import ly.post.dinar.service.mapper.BankBranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link BankBranch} entities in the database.
 * The main input is a {@link BankBranchCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BankBranchDTO} or a {@link Page} of {@link BankBranchDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BankBranchQueryService extends QueryService<BankBranch> {

    private final Logger log = LoggerFactory.getLogger(BankBranchQueryService.class);

    private final BankBranchRepository bankBranchRepository;

    private final BankBranchMapper bankBranchMapper;

    public BankBranchQueryService(BankBranchRepository bankBranchRepository, BankBranchMapper bankBranchMapper) {
        this.bankBranchRepository = bankBranchRepository;
        this.bankBranchMapper = bankBranchMapper;
    }

    /**
     * Return a {@link List} of {@link BankBranchDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BankBranchDTO> findByCriteria(BankBranchCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BankBranch> specification = createSpecification(criteria);
        return bankBranchMapper.toDto(bankBranchRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BankBranchDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BankBranchDTO> findByCriteria(BankBranchCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BankBranch> specification = createSpecification(criteria);
        return bankBranchRepository.findAll(specification, page).map(bankBranchMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BankBranchCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BankBranch> specification = createSpecification(criteria);
        return bankBranchRepository.count(specification);
    }

    /**
     * Function to convert {@link BankBranchCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BankBranch> createSpecification(BankBranchCriteria criteria) {
        Specification<BankBranch> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BankBranch_.id));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), BankBranch_.nameAr));
            }
            if (criteria.getNamEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNamEn(), BankBranch_.namEn));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), BankBranch_.address));
            }
            if (criteria.getBankId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getBankId(), root -> root.join(BankBranch_.bank, JoinType.LEFT).get(Bank_.id))
                    );
            }
        }
        return specification;
    }
}
