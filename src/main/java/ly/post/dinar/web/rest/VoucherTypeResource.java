package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import ly.post.dinar.domain.criteria.VoucherTypeCriteria;
import ly.post.dinar.repository.VoucherTypeRepository;
import ly.post.dinar.service.VoucherTypeService;
import ly.post.dinar.service.dto.VoucherTypeDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.VoucherType}.
 */
@RestController
@RequestMapping("/api/voucher-types")
public class VoucherTypeResource {

    private final Logger log = LoggerFactory.getLogger(VoucherTypeResource.class);

    private static final String ENTITY_NAME = "voucherType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VoucherTypeService voucherTypeService;

    private final VoucherTypeRepository voucherTypeRepository;

    public VoucherTypeResource(VoucherTypeService voucherTypeService, VoucherTypeRepository voucherTypeRepository) {
        this.voucherTypeService = voucherTypeService;
        this.voucherTypeRepository = voucherTypeRepository;
    }

    /**
     * {@code POST  /voucher-types} : Create a new voucherType.
     *
     * @param voucherTypeDTO the voucherTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new voucherTypeDTO, or with status {@code 400 (Bad Request)} if the voucherType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<VoucherTypeDTO>> createVoucherType(@RequestBody VoucherTypeDTO voucherTypeDTO) throws URISyntaxException {
        log.debug("REST request to save VoucherType : {}", voucherTypeDTO);
        if (voucherTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new voucherType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return voucherTypeService
            .save(voucherTypeDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/voucher-types/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /voucher-types/:id} : Updates an existing voucherType.
     *
     * @param id the id of the voucherTypeDTO to save.
     * @param voucherTypeDTO the voucherTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated voucherTypeDTO,
     * or with status {@code 400 (Bad Request)} if the voucherTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the voucherTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<VoucherTypeDTO>> updateVoucherType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VoucherTypeDTO voucherTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update VoucherType : {}, {}", id, voucherTypeDTO);
        if (voucherTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, voucherTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return voucherTypeRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return voucherTypeService
                    .update(voucherTypeDTO)
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
     * {@code PATCH  /voucher-types/:id} : Partial updates given fields of an existing voucherType, field will ignore if it is null
     *
     * @param id the id of the voucherTypeDTO to save.
     * @param voucherTypeDTO the voucherTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated voucherTypeDTO,
     * or with status {@code 400 (Bad Request)} if the voucherTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the voucherTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the voucherTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<VoucherTypeDTO>> partialUpdateVoucherType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VoucherTypeDTO voucherTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update VoucherType partially : {}, {}", id, voucherTypeDTO);
        if (voucherTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, voucherTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return voucherTypeRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<VoucherTypeDTO> result = voucherTypeService.partialUpdate(voucherTypeDTO);

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
     * {@code GET  /voucher-types} : get all the voucherTypes.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of voucherTypes in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<VoucherTypeDTO>>> getAllVoucherTypes(
        VoucherTypeCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get VoucherTypes by criteria: {}", criteria);
        return voucherTypeService
            .countByCriteria(criteria)
            .zipWith(voucherTypeService.findByCriteria(criteria, pageable).collectList())
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
     * {@code GET  /voucher-types/count} : count all the voucherTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public Mono<ResponseEntity<Long>> countVoucherTypes(VoucherTypeCriteria criteria) {
        log.debug("REST request to count VoucherTypes by criteria: {}", criteria);
        return voucherTypeService.countByCriteria(criteria).map(count -> ResponseEntity.status(HttpStatus.OK).body(count));
    }

    /**
     * {@code GET  /voucher-types/:id} : get the "id" voucherType.
     *
     * @param id the id of the voucherTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the voucherTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<VoucherTypeDTO>> getVoucherType(@PathVariable Long id) {
        log.debug("REST request to get VoucherType : {}", id);
        Mono<VoucherTypeDTO> voucherTypeDTO = voucherTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(voucherTypeDTO);
    }

    /**
     * {@code DELETE  /voucher-types/:id} : delete the "id" voucherType.
     *
     * @param id the id of the voucherTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteVoucherType(@PathVariable Long id) {
        log.debug("REST request to delete VoucherType : {}", id);
        return voucherTypeService
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
