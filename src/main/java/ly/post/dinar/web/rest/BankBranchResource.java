package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.post.dinar.repository.BankBranchRepository;
import ly.post.dinar.service.BankBranchQueryService;
import ly.post.dinar.service.BankBranchService;
import ly.post.dinar.service.criteria.BankBranchCriteria;
import ly.post.dinar.service.dto.BankBranchDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.BankBranch}.
 */
@RestController
@RequestMapping("/api")
public class BankBranchResource {

    private final Logger log = LoggerFactory.getLogger(BankBranchResource.class);

    private static final String ENTITY_NAME = "bankBranch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BankBranchService bankBranchService;

    private final BankBranchRepository bankBranchRepository;

    private final BankBranchQueryService bankBranchQueryService;

    public BankBranchResource(
        BankBranchService bankBranchService,
        BankBranchRepository bankBranchRepository,
        BankBranchQueryService bankBranchQueryService
    ) {
        this.bankBranchService = bankBranchService;
        this.bankBranchRepository = bankBranchRepository;
        this.bankBranchQueryService = bankBranchQueryService;
    }

    /**
     * {@code POST  /bank-branches} : Create a new bankBranch.
     *
     * @param bankBranchDTO the bankBranchDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bankBranchDTO, or with status {@code 400 (Bad Request)} if the bankBranch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bank-branches")
    public ResponseEntity<BankBranchDTO> createBankBranch(@RequestBody BankBranchDTO bankBranchDTO) throws URISyntaxException {
        log.debug("REST request to save BankBranch : {}", bankBranchDTO);
        if (bankBranchDTO.getId() != null) {
            throw new BadRequestAlertException("A new bankBranch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BankBranchDTO result = bankBranchService.save(bankBranchDTO);
        return ResponseEntity
            .created(new URI("/api/bank-branches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bank-branches/:id} : Updates an existing bankBranch.
     *
     * @param id the id of the bankBranchDTO to save.
     * @param bankBranchDTO the bankBranchDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankBranchDTO,
     * or with status {@code 400 (Bad Request)} if the bankBranchDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bankBranchDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bank-branches/{id}")
    public ResponseEntity<BankBranchDTO> updateBankBranch(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BankBranchDTO bankBranchDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BankBranch : {}, {}", id, bankBranchDTO);
        if (bankBranchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bankBranchDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bankBranchRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BankBranchDTO result = bankBranchService.update(bankBranchDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bankBranchDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bank-branches/:id} : Partial updates given fields of an existing bankBranch, field will ignore if it is null
     *
     * @param id the id of the bankBranchDTO to save.
     * @param bankBranchDTO the bankBranchDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankBranchDTO,
     * or with status {@code 400 (Bad Request)} if the bankBranchDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bankBranchDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bankBranchDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bank-branches/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BankBranchDTO> partialUpdateBankBranch(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BankBranchDTO bankBranchDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BankBranch partially : {}, {}", id, bankBranchDTO);
        if (bankBranchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bankBranchDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bankBranchRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BankBranchDTO> result = bankBranchService.partialUpdate(bankBranchDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bankBranchDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bank-branches} : get all the bankBranches.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bankBranches in body.
     */
    @GetMapping("/bank-branches")
    public ResponseEntity<List<BankBranchDTO>> getAllBankBranches(
        BankBranchCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get BankBranches by criteria: {}", criteria);

        Page<BankBranchDTO> page = bankBranchQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-branches/count} : count all the bankBranches.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/bank-branches/count")
    public ResponseEntity<Long> countBankBranches(BankBranchCriteria criteria) {
        log.debug("REST request to count BankBranches by criteria: {}", criteria);
        return ResponseEntity.ok().body(bankBranchQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bank-branches/:id} : get the "id" bankBranch.
     *
     * @param id the id of the bankBranchDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bankBranchDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bank-branches/{id}")
    public ResponseEntity<BankBranchDTO> getBankBranch(@PathVariable Long id) {
        log.debug("REST request to get BankBranch : {}", id);
        Optional<BankBranchDTO> bankBranchDTO = bankBranchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bankBranchDTO);
    }

    /**
     * {@code DELETE  /bank-branches/:id} : delete the "id" bankBranch.
     *
     * @param id the id of the bankBranchDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bank-branches/{id}")
    public ResponseEntity<Void> deleteBankBranch(@PathVariable Long id) {
        log.debug("REST request to delete BankBranch : {}", id);
        bankBranchService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/public/bank-branches")
    public ResponseEntity<List<BankBranchDTO>> getAllBankBranchesPublic(
        BankBranchCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get BankBranches by criteria: {}", criteria);
        Page<BankBranchDTO> page = bankBranchQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/public/bank-branches/count")
    public ResponseEntity<Long> countBankBranchesPublic(BankBranchCriteria criteria) {
        log.debug("REST request to count BankBranches by criteria: {}", criteria);
        return ResponseEntity.ok().body(bankBranchQueryService.countByCriteria(criteria));
    }

    @GetMapping("/public/bank-branches/{id}")
    public ResponseEntity<BankBranchDTO> getBankBranchPublic(@PathVariable Long id) {
        log.debug("REST request to get BankBranch : {}", id);
        Optional<BankBranchDTO> bankBranchDTO = bankBranchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bankBranchDTO);
    }
}
