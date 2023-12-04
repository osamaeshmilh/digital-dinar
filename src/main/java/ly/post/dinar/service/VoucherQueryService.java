package ly.post.dinar.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.post.dinar.domain.*; // for static metamodels
import ly.post.dinar.domain.Voucher;
import ly.post.dinar.repository.VoucherRepository;
import ly.post.dinar.service.criteria.VoucherCriteria;
import ly.post.dinar.service.dto.VoucherDTO;
import ly.post.dinar.service.mapper.VoucherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Voucher} entities in the database.
 * The main input is a {@link VoucherCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VoucherDTO} or a {@link Page} of {@link VoucherDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VoucherQueryService extends QueryService<Voucher> {

    private final Logger log = LoggerFactory.getLogger(VoucherQueryService.class);

    private final VoucherRepository voucherRepository;

    private final VoucherMapper voucherMapper;

    public VoucherQueryService(VoucherRepository voucherRepository, VoucherMapper voucherMapper) {
        this.voucherRepository = voucherRepository;
        this.voucherMapper = voucherMapper;
    }

    /**
     * Return a {@link List} of {@link VoucherDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VoucherDTO> findByCriteria(VoucherCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Voucher> specification = createSpecification(criteria);
        return voucherMapper.toDto(voucherRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VoucherDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VoucherDTO> findByCriteria(VoucherCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Voucher> specification = createSpecification(criteria);
        return voucherRepository.findAll(specification, page).map(voucherMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VoucherCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Voucher> specification = createSpecification(criteria);
        return voucherRepository.count(specification);
    }

    /**
     * Function to convert {@link VoucherCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Voucher> createSpecification(VoucherCriteria criteria) {
        Specification<Voucher> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Voucher_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Voucher_.code));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), Voucher_.amount));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), Voucher_.serialNumber));
            }
            if (criteria.getIsSold() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSold(), Voucher_.isSold));
            }
            if (criteria.getSellTransactionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSellTransactionId(), Voucher_.sellTransactionId));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Voucher_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Voucher_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Voucher_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Voucher_.lastModifiedDate));
            }
            if (criteria.getVoucherTypeId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getVoucherTypeId(),
                            root -> root.join(Voucher_.voucherType, JoinType.LEFT).get(VoucherType_.id)
                        )
                    );
            }
            if (criteria.getVoucherCompanyId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getVoucherCompanyId(),
                            root -> root.join(Voucher_.voucherCompany, JoinType.LEFT).get(VoucherCompany_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
