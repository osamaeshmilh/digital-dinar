package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.post.dinar.domain.enumeration.WalletAction;
import ly.post.dinar.domain.enumeration.WalletStatus;
import ly.post.dinar.repository.WalletTransactionRepository;
import ly.post.dinar.security.AuthoritiesConstants;
import ly.post.dinar.security.SecurityUtils;
import ly.post.dinar.service.ActivationService;
import ly.post.dinar.service.WalletTransactionQueryService;
import ly.post.dinar.service.WalletTransactionService;
import ly.post.dinar.service.WalletUserService;
import ly.post.dinar.service.criteria.WalletTransactionCriteria;
import ly.post.dinar.service.dto.WalletTransactionDTO;
import ly.post.dinar.service.dto.WalletUserDTO;
import ly.post.dinar.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.post.dinar.domain.WalletTransaction}.
 */
@RestController
@RequestMapping("/api")
public class WalletTransactionResource {

    private final Logger log = LoggerFactory.getLogger(WalletTransactionResource.class);

    private static final String ENTITY_NAME = "walletTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WalletTransactionService walletTransactionService;

    private final WalletTransactionRepository walletTransactionRepository;

    private final WalletTransactionQueryService walletTransactionQueryService;

    private final WalletUserService walletUserService;

    private final ActivationService activationService;

    public WalletTransactionResource(
        WalletTransactionService walletTransactionService,
        WalletTransactionRepository walletTransactionRepository,
        WalletTransactionQueryService walletTransactionQueryService,
        WalletUserService walletUserService,
        ActivationService activationService
    ) {
        this.walletTransactionService = walletTransactionService;
        this.walletTransactionRepository = walletTransactionRepository;
        this.walletTransactionQueryService = walletTransactionQueryService;
        this.walletUserService = walletUserService;
        this.activationService = activationService;
    }

