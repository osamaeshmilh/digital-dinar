package ly.post.dinar.service;

import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.Bank;
import ly.post.dinar.repository.BankRepository;
import ly.post.dinar.service.criteria.BankCriteria;
import ly.post.dinar.service.dto.BankDTO;
import ly.post.dinar.service.mapper.BankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Bank} entities in the database.
 * The main input is a {@link BankCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BankDTO} or a {@link Page} of {@link BankDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BankQueryService extends QueryService<Bank> {

    private final Logger log = LoggerFactory.getLogger(BankQueryService.class);

    private final BankRepository bankRepository;

    private final BankMapper bankMapper;

    public BankQueryService(BankRepository bankRepository, BankMapper bankMapper) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    /**
     * Return a {@link List} of {@link BankDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BankDTO> findByCriteria(BankCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Bank> specification = createSpecification(criteria);
        return bankMapper.toDto(bankRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BankDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BankDTO> findByCriteria(BankCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Bank> specification = createSpecification(criteria);
        return bankRepository.findAll(specification, page).map(bankMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BankCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Bank> specification = createSpecification(criteria);
        return bankRepository.count(specification);
    }

    /**
     * Function to convert {@link BankCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Bank> createSpecification(BankCriteria criteria) {
        Specification<Bank> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Bank_.id));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), Bank_.nameAr));
            }
            if (criteria.getNamEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNamEn(), Bank_.namEn));
            }
            if (criteria.getSwiftCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSwiftCode(), Bank_.swiftCode));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Bank_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Bank_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Bank_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Bank_.lastModifiedDate));
            }
        }
        return specification;
    }
}
