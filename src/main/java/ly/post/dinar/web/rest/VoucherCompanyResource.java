package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import ly.post.dinar.domain.criteria.VoucherCompanyCriteria;
import ly.post.dinar.repository.VoucherCompanyRepository;
import ly.post.dinar.service.VoucherCompanyService;
import ly.post.dinar.service.dto.VoucherCompanyDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.VoucherCompany}.
 */
@RestController
@RequestMapping("/api/voucher-companies")
public class VoucherCompanyResource {

    private final Logger log = LoggerFactory.getLogger(VoucherCompanyResource.class);

    private static final String ENTITY_NAME = "voucherCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VoucherCompanyService voucherCompanyService;

    private final VoucherCompanyRepository voucherCompanyRepository;

    public VoucherCompanyResource(VoucherCompanyService voucherCompanyService, VoucherCompanyRepository voucherCompanyRepository) {
        this.voucherCompanyService = voucherCompanyService;
        this.voucherCompanyRepository = voucherCompanyRepository;
    }

    /**
     * {@code POST  /voucher-companies} : Create a new voucherCompany.
     *
     * @param voucherCompanyDTO the voucherCompanyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new voucherCompanyDTO, or with status {@code 400 (Bad Request)} if the voucherCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<VoucherCompanyDTO>> createVoucherCompany(@RequestBody VoucherCompanyDTO voucherCompanyDTO)
        throws URISyntaxException {
        log.debug("REST request to save VoucherCompany : {}", voucherCompanyDTO);
        if (voucherCompanyDTO.getId() != null) {
            throw new BadRequestAlertException("A new voucherCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return voucherCompanyService
            .save(voucherCompanyDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/voucher-companies/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /voucher-companies/:id} : Updates an existing voucherCompany.
     *
     * @param id the id of the voucherCompanyDTO to save.
     * @param voucherCompanyDTO the voucherCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated voucherCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the voucherCompanyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the voucherCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<VoucherCompanyDTO>> updateVoucherCompany(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VoucherCompanyDTO voucherCompanyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update VoucherCompany : {}, {}", id, voucherCompanyDTO);
        if (voucherCompanyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, voucherCompanyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return voucherCompanyRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return voucherCompanyService
                    .update(voucherCompanyDTO)
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
     * {@code PATCH  /voucher-companies/:id} : Partial updates given fields of an existing voucherCompany, field will ignore if it is null
     *
     * @param id the id of the voucherCompanyDTO to save.
     * @param voucherCompanyDTO the voucherCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated voucherCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the voucherCompanyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the voucherCompanyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the voucherCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<VoucherCompanyDTO>> partialUpdateVoucherCompany(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VoucherCompanyDTO voucherCompanyDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update VoucherCompany partially : {}, {}", id, voucherCompanyDTO);
        if (voucherCompanyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, voucherCompanyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return voucherCompanyRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<VoucherCompanyDTO> result = voucherCompanyService.partialUpdate(voucherCompanyDTO);

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
     * {@code GET  /voucher-companies} : get all the voucherCompanies.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of voucherCompanies in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<VoucherCompanyDTO>>> getAllVoucherCompanies(
        VoucherCompanyCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get VoucherCompanies by criteria: {}", criteria);
        return voucherCompanyService
            .countByCriteria(criteria)
            .zipWith(voucherCompanyService.findByCriteria(criteria, pageable).collectList())
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
     * {@code GET  /voucher-companies/count} : count all the voucherCompanies.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public Mono<ResponseEntity<Long>> countVoucherCompanies(VoucherCompanyCriteria criteria) {
        log.debug("REST request to count VoucherCompanies by criteria: {}", criteria);
        return voucherCompanyService.countByCriteria(criteria).map(count -> ResponseEntity.status(HttpStatus.OK).body(count));
    }

    /**
     * {@code GET  /voucher-companies/:id} : get the "id" voucherCompany.
     *
     * @param id the id of the voucherCompanyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the voucherCompanyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<VoucherCompanyDTO>> getVoucherCompany(@PathVariable Long id) {
        log.debug("REST request to get VoucherCompany : {}", id);
        Mono<VoucherCompanyDTO> voucherCompanyDTO = voucherCompanyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(voucherCompanyDTO);
    }

    /**
     * {@code DELETE  /voucher-companies/:id} : delete the "id" voucherCompany.
     *
     * @param id the id of the voucherCompanyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteVoucherCompany(@PathVariable Long id) {
        log.debug("REST request to delete VoucherCompany : {}", id);
        return voucherCompanyService
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
