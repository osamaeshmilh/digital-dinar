package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import ly.post.dinar.domain.criteria.ActivationCriteria;
import ly.post.dinar.repository.ActivationRepository;
import ly.post.dinar.service.ActivationService;
import ly.post.dinar.service.dto.ActivationDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.Activation}.
 */
@RestController
@RequestMapping("/api/activations")
public class ActivationResource {

    private final Logger log = LoggerFactory.getLogger(ActivationResource.class);

    private static final String ENTITY_NAME = "activation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActivationService activationService;

    private final ActivationRepository activationRepository;

    public ActivationResource(ActivationService activationService, ActivationRepository activationRepository) {
        this.activationService = activationService;
        this.activationRepository = activationRepository;
    }

    /**
     * {@code POST  /activations} : Create a new activation.
     *
     * @param activationDTO the activationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activationDTO, or with status {@code 400 (Bad Request)} if the activation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<ActivationDTO>> createActivation(@RequestBody ActivationDTO activationDTO) throws URISyntaxException {
        log.debug("REST request to save Activation : {}", activationDTO);
        if (activationDTO.getId() != null) {
            throw new BadRequestAlertException("A new activation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return activationService
            .save(activationDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/activations/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
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
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ActivationDTO>> updateActivation(
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

        return activationRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return activationService
                    .update(activationDTO)
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
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ActivationDTO>> partialUpdateActivation(
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

        return activationRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ActivationDTO> result = activationService.partialUpdate(activationDTO);

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
     * {@code GET  /activations} : get all the activations.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activations in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<ActivationDTO>>> getAllActivations(
        ActivationCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get Activations by criteria: {}", criteria);
        return activationService
            .countByCriteria(criteria)
            .zipWith(activationService.findByCriteria(criteria, pageable).collectList())
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
     * {@code GET  /activations/count} : count all the activations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public Mono<ResponseEntity<Long>> countActivations(ActivationCriteria criteria) {
        log.debug("REST request to count Activations by criteria: {}", criteria);
        return activationService.countByCriteria(criteria).map(count -> ResponseEntity.status(HttpStatus.OK).body(count));
    }

    /**
     * {@code GET  /activations/:id} : get the "id" activation.
     *
     * @param id the id of the activationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ActivationDTO>> getActivation(@PathVariable Long id) {
        log.debug("REST request to get Activation : {}", id);
        Mono<ActivationDTO> activationDTO = activationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activationDTO);
    }

    /**
     * {@code DELETE  /activations/:id} : delete the "id" activation.
     *
     * @param id the id of the activationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteActivation(@PathVariable Long id) {
        log.debug("REST request to delete Activation : {}", id);
        return activationService
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
