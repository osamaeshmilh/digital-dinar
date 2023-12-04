package ly.post.dinar.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.VoucherType;
import ly.post.dinar.repository.VoucherTypeRepository;
import ly.post.dinar.service.criteria.VoucherTypeCriteria;
import ly.post.dinar.service.dto.VoucherTypeDTO;
import ly.post.dinar.service.mapper.VoucherTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link VoucherType} entities in the database.
 * The main input is a {@link VoucherTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VoucherTypeDTO} or a {@link Page} of {@link VoucherTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VoucherTypeQueryService extends QueryService<VoucherType> {

    private final Logger log = LoggerFactory.getLogger(VoucherTypeQueryService.class);

    private final VoucherTypeRepository voucherTypeRepository;

    private final VoucherTypeMapper voucherTypeMapper;

    public VoucherTypeQueryService(VoucherTypeRepository voucherTypeRepository, VoucherTypeMapper voucherTypeMapper) {
        this.voucherTypeRepository = voucherTypeRepository;
        this.voucherTypeMapper = voucherTypeMapper;
    }

    /**
     * Return a {@link List} of {@link VoucherTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VoucherTypeDTO> findByCriteria(VoucherTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<VoucherType> specification = createSpecification(criteria);
        return voucherTypeMapper.toDto(voucherTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VoucherTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VoucherTypeDTO> findByCriteria(VoucherTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<VoucherType> specification = createSpecification(criteria);
        return voucherTypeRepository.findAll(specification, page).map(voucherTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VoucherTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<VoucherType> specification = createSpecification(criteria);
        return voucherTypeRepository.count(specification);
    }

    /**
     * Function to convert {@link VoucherTypeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<VoucherType> createSpecification(VoucherTypeCriteria criteria) {
        Specification<VoucherType> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), VoucherType_.id));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), VoucherType_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), VoucherType_.nameEn));
            }
            if (criteria.getSlug() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSlug(), VoucherType_.slug));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), VoucherType_.amount));
            }
            if (criteria.getMenuOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMenuOrder(), VoucherType_.menuOrder));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), VoucherType_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), VoucherType_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), VoucherType_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), VoucherType_.lastModifiedDate));
            }
            if (criteria.getVoucherCompanyId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getVoucherCompanyId(),
                            root -> root.join(VoucherType_.voucherCompany, JoinType.LEFT).get(VoucherCompany_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
