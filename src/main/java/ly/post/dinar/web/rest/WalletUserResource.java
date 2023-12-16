package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.post.dinar.repository.WalletUserRepository;
import ly.post.dinar.security.AuthoritiesConstants;
import ly.post.dinar.security.SecurityUtils;
import ly.post.dinar.service.ActivationService;
import ly.post.dinar.service.WalletUserQueryService;
import ly.post.dinar.service.WalletUserService;
import ly.post.dinar.service.criteria.WalletUserCriteria;
import ly.post.dinar.service.dto.WalletUserDTO;
import ly.post.dinar.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.post.dinar.domain.WalletUser}.
 */
@RestController
@RequestMapping("/api")
public class WalletUserResource {

    private final Logger log = LoggerFactory.getLogger(WalletUserResource.class);

    private static final String ENTITY_NAME = "walletUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WalletUserService walletUserService;

    private final WalletUserRepository walletUserRepository;

    private final WalletUserQueryService walletUserQueryService;

    private final ActivationService activationService;

    public WalletUserResource(
        WalletUserService walletUserService,
        WalletUserRepository walletUserRepository,
        WalletUserQueryService walletUserQueryService,
        ActivationService activationService
    ) {
        this.walletUserService = walletUserService;
        this.walletUserRepository = walletUserRepository;
        this.walletUserQueryService = walletUserQueryService;
        this.activationService = activationService;
    }

