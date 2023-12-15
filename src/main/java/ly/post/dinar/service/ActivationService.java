package ly.post.dinar.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import ly.post.dinar.domain.Activation;
import ly.post.dinar.domain.User;
import ly.post.dinar.repository.ActivationRepository;
import ly.post.dinar.service.dto.ActivationDTO;
import ly.post.dinar.service.mapper.ActivationMapper;
import ly.post.dinar.web.rest.errors.BadRequestAlertException;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.post.dinar.domain.Activation}.
 */
@Service
@Transactional
public class ActivationService {

    private final Logger log = LoggerFactory.getLogger(ActivationService.class);

    private final ActivationRepository activationRepository;

    private final ActivationMapper activationMapper;

    private final MailService mailService;

    private final SMSService smsService;

    public ActivationService(
        ActivationRepository activationRepository,
        ActivationMapper activationMapper,
        MailService mailService,
        SMSService smsService
    ) {
        this.activationRepository = activationRepository;
        this.activationMapper = activationMapper;
        this.mailService = mailService;
        this.smsService = smsService;
    }

    /**
     * Save a activation.
     *
     * @param activationDTO the entity to save.
     * @return the persisted entity.
     */
    public ActivationDTO save(ActivationDTO activationDTO) {
        log.debug("Request to save Activation : {}", activationDTO);
        Activation activation = activationMapper.toEntity(activationDTO);
        activation = activationRepository.save(activation);
        return activationMapper.toDto(activation);
    }

    /**
     * Update a activation.
     *
     * @param activationDTO the entity to save.
     * @return the persisted entity.
     */
    public ActivationDTO update(ActivationDTO activationDTO) {
        log.debug("Request to update Activation : {}", activationDTO);
        Activation activation = activationMapper.toEntity(activationDTO);
        activation = activationRepository.save(activation);
        return activationMapper.toDto(activation);
    }

    /**
     * Partially update a activation.
     *
     * @param activationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ActivationDTO> partialUpdate(ActivationDTO activationDTO) {
        log.debug("Request to partially update Activation : {}", activationDTO);

        return activationRepository
            .findById(activationDTO.getId())
            .map(existingActivation -> {
                activationMapper.partialUpdate(existingActivation, activationDTO);

                return existingActivation;
            })
            .map(activationRepository::save)
            .map(activationMapper::toDto);
    }

    /**
     * Get all the activations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ActivationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Activations");
        return activationRepository.findAll(pageable).map(activationMapper::toDto);
    }

    /**
     * Get one activation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ActivationDTO> findOne(Long id) {
        log.debug("Request to get Activation : {}", id);
        return activationRepository.findById(id).map(activationMapper::toDto);
    }

    /**
     * Delete the activation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Activation : {}", id);
        activationRepository.deleteById(id);
    }

    public void sendEmailOTP(String email) {
        Activation newActivation = activationRepository.findByEmail(email).orElseGet(() -> createNewActivationForEmail(email));

        generateAndSaveActivationCode(newActivation);
        sendActivationEmail(newActivation);
    }

    public void sendSMSOTP(String mobileNo) {
        Activation newActivation = activationRepository
            .findFirstByMobileNoContains(mobileNo)
            .orElseGet(() -> createNewActivationForMobile(mobileNo));

        generateAndSaveActivationCode(newActivation);
        sendActivationSMS(newActivation);
    }

    public Activation checkCodeWithEmail(String email, String otp) {
        return checkCode(activationRepository.findByEmailAndCode(email, otp), "EMAIL_CODE_NOT_MATCH");
    }

    public Activation checkCodeWithMobileNo(String mobileNo, String otp) {
        return checkCode(activationRepository.findFirstByMobileNoContainsAndCode(mobileNo, otp), "MOBILE_CODE_NOT_MATCH");
    }

    public void sendSMSAndEmailOTP(String mobileNo, String email) {
        // Generate a common OTP code for both SMS and Email
        //        String commonCode = RandomStringUtils.randomNumeric(4);
        String commonCode = "1111";

        // Create or Update Email Activation
        Activation emailActivation = activationRepository.findByEmail(email).orElseGet(() -> createNewActivationForEmail(email));

        updateActivation(emailActivation, commonCode);

        // Create or Update SMS Activation
        Activation smsActivation = activationRepository
            .findFirstByMobileNoContains(mobileNo)
            .orElseGet(() -> createNewActivationForMobile(mobileNo));

        updateActivation(smsActivation, commonCode);

        // Send OTP via SMS
        sendActivationSMS(smsActivation);

        // Send OTP via Email
        sendActivationEmail(emailActivation);
    }

    private Activation createNewActivationForEmail(String email) {
        Activation newActivation = new Activation();
        newActivation.setEmail(email);
        return newActivation;
    }

    private Activation createNewActivationForMobile(String mobileNo) {
        Activation newActivation = new Activation();
        newActivation.setMobileNo(mobileNo);
        return newActivation;
    }

    private void generateAndSaveActivationCode(Activation activation) {
        //        String code = RandomStringUtils.randomNumeric(4);
        String code = "1111";

        activation.setCode(code);
        activation.setIsUsed(false);
        activation.setValidUntil(Instant.now().plus(1, ChronoUnit.HOURS));
        activationRepository.save(activation);
    }

    private void updateActivation(Activation activation, String commonCode) {
        activation.setCode(commonCode);
        activation.setIsUsed(false);
        activation.setValidUntil(Instant.now().plus(1, ChronoUnit.HOURS));
        activationRepository.save(activation);
    }

    private Activation checkCode(Optional<Activation> activationOptional, String errorKey) {
        return activationOptional
            .map(activation -> {
                if (activation.getIsUsed()) {
                    throw new BadRequestAlertException("OTP Already Used!", "", "OTP_USED");
                }
                if (Instant.now().isAfter(activation.getValidUntil())) {
                    throw new BadRequestAlertException("OTP Expired!", "", "OTP_Expired");
                }
                activation.setIsUsed(true);
                activationRepository.save(activation);
                return activation;
            })
            .orElseThrow(() -> new BadRequestAlertException("OTP and Email not match!", "", errorKey));
    }

    private void sendActivationEmail(Activation activation) {
        User user = new User();
        user.setEmail(activation.getEmail());
        user.setLogin(activation.getEmail());
        user.setResetKey(activation.getCode());
        mailService.sendOtpMail(user);
    }

    private void sendActivationSMS(Activation activation) {
        smsService.sendSMS(activation.getMobileNo(), activation.getCode());
    }
}
