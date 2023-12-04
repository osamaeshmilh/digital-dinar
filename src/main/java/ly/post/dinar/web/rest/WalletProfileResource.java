package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.post.dinar.repository.WalletProfileRepository;
import ly.post.dinar.service.WalletProfileQueryService;
import ly.post.dinar.service.WalletProfileService;
import ly.post.dinar.service.criteria.WalletProfileCriteria;
import ly.post.dinar.service.dto.WalletProfileDTO;
import ly.post.dinar.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.post.dinar.domain.WalletProfile}.
 */
@RestController
@RequestMapping("/api/wallet-profiles")
public class WalletProfileResource {

    private final Logger log = LoggerFactory.getLogger(WalletProfileResource.class);

    private static final String ENTITY_NAME = "walletProfile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WalletProfileService walletProfileService;

    private final WalletProfileRepository walletProfileRepository;

    private final WalletProfileQueryService walletProfileQueryService;

    public WalletProfileResource(
        WalletProfileService walletProfileService,
        WalletProfileRepository walletProfileRepository,
        WalletProfileQueryService walletProfileQueryService
    ) {
        this.walletProfileService = walletProfileService;
        this.walletProfileRepository = walletProfileRepository;
        this.walletProfileQueryService = walletProfileQueryService;
    }

    /**
     * {@code POST  /wallet-profiles} : Create a new walletProfile.
     *
     * @param walletProfileDTO the walletProfileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new walletProfileDTO, or with status {@code 400 (Bad Request)} if the walletProfile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<WalletProfileDTO> createWalletProfile(@RequestBody WalletProfileDTO walletProfileDTO) throws URISyntaxException {
        log.debug("REST request to save WalletProfile : {}", walletProfileDTO);
        if (walletProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new walletProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WalletProfileDTO result = walletProfileService.save(walletProfileDTO);
        return ResponseEntity
            .created(new URI("/api/wallet-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wallet-profiles/:id} : Updates an existing walletProfile.
     *
     * @param id the id of the walletProfileDTO to save.
     * @param walletProfileDTO the walletProfileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated walletProfileDTO,
     * or with status {@code 400 (Bad Request)} if the walletProfileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the walletProfileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<WalletProfileDTO> updateWalletProfile(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WalletProfileDTO walletProfileDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WalletProfile : {}, {}", id, walletProfileDTO);
        if (walletProfileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, walletProfileDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!walletProfileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WalletProfileDTO result = walletProfileService.update(walletProfileDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /wallet-profiles/:id} : Partial updates given fields of an existing walletProfile, field will ignore if it is null
     *
     * @param id the id of the walletProfileDTO to save.
     * @param walletProfileDTO the walletProfileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated walletProfileDTO,
     * or with status {@code 400 (Bad Request)} if the walletProfileDTO is not valid,
     * or with status {@code 404 (Not Found)} if the walletProfileDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the walletProfileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WalletProfileDTO> partialUpdateWalletProfile(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WalletProfileDTO walletProfileDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WalletProfile partially : {}, {}", id, walletProfileDTO);
        if (walletProfileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, walletProfileDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!walletProfileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WalletProfileDTO> result = walletProfileService.partialUpdate(walletProfileDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletProfileDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /wallet-profiles} : get all the walletProfiles.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of walletProfiles in body.
     */
    @GetMapping("")
    public ResponseEntity<List<WalletProfileDTO>> getAllWalletProfiles(
        WalletProfileCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get WalletProfiles by criteria: {}", criteria);

        Page<WalletProfileDTO> page = walletProfileQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wallet-profiles/count} : count all the walletProfiles.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countWalletProfiles(WalletProfileCriteria criteria) {
        log.debug("REST request to count WalletProfiles by criteria: {}", criteria);
        return ResponseEntity.ok().body(walletProfileQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /wallet-profiles/:id} : get the "id" walletProfile.
     *
     * @param id the id of the walletProfileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the walletProfileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<WalletProfileDTO> getWalletProfile(@PathVariable Long id) {
        log.debug("REST request to get WalletProfile : {}", id);
        Optional<WalletProfileDTO> walletProfileDTO = walletProfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(walletProfileDTO);
    }

    /**
     * {@code DELETE  /wallet-profiles/:id} : delete the "id" walletProfile.
     *
     * @param id the id of the walletProfileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWalletProfile(@PathVariable Long id) {
        log.debug("REST request to delete WalletProfile : {}", id);
        walletProfileService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
