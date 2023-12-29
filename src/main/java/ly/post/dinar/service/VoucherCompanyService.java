package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.VoucherCompany;
import ly.post.dinar.repository.VoucherCompanyRepository;
import ly.post.dinar.service.dto.VoucherCompanyDTO;
import ly.post.dinar.service.mapper.VoucherCompanyMapper;
import ly.post.dinar.service.utils.FileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public VoucherCompanyDTO save(VoucherCompanyDTO voucherCompanyDTO) {
        log.debug("Request to save VoucherCompany : {}", voucherCompanyDTO);
        VoucherCompany voucherCompany = voucherCompanyMapper.toEntity(voucherCompanyDTO);

        if (voucherCompanyDTO.getImageFile() != null) {
            String filePath = FileTools.upload(
                voucherCompany.getImageFile(),
                voucherCompany.getImageFileContentType(),
                "voucherCompany" + "_"
            );
            voucherCompany.setImageFile(null);
            voucherCompany.setImageFileContentType(voucherCompanyDTO.getImageFileContentType());
            voucherCompany.setImageUrlFile(filePath);
        }

        voucherCompany = voucherCompanyRepository.save(voucherCompany);
        return voucherCompanyMapper.toDto(voucherCompany);
    }

    /**
     * Update a voucherCompany.
     *
     * @param voucherCompanyDTO the entity to save.
     * @return the persisted entity.
     */
    public VoucherCompanyDTO update(VoucherCompanyDTO voucherCompanyDTO) {
        log.debug("Request to update VoucherCompany : {}", voucherCompanyDTO);
        VoucherCompany voucherCompany = voucherCompanyMapper.toEntity(voucherCompanyDTO);
        voucherCompany = voucherCompanyRepository.save(voucherCompany);
        return voucherCompanyMapper.toDto(voucherCompany);
    }

    /**
     * Partially update a voucherCompany.
     *
     * @param voucherCompanyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<VoucherCompanyDTO> partialUpdate(VoucherCompanyDTO voucherCompanyDTO) {
        log.debug("Request to partially update VoucherCompany : {}", voucherCompanyDTO);

        return voucherCompanyRepository
            .findById(voucherCompanyDTO.getId())
            .map(existingVoucherCompany -> {
                voucherCompanyMapper.partialUpdate(existingVoucherCompany, voucherCompanyDTO);

                return existingVoucherCompany;
            })
            .map(voucherCompanyRepository::save)
            .map(voucherCompanyMapper::toDto);
    }

    /**
     * Get all the voucherCompanies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VoucherCompanyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VoucherCompanies");
        return voucherCompanyRepository.findAll(pageable).map(voucherCompanyMapper::toDto);
    }

    /**
     * Get one voucherCompany by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VoucherCompanyDTO> findOne(Long id) {
        log.debug("Request to get VoucherCompany : {}", id);
        return voucherCompanyRepository.findById(id).map(voucherCompanyMapper::toDto);
    }

    /**
     * Delete the voucherCompany by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete VoucherCompany : {}", id);
        voucherCompanyRepository.deleteById(id);
    }
}
