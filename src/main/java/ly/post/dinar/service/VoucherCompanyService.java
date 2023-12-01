package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.VoucherCompanyCriteria;
import ly.post.dinar.repository.VoucherCompanyRepository;
import ly.post.dinar.service.dto.VoucherCompanyDTO;
import ly.post.dinar.service.mapper.VoucherCompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.VoucherCompany}.
 */
@Service
@Transactional
public class VoucherCompanyService {

    private final Logger log = LoggerFactory.getLogger(VoucherCompanyService.class);

    private final VoucherCompanyRepository voucherCompanyRepository;

    private final VoucherCompanyMapper voucherCompanyMapper;

    public VoucherCompanyService(VoucherCompanyRepository voucherCompanyRepository, VoucherCompanyMapper voucherCompanyMapper) {
        this.voucherCompanyRepository = voucherCompanyRepository;
        this.voucherCompanyMapper = voucherCompanyMapper;
    }

    /**
     * Save a voucherCompany.
     *
     * @param voucherCompanyDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<VoucherCompanyDTO> save(VoucherCompanyDTO voucherCompanyDTO) {
        log.debug("Request to save VoucherCompany : {}", voucherCompanyDTO);
        return voucherCompanyRepository.save(voucherCompanyMapper.toEntity(voucherCompanyDTO)).map(voucherCompanyMapper::toDto);
    }

    /**
     * Update a voucherCompany.
     *
     * @param voucherCompanyDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<VoucherCompanyDTO> update(VoucherCompanyDTO voucherCompanyDTO) {
        log.debug("Request to update VoucherCompany : {}", voucherCompanyDTO);
        return voucherCompanyRepository.save(voucherCompanyMapper.toEntity(voucherCompanyDTO)).map(voucherCompanyMapper::toDto);
    }

    /**
     * Partially update a voucherCompany.
     *
     * @param voucherCompanyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<VoucherCompanyDTO> partialUpdate(VoucherCompanyDTO voucherCompanyDTO) {
        log.debug("Request to partially update VoucherCompany : {}", voucherCompanyDTO);

        return voucherCompanyRepository
            .findById(voucherCompanyDTO.getId())
            .map(existingVoucherCompany -> {
                voucherCompanyMapper.partialUpdate(existingVoucherCompany, voucherCompanyDTO);

                return existingVoucherCompany;
            })
            .flatMap(voucherCompanyRepository::save)
            .map(voucherCompanyMapper::toDto);
    }

    /**
     * Get all the voucherCompanies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<VoucherCompanyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VoucherCompanies");
        return voucherCompanyRepository.findAllBy(pageable).map(voucherCompanyMapper::toDto);
    }

    /**
     * Find voucherCompanies by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<VoucherCompanyDTO> findByCriteria(VoucherCompanyCriteria criteria, Pageable pageable) {
        log.debug("Request to get all VoucherCompanies by Criteria");
        return voucherCompanyRepository.findByCriteria(criteria, pageable).map(voucherCompanyMapper::toDto);
    }

    /**
     * Find the count of voucherCompanies by criteria.
     * @param criteria filtering criteria
     * @return the count of voucherCompanies
     */
    public Mono<Long> countByCriteria(VoucherCompanyCriteria criteria) {
        log.debug("Request to get the count of all VoucherCompanies by Criteria");
        return voucherCompanyRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of voucherCompanies available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return voucherCompanyRepository.count();
    }

    /**
     * Get one voucherCompany by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<VoucherCompanyDTO> findOne(Long id) {
        log.debug("Request to get VoucherCompany : {}", id);
        return voucherCompanyRepository.findById(id).map(voucherCompanyMapper::toDto);
    }

    /**
     * Delete the voucherCompany by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete VoucherCompany : {}", id);
        return voucherCompanyRepository.deleteById(id);
    }
}
