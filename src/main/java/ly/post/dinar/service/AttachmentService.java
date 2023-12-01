package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.AttachmentCriteria;
import ly.post.dinar.repository.AttachmentRepository;
import ly.post.dinar.service.dto.AttachmentDTO;
import ly.post.dinar.service.mapper.AttachmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Attachment}.
 */
@Service
@Transactional
public class AttachmentService {

    private final Logger log = LoggerFactory.getLogger(AttachmentService.class);

    private final AttachmentRepository attachmentRepository;

    private final AttachmentMapper attachmentMapper;

    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentMapper attachmentMapper) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentMapper = attachmentMapper;
    }

    /**
     * Save a attachment.
     *
     * @param attachmentDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AttachmentDTO> save(AttachmentDTO attachmentDTO) {
        log.debug("Request to save Attachment : {}", attachmentDTO);
        return attachmentRepository.save(attachmentMapper.toEntity(attachmentDTO)).map(attachmentMapper::toDto);
    }

    /**
     * Update a attachment.
     *
     * @param attachmentDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AttachmentDTO> update(AttachmentDTO attachmentDTO) {
        log.debug("Request to update Attachment : {}", attachmentDTO);
        return attachmentRepository.save(attachmentMapper.toEntity(attachmentDTO)).map(attachmentMapper::toDto);
    }

    /**
     * Partially update a attachment.
     *
     * @param attachmentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AttachmentDTO> partialUpdate(AttachmentDTO attachmentDTO) {
        log.debug("Request to partially update Attachment : {}", attachmentDTO);

        return attachmentRepository
            .findById(attachmentDTO.getId())
            .map(existingAttachment -> {
                attachmentMapper.partialUpdate(existingAttachment, attachmentDTO);

                return existingAttachment;
            })
            .flatMap(attachmentRepository::save)
            .map(attachmentMapper::toDto);
    }

    /**
     * Get all the attachments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AttachmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Attachments");
        return attachmentRepository.findAllBy(pageable).map(attachmentMapper::toDto);
    }

    /**
     * Find attachments by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AttachmentDTO> findByCriteria(AttachmentCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Attachments by Criteria");
        return attachmentRepository.findByCriteria(criteria, pageable).map(attachmentMapper::toDto);
    }

    /**
     * Find the count of attachments by criteria.
     * @param criteria filtering criteria
     * @return the count of attachments
     */
    public Mono<Long> countByCriteria(AttachmentCriteria criteria) {
        log.debug("Request to get the count of all Attachments by Criteria");
        return attachmentRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of attachments available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return attachmentRepository.count();
    }

    /**
     * Get one attachment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AttachmentDTO> findOne(Long id) {
        log.debug("Request to get Attachment : {}", id);
        return attachmentRepository.findById(id).map(attachmentMapper::toDto);
    }

    /**
     * Delete the attachment by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Attachment : {}", id);
        return attachmentRepository.deleteById(id);
    }
}
