package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.VoucherTypeCriteria;
import ly.post.dinar.repository.VoucherTypeRepository;
import ly.post.dinar.service.dto.VoucherTypeDTO;
import ly.post.dinar.service.mapper.VoucherTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.VoucherType}.
 */
@Service
@Transactional
public class VoucherTypeService {

    private final Logger log = LoggerFactory.getLogger(VoucherTypeService.class);

    private final VoucherTypeRepository voucherTypeRepository;

    private final VoucherTypeMapper voucherTypeMapper;

    public VoucherTypeService(VoucherTypeRepository voucherTypeRepository, VoucherTypeMapper voucherTypeMapper) {
        this.voucherTypeRepository = voucherTypeRepository;
        this.voucherTypeMapper = voucherTypeMapper;
    }

    /**
     * Save a voucherType.
     *
     * @param voucherTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<VoucherTypeDTO> save(VoucherTypeDTO voucherTypeDTO) {
        log.debug("Request to save VoucherType : {}", voucherTypeDTO);
        return voucherTypeRepository.save(voucherTypeMapper.toEntity(voucherTypeDTO)).map(voucherTypeMapper::toDto);
    }

    /**
     * Update a voucherType.
     *
     * @param voucherTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<VoucherTypeDTO> update(VoucherTypeDTO voucherTypeDTO) {
        log.debug("Request to update VoucherType : {}", voucherTypeDTO);
        return voucherTypeRepository.save(voucherTypeMapper.toEntity(voucherTypeDTO)).map(voucherTypeMapper::toDto);
    }

    /**
     * Partially update a voucherType.
     *
     * @param voucherTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<VoucherTypeDTO> partialUpdate(VoucherTypeDTO voucherTypeDTO) {
        log.debug("Request to partially update VoucherType : {}", voucherTypeDTO);

        return voucherTypeRepository
            .findById(voucherTypeDTO.getId())
            .map(existingVoucherType -> {
                voucherTypeMapper.partialUpdate(existingVoucherType, voucherTypeDTO);

                return existingVoucherType;
            })
            .flatMap(voucherTypeRepository::save)
            .map(voucherTypeMapper::toDto);
    }

    /**
     * Get all the voucherTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<VoucherTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VoucherTypes");
        return voucherTypeRepository.findAllBy(pageable).map(voucherTypeMapper::toDto);
    }

    /**
     * Find voucherTypes by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<VoucherTypeDTO> findByCriteria(VoucherTypeCriteria criteria, Pageable pageable) {
        log.debug("Request to get all VoucherTypes by Criteria");
        return voucherTypeRepository.findByCriteria(criteria, pageable).map(voucherTypeMapper::toDto);
    }

    /**
     * Find the count of voucherTypes by criteria.
     * @param criteria filtering criteria
     * @return the count of voucherTypes
     */
    public Mono<Long> countByCriteria(VoucherTypeCriteria criteria) {
        log.debug("Request to get the count of all VoucherTypes by Criteria");
        return voucherTypeRepository.countByCriteria(criteria);
    }

    /**
     * Get all the voucherTypes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<VoucherTypeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return voucherTypeRepository.findAllWithEagerRelationships(pageable).map(voucherTypeMapper::toDto);
    }

    /**
     * Returns the number of voucherTypes available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return voucherTypeRepository.count();
    }

    /**
     * Get one voucherType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<VoucherTypeDTO> findOne(Long id) {
        log.debug("Request to get VoucherType : {}", id);
        return voucherTypeRepository.findOneWithEagerRelationships(id).map(voucherTypeMapper::toDto);
    }

    /**
     * Delete the voucherType by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete VoucherType : {}", id);
        return voucherTypeRepository.deleteById(id);
    }
}
