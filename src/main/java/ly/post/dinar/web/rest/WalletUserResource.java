package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.post.dinar.repository.WalletUserRepository;
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
@RequestMapping("/api/wallet-users")
public class WalletUserResource {

    private final Logger log = LoggerFactory.getLogger(WalletUserResource.class);

    private static final String ENTITY_NAME = "walletUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WalletUserService walletUserService;

    private final WalletUserRepository walletUserRepository;

    private final WalletUserQueryService walletUserQueryService;

    public WalletUserResource(
        WalletUserService walletUserService,
        WalletUserRepository walletUserRepository,
        WalletUserQueryService walletUserQueryService
    ) {
        this.walletUserService = walletUserService;
        this.walletUserRepository = walletUserRepository;
        this.walletUserQueryService = walletUserQueryService;
    }

    /**
     * {@code POST  /wallet-users} : Create a new walletUser.
     *
     * @param walletUserDTO the walletUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new walletUserDTO, or with status {@code 400 (Bad Request)} if the walletUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
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
    @PutMapping("/{id}")
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
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
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
    @GetMapping("")
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
    @GetMapping("/count")
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
    @GetMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWalletUser(@PathVariable Long id) {
        log.debug("REST request to delete WalletUser : {}", id);
        walletUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
