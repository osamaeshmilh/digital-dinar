package ly.post.dinar.service;

import ly.post.dinar.domain.criteria.CityCriteria;
import ly.post.dinar.repository.CityRepository;
import ly.post.dinar.service.dto.CityDTO;
import ly.post.dinar.service.mapper.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.City}.
 */
@Service
@Transactional
public class CityService {

    private final Logger log = LoggerFactory.getLogger(CityService.class);

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    /**
     * Save a city.
     *
     * @param cityDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CityDTO> save(CityDTO cityDTO) {
        log.debug("Request to save City : {}", cityDTO);
        return cityRepository.save(cityMapper.toEntity(cityDTO)).map(cityMapper::toDto);
    }

    /**
     * Update a city.
     *
     * @param cityDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CityDTO> update(CityDTO cityDTO) {
        log.debug("Request to update City : {}", cityDTO);
        return cityRepository.save(cityMapper.toEntity(cityDTO)).map(cityMapper::toDto);
    }

    /**
     * Partially update a city.
     *
     * @param cityDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CityDTO> partialUpdate(CityDTO cityDTO) {
        log.debug("Request to partially update City : {}", cityDTO);

        return cityRepository
            .findById(cityDTO.getId())
            .map(existingCity -> {
                cityMapper.partialUpdate(existingCity, cityDTO);

                return existingCity;
            })
            .flatMap(cityRepository::save)
            .map(cityMapper::toDto);
    }

    /**
     * Get all the cities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cities");
        return cityRepository.findAllBy(pageable).map(cityMapper::toDto);
    }

    /**
     * Find cities by Criteria.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CityDTO> findByCriteria(CityCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Cities by Criteria");
        return cityRepository.findByCriteria(criteria, pageable).map(cityMapper::toDto);
    }

    /**
     * Find the count of cities by criteria.
     * @param criteria filtering criteria
     * @return the count of cities
     */
    public Mono<Long> countByCriteria(CityCriteria criteria) {
        log.debug("Request to get the count of all Cities by Criteria");
        return cityRepository.countByCriteria(criteria);
    }

    /**
     * Get all the cities with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<CityDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cityRepository.findAllWithEagerRelationships(pageable).map(cityMapper::toDto);
    }

    /**
     * Returns the number of cities available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return cityRepository.count();
    }

    /**
     * Get one city by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CityDTO> findOne(Long id) {
        log.debug("Request to get City : {}", id);
        return cityRepository.findOneWithEagerRelationships(id).map(cityMapper::toDto);
    }

    /**
     * Delete the city by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete City : {}", id);
        return cityRepository.deleteById(id);
    }
}
