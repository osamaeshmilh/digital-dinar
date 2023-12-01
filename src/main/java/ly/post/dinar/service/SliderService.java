package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.SliderCriteria;
import ly.post.dinar.repository.SliderRepository;
import ly.post.dinar.service.dto.SliderDTO;
import ly.post.dinar.service.mapper.SliderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Slider}.
 */
@Service
@Transactional
public class SliderService {

    private final Logger log = LoggerFactory.getLogger(SliderService.class);

    private final SliderRepository sliderRepository;

    private final SliderMapper sliderMapper;

    public SliderService(SliderRepository sliderRepository, SliderMapper sliderMapper) {
        this.sliderRepository = sliderRepository;
        this.sliderMapper = sliderMapper;
    }

    /**
     * Save a slider.
     *
     * @param sliderDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<SliderDTO> save(SliderDTO sliderDTO) {
        log.debug("Request to save Slider : {}", sliderDTO);
        return sliderRepository.save(sliderMapper.toEntity(sliderDTO)).map(sliderMapper::toDto);
    }

    /**
     * Update a slider.
     *
     * @param sliderDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<SliderDTO> update(SliderDTO sliderDTO) {
        log.debug("Request to update Slider : {}", sliderDTO);
        return sliderRepository.save(sliderMapper.toEntity(sliderDTO)).map(sliderMapper::toDto);
    }

    /**
     * Partially update a slider.
     *
     * @param sliderDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<SliderDTO> partialUpdate(SliderDTO sliderDTO) {
        log.debug("Request to partially update Slider : {}", sliderDTO);

        return sliderRepository
            .findById(sliderDTO.getId())
            .map(existingSlider -> {
                sliderMapper.partialUpdate(existingSlider, sliderDTO);

                return existingSlider;
            })
            .flatMap(sliderRepository::save)
            .map(sliderMapper::toDto);
    }

    /**
     * Get all the sliders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<SliderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sliders");
        return sliderRepository.findAllBy(pageable).map(sliderMapper::toDto);
    }

    /**
     * Find sliders by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<SliderDTO> findByCriteria(SliderCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Sliders by Criteria");
        return sliderRepository.findByCriteria(criteria, pageable).map(sliderMapper::toDto);
    }

    /**
     * Find the count of sliders by criteria.
     * @param criteria filtering criteria
     * @return the count of sliders
     */
    public Mono<Long> countByCriteria(SliderCriteria criteria) {
        log.debug("Request to get the count of all Sliders by Criteria");
        return sliderRepository.countByCriteria(criteria);
    }

    /**
     * Returns the number of sliders available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return sliderRepository.count();
    }

    /**
     * Get one slider by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<SliderDTO> findOne(Long id) {
        log.debug("Request to get Slider : {}", id);
        return sliderRepository.findById(id).map(sliderMapper::toDto);
    }

    /**
     * Delete the slider by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Slider : {}", id);
        return sliderRepository.deleteById(id);
    }
}
