package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import ly.post.dinar.domain.criteria.WalletTransactionCriteria;
import ly.post.dinar.repository.WalletTransactionRepository;
import ly.post.dinar.service.WalletTransactionService;
import ly.post.dinar.service.dto.WalletTransactionDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.WalletTransaction}.
 */
@RestController
@RequestMapping("/api/wallet-transactions")
public class WalletTransactionResource {

    private final Logger log = LoggerFactory.getLogger(WalletTransactionResource.class);

    private static final String ENTITY_NAME = "walletTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WalletTransactionService walletTransactionService;

    private final WalletTransactionRepository walletTransactionRepository;

    public WalletTransactionResource(
        WalletTransactionService walletTransactionService,
        WalletTransactionRepository walletTransactionRepository
    ) {
        this.walletTransactionService = walletTransactionService;
        this.walletTransactionRepository = walletTransactionRepository;
    }

    /**
     * {@code POST  /wallet-transactions} : Create a new walletTransaction.
     *
     * @param walletTransactionDTO the walletTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new walletTransactionDTO, or with status {@code 400 (Bad Request)} if the walletTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<WalletTransactionDTO>> createWalletTransaction(@RequestBody WalletTransactionDTO walletTransactionDTO)
        throws URISyntaxException {
        log.debug("REST request to save WalletTransaction : {}", walletTransactionDTO);
        if (walletTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new walletTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return walletTransactionService
            .save(walletTransactionDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/wallet-transactions/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /wallet-transactions/:id} : Updates an existing walletTransaction.
     *
     * @param id the id of the walletTransactionDTO to save.
     * @param walletTransactionDTO the walletTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated walletTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the walletTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the walletTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<WalletTransactionDTO>> updateWalletTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WalletTransactionDTO walletTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WalletTransaction : {}, {}", id, walletTransactionDTO);
        if (walletTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, walletTransactionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return walletTransactionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return walletTransactionService
                    .update(walletTransactionDTO)
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
     * {@code PATCH  /wallet-transactions/:id} : Partial updates given fields of an existing walletTransaction, field will ignore if it is null
     *
     * @param id the id of the walletTransactionDTO to save.
     * @param walletTransactionDTO the walletTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated walletTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the walletTransactionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the walletTransactionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the walletTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<WalletTransactionDTO>> partialUpdateWalletTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WalletTransactionDTO walletTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WalletTransaction partially : {}, {}", id, walletTransactionDTO);
        if (walletTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, walletTransactionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return walletTransactionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<WalletTransactionDTO> result = walletTransactionService.partialUpdate(walletTransactionDTO);

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
     * {@code GET  /wallet-transactions} : get all the walletTransactions.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of walletTransactions in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<WalletTransactionDTO>>> getAllWalletTransactions(
        WalletTransactionCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get WalletTransactions by criteria: {}", criteria);
        return walletTransactionService
            .countByCriteria(criteria)
            .zipWith(walletTransactionService.findByCriteria(criteria, pageable).collectList())
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
     * {@code GET  /wallet-transactions/count} : count all the walletTransactions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public Mono<ResponseEntity<Long>> countWalletTransactions(WalletTransactionCriteria criteria) {
        log.debug("REST request to count WalletTransactions by criteria: {}", criteria);
        return walletTransactionService.countByCriteria(criteria).map(count -> ResponseEntity.status(HttpStatus.OK).body(count));
    }

    /**
     * {@code GET  /wallet-transactions/:id} : get the "id" walletTransaction.
     *
     * @param id the id of the walletTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the walletTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<WalletTransactionDTO>> getWalletTransaction(@PathVariable Long id) {
        log.debug("REST request to get WalletTransaction : {}", id);
        Mono<WalletTransactionDTO> walletTransactionDTO = walletTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(walletTransactionDTO);
    }

    /**
     * {@code DELETE  /wallet-transactions/:id} : delete the "id" walletTransaction.
     *
     * @param id the id of the walletTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteWalletTransaction(@PathVariable Long id) {
        log.debug("REST request to delete WalletTransaction : {}", id);
        return walletTransactionService
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
