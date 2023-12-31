package ly.post.dinar.service;

import java.util.Optional;
import java.util.UUID;
import ly.post.dinar.domain.User;
import ly.post.dinar.domain.WalletUser;
import ly.post.dinar.domain.enumeration.ReferenceType;
import ly.post.dinar.domain.enumeration.WalletType;
import ly.post.dinar.repository.WalletUserRepository;
import ly.post.dinar.security.AuthoritiesConstants;
import ly.post.dinar.service.dto.WalletUserDTO;
import ly.post.dinar.service.mapper.WalletUserMapper;
import ly.post.dinar.service.utils.FileTools;
import ly.post.dinar.service.utils.WalletKeyTools;
import ly.post.dinar.web.rest.errors.BadRequestAlertException;
import ly.post.dinar.web.rest.vm.ManagedUserVM;
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

    private final UserService userService;

    private final AttachmentService attachmentService;

    public WalletUserService(
        WalletUserRepository walletUserRepository,
        WalletUserMapper walletUserMapper,
        UserService userService,
        AttachmentService attachmentService
    ) {
        this.walletUserRepository = walletUserRepository;
        this.walletUserMapper = walletUserMapper;
        this.userService = userService;
        this.attachmentService = attachmentService;
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

    public WalletUserDTO create(WalletUserDTO walletUserDTO) {
        String role = walletUserDTO.getWalletType() == WalletType.CONSUMER ? AuthoritiesConstants.CONSUMER : AuthoritiesConstants.MERCHANT;
        ManagedUserVM managedUserVM = new ManagedUserVM();
        managedUserVM.setFirstName("test");
        managedUserVM.setLastName("test2");
        // managedUserVM.setFirstName(walletUserDTO.getArabicFirstName());
        // managedUserVM.setFirstName(walletUserDTO.getArabicLastName());
        managedUserVM.setEmail(walletUserDTO.getEmail());
        managedUserVM.setLogin(walletUserDTO.getMobileNo());
        managedUserVM.setMobileNo(walletUserDTO.getMobileNo());

        User user = userService.createAndAssignUserWithPassword(managedUserVM, role, walletUserDTO.getNewPassword());

        WalletUser walletUser = walletUserMapper.toEntity(walletUserDTO);
        walletUser.setUser(user);
        walletUser.setWalletPublicKey(WalletKeyTools.generateRandomString());
        walletUser.setIsKYCVerified(false);

        //        if (walletUserDTO.getImageFile() != null) {
        //            String filePath = FileTools.upload(walletUser.getImageFile(), walletUser.getImageFileContentType(), "walletUser");
        //            walletUser.setImageFile(null);
        //            walletUser.setImageFileContentType(walletUserDTO.getImageFileContentType());
        //            walletUser.setImageUrlFile(filePath);
        //        }

        walletUser = walletUserRepository.save(walletUser);

        WalletUser finalWalletUser = walletUser;
        walletUserDTO
            .getAttachments()
            .forEach(attachmentDTO -> {
                attachmentDTO.setReferenceId(finalWalletUser.getId());
                attachmentDTO.setReferenceType(ReferenceType.WALLET_USER);
                attachmentService.save(attachmentDTO);
            });

        return walletUserMapper.toDto(walletUser);
    }

    public WalletUser findOneByUser() {
        return userService
            .getUserWithAuthorities()
            .map(walletUserRepository::findByUser)
            .orElseThrow(() -> new BadRequestAlertException("WalletUser User Not Found!", "", "CUSTOMER_NOT_FOUND"));
    }

    public WalletUserDTO findOneDTOByUser() {
        return userService
            .getUserWithAuthorities()
            .map(walletUserRepository::findByUser)
            .map(walletUserMapper::toDto)
            .orElseThrow(() -> new BadRequestAlertException("WalletUser User Not Found!", "", "CUSTOMER_NOT_FOUND"));
    }

    public User findOneUser() {
        return userService
            .getUserWithAuthorities()
            .orElseThrow(() -> new BadRequestAlertException("WalletUser User Not Found!", "", "USER_NOT_FOUND"));
    }

    //    public Optional<WalletUserDTO> findOneByWalletPublicKey(String walletPublicKey) {
    //        return walletUserRepository.findTopByWalletPublicKey(walletPublicKey).map(walletUserMapper::toDto);
    //    }

    public Optional<WalletUserDTO> findOneByMobileNo(String mobileNo) {
        return walletUserRepository.findFirstByMobileNoContaining(mobileNo).map(walletUserMapper::toDto);
    }

    public Optional<WalletUserDTO> findOneByEmail(String email) {
        return walletUserRepository.findFirstByEmail(email).map(walletUserMapper::toDto);
    }

    public void verifyUser(User user) {
        WalletUserDTO walletUserDTO = walletUserMapper.toDto(walletUserRepository.findByUser(user));
        walletUserDTO.setIsKYCVerified(true);
        save(walletUserDTO);
    }

    public Optional<WalletUserDTO> findOneByWalletPublicKey(String walletPublicKey) {
        return walletUserRepository.findTopByWalletPublicKey(walletPublicKey).map(walletUserMapper::toDto);
    }
}
