package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import ly.post.dinar.domain.criteria.ViewLogCriteria;
import ly.post.dinar.repository.ViewLogRepository;
import ly.post.dinar.service.ViewLogService;
import ly.post.dinar.service.dto.ViewLogDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.ViewLog}.
 */
@RestController
@RequestMapping("/api/view-logs")
public class ViewLogResource {

    private final Logger log = LoggerFactory.getLogger(ViewLogResource.class);

    private static final String ENTITY_NAME = "viewLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewLogService viewLogService;

    private final ViewLogRepository viewLogRepository;

    public ViewLogResource(ViewLogService viewLogService, ViewLogRepository viewLogRepository) {
        this.viewLogService = viewLogService;
        this.viewLogRepository = viewLogRepository;
    }

    /**
     * {@code POST  /view-logs} : Create a new viewLog.
     *
     * @param viewLogDTO the viewLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewLogDTO, or with status {@code 400 (Bad Request)} if the viewLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<ViewLogDTO>> createViewLog(@RequestBody ViewLogDTO viewLogDTO) throws URISyntaxException {
        log.debug("REST request to save ViewLog : {}", viewLogDTO);
        if (viewLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new viewLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return viewLogService
            .save(viewLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/view-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /view-logs/:id} : Updates an existing viewLog.
     *
     * @param id the id of the viewLogDTO to save.
     * @param viewLogDTO the viewLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewLogDTO,
     * or with status {@code 400 (Bad Request)} if the viewLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ViewLogDTO>> updateViewLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewLogDTO viewLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ViewLog : {}, {}", id, viewLogDTO);
        if (viewLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return viewLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return viewLogService
                    .update(viewLogDTO)
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
     * {@code PATCH  /view-logs/:id} : Partial updates given fields of an existing viewLog, field will ignore if it is null
     *
     * @param id the id of the viewLogDTO to save.
     * @param viewLogDTO the viewLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewLogDTO,
     * or with status {@code 400 (Bad Request)} if the viewLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the viewLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ViewLogDTO>> partialUpdateViewLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewLogDTO viewLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewLog partially : {}, {}", id, viewLogDTO);
        if (viewLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return viewLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ViewLogDTO> result = viewLogService.partialUpdate(viewLogDTO);

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
     * {@code GET  /view-logs} : get all the viewLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewLogs in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<ViewLogDTO>>> getAllViewLogs(
        ViewLogCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get ViewLogs by criteria: {}", criteria);
        return viewLogService
            .countByCriteria(criteria)
            .zipWith(viewLogService.findByCriteria(criteria, pageable).collectList())
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
     * {@code GET  /view-logs/count} : count all the viewLogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public Mono<ResponseEntity<Long>> countViewLogs(ViewLogCriteria criteria) {
        log.debug("REST request to count ViewLogs by criteria: {}", criteria);
        return viewLogService.countByCriteria(criteria).map(count -> ResponseEntity.status(HttpStatus.OK).body(count));
    }

    /**
     * {@code GET  /view-logs/:id} : get the "id" viewLog.
     *
     * @param id the id of the viewLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ViewLogDTO>> getViewLog(@PathVariable Long id) {
        log.debug("REST request to get ViewLog : {}", id);
        Mono<ViewLogDTO> viewLogDTO = viewLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewLogDTO);
    }

    /**
     * {@code DELETE  /view-logs/:id} : delete the "id" viewLog.
     *
     * @param id the id of the viewLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteViewLog(@PathVariable Long id) {
        log.debug("REST request to delete ViewLog : {}", id);
        return viewLogService
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