    /**
     * {@code POST  /wallet-users} : Create a new walletUser.
     *
     * @param walletUserDTO the walletUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new walletUserDTO, or with status {@code 400 (Bad Request)} if the walletUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wallet-users")
    public ResponseEntity<WalletUserDTO> createWalletUser(@RequestBody WalletUserDTO walletUserDTO) throws URISyntaxException {
        log.debug("REST request to save WalletUser : {}", walletUserDTO);
        if (walletUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new walletUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WalletUserDTO result = walletUserService.save(walletUserDTO);
        return ResponseEntity
            .created(new URI("/api/wallet-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wallet-users/:id} : Updates an existing walletUser.
     *
     * @param id the id of the walletUserDTO to save.
     * @param walletUserDTO the walletUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated walletUserDTO,
     * or with status {@code 400 (Bad Request)} if the walletUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the walletUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wallet-users/{id}")
    public ResponseEntity<WalletUserDTO> updateWalletUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WalletUserDTO walletUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WalletUser : {}, {}", id, walletUserDTO);
        if (walletUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, walletUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!walletUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WalletUserDTO result = walletUserService.update(walletUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /wallet-users/:id} : Partial updates given fields of an existing walletUser, field will ignore if it is null
     *
     * @param id the id of the walletUserDTO to save.
     * @param walletUserDTO the walletUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated walletUserDTO,
     * or with status {@code 400 (Bad Request)} if the walletUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the walletUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the walletUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/wallet-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WalletUserDTO> partialUpdateWalletUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WalletUserDTO walletUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WalletUser partially : {}, {}", id, walletUserDTO);
        if (walletUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, walletUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!walletUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WalletUserDTO> result = walletUserService.partialUpdate(walletUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /wallet-users} : get all the walletUsers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of walletUsers in body.
     */
    @GetMapping("/wallet-users")
    public ResponseEntity<List<WalletUserDTO>> getAllWalletUsers(
        WalletUserCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get WalletUsers by criteria: {}", criteria);

        Page<WalletUserDTO> page = walletUserQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wallet-users/count} : count all the walletUsers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/wallet-users/count")
    public ResponseEntity<Long> countWalletUsers(WalletUserCriteria criteria) {
        log.debug("REST request to count WalletUsers by criteria: {}", criteria);
        return ResponseEntity.ok().body(walletUserQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /wallet-users/:id} : get the "id" walletUser.
     *
     * @param id the id of the walletUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the walletUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wallet-users/{id}")
    public ResponseEntity<WalletUserDTO> getWalletUser(@PathVariable Long id) {
        log.debug("REST request to get WalletUser : {}", id);
        Optional<WalletUserDTO> walletUserDTO = walletUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(walletUserDTO);
    }

    /**
     * {@code DELETE  /wallet-users/:id} : delete the "id" walletUser.
     *
     * @param id the id of the walletUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wallet-users/{id}")
    public ResponseEntity<Void> deleteWalletUser(@PathVariable Long id) {
        log.debug("REST request to delete WalletUser : {}", id);
        walletUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/public/wallet-users/register")
    public ResponseEntity<WalletUserDTO> registerWalletUser(@RequestBody WalletUserDTO walletUserDTO) throws URISyntaxException {
        log.debug("REST request to register WalletUserDTO : {}", walletUserDTO);
        if (walletUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new wallet user cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (walletUserService.findOneByMobileNo(walletUserDTO.getMobileNo()).isPresent()) {
            throw new BadRequestAlertException("Mobile Number Already Used!", ENTITY_NAME, "MOBILE_USED");
        }
        if (walletUserService.findOneByEmail(walletUserDTO.getEmail()).isPresent()) {
            throw new BadRequestAlertException("Email Already Used!", ENTITY_NAME, "EMAIL_USED");
        }

        if (!walletUserDTO.getMobileNo().startsWith("+218")) {
            walletUserDTO.setVerifiedByEmailOtp(true);
            walletUserDTO.setVerifiedByMobileOtp(false);
        }

        //        if (walletUserDTO.getVerifiedByEmailOtp()) {
        //            activationService.checkCodeWithEmail(walletUserDTO.getEmail(), walletUserDTO.getOtp());
        //        } else if (walletUserDTO.getVerifiedByMobileOtp()) {
        //            activationService.checkCodeWithMobileNo(walletUserDTO.getMobileNo(), walletUserDTO.getOtp());
        //        } else {
        //            throw new BadRequestAlertException("No Verification!", ENTITY_NAME, "NO_VERIFICATION");
        //        }

        WalletUserDTO result = walletUserService.create(walletUserDTO);
        return ResponseEntity
            .created(new URI("/api/customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    //@Secured(AuthoritiesConstants.CUSTOMER)
    @GetMapping("/wallet-users/request-delete")
    public ResponseEntity<WalletUserDTO> requestAccountDelete() {
        WalletUserDTO walletUserDTO = walletUserService.findOneDTOByUser();
        if (walletUserDTO.getNotes().contains("DELETE")) throw new BadRequestAlertException(
            "Account delete already requested!",
            ENTITY_NAME,
            "ACCOUNT_DELETE_REQUESTED"
        );

        walletUserDTO.setNotes("DELETE");

        WalletUserDTO result = walletUserService.save(walletUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, walletUserDTO.getId().toString()))
            .body(result);
    }

    //@Secured(AuthoritiesConstants.CUSTOMER)
    @GetMapping("/wallet-users/get-profile")
    public ResponseEntity<WalletUserDTO> getCustomerProfile() throws URISyntaxException {
        Optional<WalletUserDTO> walletUserDTO = walletUserService.findOne(walletUserService.findOneByUser().getId());
        return ResponseUtil.wrapOrNotFound(walletUserDTO);
    }

    @PutMapping("/wallet-users/update-profile")
    public ResponseEntity<WalletUserDTO> updateCustomerProfile(@RequestBody WalletUserDTO walletUserDTO) throws URISyntaxException {
        log.debug("REST request to update wallet user : {}", walletUserDTO);
        if (
            SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CONSUMER) ||
            SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.MERCHANT)
        ) {
            walletUserDTO.setId(walletUserService.findOneByUser().getId());
        } else {
            throw new BadRequestAlertException("not customer", ENTITY_NAME, "idnull");
        }
        if (walletUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WalletUserDTO result = walletUserService.save(walletUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, walletUserDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/wallet-users/find-one/")
    public ResponseEntity<WalletUserDTO> getWalletUserByCriteria(WalletUserCriteria criteria) {
        log.debug("REST request to get WalletUser by criteria: {}", criteria);

        try {
            List<WalletUserDTO> walletUsers = walletUserQueryService.findByCriteria(criteria);

            if (!walletUsers.isEmpty()) {
                WalletUserDTO walletUserDTO = walletUsers.get(0);
                return ResponseEntity.ok(walletUserDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error retrieving WalletUser by criteria", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
