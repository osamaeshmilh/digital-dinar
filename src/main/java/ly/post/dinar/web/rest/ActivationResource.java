package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.post.dinar.domain.WalletUser;
import ly.post.dinar.repository.ActivationRepository;
import ly.post.dinar.security.AuthoritiesConstants;
import ly.post.dinar.service.ActivationQueryService;
import ly.post.dinar.service.ActivationService;
import ly.post.dinar.service.WalletUserService;
import ly.post.dinar.service.criteria.ActivationCriteria;
import ly.post.dinar.service.dto.ActivationDTO;
import ly.post.dinar.service.dto.WalletUserDTO;
import ly.post.dinar.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.post.dinar.domain.Activation}.
 */
@RestController
@RequestMapping("/api")
public class ActivationResource {

    private final Logger log = LoggerFactory.getLogger(ActivationResource.class);

    private static final String ENTITY_NAME = "activation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActivationService activationService;

    private final ActivationRepository activationRepository;

    private final ActivationQueryService activationQueryService;

    private final WalletUserService walletUserService;

    public ActivationResource(
        ActivationService activationService,
        ActivationRepository activationRepository,
        ActivationQueryService activationQueryService,
        WalletUserService walletUserService
    ) {
        this.activationService = activationService;
        this.activationRepository = activationRepository;
        this.activationQueryService = activationQueryService;
        this.walletUserService = walletUserService;
    }

    /**
     * {@code POST  /activations} : Create a new activation.
     *
     * @param activationDTO the activationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activationDTO, or with status {@code 400 (Bad Request)} if the activation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/activations")
    public ResponseEntity<ActivationDTO> createActivation(@RequestBody ActivationDTO activationDTO) throws URISyntaxException {
        log.debug("REST request to save Activation : {}", activationDTO);
        if (activationDTO.getId() != null) {
            throw new BadRequestAlertException("A new activation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActivationDTO result = activationService.save(activationDTO);
        return ResponseEntity
            .created(new URI("/api/activations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /activations/:id} : Updates an existing activation.
     *
     * @param id the id of the activationDTO to save.
     * @param activationDTO the activationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activationDTO,
     * or with status {@code 400 (Bad Request)} if the activationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the activationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/activations/{id}")
    public ResponseEntity<ActivationDTO> updateActivation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ActivationDTO activationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Activation : {}, {}", id, activationDTO);
        if (activationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, activationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!activationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ActivationDTO result = activationService.update(activationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, activationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /activations/:id} : Partial updates given fields of an existing activation, field will ignore if it is null
     *
     * @param id the id of the activationDTO to save.
     * @param activationDTO the activationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activationDTO,
     * or with status {@code 400 (Bad Request)} if the activationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the activationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the activationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/activations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ActivationDTO> partialUpdateActivation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ActivationDTO activationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Activation partially : {}, {}", id, activationDTO);
        if (activationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, activationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!activationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ActivationDTO> result = activationService.partialUpdate(activationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, activationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /activations} : get all the activations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activations in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ActivationDTO>> getAllActivations(
        ActivationCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Activations by criteria: {}", criteria);

        Page<ActivationDTO> page = activationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /activations/count} : count all the activations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/activations/count")
    public ResponseEntity<Long> countActivations(ActivationCriteria criteria) {
        log.debug("REST request to count Activations by criteria: {}", criteria);
        return ResponseEntity.ok().body(activationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /activations/:id} : get the "id" activation.
     *
     * @param id the id of the activationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/activations/{id}")
    public ResponseEntity<ActivationDTO> getActivation(@PathVariable Long id) {
        log.debug("REST request to get Activation : {}", id);
        Optional<ActivationDTO> activationDTO = activationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activationDTO);
    }

    /**
     * {@code DELETE  /activations/:id} : delete the "id" activation.
     *
     * @param id the id of the activationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/activations/{id}")
    public ResponseEntity<Void> deleteActivation(@PathVariable Long id) {
        log.debug("REST request to delete Activation : {}", id);
        activationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping(path = "/public/activation/email-otp")
    public void sendEmailOTP(@RequestParam String email) {
        if (walletUserService.findOneByEmail(email).isPresent()) {
            throw new BadRequestAlertException("Email Already Used!", ENTITY_NAME, "EMAIL_USED");
        }
        activationService.sendEmailOTP(email);
    }

    @PostMapping(path = "/public/activation/sms-otp")
    public void sendSmsOTP(@RequestParam String mobileNo) {
        if (walletUserService.findOneByMobileNo(mobileNo).isPresent()) {
            throw new BadRequestAlertException("Mobile Number Already Used!", ENTITY_NAME, "MOBILE_USED");
        }
        activationService.sendSMSOTP(mobileNo);
    }

    @PostMapping(path = "/public/activation/sms-and-email-otp")
    public void sendOTPEmailAndSms(@RequestParam String mobileNo, @RequestParam String email) {
        if (walletUserService.findOneByMobileNo(mobileNo).isPresent()) {
            throw new BadRequestAlertException("Mobile Number Already Used!", ENTITY_NAME, "MOBILE_USED");
        }
        if (walletUserService.findOneByEmail(email).isPresent()) {
            throw new BadRequestAlertException("Email Already Used!", ENTITY_NAME, "EMAIL_USED");
        }
        activationService.sendSMSAndEmailOTP(mobileNo, email);
    }

    @PostMapping(path = "/activation/wallet-user/send-otp")
    public void sendSmsOTPToCurrentUser() {
        WalletUserDTO currentUser = walletUserService.findOneDTOByUser();
        boolean isMobileVerified = currentUser.getVerifiedByMobileOtp();
        boolean isEmailVerified = currentUser.getVerifiedByEmailOtp();

        if (isMobileVerified && isEmailVerified) {
            activationService.sendSMSAndEmailOTP(currentUser.getMobileNo(), currentUser.getEmail());
            return;
        }

        if (isMobileVerified) {
            activationService.sendSMSOTP(currentUser.getMobileNo());
        }

        if (isEmailVerified) {
            activationService.sendEmailOTP(currentUser.getEmail());
        }
    }
}
