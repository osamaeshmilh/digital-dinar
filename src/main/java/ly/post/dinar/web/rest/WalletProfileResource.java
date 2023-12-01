package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import ly.post.dinar.domain.criteria.WalletProfileCriteria;
import ly.post.dinar.repository.WalletProfileRepository;
import ly.post.dinar.service.WalletProfileService;
import ly.post.dinar.service.dto.WalletProfileDTO;
import ly.post.dinar.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

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

    public WalletProfileResource(WalletProfileService walletProfileService, WalletProfileRepository walletProfileRepository) {
        this.walletProfileService = walletProfileService;
        this.walletProfileRepository = walletProfileRepository;
    }

    /**
     * {@code POST  /wallet-profiles} : Create a new walletProfile.
     *
     * @param walletProfileDTO the walletProfileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new walletProfileDTO, or with status {@code 400 (Bad Request)} if the walletProfile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<WalletProfileDTO>> createWalletProfile(@RequestBody WalletProfileDTO walletProfileDTO)
        throws URISyntaxException {
        log.debug("REST request to save WalletProfile : {}", walletProfileDTO);
        if (walletProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new walletProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return walletProfileService
            .save(walletProfileDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/wallet-profiles/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
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
    public Mono<ResponseEntity<WalletProfileDTO>> updateWalletProfile(
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

        return walletProfileRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return walletProfileService
                    .update(walletProfileDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
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
    public Mono<ResponseEntity<WalletProfileDTO>> partialUpdateWalletProfile(
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

        return walletProfileRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<WalletProfileDTO> result = walletProfileService.partialUpdate(walletProfileDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /wallet-profiles} : get all the walletProfiles.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of walletProfiles in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<WalletProfileDTO>>> getAllWalletProfiles(
        WalletProfileCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get WalletProfiles by criteria: {}", criteria);
        return walletProfileService
            .countByCriteria(criteria)
            .zipWith(walletProfileService.findByCriteria(criteria, pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity
                    .ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            UriComponentsBuilder.fromHttpRequest(request),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /wallet-profiles/count} : count all the walletProfiles.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public Mono<ResponseEntity<Long>> countWalletProfiles(WalletProfileCriteria criteria) {
        log.debug("REST request to count WalletProfiles by criteria: {}", criteria);
        return walletProfileService.countByCriteria(criteria).map(count -> ResponseEntity.status(HttpStatus.OK).body(count));
    }

    /**
     * {@code GET  /wallet-profiles/:id} : get the "id" walletProfile.
     *
     * @param id the id of the walletProfileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the walletProfileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<WalletProfileDTO>> getWalletProfile(@PathVariable Long id) {
        log.debug("REST request to get WalletProfile : {}", id);
        Mono<WalletProfileDTO> walletProfileDTO = walletProfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(walletProfileDTO);
    }

    /**
     * {@code DELETE  /wallet-profiles/:id} : delete the "id" walletProfile.
     *
     * @param id the id of the walletProfileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteWalletProfile(@PathVariable Long id) {
        log.debug("REST request to delete WalletProfile : {}", id);
        return walletProfileService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
