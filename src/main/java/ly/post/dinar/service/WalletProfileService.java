package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.WalletProfile;
import ly.post.dinar.repository.WalletProfileRepository;
import ly.post.dinar.service.dto.WalletProfileDTO;
import ly.post.dinar.service.mapper.WalletProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.WalletProfile}.
 */
@Service
@Transactional
public class WalletProfileService {

    private final Logger log = LoggerFactory.getLogger(WalletProfileService.class);

    private final WalletProfileRepository walletProfileRepository;

    private final WalletProfileMapper walletProfileMapper;

    public WalletProfileService(WalletProfileRepository walletProfileRepository, WalletProfileMapper walletProfileMapper) {
        this.walletProfileRepository = walletProfileRepository;
        this.walletProfileMapper = walletProfileMapper;
    }

    /**
     * Save a walletProfile.
     *
     * @param walletProfileDTO the entity to save.
     * @return the persisted entity.
     */
    public WalletProfileDTO save(WalletProfileDTO walletProfileDTO) {
        log.debug("Request to save WalletProfile : {}", walletProfileDTO);
        WalletProfile walletProfile = walletProfileMapper.toEntity(walletProfileDTO);
        walletProfile = walletProfileRepository.save(walletProfile);
        return walletProfileMapper.toDto(walletProfile);
    }

    /**
     * Update a walletProfile.
     *
     * @param walletProfileDTO the entity to save.
     * @return the persisted entity.
     */
    public WalletProfileDTO update(WalletProfileDTO walletProfileDTO) {
        log.debug("Request to update WalletProfile : {}", walletProfileDTO);
        WalletProfile walletProfile = walletProfileMapper.toEntity(walletProfileDTO);
        walletProfile = walletProfileRepository.save(walletProfile);
        return walletProfileMapper.toDto(walletProfile);
    }

    /**
     * Partially update a walletProfile.
     *
     * @param walletProfileDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WalletProfileDTO> partialUpdate(WalletProfileDTO walletProfileDTO) {
        log.debug("Request to partially update WalletProfile : {}", walletProfileDTO);

        return walletProfileRepository
            .findById(walletProfileDTO.getId())
            .map(existingWalletProfile -> {
                walletProfileMapper.partialUpdate(existingWalletProfile, walletProfileDTO);

                return existingWalletProfile;
            })
            .map(walletProfileRepository::save)
            .map(walletProfileMapper::toDto);
    }

    /**
     * Get all the walletProfiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WalletProfileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WalletProfiles");
        return walletProfileRepository.findAll(pageable).map(walletProfileMapper::toDto);
    }

    /**
     * Get one walletProfile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WalletProfileDTO> findOne(Long id) {
        log.debug("Request to get WalletProfile : {}", id);
        return walletProfileRepository.findById(id).map(walletProfileMapper::toDto);
    }

    /**
     * Delete the walletProfile by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WalletProfile : {}", id);
        walletProfileRepository.deleteById(id);
    }
}
