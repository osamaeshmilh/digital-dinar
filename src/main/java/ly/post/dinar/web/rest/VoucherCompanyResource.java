package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.post.dinar.repository.VoucherCompanyRepository;
import ly.post.dinar.service.VoucherCompanyQueryService;
import ly.post.dinar.service.VoucherCompanyService;
import ly.post.dinar.service.criteria.VoucherCompanyCriteria;
import ly.post.dinar.service.dto.VoucherCompanyDTO;
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

    private final VoucherCompanyQueryService voucherCompanyQueryService;

    public VoucherCompanyResource(
        VoucherCompanyService voucherCompanyService,
        VoucherCompanyRepository voucherCompanyRepository,
        VoucherCompanyQueryService voucherCompanyQueryService
    ) {
        this.voucherCompanyService = voucherCompanyService;
        this.voucherCompanyRepository = voucherCompanyRepository;
        this.voucherCompanyQueryService = voucherCompanyQueryService;
    }

    /**
     * {@code POST  /voucher-companies} : Create a new voucherCompany.
     *
     * @param voucherCompanyDTO the voucherCompanyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new voucherCompanyDTO, or with status {@code 400 (Bad Request)} if the voucherCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<VoucherCompanyDTO> createVoucherCompany(@RequestBody VoucherCompanyDTO voucherCompanyDTO)
        throws URISyntaxException {
        log.debug("REST request to save VoucherCompany : {}", voucherCompanyDTO);
        if (voucherCompanyDTO.getId() != null) {
            throw new BadRequestAlertException("A new voucherCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VoucherCompanyDTO result = voucherCompanyService.save(voucherCompanyDTO);
        return ResponseEntity
            .created(new URI("/api/voucher-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
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
    public ResponseEntity<VoucherCompanyDTO> updateVoucherCompany(
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

        if (!voucherCompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VoucherCompanyDTO result = voucherCompanyService.update(voucherCompanyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, voucherCompanyDTO.getId().toString()))
            .body(result);
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
    public ResponseEntity<VoucherCompanyDTO> partialUpdateVoucherCompany(
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

        if (!voucherCompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VoucherCompanyDTO> result = voucherCompanyService.partialUpdate(voucherCompanyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, voucherCompanyDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /voucher-companies} : get all the voucherCompanies.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of voucherCompanies in body.
     */
    @GetMapping("")
    public ResponseEntity<List<VoucherCompanyDTO>> getAllVoucherCompanies(
        VoucherCompanyCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get VoucherCompanies by criteria: {}", criteria);

        Page<VoucherCompanyDTO> page = voucherCompanyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /voucher-companies/count} : count all the voucherCompanies.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countVoucherCompanies(VoucherCompanyCriteria criteria) {
        log.debug("REST request to count VoucherCompanies by criteria: {}", criteria);
        return ResponseEntity.ok().body(voucherCompanyQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /voucher-companies/:id} : get the "id" voucherCompany.
     *
     * @param id the id of the voucherCompanyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the voucherCompanyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VoucherCompanyDTO> getVoucherCompany(@PathVariable Long id) {
        log.debug("REST request to get VoucherCompany : {}", id);
        Optional<VoucherCompanyDTO> voucherCompanyDTO = voucherCompanyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(voucherCompanyDTO);
    }

    /**
     * {@code DELETE  /voucher-companies/:id} : delete the "id" voucherCompany.
     *
     * @param id the id of the voucherCompanyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoucherCompany(@PathVariable Long id) {
        log.debug("REST request to delete VoucherCompany : {}", id);
        voucherCompanyService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
