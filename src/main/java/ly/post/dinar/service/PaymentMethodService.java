package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.PaymentMethodCriteria;
import ly.post.dinar.repository.PaymentMethodRepository;
import ly.post.dinar.service.dto.PaymentMethodDTO;
import ly.post.dinar.service.mapper.PaymentMethodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.PaymentMethod}.
 */
@Service
@Transactional
public class PaymentMethodService {

    private final Logger log = LoggerFactory.getLogger(PaymentMethodService.class);

    private final PaymentMethodRepository paymentMethodRepository;

    private final PaymentMethodMapper paymentMethodMapper;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodMapper = paymentMethodMapper;
    }

    /**
     * Save a paymentMethod.
     *
     * @param paymentMethodDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<PaymentMethodDTO> save(PaymentMethodDTO paymentMethodDTO) {
        log.debug("Request to save PaymentMethod : {}", paymentMethodDTO);
        return paymentMethodRepository.save(paymentMethodMapper.toEntity(paymentMethodDTO)).map(paymentMethodMapper::toDto);
    }

    /**
     * Update a paymentMethod.
     *
     * @param paymentMethodDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<PaymentMethodDTO> update(PaymentMethodDTO paymentMethodDTO) {
        log.debug("Request to update PaymentMethod : {}", paymentMethodDTO);
        return paymentMethodRepository.save(paymentMethodMapper.toEntity(paymentMethodDTO)).map(paymentMethodMapper::toDto);
    }

    /**
     * Partially update a paymentMethod.
     *
     * @param paymentMethodDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<PaymentMethodDTO> partialUpdate(PaymentMethodDTO paymentMethodDTO) {
        log.debug("Request to partially update PaymentMethod : {}", paymentMethodDTO);

        return paymentMethodRepository
            .findById(paymentMethodDTO.getId())
            .map(existingPaymentMethod -> {
                paymentMethodMapper.partialUpdate(existingPaymentMethod, paymentMethodDTO);

                return existingPaymentMethod;
            })
            .flatMap(paymentMethodRepository::save)
            .map(paymentMethodMapper::toDto);
    }

    /**
     * Get all the paymentMethods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<PaymentMethodDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PaymentMethods");
        return paymentMethodRepository.findAllBy(pageable).map(paymentMethodMapper::toDto);
    }

    /**
     * Find paymentMethods by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<PaymentMethodDTO> findByCriteria(PaymentMethodCriteria criteria, Pageable pageable) {
        log.debug("Request to get all PaymentMethods by Criteria");
        return paymentMethodRepository.findByCriteria(criteria, pageable).map(paymentMethodMapper::toDto);
    }

    /**
     * Find the count of paymentMethods by criteria.
     * @param criteria filtering criteria
     * @return the count of paymentMethods
     */
    public Mono<Long> countByCriteria(PaymentMethodCriteria criteria) {
        log.debug("Request to get the count of all PaymentMethods by Criteria");
        return paymentMethodRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of paymentMethods available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return paymentMethodRepository.count();
    }

    /**
     * Get one paymentMethod by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<PaymentMethodDTO> findOne(Long id) {
        log.debug("Request to get PaymentMethod : {}", id);
        return paymentMethodRepository.findById(id).map(paymentMethodMapper::toDto);
    }

    /**
     * Delete the paymentMethod by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PaymentMethod : {}", id);
        return paymentMethodRepository.deleteById(id);
    }
}
