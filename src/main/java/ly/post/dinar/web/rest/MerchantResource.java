package ly.post.dinar.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import ly.post.dinar.domain.criteria.MerchantCriteria;
import ly.post.dinar.repository.MerchantRepository;
import ly.post.dinar.service.MerchantService;
import ly.post.dinar.service.dto.MerchantDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.Merchant}.
 */
@RestController
@RequestMapping("/api/merchants")
public class MerchantResource {

    private final Logger log = LoggerFactory.getLogger(MerchantResource.class);

    private static final String ENTITY_NAME = "merchant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MerchantService merchantService;

    private final MerchantRepository merchantRepository;

    public MerchantResource(MerchantService merchantService, MerchantRepository merchantRepository) {
        this.merchantService = merchantService;
        this.merchantRepository = merchantRepository;
    }

    /**
     * {@code POST  /merchants} : Create a new merchant.
     *
     * @param merchantDTO the merchantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new merchantDTO, or with status {@code 400 (Bad Request)} if the merchant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<MerchantDTO>> createMerchant(@Valid @RequestBody MerchantDTO merchantDTO) throws URISyntaxException {
        log.debug("REST request to save Merchant : {}", merchantDTO);
        if (merchantDTO.getId() != null) {
            throw new BadRequestAlertException("A new merchant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return merchantService
            .save(merchantDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/merchants/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /merchants/:id} : Updates an existing merchant.
     *
     * @param id the id of the merchantDTO to save.
     * @param merchantDTO the merchantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated merchantDTO,
     * or with status {@code 400 (Bad Request)} if the merchantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the merchantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<MerchantDTO>> updateMerchant(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MerchantDTO merchantDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Merchant : {}, {}", id, merchantDTO);
        if (merchantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, merchantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return merchantRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return merchantService
                    .update(merchantDTO)
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
     * {@code PATCH  /merchants/:id} : Partial updates given fields of an existing merchant, field will ignore if it is null
     *
     * @param id the id of the merchantDTO to save.
     * @param merchantDTO the merchantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated merchantDTO,
     * or with status {@code 400 (Bad Request)} if the merchantDTO is not valid,
     * or with status {@code 404 (Not Found)} if the merchantDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the merchantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<MerchantDTO>> partialUpdateMerchant(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MerchantDTO merchantDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Merchant partially : {}, {}", id, merchantDTO);
        if (merchantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, merchantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return merchantRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<MerchantDTO> result = merchantService.partialUpdate(merchantDTO);

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
     * {@code GET  /merchants} : get all the merchants.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of merchants in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<MerchantDTO>>> getAllMerchants(
        MerchantCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get Merchants by criteria: {}", criteria);
        return merchantService
            .countByCriteria(criteria)
            .zipWith(merchantService.findByCriteria(criteria, pageable).collectList())
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
     * {@code GET  /merchants/count} : count all the merchants.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public Mono<ResponseEntity<Long>> countMerchants(MerchantCriteria criteria) {
        log.debug("REST request to count Merchants by criteria: {}", criteria);
        return merchantService.countByCriteria(criteria).map(count -> ResponseEntity.status(HttpStatus.OK).body(count));
    }

    /**
     * {@code GET  /merchants/:id} : get the "id" merchant.
     *
     * @param id the id of the merchantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the merchantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<MerchantDTO>> getMerchant(@PathVariable Long id) {
        log.debug("REST request to get Merchant : {}", id);
        Mono<MerchantDTO> merchantDTO = merchantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(merchantDTO);
    }

    /**
     * {@code DELETE  /merchants/:id} : delete the "id" merchant.
     *
     * @param id the id of the merchantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteMerchant(@PathVariable Long id) {
        log.debug("REST request to delete Merchant : {}", id);
        return merchantService
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