    /**
     * {@code POST  /wallet-transactions} : Create a new walletTransaction.
     *
     * @param walletTransactionDTO the walletTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new walletTransactionDTO, or with status {@code 400 (Bad Request)} if the walletTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wallet-transactions")
    public ResponseEntity<WalletTransactionDTO> createWalletTransaction(@RequestBody WalletTransactionDTO walletTransactionDTO)
        throws URISyntaxException {
        log.debug("REST request to save WalletTransaction : {}", walletTransactionDTO);
        if (walletTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new walletTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WalletTransactionDTO result = walletTransactionService.save(walletTransactionDTO);
        return ResponseEntity
            .created(new URI("/api/wallet-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
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
    @PutMapping("/wallet-transactions/{id}")
    public ResponseEntity<WalletTransactionDTO> updateWalletTransaction(
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

        if (!walletTransactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WalletTransactionDTO result = walletTransactionService.update(walletTransactionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletTransactionDTO.getId().toString()))
            .body(result);
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
    @PatchMapping(value = "/wallet-transactions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WalletTransactionDTO> partialUpdateWalletTransaction(
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

        if (!walletTransactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WalletTransactionDTO> result = walletTransactionService.partialUpdate(walletTransactionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletTransactionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /wallet-transactions} : get all the walletTransactions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of walletTransactions in body.
     */
    @GetMapping("/wallet-transactions")
    public ResponseEntity<List<WalletTransactionDTO>> getAllWalletTransactions(
        WalletTransactionCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get WalletTransactions by criteria: {}", criteria);

        Page<WalletTransactionDTO> page = walletTransactionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wallet-transactions/count} : count all the walletTransactions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/wallet-transactions/count")
    public ResponseEntity<Long> countWalletTransactions(WalletTransactionCriteria criteria) {
        log.debug("REST request to count WalletTransactions by criteria: {}", criteria);
        return ResponseEntity.ok().body(walletTransactionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /wallet-transactions/:id} : get the "id" walletTransaction.
     *
     * @param id the id of the walletTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the walletTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wallet-transactions/{id}")
    public ResponseEntity<WalletTransactionDTO> getWalletTransaction(@PathVariable Long id) {
        log.debug("REST request to get WalletTransaction : {}", id);
        Optional<WalletTransactionDTO> walletTransactionDTO = walletTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(walletTransactionDTO);
    }

    /**
     * {@code DELETE  /wallet-transactions/:id} : delete the "id" walletTransaction.
     *
     * @param id the id of the walletTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wallet-transactions/{id}")
    public ResponseEntity<Void> deleteWalletTransaction(@PathVariable Long id) {
        log.debug("REST request to delete WalletTransaction : {}", id);
        walletTransactionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/wallet-transactions/balance")
    public ResponseEntity<Float> getCustomerWalletBalance(WalletTransactionCriteria criteria) {
        if (
            SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CONSUMER) ||
            SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.MERCHANT)
        ) {
            LongFilter longFilter = new LongFilter();
            longFilter.setEquals(walletUserService.findOneByUser().getId());
            criteria.setWalletUserId(longFilter);
        }
        return ResponseEntity.ok().body(walletTransactionQueryService.sumTotalByCriteria(criteria));
    }

    @PostMapping("/wallet-transactions/wallet-transfer")
    public ResponseEntity<WalletTransactionDTO> WalletToWalletTransfer(
        @RequestParam String toCustomerPublicKey,
        @RequestParam Float amount,
        @RequestParam String otp
    ) throws URISyntaxException {
        System.out.println(walletUserService.findOneUser().getResetKey() + " " + otp);

        WalletTransactionDTO result = null;
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CONSUMER)) {} else {}
        if (!walletUserService.findOneByWalletPublicKey(toCustomerPublicKey).isPresent()) throw new BadRequestAlertException(
            "Wallet Key not found!",
            ENTITY_NAME,
            "CUSTOMER_NOT_FOUND"
        );

        WalletUserDTO fromCustomer = walletUserService.findOneDTOByUser();
        WalletUserDTO toCustomer = walletUserService
            .findOneByWalletPublicKey(toCustomerPublicKey)
            .orElseThrow(() -> new BadRequestAlertException("Not found !", "", "NOT_FOUND"));
        if (fromCustomer.getVerifiedByMobileOtp()) activationService.checkCodeWithMobileNo(fromCustomer.getMobileNo(), otp); else if (
            fromCustomer.getVerifiedByEmailOtp()
        ) activationService.checkCodeWithEmail(fromCustomer.getEmail(), otp); else throw new BadRequestAlertException(
            "No Verification Way !",
            "",
            "NO_VERIFICATION_WAY"
        );

        if (fromCustomer.getWalletStatus() == WalletStatus.SUSPENDED) {
            throw new BadRequestAlertException("Wallet Suspended !", "", "CUSTOMER_BANNED");
        }
        if (!fromCustomer.getIsKYCVerified()) {
            throw new BadRequestAlertException("Wallet User Not Verified !", "", "CUSTOMER_NOT_VERIFIED");
        }

        if (fromCustomer.getId() == toCustomer.getId()) throw new BadRequestAlertException(
            "Can not transfer to yourself!",
            ENTITY_NAME,
            "TRANSFER_NOT_ALLOWED_TO_SAME_CUSTOMER"
        );

        result = walletTransactionService.walletTransfer(fromCustomer, toCustomer, amount);

        return ResponseEntity
            .created(new URI("/api/customer-wallets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    //    @PostMapping("/wallet-transactions/transfer-fees")
    //    public ResponseEntity<String> getWalletTransferFees(@RequestParam Float amount) throws URISyntaxException {
    //        String jsonResponse =
    //            "[" + "{ \"name\" : \"FEES\", \"total\" : " + walletTransactionService.getTransferFees(amount).toString() + " }" + "]";
    //        return ResponseEntity.ok(jsonResponse);
    //    }

    @PostMapping("/wallet-transactions/transfer-mobile")
    public ResponseEntity<WalletTransactionDTO> customerWalletTransferMobile(
        @RequestParam String mobileNo,
        @RequestParam Float amount,
        @RequestParam String otp
    ) throws URISyntaxException {
        mobileNo = mobileNo.trim();
        mobileNo = mobileNo.replace("+", "");
        mobileNo = mobileNo.replace(" ", "");

        System.out.println(walletUserService.findOneUser().getResetKey() + " " + otp + " " + mobileNo);

        WalletTransactionDTO result = null;
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CONSUMER)) {
            WalletUserDTO fromCustomer = walletUserService.findOneDTOByUser();
            if (fromCustomer.getVerifiedByMobileOtp()) activationService.checkCodeWithMobileNo(fromCustomer.getMobileNo(), otp); else if (
                fromCustomer.getVerifiedByEmailOtp()
            ) activationService.checkCodeWithEmail(fromCustomer.getEmail(), otp); else throw new BadRequestAlertException(
                "No Verification Way !",
                "",
                "NO_VERIFICATION_WAY"
            );

            if (!fromCustomer.getIsKYCVerified()) {
                throw new BadRequestAlertException("Customer Not Verified !", "", "CUSTOMER_NOT_VERIFIED");
            }

            if (!walletUserService.findOneByMobileNo(mobileNo).isPresent()) throw new BadRequestAlertException(
                "Mobile not found!",
                ENTITY_NAME,
                "CUSTOMER_NOT_FOUND"
            );

            WalletUserDTO toCustomer = walletUserService
                .findOneByMobileNo(mobileNo)
                .orElseThrow(() -> new BadRequestAlertException("Not found !", "", "NOT_FOUND"));

            if (fromCustomer.getId() == toCustomer.getId()) throw new BadRequestAlertException(
                "Can not transfer to yourself!",
                ENTITY_NAME,
                "TRANSFER_NOT_ALLOWED_TO_SAME_CUSTOMER"
            );
            result = walletTransactionService.walletTransfer(fromCustomer, toCustomer, amount);
        }
        return ResponseEntity
            .created(new URI("/api/customer-wallets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @Secured(AuthoritiesConstants.ADMIN)
    @PostMapping("/wallet-transactions/top-up")
    public ResponseEntity<WalletTransactionDTO> topUpBalance(@RequestBody WalletTransactionDTO walletTransactionDTO)
        throws URISyntaxException {
        if (walletTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerWallet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        walletTransactionDTO.setWalletAction(WalletAction.DEPOSIT);
        walletTransactionDTO.setNotes("Top-up " + walletTransactionDTO.getNotes());
        WalletTransactionDTO result = walletTransactionService.topUpWallet(walletTransactionDTO);

        return ResponseEntity
            .created(new URI("/api/customer-wallets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    //    @Secured(AuthoritiesConstants.ADMIN)
    //    @PostMapping("/wallet-transactions/cash-withdrawal")
    //    public ResponseEntity<WalletTransactionDTO> cashWithdrawal(@RequestBody WalletTransactionDTO walletTransactionDTO) throws URISyntaxException {
    //        if (walletTransactionDTO.getId() != null) {
    //            throw new BadRequestAlertException("A new customerWallet cannot already have an ID", ENTITY_NAME, "idexists");
    //        }
    //
    //        WalletTransactionCriteria criteria = new WalletTransactionCriteria();
    //
    //        LongFilter longFilter = new LongFilter();
    //        longFilter.setEquals(walletTransactionDTO.getWalletUser().getId());
    //        criteria.setWalletUserId(longFilter);
    //
    //        Float withdrawalFee = Float.valueOf(settingService.getSettingByKey("cash_withdrawal").get().getValue());
    //
    //        if (walletTransactionQueryService.sumTotalByCriteria(criteria) < (walletTransactionDTO.getAmount() + withdrawalFee)) {
    //            throw new BadRequestAlertException("No Credit in Wallet !", "", "NO_CREDIT");
    //        }
    //
    //        WalletTransactionDTO result = walletTransactionService.cashWithdrawalFromAdmin(walletTransactionDTO);
    //
    //        return ResponseEntity
    //            .created(new URI("/api/customer-wallets/" + result.getId()))
    //            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
    //            .body(result);
    //    }
}
