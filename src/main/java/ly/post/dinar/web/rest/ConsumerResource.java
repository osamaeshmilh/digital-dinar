package ly.post.dinar.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import ly.post.dinar.domain.criteria.ConsumerCriteria;
import ly.post.dinar.repository.ConsumerRepository;
import ly.post.dinar.service.ConsumerService;
import ly.post.dinar.service.dto.ConsumerDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.Consumer}.
 */
@RestController
@RequestMapping("/api/consumers")
public class ConsumerResource {

    private final Logger log = LoggerFactory.getLogger(ConsumerResource.class);

    private static final String ENTITY_NAME = "consumer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConsumerService consumerService;

    private final ConsumerRepository consumerRepository;

    public ConsumerResource(ConsumerService consumerService, ConsumerRepository consumerRepository) {
        this.consumerService = consumerService;
        this.consumerRepository = consumerRepository;
    }

    /**
     * {@code POST  /consumers} : Create a new consumer.
     *
     * @param consumerDTO the consumerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new consumerDTO, or with status {@code 400 (Bad Request)} if the consumer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<ConsumerDTO>> createConsumer(@Valid @RequestBody ConsumerDTO consumerDTO) throws URISyntaxException {
        log.debug("REST request to save Consumer : {}", consumerDTO);
        if (consumerDTO.getId() != null) {
            throw new BadRequestAlertException("A new consumer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return consumerService
            .save(consumerDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/consumers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /consumers/:id} : Updates an existing consumer.
     *
     * @param id the id of the consumerDTO to save.
     * @param consumerDTO the consumerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated consumerDTO,
     * or with status {@code 400 (Bad Request)} if the consumerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the consumerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ConsumerDTO>> updateConsumer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ConsumerDTO consumerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Consumer : {}, {}", id, consumerDTO);
        if (consumerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, consumerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return consumerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return consumerService
                    .update(consumerDTO)
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
     * {@code PATCH  /consumers/:id} : Partial updates given fields of an existing consumer, field will ignore if it is null
     *
     * @param id the id of the consumerDTO to save.
     * @param consumerDTO the consumerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated consumerDTO,
     * or with status {@code 400 (Bad Request)} if the consumerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the consumerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the consumerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ConsumerDTO>> partialUpdateConsumer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ConsumerDTO consumerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Consumer partially : {}, {}", id, consumerDTO);
        if (consumerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, consumerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return consumerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ConsumerDTO> result = consumerService.partialUpdate(consumerDTO);

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
     * {@code GET  /consumers} : get all the consumers.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of consumers in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<ConsumerDTO>>> getAllConsumers(
        ConsumerCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get Consumers by criteria: {}", criteria);
        return consumerService
            .countByCriteria(criteria)
            .zipWith(consumerService.findByCriteria(criteria, pageable).collectList())
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
     * {@code GET  /consumers/count} : count all the consumers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public Mono<ResponseEntity<Long>> countConsumers(ConsumerCriteria criteria) {
        log.debug("REST request to count Consumers by criteria: {}", criteria);
        return consumerService.countByCriteria(criteria).map(count -> ResponseEntity.status(HttpStatus.OK).body(count));
    }

    /**
     * {@code GET  /consumers/:id} : get the "id" consumer.
     *
     * @param id the id of the consumerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the consumerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ConsumerDTO>> getConsumer(@PathVariable Long id) {
        log.debug("REST request to get Consumer : {}", id);
        Mono<ConsumerDTO> consumerDTO = consumerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consumerDTO);
    }

    /**
     * {@code DELETE  /consumers/:id} : delete the "id" consumer.
     *
     * @param id the id of the consumerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteConsumer(@PathVariable Long id) {
        log.debug("REST request to delete Consumer : {}", id);
        return consumerService
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
