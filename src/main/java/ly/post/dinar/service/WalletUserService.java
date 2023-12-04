package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.WalletUser;
import ly.post.dinar.repository.WalletUserRepository;
import ly.post.dinar.service.dto.WalletUserDTO;
import ly.post.dinar.service.mapper.WalletUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.WalletUser}.
 */
@Service
@Transactional
public class WalletUserService {

    private final Logger log = LoggerFactory.getLogger(WalletUserService.class);

    private final WalletUserRepository walletUserRepository;

    private final WalletUserMapper walletUserMapper;

    public WalletUserService(WalletUserRepository walletUserRepository, WalletUserMapper walletUserMapper) {
        this.walletUserRepository = walletUserRepository;
        this.walletUserMapper = walletUserMapper;
    }

    /**
     * Save a walletUser.
     *
     * @param walletUserDTO the entity to save.
     * @return the persisted entity.
     */
    public WalletUserDTO save(WalletUserDTO walletUserDTO) {
        log.debug("Request to save WalletUser : {}", walletUserDTO);
        WalletUser walletUser = walletUserMapper.toEntity(walletUserDTO);
        walletUser = walletUserRepository.save(walletUser);
        return walletUserMapper.toDto(walletUser);
    }

    /**
     * Update a walletUser.
     *
     * @param walletUserDTO the entity to save.
     * @return the persisted entity.
     */
    public WalletUserDTO update(WalletUserDTO walletUserDTO) {
        log.debug("Request to update WalletUser : {}", walletUserDTO);
        WalletUser walletUser = walletUserMapper.toEntity(walletUserDTO);
        walletUser = walletUserRepository.save(walletUser);
        return walletUserMapper.toDto(walletUser);
    }

    /**
     * Partially update a walletUser.
     *
     * @param walletUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WalletUserDTO> partialUpdate(WalletUserDTO walletUserDTO) {
        log.debug("Request to partially update WalletUser : {}", walletUserDTO);

        return walletUserRepository
            .findById(walletUserDTO.getId())
            .map(existingWalletUser -> {
                walletUserMapper.partialUpdate(existingWalletUser, walletUserDTO);

                return existingWalletUser;
            })
            .map(walletUserRepository::save)
            .map(walletUserMapper::toDto);
    }

    /**
     * Get all the walletUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WalletUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WalletUsers");
        return walletUserRepository.findAll(pageable).map(walletUserMapper::toDto);
    }

    /**
     * Get all the walletUsers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<WalletUserDTO> findAllWithEagerRelationships(Pageable pageable) {
        return walletUserRepository.findAllWithEagerRelationships(pageable).map(walletUserMapper::toDto);
    }

    /**
     * Get one walletUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WalletUserDTO> findOne(Long id) {
        log.debug("Request to get WalletUser : {}", id);
        return walletUserRepository.findOneWithEagerRelationships(id).map(walletUserMapper::toDto);
    }

    /**
     * Delete the walletUser by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WalletUser : {}", id);
        walletUserRepository.deleteById(id);
    }
}
