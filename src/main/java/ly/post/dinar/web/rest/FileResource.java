package ly.post.dinar.web.rest;

import ly.post.dinar.service.utils.FileTools;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FileResource {

    public FileResource() {}

    @GetMapping("/file/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        if (fileName.endsWith(".png")) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(FileTools.download(fileName));
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(FileTools.download(fileName));
        } else if (fileName.endsWith(".pdf")) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(FileTools.download(fileName));
        } else {
            return null;
        }
    }

    @GetMapping("/public/file/download/{fileName}")
    public ResponseEntity<byte[]> downloadFilePublic(@PathVariable String fileName) {
        if (fileName.endsWith(".png")) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(FileTools.download(fileName));
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(FileTools.download(fileName));
        } else if (fileName.endsWith(".pdf")) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(FileTools.download(fileName));
        } else {
            return null;
        }
    }
}
