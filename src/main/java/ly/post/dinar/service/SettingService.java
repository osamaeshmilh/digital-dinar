package ly.post.dinar.service;

import java.util.Optional;
import ly.post.dinar.domain.Setting;
import ly.post.dinar.repository.SettingRepository;
import ly.post.dinar.service.dto.SettingDTO;
import ly.post.dinar.service.mapper.SettingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public SettingDTO save(SettingDTO settingDTO) {
        log.debug("Request to save Setting : {}", settingDTO);
        Setting setting = settingMapper.toEntity(settingDTO);
        setting = settingRepository.save(setting);
        return settingMapper.toDto(setting);
    }

    /**
     * Update a setting.
     *
     * @param settingDTO the entity to save.
     * @return the persisted entity.
     */
    public SettingDTO update(SettingDTO settingDTO) {
        log.debug("Request to update Setting : {}", settingDTO);
        Setting setting = settingMapper.toEntity(settingDTO);
        setting = settingRepository.save(setting);
        return settingMapper.toDto(setting);
    }

    /**
     * Partially update a setting.
     *
     * @param settingDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SettingDTO> partialUpdate(SettingDTO settingDTO) {
        log.debug("Request to partially update Setting : {}", settingDTO);

        return settingRepository
            .findById(settingDTO.getId())
            .map(existingSetting -> {
                settingMapper.partialUpdate(existingSetting, settingDTO);

                return existingSetting;
            })
            .map(settingRepository::save)
            .map(settingMapper::toDto);
    }

    /**
     * Get all the settings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SettingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Settings");
        return settingRepository.findAll(pageable).map(settingMapper::toDto);
    }

    /**
     * Get one setting by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SettingDTO> findOne(Long id) {
        log.debug("Request to get Setting : {}", id);
        return settingRepository.findById(id).map(settingMapper::toDto);
    }

    /**
     * Delete the setting by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Setting : {}", id);
        settingRepository.deleteById(id);
    }

    public Optional<SettingDTO> getSettingDtoByKey(String key) {
        return settingRepository.findByKey(key).map(settingMapper::toDto);
    }
}
