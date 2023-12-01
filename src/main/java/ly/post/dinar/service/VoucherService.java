package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.VoucherCriteria;
import ly.post.dinar.repository.VoucherRepository;
import ly.post.dinar.service.dto.VoucherDTO;
import ly.post.dinar.service.mapper.VoucherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Voucher}.
 */
@Service
@Transactional
public class VoucherService {

    private final Logger log = LoggerFactory.getLogger(VoucherService.class);

    private final VoucherRepository voucherRepository;

    private final VoucherMapper voucherMapper;

    public VoucherService(VoucherRepository voucherRepository, VoucherMapper voucherMapper) {
        this.voucherRepository = voucherRepository;
        this.voucherMapper = voucherMapper;
    }

    /**
     * Save a voucher.
     *
     * @param voucherDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<VoucherDTO> save(VoucherDTO voucherDTO) {
        log.debug("Request to save Voucher : {}", voucherDTO);
        return voucherRepository.save(voucherMapper.toEntity(voucherDTO)).map(voucherMapper::toDto);
    }

    /**
     * Update a voucher.
     *
     * @param voucherDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<VoucherDTO> update(VoucherDTO voucherDTO) {
        log.debug("Request to update Voucher : {}", voucherDTO);
        return voucherRepository.save(voucherMapper.toEntity(voucherDTO)).map(voucherMapper::toDto);
    }

    /**
     * Partially update a voucher.
     *
     * @param voucherDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<VoucherDTO> partialUpdate(VoucherDTO voucherDTO) {
        log.debug("Request to partially update Voucher : {}", voucherDTO);

        return voucherRepository
            .findById(voucherDTO.getId())
            .map(existingVoucher -> {
                voucherMapper.partialUpdate(existingVoucher, voucherDTO);

                return existingVoucher;
            })
            .flatMap(voucherRepository::save)
            .map(voucherMapper::toDto);
    }

    /**
     * Get all the vouchers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<VoucherDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vouchers");
        return voucherRepository.findAllBy(pageable).map(voucherMapper::toDto);
    }

    /**
     * Find vouchers by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<VoucherDTO> findByCriteria(VoucherCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Vouchers by Criteria");
        return voucherRepository.findByCriteria(criteria, pageable).map(voucherMapper::toDto);
    }

    /**
     * Find the count of vouchers by criteria.
     * @param criteria filtering criteria
     * @return the count of vouchers
     */
    public Mono<Long> countByCriteria(VoucherCriteria criteria) {
        log.debug("Request to get the count of all Vouchers by Criteria");
        return voucherRepository.countByCriteria(criteria);
    }

    /**
     * Get all the vouchers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<VoucherDTO> findAllWithEagerRelationships(Pageable pageable) {
        return voucherRepository.findAllWithEagerRelationships(pageable).map(voucherMapper::toDto);
    }

    /**
     * Returns the number of vouchers available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return voucherRepository.count();
    }

    /**
     * Get one voucher by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<VoucherDTO> findOne(Long id) {
        log.debug("Request to get Voucher : {}", id);
        return voucherRepository.findOneWithEagerRelationships(id).map(voucherMapper::toDto);
    }

    /**
     * Delete the voucher by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Voucher : {}", id);
        return voucherRepository.deleteById(id);
    }
}
