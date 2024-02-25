package ly.post.dinar.web.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.nimbusds.jose.shaded.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import ly.post.dinar.service.RecaptchaService;
import ly.post.dinar.service.dto.external.NID;
import ly.post.dinar.service.dto.external.NIDPhone;
import ly.post.dinar.service.dto.external.NIDToken;
import ly.post.dinar.web.rest.errors.BadRequestAlertException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CheckResource {

    private final RecaptchaService recaptchaService;

    public CheckResource(RecaptchaService recaptchaService) {
        this.recaptchaService = recaptchaService;
    }

    @GetMapping(path = "/public/check/nid/", produces = MediaType.APPLICATION_JSON_VALUE)
    public NID checkNID(@RequestParam String nationalNo, @RequestParam String mobileNo, @RequestParam String recapchaToken) {
        // 1. Get token
        try {
            Gson gson = new Gson();

            HttpResponse<String> tokenResponse = Unirest
                .post("https://sso.ndb.gov.ly/connect/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("grant_type", "client_credentials")
                .field("client_id", "libyaPost")
                .field("client_secret", "#wM8Yu1%J7nLXnhVkMBs95!!CxLN%PBWmzBRWw4b")
                .field("scope", "nid phone")
                .asString();

            System.out.println(tokenResponse.getBody());
            NIDToken nidToken = gson.fromJson(tokenResponse.getBody(), NIDToken.class);

            //2. Use token to check NID
            HttpResponse<String> nidResponse = Unirest
                .post("https://nid.ndb.gov.ly/search/byNid")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + nidToken.getAccess_token())
                .body(new JsonNode(String.format("{\"nationalNo\":\"%s\", \"recapchaToken\":\"%s\"}", nationalNo, recapchaToken)))
                .asString();

            System.out.println("Raw NID Response: " + nidResponse.getBody());
            NID nid = gson.fromJson(nidResponse.getBody(), NID.class);

            // 3. Use token to check phone match
            HttpResponse<String> phoneResponse = Unirest
                .post("https://phone.ndb.gov.ly/ismatching")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + nidToken.getAccess_token())
                .body(new JsonNode(String.format("{\"mobileNo\":\"%s\", \"recapchaToken\":\"%s\"}", mobileNo, recapchaToken)))
                .asString();

            // Assuming you want to log the responses or perform some checks here
            System.out.println("Phone  Response: " + phoneResponse.getRawBody().toString());
            NIDPhone nidPhone = gson.fromJson(phoneResponse.getBody(), NIDPhone.class);

            if (!nidPhone.isMatching()) throw new RuntimeException("phone not match");

            return nid;
        } catch (Exception e) {
            System.err.println(" error: " + e.getMessage());
            throw new RuntimeException(e);
        }
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
    //    @GetMapping(path = "/public/check/image-data/")
    //    public void checkImageData( HttpServletRequest request)
    //    {
    //        System.setProperty("java.library.path", "/opt/homebrew/Cellar/tesseract/5.3.3/lib/");
    //        System.setProperty("jna.library.path", "/opt/homebrew/Cellar/tesseract/5.3.3/lib/");
    //        System.setProperty("TESSDATA_PREFIX", "/opt/homebrew/Cellar/tesseract/5.3.3/share/");
    //
    //        // Provide the path to the PNG image file
    //        File imageFile = new File("/Users/osamaeshmilh/Desktop/pass.png");
    //
    //        // Create an instance of Tesseract
    //        ITesseract tesseract = new Tesseract();
    //
    //        try {
    //            // Set the tessdata path (where your language data files are located)
    //            tesseract.setDatapath("/opt/homebrew/Cellar/tesseract/5.3.3/share/tessdata/");
    ////            tesseract.setLanguage("ara"); // Use the ISO 639-3 language code for Arabic
    //
    //            // Perform OCR on the image
    //            String result = tesseract.doOCR(imageFile);
    ////            String cleanedText = cleanPassportOcr(result);
    //
    //            InputStream modelIn = new FileInputStream("/Users/osamaeshmilh/Downloads/en-ner-person.bin");
    //            TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
    //            NameFinderME nameFinder = new NameFinderME(model);
    //
    //            Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
    //            String[] tokens = tokenizer.tokenize(result);
    //
    //            // Find person names
    //            Span[] names = nameFinder.find(tokens);
    //            System.out.println();
    //
    //            // Print tokens and their indexes
    //            for (int i = 0; i < tokens.length; i++) {
    //                System.out.println(i + ": " + tokens[i]);
    //            }
    //
    //            // Print the extracted spans
    //            for (Span name : names) {
    //                System.out.println("Start: " + name.getStart() + ", End: " + name.getEnd());
    //            }
    //
    //            double[] nameProbabilities = nameFinder.probs();
    //
    //            // Iterate over the recognized names and their probabilities
    //            for (int i = 0; i < names.length; i++) {
    //                Span name = names[i];
    //                double probability = nameProbabilities[i];
    //
    //                System.out.println("Name: " + result.substring(name.getStart(), name.getEnd()) +
    //                    ", Probability: " + probability);
    //            }
    //
    //            // Print the extracted person names
    //            System.out.println("Extracted Person Names:");
    //            for (Span name : names) {
    //                int start = name.getStart();
    //                int end = name.getEnd();
    //
    //                // Extract the person name from the original text using start and end indexes
    //                String personName = concatenateTokens(tokens, start, end);
    //
    //                // Print the extracted person name
    //                System.out.println(personName);
    //            }
    //
    //            // Close the model
    //            modelIn.close();
    //
    //            // Output the extracted text
    //            System.out.println();
    //            System.out.println("Extracted Text:\n" + result);
    //
    //        } catch (Exception e) {
    //            System.err.println("Error during OCR: " + e.getMessage());
    //        }
    //
    //    }
    //
    //    private static String concatenateTokens(String[] tokens, int start, int end) {
    //        StringBuilder sb = new StringBuilder();
    //        for (int i = start; i < end; i++) {
    //            sb.append(tokens[i]);
    //            if (i < end - 1) {
    //                sb.append(" ");
    //            }
    //        }
    //        return sb.toString();
    //    }

}
