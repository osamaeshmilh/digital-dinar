package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.post.dinar.repository.VoucherTypeRepository;
import ly.post.dinar.service.VoucherTypeQueryService;
import ly.post.dinar.service.VoucherTypeService;
import ly.post.dinar.service.criteria.VoucherTypeCriteria;
import ly.post.dinar.service.dto.VoucherTypeDTO;
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

    private final VoucherTypeQueryService voucherTypeQueryService;

    public VoucherTypeResource(
        VoucherTypeService voucherTypeService,
        VoucherTypeRepository voucherTypeRepository,
        VoucherTypeQueryService voucherTypeQueryService
    ) {
        this.voucherTypeService = voucherTypeService;
        this.voucherTypeRepository = voucherTypeRepository;
        this.voucherTypeQueryService = voucherTypeQueryService;
    }

    /**
     * {@code POST  /voucher-types} : Create a new voucherType.
     *
     * @param voucherTypeDTO the voucherTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new voucherTypeDTO, or with status {@code 400 (Bad Request)} if the voucherType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<VoucherTypeDTO> createVoucherType(@RequestBody VoucherTypeDTO voucherTypeDTO) throws URISyntaxException {
        log.debug("REST request to save VoucherType : {}", voucherTypeDTO);
        if (voucherTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new voucherType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VoucherTypeDTO result = voucherTypeService.save(voucherTypeDTO);
        return ResponseEntity
            .created(new URI("/api/voucher-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
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
    public ResponseEntity<VoucherTypeDTO> updateVoucherType(
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

        if (!voucherTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VoucherTypeDTO result = voucherTypeService.update(voucherTypeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, voucherTypeDTO.getId().toString()))
            .body(result);
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
    public ResponseEntity<VoucherTypeDTO> partialUpdateVoucherType(
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

        if (!voucherTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VoucherTypeDTO> result = voucherTypeService.partialUpdate(voucherTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, voucherTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /voucher-types} : get all the voucherTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of voucherTypes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<VoucherTypeDTO>> getAllVoucherTypes(
        VoucherTypeCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get VoucherTypes by criteria: {}", criteria);

        Page<VoucherTypeDTO> page = voucherTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /voucher-types/count} : count all the voucherTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countVoucherTypes(VoucherTypeCriteria criteria) {
        log.debug("REST request to count VoucherTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(voucherTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /voucher-types/:id} : get the "id" voucherType.
     *
     * @param id the id of the voucherTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the voucherTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VoucherTypeDTO> getVoucherType(@PathVariable Long id) {
        log.debug("REST request to get VoucherType : {}", id);
        Optional<VoucherTypeDTO> voucherTypeDTO = voucherTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(voucherTypeDTO);
    }

    /**
     * {@code DELETE  /voucher-types/:id} : delete the "id" voucherType.
     *
     * @param id the id of the voucherTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoucherType(@PathVariable Long id) {
        log.debug("REST request to delete VoucherType : {}", id);
        voucherTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
