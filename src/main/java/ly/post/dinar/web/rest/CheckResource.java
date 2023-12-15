package ly.post.dinar.web.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import jakarta.servlet.http.HttpServletRequest;
import ly.post.dinar.service.RecaptchaService;
import ly.post.dinar.web.rest.errors.BadRequestAlertException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CheckResource {

    private final RecaptchaService recaptchaService;

    public CheckResource(RecaptchaService recaptchaService) {
        this.recaptchaService = recaptchaService;
    }

    @GetMapping(path = "/public/check/nid/")
    public ResponseEntity<String> checkNID(String nationalNo, String registerNo, String recapchaToken, HttpServletRequest request) {
        ////        if (!recaptchaService.verifyRecaptcha(request.getRemoteAddr(), recapchaToken)) {
        ////            throw new InvalidRecapchaException();
        ////        }
        //        NIDInfo nidInfo = APIsIntegration.getNIDInfo(nationalNo, registerNo);
        //        if (nidInfo == null) {
        //            throw new BadRequestAlertException(" خدمة الرقم الوطني متوقفة حاليا !", "", "nidError2");
        //        }
        //        System.out.println("nid " + nidInfo.getStatus() + " " + nationalNo + " " + registerNo);
        //        switch (nidInfo.getStatus()) {
        //            case 1:
        //                throw new BadRequestAlertException("خدمة الرقم الوطني تحت الصيانة !", "", "nidError2");
        //            case 2:
        //                throw new BadRequestAlertException("بيانات خاطئة من منظومة الرقم الوطني !", "", "nidError2");
        //            case 3:
        //                throw new BadRequestAlertException("مواطن موقوف من منظومة الرقم الوطني !", "", "nidError2");
        ////            case 4:
        ////                throw new BadRequestAlertException("مواطن متوفي من منظومة الرقم الوطني !", "", "nidError2");
        //            default:
        //                break;
        //        }
        //
        //        if (nidInfo.getStatus() != 0) {
        //            throw new BadRequestAlertException("خطأ بخدمة الرقم الوطني !", "", "nidError2");
        //        }

        if (!nationalNo.equals("123456789012")) {
            throw new BadRequestAlertException("بيانات خاطئة من منظومة الرقم الوطني !", "", "nidError2");
        }
        return ResponseEntity.ok().body("محمد علي");
    }

    @GetMapping(path = "/public/check/nid-mobile/")
    public ResponseEntity<String> checkNIDWithMobile(String nationalNo, String mobileNo, String recapchaToken, HttpServletRequest request) {
        //        if (!recaptchaService.verifyRecaptcha(request.getRemoteAddr(), recapchaToken)) {
        //            throw new InvalidRecapchaException();
        //        }
        //        AlmadarNIDStatus almadarNIDStatus = APIsIntegration.checkNIDWithAlmadar(nationalNo, mobileNo);
        //        if (almadarNIDStatus == null) {
        //            throw new BadRequestAlertException("خطأ بخدمة التحقق من ربط الهاتف بالرقم الوطني !", "", "nidError2");
        //        }
        //        System.out.println("almadar " + almadarNIDStatus.getStatusCode() + " " + nationalNo + " " + mobileNo);
        //        switch (almadarNIDStatus.getStatusCode()) {
        //            case 2:
        //                throw new BadRequestAlertException("رقم وطني ورقم هاتف غير متطابقان !", "", "nidError2");
        //            case 404:
        //                throw new BadRequestAlertException("لم يتم العثور على هذا الرقم الوطني ، تحقق من مراكز شركة المدار للتسجيل !", "", "nidError2");
        //            case 407:
        //                throw new BadRequestAlertException("رقم وطني غير صحيح من منظومة شركة المدار", "", "nidError2");
        //            case 408:
        //                throw new BadRequestAlertException("رقم وطني غير صحيح من منظومة شركة المدار!", "", "nidError2");
        //            case 410:
        //                throw new BadRequestAlertException("رقم هاتف غير صحيح من منظومة شركة المدار", "", "nidError2");
        //            case 50:
        //                throw new BadRequestAlertException("خطأ بمنظومة شركة المدار!", "", "nidError2");
        //            default:
        //                break;
        //        }
        //        if (almadarNIDStatus.getStatusCode() != 1) {
        //            throw new BadRequestAlertException("خطأ بخدمة التحقق من رقم الهاتف بشركة المدار !", "", "nidError2");
        //        }

        return ResponseEntity.ok().body("true");
    }

    @GetMapping(path = "/public/check/post-code/")
    public ResponseEntity<String> checkPostCode(String postCode, String recapchaToken, HttpServletRequest request) {
        try {
            HttpResponse<JsonNode> response = Unirest
                .get("https://npa.ly/map/search/ajax")
                .queryString("txtSearch", postCode) // Adjust this based on your requirements
                .header("Accept", "application/json")
                .asJson();

            // Process the response as needed
            String responseBody = response.getBody().toString();
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            // Handle exceptions appropriately
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
        }
        //        return ResponseEntity.ok().body("{" + "postCode: 10.10.100," + "lat: 32.222222," + "lng: 12.222222," + "}");
    }
}
