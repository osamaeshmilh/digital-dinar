package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.SettingCriteria;
import ly.post.dinar.repository.SettingRepository;
import ly.post.dinar.service.dto.SettingDTO;
import ly.post.dinar.service.mapper.SettingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Setting}.
 */
@Service
@Transactional
public class SettingService {

    private final Logger log = LoggerFactory.getLogger(SettingService.class);

    private final SettingRepository settingRepository;

    private final SettingMapper settingMapper;

    public SettingService(SettingRepository settingRepository, SettingMapper settingMapper) {
        this.settingRepository = settingRepository;
        this.settingMapper = settingMapper;
    }

    /**
     * Save a setting.
     *
     * @param settingDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<SettingDTO> save(SettingDTO settingDTO) {
        log.debug("Request to save Setting : {}", settingDTO);
        return settingRepository.save(settingMapper.toEntity(settingDTO)).map(settingMapper::toDto);
    }

    /**
     * Update a setting.
     *
     * @param settingDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<SettingDTO> update(SettingDTO settingDTO) {
        log.debug("Request to update Setting : {}", settingDTO);
        return settingRepository.save(settingMapper.toEntity(settingDTO)).map(settingMapper::toDto);
    }

    /**
     * Partially update a setting.
     *
     * @param settingDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<SettingDTO> partialUpdate(SettingDTO settingDTO) {
        log.debug("Request to partially update Setting : {}", settingDTO);

        return settingRepository
            .findById(settingDTO.getId())
            .map(existingSetting -> {
                settingMapper.partialUpdate(existingSetting, settingDTO);

                return existingSetting;
            })
            .flatMap(settingRepository::save)
            .map(settingMapper::toDto);
    }

    /**
     * Get all the settings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<SettingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Settings");
        return settingRepository.findAllBy(pageable).map(settingMapper::toDto);
    }

    /**
     * Find settings by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<SettingDTO> findByCriteria(SettingCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Settings by Criteria");
        return settingRepository.findByCriteria(criteria, pageable).map(settingMapper::toDto);
    }

    /**
     * Find the count of settings by criteria.
     * @param criteria filtering criteria
     * @return the count of settings
     */
    public Mono<Long> countByCriteria(SettingCriteria criteria) {
        log.debug("Request to get the count of all Settings by Criteria");
        return settingRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of settings available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return settingRepository.count();
    }

    /**
     * Get one setting by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<SettingDTO> findOne(Long id) {
        log.debug("Request to get Setting : {}", id);
        return settingRepository.findById(id).map(settingMapper::toDto);
    }

    /**
     * Delete the setting by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Setting : {}", id);
        return settingRepository.deleteById(id);
    }
}
