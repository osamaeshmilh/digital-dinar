package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.Voucher;
import ly.post.dinar.repository.VoucherRepository;
import ly.post.dinar.service.dto.VoucherDTO;
import ly.post.dinar.service.mapper.VoucherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public VoucherDTO save(VoucherDTO voucherDTO) {
        log.debug("Request to save Voucher : {}", voucherDTO);
        Voucher voucher = voucherMapper.toEntity(voucherDTO);
        voucher = voucherRepository.save(voucher);
        return voucherMapper.toDto(voucher);
    }

    /**
     * Update a voucher.
     *
     * @param voucherDTO the entity to save.
     * @return the persisted entity.
     */
    public VoucherDTO update(VoucherDTO voucherDTO) {
        log.debug("Request to update Voucher : {}", voucherDTO);
        Voucher voucher = voucherMapper.toEntity(voucherDTO);
        voucher = voucherRepository.save(voucher);
        return voucherMapper.toDto(voucher);
    }

    /**
     * Partially update a voucher.
     *
     * @param voucherDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<VoucherDTO> partialUpdate(VoucherDTO voucherDTO) {
        log.debug("Request to partially update Voucher : {}", voucherDTO);

        return voucherRepository
            .findById(voucherDTO.getId())
            .map(existingVoucher -> {
                voucherMapper.partialUpdate(existingVoucher, voucherDTO);

                return existingVoucher;
            })
            .map(voucherRepository::save)
            .map(voucherMapper::toDto);
    }

    /**
     * Get all the vouchers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VoucherDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vouchers");
        return voucherRepository.findAll(pageable).map(voucherMapper::toDto);
    }

    /**
     * Get all the vouchers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<VoucherDTO> findAllWithEagerRelationships(Pageable pageable) {
        return voucherRepository.findAllWithEagerRelationships(pageable).map(voucherMapper::toDto);
    }

    /**
     * Get one voucher by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VoucherDTO> findOne(Long id) {
        log.debug("Request to get Voucher : {}", id);
        return voucherRepository.findOneWithEagerRelationships(id).map(voucherMapper::toDto);
    }

    /**
     * Delete the voucher by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Voucher : {}", id);
        voucherRepository.deleteById(id);
    }
}
