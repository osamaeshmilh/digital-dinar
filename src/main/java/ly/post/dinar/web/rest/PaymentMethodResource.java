package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.post.dinar.repository.PaymentMethodRepository;
import ly.post.dinar.service.PaymentMethodQueryService;
import ly.post.dinar.service.PaymentMethodService;
import ly.post.dinar.service.criteria.PaymentMethodCriteria;
import ly.post.dinar.service.dto.PaymentMethodDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.PaymentMethod}.
 */
@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodResource {

    private final Logger log = LoggerFactory.getLogger(PaymentMethodResource.class);

    private static final String ENTITY_NAME = "paymentMethod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentMethodService paymentMethodService;

    private final PaymentMethodRepository paymentMethodRepository;

    private final PaymentMethodQueryService paymentMethodQueryService;

    public PaymentMethodResource(
        PaymentMethodService paymentMethodService,
        PaymentMethodRepository paymentMethodRepository,
        PaymentMethodQueryService paymentMethodQueryService
    ) {
        this.paymentMethodService = paymentMethodService;
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodQueryService = paymentMethodQueryService;
    }

    /**
     * {@code POST  /payment-methods} : Create a new paymentMethod.
     *
     * @param paymentMethodDTO the paymentMethodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentMethodDTO, or with status {@code 400 (Bad Request)} if the paymentMethod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PaymentMethodDTO> createPaymentMethod(@RequestBody PaymentMethodDTO paymentMethodDTO) throws URISyntaxException {
        log.debug("REST request to save PaymentMethod : {}", paymentMethodDTO);
        if (paymentMethodDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentMethod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentMethodDTO result = paymentMethodService.save(paymentMethodDTO);
        return ResponseEntity
            .created(new URI("/api/payment-methods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payment-methods/:id} : Updates an existing paymentMethod.
     *
     * @param id the id of the paymentMethodDTO to save.
     * @param paymentMethodDTO the paymentMethodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentMethodDTO,
     * or with status {@code 400 (Bad Request)} if the paymentMethodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentMethodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> updatePaymentMethod(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PaymentMethodDTO paymentMethodDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PaymentMethod : {}, {}", id, paymentMethodDTO);
        if (paymentMethodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentMethodDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentMethodRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PaymentMethodDTO result = paymentMethodService.update(paymentMethodDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentMethodDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /payment-methods/:id} : Partial updates given fields of an existing paymentMethod, field will ignore if it is null
     *
     * @param id the id of the paymentMethodDTO to save.
     * @param paymentMethodDTO the paymentMethodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentMethodDTO,
     * or with status {@code 400 (Bad Request)} if the paymentMethodDTO is not valid,
     * or with status {@code 404 (Not Found)} if the paymentMethodDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the paymentMethodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PaymentMethodDTO> partialUpdatePaymentMethod(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PaymentMethodDTO paymentMethodDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PaymentMethod partially : {}, {}", id, paymentMethodDTO);
        if (paymentMethodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentMethodDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentMethodRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PaymentMethodDTO> result = paymentMethodService.partialUpdate(paymentMethodDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentMethodDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /payment-methods} : get all the paymentMethods.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentMethods in body.
     */
    @GetMapping("")
    public ResponseEntity<List<PaymentMethodDTO>> getAllPaymentMethods(
        PaymentMethodCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get PaymentMethods by criteria: {}", criteria);

        Page<PaymentMethodDTO> page = paymentMethodQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payment-methods/count} : count all the paymentMethods.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countPaymentMethods(PaymentMethodCriteria criteria) {
        log.debug("REST request to count PaymentMethods by criteria: {}", criteria);
        return ResponseEntity.ok().body(paymentMethodQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /payment-methods/:id} : get the "id" paymentMethod.
     *
     * @param id the id of the paymentMethodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentMethodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethod(@PathVariable Long id) {
        log.debug("REST request to get PaymentMethod : {}", id);
        Optional<PaymentMethodDTO> paymentMethodDTO = paymentMethodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentMethodDTO);
    }

    /**
     * {@code DELETE  /payment-methods/:id} : delete the "id" paymentMethod.
     *
     * @param id the id of the paymentMethodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Long id) {
        log.debug("REST request to delete PaymentMethod : {}", id);
        paymentMethodService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
