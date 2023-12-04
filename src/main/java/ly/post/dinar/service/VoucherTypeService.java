package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.VoucherType;
import ly.post.dinar.repository.VoucherTypeRepository;
import ly.post.dinar.service.dto.VoucherTypeDTO;
import ly.post.dinar.service.mapper.VoucherTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public VoucherTypeDTO save(VoucherTypeDTO voucherTypeDTO) {
        log.debug("Request to save VoucherType : {}", voucherTypeDTO);
        VoucherType voucherType = voucherTypeMapper.toEntity(voucherTypeDTO);
        voucherType = voucherTypeRepository.save(voucherType);
        return voucherTypeMapper.toDto(voucherType);
    }

    /**
     * Update a voucherType.
     *
     * @param voucherTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public VoucherTypeDTO update(VoucherTypeDTO voucherTypeDTO) {
        log.debug("Request to update VoucherType : {}", voucherTypeDTO);
        VoucherType voucherType = voucherTypeMapper.toEntity(voucherTypeDTO);
        voucherType = voucherTypeRepository.save(voucherType);
        return voucherTypeMapper.toDto(voucherType);
    }

    /**
     * Partially update a voucherType.
     *
     * @param voucherTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<VoucherTypeDTO> partialUpdate(VoucherTypeDTO voucherTypeDTO) {
        log.debug("Request to partially update VoucherType : {}", voucherTypeDTO);

        return voucherTypeRepository
            .findById(voucherTypeDTO.getId())
            .map(existingVoucherType -> {
                voucherTypeMapper.partialUpdate(existingVoucherType, voucherTypeDTO);

                return existingVoucherType;
            })
            .map(voucherTypeRepository::save)
            .map(voucherTypeMapper::toDto);
    }

    /**
     * Get all the voucherTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VoucherTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VoucherTypes");
        return voucherTypeRepository.findAll(pageable).map(voucherTypeMapper::toDto);
    }

    /**
     * Get all the voucherTypes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<VoucherTypeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return voucherTypeRepository.findAllWithEagerRelationships(pageable).map(voucherTypeMapper::toDto);
    }

    /**
     * Get one voucherType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VoucherTypeDTO> findOne(Long id) {
        log.debug("Request to get VoucherType : {}", id);
        return voucherTypeRepository.findOneWithEagerRelationships(id).map(voucherTypeMapper::toDto);
    }

    /**
     * Delete the voucherType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete VoucherType : {}", id);
        voucherTypeRepository.deleteById(id);
    }
}
