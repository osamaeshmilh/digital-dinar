package ly.post.dinar.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.post.dinar.repository.SliderRepository;
import ly.post.dinar.service.SliderQueryService;
import ly.post.dinar.service.SliderService;
import ly.post.dinar.service.criteria.SliderCriteria;
import ly.post.dinar.service.dto.SliderDTO;
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
 * REST controller for managing {@link ly.post.dinar.domain.Slider}.
 */
@RestController
@RequestMapping("/api")
public class SliderResource {

    private final Logger log = LoggerFactory.getLogger(SliderResource.class);

    private static final String ENTITY_NAME = "slider";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SliderService sliderService;

    private final SliderRepository sliderRepository;

    private final SliderQueryService sliderQueryService;

    public SliderResource(SliderService sliderService, SliderRepository sliderRepository, SliderQueryService sliderQueryService) {
        this.sliderService = sliderService;
        this.sliderRepository = sliderRepository;
        this.sliderQueryService = sliderQueryService;
    }

    /**
     * {@code POST  /sliders} : Create a new slider.
     *
     * @param sliderDTO the sliderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sliderDTO, or with status {@code 400 (Bad Request)} if the slider has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sliders")
    public ResponseEntity<SliderDTO> createSlider(@RequestBody SliderDTO sliderDTO) throws URISyntaxException {
        log.debug("REST request to save Slider : {}", sliderDTO);
        if (sliderDTO.getId() != null) {
            throw new BadRequestAlertException("A new slider cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SliderDTO result = sliderService.save(sliderDTO);
        return ResponseEntity
            .created(new URI("/api/sliders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sliders/:id} : Updates an existing slider.
     *
     * @param id the id of the sliderDTO to save.
     * @param sliderDTO the sliderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sliderDTO,
     * or with status {@code 400 (Bad Request)} if the sliderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sliderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sliders/{id}")
    public ResponseEntity<SliderDTO> updateSlider(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SliderDTO sliderDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Slider : {}, {}", id, sliderDTO);
        if (sliderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sliderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sliderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SliderDTO result = sliderService.update(sliderDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sliderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sliders/:id} : Partial updates given fields of an existing slider, field will ignore if it is null
     *
     * @param id the id of the sliderDTO to save.
     * @param sliderDTO the sliderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sliderDTO,
     * or with status {@code 400 (Bad Request)} if the sliderDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sliderDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sliderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sliders/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SliderDTO> partialUpdateSlider(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SliderDTO sliderDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Slider partially : {}, {}", id, sliderDTO);
        if (sliderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sliderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sliderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SliderDTO> result = sliderService.partialUpdate(sliderDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sliderDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /sliders} : get all the sliders.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sliders in body.
     */
    @GetMapping("/sliders")
    public ResponseEntity<List<SliderDTO>> getAllSliders(
        SliderCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Sliders by criteria: {}", criteria);

        Page<SliderDTO> page = sliderQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sliders/count} : count all the sliders.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/sliders/count")
    public ResponseEntity<Long> countSliders(SliderCriteria criteria) {
        log.debug("REST request to count Sliders by criteria: {}", criteria);
        return ResponseEntity.ok().body(sliderQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sliders/:id} : get the "id" slider.
     *
     * @param id the id of the sliderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sliderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sliders/{id}")
    public ResponseEntity<SliderDTO> getSlider(@PathVariable Long id) {
        log.debug("REST request to get Slider : {}", id);
        Optional<SliderDTO> sliderDTO = sliderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sliderDTO);
    }

    /**
     * {@code DELETE  /sliders/:id} : delete the "id" slider.
     *
     * @param id the id of the sliderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sliders/{id}")
    public ResponseEntity<Void> deleteSlider(@PathVariable Long id) {
        log.debug("REST request to delete Slider : {}", id);
        sliderService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/public/sliders")
    public ResponseEntity<List<SliderDTO>> getAllSlidersPublic(
        SliderCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Sliders by criteria: {}", criteria);

        Page<SliderDTO> page = sliderQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/public/sliders/count")
    public ResponseEntity<Long> countSlidersPublic(SliderCriteria criteria) {
        log.debug("REST request to count Sliders by criteria: {}", criteria);
        return ResponseEntity.ok().body(sliderQueryService.countByCriteria(criteria));
    }

    @GetMapping("/public/sliders/{id}")
    public ResponseEntity<SliderDTO> getSliderPublic(@PathVariable Long id) {
        log.debug("REST request to get Slider : {}", id);
        Optional<SliderDTO> sliderDTO = sliderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sliderDTO);
    }
}
