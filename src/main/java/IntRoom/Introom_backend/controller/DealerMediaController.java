package IntRoom.Introom_backend.controller;

import IntRoom.Introom_backend.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/dealer/media")
public class DealerMediaController {
    @Autowired
    private FileUploadService fileuploadService;

    @PostMapping
    public ResponseEntity<?> uploadMedia(
            @RequestParam("file" )MultipartFile file
            ) {
        try{
            String fileUrl = fileuploadService.uploadFile(file);
            return  ResponseEntity.ok().body("Uploaded:"  + fileUrl);
        }  catch (IOException e){
            return  ResponseEntity.internalServerError().body("upload failed :" + e.getMessage());
        }
    }

}
