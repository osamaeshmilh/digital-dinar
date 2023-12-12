package ly.post.dinar.service;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaService {

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private final Logger log = LoggerFactory.getLogger(RecaptchaService.class);
    String recaptchaSecret = "6LesvAIaAAAAAJMoR3v6oXBfFC9nsxSKTQqDCvnp";

    public boolean verifyRecaptcha(String ip, String recaptchaResponse) {
        Map<String, Object> body = new HashMap<>();
        body.put("secret", recaptchaSecret);
        body.put("response", recaptchaResponse);
        body.put("remoteip", ip);
        log.debug("Request body for recaptcha: {}", body);
        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<Map<String, Object>>() {};

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map<String, Object>> recaptchaResponseEntity = restTemplate.exchange(
            GOOGLE_RECAPTCHA_VERIFY_URL + "?secret={secret}&response={response}&remoteip={remoteip}",
            HttpMethod.POST,
            null,
            typeRef,
            body
        );

        log.debug("Response from recaptcha: {}", recaptchaResponseEntity);
        Map<String, Object> responseBody = recaptchaResponseEntity.getBody();

        boolean recaptchaSucess = (Boolean) responseBody.get("success");
        if (!recaptchaSucess) {
            log.error("{}", responseBody.get("error-codes"));
        }
        return recaptchaSucess;
    }
}

class RecaptchaUtil {

    public static Map<String, String> RECAPTCHA_ERROR_CODE = new HashMap<>();

    {
        RECAPTCHA_ERROR_CODE.put("missing-input-secret", "The secret parameter is missing");
        RECAPTCHA_ERROR_CODE.put("invalid-input-secret", "The secret parameter is invalid or malformed");
        RECAPTCHA_ERROR_CODE.put("missing-input-response", "The response parameter is missing");
        RECAPTCHA_ERROR_CODE.put("invalid-input-response", "The response parameter is invalid or malformed");
        RECAPTCHA_ERROR_CODE.put("bad-request", "The request is invalid or malformed");
    }
}
