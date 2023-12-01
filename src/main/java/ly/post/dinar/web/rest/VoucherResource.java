package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import ly.post.dinar.domain.criteria.VoucherCriteria;
import ly.post.dinar.repository.VoucherRepository;
import ly.post.dinar.service.VoucherService;
import ly.post.dinar.service.dto.VoucherDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.Voucher}.
 */
@RestController
@RequestMapping("/api/vouchers")
public class VoucherResource {

    private final Logger log = LoggerFactory.getLogger(VoucherResource.class);

    private static final String ENTITY_NAME = "voucher";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VoucherService voucherService;

    private final VoucherRepository voucherRepository;

    public VoucherResource(VoucherService voucherService, VoucherRepository voucherRepository) {
        this.voucherService = voucherService;
        this.voucherRepository = voucherRepository;
    }

    /**
     * {@code POST  /vouchers} : Create a new voucher.
     *
     * @param voucherDTO the voucherDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new voucherDTO, or with status {@code 400 (Bad Request)} if the voucher has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<VoucherDTO>> createVoucher(@RequestBody VoucherDTO voucherDTO) throws URISyntaxException {
        log.debug("REST request to save Voucher : {}", voucherDTO);
        if (voucherDTO.getId() != null) {
            throw new BadRequestAlertException("A new voucher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return voucherService
            .save(voucherDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/vouchers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /vouchers/:id} : Updates an existing voucher.
     *
     * @param id the id of the voucherDTO to save.
     * @param voucherDTO the voucherDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated voucherDTO,
     * or with status {@code 400 (Bad Request)} if the voucherDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the voucherDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<VoucherDTO>> updateVoucher(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VoucherDTO voucherDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Voucher : {}, {}", id, voucherDTO);
        if (voucherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, voucherDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return voucherRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return voucherService
                    .update(voucherDTO)
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
     * {@code PATCH  /vouchers/:id} : Partial updates given fields of an existing voucher, field will ignore if it is null
     *
     * @param id the id of the voucherDTO to save.
     * @param voucherDTO the voucherDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated voucherDTO,
     * or with status {@code 400 (Bad Request)} if the voucherDTO is not valid,
     * or with status {@code 404 (Not Found)} if the voucherDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the voucherDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<VoucherDTO>> partialUpdateVoucher(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VoucherDTO voucherDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Voucher partially : {}, {}", id, voucherDTO);
        if (voucherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, voucherDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return voucherRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<VoucherDTO> result = voucherService.partialUpdate(voucherDTO);

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
     * {@code GET  /vouchers} : get all the vouchers.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vouchers in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<VoucherDTO>>> getAllVouchers(
        VoucherCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get Vouchers by criteria: {}", criteria);
        return voucherService
            .countByCriteria(criteria)
            .zipWith(voucherService.findByCriteria(criteria, pageable).collectList())
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
     * {@code GET  /vouchers/count} : count all the vouchers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public Mono<ResponseEntity<Long>> countVouchers(VoucherCriteria criteria) {
        log.debug("REST request to count Vouchers by criteria: {}", criteria);
        return voucherService.countByCriteria(criteria).map(count -> ResponseEntity.status(HttpStatus.OK).body(count));
    }

    /**
     * {@code GET  /vouchers/:id} : get the "id" voucher.
     *
     * @param id the id of the voucherDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the voucherDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<VoucherDTO>> getVoucher(@PathVariable Long id) {
        log.debug("REST request to get Voucher : {}", id);
        Mono<VoucherDTO> voucherDTO = voucherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(voucherDTO);
    }

    /**
     * {@code DELETE  /vouchers/:id} : delete the "id" voucher.
     *
     * @param id the id of the voucherDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteVoucher(@PathVariable Long id) {
        log.debug("REST request to delete Voucher : {}", id);
        return voucherService
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
