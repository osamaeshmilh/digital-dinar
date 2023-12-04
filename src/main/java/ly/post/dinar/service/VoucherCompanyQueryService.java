package ly.post.dinar.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.VoucherCompany;
import ly.post.dinar.repository.VoucherCompanyRepository;
import ly.post.dinar.service.criteria.VoucherCompanyCriteria;
import ly.post.dinar.service.dto.VoucherCompanyDTO;
import ly.post.dinar.service.mapper.VoucherCompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link VoucherCompany} entities in the database.
 * The main input is a {@link VoucherCompanyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VoucherCompanyDTO} or a {@link Page} of {@link VoucherCompanyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VoucherCompanyQueryService extends QueryService<VoucherCompany> {

    private final Logger log = LoggerFactory.getLogger(VoucherCompanyQueryService.class);

    private final VoucherCompanyRepository voucherCompanyRepository;

    private final VoucherCompanyMapper voucherCompanyMapper;

    public VoucherCompanyQueryService(VoucherCompanyRepository voucherCompanyRepository, VoucherCompanyMapper voucherCompanyMapper) {
        this.voucherCompanyRepository = voucherCompanyRepository;
        this.voucherCompanyMapper = voucherCompanyMapper;
    }

    /**
     * Return a {@link List} of {@link VoucherCompanyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VoucherCompanyDTO> findByCriteria(VoucherCompanyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<VoucherCompany> specification = createSpecification(criteria);
        return voucherCompanyMapper.toDto(voucherCompanyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VoucherCompanyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VoucherCompanyDTO> findByCriteria(VoucherCompanyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<VoucherCompany> specification = createSpecification(criteria);
        return voucherCompanyRepository.findAll(specification, page).map(voucherCompanyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VoucherCompanyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<VoucherCompany> specification = createSpecification(criteria);
        return voucherCompanyRepository.count(specification);
    }

    /**
     * Function to convert {@link VoucherCompanyCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<VoucherCompany> createSpecification(VoucherCompanyCriteria criteria) {
        Specification<VoucherCompany> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), VoucherCompany_.id));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), VoucherCompany_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), VoucherCompany_.nameEn));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileNo(), VoucherCompany_.mobileNo));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), VoucherCompany_.email));
            }
            if (criteria.getImageUrlFile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrlFile(), VoucherCompany_.imageUrlFile));
            }
            if (criteria.getPostCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPostCode(), VoucherCompany_.postCode));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), VoucherCompany_.address));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), VoucherCompany_.notes));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), VoucherCompany_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), VoucherCompany_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), VoucherCompany_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), VoucherCompany_.lastModifiedDate));
            }
            if (criteria.getUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUserId(), root -> root.join(VoucherCompany_.user, JoinType.LEFT).get(User_.id))
                    );
            }
        }
        return specification;
    }
}
